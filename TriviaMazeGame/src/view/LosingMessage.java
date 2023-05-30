package view;

import javax.swing.*;

public class LosingMessage {
    // Clas Constants

    // The default prompt for asking question.
    private static final String MESSAGE = "You are Terrible LOL!!!";
    private static final String TITLE = "You suck";
    //Class Fields

//    // The question that was asked.
//    private String myPrompt;
//    // The Image that will be display in the question.
//    private ImageIcon myImage;
//    // The list of answers to the problem.
    private String[] myOptions;
    private String myChosenAnswer;

    // The answer the player chose.

    public LosingMessage() {
        myOptions = new String[]{"Play Again", "Go to Stage"};
        myChosenAnswer = "";
    }
    public void start() {
        int ans = JOptionPane.showOptionDialog(null, MESSAGE, TITLE,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, myOptions, null);
        if (ans == -1) {
            myChosenAnswer = "Incorrect Answer";
        } else {
            myChosenAnswer =  myOptions[ans];
        }
    }
    public String getChoice() {
        return myChosenAnswer;
    }
}
