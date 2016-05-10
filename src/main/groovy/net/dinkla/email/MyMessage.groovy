package net.dinkla.email

import javax.mail.Message

/**
 * Created by Dinkla on 10.05.2016.
 */
class MyMessage {

    Message msg

    List<String> froms
    String subject
    List<String> texts
    Date sentDate
    Date receivedDate
    List<String> recipients

    MyMessage(Message msg) {
        this.msg = msg
        texts = Utils.getTexts(msg)
        subject = msg.subject
        froms = msg.from.collect { it.toString() }
        sentDate = msg.sentDate
        receivedDate = msg.receivedDate
        recipients = msg.allRecipients.collect { it.toString() }
    }

}
