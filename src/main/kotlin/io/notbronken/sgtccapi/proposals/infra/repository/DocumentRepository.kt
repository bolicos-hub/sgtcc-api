package io.notbronken.sgtccapi.proposals.infra.repository

import io.notbronken.sgtccapi.proposals.infra.entity.Document
import org.springframework.data.jpa.repository.JpaRepository

interface DocumentRepository: JpaRepository<Document, String> {
}