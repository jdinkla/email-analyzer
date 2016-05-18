package net.dinkla.email

import net.dinkla.utils.Histogram
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Dinkla on 11.05.2016.
 */
@Service
class EmailService {

    @Autowired
    EmailRepository repository

    Email findById(Long id) {
        repository.findById(id)
    }

    List<Email> findBySubjectLike(String subject) {
        repository.findBySubjectLike(subject)
    }

    void add(Email email) {
        repository.save(email);
    }

    Long findMaximalId() {
        repository.findMaximalId()
    }

    Histogram<String, Integer> getWeeklyHistogram(String topic) {
        repository.getWeeklyHistogram(topic)
    }

    void createIndexIfNotExists() {
        repository.createIndexIfNotExists()
        // create the mapping using Spring Data Elasticsearch
        Email test = new Email()
        test.id = 12356789
        repository.save(test)
        // now delete it again
        repository.delete(test)
    }

}
