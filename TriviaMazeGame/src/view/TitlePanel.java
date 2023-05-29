package view;

import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;

public class TitlePanel extends JPanel {

    private static final ImageIcon icon = new ImageIcon("titlename.png");


    private JLabel myTitle;

    private JButton myStartButton;

    private JButton myLoadButton;

    private JButton myHelpButton;

    private NorthPanel myNorthPanel;


    private static final int WIDTH  = Toolkit.getDefaultToolkit().getScreenSize().width;

    private static final int HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;

    private JPanel myButtonPanel;

    public TitlePanel() {

        myStartButton = new JButton("Start");
        myHelpButton = new JButton("Help");
        myLoadButton = new JButton("Load");
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
        myButtonPanel.add(myHelpButton);
        myButtonPanel.add(myLoadButton);

//        myTitle.setBackground(Color.BLUE);
//        myTitle.setIcon(icon);
//        this.setLayout(new BorderLayout());
        this.add(myTitle);

        this.add(myButtonPanel);
    }

}
