package view;


import controller.keyBoardHandler;

import javax.swing.*;
import java.awt.*;


    public class GUIPlayer {
        // Class Constants

        // Tile size is set for 48x48.
        private static final int TILE_SIZE = 48;
        // The movement speed of the player.
        private static final int speed = 4;
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

        /**
         * The default constructor of the Player GUI.
         */
        public GUIPlayer() {
            x = 100;
            y = 100;
            direction = "static";
            myImage = new ImageIcon("right.png");
        }

        /**
         * Sets the direction of the player.
         * @param theDirection
         */
        public void setDirection(String theDirection) {
            direction = theDirection;
            update();

        }

        /**
         * Updates the location of the player by the direction the player is facing.
         */
        public void update() {
            if (direction == "up") {
                y = y - speed;
            }
            else if (direction == "down") {
                y += speed;
            }
            else if (direction == "left") {
                x -= speed;
            }
            else if (direction == "right") {
                x += speed;
            }
        }

        /**
         * Draws the Player GUI
         * @param theGraphics the Graphics object to draw on the JPanel.=
         */
        public void draw(Graphics2D theGraphics) {

            //ImageIcon image = null;
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
                case "static" :
                    break;

            }
            theGraphics.drawImage(myImage.getImage(), x, y, TILE_SIZE, TILE_SIZE, null);

        }

    }


