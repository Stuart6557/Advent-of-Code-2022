/*
 * This file is for Advent of Code Day 1
 */

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;;

/**
 * This class is my solution for Day 1 Part 2
 */
public class day1Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day1Input.txt";
    private static final String ELF_SEPARATOR = "";

    /**
     * Calculates and prints the total Calories carried by the top three Elves
     * carrying the most Calories.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        int currElfCalories = 0;
        int maxElfCalories = 0;
        int secondMostElfCalories = 0;
        int thirdMostElfCalories = 0;
        
        while(input.hasNext()) {
            String nextVal = input.nextLine();
            if(nextVal.equals(ELF_SEPARATOR)) {
                if(currElfCalories > maxElfCalories) {
                    thirdMostElfCalories = secondMostElfCalories;
                    secondMostElfCalories = maxElfCalories;
                    maxElfCalories = currElfCalories;
                } else if(currElfCalories > secondMostElfCalories) {
                    thirdMostElfCalories = secondMostElfCalories;
                    secondMostElfCalories = currElfCalories;
                } else if(currElfCalories > thirdMostElfCalories) {
                    thirdMostElfCalories = currElfCalories;
                }
                currElfCalories = 0;
            } else {
                int numCalories = Integer.parseInt(nextVal);
                currElfCalories += numCalories;
            }
        }

        System.out.println(maxElfCalories + secondMostElfCalories + thirdMostElfCalories);
    }
}
