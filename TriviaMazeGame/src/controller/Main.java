package controller;

import model.Answer;
import model.Maze;
import model.Question;
import org.sqlite.SQLiteDataSource;
import view.GameInterface;
import view.QuestionPane;


import javax.sql.DataSource;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static ArrayList<Question> myQuestions;

    private static Map<String, String[]> myQnA;

    private static SQLiteDataSource myDataSource;

    private Main() {
        // do not instantiate objects of this class
        throw new IllegalStateException();
    }

    public static void main(String[] theArgs) throws FileNotFoundException {
        myQuestions = new ArrayList<Question>();
        //QuestionPanel questionPanel = new QuestionPanel(myQuestions);
        myDataSource = new SQLiteDataSource();
        connect();
        retrieveData();

        //convert myQuestions arraylist into a map of questions and answers to send to
        //view
        myQnA = new HashMap<String, String[]>();
        for (Question question : myQuestions) {
            int ansLength = question.getAnswers().size();
            String[] ansArray = new String[ansLength];
            for (int i = 0; i < ansLength; i++) {
                ansArray[i] = question.getAnswers().get(i).getAnswer(); //gets string of answer
                                                                        //at index i for each question
            }
            myQnA.put(question.getQuestion(), ansArray);
        }
        QuestionPane question = new QuestionPane(myQnA);
        String[] qna = question.getQnA(); //gets the question that was asked and the answer that was
                                            //chosen at index 0 and 1 respectively
        System.out.println(qna[0] + " " + qna[1]);

        //line 55 and 56 will pop up the question and then get the choice the chosen.


        //ACTUALLY MAYBE NEED TO CHANGE OF SENDING A RANDOM QUESTION HERE IN CONTROLLER TO SEND
        //TO QUESTIONPANE


        //logic that will see if answer is correct
        Question askedQuest = null;
        for (Question quest : myQuestions) {
            if (quest.getQuestion() == qna[0]) {
                askedQuest = quest;
            }
        }
        for (Answer ans: askedQuest.getAnswers()) {
            if (ans.getAnswer() == qna[1]) {
                //matching the answer with the answer object to check correctness
                //not finished
            }
        }



        Maze mazeMap = null;
        try {
            mazeMap = new Maze("maze_map2.txt");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Maze finalMazeMap = mazeMap;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();
                new GameInterface(1, 10, finalMazeMap.getArray()).start();
            }

        });
    }

    public static void connect() {
        try {
            myDataSource = new SQLiteDataSource();
            myDataSource.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    private static void retrieveData() {
        String query1 = "SELECT * FROM multiplechoicequestions";
        String query2 = "SELECT * FROM booleanquestions";

        try (Connection conn = myDataSource.getConnection();
             Statement stmt = conn.createStatement(); ) {
            ResultSet rs1 = stmt.executeQuery(query1);


            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while (rs1.next()) {
                String question = rs1.getString("question");
                String rightAnswer = rs1.getString("rightanswer");
                String wrongAnswer1 = rs1.getString("wronganswer1");
                String wrongAnswer2 = rs1.getString("wronganswer2");
                String image = rs1.getString("image");
                addMultipleChoiceQuestion(question, rightAnswer, wrongAnswer1, wrongAnswer2, image);
            }
            ResultSet rs2 = stmt.executeQuery(query2);
            while (rs2.next()) {
                String question = rs2.getString("question");
                String rightAnswer = rs2.getString("rightanswer");
                String wrongAnswer = rs2.getString("wronganswer");
                String image = rs2.getString("image");
                addBooleanQuestion(question, rightAnswer, wrongAnswer, image);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }


    }

    private static void addMultipleChoiceQuestion(String theQuestion, String theRightAnswer,
                                                  String theWrongAnswer1, String theWrongAnswer2,
                                                  String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.formAnswers(theRightAnswer, true);
        question.formAnswers(theWrongAnswer1, false);
        question.formAnswers(theWrongAnswer2, false);
        myQuestions.add(question);
    }

    private static void addBooleanQuestion(String theQuestion, String theRightAnswer,
                                           String theWrongAnswer, String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.formAnswers(theRightAnswer, true);
        question.formAnswers(theWrongAnswer, false);
        myQuestions.add(question);
    }

    private static Question initializeQuestion(String theQuestion, String theImage) {
        Question question;
        if (theImage == null) {
            question = new Question(theQuestion);
        } else {
            question = new Question(theQuestion, theImage);
        }
        return question;
    }
    private static void setLookAndFeel() {

        try {

            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

        } catch (final UnsupportedLookAndFeelException e) {
            System.out.println("UnsupportedLookAndFeelException");
        } catch (final ClassNotFoundException e) {
            System.out.println("ClassNotFoundException");
        } catch (final InstantiationException e) {
            System.out.println("InstantiationException");
        } catch (final IllegalAccessException e) {
            System.out.println("IllegalAccessException");
        }

    }

}
