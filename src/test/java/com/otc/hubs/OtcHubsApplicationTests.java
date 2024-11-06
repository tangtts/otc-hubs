package com.otc.hubs;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.security.Key;
import java.util.Date;

@SpringBootTest
class OtcHubsApplicationTests {
  private static final long ttl = 60 * 1000;
  // Signature 签名
  private static final String signature = "tang";

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Test
  public  void createToken() {

    // 设置 JWT 的过期时间（例如，1小时）
    long ttl = 1000 * 60 * 60; // 1小时
    // 生成签名密钥
    Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    // 构建 JWT
    String jwt = Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS256")
            .setExpiration(new Date(System.currentTimeMillis() + ttl))
            .setIssuedAt(new Date())
            .claim("userId", "3")
            .signWith(key)
            .compact();

    // 输出生成的 JWT
    System.out.println("Generated JWT: " + jwt);
  }

    @Test
    public void test() {

    }
  }
