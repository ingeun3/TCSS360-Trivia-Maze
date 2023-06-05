package controller;

import model.Maze;
import model.Player;
import model.Sound;
import view.*;

import java.io.*;

public class GameLoop implements Serializable {
    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private static final String LEVEL_PROMPT = "Level ";

    private static final String MAP1 = "maze_map3.txt";
    private static final String MAP2 = "maze_map4.txt";
    private static final String MAP3 = "maze_map5.txt";
    private static final String MAP4 = "maze_map6.txt";
    private int myInitialMoves = 0;
    private boolean levelSetup = false;
    private boolean gameSetup = false;
    private boolean titleSetup = false;

    private boolean title = true;
    private boolean level = false;
    private boolean game = false;
    private GameInterface myGameInterface;
    private LevelInterface myLevelInterface;
    private TitlePanel myTitlePanel;
    private GamePanel myCurrentGamePanel;
    private Maze myCurrentMaze;
    private Player myCurrentPlayer;
    private GameLogic myCurrentGameLogic;
    private NorthPanel myCurrentNorthPanel;
    private int myCurrentCenterPanel;
    private int myCompletedLevel;
    private String myMazeFileName;
    private Sound myTitleSound;
    private Sound myGameSound;
    private boolean myTitleSoundFlag;
    private boolean myGameSoundFlag;

    public GameLoop() throws IOException {
        load();
        myTitleSoundFlag = false;
        myTitlePanel = new TitlePanel();
        myGameInterface = GameInterface.getInstance(1, 100);
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
                    if (myCurrentCenterPanel == 1) {
                        myMazeFileName = MAP1;
                    } else if (myCurrentCenterPanel == 2) {
                        myMazeFileName = MAP2;
                    } else if (myCurrentCenterPanel == 3) {
                        myMazeFileName = MAP3;
                    } else if (myCurrentCenterPanel == 4) {
                        myMazeFileName = MAP4;
                    } else {
                        throw new IllegalArgumentException("Invalid level: " + myCurrentCenterPanel);
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
            if(!myTitleSoundFlag) {
                myGameSound.stop();
                myTitleSound.play();
                myTitleSound.loop();
                myTitleSoundFlag = true;
            }
            myGameInterface.setCenter(myTitlePanel);
            titleSetup = true;
        }
        myCurrentCenterPanel = myTitlePanel.getMyNum();
        if (myCurrentCenterPanel == 0) {
           // System.out.println("currentLeve" + myCurrentLevel);
            if(myTitlePanel.restartGame()) {
                myCompletedLevel = 0;
            }
            //System.out.println(myCompletedLevel);
            myLevelInterface = new LevelInterface(myCompletedLevel);
            title = false;
            level = true;
            titleSetup = false;
        }
    }
    public void runningLevelInterface() {
        if(!levelSetup) {
            if(!myTitleSoundFlag) {
                myGameSound.stop();
                myTitleSound.play();
                myTitleSound.loop();
                myTitleSoundFlag = true;
            }
            myGameInterface.setCenter(myLevelInterface);
            levelSetup = true;
        }
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
            myCurrentPlayer = new Player(myInitialMoves, myMazeFileName);
            myCurrentGameLogic = new GameLogic(myCurrentMaze, myInitialMoves, myCurrentCenterPanel, myCurrentPlayer);
            myCurrentGamePanel = new GamePanel(myCurrentMaze.getArray(), myCurrentPlayer);
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
