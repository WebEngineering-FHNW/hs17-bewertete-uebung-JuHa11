package mvc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Answer)
class AnswerSpec extends Specification {
    Answer a

    def setup() {
        a = new Answer(answer: "Answer 1", correct: true)
    }

    def cleanup() {
        a.delete()
    }

    void "incChosen"() {
        when:
            a.incChosen()
            a.incChosen()
        then:
            2 == a.chosen
        when:
            a.incChosen()
        then:
            3 == a.chosen
    }


}
