package io.notbronken.sgtccapi.semesters.infra.repository

import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.semesters.infra.entity.GradeCompositeId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GradeRepository: JpaRepository<Grade, GradeCompositeId> {
    fun findAllByGroup_IdOrderByGroup(ids: Long): List<Grade>
}