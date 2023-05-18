package controller;

        import model.Answer;
        import model.Maze;
        import model.Question;
        import org.sqlite.SQLiteDataSource;
        import view.GUIPlayer;
        import view.GameInterface;
        import view.GamePanel;
        import view.QuestionPane;

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
        import java.util.Random;

public class Main {
    // ArrayList that stores all Question and answers.
    private static ArrayList<Question> myQuestions;
    // The map that stores current question in key and answers as a value.
    private static Map<String, String[]> myQnA;
    // The SQL data source.
    private static SQLiteDataSource myDataSource;
    // Random object to grab random question.
    private static Random myRandom;


    public static void main(String[] theArgs) throws FileNotFoundException {
        myRandom = new Random();
        myQuestions = new ArrayList<Question>();
        //QuestionPanel questionPanel = new QuestionPanel(myQuestions);
        myDataSource = new SQLiteDataSource();
        connect();
        retrieveData();

        Map<String, String[]> myQnA = new HashMap<String, String[]>();

        //gets a random question
        Question askedQuestion = getRandomQuestion();

        int ansLength = askedQuestion.getAnswers().size();
        String[] ansArray = new String[ansLength];
        for (int i = 0; i < ansLength; i++) {
            ansArray[i] = askedQuestion.getAnswers().get(i).getAnswer();
        }
        //puts it in map to send to questionpane
        myQnA.put(askedQuestion.getQuestion(), ansArray);

        QuestionPane question = new QuestionPane(myQnA);

        System.out.println(question.getChoice());

        Answer chosenAnswer = null;
        String chosenAnswerString = question.getChoice();



        Maze mazeMap = new Maze("maze_map2.txt");
        GameInterface gameInterface = new GameInterface(1, 10, mazeMap.getArray());
        GamePanel gamePanel = new GamePanel(mazeMap.getArray());
        GUIPlayer playerImage = new GUIPlayer();

        Controller controller = new Controller(mazeMap, gamePanel, gameInterface, playerImage);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                setLookAndFeel();

                controller.start();
            }

        });
    }

    /**
     * Returns one of question in a random order.
     * @return random question.
     */
    private static Question getRandomQuestion() {
        int rand = myRandom.nextInt(myQuestions.size());
        Question question = myQuestions.get(rand);
        return question;
    }

    /**
     * Connects to the SQL data source.
     */
    public static void connect() {
        try {
            myDataSource = new SQLiteDataSource();
            myDataSource.setUrl("jdbc:sqlite:questions.db");
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Receives data from data source.
     */
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

    /**
     * Adds multiple choice question to the myQuestion.
     * @param theQuestion the Question prompt
     * @param theRightAnswer the Correct answer
     * @param theWrongAnswer1 the wrong answer
     * @param theWrongAnswer2 the wrong answer
     * @param theImage the image of a question
     */
    private static void addMultipleChoiceQuestion(String theQuestion, String theRightAnswer,
                                                  String theWrongAnswer1, String theWrongAnswer2,
                                                  String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.formAnswers(theRightAnswer, true);
        question.formAnswers(theWrongAnswer1, false);
        question.formAnswers(theWrongAnswer2, false);
        myQuestions.add(question);
    }

    /**
     * Adds true or false question to myQuestion.
     * @param theQuestion the Question prompt
     * @param theRightAnswer the Correct answer
     * @param theWrongAnswer the wrong answer
     * @param theImage the image of a question
     */
    private static void addBooleanQuestion(String theQuestion, String theRightAnswer,
                                           String theWrongAnswer, String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.formAnswers(theRightAnswer, true);
        question.formAnswers(theWrongAnswer, false);
        myQuestions.add(question);
    }

    /**
     * Initiating the Question.
     *
     * @param theQuestion the Question prompt
     * @param theImage the image of a question
     * @return the Question object initiated
     */
    private static Question initializeQuestion(String theQuestion, String theImage) {
        Question question;
        if (theImage == null) {
            question = new Question(theQuestion);
        } else {
            question = new Question(theQuestion, theImage);
        }
        return question;
    }

    /**
     * Sets Look and Feel of the GUI.
     */
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
