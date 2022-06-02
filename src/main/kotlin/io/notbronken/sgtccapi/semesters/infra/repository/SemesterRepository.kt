package io.notbronken.sgtccapi.semesters.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface SemesterRepository: JpaRepository<Board, Long> {
}