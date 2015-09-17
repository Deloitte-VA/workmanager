package com.github.jlgrock.informatix.workmanager.services.accounts

import com.github.jlgrock.informatix.workmanager.domain.UserAccount
import com.github.jlgrock.informatix.workmanager.domain.UserAccountDTO
import com.github.jlgrock.informatix.workmanager.domain.UserRole
import com.github.jlgrock.informatix.workmanager.exceptions.UserException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
/**
 *
 *
 */
@Service
class DebugAccountsService implements AccountsService {

    private static Logger LOGGER = LoggerFactory.getLogger(DebugAccountsService.class);

    static Map<Integer, UserAccount> data = [
            1: new UserAccount( id: 1, fname: "Peter", lname: "Jhons", email: "a@b.com" , role: UserRole.ADMIN),
            2: new UserAccount( id: 2, fname: "David", lname: "Bowie", email: "c@d.com" , role: UserRole.USER)
    ];

    @Override
    Collection<UserAccount> getAll() {
        return data.values();
    }

    @Override
    UserAccount get(int id) {
        return data[id];
    }

    @Override
    UserAccount add(UserAccountDTO userAccountDTO) {
        UserAccount userAccount = findByEmail(userAccountDTO.email)
        if (userAccount != null) {
            def s = "Cannot add another user with the email address ${userAccountDTO.email}"
            LOGGER.error(s)
            throw new UserException(s)
        }
        addOrUpdate(null, userAccountDTO)
    }

    @Override
    UserAccount update(int id, UserAccountDTO userAccountData) {
        if (data[id]?.role != userAccountData.role &&
                data[id]?.role == UserRole.ADMIN &&
                countAdmins() == 1) {
            def s = "There must always be one ADMIN in the system"
            LOGGER.error(s)
            throw new UserException(s)
        }
        addOrUpdate(id, userAccountData)
    }

    private UserAccount addOrUpdate(def id, UserAccountDTO userAccountDTO) {
        def newId = id;
        if (newId == null) {
            newId = data.keySet().max() + 1
            LOGGER.debug("Assigned new ID of ${newId}")
        }
        UserAccount userAccount = new UserAccount(newId, userAccountDTO)
        data[newId] = userAccount
    }

    @Override
    void resetPassword(String email) {
        // Not going to attempt this in DEBUG
    }

    @Override
    void askForVerification(int id) {
        // Not going to attempt this in DEBUG
    }

    @Override
    UserAccount delete(int id) {
        if (data[id]?.role == UserRole.ADMIN && countAdmins() == 1) {
            def s = "There must always be one ADMIN in the system"
            LOGGER.error(s)
            throw new UserException(s)
        }
        data.remove(id);
    }

    private UserAccount findByEmail(String email) {
        for (UserAccount account : data.values()) {
            if(account.email.equalsIgnoreCase(email)) {
                return account
            }
        }
        null
    }

    private int countAdmins() {
        int count = 0
        for (UserAccount account : data.values()) {
            if (account.role == UserRole.ADMIN) {
                count++
            }
        }
        count
    }
}
