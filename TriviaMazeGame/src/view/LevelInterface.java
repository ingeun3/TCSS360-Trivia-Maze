package view;

import model.Maze;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class LevelInterface extends JPanel {

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final ImageIcon LEVEL = new ImageIcon("level.png");

    private final int SCREEN_WIDTH = (int) screenSize.getWidth();

    private final int SCREEN_HEIGHT = (int) screenSize.getHeight();

    // The JButton object for level1.
    private final JButton myLevel1;

    // The JButton object for level2.
    private final JButton myLevel2;

    // The JButton object for level3.
    private final JButton myLevel3;

    private char[][] myLevelArrayMap;

    private int myNum ;

    private int myCompletedLevel ;

//private static ImageIcon myImage;

    public LevelInterface(int theNum) {
        myNum = -1;
        myCompletedLevel = theNum;
        setLayout(new BorderLayout());
        myLevel1 = new JButton("Level 1");
        myLevel2 = new JButton("Level 2");
        myLevel3 = new JButton("Level 3");

        //myMaze = theMaze;
        // myImage = theImage;
        start();
    }

    private void updateMap(char[][] newLevelMap){
        myLevelArrayMap = newLevelMap;
    }

    private void start() {
        JLayeredPane layeredPane = new JLayeredPane();
        //SET THE PICTURE
        layeredPane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        //layeredPane.setBackground(Color.gray);
        JLabel backgroundLabel = new JLabel(LEVEL);
        backgroundLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(500, 200, 500, 300);
        // myLevel1.setBackground(Color.lightGray);

        buttonPanel.add(myLevel1);
        buttonPanel.add(myLevel2);
        buttonPanel.add(myLevel3);

        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);
        add(layeredPane, BorderLayout.CENTER);
        if (myCompletedLevel == 1){
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(false);
            myLevel3.setEnabled(false);
        } else if (myCompletedLevel == 2){
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(true);
            myLevel3.setEnabled(false);
        } else if (myCompletedLevel == 3){
            myLevel1.setEnabled(true);
            myLevel2.setEnabled(true);
            myLevel3.setEnabled(true);
        }
        myLevel1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myNum = 1;
                //char[][] level1Map =
                //updateMap(level1Map);
            }
        });
        myLevel2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.out.println("it clicked");
                myNum = 2;
            }

        });

        myLevel3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.out.println("it clicked");
                myNum = 3;
            }
        });
    }

    public int getMyNum(){
        return myNum;
    }

}
