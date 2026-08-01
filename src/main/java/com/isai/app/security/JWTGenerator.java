package com.isai.app.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JWTGenerator {

    private static final Logger logger = LoggerFactory.getLogger(JWTAuthEntryPoint.class);

    @Value("${security.jwt.secret-key}")
    private String secreteKey;

    public String generarToken(Authentication authentication) {
        UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
        List<String> roles = userPrincipal.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Date currentDate = new Date();
        Date expiracionDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);
        return Jwts.builder()
                .subject(userPrincipal.getUsername())
                .claim("roles", roles)
                .issuedAt(currentDate)
                .expiration(expiracionDate)
                .signWith(getkey())
                .compact();
    }

    private Key getkey() {
        byte[] keyBytes = Decoders.BASE64.decode(secreteKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /*
     * Método genérico para extraer información específica (Claims) guardada dentro del JWT.
     */
    public <T> T getClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser()
                .verifyWith((SecretKey) getkey())
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public String getUsernameFromJWT(String token) {
        return getClaims(token, Claims::getSubject);
    }

    /*
     * Valida si un token recibido es auténtico, no ha caducado y no fue alterado.
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getkey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Token mal formado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token no soportado: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token expirado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token vacio: " + e.getMessage());
        } catch (SignatureException e) {
            logger.error("Firma JWT no válida: " + e.getMessage());
        }
        return false;
    }

    /**
     * Genera un nuevo token con una nueva fecha de expiración para refrescar la sesión del usuario.
     */
    public String refreshToken(Authentication authentication) {
        try {
            UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
            List<String> roles = userPrincipal.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            Date currentDate = new Date();
            Date expiracionDate = new Date(currentDate.getTime() + SecurityConstants.JWT_EXPIRATION);

            return Jwts.builder()
                    .subject(userPrincipal.getUsername())
                    .claim("roles", roles)
                    .issuedAt(currentDate)
                    .expiration(expiracionDate)
                    .signWith(getkey())
                    .compact();

        } catch (Exception e) {
            throw new RuntimeException("Error internal server: " + e.getMessage());
        }
    }
}
