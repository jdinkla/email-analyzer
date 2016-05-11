package net.dinkla.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import java.time.Instant

@SpringBootApplication
class EmailAnalyzerApplication implements CommandLineRunner {

    @Autowired
    EmailService service

    @Autowired
    ImapService imaps

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

    void importEmails() {
        final EmailProps ep = EmailProps.readFromFile('secret.properties')
        final String folderName = "Akquise"

        imaps.importEmails(ep, folderName)
    }

    void deleteAll() {
        service.repository.deleteAll();
    }

	@Override
	void run(String... args) throws Exception {
        deleteAll()
        importEmails()
    }

	static void main(String[] args) {
		SpringApplication.run EmailAnalyzerApplication, args
	}

}
