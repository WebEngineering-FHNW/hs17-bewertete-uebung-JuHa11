package mvc

class SocrativeController {

    def index() {
        Questionblock qb = new Questionblock(name: "Block 1", numberOfQuestions: 2)
        qb.save()
        List<Questionblock> qblocks = Questionblock.all
        render view:"questionBlockOverView", model:[qblocks:qblocks]
    }

    def test(){
        render view:"addQuestionBlockView"
    }

    def getQuestion(Questionblock qb){
        question = Question.findByQuestionblock(qb)
        render view:"questionBlockOverView", model:[questions: question]
    }


}
