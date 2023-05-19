package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import model.Maze;

public class NorthPanel extends JPanel{
    // Class Constants

    // The JPanel object that contains buttons.
    private final JPanel myButtonPanel;
    // The JButton object for Help buttons.
    private final JButton myHelpButton;
    // The JButton object for Save buttons.
    private final JButton mySaveButton;
    // The JButton object for Level buttons.
    private final JButton myLevelButton;

    // Class Fields

    // The Level that will display in the center of the NorthPanel.
    private JLabel myLevel;
    // The number of remaining moves display in the right of the NorthPanel.
    private JLabel myMoves;

    GamePanel gp;

    /**
     * The default constructor for NorthPanel.
     * @param theLevel the current level that will display in the center of the NorthPanel.
     * @param theMoves the number of remaining moves that will display in the right of the NorthPanel.
     */
    public NorthPanel(GamePanel gp, String theLevel, String theMoves) {
        this.gp = gp;
        myButtonPanel = new JPanel(new FlowLayout());
        myHelpButton = new JButton("Help");
        mySaveButton     = new JButton("Save");
        myLevelButton = new JButton("Level");
        myLevel = new JLabel(theLevel,  SwingConstants.CENTER);
        myMoves = new JLabel(theMoves,  SwingConstants.CENTER);
        start();
    }

    /**
     * Initializing NorthPanel.
     */
    private void start() {
        myButtonPanel.add(myHelpButton);
        myButtonPanel.add(mySaveButton);
        myButtonPanel.add(myLevelButton);
        myButtonPanel.setLayout(new GridLayout(1,3));

        myHelpButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                try {
                    Maze mazeMap = new Maze("tutorial_map.txt");
                    TutorialFrame tutorial = new TutorialFrame();
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        this.add(myButtonPanel);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));
    }

    /**
     * Updates the current level.
     * @param theLevel the level you want to update to.
     */
    public void setLevel(String theLevel) {
        myLevel = new JLabel(theLevel);
        start();
    }

    /**
     * Updates the remaining moves.
     * @param theMoves the updated remaining number of  moves.
     */
    public void setMoves(String theMoves) {
        myMoves = new JLabel(theMoves);
        start();
    }
}
