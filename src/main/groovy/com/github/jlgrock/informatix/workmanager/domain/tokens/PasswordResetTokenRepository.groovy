package com.github.jlgrock.informatix.workmanager.domain.tokens

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 */
@Repository
interface PasswordResetTokenRepository extends CrudRepository<PasswordResetToken, Integer> {
}
