package io.notbronken.sgtccapi.common.security

import io.notbronken.sgtccapi.common.model.AuthDto
import io.notbronken.sgtccapi.common.model.MeDto
import io.notbronken.sgtccapi.common.model.TokenDto
import org.springframework.stereotype.Service

interface AuthService {
    suspend fun auth(dto: AuthDto): TokenDto
    suspend fun me(token: String): MeDto
}

@Service
class AuthServiceImpl: AuthService {

    override suspend fun auth(dto: AuthDto): TokenDto {
        return TokenDto("Tstes")
    }

    override suspend fun me(token: String): MeDto {
        return MeDto(username = "Teste", name = "Teste", roles = emptyList())
    }
}