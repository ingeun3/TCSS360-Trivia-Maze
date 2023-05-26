package view;

import javax.swing.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public class QuestionPane {
    // Clas Constants

    // The default prompt for asking question.
    private static final String QUESTION_TITLE = "Answer the question";

    //Class Fields

    // The question that was asked.
    private String myPrompt;
    // The Image that will be display in the question.
    private ImageIcon myImage;
    // The list of answers to the problem.
    private String[] myAnswers;

    // The answer the player chose.
    private String myChosenAnswer;

    /**
     * Default constructor for QuestionPane.
     *
     */
    public QuestionPane(String theQuestion, String[] theAnswers) {
        myPrompt = theQuestion;
        myAnswers = theAnswers;
        Collections.shuffle(Arrays.asList(theAnswers));
        myImage = new ImageIcon("questionmark.png");
        myChosenAnswer = "";
    }

    /**
     * Launching the question window to prompt the player with a question and store the answer
     * they chose.
     */
    public void ask() {
        // null will change to game panel when merging code
        int ans = JOptionPane.showOptionDialog(null, myPrompt, QUESTION_TITLE,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                myImage, myAnswers, myAnswers[0]);
        if (ans == -1) {
            myChosenAnswer = "Incorrect Answer";
        } else {
            myChosenAnswer = myAnswers[ans];
        }

    }

    /**
     * Getters for myChosenAnswer.
     * @return the Answer the player chose.
     */
    public String getChoice() {
        return myChosenAnswer;
    }

}
