package view;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitlePanel extends JPanel {

    private static final ImageIcon icon = new ImageIcon("titlename.png");


    private JLabel myTitle;

    private JButton myStartButton;

    private static final int WIDTH  = Toolkit.getDefaultToolkit().getScreenSize().width;

    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JPanel myButtonPanel;

    private int myPanelNumber;

    public TitlePanel() {
        myPanelNumber = -2;
        myStartButton = new JButton("Start");
        myTitle = new JLabel(icon);
        myButtonPanel = new JPanel();
        start();
    }

    public void start() {
        this.setSize(800, 600);
//        myButtonPanel.setLayout(new BoxLayout(myButtonPanel, BoxLayout.PAGE_AXIS));
        myButtonPanel.setBounds(300, 500, 300, 100);
        myButtonPanel.setBackground(Color.black);
        myButtonPanel.add(myStartButton);

        this.add(myTitle);

        this.add(myButtonPanel);

        myStartButton.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                System.out.println("hi");
                myPanelNumber = 0;
            }

        });
    }
    public int getMyNum(){
        int temp = myPanelNumber;
        myPanelNumber = -1;
        return temp;
    }

}
