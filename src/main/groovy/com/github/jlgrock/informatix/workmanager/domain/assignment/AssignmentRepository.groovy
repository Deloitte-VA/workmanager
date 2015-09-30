package com.github.jlgrock.informatix.workmanager.domain.assignment

import com.github.jlgrock.informatix.workmanager.domain.batch.Batch
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 */
@Repository
interface AssignmentRepository extends CrudRepository<Assignment, Integer> {
    List<Assignment> findAllByBatchOrderByIdAsc(Batch batch)
}
