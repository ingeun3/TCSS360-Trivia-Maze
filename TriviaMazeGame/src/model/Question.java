package model;
/**
 *
 * This class is the Question class that creates the Questions for Player.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import java.util.ArrayList;

public class Question {

    /** Initialized the question. */
    private final String myQuestion;

    /** The list of answers to the question. */
    private final ArrayList<String> myAnswers;

    /**
     * The default constructor for Question object.
     * @param theQuestion the question prompt for making Question object.
     */
    public Question(final String theQuestion) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<>();
    }



    /**
     * Constructs answer for the question.
     * @param theAnswer the answer to include in the question.
     */
    public void addAnswers(String theAnswer) {
        myAnswers.add(theAnswer);
    }

    /**
     * Returns the question prompt.
     * @return the question prompt.
     */
    public String getQuestion() {
        return myQuestion;
    }



    /**
     * Returns the ArrayList of answers this question has.
     * @return the lists of answers this question has.
     */
    public ArrayList<String> getAnswers() {
        return myAnswers;
    }
}
