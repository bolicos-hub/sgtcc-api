package io.notbronken.sgtccapi.semesters.infra.repository

import org.springframework.data.jpa.repository.JpaRepository
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

interface ClassRepository: JpaRepository<Group, Long> {
}