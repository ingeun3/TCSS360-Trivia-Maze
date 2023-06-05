package view;

import java.awt.*;
import java.io.FileNotFoundException;

import javax.swing.*;

public class GameFrame extends JFrame{
    // Singleton instance
    private static GameFrame instance;

    // Class Constants
    private static final String LEVEL_PROMPT = "Level ";
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    // Class Fields
    //  private JFrame myGameInterface;
    private JPanel myNorthPanel;

    private JPanel myCenterPanel;

    GraphicsDevice mygDevice;

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
    private GameFrame(int theLevel, int theMoves)  {
        super("Trivia Maze");
        // myMazeArray = theMazeArray;
        myCenterPanel = new JPanel();
        String level = LEVEL_PROMPT + theLevel;
        String moves = MOVE_PROMPT + theMoves;
        // myGamePanel = GamePanel.getInstance(myMazeArray, thePlayer);
        myNorthPanel =  NorthPanel.getInstance(level, moves);
        GraphicsEnvironment gEnviroment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        mygDevice = gEnviroment.getDefaultScreenDevice();
        setIconImage(new ImageIcon("up.png").getImage());
    }

    /**
     * Returns the singleton instance of GameInterface.
     *
     * @param theLevel      the Level of the current game
     * @param theMoves      the number of legal moves in this level.
     * @return the singleton instance
     * @throws FileNotFoundException if the image file is not found
     */
    public static GameFrame getInstance(int theLevel, int theMoves) {
        if (instance == null) {
            instance = new GameFrame(theLevel, theMoves);
        }
        return instance;
    }

    /**
     * Starting the GUI
     */
    public void start() {
        setPreferredSize(new Dimension(800, 600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
//         fullScreen();
         // bring to front
        // unload the preveious panel and load the esc
        // set visible on the other one
    }

    public void fullScreen(){
        mygDevice.setFullScreenWindow(this);
    }
    public void removeNorthPanel() {
        getContentPane().remove(myNorthPanel);
        revalidate();
        repaint();
    }
    public void setNorthPanel(JPanel thePanel) {
        // Deleting the old game panel;
        getContentPane().remove(myNorthPanel);
        myNorthPanel = thePanel;
        revalidate();
        repaint();
        getContentPane().add(myNorthPanel, BorderLayout.NORTH);
        thePanel.requestFocus();
//        fullScreen();
        start();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public void setCenter(JPanel thePanel) {
        // Deleting the old game panel;
        getContentPane().remove(myCenterPanel);

        myCenterPanel = thePanel;
        revalidate();
        repaint();

        getContentPane().add(myCenterPanel, BorderLayout.CENTER);
        thePanel.requestFocus();

        start();
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }
    /**
     * Closes the GUI
     */
    public void close() {
        dispose();
    }
}