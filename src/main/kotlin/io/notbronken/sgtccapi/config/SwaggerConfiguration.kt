package io.notbronken.sgtccapi.config

import io.swagger.v3.core.converter.ModelConverters
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.providers.ObjectMapperProvider
import org.springdoc.webflux.core.converters.WebFluxSupportConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfiguration {

    @Bean
    fun api(): GroupedOpenApi {
        return GroupedOpenApi.builder()
            .group("SGTCC API")
            .packagesToScan("io.notbronken.sgtccapi")
            .build()
    }

    @Bean
    fun configOpenAPI(mapper: ObjectMapperProvider): OpenAPI? {
//        ModelConverters
//            .getInstance()
//            .addConverter(WebFluxSupportConverter(mapper))

        return OpenAPI()
            .info(getApiInfo())
    }

    private fun getApiInfo(): Info {
        val version = "0.0.1-SNAPSHOT"
        val contact = Contact()
            .name("Ana Lucia Bolico")
            .url("https://github.com/analuciabolico")
            .email("analuciabolico.dev@gmail.com")

        return Info()
            .title("SgTCC API")
            .description("Sistema de Gerenciamento de TCC")
            .contact(contact)
            .version(version)
    }

}