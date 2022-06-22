package io.notbronken.sgtccapi.proposals.infra.repository

import io.notbronken.sgtccapi.proposals.infra.entity.Examination
import org.springframework.data.jpa.repository.JpaRepository

interface ExaminationRepository: JpaRepository<Examination, Long>