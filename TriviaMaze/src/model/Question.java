package model;

import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Question {

    private SQLiteDataSource myDataSource;

    private String myQuestion;

    private ArrayList<Answer> myAnswers;

    public Question() {
        myAnswers = new ArrayList<Answer>();
        myDataSource = null;
        myQuestion = "";

    }
    public void connect() {
        try {
            myDataSource = new SQLiteDataSource();
            myDataSource.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("Connect successful.");
    }

    public void retrieveData() {
        String query = "SELECT * FROM question";
        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {
            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String prompt = rs.getString( "prompt" );
                String rightAnswer = rs.getString( "rightanswer" );
                String wrongAnswer1 = rs.getString( "wronganswer1" );
                String wrongAnswer2 = rs.getString( "wronganswer2" );
                createMultipleChoiceQuestions(prompt, rightAnswer, wrongAnswer1, wrongAnswer2);
            }

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    private void createMultipleChoiceQuestions(String theQuestion, String theCorrect, String theIncorrect1,
                                String theIncorrect2) {
        myQuestion = theQuestion;
        Answer ans = new Answer(theCorrect, true);
        Answer ans2 = new Answer(theIncorrect1, false);
        Answer ans3 = new Answer(theIncorrect2, false);
        myAnswers.add(ans);
        myAnswers.add(ans2);
        myAnswers.add(ans3);
    }







}
