package model;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Maze {
    // Class constant

    // the final Random.
    private static final Random RANDOM_POINTS = new Random();

    // Class field

    // initializing the 2D maze.
    private final char[][] myMaze;
    // the default map file for maze.
    private static String myMazeMap;

    private Point myExit;

    //initializing the intersection points
    private final ArrayList<Point> myIntersections;
    //initializing the intersection points
    private final ArrayList<Point> myQuestionPoints;
    // initializing the location of the player.
    private Point myPlayerLocation;

    /**
     * This is a default constructor for Maze class.
     *
     * @throws FileNotFoundException
     */
    public Maze(String mapName) throws FileNotFoundException {
        // For Program running purpose.
        myMazeMap = mapName;
        // For Program running purpose.
        myMaze = createMaze(myMazeMap);
        // Assign the instance variable myIntersections to the creates Maze.
        myIntersections = intersections(myMaze);
        myQuestionPoints = questionPointgenerator();
        myPlayerLocation = playerLocation();
        myExit = exitLocation();
    }

    /**
     * This method return the 2D array representation of the maze.
     *
     * @return the 2D list.
     */
    public char[][] getArray() {
        return myMaze;
    }

    public char charAt(int x, int y) {
        return myMaze[y][x];
    }
    /**
     * Updates the map whenever player makes a move.
     * @param thePoint The point player wants to move.
     */
    public void setArray(Point thePoint) {
        if(thePoint.x > 0 && thePoint.x > myMaze.length - 1 && thePoint.y > 0    && thePoint.y > myMaze[0].length - 1) {
            if (myMaze[thePoint.y][thePoint.x] != '@') {
                myMaze[myPlayerLocation.y][myPlayerLocation.x] = '+';
                myMaze[thePoint.y][thePoint.x] = 'M';
                myPlayerLocation = new Point(thePoint.x,thePoint.y);
            }
        }


    }
    public Point getMyExit() {
        return myExit;
    }
    /**
     * This method returns the location of the player in Point object.
     * @return the location of the player.
     */
    private Point playerLocation() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze.length; j++) {
                if (myMaze[i][j] == 'M') {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }

    private Point exitLocation() {
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze.length; j++) {
                if (myMaze[i][j] == 'E') {
                    return new Point(j,i);
                }
            }
        }
        return null;
    }

    public Point getMyPlayerLocation() {
        return myPlayerLocation;
    }

    /**
     * This method reads the character from the map.
     *
     * @param theMapName the name of the map.
     * @return the 2D array representation of the maze.
     * @throws FileNotFoundException when the file is not found.
     */
    public char[][] createMaze(String theMapName) throws FileNotFoundException {

        File mazeMapFile = new File(theMapName);
        // read the map file from scanner.
        //initialize the scanner
        Scanner myScanner = new Scanner(mazeMapFile);
        // Read the Row length from the map.
        int row = myScanner.nextInt();
        //// Read the Column length from the map.
        int column = myScanner.nextInt();
        //initialize the array size.
        char[][] mazeMap = new char[row][column];

        // read the character from the map.
        for (int i = 0; i < row; i++) {
            String line = myScanner.next();
            for (int j = 0; j < column; j++) {
                mazeMap[i][j] = line.charAt(j);
            }
        }
        return mazeMap;

    }

    /**
     * This method looks for every intersection and return the intersection (x, y)points.
     *
     * @param theMaze the 2D array representation of the map.
     * @return the lists of intersections.
     */
    public ArrayList<Point> intersections(char[][] theMaze) {
        ArrayList<Point> intersectionsPoint = new ArrayList<Point>();
        for (int i = 0; i < theMaze.length; i++) {
            for (int j = 0; j < theMaze[0].length; j++) {
                int up = i - 1;
                int down = i + 1;
                int left = j - 1;
                int right = j + 1;
                // check the road up, down, left, and right, and also the edge cases.
                if (theMaze[i][j] == '+') {
                    int counter = 0;
                    if (up >= 0 && theMaze[up][j] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (down < theMaze.length && theMaze[down][j] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (left >= 0 && theMaze[i][left] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (right < theMaze[0].length && theMaze[i][right] != '@') {
                        // add the point into the arrayList.
                        counter++;
                    }
                    if (counter > 2) {
                        intersectionsPoint.add(new Point(j, i));
                    }
                }
            }
        }
        return intersectionsPoint;

    }

    /**
     * Randomly assigns 50 percent of the intersection points to become a question points.
     * @return the lists of question points.
     */
    public ArrayList<Point> questionPointgenerator() {
        // initialize how many question will have in the map.
        int number = myIntersections.size() / 2;
        int all = myIntersections.size();
        ArrayList<Point> questionPoint = new ArrayList<Point>();
        for (int i = 0; i < all; i++) {
            //initailize the ranPoints.
            int ranPoints = RANDOM_POINTS.nextInt(myIntersections.size());
            // Get the intersections Point from the ranPoints.
            Point randomIntersection = myIntersections.get(ranPoints);
            // assign the random question point in the maze
            questionPoint.add(new Point(randomIntersection.x, randomIntersection.y));
        }
        return questionPoint;
    }

    /**
     *  This method returns the lists of the question points.
     * @return the list of the question points.
     */
    public ArrayList<Point> getQuestionPoints() {
       return myQuestionPoints;
    }
    /**
     * For testing purpose.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myMaze.length; i++) {
            for (int j = 0; j < myMaze[i].length; j++) {
                sb.append(myMaze[i][j]);
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}
