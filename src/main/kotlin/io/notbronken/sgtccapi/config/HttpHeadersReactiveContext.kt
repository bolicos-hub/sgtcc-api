package io.notbronken.sgtccapi.config

import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class HttpHeadersReactiveContext : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request: ServerHttpRequest = exchange.request
        val headers: HttpHeaders = request.headers
//        val response = exchange.response.statusCode


//        println(request.toString())
//        println(headers.toString())
//        println(response.toString())


//        return chain.filter(exchange)
//        return chain.filter { ex -> ex. }
        

//        val serverHttpRequest = exchange.request
//        val mutate = exchange.mutate()
//        val mutatedRequest = apply(serverHttpRequest)
//        val mutatedExchange = mutate.request(mutatedRequest).build()
//        return chain.filter(mutatedExchange)


//        val request: ServerHttpRequest = exchange.request
//        val headers: HttpHeaders = request.headers


        return chain
            .filter(exchange)
            .contextWrite { context -> context.put(HttpHeaders::class.java, headers) }
    }
}