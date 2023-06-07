package controller;
/**
 * This class is the Game Logic class that controls questions and key listener.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */

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

/**
 * This Game Logic class listen the Key Listener and Player movement.
 */
public class GameLogic implements KeyListener {
    //static string for title.
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    // The value that keeps track the Key Listener.
    private int pressedKeyCode = -1;

    // Initialized the Player.
    private Player myPlayer;

    // Initialized the GUIPlayer.
    private GUIPlayer mySprite;

    // Initialized the Lighting.
    private Lighting myLighting;

    // Initialized the ArrayList for Questions.
    private ArrayList<Question> myQuestions;

    // Initialized the map that stores current question in key and answers as a value.
    private Map<String, String[]> myQnA;

    // The SQL data source.
    private SQLiteDataSource myDataSource;

    // Initialized the Question Dialog.
    private QuestionDialog myQuestionPane;

    // Initialized the questions Array.
    private String[] myQ;

    // Initialized the current question.

    private int myCurrentQuestion;

    // Initialized the Question Array Size .
    private int mySize;

    // Initialized the myPoint for Player Location.
    private Point myPoint;

    //Initialized the myEndPoint for Exist Point that has on the map.
    private Point myEndPoint;

    //Initialized the myStartPoint for Player Start Point that has on the map.

    private Point myStartPoint;

    //Initialized the NorthPanel.

    private NorthPanel myNorthPanel;

    //Initialized the Maze.
    private Maze myMaze;

    // Initialized the boolean flag for winning.
    private boolean myWin;

    //Initialized the EscapeFrame

    private EscFrame myEscape;

    //Initialized the boolean flag for level panel.

    private boolean myGoToStage;

    // Initialized the initial move for Player.

    private int myMove;

    // Initialized the current level.
    private int myLevel;

    // Initialized the key count for cheat code.
    private int myCheatKeyCount;

    // Initialized the cheat code that send player to exist.
    private int myFinishCheatCode;

    /**
     *
     * @param theMaze The maze class that makes the map.
     * @param theMove The  initial move for Player.
     * @param theLevel The current Level.
     * @param thePlayer The Player class.
     * @throws FileNotFoundException
     */

