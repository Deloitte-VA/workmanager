package com.github.jlgrock.informatix.workmanager.domain.batch

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
/**
 *
 */
@Repository
interface BatchRepository extends PagingAndSortingRepository<Batch, Integer> {
    List<Batch> findAllByOrderByIdDesc()
    List<Batch> findAllByNameContainingIgnoreCaseOrderByIdDesc(String name)
}
