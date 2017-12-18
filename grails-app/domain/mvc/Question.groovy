package mvc

class Question {

    String question
    Answer answer1
    Answer answer2
    Answer answer3
    Answer answer4
    String explanation

    static constraints = {
        explanation nullable: true
    }

    static mapping = {
        question type: 'text'
        explanation type: 'text'
    }

    List<BigDecimal> getStatistic() {
        int answer1chosen = this.answer1.chosen
        int answer2chosen = this.answer2.chosen
        int answer3chosen = this.answer3.chosen
        int answer4chosen = this.answer4.chosen
        int total = answer1chosen + answer2chosen + answer3chosen + answer4chosen
        List<BigDecimal> percent = new ArrayList<>()
        if(answer1chosen !=  0){
            percent.add(new BigDecimal((answer1chosen/total)*100))
        } else {
            percent.add(new BigDecimal(0))
        }
        if(answer2.chosen != 0){
            percent.add(new BigDecimal((answer2chosen/total)*100))
        } else {
            percent.add(new BigDecimal(0))
        }
        if(answer3.chosen != 0){
            percent.add(new BigDecimal((answer3chosen/total)*100))
        } else {
            percent.add(new BigDecimal(0))
        }
        if(answer4.chosen != 0){
            percent.add(new BigDecimal((answer4chosen/total)*100))
        } else {
            percent.add(new BigDecimal(0))
        }
        return percent
    }
}
