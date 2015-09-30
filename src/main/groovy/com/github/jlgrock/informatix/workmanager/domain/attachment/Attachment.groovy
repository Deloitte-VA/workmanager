package com.github.jlgrock.informatix.workmanager.domain.attachment
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty
import org.springframework.http.MediaType

import javax.persistence.*
import javax.validation.constraints.NotNull
import java.time.LocalDateTime
/**
 *
 */
@Entity
@Table(name = "attachments")
@ToString
@EqualsAndHashCode
class Attachment {

    @Id
    @NotNull
    @GeneratedValue
    Integer id

    @NotEmpty
    @Column(name = "filename")
    String fileName

    @NotNull
    @Column(name = "upload_date")
    LocalDateTime uploadDate

    @NotNull
    @Column(name = "num_bytes")
    int numberOfBytes

    @NotNull
    @Column(name= "media_type")
    MediaType mediaType

    @NotNull
    byte[] data

}
