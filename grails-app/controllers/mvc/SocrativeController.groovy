package mvc

import org.jboss.logging.Param

class SocrativeController {

    def index() {
        Questionblock qb = new Questionblock(name: "Block 1", numberOfQuestions: 2)
        qb.save()
        List<Questionblock> qblocks = Questionblock.all
        render view:"questionBlockOverView", model:[qblocks:qblocks]
    }

    def addQuestionBlock(){
        render view:"addQuestionBlockView"
    }

    def getQuestion(Questionblock qb){
        Question question = Question.findByQuestionblock(qb)
        render view:"questionBlockOverView", model:[questions: question]
    }

    def save () {
        Questionblock qb = new Questionblock(name: params.get("name"), numberOfQuestions: 1 )
        qb.save()
        Answer a1 = new Answer(answer: params.get("answer")[0], correct: params.get("answer1correct"))
        a1.save()
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: params.get("answer2correct"))
        a2.save()
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: params.get("answer3correct"))
        a3.save()
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: params.get("answer4correct"))
        a4.save()

        Question q = new Question(questionblock: qb, question: params.get("name"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save()

        if(params.containsKey('save')){
            index()
        } else if(params.containsKey('addmore')) {
            render view:"addMoreQuestionsView", model:[title:params.get("name"), id:qb.id]
        }
    }

    def saveMoreQuestions(){
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("id")))

        qb.numberOfQuestions++
        qb.save(flush: true) //wtf? Save sollte saven, auch wenn kein flag gesetzt worden ist.

        Answer a1 = new Answer(answer: params.get("answer")[0], correct: params.get("answer1correct"))
        a1.save(flush: true)
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: params.get("answer2correct"))
        a2.save(flush: true)
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: params.get("answer3correct"))
        a3.save(flush: true)
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: params.get("answer4correct"))
        a4.save(flush: true)

        Question q = new Question(questionblock: qb, question: params.get("name"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save(flush: true)

        if(params.containsKey('save')){
            index()
        } else if(params.containsKey('addmore')) {
            render view:"addMoreQuestionsView", model:[title:qb.name, id:qb.id]
        }
    }


}
