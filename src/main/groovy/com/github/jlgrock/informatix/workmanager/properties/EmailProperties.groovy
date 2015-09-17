package com.github.jlgrock.informatix.workmanager.properties
import groovy.transform.Immutable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
/**
 *
 */
@Immutable
@Component
class EmailProperties {

    @Value('${email.type:LOCAL}')
    String type

    //@Value('${email.host}')
    //private Host host;

    @Value('${email.from}')
    String from

    @Autowired
    HostProperties hostProperties

//    private static class Host {
//
//        @Value('${email.host.ip}')
//        private String ip;
//
//        @Value('${email.host.port}')
//        private int port;
//
//    }

    EmailType getTypeWithDefault() {
        EmailType parseVal = EmailType.parse(type)
        if (parseVal == null) {
            return EmailType.LOCAL
        }
        parseVal
    }

}
