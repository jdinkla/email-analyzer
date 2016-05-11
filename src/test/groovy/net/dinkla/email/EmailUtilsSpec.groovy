package net.dinkla.email

import spock.lang.Specification

import javax.mail.Message
import javax.mail.Session
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailUtilsSpec extends Specification {

    def sdf = new SimpleDateFormat("yyyy-MM-dd")

    Message msg = createMimeMessage()
    Email em = EmailUtils.fromMessage(msg)

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
        expect: em.sentDate.toString().substring(0, 10) == '2016-01-01'
    }

    def "recipients"() {
        expect: em.recipients.size == 2
        and: em.recipients[0] == 'to@to.to'
        and: em.recipients[1] == 'cc@cc.cc'
    }

    def "toJSON"() {
        expect: EmailUtils.toJSON(em)[0] == '{'
    }

    Message createMimeMessage() {
        def props = System.getProperties();
        def session = Session.getInstance(props, null);
        def msg = new MimeMessage(session)
        msg.subject = 'Subject1'
        msg.text = 'Howdy'
        msg.addFrom(new InternetAddress('dubdi@dibdi.dub'))
        msg.addFrom(new InternetAddress('dibdi@dubdi.dub'))
        msg.sender = new InternetAddress('from@from.fro')
        msg.sentDate = sdf.parse('2016-01-01')
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress('to@to.to'))
        msg.addRecipient(Message.RecipientType.CC, new InternetAddress('cc@cc.cc'))
        //msg.receivedDate = sdf.parse('2016-02-01')
        return msg
    }
}
