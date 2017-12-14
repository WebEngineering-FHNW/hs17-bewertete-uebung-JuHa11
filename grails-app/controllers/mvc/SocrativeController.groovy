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

    def save () {
        Questionblock qb = new Questionblock(name: params.get("name"), numberOfQuestions: 1 )

        Object o = params.get('answer')

        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false))
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false))
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false))
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false))

        a1.save(flush:true, failOnError: true)
        a2.save(flush:true, failOnError: true)
        a3.save(flush:true, failOnError: true)
        a4.save(flush:true, failOnError: true)
        Question q = new Question(question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save(flush: true, failOnError: true)

        qb.questions.add(q)

        qb.save(flush:true,failOnError:true)

        if(qb.hasErrors()){
            println("ERROR: ")
            qb.getErrors().allErrors.forEach{e -> println(e.toString())}
        }

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

        Object o = params.get('answer')

        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false))
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false))
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false))
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false))

        Question q = new Question(question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)
        q.save(flush: true, failOnError: true)
        qb.questions.add(q)

        qb.save(flush:true,failOnError:true)

        if(qb.hasErrors()){
            println("ERROR: ")
            qb.getErrors().allErrors.forEach{e -> println(e.toString())}
        }

        if(params.containsKey('save')){
            redirect(uri: "/socrative/index")
        } else if(params.containsKey('addmore')) {
            render view:"addMoreQuestionsView", model:[title:qb.name, id:qb.id]
        }
    }

    def startQuestionset(){
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("questionblockID").toString()))
        render view:"quizView", model:[question: qb.questions[0], index: 0, questionblockID: params.get("questionblockID")]
    }

    def nextQuestion() {
        //Check answer
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("questionblockID").toString()))
        Question q = qb.questions[Integer.parseInt(params.get("index"))]
        List<Question> a = Question.all
        List<Answer> b = Answer.all
        boolean correct = isCorrect(q, (params.get("answer1correct")?true:false), (params.get("answer2correct")?true:false), (params.get("answer3correct")?true:false), (params.get("answer4correct")?true:false))

        //get new Question
        int index = Integer.parseInt(params.get('index')) + 1
        if(qb != null){
            render view:"quizView", model:[question: qb.questions[index], index: index, questionblockID: params.get("questionblockID"), lastquestion: correct]
        } else {
            //TODO: getResult;
            //TODO: render view:"quizView", model:[question: null, points: , numberOfQuestions: ]
        }
    }

    def isCorrect(Question q, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        if(q.answer1 != answer1 || q.answer2 != answer2 || q.answer3 != answer3 || q.answer4 != answer4) {
            return false
        }
        return true
    }
}
