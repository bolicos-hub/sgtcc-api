package io.notbronken.sgtccapi.semesters.api.dto

import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.semesters.infra.entity.Semester
import io.notbronken.sgtccapi.students.infra.enumeration.StudentStatus
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import java.time.ZonedDateTime
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

data class ClassCreateDto(
    val name: String,
    val semesterId: Long,
    val teacherId: String,
    val students: List<String>,
) {
    fun toEntity(semester: Semester, teacher: Teacher) = Group(
        name = name,
        semester = semester,
        teacher = teacher,
    )
    fun toUpdate() = ClassUpdateDto(
        name = name,
        teacherId = teacherId,
        students = students,
    )

}

data class ClassUpdateDto(
    val name: String,
    val teacherId: String,
    val students: List<String>,
) {
    fun toEntity(semester: Semester, teacher: Teacher) = Group(
        name = name,
        semester = semester,
        teacher = teacher,
    )
}

data class ClassDto(
    val id: Long,
    val name: String,
    val semester: SemesterDto,
    val teacherId: String,
    val teacherName: String,
    val students: List<GradeDto>,
    val createdAt: ZonedDateTime,
) {
    fun toEntity(teacher: Teacher, students: Set<Grade>) = Group(
        id = id,
        name = name,
        semester = semester.toEntity(),
        teacher = teacher,
        students = students,
        createdAt = createdAt,
    )
}

data class ClassAddStudentsDto(
    val students: List<String>,
)

data class ClassNotesDto(
    val studentId: String,
    val note: Double,
)

data class GradeDto(
    val registration: String,
    val name: String,
    val email: String,
    val phone: String,
    val status: StudentStatus,
    val createdAt: ZonedDateTime,
    val note: Double?,
)