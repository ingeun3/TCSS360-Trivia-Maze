package controller;

import model.Maze;
import model.Player;
import view.*;

import java.awt.*;
import java.io.FileNotFoundException;

public class GameLoop {
    private GameInterface myGameInterface;


    private static final String MAP1 = "maze_map3.txt";
    private static final String MAP2 = "maze_map4.txt";
    private static final String MAP3 = "maze_map5.txt";

    private Player myPlayer;
    private Player myPlayer2;
    private Player myPlayer3;

    private GamePanel myGamePanel;
    private GamePanel myGamePanel2;
    private GamePanel myGamePanel3;

    private Maze myMaze;
    private Maze myMaze2;
    private Maze myMaze3;

    private Controller keyH;
    private Controller keyH2;
    private Controller keyH3;

    private LevelInterface myLevelInterface;

    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Tile size is set for 48x48.
   // public int screenWidth = (int) screenSize.getWidth() / 15;
    // The movement speed of the player.
    private int myLevel;

    private boolean LVL = true;
    private boolean Game = false;

    // Checks if we set up the JFrame

    // for level interface
    private boolean haveStartedLevel = false;

    // for lvl 1 map
    private boolean haveWeSetThisMapBefore = false;
    // for lvl 2 map
    private boolean haveStarted2 = false;
    // for lvl 3 map
    private boolean haveStarted3 = false;

    private static final int level1Move = 25;

    private static final int level2Move = 50;

    private static final int level3Move = 75;
    public GameLoop() throws FileNotFoundException {
        myLevel = -1;

        myMaze = new Maze(MAP1);
        myMaze2 = new Maze(MAP2);
        myMaze3 = new Maze(MAP3);

        myGameInterface = GameInterface.getInstance(1,level1Move);

        myLevelInterface = new LevelInterface(3);

        keyH = new Controller(MAP1, level1Move, 1);
        keyH2 = new Controller(MAP2, level2Move, 2);
        keyH3 = new Controller(MAP3, level3Move, 3);

        myPlayer = new Player(level1Move, MAP1);
        myPlayer2 = new Player(level2Move, MAP2);
        myPlayer3 = new Player(level3Move, MAP3);

        myGamePanel = new GamePanel(myMaze.getArray(), myPlayer);
        myGamePanel2 = new GamePanel(myMaze2.getArray(), myPlayer2);
        myGamePanel3 = new GamePanel(myMaze3.getArray(), myPlayer3);


        start();
    }
    public void start() {
        while(true) {
            System.out.println();
            // when game run, display the level interface first
            if (LVL) {
                levelInterface();
            }
            else if (Game) {
                if (myLevel == 1) {
                    gamePlayInterface1();
                } else if (myLevel == 2) {
                    gamePlayInterface2();
                } else if (myLevel == 3) {
                    gamePlayInterface3();
                }
            }
        }
    }
    public void levelInterface() {
        // If you've never run this map
        if(!haveStartedLevel) {
            // put the level interface in the center of the JFrame
            myGameInterface.setCenter(myLevelInterface);
            // updates the JFrame
            //myGameInterface.start();
            // No this command == seizure
            haveStartedLevel = true;
        }
        // listens to the level the player chose
        // System.out.println(myLevel);
        myLevel = myLevelInterface.getMyNum();
        //System.out.println(myLevel);
        // myLevel == -1 means we are in the level interface

        // myLevel > 0 means player chose the level so we are leaving this if condition.
        if(myLevel > 0) {
            LVL = false;
            Game = true;
        }
    }
    public void gamePlayInterface1() {
            // set up the JFrame
            if (!haveWeSetThisMapBefore) {
                // put the level interface in the center of the JFrame
                myGameInterface.setCenter(myGamePanel);
                // updates the JFrame

                myGamePanel.addKeyListener(keyH);

                myGamePanel.start();
                // No this command == seizure
                haveWeSetThisMapBefore = true;
            }

            // This is the one that's going to run a loop and update
            if (keyH.didWin()) {
                gamePlayInterface2();
               // haveWeSetThisMapBefore = false;
            } else {
                myGamePanel.run();
            }
    }
    public void gamePlayInterface2() {
            // set up the JFrame
            if (!haveStarted2) {
                // put the level interface in the center of the JFrame
                myGameInterface.setCenter(myGamePanel2);
                myGameInterface.start();
                // updates the JFrame

                myGamePanel2.addKeyListener(keyH2);

                myGamePanel2.start();
                // No this command == seizure
                haveStarted2 = true;
            }

        if (keyH2.didWin()) {
            gamePlayInterface3();
           // haveWeSetThisMapBefore = false;
        } else {
            myGamePanel2.run();
        }
    }
    // MAKE SURE TO  MAKE SEPERATE WINNING PANE FOR THIS MAP. INSTEAD OF NEXT, IT SHOULD QUIT THE GAME
    public void gamePlayInterface3() {
            // set up the JFrame
            if (!haveStarted3) {
                // put the level interface in the center of the JFrame
                myGameInterface.setCenter(myGamePanel3);
                // updates the JFrame

                myGamePanel3.addKeyListener(keyH3);

                myGamePanel3.start();
                // No this command == seizure
                haveStarted3 = true;
            }

            // This is the one that's going to run a loop and update
            myGamePanel3.run();
        }
}
