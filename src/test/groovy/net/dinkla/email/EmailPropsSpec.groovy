package net.dinkla.email

import net.dinkla.imap.EmailServerProperties
import spock.lang.Specification

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailPropsSpec extends Specification {

    def ep = EmailServerProperties.readFromFile('test_secret.properties')

    def "protocol"() {
        expect: ep.protocol == 'protocol'
    }

    def "host"() {
        expect: ep.host == 'host'
    }

    def "user"() {
        expect: ep.user == 'user'
    }

    def "password"() {
        expect: ep.password == 'password'
    }

}
