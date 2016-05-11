package net.dinkla.email

import spock.lang.Specification

/**
 * Created by Dinkla on 11.05.2016.
 */
class EmailAddressSpec extends Specification {

    def "simple"() {
        EmailAddress ea1 = new EmailAddress('joern@dinkla.net')
        expect: ea1.email == 'joern@dinkla.net'
        and: ea1.name == ''
    }

    def "with name 1"() {
        EmailAddress ea2 = new EmailAddress('Joern Dinkla <joern@dinkla.net>')
        expect: ea2.email == 'joern@dinkla.net'
        and: ea2.name == 'Joern Dinkla'
    }

    def "with name 2"() {
        EmailAddress ea3 = new EmailAddress('=?UTF-8?Q?J=c3=b6rn_Dinkla_?= <joern@dinkla.net>')
        expect: ea3.email == 'joern@dinkla.net'
        and: ea3.name == '=?UTF-8?Q?J=c3=b6rn_Dinkla_?='
    }

    def "empty string"() {
        EmailAddress ea4 = new EmailAddress('')
        expect: ea4.email == ''
        and: ea4.name == ''
    }

}
