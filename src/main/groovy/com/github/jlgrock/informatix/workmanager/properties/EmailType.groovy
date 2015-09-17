package com.github.jlgrock.informatix.workmanager.properties

/**
 *
 */
enum EmailType {
    DEBUG, LOCAL, EXTERNAL

    static EmailType parse(String type) {
        for (EmailType emailtype : values()) {
            if (emailtype.name().equalsIgnoreCase(type)) {
                return emailtype
            }
        }
        return null
    }
}
