package net.dinkla.email

import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage
import spock.lang.Specification

import javax.mail.Message
import javax.mail.Session
import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 10.05.2016.
 */
class MyMessageSpec extends Specification {

    def sdf = new SimpleDateFormat("yyyy-MM-dd")

    Message mimeMsg1 = createMimeMessage()
    MyMessage msg1 = new MyMessage(mimeMsg1)

    def "subject"() {
        expect: msg1.subject == 'Subject1'
    }

    def "text"() {
        expect: msg1.texts.size == 1
           and: msg1.texts[0] == 'Howdy'
    }

    def "from"() {
        expect: msg1.froms.size == 2
        and: msg1.froms[0] == 'dubdi@dibdi.dub'
        and: msg1.froms[1] == 'dibdi@dubdi.dub'
    }

    def "sentDate"() {
        expect: msg1.sentDate == sdf.parse('2016-01-01')
    }

    def "recipients"() {
        expect: msg1.recipients.size == 2
        and: msg1.recipients[0] == 'to@to.to'
        and: msg1.recipients[1] == 'cc@cc.cc'
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
