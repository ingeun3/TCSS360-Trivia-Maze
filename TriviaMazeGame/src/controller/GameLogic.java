package controller;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import model.Maze;
import model.Player;
import model.Question;
import org.sqlite.SQLiteDataSource;
import view.*;

public class GameLogic implements KeyListener {
    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private Player myPlayer;
    private GUIPlayer mySprite;
    private Lighting myLighting;
    private ArrayList<Question> myQuestions;
    // The map that stores current question in key and answers as a value.
    private Map<String, String[]> myQnA;
    // The SQL data source.
    private SQLiteDataSource myDataSource;

    private QuestionPane myQuestionPane;

    private String[] myQ;

    private int myCurrentQuestion;

    private int mySize;

    private Point myPoint;

    private Point myEndPoint;

    private Point myStartPoint;

    private NorthPanel myNorthPanel;
    private Maze myMaze;

    private boolean myWin;

    private EscPanel myEscape;

    private boolean myGoToStage;

    private int myMove;

    private int myLevel;

    public GameLogic(String theMapName, int theMove, int theLevel) throws FileNotFoundException {
        myLevel = theLevel;
        myEscape = new EscPanel();
        myGoToStage = false;
        myWin = false;
        myMove = theMove;
        myQuestions = new ArrayList<>();
        myDataSource = new SQLiteDataSource();
        myQnA = new HashMap<String, String[]>();
        connect();
        retrieveData();
        mySize = myQuestions.size();
        myNorthPanel = NorthPanel.getInstance();
        myMaze = new Maze(theMapName);
        myPlayer = new Player(theMove, theMapName);
        myEndPoint = myMaze.getMyExit();
        myPoint = myPlayer.getLocation();
        myStartPoint = myPlayer.getLocation();
        mySprite = new GUIPlayer(myPlayer.getLocation(), myPlayer.getMazeLength());
        myLighting = Lighting.getInstance(mySprite, 350);
        myLighting.setSize(350);
        myLighting.setup();
        //gets a random question
        for (int i = 0; i < myQuestions.size(); i++) {
            Question askedQuestion = myQuestions.get(i);

            int ansLength = askedQuestion.getAnswers().size();
            String[] ansArray = new String[ansLength];
            for (int j = 0; j < ansLength; j++) {
                ansArray[j] = askedQuestion.getAnswers().get(j);
            }
            //puts it in map to send to question pane
            myQnA.put(askedQuestion.getQuestion(), ansArray);
        }

        myQ = new String[myQuestions.size()];

        int counter = 0;
        for (Map.Entry<String, String[]> entry : myQnA.entrySet()) {
            myQ[counter] = entry.getKey();
            counter++;
        }
        myCurrentQuestion = getRandomNumber(mySize);
        myQuestionPane = new QuestionPane(myQ[myCurrentQuestion], myQnA.get(myQ[myCurrentQuestion]).clone());
    }


    @Override
    public void keyTyped(KeyEvent e) {
    }

