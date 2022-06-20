package io.notbronken.sgtccapi.teachers.infra.repository

import io.notbronken.sgtccapi.teachers.infra.entity.Suggestion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SuggestionRepository: JpaRepository<Suggestion, Long> {
    fun findAllByTeacher_Registration(registration: String): List<Suggestion>
}