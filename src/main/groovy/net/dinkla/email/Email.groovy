package net.dinkla.email

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document

/**
 * Created by Dinkla on 10.05.2016.
 */
@Document(indexName = "email", type = "email")
class Email {

    @Id
    Long id

    List<String> froms
    String subject
    List<String> texts
    Date sentDate
    Date receivedDate
    List<String> recipients

    @Override
    public String toString() {
        return "Email{" +
                "id=" + id +
                ", froms=" + froms +
                ", subject='" + subject + '\'' +
                ", sentDate=" + sentDate +
                ", recipients=" + recipients +
                '}';
    }
}
