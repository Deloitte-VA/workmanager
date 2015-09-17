package com.github.jlgrock.informatix.workmanager.properties
import groovy.transform.Immutable
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
/**
 *
 */
@Immutable
@Component
class HostProperties {
    /**
     * Example "google.com"
     */
    @Value('${host.name:localhost}')
    String name

}
