package com.github.jlgrock.informatix.workmanager.domain.useraccount
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.Email
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull
/**
 *
 */
@Entity
@Table(name = "user_accounts")
@ToString
@EqualsAndHashCode
class UserAccount {

    @Id
    @NotNull
    @GeneratedValue
    Integer id

    @NotEmpty
    @Column(name = "first_name")
    String fname

    @NotEmpty
    @Column(name = "last_name")
    String lname

    @NotEmpty
    @Email
    String email

    String password

    boolean verified

    UserAccount(){}

    UserAccount(UserAccountDTO userAccountData) {
        id = userAccountData.id
        fname = userAccountData.fname
        lname = userAccountData.lname
        email = userAccountData.email
    }
}
