package io.notbronken.sgtccapi.semesters.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.semesters.infra.entity.Grade
import io.notbronken.sgtccapi.semesters.infra.entity.GradeCompositeId
import io.notbronken.sgtccapi.semesters.infra.repository.GradeRepository
import io.notbronken.sgtccapi.students.business.service.StudentService
import io.notbronken.sgtccapi.students.infra.entity.Student
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import java.util.logging.Logger
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

interface GradeService {
    fun read(id: GradeCompositeId): Grade
    fun addStudents(entity: Group, studentIds: List<String>): Set<Grade>
    fun addNote(group: Group, studentId: String, note: Double): Grade
}

@Service
class GradeServiceImpl(
    val gradeRepository: GradeRepository,
    val studentService: StudentService,
): GradeService {
    companion object {
        private val LOGGER = Logger.getLogger(GradeService::class.simpleName)
        private const val CREATE_MESSAGE = "Grade created with unique identifier:"
        private const val UPDATE_MESSAGE = "Grade updated with unique identifier:"
        private const val READ_MESSAGE = "Grade read with unique identifier:"
        private const val LIST_MESSAGE = "Grades retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Grade with unique identifier not found:"
    }

    override fun read(id: GradeCompositeId): Grade {
        val idMessageError = "Student ${id.student} and Class ${id.group}"
        val entity = runBlocking { gradeRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $idMessageError")
        }

        LOGGER.info("$READ_MESSAGE $idMessageError")

        return entity
    }


    override fun addStudents(
        entity: Group,
        studentIds: List<String>,
    ): Set<Grade> {
        val students = runBlocking {
            val output = mutableListOf<Student>()
            studentService.list(studentIds)
                .map { it.toEntity() }
                .subscribe(output::add)
            output.toList()
        }
        val gradeIds = students.map { Grade(it, entity) }
        val grades = gradeIds.filter { !entity.students.contains(it) }
        val gradesSaved = runBlocking { gradeRepository.saveAll(grades).toSet() }

        val idMessageError = gradesSaved.map { "Student: ${it.student} and Class: ${it.group}" }
        LOGGER.info("$CREATE_MESSAGE $idMessageError")

        return gradesSaved
    }

    override fun addNote(
        group: Group,
        studentId: String,
        note: Double,
    ): Grade {
        val student = runBlocking { studentService.read(studentId) }.block()!!.toEntity()
        val compositeId = GradeCompositeId(student.registration, group.id)
        val entity = read(compositeId)
        entity.addNote(note)
        return runBlocking { gradeRepository.save(entity) }
    }

}
