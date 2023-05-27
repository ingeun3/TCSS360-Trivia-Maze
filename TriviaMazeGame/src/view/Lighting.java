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
        Color color[] = new Color[12];
        float fraction[] = new float[12]; // anysame but has to be same
        //number of more gradition data
        color[0] = new Color (0,0,0,0.1f); // completely transparent
        color[1] = new Color (0,0,0,0.42f);
        color[2] = new Color (0,0,0,0.52f);
        color[3] = new Color (0,0,0,0.61f);
        color[4] = new Color (0,0,0,0.69f); // almost complete is dark
        color[5] = new Color (0,0,0,0.76f);
        color[6] = new Color (0,0,0,0.82f);
        color[7] = new Color (0,0,0,0.87f);
        color[8] = new Color (0,0,0,0.91f);
        color[9] = new Color (0,0,0,0.94f);
        color[10] = new Color (0,0,0,0.96f);
        color[11] = new Color (0,0,0,0.98f);
        fraction[0] = 0f;// center
        fraction[1] = 0.4f;//center of the light circle
        fraction[2] = 0.5f;
        fraction[3] = 0.6f;
        fraction[4] = 0.65f;
        fraction[5] = 0.7f;
        fraction[6] = 0.75f;
        fraction[7] = 0.8f;
        fraction[8] = 0.85f;
        fraction[9] = 0.9f;
        fraction[10] = 0.95f;
        fraction[11] = 1f;//edge
        //create paint setting
        RadialGradientPaint gPaint = new RadialGradientPaint(myCenterX, myCenterY,
                (myDiameter/2), fraction, color);
        //set the gradient data on g2
        myGraphics.setPaint(gPaint);
        myGraphics.fill(myLightArea);
      //  myGraphics.setColor(new Color(0,0,0,0.95f));


        myGraphics.fill(myScreenArea);
        myGraphics.dispose();
    }

    public void draw(Graphics2D g2){
        g2.drawImage(darknessFilter, 0, 0, null);
    }
}
