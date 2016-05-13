package net.dinkla

import net.dinkla.email.Email
import net.dinkla.imap.EmailServerProperties
import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer

import java.time.Instant

@SpringBootApplication
class EmailAnalyzerWebApplication extends SpringBootServletInitializer  {

    @Autowired
    EmailService service

    @Autowired
    EmailServerService imaps

    void testCreate() {
        // add an email
        Email em = new Email()
        em.froms = ["from@from.fro"]
        em.subject = "This email is about Spring"
        em.texts = ["Spring", "in Spring"]
        em.sentDate = Date.from(Instant.now())
        em.receivedDate = Date.from(Instant.now())
        em.recipients = ["rec@rec.rec"]
        service.add(em)
    }

    void testQuery() {
        List<Email> ems = service.findBySubjectLike("Spring")
        println ems
    }

    void testCustomRepositoryFindId() {
        Long id = service.repository.findMaximalId()
        println "findMaximalId returned: $id"
    }

    void testCustomRepositoryAggregation() {
        def xs = service.repository.getWeeklyHistogram("Scala")
        println "getWeeklyHistogram returned: $xs"
    }

    void importEmails() {
        final EmailServerProperties ep = EmailServerProperties.readFromFile('secret.properties')
        ep.folder = "Akquise"
        Long numLoaded = imaps.importEmails(ep)
    }


    void deleteAll() {
        service.repository.deleteAll();
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(EmailAnalyzerWebApplication.class);
    }

    static void main(String[] args) {
		SpringApplication.run EmailAnalyzerWebApplication, args
	}

}

