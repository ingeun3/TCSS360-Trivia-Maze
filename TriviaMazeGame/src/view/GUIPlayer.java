package view;


import controller.keyBoardHandler;
import model.Maze;
import model.Player;
import model.Terrain;

import javax.swing.*;
import java.awt.*;


    public class GUIPlayer {
        // Class Constants

        private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // Tile size is set for 48x48.
        private static final int TILE_SIZE = (int) screenSize.getWidth() / 30; // change 18 or 14
        // The movement speed of the player.
        private static final int speed =6;
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

        Player myPlayer;

        char [][] myMaze;

        GamePanel gp;

        private keyBoardHandler keyH;

        /**
         * The default constructor of the Player GUI.
         */
        public GUIPlayer(GamePanel gp, keyBoardHandler theKey, Player thePlayer, char[][] theMaze) {
            this.gp = gp;
            this.keyH = theKey;
            x = 100;
            y = 100;
            direction = "right";
            myImage = new ImageIcon("right.png");
            myPlayer = thePlayer;
            myMaze = theMaze;
        }

        /**
         * Updates the location of the player by the direction the player is facing.
         */
        public void update() {
            boolean value = true;
            //if (myPlayer.canMove(Terrain.valueOf(myMaze.)) == true) {
                if (keyH.getKey() == "up") {
                    direction = "up";
                    y = y - speed;
                } else if (keyH.getKey() == "down") {
                    direction = "down";
                    y += speed;
                } else if (keyH.getKey() == "left") {
                    direction = "left";
                    x -= speed;
                } else if (keyH.getKey() == "right") {
                    direction = "right";
                    x += speed;
                }
          //  }
        }

        /**
         * Draws the Player GUI
         * @param theGraphics the Graphics object to draw on the JPanel.=
         */
        public void draw(Graphics2D theGraphics) {

            ImageIcon image = null;
            switch(direction) {
                case "up" :
                    myImage = up;
                    break;
                case "down" :
                    myImage = down;
                    break;
                case "left" :
                    myImage = left;
                    break;
                case "right" :
                    myImage = right;
                    break;


            }
            theGraphics.drawImage(myImage.getImage(), x, y, TILE_SIZE, TILE_SIZE, null);

        }

    }


