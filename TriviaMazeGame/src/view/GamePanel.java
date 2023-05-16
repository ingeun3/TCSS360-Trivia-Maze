package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    // 16 by 16 for Tiles for screen setting.
    final int originalTileSize = 16;
    final int scale = 3;
    //48 * 48 tiles
    public final int tileSize = originalTileSize * scale;
    public final int maxScreenColumn = 16; // change 18 or 14
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; //768 pixels
    public final int screenHeight = tileSize * maxScreenRow; //576
    // NorthPanel mynorthPanel;
    Thread gameThread;
    char[][] myMazeArray;
    // set player default position;
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4; // 4pixels increased or decreased
    int FPS = 60; // FPS 60 times
    keyBoardHandler keyH = new keyBoardHandler();
    GUIPlayer player = new GUIPlayer(this, keyH);
    //TileManager tileM = new TileManager(this,myMazeArray  );
    MazeMap myMazemap;
    NorthPanel myNorthPanel;
    //world setting
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol; //as you like
    public final int worldHeight = tileSize * maxWorldRow;
    public GamePanel(String theLevel, String theMove, char[][] theArray) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        myMazeArray = theArray;
        //mynorthPanel = new NorthPanel (theLevel, theMove);

        myMazemap = new MazeMap(this, myMazeArray);
        myNorthPanel = new NorthPanel(this, theLevel, theMove);
        start();

    }

    public void start() {
        //this.setLayout(new GridLayout(2, 1));
        //this.add(myNorthPanel, BorderLayout.NORTH);
        //this.add(myMazemap, FlowLayout.CENTER);
        this.addKeyListener(keyH);
        this.setFocusable(true); //???
        this.requestFocus();
        run();
        startGameThread();
        //this.setPreferredSize(new Dimension(520,520));
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

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
                //System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }


        }
    }
    //change player position, I need to add the
    //code that check player can move it or not.
    // need to connect with player class.
    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);// you need to type i this whenever you do it
        Graphics2D g2 = (Graphics2D) g; // graphics to graphics 2D
        //tileM.draw(g2); // make sure to type this one before player.
        //player.draw(g2);
        myMazemap.draw(g2);
        player.draw(g2);
        //g2.fillRect(0, 0, screenWidth, screenHeight);
        g2.dispose();

    }
}
