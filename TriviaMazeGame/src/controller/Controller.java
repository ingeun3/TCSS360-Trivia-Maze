package controller;

import model.Maze;
import model.Player;
import view.GameInterface;
import view.GamePanel;
import view.QuestionPane;

import java.awt.*;
import java.io.FileNotFoundException;

public class Controller {
    private Player myPlayer;
    private GamePanel myGamePanel;
    private GameInterface myGameInterface;
    private Maze myMaze;
    private keyBoardHandler keyH;

    private QuestionPane myQuestion;
    public Controller (final int move, final String theMap, QuestionPane theQuestion) throws FileNotFoundException {
        myMaze = new Maze(theMap);
        myPlayer = new Player(move);
        myQuestion = theQuestion;
        myGameInterface = new GameInterface(1,10, myMaze.getArray(), myPlayer);
        myGamePanel = GamePanel.getInstance(myMaze.getArray(), myPlayer);
        keyH = keyBoardHandler.getInstance();
        start();
    }
    public void start() {
        myGameInterface.start();
        myGamePanel.start();



        while(myPlayer.getLivingStatus()) {
//            if (keyH.getKey() == "up") {
//                System.out.println("hi");
//                myGamePanel.run();
//            } else if (keyH.getKey() == "down" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerS()))) {
//                myGamePanel.run();
//            } else if (keyH.getKey() == "left" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerW()))) {
//                myGamePanel.run();
//            } else if (keyH.getKey() == "right" && myPlayer.canMove(myMaze.getTerrain(myMaze.PlayerE()))) {
//                myGamePanel.run();
//            }
            myGamePanel.run();
//            System.out.println(myPlayer.getLocation());
            if (myPlayer.getLocation().equals(new Point(1, 2))) {
                myQuestion.ask();
            }
        }
    }
}
