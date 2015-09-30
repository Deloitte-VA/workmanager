package com.github.jlgrock.informatix.workmanager.services

import com.github.jlgrock.informatix.workmanager.domain.assignment.Assignment
import com.github.jlgrock.informatix.workmanager.domain.assignment.AssignmentDTO
import com.github.jlgrock.informatix.workmanager.domain.assignment.AssignmentRepository
import com.github.jlgrock.informatix.workmanager.domain.attachment.Attachment
import com.github.jlgrock.informatix.workmanager.domain.batch.Batch
import com.github.jlgrock.informatix.workmanager.domain.batch.BatchDTO
import com.github.jlgrock.informatix.workmanager.domain.batch.BatchRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.stereotype.Service

import java.time.LocalDateTime
/**
 *
 */
@Service
class BatchService {
    static final int PAGE_SIZE = 10; //TODO

    private static Logger LOGGER = LoggerFactory.getLogger(BatchService.class)

    @Autowired
    BatchRepository batchRepository

    @Autowired
    AssignmentRepository assignmentsRepository

    List<BatchDTO> findAllBatches(String searchTerm) {
        List<Batch> batches
        if (searchTerm == null) {
            batches = batchRepository.findAllByOrderByIdDesc()
        } else {
            batches = batchRepository.findAllByNameContainingIgnoreCaseOrderByIdDesc(searchTerm)
        }
        LOGGER.debug("batch size: ${batches?.size()}")
        batches.collect { batch ->
            List<Assignment> assignments = assignmentsRepository.findAllByBatchOrderByIdAsc(batch)
            List<AssignmentDTO> assignmentDTOs = assignments.collect {
                assignment ->
                    new AssignmentDTO(assignment)
            }
            new BatchDTO(batch, assignmentDTOs)
        }
    }

    BatchDTO add(String filename, String contentType, byte[] data) {
        ProcessedData processedData = processData(data)

        Batch batch = new Batch([
                numberOfRecords: processedData.numberOfRecords,
                numberOfUnvalidatedRecords: processedData.numberOfUnvalidatedRecords,
                original: new Attachment([
                        fileName: filename,
                        uploadDate: LocalDateTime.now(),
                        numberOfBytes: data.size(),
                        mediaType: MediaType.parseMediaType(contentType),
                        data: data]),
                postValidation: new Attachment([
                        fileName: stripExtension(filename) + "_processed.xlsx",
                        uploadDate: LocalDateTime.now(),
                        numberOfBytes: data.size(),
                        mediaType: MediaType.parseMediaType(contentType),
                        data: processedData.postValidatedFile])

                ])
        batchRepository.save(batch)
        new BatchDTO(batch, null) //TODO need assignments
    }

    Attachment downloadBatchFile(int id) {
        Batch batch = batchRepository.findOne(id)
        batch.getOriginal()
    }

    private ProcessedData processData(byte[] data) {
        // TODO this needs to read the xlsx and store it to the database with information saved
        ProcessedData p = new ProcessedData()
        p.numberOfRecords = 5
        p.postValidatedFile = data
        p
    }

    private stripExtension(String filename) {
        def names = filename.split("\\.")
        names.size() > 1 ? (names - names[-1]).join('.') : names[0]
    }

    class ProcessedData {
        int numberOfRecords
        int numberOfUnvalidatedRecords
        byte[] postValidatedFile
    }
}
