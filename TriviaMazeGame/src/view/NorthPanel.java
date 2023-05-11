package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class NorthPanel extends JPanel{
    private final JPanel myButtonPanel;

    private final JButton myHelpButton;

    private final JButton mySaveButton;

    private final JButton myLevelButton;

    private JLabel myLevel;

    private JLabel myMoves;


    public NorthPanel(String theLevel, String theMoves) {
        myButtonPanel = new JPanel(new FlowLayout());
        myHelpButton = new JButton("Help");
        mySaveButton     = new JButton("Save");
        myLevelButton = new JButton("Level");
        myLevel = new JLabel(theLevel);
        myMoves = new JLabel(theMoves);
        start();
    }
    private void start() {
        myButtonPanel.add(myHelpButton);
        myButtonPanel.add(mySaveButton);
        myButtonPanel.add(myLevelButton);

        this.add(myButtonPanel);
        this.add(myLevel);
        this.add(myMoves);
    }

    public void setLevel(String theLevel) {
        myLevel = new JLabel(theLevel);
        start();
    }

    public void setMoves(String theMoves) {
        myMoves = new JLabel(theMoves);
        start();
    }
}
