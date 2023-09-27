package com.delivery.apigw.filter

import com.delivery.apigw.common.Log
import com.delivery.common.dto.Token
import com.delivery.common.dto.TokenValidationRequest
import com.delivery.common.dto.TokenValidationResponse
import com.delivery.common.exception.ApiException
import com.delivery.common.status.TokenErrorStatusCode
import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpStatusCode
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono


@Component
class ServiceApiPrivateFilter(
    @Value("\${internal-api.account.url}")
    private val accountApiUrl: String,
) : AbstractGatewayFilterFactory<ServiceApiPrivateFilter.Config>(Config::class.java) {
    companion object : Log

    class Config

    private val HEADER_KEY_AUTHORIZATION = "authorization"

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val uri = exchange.request.uri
            log.info("service api private filter route uri : {}", uri)

            // 토큰 유무 확인
            var headers = exchange.request.headers[HEADER_KEY_AUTHORIZATION] ?: listOf()
            val token = if (headers.isEmpty()) {
                throw ApiException(TokenErrorStatusCode.TOKEN_NOT_FOUND)
            } else {
                headers.get(0)
            }

            log.info("authorization token : {}", token)

            // 토큰 유효성 체크
            val accountApiUrl = UriComponentsBuilder
                .fromUriString(accountApiUrl)
                .path("/internal-api/token/validate")
                .build()
                .encode()
                .toUriString()

            val webclient = WebClient.builder().baseUrl(accountApiUrl).build()

            val request = TokenValidationRequest(
                token = Token(token = token)
            )

            webclient.post()
                .body(Mono.just(request), object : ParameterizedTypeReference<TokenValidationRequest>() {})
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .onStatus({ status: HttpStatusCode ->
                    status.isError
                }, { response: ClientResponse ->
                    response.bodyToMono(object : ParameterizedTypeReference<Any>() {})
                        .flatMap { error ->
                            log.error("{}", error)

                            Mono.error(ApiException(TokenErrorStatusCode.TOKEN_EXCEPTION))
                        }
                })
                .bodyToMono(object : ParameterizedTypeReference<TokenValidationResponse>() {})
                .flatMap { response ->
                    log.info("account api response : {}", response)


                    val mono = chain.filter(exchange)
                    mono
                }
        }
    }
}