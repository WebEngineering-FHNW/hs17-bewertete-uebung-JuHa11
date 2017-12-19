package mvc

class SocrativeController {

    /*
     * Getting all Questionblocks for Startpage.
     */
    def index() {
        List<Questionblock> qblocks = Questionblock.all
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
        boolean id = params.get("id")==null
        Questionblock qb
        if(id){
            qb = new Questionblock(name: params.get("name"), highscore: 0)
        } else {
            qb = Questionblock.get(Integer.parseInt(params.get("id")))
        }
        savequestion(params, qb)
        if(params.containsKey('save')){
            redirect(uri: "/socrative/index")
        } else if(params.containsKey('addmore')) {
            render view:"addQuestionBlockView", model:[title:qb.name, id:qb.id]
        }
    }

    /*
     * Saves the question with all answers
     */
    def savequestion(params, Questionblock qb){
        Answer a1 = new Answer(answer: params.get("answer")[0], correct: (params.get("answer1correct")?true:false), chosen: 0)
        Answer a2 = new Answer(answer: params.get("answer")[1], correct: (params.get("answer2correct")?true:false), chosen: 0)
        Answer a3 = new Answer(answer: params.get("answer")[2], correct: (params.get("answer3correct")?true:false), chosen: 0)
        Answer a4 = new Answer(answer: params.get("answer")[3], correct: (params.get("answer4correct")?true:false), chosen: 0)

        Question q = new Question(question: params.get("question"), answer1: a1, answer2: a2, answer3: a3, answer4: a4, explanation: params.get("explanation"))
        q.save(flush: true, failOnError: true)

        qb.questions.add(q)
        qb.correct.add(false)
        qb.save(flush:true,failOnError:true)

        if(qb.hasErrors()){
            println("ERROR: ")
            qb.getErrors().allErrors.forEach{e -> println(e.toString())}
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

        boolean correct = isCorrect(qb, index, q,
            (params.get("answer1correct")?true:false),
            (params.get("answer2correct")?true:false),
            (params.get("answer3correct")?true:false),
            (params.get("answer4correct")?true:false))

        //get new Question
        index = index + 1
        if(qb.questions[index] != null){
            render view:"quizView", model:[
                    question        : qb.questions[index],  // todo dk: is index inside the available range?
                    index           : index,
                    questionblockID : questionBlockId,
                    previousquestion: correct,
                    explanation: q.explanation
            ]
        } else {
            //no question left.
            //TODO: getResult;
            //TODO: render view:"quizView", model:[question: null, points: , numberOfQuestions: ]
            int result = qb.getResult()
            if(result > qb.highscore) {
                qb.highscore = result
                qb.save(flush: true, failOnError: true)
            }
            render view:"quizView", model:[
                    question         : null,
                    index            : 0,
                    questionblockID  : questionBlockId,
                    previousquestion : correct,
                    numberOfQuestions: result,
                    explanation: q.explanation
            ]
        }
    }

    static Integer intParam(params, String parameterName) {
        params.get(parameterName)?.toInteger() ?: 0
    }

    /*
     * Check if the question was answered correct
     */
    def isCorrect(Questionblock qb, int index, Question q, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        doStatistic(q, answer1, answer2, answer3, answer4)
        if(q.answer1.correct != answer1 || q.answer2.correct != answer2 || q.answer3.correct != answer3 || q.answer4.correct != answer4) {
            qb.correct[index] = false
            qb.save(flush:true)
            return false
        }
        qb.correct[index] = true
        qb.save(flush:true)
        return true
    }

    def doStatistic(Question q, boolean answer1, boolean answer2, boolean answer3, boolean answer4) {
        if(answer1){
            q.answer1.incChosen()
            q.answer1.save(flush: true)
        }
        if(answer2){
            q.answer2.incChosen()
            q.answer2.save(flush: true)
        }
        if(answer3){
            q.answer3.incChosen()
            q.answer3.save(flush: true)
        }
        if(answer4){
            q.answer4.incChosen()
            q.answer4.save(flush: true)
        }
    }

    def getDetails() {
        int questionBlockId = intParam(params, "questionblockID")
        Questionblock qb = Questionblock.get(questionBlockId)
        if(qb == null){
            render view:"/error"
            return
        }
        List<List<BigDecimal>> statistics = new ArrayList<>()
        for(Question q : qb.questions ) {
            statistics.add(q.getStatistic())
        }
        render view:"questionblockDetailsView", model: [
                questionblocktitle: qb.name,
                questions: qb.questions,
                statistic: statistics
        ]
    }
}
