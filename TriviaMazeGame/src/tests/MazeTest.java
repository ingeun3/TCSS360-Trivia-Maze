package tests;

import model.Maze;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
	// The maze object that will be used for testing.
	private Maze myMaze;

	@BeforeEach
	void setUp() throws FileNotFoundException {
		myMaze = new Maze("./resources/maps/testing_map.txt");
	}

	// Tests createMaze method on one of the maps.
	@Test
	void createMazeActualMapTest() throws FileNotFoundException {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
		char[][] actual = myMaze.createMaze("./resources/maps/testing_map.txt");
		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];


				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Tests createMaze method on the 4x4 map with all walls.
	@Test
	void createMaze4x4AllWallTest() throws FileNotFoundException {
		myMaze = new Maze("./resources/maps/4x4AllWallTest.txt");
		char[][] expected = {{'@','@','@','@'},
							{'@','@','@','@'},
							{'@','@','@','@'},
							{'@','@','@','@'}};
		char[][] actual = myMaze.createMaze("./resources/maps/4x4AllWallTest.txt");
		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Tests createMaze method on the 4x4 map with all roads.
	@Test
	void createMaze4x4AllRoadTest() throws FileNotFoundException {
		myMaze = new Maze("./resources/maps/4x4AllRoadTest.txt");
		char[][] expected = {{'+','+','+','+'},
							{'+','+','+','+'},
							{'+','+','+','+'},
							{'+','+','+','+'}};
		char[][] actual = myMaze.createMaze("./resources/maps/4x4AllRoadTest.txt");
		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Tests createMaze method on the 1x1 map with all walls.
	@Test
	void createMaze1x1AllWallTest() throws FileNotFoundException {
		myMaze = new Maze("./resources/maps/1x1AllWallTest.txt");
		char[][] actual = myMaze.createMaze("./resources/maps/1x1AllWallTest.txt");
		String expectedMap = "@";
		String actualMap = "";
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual[i].length; j++) {
				actualMap += actual[i][j];
			}
		}
		assertEquals(expectedMap, actualMap);
	}
	// Tests createMaze method on the 1x1 map with all roads.
	@Test
	void createMaze1x1AllRoadTest() throws FileNotFoundException {
		myMaze = new Maze("./resources/maps/1x1AllRoadTest.txt");
		char[][] actual = myMaze.createMaze("./resources/maps/1x1AllRoadTest.txt");
		String expectedMap = "+";
		String actualMap = "";
		for (int i = 0; i < actual.length; i++) {
			for (int j = 0; j < actual.length; j++) {
				actualMap += actual[i][j];
			}
		}
		assertEquals(expectedMap, actualMap);
	}
	// Tests createMaze method on the 10x10 map with roads and wall oriented in alternating order.
	@Test
	void createMaze10x10Test() throws FileNotFoundException {
		String expectedMap = "";
		myMaze = new Maze("./resources/maps/10x10Test.txt");
		char[][] actual = myMaze.createMaze("./resources/maps/10x10Test.txt");
		String actualMap = "";
		for(int i = 0; i < actual.length; i++) {
			for(int j = 0; j < actual.length; j++) {
				if(i % 2 == 0 && j % 2 == 0) {
					expectedMap += '@';
				} else if(i % 2 == 0 && j % 2 == 1) {
					expectedMap += '+';
				} else if(i % 2 == 1 && j % 2 == 0) {
					expectedMap += '+';
				} else {
					expectedMap += '@';
				}
				actualMap += actual[i][j];
			}
			actualMap += "\n";
			expectedMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on a top left wall.
	@Test
	void setArrayOnWallNWTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
		myMaze.setPlayerLocation(new Point(0,0));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on a top right wall.
	@Test
	void setArrayOnWallNETest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(12,0));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on a bottom left wall.
	@Test
	void setArrayOnWallSWTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(0,12));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on a bottom right wall.
	@Test
	void setArrayOnWallSETest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(12,12));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on top left most road of the map.
	@Test
	void setArrayOnRoadNWTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','M','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(1,1));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}

	// Setting player on top right most road of the map.
	@Test
	void setArrayOnRoadNETest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','M','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(11,1));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on bottom left most road of the map.
	@Test
	void setArrayOnRoadSWTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','M','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(1,11));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on bottom right most road of the map.
	@Test
	void setArrayOnRoadSETest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','M','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(11,11));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Moving player road to road twice.
	@Test
	void setArrayTwiceTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','M','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(1,1));
		myMaze.setPlayerLocation(new Point(2,1));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on the starting location of the map.
	@Test
	void setArrayStartTest() {
		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(6,12));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Setting player on the end location of the map.
	@Test
	void setArrayEndTest() {
		char[][] expected = {{'@','@','@','@','@','@','M','@','@','@','@','@','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};

		myMaze.setPlayerLocation(new Point(6,0));
		char[][] actual = myMaze.getArray();

		String expectedMap = "";
		String actualMap = "";
		for (int i = 0; i < expected.length; i++) {
			for (int j = 0; j < expected[i].length; j++) {
				expectedMap += expected[i][j];
				actualMap += actual[i][j];
			}
			expectedMap += "\n";
			actualMap += "\n";
		}
		assertEquals(expectedMap, actualMap);
	}
	// Testing charAt method on a top left corner wall
	@Test
	void charAtNWWallTest() {
		char expected = '@';
		assertEquals(expected, myMaze.charAt(0,0));
	}
	// Testing charAt method on a top right corner wall
	@Test
	void charAtNEWallTest() {
		char expected = '@';
		assertEquals(expected, myMaze.charAt(12,0));
	}
	// Testing charAt method on a bottom left corner wall
	@Test
	void charAtSWWallTest() {
		char expected = '@';
		assertEquals(expected, myMaze.charAt(0,12));
	}
	// Testing charAt method on a bottom right corner wall
	@Test
	void charAtSEWallTest() {
		char expected = '@';
		assertEquals(expected, myMaze.charAt(12,12));
	}
	// Testing charAt method on a top left corner road
	@Test
	void charAtNWRoadTest() {
		char expected = '+';
		assertEquals(expected, myMaze.charAt(1,1));
	}
	// Testing charAt method on a top right corner road
	@Test
	void charAtNERoadTest() {
		char expected = '+';
		assertEquals(expected, myMaze.charAt(1,11));
	}
	// Testing charAt method on a bottom left corner road
	@Test
	void charAtSWRoadTest() {
		char expected = '+';
		assertEquals(expected, myMaze.charAt(11,1));
	}
	// Testing charAt method on a bottom right corner road
	@Test
	void charAtSERoadTest() {
		char expected = '+';
		assertEquals(expected, myMaze.charAt(11,11));
	}

	// Testing intersections method on maze_map1.
	@Test
	void intersectionsTest() throws FileNotFoundException {
		List<Point> expected = new ArrayList<Point>();
		expected.add(new Point(6,1));
		expected.add(new Point(3,2));
		expected.add(new Point(5,2));
		expected.add(new Point(11,4));
		expected.add(new Point(7,7));
		expected.add(new Point(5,9));
		expected.add(new Point(6,11));
		expected.add(new Point(9,11));
		char[][] map = myMaze.createMaze("./resources/maps/testing_map.txt");
		List<Point> actual = myMaze.intersections(map);
		assertEquals(expected, actual);
	}
}