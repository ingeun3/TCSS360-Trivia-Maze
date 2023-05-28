package tests;

import model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    // The player object that will be used for testing.
    private Player myPlayer;

    // Initialize myPlayer object with 5 moves before each test.
    @BeforeEach
    void setUp() throws FileNotFoundException {
            myPlayer = new Player(5, "maze_map1.txt");
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
}
