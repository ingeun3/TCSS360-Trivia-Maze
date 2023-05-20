package view;

import controller.keyBoardHandler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    // Class Constants

    // The serial Version ID.
    private static final long serialVersionUID = 1L;
    //Tile size is set for 48x48.
    private static final int TILE_SIZE = 48;
    // Number of Tiles in Width of the screen
    private static final int SCREEN_WIDTH_TILE = 16; // change 18 or 14
    // Number of Tiles in Height of the screen.
    private static final int SCREEN_HEIGHT_TITLE = 12;
    // The Width of the screen.
    private static final int SCREEN_WIDTH = TILE_SIZE * SCREEN_WIDTH_TILE; //768 pixels
    // The height of the screen.
    private static final int SCREEN_HEIGHT = TILE_SIZE * SCREEN_HEIGHT_TITLE; //576
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
    GUIPlayer myPlayerGUI = new GUIPlayer(this, keyH);

    /**
     * The default constructor for GamePanel object
     * @param theArray the 2D array representation of the map that GamePanel will draw.
     */
    public GamePanel(char[][] theArray) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        // The 2D Array of the map layout.
        myMazemap = new MazeMap(this, myMazeArray);
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
