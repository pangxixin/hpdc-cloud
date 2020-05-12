package com.hpdc.test.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

public class ParseJwtTest {
    public static void main(String[] args) {
        Claims jwtBodyClaims = Jwts.parser()
                .setSigningKey("hpdc")
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiJweHgiLCJpYXQiOjE1ODI4NDkxNTUsImV4cCI6MTU4Mjg0OTIxNSwicm9sZSI6ImFkbWluIn0.iPvEYz7m5oxTl3b0y6EPKyNihYLYTcMpu9AOr4mWIZA")
                .getBody();
        System.out.println("用户ID：" + jwtBodyClaims.getId());
        System.out.println("用户名：" + jwtBodyClaims.getSubject());
        System.out.println("登录时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jwtBodyClaims.getIssuedAt()));
        System.out.println("过期时间：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(jwtBodyClaims.getExpiration()));
        System.out.println("登录角色：" + jwtBodyClaims.get("role"));
    }
}
