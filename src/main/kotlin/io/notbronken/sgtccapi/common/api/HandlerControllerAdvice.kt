package io.notbronken.sgtccapi.common.api

import io.notbronken.sgtccapi.common.error.BusinessException
import io.notbronken.sgtccapi.common.model.ErrorMessageModel
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange

@ControllerAdvice
class HandlerControllerAdvice {
    companion object {
        const val defaultMessage = "Internal Server Error"
        val status = HttpStatus.INTERNAL_SERVER_ERROR
    }

    fun factoryErrorMessage(status: HttpStatus, message: String) = ErrorMessageModel(status.value(), message)

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorMessageModel> {
        val message = exception.message ?: defaultMessage
        val errorMessage = factoryErrorMessage(status, message)
        return ResponseEntity(errorMessage, status)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<ErrorMessageModel> {
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ErrorMessageModel> {
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }

//    @ExceptionHandler(ResponseStatusException::class)
//    fun handleResponseStatusException(
//        exception: BusinessException,
//        server: ServerWebExchange
//    ): ResponseEntity<ErrorMessageModel> {
//        val status = server.response.statusCode ?: HttpStatus.NOT_FOUND
//        val errorMessage = ErrorMessageModel(status.value(), exception.message)
//        return ResponseEntity(errorMessage, status)
//    }
}