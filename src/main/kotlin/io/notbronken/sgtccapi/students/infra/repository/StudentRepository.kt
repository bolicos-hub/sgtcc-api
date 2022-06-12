package io.notbronken.sgtccapi.students.infra.repository

import io.notbronken.sgtccapi.students.infra.entity.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository: JpaRepository<Student, String> {
}