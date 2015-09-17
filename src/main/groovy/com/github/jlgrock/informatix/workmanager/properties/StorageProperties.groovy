package com.github.jlgrock.informatix.workmanager.properties
import groovy.transform.Immutable
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
/**
 *
 */
@Immutable
@Component
class StorageProperties {
    @Value('${storage.type:RDBMS}')
    String type

    StorageType getConvertedType() {
        StorageType parsedStorageType = StorageType.parse(type)
        if (parsedStorageType == null) {
            parsedStorageType = StorageType.RDBMS
        }
        parsedStorageType
    }
}
