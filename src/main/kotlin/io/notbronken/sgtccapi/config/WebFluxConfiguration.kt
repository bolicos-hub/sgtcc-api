package io.notbronken.sgtccapi.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.context.annotation.Configuration
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder

@Configuration
class WebFluxConfiguration : WebMvcConfigurer {

    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
    }


//    override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
//        val module = KotlinModule.Builder()
//            .configure(KotlinFeature.NullToEmptyCollection, nullToEmptyCollection)
//            .configure(KotlinFeature.NullToEmptyMap, nullToEmptyMap)
//            .configure(KotlinFeature.NullIsSameAsDefault, nullIsSameAsDefault)
//            .configure(KotlinFeature.SingletonSupport, singletonSupport)
//            .configure(KotlinFeature.StrictNullChecks, strictNullChecks)
//            .build()
//
//        val mapper = ObjectMapper()
//            .registerModule(JavaTimeModule())
//            .registerModule(module)
//            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
//
//        val decoder = Jackson2JsonDecoder(mapper)
//        val encoder = Jackson2JsonEncoder(mapper)
//
//        configurer.defaultCodecs().jackson2JsonDecoder(decoder)
//        configurer.defaultCodecs().jackson2JsonEncoder(encoder)
//    }

//    override fun configureArgumentResolvers(configurer: ArgumentResolverConfigurer) {
//        configurer.addCustomResolver(ReactivePageableHandlerMethodArgumentResolver())
//    }

//    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
//        registry
//            .addResourceHandler("swagger-ui.html")
//            .addResourceLocations("classpath:/META-INF/resources/")
//    }

//    override fun configurePathMatch(configurer: PathMatchConfigurer) {
//        configurer
//            .setUseCaseSensitiveMatch(true)
//            .setUseTrailingSlashMatch(false)
//            .addPathPrefix("/api",
//                HandlerTypePredicate.forAnnotation(RestController::class.java))
//    }


    companion object {
        const val nullToEmptyCollection = false
        const val nullToEmptyMap = false
        const val nullIsSameAsDefault = true
        const val singletonSupport = false
        const val strictNullChecks = false
    }
}