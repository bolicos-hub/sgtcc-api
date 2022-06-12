package io.notbronken.sgtccapi.common.model

data class CreatedResource(val id: Long)
data class CreatedResourceWithId(val id: String)
data class DeletedResource(val isDelete: Boolean)
data class ErrorMessageModel(
    var status: Int? = null,
    var message: String? = null
)