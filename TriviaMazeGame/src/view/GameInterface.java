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

    private final JPanel myNorthPanel;

    /** A size of the window. */
    private final Dimension myDimension;
    public GameInterface (int theLevel, int theMoves) {
        myGameInterface = new JFrame("Trivia Maze");
        myDimension = new Dimension(WIDTH,HEIGHT);
        myLevel = LEVEL_PROMPT + theLevel;
        myMoves = MOVE_PROMPT + theMoves;
        myNorthPanel = new NorthPanel(myLevel, myMoves);
    }

    public void start() {
        myGameInterface.add(myNorthPanel, BorderLayout.NORTH);


        myGameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myGameInterface.setPreferredSize(myDimension);
        myGameInterface.pack();
        myGameInterface.setLocationRelativeTo(null);
        myGameInterface.setVisible(true);
    }
}
