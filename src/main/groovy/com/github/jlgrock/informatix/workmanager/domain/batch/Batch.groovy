package com.github.jlgrock.informatix.workmanager.domain.batch

import com.github.jlgrock.informatix.workmanager.domain.attachment.Attachment
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.*
import javax.validation.constraints.NotNull
/**
 *
 */
@Entity
@Table(name="batches")
@ToString
@EqualsAndHashCode
class Batch {

    @Id
    @NotNull
    @GeneratedValue
    Integer id

    @Column
    String name

    @Column(name="num_records")
    int numberOfRecords

    @OneToOne(targetEntity = Attachment.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "original_attachment_id")
    Attachment original

    @Column(name="num_unvalidated_records")
    int numberOfUnvalidatedRecords

    @OneToOne(targetEntity = Attachment.class, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, name = "post_validation_attachment_id")
    Attachment postValidation

}
