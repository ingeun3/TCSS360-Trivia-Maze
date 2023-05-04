package model;

public class Answer {

    private String myAnswer;

    private boolean myCorrectness;

    public Answer(String theAnswer, boolean theCorrectness) {
        myAnswer = theAnswer;
        myCorrectness = theCorrectness;
    }

    public String getAnswer() {
        return myAnswer;
    }

    public boolean isCorrect() {
        return myCorrectness;
    }
}
