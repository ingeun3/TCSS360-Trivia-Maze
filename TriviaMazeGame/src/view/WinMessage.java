package view;

import javax.swing.*;

public class WinMessage {
    // Clas Constants

    // The default prompt for asking question.
    private static final String MESSAGE = "Great Job!";

    //Class Fields

//    // The question that was asked.
//    private String myPrompt;
//    // The Image that will be display in the question.
//    private ImageIcon myImage;
//    // The list of answers to the problem.
//    private String[] myAnswers;

    // The answer the player chose.
    private String myChosenAnswer;
    public WinMessage() {
    }
    public void start() {
        int ans = JOptionPane.showOptionDialog(null, MESSAGE, MESSAGE,
                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, null);

    }
}
