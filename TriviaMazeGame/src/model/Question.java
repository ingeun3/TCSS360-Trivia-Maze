package model;

import javax.swing.*;
import java.util.ArrayList;

public class Question {
    // Class Fields


    // The question.
    private final String myQuestion;
    // The list of answers to the question.
    private final ArrayList<String> myAnswers;

    /**
     * The default constructor for Question object.
     * @param theQuestion the question prompt for making Question object.
     */
    public Question(String theQuestion) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<String>();

    }



    /**
     * Constructs answer for the question.
     * @param theAnswer the answer to include in the question.
     */
    public void addAnswers(String theAnswer) {
        myAnswers.add(theAnswer);
    }

    /**
     * Adding answer to the Answer object.
     * @param theAnswer
     */


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
        //should clone?
    }
}
