package io.notbronken.sgtccapi.students.infra.repository

import io.notbronken.sgtccapi.students.infra.entity.Student
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StudentRepository: JpaRepository<Student, String> {
    fun findAllByOrderByCreatedAtDesc(): List<Student>
}