package controller;

import model.Maze;
import model.Player;
import view.GameInterface;
import view.GamePanel;
import view.NorthPanel;
import view.QuestionPane;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;

public class GameLoop {
    private Player myPlayer;
    private GamePanel myGamePanel;
    private GameInterface myGameInterface;
    private Maze myMaze;
    private Controller keyH;
    public Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    // Tile size is set for 48x48.
    public int screenWidth = (int) screenSize.getWidth() / 15;
    // The movement speed of the player.

    private QuestionPane myQuestion;
    public GameLoop(final int theMove, final int theLevel, final String theMap) throws FileNotFoundException {
        keyH = new Controller(theMap, theMove, theLevel);
        myMaze = new Maze(theMap);
        myPlayer = new Player(theMove, theMap);
        myGameInterface = new GameInterface(1,theMove, myMaze.getArray(), myPlayer);
        myGamePanel = GamePanel.getInstance(myMaze.getArray(), myPlayer);
        myGamePanel.addKeyListener(keyH);
        start();
    }
    public void start() {
        myGameInterface.start();
        myGamePanel.start();
        while(true) {
            myGamePanel.run();
        }
    }
}
