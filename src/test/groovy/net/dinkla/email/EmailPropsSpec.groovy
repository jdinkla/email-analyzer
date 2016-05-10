package net.dinkla.email

import spock.lang.Specification

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailPropsSpec extends Specification {

    def ep = EmailProps.readFromFile('test_secret.properties')

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
