package com.otc.hubs.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {
    // 设置 token 过期时间(单位:毫秒)：目前为 60 秒
    private static final long ttl = 60 * 1000;
    // Signature 签名
    private static final String signature = "tang";
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public static String createToken(String userId) {

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS256")
                .setExpiration(new Date(System.currentTimeMillis() + ttl))
                .setIssuedAt(new Date())
                .claim("userId", userId)
                .signWith(key)
                .compact();
    }

    public static Object parseToken(String token) {
        if (token == null) {
            return null;
        }

        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

        } catch (Exception e) {
            return null;
        }
    }
}
