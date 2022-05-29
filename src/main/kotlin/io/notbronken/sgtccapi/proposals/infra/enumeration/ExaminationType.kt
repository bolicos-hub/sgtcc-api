package io.notbronken.sgtccapi.proposals.infra.enumeration

enum class ExaminationType(
    private val key: String,
) {
    PRE_EVALUATION("Pré Avaliação"),
    FINAL_EVALUATION("Avaliação Final"),
}