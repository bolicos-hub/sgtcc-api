package io.notbronken.sgtccapi.common.api

import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.error.BusinessException
import io.notbronken.sgtccapi.common.model.ErrorMessageModel
import org.springframework.core.codec.DecodingException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import java.util.logging.Logger

@ControllerAdvice
class HandlerControllerAdvice {
    companion object {
        private val LOGGER = Logger.getLogger(HandlerControllerAdvice::class.simpleName)
        const val causeMessage = "CAUSE OF EXCEPTION"
        const val defaultMessage = "Internal Server Error"
        val status = HttpStatus.INTERNAL_SERVER_ERROR
    }

    fun factoryErrorMessage(status: HttpStatus, message: String) = ErrorMessageModel(status.value(), message)
    fun factoryErrorCause(throwable: Throwable) {
        val causeException = throwable.toString()
        LOGGER.info(causeMessage)
        LOGGER.warning(causeException)
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val message = exception.message ?: defaultMessage
        val errorMessage = factoryErrorMessage(status, message)
        return ResponseEntity(errorMessage, status)
    }

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(exception: RuntimeException): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }

    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(exception: BusinessException): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val status = HttpStatus.UNPROCESSABLE_ENTITY
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }
    @ExceptionHandler(BusinessEntityNotFoundException::class)
    fun handleBusinessEntityNotFoundException(exception: BusinessEntityNotFoundException): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val status = HttpStatus.NOT_FOUND
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }
    @ExceptionHandler(DecodingException::class)
    fun handleDecodingException(exception: DecodingException): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val status = HttpStatus.BAD_REQUEST
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingKotlinParameterException(exception: MissingKotlinParameterException): ResponseEntity<ErrorMessageModel> {
        factoryErrorCause(exception)
        val status = HttpStatus.BAD_REQUEST
        val errorMessage = ErrorMessageModel(status.value(), exception.message)
        return ResponseEntity(errorMessage, status)
    }

//    @ExceptionHandler(ResponseStatusException::class)
//    fun handleResponseStatusException(
//        exception: BusinessException,
//        server: ServerWebExchange
//    ): ResponseEntity<ErrorMessageModel> {
//        factoryErrorCause(exception)
//        val status = server.response.statusCode ?: HttpStatus.NOT_FOUND
//        val errorMessage = ErrorMessageModel(status.value(), exception.message)
//        return ResponseEntity(errorMessage, status)
//    }
}