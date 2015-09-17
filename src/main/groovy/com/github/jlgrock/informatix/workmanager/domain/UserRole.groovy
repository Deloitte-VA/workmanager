package com.github.jlgrock.informatix.workmanager.domain

/**
 *
 */
enum UserRole {
    ADMIN, USER

    static UserRole parse(String name) {
        for (UserRole userRole : values()) {
            if (name.equalsIgnoreCase(name)) {
                return userRole
            }
        }
        null
    }
}
