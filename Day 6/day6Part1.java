/*
 * This file is for Advent of Code Day 6
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 6 Part 1
 */
public class day6Part1 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day6Input.txt";
    private static final int MARKER_LENGTH = 4;

    /**
     * Finds and prints the number of characters that need to be processed
     * before the first start-of-packet marker is detected
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        String datastreamBuffer = input.nextLine();
        for(int i = 0; i <= datastreamBuffer.length() - MARKER_LENGTH; i++) {
            String nextFour = datastreamBuffer.substring(i , i + MARKER_LENGTH);
            if(!hasRepeat(nextFour)) {
                System.out.println(i + MARKER_LENGTH);
                break;
            }
        }
    }

    /**
     * Returns whether or not there is a repeated character in the given marker
     * 
     * @param marker the given marker
     * @return whether or not there is a repeated character
     */
    public static boolean hasRepeat(String marker) {
        for(int i = 0; i < marker.length() - 1; i++) {
            for(int j = i + 1; j < marker.length(); j++) {
                if(marker.charAt(i) == marker.charAt(j))
                    return true;
            }
        }
        return false;
    }
}
