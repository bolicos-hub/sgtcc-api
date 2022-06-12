package io.notbronken.sgtccapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity


@EnableWebSecurity
@SpringBootApplication
@ComponentScan("io.notbronken.sgtccapi.config")
class Application

fun main(args: Array<String>) {
	runApplication<Application>(*args)
}
