package io.notbronken.sgtccapi.proposals.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface ProposalRepository: JpaRepository<Board, Long> {
}