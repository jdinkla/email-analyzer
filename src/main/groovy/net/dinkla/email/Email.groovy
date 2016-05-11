package net.dinkla.email

import net.dinkla.TextDate
import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.DateFormat
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field

/**
 * Created by Dinkla on 10.05.2016.
 */
@Document(indexName = "email3", type = "email")
class Email {

    @Id
    Long id

    List<String> recipients
    List<String> froms
    String subject

    @Field(format=DateFormat.basic_date_time_no_millis)
    TextDate sentDate

    @Field(format=DateFormat.basic_date_time_no_millis)
    TextDate receivedDate

    List<String> texts

    void setSentDate(Date date) {
        sentDate = new TextDate(date)
    }

    void setReceivedDate(Date date) {
        receivedDate = new TextDate(date)
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
