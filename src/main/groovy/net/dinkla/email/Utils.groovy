package net.dinkla.email

import com.sun.mail.imap.IMAPInputStream
import com.sun.mail.util.BASE64DecoderStream

import javax.mail.BodyPart
import javax.mail.Message
import javax.mail.internet.MimeMultipart

/**
 * Created by Dinkla on 10.05.2016.
 */
class Utils {

    static List<String> getTexts(Message msg) {
        Object obj = msg.getContent()
        def list = new LinkedList<String>()
        getTextsAux(obj, list)
        return list
    }

    static void getTextsAux(Object obj, List<String> acc) {
        if (obj instanceof String) {
            String txt = obj
            acc.add(txt)
        } else if (obj instanceof MimeMultipart) {
            MimeMultipart mmp = obj
            for (int j=0; j<mmp.getCount(); j++) {
                BodyPart bp = mmp.getBodyPart(j)
                Object obj2 = bp.getContent()
                getTextsAux(obj2, acc)
            }
        } else if (obj instanceof BASE64DecoderStream) {
            BASE64DecoderStream ds = obj
            //acc.add("BASE64DecoderStream") //ds.getText())
        } else if (obj instanceof IMAPInputStream) {
            IMAPInputStream is = obj
            acc.add(is.getText())
        } else {
            throw new RuntimeException("unknown text type ${obj.getClass()} with content ${obj.toString()}")
        }
    }

}
