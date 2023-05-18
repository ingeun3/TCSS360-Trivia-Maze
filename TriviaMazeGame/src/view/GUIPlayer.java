package view;


import controller.keyBoardHandler;

import javax.swing.*;
import java.awt.*;


    public class GUIPlayer  extends Entity {
        private GamePanel gp;

        private final int screenX;
        private final int screenY;

        private int x, y;
        private int speed;
        private ImageIcon up, down, left,  right;
        private String direction;

        private ImageIcon myImage;

        public GUIPlayer(GamePanel gp) {

            this.gp = gp;
            setDefaultValues();
            getPlayerImage();
            screenX = gp.screenWidth/2;
            screenY = gp.screenHeight/2;
        }

        public void setDefaultValues() {
            x = 100;
            y = 100;
            speed = 4;
            direction = "static";
            myImage = new ImageIcon("right.png");
        }
        public void getPlayerImage() {
            up = new ImageIcon("up.png");
            down = new ImageIcon("down.png");
            left = new ImageIcon("left.png");
            right = new ImageIcon("right.png");

        }
        public void update() {
            //we can change it, if we don't like the player body to kepp
            // shaking even does not hit anykeys then
            // we will do
            // if (keyH.upPressed == true || keyH.downPressed == true \\ keyH.leftPressed == true)
            // keyH.rightPressed == true)
            // then we will move every thing inside this if statement.
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
            //spriteCounter++; this update method call 60 times per sec.
            //if(spriteCounter > 12) // you can change whatever you like
            // if (spriteNum ==1){
            //sprteNum =2;
            // else if(spriteNum ==2)
            //spriteNum =1;

            //reset spriteCounter = 0;
        }

        public void draw(Graphics2D g2) {

//		g2.setColor(Color.WHITE);
//		//rectangle
//		g2.fillRect(x, y, gp.tileSize, gp.tileSize);

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
            g2.drawImage(myImage.getImage(), x, y, gp.tileSize, gp.tileSize, null);

        }




    }


