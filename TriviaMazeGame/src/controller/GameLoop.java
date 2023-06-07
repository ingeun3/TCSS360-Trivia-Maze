package controller;

/**
 *
 * This class is the Game Loop class that controls all the classes.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */

import model.Maze;
import model.Player;
import model.Sound;
import view.*;

import java.io.*;

// This Game Loop class that controls all classes.

public class GameLoop implements Serializable {

    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private static final String LEVEL_PROMPT = "Level ";

    private static final String MAP1 = "maze_map0.txt";
    private static final String MAP2 = "maze_map1.txt";
    private static final String MAP3 = "maze_map2.txt";
    private static final String MAP4 = "maze_map3.txt";
    private int myInitialMoves = 0;

    // Initialized the Level Interface flag to false.
    private boolean levelSetup = false;

    // Initialized the Game Interface flag to false.
    private boolean gameSetup = false;

    // Initialized the Title Interface flag to false.
    private boolean titleSetup = false;

    // set the title, level, game booleans to flase.
    private boolean title = true;
    private boolean level = false;
    private boolean game = false;

    //Initialized the GameInterface.
    private GameFrame myGameInterface;

    //Initialized the LevelInterface.
    private LevelPanel myLevelInterface;

    //Initialized the Title Interface.
    private TitlePanel myTitlePanel;

    //Initialized the Game Panel.
    private GamePanel myCurrentGamePanel;

    //Initialized the Maze.
    private Maze myCurrentMaze;

    //Initialized the Player.
    private Player myCurrentPlayer;

    //Initialized the GameLogic.
    private GameLogic myCurrentGameLogic;

    //Initialized the NorthPanel.
    private NorthPanel myCurrentNorthPanel;

    // Initialized the myCurrentCenterPanel that keeps track of which Panel currently Player in.
    private int myCurrentCenterPanel;

    //Initialized the Player Completed Level.
    private int myCompletedLevel;

    //Initialized the maze File Name.
    private String myMazeFileName;

    // Initialized the Title Sound.
    private Sound myTitleSound;

    // Initialized the Game Sound.
    private Sound myGameSound;

    // Initialized the boolean Flag for Title Sound.
    private boolean myTitleSoundFlag;

    // Initialized the boolean Flag for myGameSound.
    private boolean myGameSoundFlag;

    public GameLoop() throws IOException {
        // call the Load method for resuming the previous level
        load();
        myTitleSoundFlag = false;
        myTitlePanel = new TitlePanel();
        myGameInterface = GameFrame.getInstance(1, 100);
        myCurrentGamePanel = null;
        myCurrentMaze = null;
        myCurrentPlayer = null;
        myCurrentGameLogic = null;
        myCurrentCenterPanel = -2;
        myTitleSound = new Sound("Title Sound.wav");
        myGameSound = new Sound("Game Sound.wav");
        start();
    }

