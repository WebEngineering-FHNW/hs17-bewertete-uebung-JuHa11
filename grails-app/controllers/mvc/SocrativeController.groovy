package mvc

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
        question = Question.findByQuestionblock(qb)
        render view:"questionBlockOverView", model:[questions: question]
    }

    def save () {
        if(params.get("action") == "save"){
            Questionblock qb = new Questionblock(name: params.get("name"), numberOfQuestions: 1 )
            qb.save()
            Answer a1 = new Answer(answer: params.get("answer")[0], correct: params.get("answer1correct"))
            a1.save()
            print(a1.answer + " " + a1.correct);
        }
        render view:"addQuestionBlockView"
    }


}
