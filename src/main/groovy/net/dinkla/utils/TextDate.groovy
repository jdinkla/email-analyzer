package net.dinkla.utils

import java.text.DateFormat
import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 11.05.2016.
 */
class TextDate implements Serializable {

    static DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hhmmss")

    private Date date

    TextDate(Date date) {
        this.date = date
    }

    String getDate() {
        return toString()
    }

    @Override
    boolean equals(Object obj) {
        if (obj instanceof TextDate) {
            TextDate td = obj
            return toString() == obj.toString()
        } else {
            return false
        }
    }

    @Override
    String toString() {
        sdf.format(date)
    }

}
