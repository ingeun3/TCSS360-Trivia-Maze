package view;

import javax.swing.*;
import java.awt.*;

public class TutorialFrame {

    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();

    /** The width of the frame.  */
    private static final int WIDTH = SCREEN_SIZE.width / 4;

    /** The height of the frame.  */
    private static final int HEIGHT = SCREEN_SIZE.height / 4;

    private final Dimension myDimension;

    private final JFrame myFrame;

    private final JPanel myTutorialPanel;

    private final GamePanel myMazeMap;

    private final JLabel myInstruction;

    private char[][] myMazeArray;

    public TutorialFrame(char[][] theMazeArray) {
        myFrame = new JFrame("Tutorial");
        myTutorialPanel = new JPanel();
        myDimension = new Dimension(WIDTH,HEIGHT);
        myMazeArray = theMazeArray;
        myMazeMap = new GamePanel(myMazeArray);
        myInstruction = new JLabel("Click D to Move Left");
        myInstruction.setFont(new Font("Serif", Font.PLAIN, 25));
        start();
    }
    private void start() {
        myFrame.add(myTutorialPanel);
        myFrame.add(myInstruction, BorderLayout.NORTH);
        myFrame.getContentPane().add(myMazeMap, BorderLayout.CENTER);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
}
