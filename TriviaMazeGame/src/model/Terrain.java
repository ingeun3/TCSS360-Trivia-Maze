/*
 * TCSS 305 - Assignment 3
 */

package model;

/**
 * An enumeration (and associated functionality) for types of terrain that may
 * appear on the map.
 * 
 * @author TCSS 305 instructors
 * @version 1.2
 */

public enum Terrain {
    /**
     * Wall.
     */
    WALL('@'),

    /**
     * Road.
     */
    ROAD('+'),

    /**
     * Player.
     */
    PLAYER('M'),
    
    /**
     * Question Point.
     */
    QUESTION_POINT('?'),

	/**
     * Check Point.
     */
    CHECK_POINT('#'),
    
	/**
     * Start Point.
     */
    START_POINT('S'),
    
    /**
     * End Point.
     */
    END_POINT('E');
    /**
     * The character corresponding to a particular value of the enumeration.
     */
    private char myLetter;

    // Constructor

    /**
     * Constructs a new Terrain with the specified letter.
     * 
     * @param theLetter The letter.
     */
    Terrain(final char theLetter) {
        myLetter = theLetter;
    }

    // Instance Methods

    /**
     * Returns the Terrain represented by the given letter.
     * 
     * @param theLetter The letter.
     * @return the Terrain represented by the given letter, or GRASS if no
     *         Terrain is represented by the given letter.
     */
    public static Terrain valueOf(final char theLetter) {
        Terrain result = WALL;

        for (final Terrain terrain : Terrain.values()) {
            if (terrain.myLetter == theLetter) {
                result = terrain;
                break;
            }
        }

        return result;
    }

    /**
     * Returns a String representation of this Terrain, such as "WALL (X)".
     * 
     * @return a String representation of this Terrain.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + myLetter + ")";
    }
}

// end of class Terrain
