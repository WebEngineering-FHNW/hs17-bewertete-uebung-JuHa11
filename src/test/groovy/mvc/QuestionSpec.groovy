package mvc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Question)
class QuestionSpec extends Specification {
    Question q
    Answer a1
    Answer a2
    Answer a3
    Answer a4

    def setup() {
        a1 = new Answer(answer: "Answer 1", correct: true)
        a2 = new Answer(answer: "Answer 2", correct: false)
        a3 = new Answer(answer: "Answer 3", correct: true)
        a4 = new Answer(answer: "Answer 4", correct: false)
        q = new Question(question: "Question", answer1: a1, answer2: a2, answer3: a3, answer4: a4)
    }

    def cleanup() {
        q.delete()
        a1.delete()
        a2.delete()
        a3.delete()
        a4.delete()
    }

    void "getStatistic"() {
        when:
            a1.incChosen()
            a2.incChosen()
            a3.incChosen()
            a2.incChosen()
            a2.incChosen()
            List<BigDecimal> statistic = q.getStatistic()
        then:
            statistic.get(0) == 20
            statistic.get(1) == 60
            statistic.get(2) == 20
            statistic.get(3) == 0
    }


}
