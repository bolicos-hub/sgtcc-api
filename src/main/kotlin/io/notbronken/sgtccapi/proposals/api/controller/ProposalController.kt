package io.notbronken.sgtccapi.proposals.api.controller

import io.notbronken.sgtccapi.common.annotation.MappingJson
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.proposals.api.dto.ProposalCreateDto
import io.notbronken.sgtccapi.proposals.business.service.ProposalService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.util.logging.Logger

@RestController
@RequestMapping("/api/proposals")
class ProposalController(
    val proposalService: ProposalService,
) {
    companion object {
        private val LOGGER = Logger.getLogger(ProposalController::class.simpleName)
    }

    @MappingJson(method = [RequestMethod.POST], code = HttpStatus.CREATED)
    fun create(@RequestBody dto: ProposalCreateDto): Mono<CreatedResource> = proposalService.create(dto)

}