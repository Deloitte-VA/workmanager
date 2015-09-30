package com.github.jlgrock.informatix.workmanager.services.email

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

/**
 *
 */
@Service
class DebugEmailService extends AbstractEmailService {
    private static Logger LOGGER = LoggerFactory.getLogger(DebugEmailService.class);

    @Override
    void sendMail(SimpleMailMessage simpleMailMessage) {
        LOGGER.info("The following message would have been sent: ${simpleMailMessage}")
    }
}
