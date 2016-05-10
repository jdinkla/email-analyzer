package net.dinkla.email

import com.sun.mail.imap.IMAPInputStream
import com.sun.mail.util.BASE64DecoderStream

import javax.mail.BodyPart
import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.Store
import javax.mail.internet.MimeMultipart

/**
 * Created by Dinkla on 10.05.2016.
 */
class ImapReader {



    public static void main(String[] args) {


        boolean debug = false
        EmailProps ep = EmailProps.readFromFile('secret.properties')

        // Get a properties object
        Properties props = System.getProperties();

        // Get the default Session object
        Session session = Session.getInstance(props, null);
        session.setDebug(debug);

        // Get a Store object that implements the protocol.
        Store store = session.getStore(ep.protocol);
        store.connect(ep.host, ep.user, ep.password);

        System.out.println("Connected...");

        //Folder rf = store.getFolder("/")
        //Folder rf = store.getDefaultFolder()
        Folder rf = store.getFolder("Akquise")
        rf.open(Folder.READ_ONLY)
        printFolder(rf)
        rf.close(false)

        store.close()

    }

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
            acc.add("BASE64DecoderStream") //ds.getText())
        } else if (obj instanceof IMAPInputStream) {
            IMAPInputStream is = obj
            acc.add(is.getText())
        } else {
            throw new RuntimeException("unknown text type ${obj.getClass()} with content ${obj.toString()}")
        }
    }

    static void printFolder(Folder folder, String tab = "") {
        println(tab + "Name:      '${folder.getName()}'")
        println(tab + "Full Name: '${folder.getFullName()}'")
        println(tab + "URL:       '${folder.getURLName()}'")

        boolean isDirectory = (folder.getType() & Folder.HOLDS_FOLDERS) != 0

        if (isDirectory) {
            println(tab + "Total Messages:  " + folder.getMessageCount());
            println(tab + "New Messages:    " + folder.getNewMessageCount());
            println(tab + "Unread Messages: " + folder.getUnreadMessageCount());

            final int c = folder.getMessageCount();
            for (int i=0; i<c; i++) {
                println("---------------------------------------------------------------------------------")
                Message msg = folder.getMessage(i+1)
                println("${msg.sentDate} '${msg.subject}'")
                String from = msg.from
                List<String> txts = getTexts(msg)

                for (String txt : txts) {
                    println("---  '${txt.substring(0, Math.min(txt.size(), 100))}'")
                }
/*
                Object obj = msg.getContent()
                if (obj instanceof String) {
                    String txt = obj
                    println("--- TXT:  ---")
                } else if (obj instanceof MimeMultipart) {
                    MimeMultipart mmp = obj
                    for (int j=0; j<mmp.getCount(); j++) {
                        BodyPart bp = mmp.getBodyPart(j)
                        Object obj2 = bp.getContent()
                        if (obj2 instanceof String) {
                            String txt = obj2
                            println("------ #$j ${txt.substring(0, Math.min(txt.size() - 1, 100))} ---")
                        } else if (obj2 instanceof MimeMultipart) {
                            MimeMultipart mmp2 = obj2
                            for (int k=0; k<mmp2.getCount(); k++) {
                                BodyPart bp2 = mmp2.getBodyPart(j)
                                Object obj3 = bp2.getContent()
                                if (obj3 instanceof String) {
                                    String txt = obj3
                                    println("--------- #$k ${txt.substring(0, Math.min(txt.size() - 1, 100))} ---")
                                } else {
                                    println("--------- #$k ${obj3.toString()} ---")
                                }
                            }
                        } else {
                            println("------ #$j ${obj2.toString()} ---")
                        }
                    }
                } else {
                    println("--- ${obj.toString()} ---")
                }
*/
            }
            //printFolder(folder, "$tab  ")
        }

    }
}
