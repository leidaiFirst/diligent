package com.dl.diligent.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author dail
 * @since 2018/11/22 14:33
 */

@Slf4j
public class TokenHelper {

    private static final String TOKEN_SECRET = "secret";

    private static final String TOKEN_EXPIRES = "2000";

    private static final String DEFAULT_TOKEN_EXPIREIN = "2592000";

    private static final String TOKEN_SUBJECT = "auth";


    /**
     * 生成Token签名
     *
     * @param userId 用户ID
     * @return 签名
     */
    public static String token(long userId, String from) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        claims.put("from", from);

        long nowMillis = System.currentTimeMillis();

        return Jwts.builder()
                .setId(String.valueOf(userId))
                .setClaims(claims)
                .setSubject(TOKEN_SUBJECT)
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .setIssuedAt(new Date(nowMillis))
                .setExpiration(DateHelper.getDate(
                        Calendar.SECOND,
                        Integer.parseInt(Optional.ofNullable(TOKEN_EXPIRES).orElse(DEFAULT_TOKEN_EXPIREIN))))
                .compact();
    }


    /**
     * 解析Token
     *
     * @param token 签名
     * @return 签名信息
     */
    public static Claims parser(String token) {
        return Jwts.parser()
                .setSigningKey(TOKEN_SECRET)
                .parseClaimsJws(token).getBody();
    }
}
