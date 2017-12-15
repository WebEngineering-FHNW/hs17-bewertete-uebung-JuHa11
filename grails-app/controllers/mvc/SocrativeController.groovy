package mvc

import org.jboss.logging.Param

class SocrativeController {

    def index() {
        List<Questionblock> qblocks = Questionblock.all
        List<Answer> answers = Answer.all
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

        List<Answer> answers = Answer.all


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
        int questionBlockId = intParam(params, "questionblockID")
        Questionblock qb = Questionblock.get(questionBlockId)
        int index = intParam(params, 'index')
        println "index is : $index"      // todo dk: check
        Question q = qb.questions[index] // todo dk: what happens if qb is null? see (1)
        println "question is : $q"       // todo dk: check
        List<Question> a = Question.all  // todo dk: is 'a' ever used ?
        List<Answer> b = Answer.all      // todo dk: is 'b' ever used ?
        boolean correct = isCorrect(q,
            (params.get("answer1correct")?true:false), // todo dk: these are boolean arguments
            (params.get("answer2correct")?true:false), //no shit sherlock?
            (params.get("answer3correct")?true:false),
            (params.get("answer4correct")?true:false))

        //get new Question
        index = index + 1
        if(qb != null){                         // todo dk: (1) apparently, qb might be null
            render view:"quizView", model:[
                question: qb.questions[index],  // todo dk: is index inside the available range?
                index: index,
                questionblockID: questionBlockId,
                lastquestion: correct
            ]
        } else {
            //TODO: getResult;
            //TODO: render view:"quizView", model:[question: null, points: , numberOfQuestions: ]
        }
    }

    static Integer intParam(params, String parameterName) {
        params.get(parameterName)?.toInteger() ?: 0
    }

    def isCorrect(Question q, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        // todo dk: which type is q.answer1?
        println "q.answer1 == answer1 : ${q.answer1 == answer1}"
        println "q.answer1 != answer1 : ${q.answer1 != answer1}"
        println "suprise!"
        if(q.answer1.correct != answer1 || q.answer2.correct != answer2 || q.answer3.correct != answer3 || q.answer4.correct != answer4) {
            return false
        }
        return true
        // todo dk: can you code the above by using == instead of != ?
        //          then you see the error immediately.
        //          (it also make the code much nicer ...)
    }
}
