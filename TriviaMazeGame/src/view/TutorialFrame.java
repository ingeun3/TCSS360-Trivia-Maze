package view;

import javax.swing.*;
import java.awt.*;

public class TutorialFrame {
    // Class Constants

    // A ToolKit.
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    // The Dimension of the screen.
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    // The width of the frame.
    private static final int WIDTH = SCREEN_SIZE.width / 4;
    // The height of the frame.
    private static final int HEIGHT = SCREEN_SIZE.height / 4;

    // Class Fields

    // The Dimension of the Tutorial Frame.
    private final Dimension myDimension;
    // The JFrame that will be used to display Tutorial panel.
    private final JFrame myFrame;
    // The JPanel that will be used to display of demonstration of the game play.
    private final JPanel myTutorialPanel;

    /**
     * The default constructor for tutorial frame.
     */
    public TutorialFrame() {
        myFrame = new JFrame("Tutorial");
        myTutorialPanel = new JPanel();
        myDimension = new Dimension(WIDTH,HEIGHT);
        start();
    }

    /**
     * Initializing the tutorial frame.
     */
    private void start() {
        myFrame.add(myTutorialPanel);
        myFrame.setPreferredSize(myDimension);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
}
