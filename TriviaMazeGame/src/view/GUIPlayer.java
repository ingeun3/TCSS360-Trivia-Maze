package view;

import javax.swing.*;
import java.awt.*;

public class GUIPlayer {
    // Class Constants

    private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Tile size is set for 48x48.

    private static int myTileNumber;
    // Tile size is set for 48x48.
    private static int myTileSize;

    // The movement speed of the player.
    private static int mySpeed;
    // The Image of a player facing up.
    private static final ImageIcon up = new ImageIcon("up.png");
    // The Image of a player facing down.
    private static final ImageIcon down = new ImageIcon("down.png");
    // The Image of a player facing left.
    private static final ImageIcon left = new ImageIcon("left.png");
    // The Image of a player facing right.
    private static final ImageIcon right = new ImageIcon("right.png");

    private final int myGap;



    // Class Fields

    // The x, and y location of the player.
    private static int x;
    private static int y;
    // The direction the player is facing.
    private static String direction;
    // The current image of a player.
    private static ImageIcon myImage;


    public GUIPlayer(Point theSpawnPoint, int theTileNum) {
        myTileNumber = theTileNum;
        myTileSize = (int) screenSize.getWidth() / myTileNumber;
        mySpeed = myTileSize;
        myGap = (int) (screenSize.getWidth() - (myTileSize * theTileNum)) / 2;
        System.out.println("before return" + myGap);
        x = (int) theSpawnPoint.getX() * myTileSize + myGap;
        y = (int) theSpawnPoint.getY() * myTileSize;
        direction = "up";
        myImage = new ImageIcon("up.png");
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
        return mySpeed;
    }

    public int getTileSize() {
        return myTileSize;
    }

    public int getGap() {
        return myGap;
    }

    public Point getsize(){
        return new Point(screenSize.width, screenSize.height);
    }

    /**
     * Draws the Player GUI
     *
     * @param theGraphics the Graphics object to draw on the JPanel.
     */

    //maybe random number generator for multiple direction images so it looks like character is moving
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
        theGraphics.drawImage(myImage.getImage(), x, y, myTileSize , myTileSize, null);
    }
}
