package mvc

import org.jboss.logging.Param

class SocrativeController {

    def index() {
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
        qb.save(flush: true, failOnError: true)
        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false))
        a1.save(flush: true, failOnError: true)
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false))
        a2.save(flush: true, failOnError: true)
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false))
        a3.save(flush: true, failOnError: true)
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false))
        a4.save(flush: true, failOnError: true)

        Question q = new Question(questionblock: qb, question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save(flush: true, failOnError: true)

        if(params.containsKey('save')){
            //index()
            redirect(uri: "/socrative/index")
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

        Question q = new Question(questionblock: qb, question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save(flush: true)

        if(params.containsKey('save')){
            redirect(uri: "/socrative/index")
        } else if(params.containsKey('addmore')) {
            render view:"addMoreQuestionsView", model:[title:qb.name, id:qb.id]
        }
    }

    def getQuestions(){
        List<Questionblock> qbs = Questionblock.all
        List<Question> qs = Question.all
        List<Answer> ans = Answer.all


        Questionblock questionblock = Questionblock.get(Integer.parseInt(params.get("questionblockID").toString()))
        println(questionblock)
        List<Question> a = Question.all
        List<Question> questions = Question.findByQuestionblock(questionblock) as List<Question>
        println(questions)
        render view:"quizView", model:[questions: questions]
    }


}
