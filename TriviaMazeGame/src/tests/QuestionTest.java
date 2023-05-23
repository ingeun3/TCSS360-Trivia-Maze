package tests;

import model.Question;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.sqlite.SQLiteDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class QuestionTest {

    private SQLiteDataSource myDataSource;

    private ArrayList<Question> myQuestions;

   // @BeforeAll
//    void setUp() {
//        myQuestions = new ArrayList<Question>();
//        try {
//            myDataSource = new SQLiteDataSource();
//            myDataSource.setUrl("jdbc:sqlite:questions.db");
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//        retrieveData();
//    }

//    private void retrieveData() {
//        String query1 = "SELECT * FROM multiplechoicequestions";
//        String query2 = "SELECT * FROM booleanquestions";
//
//        try (Connection conn = myDataSource.getConnection();
//             Statement stmt = conn.createStatement(); ) {
//            ResultSet rs1 = stmt.executeQuery(query1);
//
//
//            //walk through each 'row' of results, grab data by column/field name
//            // and print it
//            while (rs1.next()) {
//                String question = rs1.getString("question");
//                String rightAnswer = rs1.getString("rightanswer");
//                String wrongAnswer1 = rs1.getString("wronganswer1");
//                String wrongAnswer2 = rs1.getString("wronganswer2");
//                addMultipleChoiceQuestion(question, rightAnswer, wrongAnswer1, wrongAnswer2);
//            }
//            ResultSet rs2 = stmt.executeQuery(query2);
//            while (rs2.next()) {
//                String question = rs2.getString("question");
//                String rightAnswer = rs2.getString("rightanswer");
//                String wrongAnswer = rs2.getString("wronganswer");
//                addBooleanQuestion(question, rightAnswer, wrongAnswer);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.exit(0);
//        }
//
//
//    }
//
//    private void addMultipleChoiceQuestion(String theQuestion, String theRightAnswer,
//                                           String theWrongAnswer1, String theWrongAnswer2) {
//        Question question = new Question(theQuestion);
//        question.formAnswer(theRightAnswer, true);
//        question.formAnswers(theWrongAnswer1, false);
//        question.formAnswers(theWrongAnswer2, false);
//        myQuestions.add(question);
//    }
//
//    private void addBooleanQuestion(String theQuestion, String theRightAnswer,
//                                    String theWrongAnswer) {
//        Question question = new Question(theQuestion);
//        question.formAnswers(theRightAnswer, true);
//        question.formAnswers(theWrongAnswer, false);
//        myQuestions.add(question);
//    }
//
//    @Test
//    void testQuestionPrompt1() {
//        String expectedQuestion = "How do you spell...?";
//        String actualQuestion = myQuestions.get(0).getQuestion();
//        assertEquals(expectedQuestion, actualQuestion);
//    }
//
//    @Test
//    void testQuestionAnswer1() {
//        String expectedAnswer = "Ingeun";
//        String actualAnswer = myQuestions.get(0).getAnswers().get(0).getAnswer();
//        assertEquals(expectedAnswer, actualAnswer);
//    }
//
//    @Test
//    void testQuestionAnswerValidity1() {
//        boolean expectedCorrectness = true;
//        boolean actualCorrectness = myQuestions.get(0).getAnswers().get(0).getCorrectness();
//        assertEquals(expectedCorrectness, actualCorrectness);
//    }
//
//    @Test
//    void testQuestionPrompt2() {
//        String expectedQuestion = "Khivgeun is smart.";
//        String actualQuestion = myQuestions.get(1).getQuestion();
//        assertEquals(expectedQuestion, actualQuestion);
//    }
//}
}