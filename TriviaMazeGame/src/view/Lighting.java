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

    public Lighting(GUIPlayer gp, int circleSize) {
        mySprite = gp;
        myDiameter = circleSize;

        myW = (int) mySprite.getsize().getX();
        myH = (int) mySprite.getsize().getY();

        setup();
    }

    public static Lighting getInstance(GUIPlayer gp, int circleSize) {
        if (instance == null) {
            instance = new Lighting(gp, circleSize);

        }
        return instance;
    }
    public static Lighting getInstance() {
        return instance;
    }
    public void increaseSize(int theDiameter) {
        myDiameter = myDiameter + theDiameter;
    }
    public void setSize(int theDiameter) {
        myDiameter = theDiameter;
    }
    public void setup() {

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

        Color color[] = new Color[5];
        float fraction[] = new float[5];

        color[0] = new Color(0,0,0,0f);
        color[1] = new Color(0,0,0,0.25f);
        color[2] = new Color(0,0,0,0.5f);
        color[3] = new Color(0,0,0,0.75f);
        color[4] = new Color(0,0,0,0.98f);

        fraction[0] = 0f;
        fraction[1] = 0.25f;
        fraction[2] = 0.5f;
        fraction[3] = 0.75f;
        fraction[4] = 1f;

        RadialGradientPaint gPaint = new RadialGradientPaint(myCenterX, myCenterY, (myDiameter/2), fraction, color);

        myGraphics.setPaint(gPaint);

        myGraphics.fill(myLightArea);
        //myGraphics.setColor(new Color(0,0,0,0.95f));
        myGraphics.fill(myScreenArea);
        myGraphics.dispose();
    }
    public void disableLight() {
        darknessFilter = null;
    }
    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter, 0, 0, null);
    }


}
