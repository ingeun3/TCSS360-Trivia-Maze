package controller;

import model.Maze;
import model.Player;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GameLoop {
    private GameInterface myGameInterface;


    private static final String MAP1 = "maze_map1.txt";
    private static final String MAP2 = "maze_map2.txt";
    private static final String MAP3 = "maze_map3.txt";

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
    private boolean haveStarted1 = false;
    // for lvl 2 map
    private boolean haveStarted2 = false;
    // for lvl 3 map
    private boolean haveStarted3 = false;

    public GameLoop(final int theMove, final int theLevel) throws FileNotFoundException {
        myLevel = -1;

        myMaze = new Maze(MAP1);
        myMaze2 = new Maze(MAP2);
        myMaze3 = new Maze(MAP3);

        myGameInterface = GameInterface.getInstance(1,theMove);
        myLevelInterface = new LevelInterface(theLevel);

        keyH = new Controller(MAP1, theMove, theLevel);
        keyH2 = new Controller(MAP2, theMove, theLevel);
        keyH3 = new Controller(MAP3, theMove, theLevel);

        myPlayer = new Player(theMove, MAP1);
        myPlayer2 = new Player(theMove, MAP2);
        myPlayer3 = new Player(theMove, MAP3);

        myGamePanel = GamePanel.getInstance(myMaze.getArray(), myPlayer);
        myGamePanel2 = GamePanel.getInstance(myMaze2.getArray(), myPlayer2);
        myGamePanel3 = GamePanel.getInstance(myMaze3.getArray(), myPlayer3);


        start();
    }
    public void start() {
        //myGameInterface.start();


//        myGamePanel2.start();
//        myGamePanel3.start();

        while(true) {
            // when game run, display the level interface first
            if (LVL) {
                // If you've never run this map
                if(!haveStartedLevel) {
                   // put the level interface in the center of the JFrame
                   myGameInterface.setCenter(myLevelInterface);
                   // updates the JFrame
                   myGameInterface.start();
                   // No this command == seizure
                    haveStartedLevel = true;
                }
                // listens to the level the player chose
                System.out.println(myLevel);
                myLevel = myLevelInterface.getMyNum();
                System.out.println(myLevel);
                // myLevel == -1 means we are in the level interface

                // myLevel > 0 means player chose the level so we are leaving this if condition.
                if(myLevel > 0) {
                    LVL = false;
                    Game = true;
                }
            }

            // running game panel

            else if (Game) {
                if (myLevel == 1) {
                    // set up the JFrame
                    if (!haveStarted1) {
                        // put the level interface in the center of the JFrame
                        myGameInterface.setCenter(myGamePanel3);
                        // updates the JFrame
                        myGameInterface.start();

                        myGamePanel3.addKeyListener(keyH3);

                        myGamePanel3.start();
                        // No this command == seizure
                        haveStarted1 = true;
                    }

                    // This is the one that's going to run a loop and update
                    myGamePanel3.run();
                }


            }
        }
    }
}
