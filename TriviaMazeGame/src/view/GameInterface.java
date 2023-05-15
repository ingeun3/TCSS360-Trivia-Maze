package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class GameInterface {
    private static final String LEVEL_PROMPT = "Level ";

    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private final String myLevel;

    private final String myMoves;

    private final JFrame myGameInterface;

    private final NorthPanel myNorthPanel;

    private final GamePanel myMazeMap;

    private char[][] myMazeArray;

    /** A size of the window. */
   //private final Dimension myDimension;
    public GameInterface (int theLevel, int theMoves, char[][] theMazeArray) {
        myGameInterface = new JFrame("Trivia Maze");
        myMazeArray = theMazeArray;
        myLevel = LEVEL_PROMPT + theLevel;
        myMoves = MOVE_PROMPT + theMoves;
        myMazeMap = new GamePanel(myLevel, myMoves, myMazeArray);
        myNorthPanel = new NorthPanel(myMazeMap, myLevel, myMoves);


    }

    public void start() {
        myGameInterface.add(myNorthPanel, BorderLayout.NORTH);
        myGameInterface.getContentPane().add(myMazeMap, BorderLayout.CENTER);
        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameInterface.pack();
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);

    }



}