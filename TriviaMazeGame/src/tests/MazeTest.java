package tests;

import model.Maze;
import org.junit.jupiter.api.Test;


import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MazeTest {
	// The maze object that will be used for testing.
	private Maze testMaze;

	// Tests createMaze method on one of the maps.
//	@Test
//	void createMazeActualMapTest() throws FileNotFoundException {
//		testMaze = new Maze("maze_map1.txt");
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		char[][] actual = testMaze.createMaze("maze_map1.txt");
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//
//
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Tests createMaze method on the 4x4 map with all walls.
//	@Test
//	void createMaze4x4AllWallTest() throws FileNotFoundException {
//		testMaze = new Maze("4x4AllWallTest.txt");
//		char[][] expected = {{'@','@','@','@'},
//							{'@','@','@','@'},
//							{'@','@','@','@'},
//							{'@','@','@','@'}};
//		char[][] actual = testMaze.createMaze("4x4AllWallTest.txt");
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Tests createMaze method on the 4x4 map with all roads.
//	@Test
//	void createMaze4x4AllRoadTest() throws FileNotFoundException {
//		testMaze = new Maze("4x4AllRoadTest.txt");
//		char[][] expected = {{'+','+','+','+'},
//							{'+','+','+','+'},
//							{'+','+','+','+'},
//							{'+','+','+','+'}};
//		char[][] actual = testMaze.createMaze("4x4AllRoadTest.txt");
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Tests createMaze method on the 1x1 map with all walls.
//	@Test
//	void createMaze1x1AllWallTest() throws FileNotFoundException {
//		testMaze = new Maze("1x1AllWallTest.txt");
//		char[][] actual = testMaze.createMaze("1x1AllWallTest.txt");
//		String expectedMap = "@";
//		String actualMap = "";
//		for (int i = 0; i < actual.length; i++) {
//			for (int j = 0; j < actual[i].length; j++) {
//				actualMap += actual[i][j];
//			}
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Tests createMaze method on the 1x1 map with all roads.
//	@Test
//	void createMaze1x1AllRoadTest() throws FileNotFoundException {
//		testMaze = new Maze("1x1AllRoadTest.txt");
//		char[][] actual = testMaze.createMaze("1x1AllRoadTest.txt");
//		String expectedMap = "+";
//		String actualMap = "";
//		for (int i = 0; i < actual.length; i++) {
//			for (int j = 0; j < actual.length; j++) {
//				actualMap += actual[i][j];
//			}
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Tests createMaze method on the 10x10 map with roads and wall oriented in alternating order.
//	@Test
//	void createMaze10x10Test() throws FileNotFoundException {
//		String expectedMap = "";
//		testMaze = new Maze("10x10Test.txt");
//		char[][] actual = testMaze.createMaze("10x10Test.txt");
//		String actualMap = "";
//		for(int i = 0; i < actual.length; i++) {
//			for(int j = 0; j < actual.length; j++) {
//				if(i % 2 == 0 && j % 2 == 0) {
//					expectedMap += '@';
//				} else if(i % 2 == 0 && j % 2 == 1) {
//					expectedMap += '+';
//				} else if(i % 2 == 1 && j % 2 == 0) {
//					expectedMap += '+';
//				} else {
//					expectedMap += '@';
//				}
//				actualMap += actual[i][j];
//			}
//			actualMap += "\n";
//			expectedMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on a top left wall.
//	@Test
//	void setArrayOnWallNWTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(0,0));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on a top right wall.
//	@Test
//	void setArrayOnWallNETest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(12,0));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on a bottom left wall.
//	@Test
//	void setArrayOnWallSWTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(0,12));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on a bottom right wall.
//	@Test
//	void setArrayOnWallSETest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(12,12));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on top left most road of the map.
//	@Test
//	void setArrayOnRoadNWTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','M','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(1,1));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on top right most road of the map.
//	@Test
//	void setArrayOnRoadNETest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','M','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(11,1));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on bottom left most road of the map.
//	@Test
//	void setArrayOnRoadSWTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','M','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(1,11));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on bottom right most road of the map.
//	@Test
//	void setArrayOnRoadSETest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','M','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(11,11));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Moving player road to road twice.
//	@Test
//	void setArrayTwiceTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','M','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(1,1));
//		testMaze.setArray(new Point(2,1));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on the starting location of the map.
//	@Test
//	void setArrayStartTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','E','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','M','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(6,12));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Setting player on the end location of the map.
//	@Test
//	void setArrayEndTest() throws FileNotFoundException {
//		char[][] expected = {{'@','@','@','@','@','@','M','@','@','@','@','@','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','+','@','+','+','+','@','@','@','@','@','+','@'},
//							{'@','+','@','+','@','+','@','+','+','+','@','+','@'},
//							{'@','+','@','@','@','@','@','@','@','+','+','+','@'},
//							{'@','+','@','+','@','+','+','+','@','@','@','+','@'},
//							{'@','@','@','+','@','+','@','+','@','+','+','+','@'},
//							{'@','+','+','+','@','@','@','+','+','+','@','@','@'},
//							{'@','+','@','@','@','+','@','+','@','@','@','+','@'},
//							{'@','+','@','+','+','+','@','+','+','+','@','+','@'},
//							{'@','+','@','+','@','+','@','@','@','+','@','+','@'},
//							{'@','+','+','+','@','+','+','+','+','+','+','+','@'},
//							{'@','@','@','@','@','@','+','@','@','@','@','@','@'}};
//		testMaze = new Maze("maze_map1.txt");
//		testMaze.setArray(new Point(6,0));
//		char[][] actual = testMaze.getArray();
//
//		String expectedMap = "";
//		String actualMap = "";
//		for (int i = 0; i < expected.length; i++) {
//			for (int j = 0; j < expected[i].length; j++) {
//				expectedMap += expected[i][j];
//				actualMap += actual[i][j];
//			}
//			expectedMap += "\n";
//			actualMap += "\n";
//		}
//		assertEquals(expectedMap, actualMap);
//	}
//	// Testing intersections method on maze_map1.
//	@Test
//	void intersectionsTest() throws FileNotFoundException {
//		List<Point> expected = new ArrayList<Point>();
//		expected.add(new Point(6,1));
//		expected.add(new Point(3,2));
//		expected.add(new Point(5,2));
//		expected.add(new Point(11,4));
//		expected.add(new Point(7,7));
//		expected.add(new Point(5,9));
//		expected.add(new Point(6,11));
//		expected.add(new Point(9,11));
//		Maze testMaze = new Maze("maze_map1.txt");
//		char[][] map = testMaze.createMaze("maze_map1.txt");
//		List<Point> actual = testMaze.intersections(map);
//		assertEquals(expected, actual);
//	}
}