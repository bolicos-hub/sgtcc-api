package io.notbronken.sgtccapi.teachers.infra.repository

import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TeacherRepository: JpaRepository<Teacher, String>