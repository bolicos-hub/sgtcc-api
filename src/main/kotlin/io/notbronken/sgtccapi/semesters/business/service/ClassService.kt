package io.notbronken.sgtccapi.semesters.business.service

import io.notbronken.sgtccapi.common.error.BusinessEntityNotFoundException
import io.notbronken.sgtccapi.common.model.CreatedResource
import io.notbronken.sgtccapi.semesters.api.dto.ClassCreateDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassDto
import io.notbronken.sgtccapi.semesters.api.dto.ClassUpdateDto
import io.notbronken.sgtccapi.semesters.infra.repository.ClassRepository
import io.notbronken.sgtccapi.teachers.business.service.TeacherService
import kotlinx.coroutines.runBlocking
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.logging.Logger
import io.notbronken.sgtccapi.semesters.infra.entity.Class as Group

interface ClassService {
    fun create(dto: ClassCreateDto): Mono<CreatedResource>
    fun update(dto: ClassUpdateDto, id: Long): Mono<ClassDto>
    fun read(id: Long): Mono<ClassDto>
    fun delete(id: Long): Mono<Void>
    fun list(): Flux<ClassDto>
    fun listBySemester(semesterId: Long): Flux<ClassDto>
    fun addStudents(id: Long, studentIds: List<String>): Mono<ClassDto>
    fun addNote(id: Long, studentId: String, note: Double): Mono<ClassDto>
}

@Service
class ClassServiceImpl(
    val classRepository: ClassRepository,
    val teacherService: TeacherService,
    val semesterService: SemesterService,
    val gradeService: GradeService,
): ClassService {
    companion object {
        private val LOGGER = Logger.getLogger(ClassService::class.simpleName)
        private const val CREATE_MESSAGE = "Class created with unique identifier:"
        private const val UPDATE_MESSAGE = "Class updated with unique identifier:"
        private const val READ_MESSAGE = "Class read with unique identifier:"
        private const val LIST_MESSAGE = "Classes retrieved from the database"
        private const val ENTITY_NOT_FOUND_MESSAGE = "Class with unique identifier not found:"
        private const val ADD_NOTE_MESSAGE = "Class add note with unique identifier:"
    }

    override fun create(dto: ClassCreateDto): Mono<CreatedResource> {
        val teacher = runBlocking { teacherService.read(dto.teacherId).block()!!.toEntity() }
        val semester = runBlocking { semesterService.read(dto.semesterId).block()!!.toEntity() }
        val entity = dto.toEntity(semester, teacher)
        val response = runBlocking { classRepository.save(entity) }

        LOGGER.info("$CREATE_MESSAGE ${response.id}")

        val created = CreatedResource(response.id!!)
        return Mono.just(created)
    }

    override fun update(dto: ClassUpdateDto, id: Long): Mono<ClassDto> {
        val entity = runBlocking { classRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
        val semester = runBlocking { semesterService.read(dto.semesterId).block()!!.toEntity() }
        val grades = gradeService.addStudents(entity, dto.students)
        entity.update(dto, grades, semester)
        val response = runBlocking { classRepository.save(entity) }

        LOGGER.info("$UPDATE_MESSAGE ${response.id}")

        val updated = response.toDto()
        return Mono.just(updated)
    }

    override fun read(id: Long): Mono<ClassDto> {
        val entity = runBlocking { classRepository.findById(id) }.orElseThrow {
            BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id")
        }

        LOGGER.info("$READ_MESSAGE ${entity.id}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }

    override fun delete(id: Long): Mono<Void> {
        TODO("Not yet implemented")
    }

    override fun list(): Flux<ClassDto> {
        val list = runBlocking { classRepository.findAllByOrderByCreatedAtDesc() }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Group::toDto).stream()
        return Flux.fromStream(converted)
    }

    override fun listBySemester(semesterId: Long): Flux<ClassDto> {
        val list = runBlocking { classRepository.findAllBySemester_IdOrderById(semesterId) }

        LOGGER.info(LIST_MESSAGE)

        val converted = list.map(Group::toDto).stream()
        return Flux.fromStream(converted)
    }

    override fun addStudents(id: Long, studentIds: List<String>): Mono<ClassDto> {
        val entity = runBlocking { classRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
        val grades = runBlocking { gradeService.addStudents(entity, studentIds) }
        entity.addStudents(grades)

        LOGGER.info("$UPDATE_MESSAGE ${entity.id}")

        val updated = entity.toDto()
        return Mono.just(updated)
    }


    override fun addNote(id: Long, studentId: String, note: Double): Mono<ClassDto> {
        val entity = runBlocking { classRepository.findById(id) }
            .orElseThrow { BusinessEntityNotFoundException("$ENTITY_NOT_FOUND_MESSAGE $id") }
        val grade = gradeService.addNote(entity, studentId, note)
        entity.addStudents(setOf(grade))

        LOGGER.info("$ADD_NOTE_MESSAGE ${entity.id} for student: $studentId")

        val updated = entity.toDto()
        return Mono.just(updated)
    }
}