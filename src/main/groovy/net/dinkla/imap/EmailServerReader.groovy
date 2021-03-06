package net.dinkla.imap

import net.dinkla.email.Email
import net.dinkla.email.EmailFolder
import net.dinkla.email.EmailUtils

import javax.mail.Folder
import javax.mail.Message
import javax.mail.Provider
import javax.mail.Session
import javax.mail.Store

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailServerReader {

    static boolean debug = false

    EmailServerProperties ep

    Store store
    Session session

    EmailServerReader(EmailServerProperties ep) {
        this.ep = ep
    }

    void connect() {
        // Get a properties object
        Properties props = System.getProperties();

        // Get the default Session object
        session = Session.getInstance(props, null);
        session.setDebug(debug);

        // Get a store object that implements the protocol.
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

    EmailFolder read(String folderName) {
        List<Email> msgs = new LinkedList<>()
        Folder folder = store.getFolder(folderName)
        folder.open(Folder.READ_ONLY)
        getMessages(folder, msgs)

        EmailFolder mf = new EmailFolder()
        mf.folder = folder
        mf.msgs = msgs
        return mf
    }


    void getMessages(Folder folder, List<Email> msgs) {

        boolean isDirectory = (folder.getType() & Folder.HOLDS_FOLDERS) != 0

        if (isDirectory) {
            final int c = folder.getMessageCount();
            for (int i=0; i<c; i++) {
                Message msg = folder.getMessage(i+1)
                Email mmsg = EmailUtils.fromMessage(msg)
                msgs.add(mmsg)
            }
        }
    }

    // returns the providers (imap, pop3, etc.)
    static List<String> getProviders() {
        List<String> result = []
        Properties props = System.getProperties();
        Session session = Session.getInstance(props, null);
        for (Provider provider : session.getProviders()) {
            result.add(provider.getProtocol())
        }
        return result
    }

    public static void main(String[] args) {
        final EmailServerProperties ep = EmailServerProperties.readFromFile('secret.properties')
        final String folderName = "Akquise"

        def ir = new EmailServerReader(ep)
        ir.connect()
        EmailFolder folder = ir.read(folderName)

        for (Email msg : folder.msgs) {
            println("---------------------------------------------------------------------------------")
            println("${msg.sentDate} '${msg.subject}'")
            for (String txt : msg.texts) {
                println("---  '${txt.substring(0, Math.min(txt.size(), 100))}'")
           }
        }
        folder.close()
    }

}
