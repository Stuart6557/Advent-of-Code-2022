/*
 * This file is for Advent of Code Day 4
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 4 Part 2
 */
public class day4Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day4Input.txt";

    /**
     * Counts and prints the number of assignment pairs where the ranges
     * overlap.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        int count = 0;
        while(input.hasNext()) {
            String[] IDs = input.nextLine().split("-|,");
            if(Integer.parseInt(IDs[1]) >= Integer.parseInt(IDs[2])
                    && Integer.parseInt(IDs[0]) <= Integer.parseInt(IDs[3])) {
                count++;
            }
        }
        System.out.println(count);
    }
}
