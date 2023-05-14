package view;

import javax.swing.*;
import java.awt.*;

public class GameInterface {

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the frame.  */
    private static final int WIDTH = SCREEN_SIZE.width / 3;

    /** The height of the frame.  */
    private static final int HEIGHT = SCREEN_SIZE.height / 3;

    private static final String LEVEL_PROMPT = "Level ";

    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private final String myLevel;

    private final String myMoves;

    private final JFrame myGameInterface;

    private final NorthPanel myNorthPanel;

    private final MazeMap mymazeMap;

    private char[][] myMazeArray;

    /** A size of the window. */
    private final Dimension myDimension;
    public GameInterface (int theLevel, int theMoves, char[][] theMazeArray) {
        myGameInterface = new JFrame("Trivia Maze");
        myDimension = new Dimension(WIDTH,HEIGHT);
        System.out.println(WIDTH);
        System.out.println(HEIGHT);

        myLevel = LEVEL_PROMPT + theLevel;
        myMoves = MOVE_PROMPT + theMoves;
        myNorthPanel = new NorthPanel(myLevel, myMoves);
        myMazeArray = theMazeArray;
        mymazeMap = new MazeMap(myMazeArray);
    }

    public void start() {
        myGameInterface.add(myNorthPanel, BorderLayout.NORTH);
        myGameInterface.add(mymazeMap, BorderLayout.CENTER);

        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //  myGameInterface.setPreferredSize(myDimension);
        myGameInterface.pack();
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);
    }
}