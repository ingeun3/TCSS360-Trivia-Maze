package tests;

import model.Maze;
import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    // The player object that will be used for testing.
    private Player myPlayer;

    private Maze myMaze;
    // Initialize myPlayer object with 5 moves before each test.
    @BeforeEach
    void setUp() throws FileNotFoundException {
        myMaze = new Maze("testing_map.txt");
            myPlayer = new Player(5, myMaze);
    }

    // Move Player one tile above starting point
    @Test
    void isPlayerMovingNorth() {
        Point exected = new Point(6, 11);
        Point actual = myPlayer.PlayerN();
        assertEquals(exected, actual);
    }
    // Move Player one tile above then move it back to starting point
    @Test
    void isPlayerMovingSouth() {
        myPlayer.movePlayer(new Point(6, 11));
        Point exected = new Point(6, 12);
        Point actual = myPlayer.PlayerS();
        assertEquals(exected, actual);
    }
    // Move Player one tile left of the starting point
    @Test
    void isPlayerMovingWest() {
        Point exected = new Point(5, 12);
        Point actual = myPlayer.PlayerW();
        assertEquals(exected, actual);
    }
    // Move Player one tile right of the starting point
    @Test
    void isPlayerMovingEast() {
        Point exected = new Point(7, 12);
        Point actual = myPlayer.PlayerE();
        assertEquals(exected, actual);
    }

    // Tests canMove method on Wall.
    @Test
    void canMoveWallTest() {
        assertEquals(false, myPlayer.canMove(new Point(0,0)));
    }
    // Tests canMove method on Road.
    @Test
    void canMoveRoadTest() {
        assertEquals(true, myPlayer.canMove(new Point(1,1)));
    }

    // Tests canMove method on Start Point.
    @Test
    void canMoveStartPointTest() {
        assertEquals(true, myPlayer.canMove(new Point(6,12)));
    }
    // Tests canMove method on End Point.
    @Test
    void canMoveEndPointTest() {
        assertEquals(true, myPlayer.canMove(new Point(6,0)));
    }
    // Tests isAlive method with myPlayer object with 5 different legal moves.
    @Test
    void isAlive0RemainingMoveTest() {
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));

        assertEquals(false, myPlayer.isAlive());
    }
    // Tests isAlive method with myPlayer object with 4 different legal moves and 1 illegal move.
    @Test
    void isAlive1RemainingMoveTest() {
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(0,0));

        assertEquals(true, myPlayer.isAlive());
    }
    // Tests isAlive method with myPlayer object with 2 legal moves with 3 illegal moves.
    @Test
    void isAlive3RemainingMoveTest() {
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(1,1));
        myPlayer.canMove(new Point(0,0));

        assertEquals(true, myPlayer.isAlive());
    }
    // Tests isAlive method with myPlayer object with 6 illegal moves.
    @Test
    void isAlive5RemainingMoveTest() {
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(0,0));
        myPlayer.canMove(new Point(0,0));

        assertEquals(true, myPlayer.isAlive());
    }
    // Checks if isQuestionPoint works on a question point
    @Test
    void isQuestionPoint() {
        Point testPoint = myMaze.getQuestionPoints().get(0);
        myPlayer.movePlayer(testPoint);

        assertEquals(true, myPlayer.isQuestionPoint());
    }
    // Checks if isQuestionPoint works on a question point
    @Test
    void isQuestionPoint2() {
        Point testPoint = myMaze.getQuestionPoints().get(1);
        myPlayer.movePlayer(testPoint);

        assertEquals(true, myPlayer.isQuestionPoint());
    }
    // Checks if it's a question point on a location that shouldn't be a question point
    @Test
    void isNotQuestionPoint() {
        Point testPoint = new Point(1,1);
        myPlayer.movePlayer(testPoint);
        assertEquals(false, myPlayer.isQuestionPoint());
    }
    // Checks if it's a question point on a location that shouldn't be a question point
    @Test
    void isNotQuestionPoint2() {
        Point testPoint = new Point(1,11);
        myPlayer.movePlayer(testPoint);

        assertEquals(false, myPlayer.isQuestionPoint());
    }
}
