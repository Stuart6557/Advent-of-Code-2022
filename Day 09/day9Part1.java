/*
 * This file is for Advent of Code Day 9
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.HashSet;

/**
 * This class is my solution for Day 9 Part 1
 */
public class day9Part1 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day9Input.txt";
    private static final String LEFT = "L";
    private static final String UP = "U";
    private static final String RIGHT = "R";
    private static final String SEPARATOR = " ";

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
        int headX = 0;
        int headY = 0;
        int tailX = 0;
        int tailY = 0;

        while(input.hasNextLine()) {
            String dir = input.next();
            int numStepsInDir = Integer.parseInt(input.next());
            if(dir.equals(LEFT)) {
                // head moves left
                for(int i = 0; i < numStepsInDir; i++) {
                    headX--;
                    String newTailPos = newTailPos(headX, headY, tailX, tailY);
                    tailX = getX(newTailPos);
                    tailY = getY(newTailPos);
                    tailPoints.add(tailX + SEPARATOR + tailY);
                }
            } else if(dir.equals(UP)) {
                // head moves up
                for(int i = 0; i < numStepsInDir; i++) {
                    headY++;
                    String newTailPos = newTailPos(headX, headY, tailX, tailY);
                    tailX = getX(newTailPos);
                    tailY = getY(newTailPos);
                    tailPoints.add(tailX + SEPARATOR + tailY);
                }
            } else if(dir.equals(RIGHT)) {
                // head moves right
                for(int i = 0; i < numStepsInDir; i++) {
                    headX++;
                    String newTailPos = newTailPos(headX, headY, tailX, tailY);
                    tailX = getX(newTailPos);
                    tailY = getY(newTailPos);
                    tailPoints.add(tailX + SEPARATOR + tailY);
                }
            } else {
                // head moves down
                for(int i = 0; i < numStepsInDir; i++) {
                    headY--;
                    String newTailPos = newTailPos(headX, headY, tailX, tailY);
                    tailX = getX(newTailPos);
                    tailY = getY(newTailPos);
                    tailPoints.add(tailX + SEPARATOR + tailY);
                }
            }
        }
        System.out.println(tailPoints.size());
    }

    /**
     * Returns the new x and y positions of the tail given the x and y positions
     * of the had.
     * 
     * @param headX x position of the head
     * @param headY y position of the head
     * @param tailX x position of the head
     * @param tailY y position of the head
     * @return updated tail position in the format "x y"
     */
    private static String newTailPos(int headX, int headY, int tailX, int tailY) {
        if(headX == tailX) {
            if(tailY - headY > 1) {
                return tailX + SEPARATOR + (tailY - 1);
            }
            if(headY - tailY > 1) {
                return tailX + SEPARATOR + (tailY + 1);
            }
            return tailX + SEPARATOR + tailY;
        }
        if(headY == tailY) {
            if(tailX - headX > 1) {
                return (tailX - 1) + SEPARATOR + tailY;
            }
            if(headX - tailX > 1) {
                return (tailX + 1) + SEPARATOR + tailY;
            }
            return tailX + SEPARATOR + tailY;
        }

        if(Math.abs(tailX - headX) == 1 && Math.abs(tailY - headY) == 1)
            return tailX + SEPARATOR + tailY;

        /* if we get to this point, we know that the head and tail aren't
        touching and aren't in the same row or column, so the tail has to move
        one step diagonally to keep up */
        if(tailX - headX > 1) {
            if(tailY > headY) {
                return (tailX - 1) + SEPARATOR + (tailY - 1);
            } else {
                return (tailX - 1) + SEPARATOR + (tailY + 1);
            }
        }
        if(headX - tailX > 1) {
            if(tailY > headY) {
                return (tailX + 1) + SEPARATOR + (tailY - 1);
            } else {
                return (tailX + 1) + SEPARATOR + (tailY + 1);
            }
        }
        if(tailY - headY > 1) {
            if(tailX > headX) {
                return (tailX - 1) + SEPARATOR + (tailY - 1);
            } else {
                return (tailX + 1) + SEPARATOR + (tailY - 1);
            }
        }
        // at this point, headY - tailY > 1
        if(tailX > headX) {
            return (tailX - 1) + SEPARATOR + (tailY + 1);
        } else {
            return (tailX + 1) + SEPARATOR + (tailY + 1);
        }
    }

    /**
     * Returns the x position given a position in the format "x y"
     * 
     * @param position the given x and y position
     * @return the x position
     */
    private static int getX(String position) {
        String xPos = position.substring(0, position.indexOf(SEPARATOR));
        return Integer.parseInt(xPos);
    }

    /**
     * Returns the y position given a position in the format "x y"
     * 
     * @param position the given x and y position
     * @return the y position
     */
    private static int getY(String position) {
        String yPos = position.substring(position.indexOf(SEPARATOR) + 1);
        return Integer.parseInt(yPos);
    }
}
