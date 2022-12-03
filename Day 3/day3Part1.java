/*
 * This file is for Advent of Code Day 3
 */

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 3 Part 1
 */
public class day3Part1 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day3Input.txt";
    private static final String PLACEHOLDER_DUPLICATE_ITEM = "";
    private static final int NUM_COMPARTMENTS = 2;
    private static final int LOWERCASE_OFFSET = 96;
    private static final int UPPERCASE_OFFSET = 38;

    /**
     * Finds the item type that appears in both compartments of each rucksack.
     * Calculates and prints the sum of the priorities of those item types.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        int sumPriorities = 0;

        while(input.hasNext()) {
            String itemThatAppearsInBothHalves = PLACEHOLDER_DUPLICATE_ITEM;
            String rucksack = input.nextLine();
            String firstHalf = rucksack.substring(0, rucksack.length() / NUM_COMPARTMENTS);
            String secondHalf = rucksack.substring(rucksack.length() / NUM_COMPARTMENTS);

            // Puts all items from firstHalf into a HashSet
            Set <String> items = new HashSet<String>();
            for(int i = 0; i < firstHalf.length(); i++) {
                items.add(firstHalf.substring(i, i+1));
            }

            /* If an item that appears in secondHalf also appears in firstHalf,
            it is the item type that appears in both compartments of each
            rucksack */
            for(int i = 0; i < secondHalf.length(); i++) {
                if(items.contains(secondHalf.substring(i, i+1))) {
                    itemThatAppearsInBothHalves = secondHalf.substring(i, i+1);
                    break;
                }
            }

            // add priority of item type
            if(Character.isLowerCase(itemThatAppearsInBothHalves.charAt(0))) {
                sumPriorities += (int) itemThatAppearsInBothHalves.charAt(0) - LOWERCASE_OFFSET;
            } else {
                sumPriorities += (int) itemThatAppearsInBothHalves.charAt(0) - UPPERCASE_OFFSET;
            }
        }

        System.out.println(sumPriorities);
    }
}
