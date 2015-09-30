package com.github.jlgrock.informatix.workmanager.services.email

import com.github.jlgrock.informatix.workmanager.domain.useraccount.UserAccount

/**
 *
 */
interface EmailService {

    SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, UserAccount user)

    void sendMail(SimpleMailMessage simpleMailMessage)
}
