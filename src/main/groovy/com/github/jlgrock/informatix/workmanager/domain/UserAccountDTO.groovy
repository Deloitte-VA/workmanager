package com.github.jlgrock.informatix.workmanager.domain
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import javax.validation.constraints.NotNull

/**
 *
 */
@ToString
@EqualsAndHashCode
class UserAccountDTO {

    @NotEmpty
    String fname

    @NotEmpty
    String lname

    @NotEmpty
    @Email
    String email

    @NotNull
    UserRole role
}
