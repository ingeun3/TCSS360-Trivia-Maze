package model;

import javax.swing.*;
import java.util.ArrayList;

public class Question {
    // Class Fields

    // Image for the question.
    private final ImageIcon myImage;
    // The question.
    private final String myQuestion;
    // The list of answers to the question.
    private final ArrayList<Answer> myAnswers;

    /**
     * The default constructor for Question object.
     * @param theQuestion the question prompt for making Question object.
     */
    public Question(String theQuestion) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<Answer>();
        myImage = null;
    }

    /**
     * The overloaded constructor for question object that takes image.
     * @param theQuestion the quetsion prompt for making Question object.
     * @param theImage the image for the question.
     */
    public Question(String theQuestion, String theImage) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<Answer>();
        myImage = new ImageIcon(theImage);
    }

    /**
     * Constructs answer for the question.
     * @param theAnswer the answer to include in the question.
     * @param theCorrectness the correctness of this answer.
     */
    public void formAnswers(String theAnswer, boolean theCorrectness) {
        Answer answer = new Answer(theAnswer, theCorrectness);
        addAnswer(answer);
    }

    /**
     * Adding answer to the Answer object.
     * @param theAnswer
     */
    private void addAnswer(Answer theAnswer) {
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
     * Returns the Image included in the question.
     * @return the Image included in the question.
     */
    public ImageIcon getImage() {
        return myImage;
    }

    /**
     * True if the question has image, false otherwise.
     * @return true if the question has image, false otherwise.
     */
    public boolean hasImage() {
        boolean flag = true;
        if (myImage == null) {
            flag = false;
        }
        return flag;
    }

    /**
     * Returns the ArrayList of answers this question has.
     * @return the lists of answers this question has.
     */
    public ArrayList<Answer> getAnswers() {
        return myAnswers;
        //should clone?
    }
}
