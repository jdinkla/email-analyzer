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

	@Override
	void run(String... args) throws Exception {
		println "Hello"

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

	static void main(String[] args) {
		SpringApplication.run EmailAnalyzerApplication, args
	}
}
