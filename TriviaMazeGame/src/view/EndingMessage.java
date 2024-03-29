package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * This EndingMessage object is a JFrame that will appear when ending condition for the game is met.
 * @author Kevin Truong, Ingeun Hwang, Khin Win
 */
public class EndingMessage extends JFrame {
    /**
     * Dimension of a screen.
     */
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    /**
     * Prompt for Winning message.
     */
    private static final String WINNING_MESSAGE = "GOOD JOB";
    /**
     * Prompt for Losing message.
     */
    private static final String LOSING_MESSAGE = "LOL YOU SUCK";
    /**
     * Prompt for Completing all the levels.
     */
    private static final String COMPLETE_MESSAGE = "CONGRATS!";
    /**
     * Initialize OptionSelectedListener that listens for the user's input.
     */
    private OptionSelectedListener optionSelectedListener;

    /**
     * Constructor for to EndingMessage object
     * @param theResult the integer value that represents one of the 3 ending condition.
     */
    public EndingMessage(final int theResult) {
        super();

        Font messageFont;
        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            assert is != null;
            messageFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        JLabel label = new JLabel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(0, 0, 0, 0)); // Set the panel's background same as frame

        JButton playButton = new JButton("PLAY AGAIN");
        JButton levelButton = new JButton("GO TO LEVELS");
        JButton nextButton = new JButton("NEXT LEVEL");

        playButton.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        playButton.setForeground(Color.WHITE);
        playButton.setBorderPainted(false);
        playButton.setOpaque(true);
        playButton.setBackground(new Color(0, 0, 0));

        levelButton.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        levelButton.setForeground(Color.WHITE);
        levelButton.setOpaque(true);
        levelButton.setBorderPainted(false);
        levelButton.setBackground(new Color(0, 0, 0));

        nextButton.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        nextButton.setForeground(Color.WHITE);
        nextButton.setOpaque(true);
        nextButton.setBorderPainted(false);
        nextButton.setBackground(new Color(0, 0, 0));

        if (theResult == 1) {
            label.setText(COMPLETE_MESSAGE);
            buttonPanel.add(playButton);
            buttonPanel.add(levelButton);
        } else if (theResult == 2) {
            label.setText(WINNING_MESSAGE);
            buttonPanel.add(playButton);
            buttonPanel.add(nextButton);
        } else if (theResult == 3) {
            label.setText(LOSING_MESSAGE);
            buttonPanel.add(playButton);
            buttonPanel.add(levelButton);
        }

        label.setFont(messageFont.deriveFont(Font.PLAIN, 45));
        label.setForeground(new Color(255, 255, 255, 200));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        playButton.addActionListener(e -> {
            if (optionSelectedListener != null) {
                optionSelectedListener.onOptionSelected(1);
            }
            dispose();
        });

        levelButton.addActionListener(e -> {
            if (optionSelectedListener != null) {
                optionSelectedListener.onOptionSelected(2);
            }
            dispose();
        });

        nextButton.addActionListener(e -> {
            if (optionSelectedListener != null) {
                optionSelectedListener.onOptionSelected(3);
            }
            dispose();
        });

        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        playButton.setFocusable(false);
        levelButton.setFocusable(false);
        nextButton.setFocusable(false);

        setPreferredSize(new Dimension((int) screenSize.getWidth(),(int) screenSize.getHeight() * 9 / 10));
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 200)); // Transparent black background
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    /**
     * Sets the optionSelectedListener to the button that Player clicked.
     * @param theListener the Listener that will listen for the Player's input.
     */
    public void setOptionSelectedListener(final
                                          OptionSelectedListener theListener) {
        this.optionSelectedListener = theListener;
    }

    /**
     * Interface for OptionSelectedListener.
     */
    public interface OptionSelectedListener {
        void onOptionSelected(int option);
    }
}
