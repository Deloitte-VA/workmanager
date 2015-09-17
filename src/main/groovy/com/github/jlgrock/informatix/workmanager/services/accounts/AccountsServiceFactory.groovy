package com.github.jlgrock.informatix.workmanager.services.accounts

import com.github.jlgrock.informatix.workmanager.properties.StorageProperties
import com.github.jlgrock.informatix.workmanager.properties.StorageType
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
/**
 *
 */
@Service
class AccountsServiceFactory {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountsServiceFactory.class);

    @Autowired
    @Lazy
    StorageProperties storageProperties

    @Autowired
    @Lazy
    DebugAccountsService debugAccountsService

    @Autowired
    RdbmsAccountsService rdbmsAccountsService

    AccountsService getAccountsService() {
        StorageType s;
        LOGGER.info("Storage type of {${storageProperties?.convertedType}} chosen")
        switch(storageProperties.convertedType) {
            case StorageType.DEBUG:
                return debugAccountsService
            case StorageType.RDBMS:
                return rdbmsAccountsService
            default:
                LOGGER.error("Invalid type of Storage type.  Valid types are " + StorageType.values().toString())
        }
    }
}
