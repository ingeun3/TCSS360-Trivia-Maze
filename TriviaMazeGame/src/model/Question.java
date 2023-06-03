package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class Question {
    // Class Fields

    // Image for the question.
    private final ImageIcon myImage;
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
        myImage = null;

    }

    /**
     * The overloaded constructor for question object that takes image.
     * @param theQuestion the quetsion prompt for making Question object.
     * @param theImage the image for the question.
     */
    public Question(String theQuestion, String theImage) {
        myQuestion = theQuestion;
        myAnswers = new ArrayList<String>();
        myImage = new ImageIcon(theImage);
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
    public ArrayList<String> getAnswers() {
        return myAnswers;
        //should clone?
    }
}
