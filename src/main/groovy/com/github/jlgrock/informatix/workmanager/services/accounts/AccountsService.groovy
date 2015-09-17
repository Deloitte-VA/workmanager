package com.github.jlgrock.informatix.workmanager.services.accounts

import com.github.jlgrock.informatix.workmanager.domain.UserAccount
import com.github.jlgrock.informatix.workmanager.domain.UserAccountDTO

/**
 *
 */
interface AccountsService {
    Collection<UserAccount> getAll()

    UserAccount get(int id)

    UserAccount add(UserAccountDTO userAccountDTO)

    UserAccount update(int id, UserAccountDTO userAccountData)

    void resetPassword(String email)

    void askForVerification(int id)

    UserAccount delete(int id)
}
