package com.github.jlgrock.informatix.workmanager.domain.assignment

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.springframework.format.annotation.NumberFormat

import javax.validation.constraints.NotNull

/**
 *
 */
@ToString
@EqualsAndHashCode
class HoursDTO {
    @NotNull
    @NumberFormat(pattern="###.#")
    double hours
}
