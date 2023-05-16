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

    public TutorialFrame() {
        myFrame = new JFrame("Tutorial");
        myTutorialPanel = new JPanel();
        myDimension = new Dimension(WIDTH,HEIGHT);
        start();
    }
    private void start() {
        myFrame.add(myTutorialPanel);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setPreferredSize(myDimension);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
}