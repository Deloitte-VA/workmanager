package com.github.jlgrock.informatix.workmanager.controllers
import com.github.jlgrock.informatix.workmanager.domain.attachment.Attachment
import com.github.jlgrock.informatix.workmanager.domain.attachment.AttachmentDTO
import com.github.jlgrock.informatix.workmanager.domain.attachment.AttachmentRepository
import com.github.jlgrock.informatix.workmanager.exceptions.UserException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletResponse
/**
 *
 */
@RestController
@RequestMapping("attachments")
class AttachmentController {

    @Autowired
    AttachmentRepository attachmentRepository

    private static Logger LOGGER = LoggerFactory.getLogger(AttachmentController.class)

    @RequestMapping(method = RequestMethod.GET, value="/{id}")
    public AttachmentDTO get(@PathVariable int id) {
        if (id == null) {
            throw new UserException("id is null. Cannot continue with edit")
        }
        new AttachmentDTO(attachmentRepository.find(id))
    }

    @RequestMapping(method = RequestMethod.GET, value="/{id}/download")
    public ResponseEntity<byte[]> get(@PathVariable int id,
                             HttpServletResponse response) {
        if (id == null) {
            throw new UserException("id is null. Cannot continue with download")
        }
        Attachment attachment = attachmentRepository.findOne(id)

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Content-Disposition", "attachment; filename=\"${attachment.fileName}\"");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(attachment.data.size())
                .contentType(attachment.mediaType)
                .body(attachment.data);
    }

//    @RequestMapping(method = RequestMethod.GET, value="/{id}/download")
//    ResponseEntity<byte[]> downloadBatch(@PathVariable("id") int id) {
//        LOGGER.debug("download batch: ${id}")
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        Attachment attachment = batchService.downloadBatchFile(id)
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentLength(attachment.data.size())
//                .contentType(MediaType.parseMediaType(attachment.mediaType))
//                .body(attachment.data);
//    }
}
