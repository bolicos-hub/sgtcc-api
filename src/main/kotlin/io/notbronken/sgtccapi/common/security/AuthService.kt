package io.notbronken.sgtccapi.common.security

import io.notbronken.sgtccapi.common.model.AuthDto
import io.notbronken.sgtccapi.common.model.MeDto
import io.notbronken.sgtccapi.common.model.TokenDto
import org.springframework.stereotype.Service

interface AuthService {
    fun auth(dto: AuthDto): TokenDto
    fun me(token: String): MeDto
}

@Service
class AuthServiceImpl: AuthService {

    override fun auth(dto: AuthDto): TokenDto {
        return TokenDto("Tstes")
    }

    override fun me(token: String): MeDto {
        return MeDto(username = "Teste", name = "Teste", roles = emptyList())
    }
}