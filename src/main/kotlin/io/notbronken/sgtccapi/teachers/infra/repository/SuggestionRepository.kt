package io.notbronken.sgtccapi.teachers.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface SuggestionRepository: JpaRepository<Board, Long> {
}