package view;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

public class QuestionDialog {
    // Clas Constants

    // The default prompt for asking question.
    private static final String QUESTION_TITLE = "Answer the question";

    //Class Fields

    // The question that was asked.
    private String myPrompt;

    private JLabel myPromptLabel;
    // The Image that will be display in the question.
    private ImageIcon myImage;
    // The list of answers to the problem.
    private String[] myAnswers;

    private JLabel myAnswersLabel;

    // The answer the player chose.
    private String myChosenAnswer;




    /**
     * Default constructor for QuestionPane.
     *
     */
    public QuestionDialog(String theQuestion, String[] theAnswers) {

        myPrompt = theQuestion;
        myAnswers = theAnswers;
        Collections.shuffle(Arrays.asList(theAnswers));
        myImage = new ImageIcon("down.png");
        myChosenAnswer = "";

        myPromptLabel = new JLabel();
        myPromptLabel.setText(myPrompt);

    }



    /**
     * Launching the question window to prompt the player with a question and store the answer
     * they chose.
     */
    public void ask() {

        myPromptLabel = new JLabel();
        myPromptLabel.setText(myPrompt);
        myPromptLabel.setFont(new Font(
                "Arial", Font.BOLD, 40));

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();

        buttonPanel.setLayout(new GridLayout(2,2));

        for (String answer : myAnswers) {
            JButton button = new JButton(answer);
            button.setFont(new Font(
                    "Arial", Font.BOLD, 20));
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
