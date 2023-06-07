package view;

/**
 *
 * This class is the making the Instruction Frame.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

// This is the QuestionDialog class that creates Question Dialog.

public class QuestionDialog {
    /** The default prompt for asking question. */
    private static final String QUESTION_TITLE = "Answer the question";

    /**The question that was asked. */
    private final String myPrompt;

    /** Initialized the Prompt Label. */
    private JLabel myPromptLabel;

    /** The list of answers to the problem. */
    private final String[] myAnswers;

    /** The answer the player chose. */
    private String myChosenAnswer;

    /**
     *
     * @param theQuestion Receive the Current Questions.
     * @param theAnswers Receive the Answers Array.
     */
    public QuestionDialog(String theQuestion, String[] theAnswers) {
        myPrompt = theQuestion;
        myAnswers = theAnswers;
        // we shuffle the answer.
        Collections.shuffle(Arrays.asList(theAnswers));
        myChosenAnswer = "";
        myPromptLabel = new JLabel();
        myPromptLabel.setText(myPrompt);
    }

    /**
     * Launching the question window to prompt the player with a question and store the answer
     * they chose.
     */
    public void ask() {
        // set the Question Dialog.
        myPromptLabel = new JLabel();
        myPromptLabel.setText(myPrompt);
        myPromptLabel.setFont(new Font(
                "Arial", Font.BOLD, 40));

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(2,2));

        // loop through the Array Answer List.
        for (String answer : myAnswers) {
            JButton button = new JButton(answer);
            button.setFont(new Font(
                    "Arial", Font.BOLD, 20));
            // add the action listener for buttons.
            button.addActionListener(e -> {
                myChosenAnswer = answer;
                buttonPanel.getTopLevelAncestor().setVisible(false); // Hide the dialog
            });
          //  button.setPreferredSize(new Dimension(100,150));
            button.setBackground(Color.WHITE);
            buttonPanel.add(button);

        }

        buttonPanel.setPreferredSize(new Dimension(1300,500));

        // Create a panel to hold the prompt label and button panel
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(myPromptLabel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        // Create a custom dialog
        JDialog dialog = new JDialog();
        dialog.setIconImage(new ImageIcon("down.png").getImage());

        dialog.setTitle(QUESTION_TITLE);
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setContentPane(contentPanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);

        // Show the dialog
        dialog.setVisible(true);
    }

    /**
     * Getters for myChosenAnswer.
     * @return the Answer the player chose.
     */
    public String getChoice() {
        return myChosenAnswer;
    }

}
