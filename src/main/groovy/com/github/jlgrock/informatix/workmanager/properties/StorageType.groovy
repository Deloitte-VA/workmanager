package com.github.jlgrock.informatix.workmanager.properties

/**
 *
 */
enum StorageType {
    DEBUG, RDBMS

    static StorageType parse(String type) {
        for (StorageType storageType : values()) {
            if (storageType.name().equalsIgnoreCase(type)) {
                return storageType
            }
        }
        return null
    }
}
