package net.dinkla

import net.dinkla.email.Email
import net.dinkla.email.EmailService
import net.dinkla.imap.EmailServerProperties
import net.dinkla.imap.EmailServerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

//@SpringBootApplication
@ComponentScan
class EmailAnalyzerCommandLineApplication implements CommandLineRunner {

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
        def x = service.repository.getWeeklyHistogram("Scala")
        println "getWeeklyHistogram returned: $x"
    }


    void importEmails() {
        final EmailServerProperties ep = EmailServerProperties.readFromFile('secret.properties')
        final String folderName = "Akquise"
        importEmails(ep, folderName)
    }

    void importEmails(final EmailServerProperties ep, final String folderName) {
        final Long maxId = service.repository.findMaximalId()
        final Long startId = maxId + 1
        imaps.importEmails(ep, folderName, startId)
    }

    void deleteAll() {
        service.repository.deleteAll();
    }

	@Override
	void run(String... args) throws Exception {
        println("RUN RUN RUN")
        //deleteAll()
        //importEmails()
        //testCustomRepositoryAggregation()
        testCustomRepositoryFindId()
    }

	static void main(String[] args) {
		SpringApplication.run EmailAnalyzerCommandLineApplication, args
	}

}

