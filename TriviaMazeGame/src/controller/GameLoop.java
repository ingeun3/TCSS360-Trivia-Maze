package controller;

import model.Maze;
import model.Player;
import view.*;

import java.io.FileNotFoundException;

public class GameLoop {
    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private static final String LEVEL_PROMPT = "Level ";

    private static final String MAP1 = "maze_map3.txt";
    private static final String MAP2 = "maze_map4.txt";
    private static final String MAP3 = "maze_map5.txt";



    private int myInitialMoves = 0;


    private GameInterface myGameInterface;
    private LevelInterface myLevelInterface;
    private GamePanel myCurrentGamePanel;
    private Maze currentMaze;
    private Player currentPlayer;
    private Controller myCurrentController;
    private NorthPanel myCurrentNorthPanel;

    private int myCurrentLevel;

    private int myCompletedLevel;

    private String mymMazeFileName;

    private boolean levelSetup = false;
    private boolean gameSetup = false;
    private boolean level = true;
    private boolean game = false;



    public GameLoop() throws FileNotFoundException {
        myCompletedLevel = 1;
        myGameInterface = GameInterface.getInstance(1, 100);
        myLevelInterface = new LevelInterface();
        myCurrentGamePanel = null;
        currentMaze = null;
        currentPlayer = null;
        myCurrentController = null;
        myCurrentLevel = -1;
        start();
    }
    public void start() throws FileNotFoundException {
        myGameInterface.start();
        while(true) {
            if(level) {
                runningLevelInterface();
            } else if (game) {
                if (myCurrentLevel == 1) {
                    mymMazeFileName = MAP1;
                } else if (myCurrentLevel == 2) {
                    mymMazeFileName = MAP2;
                } else if (myCurrentLevel == 3) {
                    mymMazeFileName = MAP3;
                } else {
                    throw new IllegalArgumentException("Invalid level: " + myCurrentLevel);
                }
                runningGamePanel();
            }
        }
    }


    public void runningLevelInterface() {
        if(!levelSetup) {
            myGameInterface.setCenter(myLevelInterface);
            levelSetup = true;
        }
        System.out.println();
        myCurrentLevel = myLevelInterface.getMyNum();
        if (myCurrentLevel > 0) {
            level = false;
            game = true;
            levelSetup = false;
        }

    }

    private void runningGamePanel() throws FileNotFoundException {
        if(!gameSetup) {
            if(myCurrentLevel == 1) {
                myInitialMoves = 25;
            } else if(myCurrentLevel == 2) {
                myInitialMoves = 50;
            } else if(myCurrentLevel == 3) {
                myInitialMoves = 75;
            }
            currentMaze = new Maze(mymMazeFileName);
            currentPlayer = new Player(myInitialMoves, mymMazeFileName);
            myCurrentController = new Controller(mymMazeFileName, myInitialMoves, myCurrentLevel);
            myCurrentGamePanel = new GamePanel(currentMaze.getArray(), currentPlayer);
            myCurrentNorthPanel = NorthPanel.getInstance(LEVEL_PROMPT + myCurrentLevel, MOVE_PROMPT + myInitialMoves);
            myCurrentNorthPanel.setMoves(MOVE_PROMPT + myInitialMoves);
            myCurrentNorthPanel.setLevel(LEVEL_PROMPT + myCurrentLevel);
            myCurrentGamePanel.start();
            myCurrentGamePanel.addKeyListener(myCurrentController);
            myGameInterface.setNorthPanel(myCurrentNorthPanel);
            myGameInterface.setCenter(myCurrentGamePanel);
            gameSetup = true;
        }
        if(myCurrentNorthPanel.stageButton()) {

            level = true;
            game = false;
            gameSetup = false;
            myCurrentLevel = -1;
            myGameInterface.removeNorthPanel();

        } else if(myCurrentController.didWin() && myCurrentLevel < 3) {
            myCurrentLevel++;
            if (myCurrentLevel > myCompletedLevel) {
                System.out.println("hi");
                myCompletedLevel++;
                myLevelInterface.unLockLevel();
            }
            gameSetup = false;

        } else {
            myCurrentGamePanel.run();
        }

    }

}