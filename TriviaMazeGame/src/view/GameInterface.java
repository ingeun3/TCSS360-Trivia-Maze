package view;

import java.awt.*;

import javax.swing.JFrame;

public class GameInterface {

    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();

    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The constant number. */
    private static final int CONSTANT_NUMBER = 3;

    private static final String LEVEL_PROMPT = "Level ";

    private static final String MOVE_PROMPT = "Remaining Moves: ";
    private final String myLevel;

    private final String myMoves;

    private final JFrame myGameInterface;

    private final NorthPanel myNorthPanel;
    private char[][] myMazeArray;

    private final GamePanel myPanelMap;

    /** A size of the window. */
   //private final Dimension myDimension;
    public GameInterface (int theLevel, int theMoves, char[][] theMazeArray) {
        myGameInterface = new JFrame("Trivia Maze");
        myMazeArray = theMazeArray;
        myLevel = LEVEL_PROMPT + theLevel;
        myMoves = MOVE_PROMPT + theMoves;
        myPanelMap = new GamePanel(myLevel, myMoves, myMazeArray);
        myNorthPanel = new NorthPanel(myPanelMap, myLevel, myMoves);


    }

    public void start() {
        myGameInterface.add(myNorthPanel, BorderLayout.NORTH);
        myGameInterface.getContentPane().add(myPanelMap, BorderLayout.CENTER);
        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameInterface.pack();
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);
        // The Initial Window Size.
        myGameInterface.setPreferredSize(new Dimension(SCREEN_SIZE.width / CONSTANT_NUMBER,
                SCREEN_SIZE.height / CONSTANT_NUMBER));
        myGameInterface.setResizable(false);
        myPanelMap.requestFocus();

    }




}