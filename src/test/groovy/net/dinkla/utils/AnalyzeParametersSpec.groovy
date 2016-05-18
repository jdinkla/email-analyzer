package net.dinkla.utils

import spock.lang.Specification

/**
 * Created by Dinkla on 18.05.2016.
 */
class AnalyzeParametersSpec extends Specification {

    def "parse #1"() {
        expect: AnalyzeParameters.split("Java") == ["Java"]
    }

    def "parse #2"() {
        expect: AnalyzeParameters.split("") == []
    }

    def "parse #3"() {
        expect: AnalyzeParameters.split("A,B") == ["A", "B"]
    }

    def "parse #4"() {
        expect: AnalyzeParameters.split("A, B") == ["A", "B"]
    }

    def "parse #5"() {
        expect: AnalyzeParameters.split("A , B") == ["A", "B"]
    }

    def "parse #6"() {
        expect: AnalyzeParameters.split("A , B ") == ["A", "B"]
    }

    def "parse #7"() {
        expect: AnalyzeParameters.split(" A , B ") == ["A", "B"]
    }

    def "parse #8"() {
        expect: AnalyzeParameters.split("A C, B") == ["A C", "B"]
    }

    def "parse #9"() {
        expect: AnalyzeParameters.split("A C , B") == ["A C", "B"]
    }

    def "parse #10"() {
        expect: AnalyzeParameters.split(" A C, B") == ["A C", "B"]
    }

    def "parse #11"() {
        expect: AnalyzeParameters.split("A,B C") == ["A", "B C"]
    }

    def "parse #12"() {
        expect: AnalyzeParameters.split("A, B C") == ["A", "B C"]
    }

    def "parse #13"() {
        expect: AnalyzeParameters.split("A, B C ") == ["A", "B C"]
    }

    def "parse #14"() {
        expect: AnalyzeParameters.split("Java, Spring Boot, Scala") == ["Java", "Spring Boot", "Scala"]
    }
}
