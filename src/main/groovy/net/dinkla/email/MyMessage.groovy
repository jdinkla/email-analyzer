package net.dinkla.email

import com.fasterxml.jackson.databind.ObjectMapper

import javax.mail.Message

/**
 * Created by Dinkla on 10.05.2016.
 */
class MyMessage {

    static ObjectMapper mapper = new ObjectMapper()

    List<String> froms
    String subject
    List<String> texts
    Date sentDate
    Date receivedDate
    List<String> recipients

    MyMessage(Message msg) {
        texts = Utils.getTexts(msg)
        subject = msg.subject
        froms = msg.from.collect { it.toString() }
        sentDate = msg.sentDate
        receivedDate = msg.receivedDate
        recipients = msg.allRecipients.collect { it.toString() }
    }

    String toJSON() {
        mapper.writeValueAsString(this)
    }

}
