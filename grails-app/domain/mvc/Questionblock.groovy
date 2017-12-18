package mvc

class Questionblock {

    String name
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
        return i
    }

    int getNumberOfQuestions() {
        return questions.size()
    }
}
