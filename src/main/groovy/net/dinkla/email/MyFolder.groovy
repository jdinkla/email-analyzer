package net.dinkla.email

import javax.mail.Folder

/**
 * Created by Dinkla on 10.05.2016.
 */
class MyFolder {

    Folder folder
    List<MyMessage> msgs

    @Override
    protected void finalize() throws Throwable {
        if (folder.isOpen()) folder.close(false)
    }

}
