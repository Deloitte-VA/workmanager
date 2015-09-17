package com.github.jlgrock.informatix.workmanager.services.accounts
import com.github.jlgrock.informatix.workmanager.domain.*
import com.github.jlgrock.informatix.workmanager.exceptions.UserException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 *
 */
@Service
class RdbmsAccountsService implements AccountsService {

    private static Logger LOGGER = LoggerFactory.getLogger(RdbmsAccountsService.class);

    @Autowired
    UserAccountRepository userAccountRepository

    @Autowired
    VerificationTokenRepository verificationTokenRepository

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository

    @Override
    Collection<UserAccount> getAll() {
        userAccountRepository.findAll()
    }

    @Override
    UserAccount get(int id) {
        userAccountRepository.findOne(id)
    }

    @Override
    UserAccount add(UserAccountDTO userAccountDTO) {
        if (userAccountRepository.findUserAccountsByEmail(userAccountDTO.email).size() > 0) {
            throw new UserException("This email address has already been used to create an account")
        }
        UserAccount userAccount = new UserAccount(userAccountDTO)
        userAccountRepository.save(userAccount)
    }

    @Override
    UserAccount update(int id, UserAccountDTO userAccountDTO) {
        if (userAccountRepository.findUserAccountsByEmailAndIdNot(userAccountDTO.email, id).size() > 0) {
            throw new UserException("This email address has already been used to create an account")
        }
        UserAccount currentUser = userAccountRepository.findOne(id)
        if(currentUser.role != userAccountDTO.role &&
                currentUser.role == UserRole.ADMIN &&
                userAccountRepository.findUserAccountsByRole(UserRole.ADMIN).size() == 1) {
            def s = "There must always be one ADMIN in the system"
            LOGGER.error(s)
            throw new UserException(s)
        }
        UserAccount userAccount = new UserAccount(id, userAccountDTO)
        userAccountRepository.save(userAccount)
    }

    @Override
    void resetPassword(String email) {
        UserAccount userAccount = userAccountRepository.findOneUserAccountByEmail(email)
        if (userAccount == null) {
            throw new UserException("Email does not exist in the system")
        }
        PasswordResetToken passwordResetToken = new PasswordResetToken(userAccount)
        passwordResetTokenRepository.save(passwordResetToken)
        //TODO send email
    }

    @Override
    void askForVerification(int id) {
        UserAccount userAccount = userAccountRepository.findOne(id)
        if (userAccount == null) {
            throw new UserException("User does not exist in the system")
        }
        VerificationToken verificationToken = new VerificationToken()
        verificationTokenRepository.save(verificationToken)
        //TODO send email
    }

    @Override
    UserAccount delete(int id) {
        UserAccount currentUser = userAccountRepository.findOne(id)
        if (currentUser.role == UserRole.ADMIN &&
                userAccountRepository.findUserAccountsByRole(UserRole.ADMIN).size() == 1) {
            def s = "There must always be one ADMIN in the system"
            LOGGER.error(s)
            throw new UserException(s)
        }
        userAccountRepository.delete(id)
    }
}
