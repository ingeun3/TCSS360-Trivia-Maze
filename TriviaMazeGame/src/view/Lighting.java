package view;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class Lighting {
    private static Lighting instance;

    private BufferedImage darknessFilter;
    private int myW;
    private int myH;
    private int myCenterX;
    private int myCenterY;
    private double myTopLeftX;
    private double myTopLeftY;
    private static int myDiameter;
    private Area myScreenArea;
    private Area myLightArea;
    private Graphics2D myGraphics;
    private static GUIPlayer mySprite;

    private Lighting(GUIPlayer gp, int circleSize) {
        mySprite = gp;
        myDiameter = circleSize;

        myW = (int) mySprite.getsize().getX();
        myH = (int) mySprite.getsize().getY();

        darknessFilter = new BufferedImage(myW, myH, BufferedImage.TYPE_INT_ARGB );
        myGraphics = (Graphics2D) darknessFilter.getGraphics();
        myScreenArea = new Area(new Rectangle2D.Double(0, 0, myW ,myH));

        myCenterX = mySprite.getX() + (mySprite.getTileSize()/2); // x and y of the light circle.
        myCenterY = mySprite.getY() + (mySprite.getTileSize()/2);

        myTopLeftX = myCenterX - (myDiameter / 2); // get top of the light circle.
        myTopLeftY = myCenterY - (myDiameter / 2);

        Shape circleShape = new Ellipse2D.Double(myTopLeftX, myTopLeftY, myDiameter, myDiameter);
        // create anohter light circle
        myLightArea = new Area (circleShape);
        // subtract from the screenARea
        myScreenArea.subtract(myLightArea);
        myGraphics.setColor(new Color(0,0,0,0.95f));
        myGraphics.fill(myScreenArea);
       // myGraphics.dispose();
    }

    public static Lighting getInstance(GUIPlayer gp, int circleSize) {
        if (instance == null) {
            instance = new Lighting(gp, circleSize);

        }
        return instance;
    }
    public void setup() {

        darknessFilter = new BufferedImage(myW, myH, BufferedImage.TYPE_INT_ARGB );
        myGraphics = (Graphics2D) darknessFilter.getGraphics();
        myScreenArea = new Area(new Rectangle2D.Double(0, 0, myW ,myH));


        myCenterX = mySprite.getX() + (mySprite.getTileSize()/2); // x and y of the light circle.
        myCenterY = mySprite.getY() + (mySprite.getTileSize()/2);

        myTopLeftX = myCenterX - (myDiameter / 2); // get top of the light circle.
        myTopLeftY = myCenterY - (myDiameter / 2);

        System.out.println(myCenterX);
        System.out.println(myCenterY);
        Shape circleShape = new Ellipse2D.Double(myTopLeftX, myTopLeftY, myDiameter, myDiameter);
        // create anohter light circle
        myLightArea = new Area (circleShape);
        // subtract from the screenARea
        myScreenArea.subtract(myLightArea);
        myGraphics.setColor(new Color(0,0,0,0.95f));
        myGraphics.fill(myScreenArea);
        myGraphics.dispose();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
