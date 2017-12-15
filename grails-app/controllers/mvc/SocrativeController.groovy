package mvc

class SocrativeController {

    /*
     * Getting all Questionblocks for Startpage.
     */
    def index() {
        List<Questionblock> qblocks = Questionblock.all
        List<Answer> answers = Answer.all
        render view:"questionBlockOverView", model:[qblocks:qblocks]
    }

    /*
     * rendering addQuestionBlockView.
     */
    def addQuestionBlock(){
        render view:"addQuestionBlockView"
    }

    /*
     * Creates a new Questionblock and saves
     */
    def save () {
        Questionblock qb = new Questionblock(name: params.get("name"), numberOfQuestions: 1 )
        save(params, qb)
    }

    /*
     * Get Questionblock and saves answers
     */
    def saveMoreQuestions(){
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("id")))
        qb.numberOfQuestions++
        save(params, qb)
    }

    /*
     * Saves the question with all answers
     */
    def save(params, Questionblock qb){
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

    /*
     * Start Questionset
     * getting first question for the quiz.
     */
    def startQuestionset(){
        Questionblock qb = Questionblock.get(Integer.parseInt(params.get("questionblockID").toString()))
        render view:"quizView", model:[question: qb.questions[0], index: 0, questionblockID: params.get("questionblockID")]
    }

    /*
     * Check answer and
     * Get next Question
     */
    def nextQuestion() {
        //Check answer
        int questionBlockId = intParam(params, "questionblockID")
        Questionblock qb = Questionblock.get(questionBlockId)
        if(qb == null) {
            render view:"/error"
            return
        }
        int index = intParam(params, 'index')

        Question q = qb.questions[index]
        if(q == null) {
            render view:"/error"
            return
        }

        boolean correct = isCorrect(q,
            (params.get("answer1correct")?true:false),
            (params.get("answer2correct")?true:false),
            (params.get("answer3correct")?true:false),
            (params.get("answer4correct")?true:false))

        //get new Question
        index = index + 1
        if(qb.questions[index] != null){
            render view:"quizView", model:[
                    question: qb.questions[index],  // todo dk: is index inside the available range?
                    index: index,
                    questionblockID: questionBlockId,
                    lastquestion: correct
            ]
        } else {
            //no question left.
            //TODO: getResult;
            //TODO: render view:"quizView", model:[question: null, points: , numberOfQuestions: ]
            render view:"quizView", model:[
                    question: null,
                    index: 0,
                    questionblockID: questionBlockId,
                    lastquestion: correct,
                    numberOfQuestions: 0
            ]
        }
    }

    static Integer intParam(params, String parameterName) {
        params.get(parameterName)?.toInteger() ?: 0
    }

    /*
     * Check if the question was answered correct
     */
    def isCorrect(Question q, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        if(q.answer1.correct != answer1 || q.answer2.correct != answer2 || q.answer3.correct != answer3 || q.answer4.correct != answer4) {
            return false
        }
        //TODO: increase number of correct questions
        return true
    }
}
