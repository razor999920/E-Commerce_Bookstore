package com.mnp.store.common.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthenticationFilter(JwtTokenProvider tokenProvider) {
        this.jwtTokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtTokenProvider.resolveCookieToken(httpServletRequest);

            if (!StringUtils.hasText(jwt))
                jwt = jwtTokenProvider.resolveBearerToken(httpServletRequest);

            if (StringUtils.hasText(jwt) &&
                    jwtTokenProvider.validate(jwt) &&
                    jwtTokenProvider.getTokenType(jwt) == JwtTokenType.ACCESS_TOKEN) {
                Authentication authentication = jwtTokenProvider.getAuthentication(jwt);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            e.printStackTrace();
            SecurityContextHolder.clearContext();
            httpServletResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        }
    }
}
