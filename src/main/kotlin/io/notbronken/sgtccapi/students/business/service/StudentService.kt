package io.notbronken.sgtccapi.students.business.service

import io.notbronken.sgtccapi.common.model.CreatedResourceWithId
import io.notbronken.sgtccapi.common.model.DeletedResource
import io.notbronken.sgtccapi.students.api.dto.CreateDto
import io.notbronken.sgtccapi.students.api.dto.ListDto
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface StudentService {
    fun create(dto: CreateDto): Mono<CreatedResourceWithId>
    fun delete(): Mono<DeletedResource>
    fun list(): Flux<List<ListDto>>
}