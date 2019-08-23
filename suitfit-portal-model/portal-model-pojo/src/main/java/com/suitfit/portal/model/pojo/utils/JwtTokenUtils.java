package com.suitfit.portal.model.pojo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {
    private static final String secret = "SECRET";
    private static final long expire = 60 * 60 * 24 * 1000;

    private static String generateToken(Map<String, Object> claims) {
        Date expireDate = new Date(System.currentTimeMillis() + expire);
        return Jwts.builder().setClaims(claims).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    private static Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public static String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        claims.put("created", new Date());
        return generateToken(claims);
    }

    public static String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public static boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expireDate = claims.getExpiration();
            return expireDate.before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    public static String refreshToken(String token) {
        String refreshToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put("created", new Date());
            refreshToken = generateToken(claims);
        } catch (Exception e) {
            refreshToken = null;
        }
        return refreshToken;
    }

    public static boolean validateToken(String token, String username) {
        String sub = getUsernameFromToken(token);
        return username.equals(sub) && !isTokenExpired(token);

    }
}
