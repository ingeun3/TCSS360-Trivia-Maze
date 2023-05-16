package view;


import javax.swing.*;
import java.awt.*;


    public class GUIPlayer  extends Entity {
        GamePanel gp;
        keyBoardHandler keyH;

        public final int screenX;
        public final int screenY;
        public GUIPlayer(GamePanel gp, keyBoardHandler keyH) {

            this.gp = gp;
            this.keyH = keyH;
            setDefaultValues();
            getPlayerImage();
            screenX = gp.screenWidth/2;
            screenY = gp.screenHeight/2;
            //screenX = gp.screenWidth/2 - (gp.tileSize/2);
            //screenY = gp.screenHeight/2 - (gp.tileSize/2);
        }

        public void setDefaultValues() {
            //worldX = gp.tileSize * 9; worldY = gp.tileSize * 1;
            x = 100;
            y = 100;
            speed = 4;
            direction = "down";
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
            if (keyH.upPressed == true) {
                direction = "up";
                //worldY  = worldY - speed;
                y = y - speed;
            }
            else if (keyH.downPressed == true) {
                direction = "down";
                //worldY += speed;
                y += speed;
            }
            else if (keyH.leftPressed == true) {
                direction = "left";
                //worldX -= speed;
                x -= speed;
            }
            else if (keyH.rightPressed == true) {
                direction = "right";
                //worldX += speed;
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

            ImageIcon image = null;
            switch(direction) {
                case "up" :
                    //if (spriteNum ==1){
                    // image = up1;
                    // if (spriteNum ==2)
                    //image = up2;
                    //break;
                    image = up;
                    break;
                case "down" :
                    //if (spriteNum ==1){
                    // image = down;
                    // if (spriteNum ==2)
                    //image = down2; keep doing like this
                    image = down;
                    break;
                case "left" :
                    image = left;
                    break;
                case "right" :
                    image = right;
                    break;
            }
            //g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            g2.drawImage(image.getImage(), x, y, gp.tileSize, gp.tileSize, null);

        }




    }


