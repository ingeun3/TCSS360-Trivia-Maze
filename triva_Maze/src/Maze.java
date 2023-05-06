import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Maze {

    // the default map file for maze.
    private static final File MAZE_MAP =  new File ("maze_map1.txt");

    // the final Radom.
    private static Random RANDOM_POINTS = new Random();

    // initializing the 2D maze.
    private char[][] myMaze;

    //initializing the intersection points
    private ArrayList<Point> myIntersections;

    //initialize the scanner
    private Scanner myScanner;

    /**
     * This is a default constructor for Maze class.
     *
     * @throws FileNotFoundException
     */
    public Maze() throws FileNotFoundException {
        // For Program running purpose.
        start();
    }

    /**
     * This method calls all of the sub-methods that has in this class.
     * @throws FileNotFoundException
     */
    private void start() throws FileNotFoundException {
        // read the map file from scanner.
        myScanner = new Scanner(MAZE_MAP);
        // Read the Row length from the map.
        int row = myScanner.nextInt();
        //// Read the Column length from the map.
        int column = myScanner.nextInt();
        // Assign the instance variable myMaze to the create Maze.
        myMaze = createMaze(row, column);
        // Assign the instance variable myIntersections to the create Maze.
        myIntersections = intersections(myMaze);
        questionPointgenerator();
    }

    /**
     * This method return the 2D maze List.
     * @return the 2D list.
     */
    public char[][] getArray(){
        return myMaze;
    }

    // everytime players move just update the map.
    public void setArray (Point thePoint) {
        myMaze[(int) thePoint.getX()][(int) thePoint.getY()] = 'M';
    }

    /**
     * This method reads the character from the map.
     *
     * @param theRow the row length.
     * @param theColumn the column length.
     *
     * @return the 2D maze List.
     * @throws FileNotFoundException
     */
    private char[][] createMaze (int theRow, int theColumn) throws FileNotFoundException{
        //initialize the array size.
        char[][] mazeMap = new char[theRow][theColumn];
        // read the character from the map.
        for (int i = 0; i < theRow; i++) {
            String line = myScanner.next();
            for (int j = 0; j < theColumn; j++) {
                mazeMap[i][j] = line.charAt(j);
            }
        }
        return mazeMap;

    }

    /**
     * This method return the intersection (x, y)points.
     * @param theMaze the 2D List.
     * @return the intersections point.
     */
    private ArrayList<Point> intersections(char[][] theMaze) {
        ArrayList<Point> intersectionsPoint = new ArrayList <Point>();
        for (int i = 0; i < theMaze.length; i++) {
            for (int j = 0; j < theMaze[0].length; j++) {
                int up = i - 1;
                int down = i + 1;
                int left = j - 1;
                int right = j + 1;
                // check the road up, down, left, and right, and also the edge cases.
                if ((up >= 0 && theMaze[up][j] == '+') &&
                        (down < theMaze.length && theMaze[down][j] == '+') &&
                        (left >= 0 && theMaze[i][left] == '+') &&
                        (right < theMaze[0].length && theMaze[i][right] == '+')) {
                    // add the point into the arrayList.
                    intersectionsPoint.add(new Point(i, j));
                }
            }
        }
        return intersectionsPoint;

    }


    private void questionPointgenerator() {
        // initialize how many question will have in the map.
        int number = myIntersections.size()/2;
        for (int i = 0; i < number; i++) {
            //initailize the ranPoints.
            int ranPoints = RANDOM_POINTS.nextInt(myIntersections.size());
            // Get the intersections Point from the ranPoints.
            Point randomIntersection = myIntersections.get(ranPoints);
            // assign the radom question point in the maze
            myMaze[randomIntersection.y][randomIntersection.x] = '?';
        }
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

