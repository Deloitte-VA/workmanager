package com.github.jlgrock.informatix.workmanager.domain.useraccount

/**
 *
 */
enum Role {
    ADMIN, USER

    static Role parse(String name) {
        for (Role userRole : values()) {
            if (name.equalsIgnoreCase(name)) {
                return userRole
            }
        }
        null
    }
}
