package controller;

import model.Maze;
import model.Player;
import view.GUIPlayer;
import view.GameInterface;
import view.GamePanel;

import java.io.FileNotFoundException;

public class Controller {
    // The graphic of a player
    private static GUIPlayer myPlayerImage;
    // The GamePanel to display the game.
    private static GamePanel myGamePanel;
    // The Maze object that will be used for creating the map.
    private static Maze myMaze;
    // The JFrame that will be used as a base of GUI.
    private static GameInterface myInterface;
    // The keyboard listener.
    private keyBoardHandler myKeyInput;
    // The player object.
    private static Player myPlayer;

    /**
     * Default constructor for the Controller object.
     * @param theMaze the Maze object that will be used to create the map
     * @param theGamePanel the GamePanel object that will be used to display the game
     * @param theInterface the JFrame object that will be used as a base of the GUI
     * @param thePlayerImage the Image of the player
     * @throws FileNotFoundException if map file isn't found for Maze object.
     */
    public Controller (Maze theMaze, GamePanel theGamePanel,
                       GameInterface theInterface, GUIPlayer thePlayerImage) throws FileNotFoundException {
        myMaze = theMaze;

        myGamePanel = theGamePanel;

        myInterface = theInterface;

        myPlayerImage = thePlayerImage;

        myPlayer = new Player(10);

        myKeyInput = new keyBoardHandler();

        start();
    }

    /**
     * Main game logic
     */
    public void start() {
        if(myPlayer.getLivingStatus()) {
            myInterface.start();
            myGamePanel.addKeyListener(myKeyInput);
        }
    }


}
