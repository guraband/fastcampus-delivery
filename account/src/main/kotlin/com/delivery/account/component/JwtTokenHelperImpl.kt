package com.delivery.account.component

import com.delivery.account.model.Token
import com.delivery.common.exception.ApiException
import com.delivery.common.status.TokenErrorStatusCode
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Component
class JwtTokenHelperImpl(
    @Value("\${token.secret.key}")
    private val secretKey: String,

    @Value("\${token.access-token.ttl-hour}")
    private val accessTokenTtlHour: Long,

    @Value("\${token.refresh-token.ttl-hour}")
    private val refreshTokenTtlHour: Long,
) : JwtTokenHelper {

    override fun issueAccessToken(data: Map<String, Any>): Token? {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val expiredLocalDateTime = LocalDateTime.now().plusHours(accessTokenTtlHour)
        val expiredAt = Date.from(
            expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant()
        )

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact()

        return Token(jwtToken, expiredLocalDateTime)
    }

    override fun issueRefreshToken(data: Map<String, Any>): Token? {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val expiredLocalDateTime = LocalDateTime.now().plusHours(refreshTokenTtlHour)
        val expiredAt = Date.from(
            expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant()
        )

        val jwtToken = Jwts.builder()
            .signWith(key, SignatureAlgorithm.HS256)
            .setClaims(data)
            .setExpiration(expiredAt)
            .compact()

        return Token(jwtToken, expiredLocalDateTime)
    }

    override fun validate(token: String): Map<String, Any>? {
        val key = Keys.hmacShaKeyFor(secretKey.toByteArray())

        val parser = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()

        return try {
            HashMap(parser.parseClaimsJws(token).body)
        } catch (e: Exception) {
            when (e) {
                is SignatureException -> {
                    throw ApiException(TokenErrorStatusCode.INVALID_TOKEN, e)
                }

                is ExpiredJwtException -> {
                    throw ApiException(TokenErrorStatusCode.EXPIRED_TOKEN, e)
                }

                else -> {
                    throw ApiException(TokenErrorStatusCode.TOKEN_EXCEPTION, e)
                }
            }
        }
    }
}