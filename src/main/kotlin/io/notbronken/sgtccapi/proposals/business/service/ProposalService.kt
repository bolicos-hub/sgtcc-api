package io.notbronken.sgtccapi.proposals.business.service

import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.proposals.api.dto.ProposalCreateDto
import io.notbronken.sgtccapi.proposals.infra.repository.ProposalRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

interface ProposalService {
    fun create(dto: ProposalCreateDto): Mono<CreatedResource>
}

@Service
class ProposalServiceImpl(
    private val proposalRepository: ProposalRepository,
): ProposalService {
    override fun create(dto: ProposalCreateDto): Mono<CreatedResource> {
        TODO("Not yet implemented")
    }

}