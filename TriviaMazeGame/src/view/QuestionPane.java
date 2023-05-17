package view;

import javax.swing.*;
import java.util.Map;
import java.util.Random;

public class QuestionPane {

    private static final String QUESTION_TITLE = "Answer the question";
    private String myPrompt;

    private ImageIcon myImage;

//    private ArrayList<Question> myQuestions;

    private Map<String, String[]> myQnA;

    private String[] myAnswers;

    private Random myRandom;

    private String myChosenAnswer;

    //new mvc constructor
    //key is question, value is array of answers
    //construction of 1 instance of a random question and possible answers everytime
    //joptionpane opens
    public QuestionPane(Map<String, String[]> theQnA) {
        myRandom = new Random();
        myQnA = theQnA;

        myAnswers = theQnA.get(myPrompt);
        myImage = new ImageIcon("questionmark.png");
        myChosenAnswer = "";
        start();
        //answers are just string[] without boolean value because view doesn't need to know if
        //answer is right or wrong because listener is in controller I think

    }

    //change getting image later
//    private ImageIcon setImage(Question theQuestion) {
//        ImageIcon icon;
//        if (theQuestion.hasImage()) {
//            icon = theQuestion.getImage();
//        } else {
//            icon = new ImageIcon("questionmark.png");
//        }
//        return icon;
//    }

    private void start() {
        // null will change to game panel when merging code
        int ans = JOptionPane.showOptionDialog(null, myPrompt, QUESTION_TITLE,
                                     JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                     myImage, myAnswers, myAnswers[0]);
        myChosenAnswer = myAnswers[ans];

    }


    public String getChoice() {
        return myChosenAnswer;
    }
    private String getRandomQuestion() {
        int rand = myRandom.nextInt(myQnA.size());
        Object question = myQnA.keySet().toArray()[rand];
        return question.toString();
    }

}
