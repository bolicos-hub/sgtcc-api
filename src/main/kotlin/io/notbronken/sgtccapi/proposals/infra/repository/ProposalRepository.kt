package io.notbronken.sgtccapi.proposals.infra.repository

import io.notbronken.sgtccapi.proposals.infra.entity.Proposal
import org.springframework.data.jpa.repository.JpaRepository

interface ProposalRepository: JpaRepository<Proposal, Long>