package com.github.jlgrock.informatix.workmanager.domain.useraccount
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

    Integer id

    @NotEmpty
    String fname

    @NotEmpty
    String lname

    @NotEmpty
    @Email
    String email

    @NotNull
    UserRole role

    UserAccountDTO() {}

    UserAccountDTO(UserAccount userAccount) {
        id = userAccount.id
        fname = userAccount.fname
        lname = userAccount.lname
        email = userAccount.email
        role = userAccount.role
    }
}
