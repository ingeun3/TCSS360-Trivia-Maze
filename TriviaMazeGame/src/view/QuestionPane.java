package view;

import model.Answer;
import model.Question;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class QuestionPane {

    private static final String QUESTION_TITLE = "Answer the question";
    private String myPrompt;

    private ImageIcon myImage;

    private ArrayList<Question> myQuestions;

    private String[] myAnswers;

    private Random myRandom;
    public QuestionPane(ArrayList<Question> theQuestions) {
        myRandom = new Random();
        myQuestions = theQuestions;
        Question question = getRandomQuestion();
        myAnswers = new String[question.getAnswers().size()];
        initialize(question);
        myPrompt = question.getQuestion();
        myImage = setImage(question);
        start();
    }

    /*
    For construction that takes more than one line.
     */
    private void initialize(Question theQuestion) {
       for (int i = 0; i < myAnswers.length; i++) {
           myAnswers[i] = theQuestion.getAnswers().get(i).getAnswer();
       }
    }

    private ImageIcon setImage(Question theQuestion) {
        ImageIcon icon;
        if (theQuestion.hasImage()) {
            icon = theQuestion.getImage();
        } else {
            icon = new ImageIcon("questionmark.png");
        }
        return icon;
    }

    private void start() {
        // null will change to game panel when merging code
        JOptionPane.showOptionDialog(null, myPrompt, QUESTION_TITLE,
                                     JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                     myImage, myAnswers, myAnswers[0]);
    }


    private Question getRandomQuestion() {
        int rand = myRandom.nextInt(myQuestions.size());
        Question question = myQuestions.get(rand);
        return question;
    }

}
