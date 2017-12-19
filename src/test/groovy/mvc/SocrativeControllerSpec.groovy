package mvc

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(SocrativeController)
class SocrativeControllerSpec extends Specification {

    Questionblock qb
    SocrativeController c

    def setup() {
        c = new SocrativeController()
        qb = new Questionblock(name: "Questionblock1", highscore: 0 )
    }

    def cleanup() {
        qb.delete()
    }

    /*void "savequestion"(){
        when:
            params.answer = ["answer1","answer2","answer3","answer4"]
            params.answer1correct = "checked"
            params.question = "Question"
            params.explanation = "explanation"
            c.savequestion(params, qb)
            Question q = qb.questions.get(0)
        then:
            q.question == "Question"

    }*/
}
