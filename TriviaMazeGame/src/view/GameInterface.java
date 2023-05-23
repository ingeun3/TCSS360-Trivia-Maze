package view;

import model.Player;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.JFrame;

public class GameInterface {
    // Class Constants

    // The level prompt that will be displayed in the middle of the north panel.
    private static final String LEVEL_PROMPT = "Level ";
    // The move prompt that will be displayed in the right of the north panel.
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    // Class Fields


    // The JFrame that will be the base of the GUI.
    private final JFrame myGameInterface;
    // The JPanel that will locate in the NORTH of the JFrame.
//    private final NorthPanel myNorthPanel;
    // The JPanel that displays the game in the CENTER of the JFrame.
    private final GamePanel myGamePanel;
   // GraphicsDevice mygDevice;

//    keyBoardHandler keyH = keyBoardHandler.getInstance();

    // The JPanel that will pop up from the JFrame.
    private final LevelInterface myLevelInterface;
    private char[][] myMazeArray;

    /**
     * Default constructor of the GameInterface (main GUI).
     * @param theLevel the Level of the current game
     * @param theMoves the number of legal moves in this level.
     * @param theMazeArray the 2d array that contains the map structure.
     */
    public GameInterface (int theLevel, int theMoves, char[][] theMazeArray, Player thePlayer) throws FileNotFoundException {
        myGameInterface = new JFrame("Trivia Maze");
        myMazeArray = theMazeArray;
        String level = LEVEL_PROMPT + theLevel;
        String moves = MOVE_PROMPT + theMoves;
        myGamePanel = GamePanel.getInstance(myMazeArray, thePlayer);
//        myNorthPanel = new NorthPanel(level, moves);
        myLevelInterface = new LevelInterface(theMazeArray);
    }

    /**
     * Starting the GUI
     */
    public void start() {
        myGameInterface.setSize(800, 600);
   //     myGameInterface.getContentPane().add(myNorthPanel, BorderLayout.NORTH);
        myGameInterface.getContentPane().add(myGamePanel, BorderLayout.CENTER);
        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameInterface.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //myGameInterface.pack();
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);
        myGamePanel.requestFocus();
    }
   // public void setFullScren(){
       // mygDevice.setFullScreenWindow(myGameInterface);
    //}
}
