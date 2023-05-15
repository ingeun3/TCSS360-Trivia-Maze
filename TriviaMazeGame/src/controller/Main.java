package controller;

import model.Question;
import org.sqlite.SQLiteDataSource;

import javax.sql.DataSource;
import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Main {

    private static ArrayList<Question> myQuestions;

    private static SQLiteDataSource myDataSource;
    public static void main(String[] theArgs) {
        myQuestions = new ArrayList<Question>();
        myDataSource = new SQLiteDataSource();
        connect();
        retrieveData();
        ImageIcon image = myQuestions.get(1).getImage();
        String question = myQuestions.get(1).getQuestion();
        JOptionPane.showMessageDialog(null, question,
                "test", JOptionPane.PLAIN_MESSAGE, image);
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
}
