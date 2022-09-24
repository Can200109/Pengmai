package com.example.pengmai.utill;


import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static cn.hutool.jwt.JWTUtil.parseToken;

@Component
public class JWTUtill {

    private String secretKey = "ak47";
    private SimpleDateFormat time=new SimpleDateFormat("yyyy MM dd HH mm ss");
    public String createJWT(String id, String subject, long ttlMillis, Map<String, Object> map) throws Exception {
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject) // 发行者
                .setId(id)
                .setIssuedAt(new Date(System.currentTimeMillis())) // 发行时间
                .signWith(SignatureAlgorithm.HS256, secretKey) // 签名类型 与 密钥
                .compressWith(CompressionCodecs.DEFLATE);// 对载荷进行压缩
        if (!CollectionUtils.isEmpty(map)) {
            builder.setClaims(map);
        }
        if (ttlMillis > 0) {
            builder.setExpiration(new Date(System.currentTimeMillis() + ttlMillis));
        }
        return builder.compact();
    }


    public Claims parseJwt(String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置标识名
                    .parseClaimsJws(token)  //解析token
                    .getBody();
        } catch (ExpiredJwtException e) {
            claims = e.getClaims();
        }
        return claims;
    }
    public Boolean isTokenExpired(String token) {
        //不管是否过期，都返回claims对象
        Claims claims = this.parseJwt(token);
        Date expiration = claims.getExpiration();
        //和当前时间进行对比来判断是否过期
        return new Date(System.currentTimeMillis()).after(expiration);
    }
}