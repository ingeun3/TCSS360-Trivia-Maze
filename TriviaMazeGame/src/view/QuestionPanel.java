package view;

import model.Answer;
import model.Question;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class QuestionPanel {

    private ArrayList<Question> myQuestions;

    private JFrame myQuestionPanel;

    private ArrayList<JRadioButton> myButtons;

    private JPanel myButtonPanel;

    private Random myRandom;

    public QuestionPanel(ArrayList<Question> theQuestions) {
        myRandom = new Random();
        myQuestions = theQuestions;
        myQuestionPanel = new JFrame();
        myButtonPanel = new JPanel();
        myButtons = new ArrayList<JRadioButton>();
        setup();
        myQuestionPanel.setVisible(true);
        myQuestionPanel.setLocationRelativeTo(null);

        ImageIcon image = myQuestions.get(1).getImage();
        String question = myQuestions.get(1).getQuestion();
        JOptionPane.showMessageDialog(null, question,
                "test", JOptionPane.PLAIN_MESSAGE, image);
    }

    public JFrame getQuestion() {
        return myQuestionPanel;
    }
    private void setup() {
        Question question = getRandomQuestion();
        ButtonGroup buttonGroup = new ButtonGroup();
        JLabel label = new JLabel(question.getQuestion());
        JLabel image = new JLabel();
        image.setIcon(question.getImage());
        myQuestionPanel.add(image, BorderLayout.CENTER);
//        if (question.hasImage()) {
//            JLabel image = new JLabel(question.getImage());
//            myQuestionPanel.add(image, BorderLayout.CENTER);
//        }
        myButtonPanel.setLayout(new FlowLayout());
        myQuestionPanel.add(myButtonPanel, BorderLayout.SOUTH);
        myQuestionPanel.add(label, BorderLayout.NORTH);
        for (Answer ans : question.getAnswers()) {
            String answer = ans.getAnswer();
            JButton button = new JButton(answer);
            buttonGroup.add(button);
            myButtonPanel.add(button);
        }
        myQuestionPanel.pack();
        myQuestionPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private Question getRandomQuestion() {
        int rand = myRandom.nextInt(myQuestions.size());
        Question question = myQuestions.get(rand);
        return question;
    }


}
