package com.github.jlgrock.informatix.workmanager.domain

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 *
 */
@Entity
@Table(name = "password_reset_tokens")
@ToString
@EqualsAndHashCode
class PasswordResetToken extends AbstractExpiry {
    @Id
    @GeneratedValue
    @NotNull
    int id

    @NotEmpty
    String token = generateToken()

    @OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_account_id")
    UserAccount userAccount

    PasswordResetToken(UserAccount userAccountIn) {
        userAccount = userAccountIn
    }
}
