package io.notbronken.sgtccapi.boards.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardRepository: JpaRepository<Board, Long> {
}