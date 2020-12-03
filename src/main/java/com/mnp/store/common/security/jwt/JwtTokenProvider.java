package com.mnp.store.common.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    @Value("${mnp.jwt.secret}")
    private String jwtSecret;

    @Value(("${mnp.jwt.accessTokenExpiration}"))
    private Long jwtExpiration;

    @Value(("${mnp.jwt.refreshTokenExpiration}"))
    private Long refreshExpiration;

    private Key key;

    @PostConstruct
    public void init() {
        // make key
        //
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecret);
        this.key = Keys.hmacShaKeyFor(keyBytes);

        // convert to milliseconds.
        //
        this.jwtExpiration *= 1000;
        this.jwtExpiration *= 1000;
    }

    public boolean validate(String token) {
        if (!StringUtils.hasText(token))
            return false;

        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("invalid jwt token");
            e.printStackTrace();
        }
        return false;
    }

    public JwtToken generateAccessToken(Authentication authentication) {
        return generateJwtToken(authentication, JwtTokenType.ACCESS_TOKEN, jwtExpiration);
    }

    public JwtToken generateRefreshToken(Authentication authentication) {
        return generateJwtToken(authentication, JwtTokenType.REFRESH_TOKEN, refreshExpiration);
    }

    public JwtToken generateJwtToken(Authentication authentication, JwtTokenType tokenType, long expiration) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        long duration = now.getTime() + expiration;
        Date expiryDate = new Date(duration);

        String token = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth", authorities)
                .claim("token_type", tokenType.name())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

        return new JwtToken(
                tokenType,
                token,
                duration,
                LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault())
        );
    }

    public Authentication getAuthentication(String token) {
        if (!StringUtils.hasText(token))
            return null;

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Collection<? extends GrantedAuthority> authorities;

        authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    public String resolveBearerToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (!StringUtils.hasText(bearerToken) || !bearerToken.startsWith("Bearer "))
            return null;

        String token = bearerToken.substring(7);
        return StringUtils.hasText(token) ? decrypt(token) : null;
    }

    public JwtTokenType getTokenType(String token) {
        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token);
        return JwtTokenType.valueOf(claims.getBody().get("token_type", String.class));
    }

    public String resolveCookieToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(JwtTokenType.ACCESS_TOKEN.name())) {
                String accessToken = cookie.getValue();
                return StringUtils.hasText(accessToken) ? decrypt(accessToken) : null;
            }
        }

        return null;
    }

    public String encrypt(String token) {
        if (!StringUtils.hasText(token))
            return null;

        try {
            SecretKeySpec key = getKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(token.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String decrypt(String token) {
        if (!StringUtils.hasText(token))
            return null;

        try {
            SecretKeySpec key = getKey();
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] plaintext = Base64.getDecoder().decode(token);
            return new String(cipher.doFinal(plaintext));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private SecretKeySpec getKey() {
        try {
            byte[] encKeyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            encKeyBytes = sha.digest(encKeyBytes);
            encKeyBytes = Arrays.copyOf(encKeyBytes, 16);
            return new SecretKeySpec(encKeyBytes, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