    private int pressedKeyCode = -1;

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W && pressedKeyCode != KeyEvent.VK_W && myPlayer.isAlive()) {
            pressedKeyCode = KeyEvent.VK_W;
            update();
        } else if (code == KeyEvent.VK_S && pressedKeyCode != KeyEvent.VK_S && myPlayer.isAlive()) {
            pressedKeyCode = KeyEvent.VK_S;
            update();
        } else if (code == KeyEvent.VK_A && pressedKeyCode != KeyEvent.VK_A && myPlayer.isAlive()) {
            pressedKeyCode = KeyEvent.VK_A;
            update();
        } else if (code == KeyEvent.VK_D && pressedKeyCode != KeyEvent.VK_D && myPlayer.isAlive()) {
            pressedKeyCode = KeyEvent.VK_D;
            update();
        } else if (code == KeyEvent.VK_ESCAPE) {
            pressedKeyCode = KeyEvent.VK_ESCAPE;
            myEscape.start();
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
            myNorthPanel.setMoves(myPlayer.getMyMove());
        } else if (pressedKeyCode == KeyEvent.VK_S && myPlayer.canMove(myPlayer.PlayerS())) {
            mySprite.setDirection("down");
            mySprite.setY(mySprite.getY() + mySprite.getSpeed());
            myNorthPanel.setMoves(myPlayer.getMyMove());
        } else if (pressedKeyCode == KeyEvent.VK_A && myPlayer.canMove(myPlayer.PlayerW())) {
            mySprite.setDirection("left");
            mySprite.setX(mySprite.getX() - mySprite.getSpeed());
            myNorthPanel.setMoves(myPlayer.getMyMove());
        } else if (pressedKeyCode == KeyEvent.VK_D && myPlayer.canMove(myPlayer.PlayerE())) {
            mySprite.setDirection("right");
            mySprite.setX(mySprite.getX() + mySprite.getSpeed());
            myNorthPanel.setMoves(myPlayer.getMyMove());
        }
        myLighting.setup();
        promptQuestions();
        checkFinish();
    }

    public void checkFinish() {
        if (myPlayer.getLocation().equals(myEndPoint) && myLevel < 4) {
            EndingMessage endingMessage = new EndingMessage(true);
            endingMessage.setOptionSelectedListener(new EndingMessage.OptionSelectedListener() {
                @Override
                public void onOptionSelected(int option) {
                    if (option == 1) {
                        // Play again
                        mySprite.setDirection("up");
                        mySprite.setX(myStartPoint.x * mySprite.getTileSize());
                        mySprite.setY(myStartPoint.y * mySprite.getTileSize());
                        myPlayer.movePlayer(myStartPoint);
                        myPlayer.setMyMove(myMove);
                        myNorthPanel.setMoves(myMove);
                        myPoint = myStartPoint;
                        myLighting.setSize(350);
                        myLighting.setup();
                    } else if (option == 3) {
                        myWin = true;
                    }
                }
            });
        } else if (!myPlayer.isAlive() || myPlayer.getLocation().equals(myEndPoint) && myLevel == 4) {
            EndingMessage endingMessage = new EndingMessage(false);
            endingMessage.setOptionSelectedListener(new EndingMessage.OptionSelectedListener() {
                @Override
                public void onOptionSelected(int option) {
                    if (option == 1) {
                        // Play again
                        mySprite.setDirection("up");
                        mySprite.setX(myStartPoint.x * mySprite.getTileSize());
                        mySprite.setY(myStartPoint.y * mySprite.getTileSize());
                        myPlayer.movePlayer(myStartPoint);
                        myPlayer.setMyMove(myMove);
                        myNorthPanel.setMoves(myMove);
                        myPoint = myStartPoint;
                        myLighting.setSize(350);
                        myLighting.setup();
                    } else if (option == 2) {
                        // Go to levels
                        myGoToStage = true;
                    }
                }
            });
        }
    }

    public boolean goToStage() {
        return myGoToStage;
    }
    public boolean didWin() {
        return myWin;
    }
    public void promptQuestions() {
        if(myPlayer.isQuestionPoint()) {
            myQuestionPane.ask();
            isRightAnswer(myQuestionPane.getChoice());

            myCurrentQuestion = getRandomNumber(mySize);
            myQuestionPane = new QuestionPane(myQ[myCurrentQuestion],
                    myQnA.get(myQ[myCurrentQuestion]).clone());
        }
    }

    private void isRightAnswer(String theChoice) {
        if (theChoice.equals(myQnA.get(myQ[myCurrentQuestion])[0])) {
            myPoint = myPlayer.getLocation();
            if(myLevel < 3) {
                myLighting.increaseSize(150);
            } else if(myLevel == 3) {
                myLighting.increaseSize(75);
            } else if(myLevel == 4) {
                myLighting.increaseSize(50);
            }

        } else {
            pressedKeyCode = -1;
            setLocation(myPoint);
        }
        pressedKeyCode = -1;
        myLighting.setup();
    }

    private void setLocation(Point thePoint){
        myPlayer.movePlayer(thePoint);
        mySprite.setX((thePoint.x * mySprite.getTileSize()));
        mySprite.setY(thePoint.y * mySprite.getTileSize());
    }

    /**
     * Connects to the SQL data source.
     */
    public void connect() {
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
    private void retrieveData() {
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
    private void addMultipleChoiceQuestion(String theQuestion, String theRightAnswer,
                                           String theWrongAnswer1, String theWrongAnswer2,
                                           String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.addAnswers(theRightAnswer);
        question.addAnswers(theWrongAnswer1);
        question.addAnswers(theWrongAnswer2);
        myQuestions.add(question);
    }

    /**
     * Adds true or false question to myQuestion.
     * @param theQuestion the Question prompt
     * @param theRightAnswer the Correct answer
     * @param theWrongAnswer the wrong answer
     * @param theImage the image of a question
     */
    private void addBooleanQuestion(String theQuestion, String theRightAnswer,
                                    String theWrongAnswer, String theImage) {

        Question question = initializeQuestion(theQuestion, theImage);
        question.addAnswers(theRightAnswer);
        question.addAnswers(theWrongAnswer);
        myQuestions.add(question);
    }

    /**
     * Initiating the Question.
     *
     * @param theQuestion the Question prompt
     * @param theImage the image of a question
     * @return the Question object initiated
     */
    private Question initializeQuestion(String theQuestion, String theImage) {
        Question question;
        if (theImage == null) {
            question = new Question(theQuestion);
        } else {
            question = new Question(theQuestion, theImage);
        }
        return question;
    }
    public static int getRandomNumber(int maxValue) {
        Random rand = new Random();
        int range = maxValue;
        int randomNum = rand.nextInt(range);
        return randomNum;
    }
}