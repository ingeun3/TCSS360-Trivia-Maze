package view;
/**
 *
 * This class is the making the Title Panel.
 *
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 *
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

// This class is making Title Panel and extend the JPanel.
public class TitlePanel extends JPanel {

    /** Static for Image Icon. */
    private static final ImageIcon icon = new ImageIcon("titlename.png");

    /** Initialized the JLabel Title Name. */
    private final JLabel myTitle;

    /** Initialized the JButton for start, reset , and quit. */
    private final JButton myStartButton;
    private final JButton myResetButton;
    private final JButton myQuitButton;

    /** Initialized the Button */
    private final JPanel myButtonPanel;

    /** Initialized the panel Number that player clicks */
    private int myPanelNumber;

    /** Initialized the Font. */
    private Font myFont;

    /** Initialized the Reset Flag. */
    private boolean myReset;

    // This class creates the Title Panel.
    public TitlePanel() {
        // set the Reset Flag to false.
        myReset = false;
        myPanelNumber = -3;
        myStartButton = new JButton("START");
        myResetButton = new JButton("RESTART");
        myQuitButton = new JButton("Quit");
        myTitle = new JLabel(icon);
        myButtonPanel = new JPanel();
        setup();
        start();
    }

    /**
     * This method creates the J Buttons and J Labels.
     */
    public void setup() {
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }
        setBackground(new Color(0, 0, 0)); // Transparent black background
        myButtonPanel.setBackground(new Color(0, 0, 0)); // Set the panel's background same as frame

        myStartButton.setPreferredSize(new Dimension(120, 60));
        myStartButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myStartButton.setForeground(Color.WHITE);
        myStartButton.setBackground(new Color(0, 0, 0));
        myStartButton.setBorderPainted(false);

        myResetButton.setPreferredSize(new Dimension(120, 60));
        myResetButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myResetButton.setForeground(Color.WHITE);
        myResetButton.setBackground(new Color(0, 0, 0));
        myResetButton.setBorderPainted(false);

        myQuitButton.setPreferredSize(new Dimension(120, 60));
        myQuitButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myQuitButton.setForeground(Color.WHITE);
        myQuitButton.setBackground(new Color(0, 0, 0));
        myQuitButton.setBorderPainted(false);

        myButtonPanel.add(myStartButton);
        myButtonPanel.add(myResetButton);
        myButtonPanel.add(myQuitButton);
        myButtonPanel.setLayout(new GridLayout(3, 1));

        setLayout(new GridLayout(2,1));
        add(myTitle);
        add(myButtonPanel);
    }

    /**
     * This method creates the action listener for JButtons.
     */

    public void start() {
        // if start button clicks then reset flag will be false and myPanel Number will be zero.
        myStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 0;
                myReset = false;
            }

        });
        // if reset button clicks then reset flag will be true and myPanel Number will be zero.
        myResetButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 0;
                myReset = true;
            }

        });
        // if quick button clicks then it will quit the game.
        myQuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }

        });
    }

    /**
     * Return the boolean flag for Reset Button.
     * @return the boolean value for myReset.
     */
    public boolean restartGame() {
        return myReset;
    }

    /**
     * Return the myPanelNumber that send the current Action Listener Value.
     * @return the current Action Listenenr Value.
     */
    public int getMyNum(){
        int temp = myPanelNumber;
        // reset the myPanelNumber.
        myPanelNumber = -1;
        return temp;
    }
}