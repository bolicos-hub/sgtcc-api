package io.notbronken.sgtccapi.common.model

data class TokenDto(val token: String)
data class AuthDto(val username: String, val password: String)
data class MeDto(val username: String, val name: String, val roles: List<String>)
data class CreatedResource(val id: Long)
data class CreatedResourceWithId(val id: String)
data class DeletedResource(val isDelete: Boolean)
data class ErrorMessageModel(
    var status: Int? = null,
    var message: String? = null
)