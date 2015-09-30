package com.github.jlgrock.informatix.workmanager.domain.useraccount
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.Table
/**
 *
 */
@Entity
@Table(name = "user_roles")
@ToString
@EqualsAndHashCode
class UserRole {

    @EmbeddedId
    UserRolePK userRolePK

}
