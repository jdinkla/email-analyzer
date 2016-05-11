package net.dinkla.email

import org.elasticsearch.client.Client
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

import java.time.Instant

@SpringBootApplication
class EmailAnalyzerApplication implements CommandLineRunner {

    @Autowired
    EmailService service

    void test() {
        // add an email
        Email em = new Email()
        em.froms = [ "from@from.fro"]
        em.subject = "This email is about Spring"
        em.texts = [ "Spring", "in Spring"]
        em.sentDate = Date.from(Instant.now())
        em.receivedDate = Date.from(Instant.now())
        em.recipients = [ "rec@rec.rec"]
        service.add(em)

        // get it
        List<Email> ems = service.findBySubjectLike("Spring")
        println ems
    }

    void readEmailsAndSave() {
        final EmailProps ep = EmailProps.readFromFile('secret.properties')
        final String folderName = "Akquise"

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

	@Override
	void run(String... args) throws Exception {
        readEmailsAndSave()

        /*
        // get some
        List<Email> ems = service.findBySubjectLike("gesucht")
        println ems
        */


    }

	static void main(String[] args) {
		SpringApplication.run EmailAnalyzerApplication, args
	}
}
