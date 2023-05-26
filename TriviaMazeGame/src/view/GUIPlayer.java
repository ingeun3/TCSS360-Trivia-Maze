package view;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

public class GUIPlayer {
    // Class Constants

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Tile size is set for 48x48.
    private static final int TILE_SIZE = (int) screenSize.getWidth() / 15; // change 18 or 14
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

    // Class Fields

    // The x, and y location of the player.
    private int x, y;
    // The direction the player is facing.
    private String direction;
    // The current image of a player.
    private ImageIcon myImage;

    // Singleton instance
    private static GUIPlayer instance;

    private Controller keyH;

    private GUIPlayer() {
        x = TILE_SIZE + 5;
        y = TILE_SIZE;
        direction = "right";
        myImage = new ImageIcon("right.png");
    }

    public static GUIPlayer getInstance() {
        if (instance == null) {
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
