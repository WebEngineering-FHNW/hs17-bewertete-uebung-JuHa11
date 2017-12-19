package mvc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Questionblock)
class QuestionblockSpec extends Specification {

    def setup() {
        //Questionblock qb = new Questionblock(name: "Questionblock1", highscore: 0 )
    }

    def cleanup() {
    }

    void "getNumberOfQuestions"() {
        int numberOfQuestions = qb.getNumberOfQuestions()
        numberOfQuestions == 0
    }


}
