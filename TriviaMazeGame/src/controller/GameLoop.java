package controller;

import model.Maze;
import model.Player;
import view.*;

import java.io.*;

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
        myCurrentLevel = -2;
        start();
        load();
    }
    public void start() throws FileNotFoundException {
            // Variables for tracking time
            long startTime;
            long elapsedTime;
            myGameInterface.start();
            int fPS = 60;
            long frameTimePerSecond = 1000 / fPS;
            boolean running = true;
            while (running) {
                startTime = System.currentTimeMillis();
                if (title) {
                    runningTitleInterface();
                } else if (level) {
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
                // Calculate the elapsed time for this frame
                elapsedTime = System.currentTimeMillis() - startTime;

                // Delay the loop to maintain the frame rate
                if (elapsedTime < frameTimePerSecond) {
                    try {
                        Thread.sleep(frameTimePerSecond - elapsedTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    public void runningTitleInterface() {
        if(!titleSetup) {
            myGameInterface.setCenter(myTitlePanel);
            titleSetup = true;
            System.out.println("second time");
        }
        myCurrentLevel = myTitlePanel.getMyNum();
        if (myCurrentLevel == 0) {
            System.out.println("currentLeve" + myCurrentLevel);
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
        myCurrentLevel = myLevelInterface.getMyNum();
        if (myCurrentLevel > 0) {
            level = false;
            game = true;
            levelSetup = false;
        } else if (myCurrentLevel  < 0) {
            System.out.println("it goes here");
            level = false;
            title = true;
            levelSetup = false;
            //titleSetup = false;
        }
    }

    private void runningGamePanel() throws FileNotFoundException {
        if(!gameSetup) {
            if(myCurrentLevel == 1) {
                myInitialMoves = 20;
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
    public void save() throws IOException {
        GameState gameState = new GameState(myCompletedLevel, myCurrentLevel);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
            oos.writeObject(gameState);
            System.out.println("Game saved successfully.");

            System.out.println("it's save");

        } catch (IOException e) {
            System.out.println("Failed to save the game.");
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
            GameState gameState = (GameState) ois.readObject();
            myCompletedLevel = gameState.getCompletedLevel();
            myCurrentLevel = gameState.getCurrentLevel();
            System.out.println("Game loaded successfully.");
        } catch (FileNotFoundException e) {
            // No save file found, continue with default values
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Failed to load the game.");
            e.printStackTrace();
        }
    }

}
