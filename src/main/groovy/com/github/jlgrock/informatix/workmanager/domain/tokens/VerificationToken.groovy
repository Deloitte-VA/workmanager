package com.github.jlgrock.informatix.workmanager.domain.tokens

import com.github.jlgrock.informatix.workmanager.domain.AbstractExpiry
import com.github.jlgrock.informatix.workmanager.domain.useraccount.UserAccount
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import org.hibernate.validator.constraints.NotEmpty

import javax.persistence.*
import javax.validation.constraints.NotNull

/**
 *
 */
@Entity
@Table(name = "verification_tokens")
@ToString
@EqualsAndHashCode
class VerificationToken extends AbstractExpiry {

    @Id
    @GeneratedValue
    @NotNull
    private int id

    @NotEmpty
    private String token = generateToken()

    @OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_account_id")
    private UserAccount userAccount

    public VerificationToken(UserAccount userAccountIn) {
        userAccount = userAccountIn
    }

}
