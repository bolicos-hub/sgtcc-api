package io.notbronken.sgtccapi.semesters.infra.repository

import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.semesters.infra.entity.GradeCompositeId
import org.springframework.data.jpa.repository.JpaRepository

interface GradeRepository: JpaRepository<Grade, GradeCompositeId> {
}