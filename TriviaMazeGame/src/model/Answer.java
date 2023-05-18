package model;

public class Answer {
    // Class fields

    // The answer to verify the correctness.
    private String myAnswer;
    // True if correct, false if otherwise.
    private boolean myCorrectness;

    /**
     * The default constructor for Answer object.
     * @param theAnswer the Answer to verify
     * @param theCorrectness the correctness of the answer.
     */
    public Answer(String theAnswer, boolean theCorrectness) {
        myAnswer = theAnswer;
        myCorrectness = theCorrectness;
    }

    /**
     * Getter for answer you want to verify.
     * @return the answer that was passed to verify
     */
    public String getAnswer() {
        return myAnswer;
    }

    /**
     * Getter for the correctness of the answer.
     * @return the correctness of this answer.
     */
    public boolean getCorrectness() {
        return myCorrectness;
    }
}
