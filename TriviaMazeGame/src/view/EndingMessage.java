package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class EndingMessage extends JFrame {
    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    private static final String WINNING_MESSAGE = "GOOD JOB";
    private static final String LOSING_MESSAGE = "LOL YOU SUCK";
    private static final String COMPLETE_MESSAGE = "CONGRATS!";
    private Font myFont;
    private OptionSelectedListener optionSelectedListener;

    public EndingMessage(int theResult) {
        super();

        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
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

        playButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        playButton.setForeground(Color.WHITE);
        playButton.setBorderPainted(false);
        playButton.setOpaque(true);
        playButton.setBackground(new Color(0, 0, 0));

        levelButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        levelButton.setForeground(Color.WHITE);
        levelButton.setOpaque(true);
        levelButton.setBorderPainted(false);
        levelButton.setBackground(new Color(0, 0, 0));

        nextButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
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

        label.setFont(myFont.deriveFont(Font.PLAIN, 45));
        label.setForeground(new Color(255, 255, 255, 200));
        label.setHorizontalAlignment(SwingConstants.CENTER);

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionSelectedListener != null) {
                    optionSelectedListener.onOptionSelected(1);
                }
                dispose(); // Close the window
            }
        });

        levelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionSelectedListener != null) {
                    optionSelectedListener.onOptionSelected(2);
                }
                dispose(); // Close the window
            }
        });

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (optionSelectedListener != null) {
                    optionSelectedListener.onOptionSelected(3);
                }
                dispose(); // Close the window
            }
        });

        add(label, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        playButton.setFocusable(false);
        levelButton.setFocusable(false);
        nextButton.setFocusable(false);

        setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight() * 5 / 6));
        setUndecorated(true);
        setBackground(new Color(0, 0, 0, 200)); // Transparent black background
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        pack(); // Pack the components
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void setOptionSelectedListener(OptionSelectedListener listener) {
        this.optionSelectedListener = listener;
    }

    public interface OptionSelectedListener {
        void onOptionSelected(int option);
    }
}
