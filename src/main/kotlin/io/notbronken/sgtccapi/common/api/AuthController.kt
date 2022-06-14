package io.notbronken.sgtccapi.common.api

import io.notbronken.sgtccapi.common.model.AuthDto
import io.notbronken.sgtccapi.common.model.MeDto
import io.notbronken.sgtccapi.common.model.TokenDto
import io.notbronken.sgtccapi.common.security.AuthService
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService,
) {
    @PostMapping(
        value = ["/token"],
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseStatus(HttpStatus.CREATED)
    fun auth(@RequestBody dto: AuthDto): Mono<TokenDto> {
        val token = authService.auth(dto)
        return Mono.just(token)
    }

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    fun me(@RequestHeader(HttpHeaders.AUTHORIZATION) header: String): Mono<MeDto> {
        val me = authService.me(header)
        return Mono.just(me)
    }
}