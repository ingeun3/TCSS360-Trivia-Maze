package controller;

import model.Maze;
import model.Player;
import view.GameInterface;
import view.GamePanel;
import view.QuestionPane;

import javax.swing.*;
import java.io.FileNotFoundException;

public class GameLoop {
    private Player myPlayer;
    private GamePanel myGamePanel;
    private GameInterface myGameInterface;
    private Maze myMaze;
    private Controller keyH;

    private QuestionPane myQuestion;
    public GameLoop(final int theMove, final String theMap) throws FileNotFoundException {
        keyH = new Controller(theMap, theMove);
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
