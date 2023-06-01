package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import model.Maze;

public class NorthPanel extends JPanel {

    private static NorthPanel myInstance;
    ;
    private JButton myStageButton;


    private JLabel myLevel;
    private JLabel myMoves;

    private Font myFont;

    private boolean myStageButtonClicked;

    private NorthPanel(String theLevel, String theMoves) {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        myStageButtonClicked = false;

        setBackground(new Color(0, 0, 0));


        myStageButton = new JButton("Stage");
        myStageButton.setFont(myFont.deriveFont(Font.PLAIN, 20));
        myStageButton.setBackground(Color.black);
        //
        myStageButton.setOpaque(true);
        myStageButton.setBorderPainted(false);
        myStageButton.setForeground(Color.WHITE);



        myLevel = new JLabel(theLevel, SwingConstants.CENTER);
        myLevel.setForeground(new Color(255, 255, 255, 200));
        myLevel.setFont(myFont.deriveFont(Font.PLAIN, 20));

        myMoves = new JLabel(theMoves, SwingConstants.CENTER);
        myMoves.setForeground(new Color(255, 255, 255, 200));
        myMoves.setFont(myFont.deriveFont(Font.PLAIN, 20));


        this.add(myStageButton);
        this.add(myLevel);
        this.add(myMoves);
        this.setLayout(new GridLayout(1, 3));

        start();
    }

    // Theme
    // Qiestions
    // Maps
    // Equation move



    public static NorthPanel getInstance(String theLevel, String theMoves) {
        if (myInstance == null) {
            synchronized (NorthPanel.class) {
                if (myInstance == null) {
                    myInstance = new NorthPanel(theLevel, theMoves);
                }
            }
        }
        return myInstance;
    }

    public void start() {
        myStageButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myStageButtonClicked = true;
            }

        });
    }
    public static NorthPanel getInstance() {
        return myInstance;
    }

    public boolean stageButton() {
        boolean flag = myStageButtonClicked;
        myStageButtonClicked = false;
        return flag;
    }

    public void setLevel(String theLevel) {
        myLevel.setText(theLevel);
    }

    public void setMoves(String theMoves) {
        myMoves.setText(theMoves);
    }
}
