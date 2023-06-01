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
    private static final String MAP4 = "maze_map6.txt";


    private int myInitialMoves = 0;


    private GameInterface myGameInterface;
    private LevelInterface myLevelInterface;
    private TitlePanel myTitlePanel;
    private GamePanel myCurrentGamePanel;
    private Maze currentMaze;
    private Player currentPlayer;
    private GameLogic myCurrentGameLogic;
    private NorthPanel myCurrentNorthPanel;

    private int myCurrentLevel;

    private int myCompletedLevel;

    private String mymMazeFileName;

    private boolean levelSetup = false;
    private boolean gameSetup = false;
    private boolean titleSetup = false;

    private boolean title = true;
    private boolean level = false;
    private boolean game = false;

    public GameLoop() throws FileNotFoundException {
        myCompletedLevel = 1;
        myTitlePanel = new TitlePanel();
        myGameInterface = GameInterface.getInstance(1, 100);
        myLevelInterface = new LevelInterface();
        myCurrentGamePanel = null;
        currentMaze = null;
        currentPlayer = null;
        myCurrentGameLogic = null;
        myCurrentLevel = -1;
        start();
    }
    public void start() throws FileNotFoundException {
        myGameInterface.start();
        while(true) {
            if (title) {
                runningTitleInterface();
            } else if(level) {
                runningLevelInterface();
            } else if (game) {
                if (myCurrentLevel == 1) {
                    mymMazeFileName = MAP1;
                } else if (myCurrentLevel == 2) {
                    mymMazeFileName = MAP2;
                } else if (myCurrentLevel == 3) {
                    mymMazeFileName = MAP3;
                } else if (myCurrentLevel == 4) {
                    mymMazeFileName = MAP4;
                } else {
                    throw new IllegalArgumentException("Invalid level: " + myCurrentLevel);
                }
                runningGamePanel();
            }
        }
    }
    public void runningTitleInterface() {
        if(!titleSetup) {
            myGameInterface.setCenter(myTitlePanel);
            titleSetup = true;
        }
        System.out.println();
        myCurrentLevel = myTitlePanel.getMyNum();
        if (myCurrentLevel == 0) {
            title = false;
            level = true;
            titleSetup = false;
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
        } else if (myCurrentLevel < 0) {
            level = false;
            title = true;
            levelSetup = false;
        }
    }

    private void runningGamePanel() throws FileNotFoundException {
        if(!gameSetup) {
            if(myCurrentLevel == 1) {
                myInitialMoves = 1000;
            } else if(myCurrentLevel == 2) {
                myInitialMoves = 25;
            } else if(myCurrentLevel == 3) {
                myInitialMoves = 50;
            } else if(myCurrentLevel == 4) {
                myInitialMoves = 75;
            }
            currentMaze = new Maze(mymMazeFileName);
            currentPlayer = new Player(myInitialMoves, mymMazeFileName);
            myCurrentGameLogic = new GameLogic(mymMazeFileName, myInitialMoves, myCurrentLevel);

            myCurrentGamePanel = new GamePanel(currentMaze.getArray(), currentPlayer);
            myCurrentNorthPanel = NorthPanel.getInstance(LEVEL_PROMPT + myCurrentLevel, MOVE_PROMPT + myInitialMoves);
            myCurrentNorthPanel.setMoves(MOVE_PROMPT + myInitialMoves);
            myCurrentNorthPanel.setLevel(LEVEL_PROMPT + myCurrentLevel);
            myCurrentGamePanel.start();
            myCurrentGamePanel.addKeyListener(myCurrentGameLogic);
            myGameInterface.setNorthPanel(myCurrentNorthPanel);
            myGameInterface.setCenter(myCurrentGamePanel);
            gameSetup = true;
        }
        if(myCurrentNorthPanel.stageButton() || myCurrentGameLogic.goToStage()) {

            level = true;
            game = false;
            gameSetup = false;
            myCurrentLevel = 0;
            myGameInterface.removeNorthPanel();

        } else if(myCurrentGameLogic.didWin() && myCurrentLevel < 4) {
            myCurrentLevel++;
            if (myCurrentLevel > myCompletedLevel) {
                myCompletedLevel++;
                myLevelInterface.unLockLevel();
            }
            gameSetup = false;

        } else {
            myCurrentGamePanel.run();
        }

    }

}