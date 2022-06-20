package io.notbronken.sgtccapi.semesters.infra.repository

import io.notbronken.sgtccapi.semesters.infra.entity.Semester
import org.springframework.data.jpa.repository.JpaRepository

interface SemesterRepository: JpaRepository<Semester, Long>