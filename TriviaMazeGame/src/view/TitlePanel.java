package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class TitlePanel extends JPanel {

    private static final ImageIcon icon = new ImageIcon("titlename.png");

    private JLabel myTitle;
    private JButton myStartButton;
    private JButton myAboutButton;
    private JButton myQuitButton;
    private static final int WIDTH  = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private JPanel myButtonPanel;
    private JLabel myBlankSpace;
    private JLabel myBlankSpace2;
    private int myPanelNumber;

    private Font myFont;

    public TitlePanel() {
        myPanelNumber = -1;
        myStartButton = new JButton("Start");
        myAboutButton = new JButton("About");
        myQuitButton = new JButton("Quit");
        myTitle = new JLabel(icon);
        myButtonPanel = new JPanel();
        setup();
        start();
    }

    public void setup() {

        try {
            InputStream is = getClass().getResourceAsStream("smalle.ttf");
            myFont = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException | IOException e) {
            throw new RuntimeException(e);
        }

        setBackground(new Color(0, 0, 0)); // Transparent black background
        myButtonPanel.setBackground(new Color(0, 0, 0, 200)); // Set the panel's background same as frame

//        myButtonPanel.setBounds(300, 500, 300, 100);
        myStartButton.setPreferredSize(new Dimension(120, 60));
        myStartButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myStartButton.setForeground(Color.WHITE);
        myStartButton.setBackground(new Color(0, 0, 0));
//        myStartButton.setOpaque(false);
        myAboutButton.setPreferredSize(new Dimension(120, 60));
        myAboutButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myAboutButton.setForeground(Color.WHITE);
        myAboutButton.setBackground(new Color(0, 0, 0));
//        myAboutButton.setOpaque(false);
        myQuitButton.setPreferredSize(new Dimension(120, 60));
        myQuitButton.setFont(myFont.deriveFont(Font.PLAIN, 45));
        myQuitButton.setForeground(Color.WHITE);
        myQuitButton.setBackground(new Color(0, 0, 0));
//        myQuitButton.setOpaque(false);
        myButtonPanel.add(myStartButton);
        myButtonPanel.add(myAboutButton);
        myButtonPanel.add(myQuitButton);
        myButtonPanel.setLayout(new GridLayout(3, 1));

        setLayout(new GridLayout(2,1));
        add(myTitle);
//        myButtonPanel.setBackground(Color.black);
        add(myButtonPanel);
    }

    public void start() {
        myStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                myPanelNumber = 0;
            }

        });
        myQuitButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.exit(0);
            }

        });
    }

    public int getMyNum(){
        int temp = myPanelNumber;
        myPanelNumber = -1;
        return temp;
    }
}
