package tests;

import model.Player;
import model.Terrain;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {
    // The player object that will be used for testing.
    private Player myPlayer;

    // Initialize myPlayer object with 5 moves before each test.
    @BeforeEach
    void setUp() {
            myPlayer = new Player(5);
    }
    // Tests canMove method on Wall.
    @Test
    void canMoveWallTest() {
        assertEquals(false, myPlayer.canMove(Terrain.WALL));
    }
    // Tests canMove method on Road.
    @Test
    void canMoveRoadTest() {
        assertEquals(true, myPlayer.canMove(Terrain.ROAD));
    }
    // Tests canMove method on Question Point.
    @Test
    void canMoveQuestionPointTest() {
        assertEquals(true, myPlayer.canMove(Terrain.QUESTION_POINT));
    }
    // Tests canMove method on Check Point.
    @Test
    void canMoveCheckPointTest() {
        assertEquals(true, myPlayer.canMove(Terrain.CHECK_POINT));
    }
    // Tests canMove method on Start Point.
    @Test
    void canMoveStartPointTest() {
        assertEquals(true, myPlayer.canMove(Terrain.START_POINT));
    }
    // Tests canMove method on End Point.
    @Test
    void canMoveEndPointTest() {
        assertEquals(true, myPlayer.canMove(Terrain.END_POINT));
    }
    // Tests isAlive method with myPlayer object with 5 different legal moves.
    @Test
    void isAlive0RemainingMoveTest() {
        myPlayer.canMove(Terrain.ROAD);
        myPlayer.canMove(Terrain.QUESTION_POINT);
        myPlayer.canMove(Terrain.START_POINT);
        myPlayer.canMove(Terrain.END_POINT);
        myPlayer.canMove(Terrain.CHECK_POINT);

        assertEquals(false, myPlayer.getLivingStatus());
    }
    // Tests isAlive method with myPlayer object with 4 different legal moves and 1 illegal move.
    @Test
    void isAlive1RemainingMoveTest() {
        myPlayer.canMove(Terrain.ROAD);
        myPlayer.canMove(Terrain.QUESTION_POINT);
        myPlayer.canMove(Terrain.START_POINT);
        myPlayer.canMove(Terrain.END_POINT);
        myPlayer.canMove(Terrain.WALL);

        assertEquals(true, myPlayer.getLivingStatus());
    }
    // Tests isAlive method with myPlayer object with 2 legal moves with 3 illegal moves.
    @Test
    void isAlive3RemainingMoveTest() {
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.ROAD);
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.CHECK_POINT);
        myPlayer.canMove(Terrain.WALL);

        assertEquals(true, myPlayer.getLivingStatus());
    }
    // Tests isAlive method with myPlayer object with 6 illegal moves.
    @Test
    void isAlive5RemainingMoveTest() {
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.WALL);
        myPlayer.canMove(Terrain.WALL);

        assertEquals(true, myPlayer.getLivingStatus());
    }
}
