package net.dinkla
//@SpringBootApplication
/*
class EmailAnalyzerCommandLineApplication implements CommandLineRunner {

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
        //deleteAll()
        //importEmails()

        testCustomRepositoryAggregation()

    }

	static void main(String[] args) {
		SpringApplication.run EmailAnalyzerCommandLineApplication, args
	}

}
*/
