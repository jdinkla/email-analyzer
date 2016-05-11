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

    @Id
    Long id

    List<String> recipients
    List<String> froms
    String subject

    @Field(type=FieldType.Date, format=DateFormat.custom, pattern = Constants.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    Date sentDate

    @Field(type=FieldType.Date, format=DateFormat.custom, pattern = Constants.DATE_FORMAT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.DATE_FORMAT)
    Date receivedDate

    List<String> texts

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
