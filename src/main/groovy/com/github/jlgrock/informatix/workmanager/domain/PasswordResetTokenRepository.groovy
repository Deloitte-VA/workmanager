package com.github.jlgrock.informatix.workmanager.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 *
 */
@Repository
interface PasswordResetTokenRepository  extends CrudRepository<PasswordResetToken, Integer> {
}
