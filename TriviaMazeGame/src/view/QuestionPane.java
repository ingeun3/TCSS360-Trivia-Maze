package view;

import javax.swing.*;
import java.util.Arrays;
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
     * @param theQnA Map that contains the question as the key and array of answers in value.
     */
    public QuestionPane(Map<String, String[]> theQnA) {
        myAnswers = theQnA.get(myPrompt);
        myImage = new ImageIcon("questionmark.png");
        myChosenAnswer = "";
        start();
        //answers are just string[] without boolean value because view doesn't need to know if
        //answer is right or wrong because listener is in controller I think

    }

    /**
     * Initializing QuestionPane.
     */
    private void start() {
        // null will change to game panel when merging code
        int ans = JOptionPane.showOptionDialog(null, myPrompt, QUESTION_TITLE,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                myImage, myAnswers, myAnswers[0]);
        myChosenAnswer = myAnswers[ans];

    }

    /**
     * Getters for myChosenAnswer.
     * @return the Answer the player chose.
     */
    public String getChoice() {
        return myChosenAnswer;
    }

}
