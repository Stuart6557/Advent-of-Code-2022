/*
 * This file is for Advent of Code Day 9
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashSet;

/**
 * This class is my solution for Day 9 Part 2
 * 
 * Instance variables:
 * int x - x position of the knot
 * int y - y position of the knot
 */
public class day9Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day9Input.txt";
    private static final int NUM_KNOTS = 10;
    private static final String LEFT = "L";
    private static final String UP = "U";
    private static final String RIGHT = "R";
    private static final String SEPARATOR = " ";

    /* Instance variables */
    private int x;
    private int y;

    /**
     * Finds and prints the number of positions that the tail of the rope visits
     * at least once after simulating the complete hypothetical series of
     * motions.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        HashSet<String> tailPoints = new HashSet<String>();
        day9Part2[] knots = new day9Part2[NUM_KNOTS];
        for(int i = 0; i < NUM_KNOTS; i++) {
            knots[i] = new day9Part2();
        }

        while(input.hasNext()) {
            String dir = input.next();
            int numStepsInDir = Integer.parseInt(input.next());

            for(int numSteps = 0; numSteps < numStepsInDir; numSteps++) {
                // move head
                if(dir.equals(LEFT)) {
                    knots[0].setX(knots[0].getX() - 1);
                } else if(dir.equals(UP)) {
                    knots[0].setY(knots[0].getY() + 1);
                } else if(dir.equals(RIGHT)) {
                    knots[0].setX(knots[0].getX() + 1);
                } else {
                    knots[0].setY(knots[0].getY() - 1);
                }

                // move the rest of the knots one by one
                for(int i = 1; i < NUM_KNOTS; i++) {
                    knots[i].move(knots[i-1]);
                }

                // add tail position to tailPoints HashSet
                tailPoints.add(knots[NUM_KNOTS-1].getX() + SEPARATOR
                        + knots[NUM_KNOTS-1].getY());
            }
        }

        System.out.println(tailPoints.size());
    }

    /**
     * Default constructor - set both x and y to zero
     */
    public day9Part2() {
        x = 0;
        y = 0;
    }

    /**
     * Returns x position
     * 
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * Returns y position
     * 
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x position to x
     * 
     * @param x new x position
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y position to y
     * 
     * @param y new y position
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Updates x and y for this day9Part2 object based on the position of the
     * previous knot
     * 
     * @param previousKnot the previous knot
     */
    public void move(day9Part2 previousKnot) {
        if(previousKnot.getX() == x) {
            if(y - previousKnot.getY() > 1) {
                y--;
                return;
            }
            if(previousKnot.getY() - y > 1) {
                y++;
                return;
            }
            return;
        }
        if(previousKnot.getY() == y) {
            if(x - previousKnot.getX() > 1) {
                x--;
                return;
            }
            if(previousKnot.getX() - x > 1) {
                x++;
                return;
            }
            return;
        }

        if(Math.abs(x - previousKnot.getX()) == 1 && Math.abs(y - previousKnot.getY()) == 1)
            return;

        /* if we get to this point, we know that the previous knot and this knot
        aren't touching and aren't in the same row or column, so this knot has
        to move one step diagonally to keep up */
        if(x - previousKnot.getX() > 1) {
            if(y > previousKnot.getY()) {
                x--;
                y--;
                return;
            } else {
                x--;
                y++;
                return;
            }
        }
        if(previousKnot.getX() - x > 1) {
            if(y > previousKnot.getY()) {
                x++;
                y--;
                return;
            } else {
                x++;
                y++;
                return;
            }
        }
        if(y - previousKnot.getY() > 1) {
            if(x > previousKnot.getX()) {
                x--;
                y--;
                return;
            } else {
                x++;
                y--;
                return;
            }
        }
        /* at this point, the y position of the previous knot - the y position
        of this knot > 1 */
        if(x > previousKnot.getX()) {
            x--;
            y++;
            return;
        } else {
            x++;
            y++;
            return;
        }
    }
}
