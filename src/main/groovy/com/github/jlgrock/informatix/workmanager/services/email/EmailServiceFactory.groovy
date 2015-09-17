package com.github.jlgrock.informatix.workmanager.services.email
import com.github.jlgrock.informatix.workmanager.properties.EmailProperties
import com.github.jlgrock.informatix.workmanager.properties.EmailType
import com.github.jlgrock.informatix.workmanager.services.accounts.AccountsService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 *
 */
@Service
class EmailServiceFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(EmailServiceFactory.class);

    @Autowired
    @Lazy
    EmailProperties emailProperties

    @Autowired
    @Lazy
    DebugEmailService debugEmailService

    @Autowired
    @Lazy
    LocalEmailService localEmailService

    @Autowired
    ExternalEmailService externalEmailService

    AccountsService getAccountsService() {
        LOGGER.info("Email type of {${emailProperties?.type}} chosen")
        switch(emailProperties.type) {
            case EmailType.DEBUG:
                return debugEmailService
            case EmailType.LOCAL:
                return localEmailService
            case EmailType.EXTERNAL:
                return externalEmailService
            default:
                LOGGER.error("Invalid type of email type.  Valid types are " + EmailType.values().toString())
        }
    }
}
