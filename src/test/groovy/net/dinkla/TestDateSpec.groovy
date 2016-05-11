package net.dinkla

import spock.lang.Specification

import java.text.SimpleDateFormat

/**
 * Created by Dinkla on 11.05.2016.
 */
class TestDateSpec extends Specification {

    String example = "2016-01-02 030405"
    def sdf = new SimpleDateFormat("yyyy-MM-dd hhmmss")
    Date date = sdf.parse(example)
    TextDate td = new TextDate(date)

    def "testToString"() {
        expect: td.toString() == example
    }

}
