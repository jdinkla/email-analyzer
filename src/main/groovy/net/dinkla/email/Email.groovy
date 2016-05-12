package net.dinkla.email

import com.fasterxml.jackson.annotation.JsonFormat
import net.dinkla.Constants
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field
import org.springframework.data.elasticsearch.annotations.FieldType

/**
 * Created by Dinkla on 10.05.2016.
 */
@Document(indexName = Constants.EMAIL_INDEX, type = Constants.EMAIL_TYPE)
class Email {

    // Spring Data seems to need an @Id, so we use a surrogate one
    @Id
    Long id

    @Field(type=FieldType.Object)
    List<EmailAddress> recipients

    @Field(type=FieldType.Object)
    List<EmailAddress> froms
    String subject

    @Field(type=FieldType.Date, format=DateFormat.custom, pattern = Constants.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    Date sentDate

    @Field(type=FieldType.Date, format=DateFormat.custom, pattern = Constants.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    Date receivedDate

    List<String> texts

    Email() {
        recipients = new LinkedList<EmailAddress>()
        froms = new LinkedList<EmailAddress>()
    }

    void addRecipient(EmailAddress email) {
        recipients.add(email)
    }

    void addFroms(EmailAddress email) {
        froms.add(email)
    }

    List<EmailAddress> getFroms() {
        return froms
    }

    List<EmailAddress> getRecipients() {
        return recipients
    }

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
