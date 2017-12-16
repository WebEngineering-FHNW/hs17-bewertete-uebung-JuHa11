package mvc

class Questionblock {

    String name
    int numberOfQuestions
    List<Question> questions = new ArrayList<>()
    int highscore
    List<Boolean> correct = new ArrayList<>()

    static constraints = {
    }

    int getResult() {
        int i = 0
        for(Boolean b : correct){
            if(b){
                i++
            }
        }
        if(highscore > i) highscore = i
        return i
    }
}
