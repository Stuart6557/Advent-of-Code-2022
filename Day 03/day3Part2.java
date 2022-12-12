/*
 * This file is for Advent of Code Day 3
 */

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 3 Part 2
 */
public class day3Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day3Input.txt";
    private static final String PLACEHOLDER_BADGE = "";
    private static final int LOWERCASE_OFFSET = 96;
    private static final int UPPERCASE_OFFSET = 38;

    /**
     * Finds the item type that corresponds to the badges of each three-Elf
     * group. Calculates and prints the sum of the priorities of those item
     * types.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        int sumPriorities = 0;

        while(input.hasNext()) {
            String badge = PLACEHOLDER_BADGE;
            String rucksack1 = input.nextLine();
            String rucksack2 = input.nextLine();
            String rucksack3 = input.nextLine();

            // Puts all items from firstHalf into a HashSet
            Set <String> itemsRucksack1 = new HashSet<String>();
            for(int i = 0; i < rucksack1.length(); i++) {
                itemsRucksack1.add(rucksack1.substring(i, i+1));
            }

            /* If an item that appears in secondHalf also appears in firstHalf,
            it is possible for it to be the group's badge */
            Set <String> potentialBadges = new HashSet<String>();
            for(int i = 0; i < rucksack2.length(); i++) {
                if(itemsRucksack1.contains(rucksack2.substring(i, i+1))) {
                    potentialBadges.add(rucksack2.substring(i, i+1));
                }
            }

            /* Finds item that appears in all 3 rucksacks. This is the group's
            badge */
            for(int i = 0; i < rucksack3.length(); i++) {
                if(potentialBadges.contains(rucksack3.substring(i, i+1))) {
                    badge = rucksack3.substring(i, i+1);
                    break;
                }
            }

            // add priority of group's badge
            if(Character.isLowerCase(badge.charAt(0))) {
                sumPriorities += (int) badge.charAt(0) - LOWERCASE_OFFSET;
            } else {
                sumPriorities += (int) badge.charAt(0) - UPPERCASE_OFFSET;
            }
        }

        System.out.println(sumPriorities);
    }
}
