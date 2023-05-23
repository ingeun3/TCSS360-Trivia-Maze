package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;

import model.Maze;
import model.Player;
import view.GUIPlayer;

public class keyBoardHandler implements KeyListener{
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private Player myPlayer = new Player(10);
    private GUIPlayer mySprite = GUIPlayer.getInstance();
    private static keyBoardHandler instance;
    private keyBoardHandler() throws FileNotFoundException {
    }

    /**
     * Method to get the singleton instance of the keyBoardHandler class.
     * If the instance does not exist, it will be created.
     *
     * @return the singleton instance of the keyBoardHandler class.
     */
    public static keyBoardHandler getInstance() throws FileNotFoundException {
        if (instance == null) {
            instance = new keyBoardHandler();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W && !upPressed) {
            upPressed = true;
            update();
        } else if (code == KeyEvent.VK_S && !downPressed) {
            downPressed = true;
            update();
        } else if (code == KeyEvent.VK_A && !leftPressed) {
            leftPressed = true;
            update();
        } else if (code == KeyEvent.VK_D && !rightPressed) {
            rightPressed = true;
            update();
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        } else if (code == KeyEvent.VK_S) {
            downPressed = false;
        } else if (code == KeyEvent.VK_A) {
            leftPressed = false;
        } else if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
    public String getKey() {
        if (upPressed) {
            return "up";
        } else if (downPressed) {
            return "down";
        } else if (leftPressed) {
            return "left";
        } else if (rightPressed) {
            return "right";
        } else {
            return "";
        }
    }
    public void update() {
        String key = getKey();
        if (key.equals("up") && myPlayer.canMove(myPlayer.PlayerN())) {
            mySprite.setDirection("up");
            mySprite.setY(mySprite.getY() - mySprite.getSpeed());
            myPlayer.setMyMove();
        } else if (key.equals("down") && myPlayer.canMove(myPlayer.PlayerS())) {
            mySprite.setDirection("down");
            mySprite.setY(mySprite.getY() + mySprite.getSpeed());
            myPlayer.setMyMove();
        } else if (key.equals("left") && myPlayer.canMove(myPlayer.PlayerW())) {
            mySprite.setDirection("left");
            mySprite.setX(mySprite.getX() - mySprite.getSpeed());
            myPlayer.setMyMove();
        } else if (key.equals("right") && myPlayer.canMove(myPlayer.PlayerE())) {
            mySprite.setDirection("right");
            mySprite.setX(mySprite.getX() + mySprite.getSpeed());
            myPlayer.setMyMove();
        }
    }
}
