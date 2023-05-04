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

    private Answer[] myAnswers;

    public Question() {
        myAnswers = null;
        myDataSource = null;

    }
    public void connect() {
        try {
            myDataSource = new SQLiteDataSource();
            myDataSource.setUrl("jdbc:sqlite:database.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    public void retrieveData() {
        String query = "SELECT * FROM questions";
        try ( Connection conn = myDataSource.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                String question = rs.getString( "question" );
                String correctAnswer = rs.getString( "correctanswer" );
                String incorrectAnswer1 = rs.getString( "incorrectanswer1" );
                String incorrectAnswer2 = rs.getString( "incorrectanswer2" );

                System.out.println( "Result: Question = " + question +
                        ", Answer = " + answer );
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }

    public void createQuestions(String theQuestion, String theCorrect, String theIncorrect1,
                                String theIncorrect2) {

    }


}
