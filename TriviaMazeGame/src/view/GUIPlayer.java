package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GUIPlayer {
    // Class Constants

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Tile size is set for 48x48.

    public final int screenW = screenSize.width;

    public final int screenH = screenSize.height;

    private static final int TILE_SIZE = (int) screenSize.getWidth() / 15;
    // The movement speed of the player.
    private static final int speed = TILE_SIZE;
    // The Image of a player facing up.
    private static final ImageIcon up = new ImageIcon("up.png");
    // The Image of a player facing down.
    private static final ImageIcon down = new ImageIcon("down.png");
    // The Image of a player facing left.
    private static final ImageIcon left = new ImageIcon("left.png");
    // The Image of a player facing right.
    private static final ImageIcon right = new ImageIcon("right.png");

    Enviroment myEnviro = new Enviroment(this);



    // Class Fields

    // The x, and y location of the player.
    private static int x;
    private static int y;
    // The direction the player is facing.
    private static String direction;
    // The current image of a player.
    private static ImageIcon myImage;

    // Singleton instance
    private static GUIPlayer instance;

    private Controller keyH;
    public int tilesize;

    private GUIPlayer() {

    }

    public static GUIPlayer getInstance(Point theSpawnPoint) {
        if (instance == null) {
            x = (int) theSpawnPoint.getX() * TILE_SIZE;
            y = (int) theSpawnPoint.getY() * TILE_SIZE;
            direction = "up";
            myImage = new ImageIcon("up.png");

            instance = new GUIPlayer();

        }

        return instance;
    }

    public void setDirection(String theDirection) {
        direction = theDirection;
    }

    public void setY(int theY) {
        y = theY;
    }

    public void setX(int theX) {
        x = theX;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getSpeed() {
        return speed;
    }

    public int getTileSize() {
        return TILE_SIZE;
    }

    public int getsize(){
        return tilesize= (int) screenSize.getWidth() / 15;
    }

    public void set(){
        myEnviro.setup();
    }
    /**
     * Draws the Player GUI
     *
     * @param theGraphics the Graphics object to draw on the JPanel.
     */
    public void draw(Graphics2D theGraphics) {
        ImageIcon image = null;
        switch (direction) {
            case "up":
                myImage = up;
                break;
            case "down":
                myImage = down;
                break;
            case "left":
                myImage = left;
                break;
            case "right":
                myImage = right;
                break;
        }
        theGraphics.drawImage(myImage.getImage(), x, y, TILE_SIZE , TILE_SIZE, null);
    }
}
