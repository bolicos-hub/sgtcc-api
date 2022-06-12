package io.notbronken.sgtccapi.common.api

import io.notbronken.sgtccapi.common.model.AuthDto
import io.notbronken.sgtccapi.common.model.MeDto
import io.notbronken.sgtccapi.common.model.TokenDto
import io.notbronken.sgtccapi.common.security.AuthService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    val authService: AuthService,
) {
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun auth(@RequestBody dto: AuthDto): ResponseEntity<TokenDto> {
        val token = authService.auth(dto)
        return ResponseEntity(token, HttpStatus.OK)
    }

    @GetMapping("/me")
    suspend fun me(@RequestHeader("authorization") header: String): ResponseEntity<MeDto> {
        val me = authService.me(header)
        return ResponseEntity(me, HttpStatus.OK)
    }
}