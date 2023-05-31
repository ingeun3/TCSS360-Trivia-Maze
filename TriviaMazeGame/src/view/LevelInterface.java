package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

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

    private int myPanelNumber ;

    private int myCompletedLevel ;

    private Font myFont;

    public LevelInterface() {
        myPanelNumber = 0;
        myCompletedLevel = 1;
        setLayout(new BorderLayout());
        myLevel1 = new JButton("Level 1");
        myLevel2 = new JButton("Level 2");
        myLevel3 = new JButton("Level 3");
        start();
    }

    private void start() {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        unLockLevel();
        JLayeredPane layeredPane = new JLayeredPane();

        //SET THE PICTURE
        layeredPane.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        JLabel backgroundLabel = new JLabel(LEVEL);
        backgroundLabel.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));
        buttonPanel.setOpaque(false);
        buttonPanel.setBounds(SCREEN_WIDTH/4, SCREEN_HEIGHT/5, SCREEN_WIDTH/2,
                SCREEN_HEIGHT/2);

        myLevel1.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myLevel2.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myLevel3.setFont(myFont.deriveFont(Font.PLAIN, 45));

        buttonPanel.add(myLevel1);
        buttonPanel.add(myLevel2);
        buttonPanel.add(myLevel3);

        layeredPane.add(buttonPanel, JLayeredPane.PALETTE_LAYER);
        add(layeredPane, BorderLayout.CENTER);
        myLevel1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 1;
            }
        });
        myLevel2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 2;
            }

        });

        myLevel3.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 3;
            }
        });
    }

    public int getMyNum(){
        int temp = myPanelNumber;
        myPanelNumber = 0;
        return temp;
    }


    public void unLockLevel(){
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
        myCompletedLevel++;


    }

}
