package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.Maze;
import model.Player;
import view.GUIPlayer;

public class keyBoardHandler implements KeyListener{

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean keyPressed;
    private Player myPlayer;
    private GUIPlayer mySprite = GUIPlayer.getInstance();
    private static keyBoardHandler instance;
    private static Maze myMaze;
    private keyBoardHandler() {
        // Private constructor to prevent direct instantiation.
    }

    /**
     * Method to get the singleton instance of the keyBoardHandler class.
     * If the instance does not exist, it will be created.
     *
     * @return the singleton instance of the keyBoardHandler class.
     */
    public static keyBoardHandler getInstance(Maze theMaze) {
        if (instance == null) {
            myMaze = theMaze;
            instance = new keyBoardHandler();
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode(); // number of the key that processed
        if (code == KeyEvent.VK_W) {
            upPressed = true;
            update();
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
            update();
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
            update();
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
            update();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W)   {
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

    /**
     * Updates the location of the player by the direction the player is facing.
     */
    public void update() {
        //if (myPlayer.canMove(Terrain.valueOf(myMaze.)) == true) {
        if (getKey() == "up" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerS()))) {
            mySprite.setDirection("up");
            mySprite.setY(mySprite.getY() - mySprite.getSpeed()/2);
        } else if (getKey() == "down" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerS()))) {
            mySprite.setDirection("down");
            mySprite.setY(mySprite.getY() + mySprite.getSpeed()/2);
        } else if (getKey() == "left" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerS()))) {
            mySprite.setDirection("left");
            mySprite.setX(mySprite.getX() - mySprite.getSpeed()/2);
        } else if (getKey() == "right" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerS()))) {
            mySprite.setDirection("right");
            mySprite.setX(mySprite.getX() + mySprite.getSpeed()/2);
        }
        //  }
    }
}
