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

        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false))
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false))
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false))
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false))

        Question q = new Question(question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)

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

        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false))
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false))
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false))
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false))

        Question q = new Question(question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4)

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
        int index = Integer.parseInt(params.get('index')) + 1
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("questionblockID").toString()))
        render view:"quizView", model:[question: qb.questions[index], index: index, questionblockID: params.get("questionblockID")]
    }


}
