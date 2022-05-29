package io.notbronken.sgtccapi.students.infra.enumeration

enum class StudentStatus(
    private val key: String,
) {
    ACTIVE("Ativo"),
    APPROVED("Aprovado"),
    LOCKED("Trancado"),
    ESCAPE("Evadido"),
}