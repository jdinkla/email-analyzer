package net.dinkla.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Dinkla on 11.05.2016.
 */
@Service
class ImapService {

    @Autowired
    EmailService service

    void importEmails(EmailProps ep, String folderName) {

        def ir = new ImapReader(ep)
        ir.connect()
        EmailFolder folder = ir.read(folderName)

        Long id = 0
        for (Email em : folder.msgs) {
            em.id = id++
            service.add(em)
        }
        folder.close()
    }

}