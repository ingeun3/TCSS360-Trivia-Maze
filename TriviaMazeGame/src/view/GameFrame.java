package view;

import java.awt.*;
import javax.swing.*;

/*
 * The main JFrame that will display the game.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */

public class GameFrame extends JFrame{

    private static GameFrame instance;
    private static final String LEVEL_PROMPT = "Level ";
    private static final String MOVE_PROMPT = "Remaining Moves: ";

    /**
     * Initialize the NorthPanel object.
     */
    private JPanel myNorthPanel;
    /**
     * Initialize the CenterPanel Object.
     */
    private JPanel myCenterPanel;

    /**
     * Private constructor of the GameInterface (main GUI).
     *
     * @param theLevel the Level of the current game
     * @param theMoves the number of legal moves in this level.
     */
    private GameFrame(final int theLevel, final int theMoves)  {
        super("Trivia Maze");
        myCenterPanel = new JPanel();
        String level = LEVEL_PROMPT + theLevel;
        String moves = MOVE_PROMPT + theMoves;
        myNorthPanel =  NorthPanel.getInstance(level, moves);
        setIconImage(new ImageIcon("./resources/visuals/up.png").getImage());
    }

    /**
     * Returns the singleton instance of GameInterface.
     *
     * @param theLevel the Level of the current game
     * @param theMoves the number of legal moves in this level.
     * @return the singleton instance
     */
    public static GameFrame getInstance(final int theLevel, final int theMoves) {
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
    }

    /**
     * Delete the Panel in the NorthRegion of the GameFrame
     */
    public void removeNorthPanel() {
        getContentPane().remove(myNorthPanel);
        revalidate();
        repaint();
    }

    /**
     * Adds JPanel to the North region of the GameFrame.
     * @param thePanel the Panel that will be added to the North of the JFrame.
     */
    public void setNorthPanel(final JPanel thePanel) {
        removeNorthPanel();
        myNorthPanel = thePanel;
        getContentPane().add(myNorthPanel, BorderLayout.NORTH);
        thePanel.requestFocus();
        start();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    /**
     * Adds JPanel to the Center region of the GameFrame.
     * @param thePanel the Panel that will be added to the Center of the JFrame.
     */
    public void setCenter(final JPanel thePanel) {
        getContentPane().remove(myCenterPanel);
        myCenterPanel = thePanel;
        revalidate();
        repaint();
        getContentPane().add(myCenterPanel, BorderLayout.CENTER);
        thePanel.requestFocus();
        start();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

}