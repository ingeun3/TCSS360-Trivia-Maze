package controller;

import model.Maze;
import model.Player;
import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class GameLoop {
    private static final String MAP1 = "maze_map1.txt";
    private static final String MAP2 = "maze_map2.txt";
    private static final String MAP3 = "maze_map3.txt";

    // for level interface
    private boolean myLevelStarted = false;

    private GameInterface myGameInterface;
    private LevelInterface myLevelInterface;
    private GamePanel myCurrentGamePanel;
    private Maze myCurrentMaze;
    private Player myCurrentPlayer;
    private Controller myCurrentKeyHandler;
    private int myCurrentLevel;
    private boolean myLevelCompleted;
    private int myMoveCount;
    private int mySelectedLevel;

    public GameLoop(int theMove, int theLevel) throws FileNotFoundException {
        myGameInterface = GameInterface.getInstance(theLevel, theMove);
        myLevelInterface = new LevelInterface(theLevel);
        myCurrentGamePanel = null;
        myCurrentMaze = null;
        myCurrentPlayer = null;
        myCurrentKeyHandler = null;
        myCurrentLevel = -1;
        myLevelCompleted = false;
        myMoveCount = theMove;
        mySelectedLevel = theLevel;

        start();
    }

    void start() throws FileNotFoundException {
        if (!myLevelCompleted) { // false which mean hasn't started yet.
            myGameInterface.setCenter(myLevelInterface);
            myGameInterface.start();
            System.out.println("passedd");
            myLevelCompleted = true;
        }
       //myGameInterface.start();

        myLevelInterface.addLevelButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                String text = source.getText().trim(); // Remove spaces
                String numericPart = text.replaceAll("\\D+", "");
                mySelectedLevel = Integer.parseInt(numericPart);
                System.out.println(mySelectedLevel);
                if ((mySelectedLevel != myCurrentLevel && myLevelCompleted)) {
                    try {
                        switchToLevel(mySelectedLevel);
                    } catch (FileNotFoundException et) {
                        throw new RuntimeException(et);
                    }
                }
                else {
                    try {
                        switchToLevel(mySelectedLevel);
                        System.out.println("This is passed");
                    } catch (FileNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }

            }
        });

        //switchToLevel(mySelectedLevel);
    }

    private void switchToLevel(int level) throws FileNotFoundException {
        myCurrentLevel = level;
        // Load the appropriate maze and player based on the level
        String mazeFileName;
        if (level == 1) {
            mazeFileName = MAP1;
        } else if (level == 2) {
            mazeFileName = MAP2;
        } else if (level == 3) {
            mazeFileName = MAP3;
        } else {
            throw new IllegalArgumentException("Invalid level: " + level);
        }

        myCurrentMaze = new Maze(mazeFileName);
        myCurrentKeyHandler = new Controller(mazeFileName, myMoveCount, level);
        myCurrentPlayer = new Player(myMoveCount, mazeFileName);
        myCurrentGamePanel = GamePanel.getInstance(myCurrentMaze.getArray(), myCurrentPlayer);
        myGameInterface.setCenter(myCurrentGamePanel);
        myGameInterface.start();
        myCurrentGamePanel.addKeyListener(myCurrentKeyHandler);
        myCurrentGamePanel.start();


       // while(true) {
            myCurrentGamePanel.run();
            System.out.println("This is passed 2");
       // }
       // myLevelCompleted = false; //need to check they win or not in this comment
       // }

    }
//    public void levelCompleted() {
//        myLevelCompleted = true;
//    }
}
