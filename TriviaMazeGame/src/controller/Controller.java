package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Answer;
import model.Player;
import model.Question;
import org.sqlite.SQLiteDataSource;
import view.GUIPlayer;
import view.QuestionPane;

public class Controller implements KeyListener{
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Player myPlayer = new Player(10);
    private GUIPlayer mySprite = GUIPlayer.getInstance();
    private static Controller instance;
    private static ArrayList<Question> myQuestions;
    // The map that stores current question in key and answers as a value.
    private static Map<String, String[]> myQnA;
    // The SQL data source.
    private static SQLiteDataSource myDataSource;
    // Random object to grab random question.
    private static Random myRandom;

    private QuestionPane myQuestionPane;

    private Controller() throws FileNotFoundException {
        myRandom = new Random();
        myQuestions = new ArrayList<Question>();
        //QuestionPanel questionPanel = new QuestionPanel(myQuestions);
        myDataSource = new SQLiteDataSource();
        connect();
        retrieveData();

        Map<String, String[]> myQnA = new HashMap<String, String[]>();

        Collections.shuffle(myQuestions);

        //gets a random question
        Question askedQuestion = myQuestions.get(0);

        int ansLength = askedQuestion.getAnswers().size();
        String[] ansArray = new String[ansLength];
        for (int i = 0; i < ansLength; i++) {
            ansArray[i] = askedQuestion.getAnswers().get(i).getAnswer();
        }
        //puts it in map to send to questionpane
        myQnA.put(askedQuestion.getQuestion(), ansArray);

        myQuestionPane = new QuestionPane(myQnA);


        System.out.println(myQuestionPane.getChoice());

        Answer chosenAnswer = null;
        String chosenAnswerString = myQuestionPane.getChoice();
    }
    /**
     * Method to get the singleton instance of the keyBoardHandler class.
     * If the instance does not exist, it will be created.
     *
     * @return the singleton instance of the keyBoardHandler class.
     */
    public static Controller getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    private int pressedKeyCode = -1;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W && pressedKeyCode != KeyEvent.VK_W) {
            pressedKeyCode = KeyEvent.VK_W;
            update();
        } else if (code == KeyEvent.VK_S && pressedKeyCode != KeyEvent.VK_S) {
            pressedKeyCode = KeyEvent.VK_S;
            update();
        } else if (code == KeyEvent.VK_A && pressedKeyCode != KeyEvent.VK_A) {
            pressedKeyCode = KeyEvent.VK_A;
            update();
        } else if (code == KeyEvent.VK_D && pressedKeyCode != KeyEvent.VK_D) {
            pressedKeyCode = KeyEvent.VK_D;
            update();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == pressedKeyCode) {
            pressedKeyCode = -1;
        }
    }

    public void update() {
        if (pressedKeyCode == KeyEvent.VK_W && myPlayer.canMove(myPlayer.PlayerN())) {
            mySprite.setDirection("up");
            mySprite.setY(mySprite.getY() - mySprite.getSpeed());
            if(myPlayer.isQuestionPoint()) {
                myQuestionPane.ask();
            }
        } else if (pressedKeyCode == KeyEvent.VK_S && myPlayer.canMove(myPlayer.PlayerS())) {
            mySprite.setDirection("down");
            mySprite.setY(mySprite.getY() + mySprite.getSpeed());
            if(myPlayer.isQuestionPoint()) {
                myQuestionPane.ask();
            }
        } else if (pressedKeyCode == KeyEvent.VK_A && myPlayer.canMove(myPlayer.PlayerW())) {
            mySprite.setDirection("left");
            mySprite.setX(mySprite.getX() - mySprite.getSpeed());
            if(myPlayer.isQuestionPoint()) {
                myQuestionPane.ask();
            }
        } else if (pressedKeyCode == KeyEvent.VK_D && myPlayer.canMove(myPlayer.PlayerE())) {
            mySprite.setDirection("right");
            mySprite.setX(mySprite.getX() + mySprite.getSpeed());
            if(myPlayer.isQuestionPoint()) {
                myQuestionPane.ask();
            }
        }
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
}