    public GameLogic(Maze theMaze, int theMove, int theLevel, Player thePlayer) throws FileNotFoundException {
        myLevel = theLevel;
        myEscape = new EscFrame();
        // initialized the level interface flag to false.
        myGoToStage = false;
        //// initialized the winning state flag to false.
        myWin = false;
        myMove = theMove;
        myQuestions = new ArrayList<>();
        myDataSource = new SQLiteDataSource();
        myQnA = new HashMap<String, String[]>();
        mySize = myQuestions.size();
        myNorthPanel = NorthPanel.getInstance();
        myMaze = theMaze;
        myPlayer = thePlayer;
        myEndPoint = myMaze.getMyExit();
        myPoint = myPlayer.getLocation();
        myStartPoint = myPlayer.getLocation();
        // Initialized the GUIPlayer sprite.
        mySprite = new GUIPlayer(myPlayer.getLocation(), myPlayer.getMazeLength());
        myLighting = Lighting.getInstance(mySprite, 350);
        //set the initial size for circle is 350.
        myLighting.setSize(350);
        // set up the lighting.
        myLighting.setup();
        // connect the sql data source.
        connect();
        // retrieve the Data.
        retrieveData();

        //gets a random question for question point.
        for (int i = 0; i < myQuestions.size(); i++) {
            Question askedQuestion = myQuestions.get(i);
            // initialized the answer length.
            int ansLength = askedQuestion.getAnswers().size();
            String[] ansArray = new String[ansLength];
            for (int j = 0; j < ansLength; j++) {
                ansArray[j] = askedQuestion.getAnswers().get(j);
            }
            //puts it in map to send to question pane
            myQnA.put(askedQuestion.getQuestion(), ansArray);
        }
        // initialized myQ String Array with Questions size.
        myQ = new String[myQuestions.size()];
        int counter = 0;
        // loop through the myQnA map.
        for (Map.Entry<String, String[]> entry : myQnA.entrySet()) {
            myQ[counter] = entry.getKey();
            // increase the counter.
            counter++;
        }
        // get a random number for question.
        myCurrentQuestion = getRandomNumber(mySize);
        // Clone the Question to avoid encapsulation and add the question in the question pane.
        myQuestionPane = new QuestionDialog(myQ[myCurrentQuestion], myQnA.get(myQ[myCurrentQuestion]).clone());
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * This class update when the Player pressed the Key.
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // get a key code that player pressed.
        int code = e.getKeyCode();
        /*
         * These "if" statements check the following three conditions :
         * Player pressed the key (W, A, S, D) or not.
         * Also, check the key is previously pressed or not.
         * Also, player is still alive or not.
         * If these three conditions passed then we update the key.
         */
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
        } else if (code == KeyEvent.VK_ESCAPE) { // if player pressed the "esc" key code
            pressedKeyCode = KeyEvent.VK_ESCAPE;
            myEscape.start(); // shows the escape panel on the frame
        } else if (code == KeyEvent.VK_M) { // For the cheat code purpose.
            pressedKeyCode = KeyEvent.VK_M;
            myCheatKeyCount++;
            if(myCheatKeyCount >= 5) { // If player pressed "m" five times or more,
                cheat(); // call the cheat code method.
                myCheatKeyCount = 0;  // set the cheat code to zero.
            }
        } else if (code == KeyEvent.VK_N) { // This is for presentation purpose.
        pressedKeyCode = KeyEvent.VK_N; // If Player presses "n" five times, we will set player move to "10"
            myFinishCheatCode++;
            if(myFinishCheatCode >= 5) {
                myPlayer.setMyMove(10);
                myNorthPanel.setMoves(myPlayer.getMyMove());
            }

         }
    }

    /**
     * This method set the pressed key code to be -1 again.
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == pressedKeyCode) {
            pressedKeyCode = -1;
        }
    }

    /**
     * This method set where the player to move.
     */
    public void update() {
        /*
         * These if statements check current key code is equal to "W, S, A , D" key code or not.
         * Also, check player can move in this direction or not.
         * If it passes these two conditions, player can go to their desire direction : Up, Down, Left, Right.
         * Also update the player remaining move.
         */
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
        // set the lighting.
        myLighting.setup();
        // if player is at the same intersection point and question points then call
        // promptQuestions() method.
        promptQuestions();
        //call checkFinish() method to see which button player presses.
        checkFinish();
    }

    /**
     * This check Finish method checks which JButton that Player clicks, and also
     * which JPanel needs to show based on the player choices.
     */
    public void checkFinish() {
        // check if player current location is equal to "player endpoint" that locates on map or not,
        // and check player finish all the levels or not.
        if (myPlayer.getLocation().equals(myEndPoint) && myLevel >= 4) {
            // if these above conditions met, pass the one to call the "winning frame" that show
            // congratulation message .
            EndingMessage endingMessage = new EndingMessage(1);

            // set the option listener that listen for winning frame.
            endingMessage.setOptionSelectedListener(new EndingMessage.OptionSelectedListener() {
                @Override
                public void onOptionSelected(int option) {
                    // if player selects one which is play again then
                    if (option == 1) {
                        //set the player points to the start point.
                        mySprite.setDirection("up");
                        mySprite.setX(myStartPoint.x * mySprite.getTileSize() + mySprite.getGap());
                        mySprite.setY(myStartPoint.y * mySprite.getTileSize());
                        myPlayer.movePlayer(myStartPoint);
                        myPlayer.setMyMove(myMove);
                        myNorthPanel.setMoves(myMove);
                        myPoint = myStartPoint;
                        // set the lighting size and set up the lighting.
                        myLighting.setSize(350);
                        myLighting.setup();
                        // if player selects two which leads to go back level interface,
                    } else if (option == 2) {
                        // myGoToStage becomes true and shows level interface.
                        myGoToStage = true;
                    }
                }
            });
        }
        // check if player current location is equal to "player endpoint" that locates on map or not,
        // and checks player reaches the final level or not.
        else if (myPlayer.getLocation().equals(myEndPoint) && myLevel < 4) {
            // if these condition met, call the "winning frame".
            EndingMessage endingMessage = new EndingMessage(2);

            // set the option listener that listen for winning frame.
            endingMessage.setOptionSelectedListener(new EndingMessage.OptionSelectedListener() {

                // if player selects one which is play again then
                @Override
                public void onOptionSelected(int option) {
                    if (option == 1) {
                        // set the player location to the start point.
                        mySprite.setDirection("up");
                        mySprite.setX(myStartPoint.x * mySprite.getTileSize());
                        mySprite.setY(myStartPoint.y * mySprite.getTileSize());
                        myPlayer.movePlayer(myStartPoint);
                        myPlayer.setMyMove(myMove);
                        myNorthPanel.setMoves(myMove);
                        myPoint = myStartPoint;
                        // set the lighting size and set up the lighting.
                        myLighting.setSize(350);
                        myLighting.setup();
                    } else if (option == 3) {
                        myWin = true;
                    }
                }
            });
            // if Player is not alive then show the "Losing Message Frame".
        } else if (!myPlayer.isAlive() || myPlayer.getLocation().equals(myEndPoint) && myLevel == 4) {
            EndingMessage endingMessage = new EndingMessage(3);
            endingMessage.setOptionSelectedListener(new EndingMessage.OptionSelectedListener() {
                @Override
                public void onOptionSelected(int option) {
                    // if player selects one which is play again then
                    if (option == 1) {
                        // set the player location to the start point.
                        mySprite.setDirection("up");
                        mySprite.setX(myStartPoint.x * mySprite.getTileSize());
                        mySprite.setY(myStartPoint.y * mySprite.getTileSize());
                        myPlayer.movePlayer(myStartPoint);
                        myPlayer.setMyMove(myMove);
                        myNorthPanel.setMoves(myMove);
                        myPoint = myStartPoint;
                        myLighting.setSize(350);
                        myLighting.setup();
                        // If player selects "Go To Levels" then set the Level interface to false.
                    } else if (option == 2) {
                        // Go to levels
                        myGoToStage = true;
                    }
                }
            });
        }
    }

    /**
     *
     * @return this method returns the Level Interface Flag.
     */
    public boolean goToStage() {
        return myGoToStage;
    }

    /**
     * @return This method returns the Winning status.
     */
    public boolean didWin() {
        return myWin;
    }

    /**
     * This method shows the question message on the Game Panel.
     */
    public void promptQuestions() {
        // check player current point is equal to random question point or not.
        if(myPlayer.isQuestionPoint()) {
            // if it's ask the question.
            myQuestionPane.ask();
            // check player chooses right answer or not.
            isRightAnswer(myQuestionPane.getChoice());
            // Prepare for next question.
            myCurrentQuestion = getRandomNumber(mySize);
            myQuestionPane = new QuestionDialog(myQ[myCurrentQuestion],
                    myQnA.get(myQ[myCurrentQuestion]).clone());
        }
    }

    /**
     *
     * @param theChoice The answer that Player choose.
     */
    private void isRightAnswer(String theChoice) {
        //check player chooses the right answer or not.
        if (theChoice.equals(myQnA.get(myQ[myCurrentQuestion])[0])) {
            myPoint = myPlayer.getLocation();
            // if player choose right answer, and player current level is less than 3 then
            if(myLevel < 3) {
                // increase the lighting size.
                myLighting.increaseSize(80);
            } else if(myLevel == 3) {
                // increase the lighting size.
                myLighting.increaseSize(60);
            } else if(myLevel == 4) {
                // increase the lighting size.
                myLighting.increaseSize(30);
            }

        } else {
            // if player gets the answer wrong, set the key code flag to -1 again.
            pressedKeyCode = -1;
            // send the Player to less Question point that player gets right answer.
            setLocation(myPoint);
        }
        // set the key code flag to -1 again.
        pressedKeyCode = -1;
        myLighting.setup();
    }

    /**
     * This method Teleport back to the Player Location that
     * player gets it right last time.
     * @param thePoint
     */
    private void setLocation(Point thePoint){
        myPlayer.movePlayer(thePoint);
        mySprite.setX((thePoint.x * mySprite.getTileSize() + mySprite.getGap()));
        mySprite.setY(thePoint.y * mySprite.getTileSize());
    }

    /**
     * This method is for cheating purpose.
     */
    private void cheat() {
        if(((int) myEndPoint.getX() - 1 > 0) && (myMaze.charAt((int) myEndPoint.getX() - 1, (int) myEndPoint.getY()) == '+')) {
            myPlayer.canMove(new Point((int) myEndPoint.getX() - 1, (int) myEndPoint.getY()));
            mySprite.setDirection("right");
            mySprite.setY((int) myEndPoint.getY() * mySprite.getSpeed());
            mySprite.setX((int) myEndPoint.getX() * mySprite.getSpeed() + mySprite.getGap() - mySprite.getSpeed());
            myLighting.disableLight();
        } else if(((int) myEndPoint.getX() + 1 < myMaze.getArray()[0].length) && (myMaze.charAt((int) myEndPoint.getX() + 1, (int) myEndPoint.getY()) == '+')) {
            myPlayer.canMove(new Point((int) myEndPoint.getX() + 1, (int) myEndPoint.getY()));
            mySprite.setDirection("left");
            mySprite.setY((int) myEndPoint.getY() * mySprite.getSpeed());
            mySprite.setX((int) myEndPoint.getX() * mySprite.getSpeed() + mySprite.getGap() + mySprite.getSpeed());
            myLighting.disableLight();
        } else if(((int) myEndPoint.getY() - 1 > 0) && (myMaze.charAt((int) myEndPoint.getX(), (int) myEndPoint.getY() - 1) == '+')) {
            myPlayer.canMove(new Point((int) myEndPoint.getX(), (int) myEndPoint.getY()-1));
            mySprite.setDirection("down");
            mySprite.setY((int) myEndPoint.getY() * mySprite.getSpeed() - mySprite.getSpeed());
            mySprite.setX((int) myEndPoint.getX() * mySprite.getSpeed() + mySprite.getGap());
            myLighting.disableLight();
        } else if(((int) myEndPoint.getY() + 1 < myMaze.getArray().length) && (myMaze.charAt((int) myEndPoint.getX(), (int) myEndPoint.getY() + 1) == '+')) {
            myPlayer.canMove(new Point((int) myEndPoint.getX(), (int) myEndPoint.getY() + 1));
            mySprite.setDirection("up");
            mySprite.setY((int) myEndPoint.getY() * mySprite.getSpeed() + mySprite.getSpeed());
            mySprite.setX((int) myEndPoint.getX() * mySprite.getSpeed() + mySprite.getGap());
            myLighting.disableLight();
        }
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
        String query1 = "SELECT * FROM funquestions";
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
                String wrongAnswer3 = rs1.getString("wronganswer3");
                addMultipleChoiceQuestion(question, rightAnswer, wrongAnswer1, wrongAnswer2, wrongAnswer3);
            }
            ResultSet rs2 = stmt.executeQuery(query2);
