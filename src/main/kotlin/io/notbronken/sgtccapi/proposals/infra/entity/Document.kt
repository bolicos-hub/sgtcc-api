package io.notbronken.sgtccapi.proposals.infra.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import java.time.ZonedDateTime

@Entity
@Table(name = "DOCUMENTS")
class Document(
    @Id
    @Column(name = "FILE_PATH", nullable = false, unique = true)
    val filePath: String,
    @Column(nullable = false, length = 50)
    val name: String,
    @Column
    val description: String,
    @Column(name = "CREATED_AT", nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now(),
    @ManyToOne
    @JoinColumn(name = "FK_PROPOSAL_PK", nullable = false)
    val proposal: Proposal,
) {

}