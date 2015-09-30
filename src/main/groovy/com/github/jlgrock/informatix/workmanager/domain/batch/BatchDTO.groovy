package com.github.jlgrock.informatix.workmanager.domain.batch

import com.github.jlgrock.informatix.workmanager.domain.assignment.AssignmentDTO
import com.github.jlgrock.informatix.workmanager.domain.attachment.AttachmentDTO
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

/**
 *
 */
@ToString
@EqualsAndHashCode
class BatchDTO {

    Integer id

    String name

    List<AssignmentDTO> assignments

    int numberOfRecords

    AttachmentDTO original

    int numberOfUnvalidatedRecords

    AttachmentDTO postValidation

    BatchDTO() {
    }

    BatchDTO(Batch batch, List<AssignmentDTO> assignmentsIn) {
        id = batch.id
        name = batch.name
        numberOfRecords = batch.numberOfRecords
        original = new AttachmentDTO(batch.original)
        numberOfUnvalidatedRecords = batch.numberOfUnvalidatedRecords
        postValidation = new AttachmentDTO(batch.postValidation)
        assignments = assignmentsIn
    }
}
