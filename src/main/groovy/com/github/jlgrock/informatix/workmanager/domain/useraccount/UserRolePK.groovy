package com.github.jlgrock.informatix.workmanager.domain.useraccount

import javax.persistence.Embeddable
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.OneToOne
import javax.validation.constraints.NotNull

/**
 *
 */
@Embeddable
class UserRolePK implements Serializable {
    @OneToOne(targetEntity = UserAccount.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_account_id")
    @NotNull
    UserAccount userAccount

    @NotNull
    @Enumerated(EnumType.STRING)
    Role role
}
