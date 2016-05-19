package net.dinkla.imap

import net.dinkla.email.Email
import net.dinkla.email.EmailFolder
import net.dinkla.email.EmailService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Dinkla on 11.05.2016.
 */
@Service
class EmailServerService {

    @Autowired
    EmailService service

    List<String> providersCache;

    Long importEmails(final EmailServerProperties ep) {
        Long startId = 0
        try {
            // in case the index does not exist, we catch this
            startId = service.findMaximalId()
            startId++
        } catch (Exception e) {
        } finally {
        }
        return importEmails(ep, startId)
    }

    Long importEmails(final EmailServerProperties ep, final Long startId) {
        def ir = new EmailServerReader(ep)
        ir.connect()
        EmailFolder folder = ir.read(ep.folder)

        Long numProcessed = 0
        Long id = startId
        for (Email em : folder.msgs) {
            em.id = id++
            numProcessed++
            service.add(em)
        }

        folder.close()
        ir.close()

        return numProcessed
    }

    List<String> getProviders() {
        if (!providersCache) {
            providersCache = EmailServerReader.getProviders()
        }
        providersCache
    }

}
