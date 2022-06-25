package io.notbronken.sgtccapi.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Configuration
class WebFluxConfiguration : WebFluxConfigurer {
    companion object {
        const val nullToEmptyCollection = false
        const val nullToEmptyMap = false
        const val nullIsSameAsDefault = true
        const val singletonSupport = false
        const val strictNullChecks = false
        const val writeDatesAsTimesTamp = false
        const val writeDateTimesTampAsNanoseconds = false
        const val failOnUnknownProperties = false
    }

    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
        val module = KotlinModule.Builder()
            .configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection)
            .configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap)
            .configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault)
            .configure(KotlinFeature.SingletonSupport, singletonSupport)
            .configure(KotlinFeature.StrictNullChecks, strictNullChecks)
            .build()

        val mapper = ObjectMapper()
            .registerModule(JavaTimeModule())
            .registerModule(module)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, writeDatesAsTimesTamp)
            .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, writeDateTimesTampAsNanoseconds)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties)

        val decoder = Jackson2JsonDecoder(mapper)
        val encoder = Jackson2JsonEncoder(mapper)

        configurer.defaultCodecs().jackson2JsonDecoder(decoder)
        configurer.defaultCodecs().jackson2JsonEncoder(encoder)
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry
            .addMapping("/**")
            .allowedMethods("*");
    }
}

//@Component
//class HttpHeadersReactiveContext : WebFilter {
//
//    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
//        val request: ServerHttpRequest = exchange.request
//        val headers: HttpHeaders = request.headers
//        val localAddress = request.localAddress
//        val remoteAddress = request.remoteAddress
//
//        headers.map { println(it) }
//
//        println("localAddress: $localAddress")
//
//        println("-----------------------------")
//        println("remoteAddress: $remoteAddress")
//        println("remoteAddress address: ${remoteAddress?.address}")
//        println("remoteAddress hostName: ${remoteAddress?.hostName}")
//        println("remoteAddress port: ${remoteAddress?.port}")
//        println("remoteAddress hostString: ${remoteAddress?.hostString}")
//
//        return chain
//            .filter(exchange)
//            .contextWrite { context -> context.put(HttpHeaders::class.java, headers) }
//    }
//}