package com.github.jlgrock.informatix.workmanager.domain.attachment

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 */
@Repository
interface AttachmentRepository extends CrudRepository<Attachment, Integer> {
}
