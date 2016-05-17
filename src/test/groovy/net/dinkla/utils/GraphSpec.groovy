package net.dinkla.utils

import com.fasterxml.jackson.databind.ObjectMapper
import spock.lang.Specification

/**
 * Created by Dinkla on 17.05.2016.
 */
class GraphSpec extends Specification {

    final def dates = ["2016-01-01 00:00:00.000000", "2016-02-01 00:00:00.000000", "2016-03-01 00:00:00.000000", "2016-04-01 00:00:00.000000"]

    def createExamples() {
        def t0 = new Graph()
        t0.x = dates
        t0.y = [10, 15, 13, 17]
        t0.type = 'scatter'
        t0.name = 'Java'

        def t1 = new Graph()
        t1.x = dates
        t1.y = [16, 5, 11, 9]
        t1.type = 'scatter'
        t1.mode = 'lines+markers'
        t1.name = 'C++'

        [t0, t1]
    }

    def es = createExamples()
    def t0 = es[0]
    def t1 = es[0]

    def "convert to JSON"() {
        ObjectMapper mapper = new ObjectMapper()
        String result = mapper.writeValueAsString(t0)
        println "result=$result"
        expect: result.size() > 0
    }

}
