package net.dinkla.email

import javax.mail.Folder

/**
 * Created by Dinkla on 10.05.2016.
 */
class EmailFolder {

    Folder folder
    List<Email> msgs

    @Override
    protected void finalize() throws Throwable {
        close()
    }

    void close() {
        if (folder.isOpen()) folder.close(false)
    }

}
