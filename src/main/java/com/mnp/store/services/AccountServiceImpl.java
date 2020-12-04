package com.mnp.store.services;

import com.mnp.store.common.security.CookieProvider;
import com.mnp.store.common.security.jwt.JwtToken;
import com.mnp.store.common.security.jwt.JwtTokenProvider;
import com.mnp.store.contracts.authorization.AccountService;
import com.mnp.store.contracts.authorization.dtos.LoginRequestDto;
import com.mnp.store.contracts.authorization.dtos.RefreshTokenDto;
import com.mnp.store.contracts.users.UserService;
import com.mnp.store.contracts.users.dtos.InvalidCredentialsException;
import com.mnp.store.contracts.users.dtos.UserResponseDto;
import com.mnp.store.domain.constants.ROLE;
import com.mnp.store.domain.constants.RoleConstants;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final CookieProvider cookieProvider;
    private final UserService userService;

    public AccountServiceImpl(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, CookieProvider cookieProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.cookieProvider = cookieProvider;
        this.userService = userService;
    }

    @Override
    public UserResponseDto login(LoginRequestDto request, HttpHeaders httpHeaders) {
        String login = request.getLogin();
        String password = request.getPassword();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication = authenticationManager.authenticate(authToken);

        if (!authentication.isAuthenticated())
            throw new InvalidCredentialsException();

        UserResponseDto user = userService.getUserByLogin(request.getLogin()).orElseThrow(InvalidCredentialsException::new);

        JwtToken accessToken = tokenProvider.generateAccessToken(authentication);
        HttpCookie accessCookie = cookieProvider.createAccessTokenCookie(accessToken.getTokenValue(), accessToken.getDuration());

        JwtToken refreshToken = tokenProvider.generateRefreshToken(authentication);
        HttpCookie refreshCookie = cookieProvider.createRefreshTokenCookie(refreshToken.getTokenValue(), refreshToken.getDuration());

        httpHeaders.add(HttpHeaders.SET_COOKIE, accessCookie.toString());
        httpHeaders.add(HttpHeaders.SET_COOKIE, refreshCookie.toString());

        return user;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, HttpHeaders httpHeaders) {
        if (!isAuthenticated()) {
            return;
        }

        HttpCookie removeAccessCookie = cookieProvider.deleteAccessTokenCookie();
        httpHeaders.add(HttpHeaders.SET_COOKIE, removeAccessCookie.toString());

        HttpCookie removeRefreshCookie = cookieProvider.deleteRefreshTokenCookie();
        httpHeaders.add(HttpHeaders.SET_COOKIE, removeRefreshCookie.toString());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        new SecurityContextLogoutHandler().logout(request, response, auth);
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public RefreshTokenDto refreshToken(String refreshToken, HttpHeaders httpHeaders) {
        if (!StringUtils.hasText(refreshToken) || !tokenProvider.validate(refreshToken))
            throw new JwtException("Invalid refresh token");

        Authentication authentication = tokenProvider.getAuthentication(refreshToken);
        JwtToken accessToken = tokenProvider.generateAccessToken(authentication);
        HttpCookie accessCookie = cookieProvider.createAccessTokenCookie(accessToken.getTokenValue(), accessToken.getDuration());
        httpHeaders.add(HttpHeaders.SET_COOKIE, accessCookie.toString());
        return new RefreshTokenDto(true, "Access token is refreshed.");
    }

    public boolean isAuthenticated() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth != null &&
                auth.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .noneMatch(ROLE.ANONYMOUS.name()::equals);
    }
}
