package com.delivery.api.helper;

import com.delivery.common.dto.Token;
import com.delivery.common.exception.ApiException;
import com.delivery.common.status.TokenErrorStatusCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenHelper {

    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-token.ttl-hour}")
    private Long accessTokenTtlHour;

    @Value("${token.refresh-token.ttl-hour}")
    private Long refreshTokenTtlHour;

    public Token issueAccessToken(Map<String, Object> data) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var expiredLocalDatetime = LocalDateTime.now().plus(accessTokenTtlHour, ChronoUnit.HOURS);
        var expiredAt = Date.from(
                expiredLocalDatetime.atZone(ZoneId.systemDefault()).toInstant()
        );

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();
        return new Token(jwtToken, expiredLocalDatetime);
    }

    public Token issueRefreshToken(Map<String, Object> data) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var expiredLocalDatetime = LocalDateTime.now().plus(refreshTokenTtlHour, ChronoUnit.HOURS);
        var expiredAt = Date.from(
                expiredLocalDatetime.atZone(ZoneId.systemDefault()).toInstant()
        );

        var jwtToken = Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();
        return new Token(jwtToken, expiredLocalDatetime);
    }

    public Map<String, Object> validate(String token) {
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());

        var parser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        try {
            return new HashMap<>(parser.parseClaimsJws(token).getBody());
        } catch (SignatureException se) {
            throw new ApiException(TokenErrorStatusCode.INVALID_TOKEN, se);
        } catch (ExpiredJwtException eje) {
            throw new ApiException(TokenErrorStatusCode.EXPIRED_TOKEN, eje);
        } catch (Exception e) {
            throw new ApiException(TokenErrorStatusCode.TOKEN_EXCEPTION, e);
        }
    }
}
