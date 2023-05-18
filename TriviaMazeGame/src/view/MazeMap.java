package view;

import java.awt.Graphics2D;
import javax.swing.*;


public class MazeMap {


    private static final long serialVersionUID = 1L;

    private static final int SQUARE_SIZE = 40;
    GamePanel gp;

    //public BufferedImage road, wall;
    public ImageIcon road, wall;
    private char[][] myArray;

    public MazeMap(GamePanel gp, char[][] theArray) {
        this.gp = gp;
        myArray = theArray;
        start();
        getMapImage();
    }

    public void start(){

        //this.setPreferredSize(new Dimension(520,520));
    }
    public void getMapImage(){
        road = new ImageIcon("road.png");
        wall = new ImageIcon("wall.png");
    }



    public void draw(final Graphics2D theGraphics) {
        //BufferedImage image = null;
        //ImageIcon  image = null;
        int topy = -48;

        for (int y = 0; y < myArray.length; y++) {
            topy = topy + gp.tileSize;
            int leftx = -48;
            for (int x = 0; x < myArray[y].length; x++) {
                leftx = leftx + gp.tileSize;

                switch (myArray[y][x]) {
                    case '@':
                        theGraphics.drawImage(wall.getImage(), leftx, topy, gp.tileSize, gp.tileSize , null);
                        break;
                    case '+':
                        theGraphics.drawImage(road.getImage(), leftx, topy, gp.tileSize, gp.tileSize, null);
                        break;
                }

            }
        }
    }
}
