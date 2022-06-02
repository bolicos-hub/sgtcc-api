package io.notbronken.sgtccapi.students.infra.repository

import io.notbronken.sgtccapi.boards.infra.entity.Board
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Board, String> {
}