    public void start() throws IOException {
            // Variables for tracking time
            long startTime;
            long elapsedTime;
            // called the GameInterface to start the frame.
            myGameInterface.start();
            int fPS = 60;
            long frameTimePerSecond = 1000 / fPS;
            boolean running = true;
            while (running) {
                startTime = System.currentTimeMillis();
                // if Title is true then run the title Interface Panel.
                if (title) {
                    runningTitleInterface();
                    // if level is true then run the Level Interface Panel.
                } else if (level) {
                    runningLevelInterface();
                    // run the GamePanel.
                } else if (game) {
                    // generate the MAZE MAP 1 if myCurrentCenterPanel = 1.
                    if (myCurrentCenterPanel == 1) {
                        myMazeFileName = MAP1;
                    // generate the MAZE MAP 2 if myCurrentCenterPanel = 2.
                    } else if (myCurrentCenterPanel == 2) {
                        myMazeFileName = MAP2;
                    // generate the MAZE MAP 3 if myCurrentCenterPanel = 3.
                    } else if (myCurrentCenterPanel == 3) {
                        myMazeFileName = MAP3;
                    // generate the MAZE MAP 4 if myCurrentCenterPanel = 4.
                    } else if (myCurrentCenterPanel == 4) {
                        myMazeFileName = MAP4;
                    } else {
                        throw new IllegalArgumentException("Invalid level: " + myCurrentCenterPanel);
                    }
                    // run the Game Panel.
                    runningGamePanel();
                }
                // Calculate the elapsed time for the Frame.
                elapsedTime = System.currentTimeMillis() - startTime;

                // using the Thread method to delay the loop
                if (elapsedTime < frameTimePerSecond) {
                    try {
                        Thread.sleep(frameTimePerSecond - elapsedTime);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    /**
     * This method run the Title Interface.
      */
    public void runningTitleInterface() {
        //check the TitleSetup condition.
        if(!titleSetup) {
            //check the Title Sound Flag condition.
            if(!myTitleSoundFlag) {
                // If these condition meets
                // stop the game sound and play the title sound.
                myGameSound.stop();
                myTitleSound.play();
                // loop the title sound.
                myTitleSound.loop();
                myTitleSoundFlag = true;
            }
            // set the Title Interface into the GameInterface Frame.
            myGameInterface.setCenter(myTitlePanel);
            titleSetup = true;
        }
        // gets the Title Interface button number that user clicks
        myCurrentCenterPanel = myTitlePanel.getMyNum();
        // if it's zero, restart the TitlePanel.
        if (myCurrentCenterPanel == 0) {
            if(myTitlePanel.restartGame()) {
                myCompletedLevel = 0;
            }
            myLevelInterface = new LevelPanel(myCompletedLevel);
            title = false;
            level = true;
            titleSetup = false;
        }
    }

    /**
     * This method run the Level Interface.
     */
    public void runningLevelInterface() {
        //check the LevelSetup condition.
        if(!levelSetup) {
            if(!myTitleSoundFlag) {
                myGameSound.stop();
                myTitleSound.play();
                myTitleSound.loop();
                myTitleSoundFlag = true;
            }
            // set the LevelInterface Panel.
            myGameInterface.setCenter(myLevelInterface);
            levelSetup = true;
        }
        //
        myCurrentCenterPanel = myLevelInterface.getMyNum();
        if (myCurrentCenterPanel > 0) {
            level = false;
            game = true;
            levelSetup = false;
        } else if (myCurrentCenterPanel < 0) {
            level = false;
            title = true;
            levelSetup = false;
        }
    }
    private void runningGamePanel() throws IOException {
        if(!gameSetup) {
            myTitleSoundFlag = false;
            myTitleSound.stop();
            if(!myGameSoundFlag) {
                myGameSound = new Sound("Game Sound.wav");
                myGameSoundFlag = true;
                myGameSound.loop();
            }



            myCurrentMaze = new Maze(myMazeFileName);
            if(myCurrentCenterPanel == 1) {
                myInitialMoves = 1000;
            } else {
                // Clinton -> 422
                // David -> 200
                myInitialMoves = (int) Math.ceil(myCurrentMaze.getNumOfStr() / 10.0) * 10;
            }
            myCurrentPlayer = new Player(myInitialMoves, myCurrentMaze);
            myCurrentGameLogic = new GameLogic(myCurrentMaze, myInitialMoves, myCurrentCenterPanel, myCurrentPlayer);
            myCurrentGamePanel = new GamePanel(myCurrentMaze.getArray(), myCurrentPlayer.getLocation());
            myCurrentNorthPanel = NorthPanel.getInstance(LEVEL_PROMPT + myCurrentCenterPanel, MOVE_PROMPT + myInitialMoves);
            myCurrentNorthPanel.setMoves(myInitialMoves);
            myCurrentNorthPanel.setLevel(myCurrentCenterPanel);
            myCurrentGamePanel.start();
            myCurrentGamePanel.addKeyListener(myCurrentGameLogic);
            myGameInterface.setNorthPanel(myCurrentNorthPanel);
            myGameInterface.setCenter(myCurrentGamePanel);
            if(myCurrentCenterPanel == 1) {
                TutorialInstructionFrame theInstruction = new TutorialInstructionFrame();
                theInstruction.start();
            }
            gameSetup = true;
        }
        if(myCurrentNorthPanel.stageButton() || myCurrentGameLogic.goToStage()) {
            level = true;
            game = false;
            gameSetup = false;
            myCurrentCenterPanel = 0;
            myGameInterface.removeNorthPanel();
            myGameSound.stop();
            myGameSoundFlag = false;
        } else if(myCurrentGameLogic.didWin() && myCurrentCenterPanel < 4) {
            myCurrentCenterPanel++;
            if (myCurrentCenterPanel > myCompletedLevel) {
                myCompletedLevel++;
                myLevelInterface.unLockLevel();
            }
            save();
            gameSetup = false;
        } else {
            myCurrentGamePanel.run();
        }

    }
    public void save() {
        GameState gameState = new GameState(myCompletedLevel, myCurrentCenterPanel);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")))) {
            oos.writeObject(gameState);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")))) {
            GameState gameState = (GameState) ois.readObject();
            myCompletedLevel = gameState.getCompletedLevel();
            myCurrentCenterPanel = gameState.getCurrentLevel();
        } catch (FileNotFoundException e) {
            // No save file found, continue with default values
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
    