package com.github.jlgrock.informatix.workmanager.services.email

import com.github.jlgrock.informatix.workmanager.domain.SimpleMailMessage
import com.github.jlgrock.informatix.workmanager.domain.UserAccount
import com.github.jlgrock.informatix.workmanager.properties.EmailProperties
import org.springframework.beans.factory.annotation.Autowired

/**
 *
 */
abstract class AbstractEmailService implements EmailService {
    @Autowired
    EmailProperties emailProperties

    static final String RESET = "Reset Password"

    @Override
    SimpleMailMessage constructResetTokenEmail(
            String contextPath, Locale locale, String token, UserAccount user) {
        String url = contextPath + "/user/changePassword?id=" + user.getId() + "&token=" + token
        String message = "Your password has been reset."
        //messages.getMessage("message.resetPassword", null, locale); //TODO replace with a messages file

        SimpleMailMessage email = new SimpleMailMessage(
                to: user.getEmail(),
                from: emailProperties.typeWithDefault,
                subject: RESET,
                body: message + " rn" + url)
        return email
    }

    @Override
    abstract void sendMail(SimpleMailMessage simpleMailMessage)
}
