package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import model.Player;

public class keyBoardHandler implements KeyListener{

    private boolean upPressed, downPressed, leftPressed, rightPressed;

    Player myPlayer;


    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // number of the key that proesssed
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }

    }
    public String getKey() {
        String result = "";
        if(upPressed == true) {
            result = "up";
        } else if (downPressed == true) {
            result = "down";
        } else if (leftPressed == true) {
            result = "left";
        } else if (rightPressed == true){
            result = "right";
        }

        return result;
    }
}
