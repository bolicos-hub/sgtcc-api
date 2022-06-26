package io.notbronken.sgtccapi.semesters.infra.entity

import io.notbronken.sgtccapi.semesters.api.dto.ClassDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassUpdateDto
import io.notbronken.sgtccapi.teachers.infra.entity.Teacher
import java.time.ZonedDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.SequenceGenerator
import javax.persistence.Table

@Entity
@Table(name = "CLASSES")
@SequenceGenerator(name = "SEQ_CLASS", sequenceName = "SEQUENCE_CLASS", allocationSize = 1)
class Class (
    @Id
    @GeneratedValue(generator = "SEQ_CLASS", strategy = GenerationType.SEQUENCE)
    @Column(nullable = false, unique = true)
    val id: Long? = null,
    @Column(nullable = false, length = 50)
    var name: String,
    @ManyToOne
    @JoinColumn(name = "FK_SEMESTER_PK", nullable = false)
    var semester: Semester,
    @ManyToOne
    @JoinColumn(name = "FK_TEACHER_PK", nullable = false)
    val teacher: Teacher,
    @OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
    var students: Set<Grade> = setOf(),
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
) {
    fun toDto() = ClassDto(
        id = id!!,
        name = name,
        semester = semester.toDto(),
        teacherId = teacher.registration,
        teacherName = teacher.name,
        students = students.map(Grade::toDto),
        createdAt = createdAt,
    )

    fun update(dto: ClassUpdateDto, grades: Set<Grade>, entity: Semester): Class {
        name = dto.name
        semester = entity
        students = grades

        return this
    }
    fun addStudents(grades: Set<Grade>) {
        grades.map { grade ->
            val hasAdd = students.contains(grade)
            students = if (hasAdd) students.map {
                    val studentId = it.student.registration
                    val gradeId = grade.student.registration
                    if (studentId === gradeId) Grade(it.student, it.group, grade.note)
                    else it
            }.toSet()
            else students.plusElement(grade)
        }
    }
}