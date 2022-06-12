package io.notbronken.sgtccapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.hateoas.config.EnableHypermediaSupport
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType
import org.springframework.hateoas.support.WebStack
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.web.reactive.config.EnableWebFlux

@EnableWebFlux
@EnableWebFluxSecurity
@SpringBootApplication
@ComponentScan("io.notbronken.sgtccapi.config")
@EnableHypermediaSupport(
	stacks = [ WebStack.WEBFLUX ],
	type = [
		HypermediaType.HAL,
		HypermediaType.UBER,
		HypermediaType.HAL_FORMS,
		HypermediaType.COLLECTION_JSON,
	]
)
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
