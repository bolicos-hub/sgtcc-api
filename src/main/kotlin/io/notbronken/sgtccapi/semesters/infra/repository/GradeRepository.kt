package io.notbronken.sgtccapi.semesters.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import io.notbronken.sgtccapi.semesters.infra.entity.GradeCompositeId
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository: JpaRepository<Board, GradeCompositeId> {
}