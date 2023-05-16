package model;

public enum Direction {

    /**
     * North (which is up on the screen).
     */
    NORTH('N'),

    /**
     * West (which is left on the screen).
     */
    WEST('W'),

    /**
     * South (which is down on the screen).
     */
    SOUTH('S'),

    /**
     * East (which is right on the screen).
     */
    EAST('E');

    /**
     * The letter corresponding to a particular value of the enumeration.
     */
    private final char myLetter;

    // Constructor

    /**
     * Constructs a new Terrain with the specified letter.
     * 
     * @param theLetter The letter.
     */
    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    // Instance Methods

    /**
     * Returns the Direction represented by the given letter.
     * 
     * @param theLetter The letter.
     * @return the Direction represented by the given letter, or null if no
     *         Direction is represented by the given letter.
     */
    public static Direction valueOf(final char theLetter) {
        Direction result = null;

        for (final Direction direction : Direction.values()) {
            if (direction.letter() == theLetter) {
                result = direction;
                break;
            }
        }

        return result;
    }

    /**
     * Returns the letter corresponding to this direction.
     * 
     * @return the letter corresponding to this direction.
     */
    public char letter() {
        return myLetter;
    }

    /**
     * Returns the change in x-coordinate by moving one space in this direction
     * (for example, WEST would be -1, and NORTH would be 0).
     * 
     * @return the change in x-coordinate.
     */
    public int dx() {
        int result = 0;

        switch (this) {
            case WEST:
                result = -1;
                break;

            case EAST:
                result = 1;
                break;

            default:
        }

        return result;
    }

    /**
     * Returns the change in y-coordinate by moving one space in this direction
     * (for example, WEST would be 0, and NORTH would be -1).
     * 
     * @return the change in y-coordinate.
     */
    public int dy() {
        int result = 0;

        switch (this) {
            case SOUTH:
                result = 1;
                break;

            case NORTH:
                result = -1;
                break;

            default:
        }

        return result;
    }
}

