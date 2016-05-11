package net.dinkla.email

import net.dinkla.TextDate
import spock.lang.Specification

import javax.mail.Message
import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailSpec extends Specification {

    def sdf = new SimpleDateFormat("yyyy-MM-dd")
    Email em = createEmail()

    def "subject"() {
        expect: em.subject == 'Subject1'
    }

    def "text"() {
        expect: em.texts.size == 1
           and: em.texts[0] == 'Howdy'
    }

    def "from"() {
        expect: em.froms.size == 2
        and: em.froms[0] == 'dubdi@dibdi.dub'
        and: em.froms[1] == 'dibdi@dubdi.dub'
    }

    def "sentDate"() {
        expect: em.sentDate == new TextDate(sdf.parse('2016-01-01'))
    }

    def "recipients"() {
        expect: em.recipients.size == 2
        and: em.recipients[0] == 'to@to.to'
        and: em.recipients[1] == 'cc@cc.cc'
    }

    def "toJSON"() {
        expect: EmailUtils.toJSON(em)[0] == '{'
    }

    Email createEmail() {
        Email em = new Email()
        em.froms = [ 'dubdi@dibdi.dub', 'dibdi@dubdi.dub']
        em.subject = 'Subject1'
        em.texts = [ "Howdy" ]
        em.sentDate = sdf.parse('2016-01-01')
        em.receivedDate = sdf.parse('2016-01-01')
        em.recipients = ['to@to.to', 'cc@cc.cc']
        return em
    }

}
