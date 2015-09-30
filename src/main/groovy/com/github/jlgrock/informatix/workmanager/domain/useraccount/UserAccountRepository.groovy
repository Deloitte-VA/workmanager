package com.github.jlgrock.informatix.workmanager.domain.useraccount

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
/**
 *
 */
@Repository
public interface UserAccountRepository extends CrudRepository<UserAccount, Integer> {

    List<UserAccount> findUserAccountsByEmail(String email)

    UserAccount findOneUserAccountByEmail(String email)

    List<UserAccount> findUserAccountsByEmailAndIdNot(String email, int id)

    // Using JPQL query to find users by role
    @Query("SELECT u FROM UserRole r INNER JOIN r.userRolePK.userAccount u WHERE r.userRolePK.role = :roleParam")
    List<UserAccount> findUserAccountsByRole(@Param("roleParam") Role role)

}
