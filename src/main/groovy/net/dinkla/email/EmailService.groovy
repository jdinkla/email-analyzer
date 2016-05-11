package net.dinkla.email

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * Created by Dinkla on 11.05.2016.
 */
@Service
class EmailService {

    @Autowired
    EmailRepository repository

    public Email findById(Long id) {
        repository.findById(id)
    }

    public List<Email> findBySubjectLike(String subject) {
        repository.findBySubjectLike(subject)
    }

    public void add(Email email) {
        repository.save(email);
    }


}
