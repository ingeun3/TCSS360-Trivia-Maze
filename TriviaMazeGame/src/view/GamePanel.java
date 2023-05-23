package view;

import controller.keyBoardHandler;
import model.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable {


    // The serial Version ID.
    private static final long serialVersionUID = 1L;
    // The FPS of the game is set 60.
    private static final int FPS = 60; // FPS 60 times

    // Class Fields

    char[][] myMazeArray;

    // The Map Object that contains the graphic of the map
    private final MazeMap myMazemap;
    // The thread.
    private Thread gameThread;

    keyBoardHandler keyH = new keyBoardHandler();


    // The Player object that contains graphic of the player.
    GUIPlayer myPlayerGUI;

    // The Level that will display in the center of the NorthPanel.
    private JLabel myLevel;
    // The number of remaining moves display in the right of the NorthPanel.
    private JLabel myMoves;

    private JPanel myHUD;

    private JPanel myNorthPanel;

    /**
     * The default constructor for GamePanel object
     * @param theArray the 2D array representation of the map that GamePanel will draw.
     */
    public GamePanel(char[][] theArray, Player thePlayer, String theLevel, String theMoves) {
       // this.setPreferredSize(new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight()));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        myHUD = new JPanel();
        myHUD.setVisible(true);
        myLevel = new JLabel(theLevel,  SwingConstants.CENTER);
        myMoves = new JLabel(theMoves,  SwingConstants.CENTER);
        // The 2D Array of the map layout.
        myMazemap = new MazeMap(this, myMazeArray);
        myPlayerGUI = new GUIPlayer(this, keyH, thePlayer, myMazeArray);
        start();
    }

    /**
     * Initializing GamePanel object.
     */
    public void start() {
        this.addKeyListener(keyH);
        this.setFocusable(true); //???




        run();
        startGameThread();
        myHUD.add(myLevel);
        myHUD.add(myMoves);
        this.add(myHUD, BorderLayout.NORTH);
        //playMusic(0);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Request focus when the panel is clicked
                requestFocusInWindow();
            }
        });

    }

    /**
     * Starts the thread of the program.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main logic that updates the GamePanel.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null) {
            currentTime = System.nanoTime();
            //how much time has passed
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            // when delta reach draw interval, we update
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;

            }
            //When timer reach 1 sec.
            if (timer >= 1000000000) {
                drawCount = 0;
                timer = 0;
            }


        }
    }

    /**
     * Updates player position
     */
    public void update() {
        myPlayerGUI.update();
    }

    /**
     * paintComponent method that draws the player.
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void paintComponent(Graphics theGraphics) {
        super.paintComponent(theGraphics);// you need to type i this whenever you do it
        Graphics2D g2 = (Graphics2D) theGraphics; // graphics to graphics 2D
        myMazemap.draw(g2);
        myPlayerGUI.draw(g2);
        g2.dispose();

    }
}
