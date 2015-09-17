package com.github.jlgrock.informatix.workmanager.domain
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
/**
 *
 */
@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    List<UserAccount> findUserAccountsByEmail(String email)

    UserAccount findOneUserAccountByEmail(String email)

    List<UserAccount> findUserAccountsByEmailAndIdNot(String email, int id)

    List<UserAccount> findUserAccountsByRole(UserRole role)

}
