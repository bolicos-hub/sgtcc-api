package io.notbronken.sgtccapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SgtccApiApplication

fun main(args: Array<String>) {
	runApplication<SgtccApiApplication>(*args)
}
