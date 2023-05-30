package view;

import model.Player;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

public class GameInterface {
    // Singleton instance
    private static GameInterface instance;

    // Class Constants
    private static final String LEVEL_PROMPT = "Level ";
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    // Class Fields
    private JFrame myGameInterface;
    private JPanel myNorthPanel;

    private JPanel myGamePanel;

  //  private GamePanel myGamePanel;
  //  private LevelInterface myLevelInterface;
  //  private char[][] myMazeArray;

    /**
     * Private constructor of the GameInterface (main GUI).
     *
     * @param theLevel      the Level of the current game
     * @param theMoves      the number of legal moves in this level.
     * @throws FileNotFoundException if the image file is not found
     */
    private GameInterface(int theLevel, int theMoves)  {
        myGameInterface = new JFrame("Trivia Maze");
       // myMazeArray = theMazeArray;
        myGamePanel = new JPanel();
        String level = LEVEL_PROMPT + theLevel;
        String moves = MOVE_PROMPT + theMoves;
       // myGamePanel = GamePanel.getInstance(myMazeArray, thePlayer);
        myNorthPanel =  NorthPanel.getInstance(level, moves);
       // myLevelInterface = new LevelInterface(theLevel);
    }

    /**
     * Returns the singleton instance of GameInterface.
     *
     * @param theLevel      the Level of the current game
     * @param theMoves      the number of legal moves in this level.
     * @return the singleton instance
     * @throws FileNotFoundException if the image file is not found
     */
    public static GameInterface getInstance(int theLevel, int theMoves) {
        if (instance == null) {
            instance = new GameInterface(theLevel, theMoves);
        }
        return instance;
    }

    /**
     * Starting the GUI
     */
    public void start() {
        myGameInterface.setPreferredSize(new Dimension(800, 600));
        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameInterface.setExtendedState(JFrame.MAXIMIZED_BOTH);
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);
       // myGamePanel.requestFocus();
    }
    public void removeNorthPanel() {
        myGameInterface.getContentPane().remove(myNorthPanel);
    }
    public void setNorthPanel(JPanel thePanel) {
        // Deleting the old game panel;
        myGameInterface.getContentPane().remove(myNorthPanel);
        myNorthPanel = thePanel;
        myGameInterface.getContentPane().add(myNorthPanel, BorderLayout.NORTH);
        thePanel.requestFocus();

        start();
    }
    public void setCenter(JPanel thePanel) {
        // Deleting the old game panel;
        myGameInterface.getContentPane().remove(myGamePanel);
        myGamePanel = thePanel;
        myGameInterface.getContentPane().add(myGamePanel, BorderLayout.CENTER);
        thePanel.requestFocus();

        start();
    }
    /**
     * Closes the GUI
     */
    public void close() {
        myGameInterface.dispose();
    }
}
