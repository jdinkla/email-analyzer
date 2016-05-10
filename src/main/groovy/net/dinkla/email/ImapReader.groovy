package net.dinkla.email

import javax.mail.Folder
import javax.mail.Message
import javax.mail.Session
import javax.mail.Store

/**
 * Created by Dinkla on 10.05.2016.
 */
class ImapReader {

    static boolean debug = false

    EmailProps ep

    Store store
    Session session

    ImapReader(EmailProps ep) {
        this.ep = ep
    }

    void connect() {
        // Get a properties object
        Properties props = System.getProperties();

        // Get the default Session object
        session = Session.getInstance(props, null);
        session.setDebug(debug);

        // Get a Store object that implements the protocol.
        store = session.getStore(ep.protocol);
        store.connect(ep.host, ep.user, ep.password);
    }

    void close() {
        if (store.isConnected()) {
            store.close()
        }
    }

    @Override
    protected void finalize() throws Throwable {
        close()
        super.finalize()
    }

    MyFolder read(String folderName) {
        List<MyMessage> msgs = new LinkedList<>()
        Folder folder = store.getFolder(folderName)
        folder.open(Folder.READ_ONLY)
        printFolder(folder, msgs)
//        folder.close(false)

        MyFolder mf = new MyFolder()
        mf.folder = folder
        mf.msgs = msgs
        return mf
    }


    void printFolder(Folder folder, List<MyMessage> msgs) {
//        println(tab + "Name:      '${folder.getName()}'")
//        println(tab + "Full Name: '${folder.getFullName()}'")
//        println(tab + "URL:       '${folder.getURLName()}'")

        boolean isDirectory = (folder.getType() & Folder.HOLDS_FOLDERS) != 0

        if (isDirectory) {
//            println(tab + "Total Messages:  " + folder.getMessageCount());
//            println(tab + "New Messages:    " + folder.getNewMessageCount());
//            println(tab + "Unread Messages: " + folder.getUnreadMessageCount());

            final int c = folder.getMessageCount();
            for (int i=0; i<c; i++) {
                Message msg = folder.getMessage(i+1)
                List<String> txts = Utils.getTexts(msg)

//                for (String txt : txts) {
//                    println("---  '${txt.substring(0, Math.min(txt.size(), 100))}'")
//                }

                MyMessage mmsg = new MyMessage()
                mmsg.msg = msg
                mmsg.texts = txts

                msgs.add(mmsg)
            }
        }

    }

    public static void main(String[] args) {
        EmailProps ep = EmailProps.readFromFile('secret.properties')

        def ir = new ImapReader(ep)
        ir.connect()

        MyFolder folder = ir.read("Akquise")

        for (MyMessage msg : folder.msgs) {
            println("---------------------------------------------------------------------------------")
            println("${msg.msg.sentDate} '${msg.msg.subject}'")
            for (String txt : msg.texts) {
                println("---  '${txt.substring(0, Math.min(txt.size(), 100))}'")
           }
        }

    }
}
