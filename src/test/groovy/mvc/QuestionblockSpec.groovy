package mvc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Questionblock)
class QuestionblockSpec extends Specification {
    Questionblock qb;
    def setup() {
        qb = new Questionblock(name: "Questionblock1", highscore: 0 )
    }

    def cleanup() {
        qb.delete()
    }

    void "getNumberOfQuestions"() {
        when:
            int numberOfQuestions = qb.getNumberOfQuestions()
        then:
            numberOfQuestions == 0
    }

    void "getResult"() {
        when:
            qb.correct.add(false)
            qb.correct.add(true)
            qb.correct.add(true)
            qb.correct.add(true)
            qb.correct.add(false)
            qb.correct.add(true)
            int correct = qb.getResult()
        then:
            correct == 4
    }




}
