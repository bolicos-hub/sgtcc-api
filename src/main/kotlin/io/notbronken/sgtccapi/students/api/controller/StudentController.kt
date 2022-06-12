package io.notbronken.sgtccapi.students.api.controller

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto
import io.notbronken.sgtccapi.students.business.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/students")
class StudentController(
    val studentService: StudentService,
) {
    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun create(@RequestBody dto: CreateDto): ResponseEntity<CreatedResourceWithId> {
        val createdResource = studentService.create(dto)
        return ResponseEntity(createdResource, HttpStatus.CREATED)
    }

    @GetMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    suspend fun findAll(): ResponseEntity<List<ListDto>> {
        val list = studentService.list()
        return ResponseEntity(list, HttpStatus.OK)
    }

    companion object {
        private val LOGGER = ""
    }
}



//runCatching {
//    val exception = Supplier<IllegalCallerException> {
//        IllegalCallerException("Circuit breaker $name not exists")
//    }
//
//    val circuitBreaker = circuitBreakerRegistry.allCircuitBreakers.find {
//        it.name == name
//    }.getOrElseThrow(exception)
//
//    circuitBreaker.transitionToClosedState()
//}.fold(
//onSuccess = {
//    ResponseEntity("Circuit breaker $name switched to CLOSED state.", HttpStatus.OK)
//},
//onFailure = { throwable ->
//    ResponseEntity("There was an error: ${throwable.message}.", HttpStatus.INTERNAL_SERVER_ERROR)
//}
//)
//}