//            while (rs2.next()) {
//                String question = rs2.getString("question");
//                String rightAnswer = rs2.getString("rightanswer");
//                String wrongAnswer = rs2.getString("wronganswer");
//                addBooleanQuestion(question, rightAnswer, wrongAnswer);
//            }
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
     */
    private void addMultipleChoiceQuestion(String theQuestion, String theRightAnswer,
                                           String theWrongAnswer1, String theWrongAnswer2, String theWrongAnswer3) {

        Question question = initializeQuestion(theQuestion);
        question.addAnswers(theRightAnswer);
        question.addAnswers(theWrongAnswer1);
        question.addAnswers(theWrongAnswer2);
        question.addAnswers(theWrongAnswer3);
        myQuestions.add(question);
    }

    /**
     * Adds true or false question to myQuestion.
     * @param theQuestion the Question prompt
     * @param theRightAnswer the Correct answer
     * @param theWrongAnswer the wrong answer
     */
    private void addBooleanQuestion(String theQuestion, String theRightAnswer,
                                    String theWrongAnswer) {

        Question question = initializeQuestion(theQuestion);
        question.addAnswers(theRightAnswer);
        question.addAnswers(theWrongAnswer);
        myQuestions.add(question);
    }

    /**
     * Initiating the Question.
     *
     * @param theQuestion the Question prompt
     * @return the Question object initiated
     */
    private Question initializeQuestion(String theQuestion) {
        Question question = new Question(theQuestion);

        return question;
    }

    /**
     *
     * @param maxValue This Question size.
     * @return The random number.
     */
    public static int getRandomNumber(int maxValue) {
        Random rand = new Random();
        int range = maxValue;
        int randomNum = rand.nextInt(range);
        return randomNum;
    }
}