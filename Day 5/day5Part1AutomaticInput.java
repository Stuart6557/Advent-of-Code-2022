/*
 * This file is for Advent of Code Day 5
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Stack;

/**
 * This class is my second solution for Day 5 Part 1. It utilizes Stacks and
 * reads data from the input file instead of hard coding it.
 */
public class day5Part1AutomaticInput {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day5Input.txt";
    private static final int SPACE_FOR_EACH_CRATE = 4;
    private static final String NO_CRATE = " ";
    private static final String SEPARATOR = " ";
    private static final int IDX_AMOUNT = 1;
    private static final int IDX_FROM = 3;
    private static final int IDX_TO = 5;

    /**
     * Finds and prints the crates that end up on top of each stack after the
     * rearrangement procedure completes.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));

        ArrayList<String> stackStrings = new ArrayList<>();
        String nextRowInput = input.nextLine();
        int numStacks = (nextRowInput.length() + 1) / SPACE_FOR_EACH_CRATE;
        while(!Character.isDigit(nextRowInput.charAt(1))) {
            stackStrings.add(nextRowInput);
            nextRowInput = input.nextLine();
        }
        int initialTallestStack = stackStrings.size();
        input.nextLine();

        ArrayList<Stack<String>> stacks = new ArrayList<>();
        for(int i = 0; i < numStacks; i++) {
            Stack<String> anotherStack = new Stack<String>();
            for(int j = initialTallestStack - 1; j >= 0; j--) {
                int crateIndex = SPACE_FOR_EACH_CRATE * i + 1;
                String nextCrate = stackStrings.get(j).substring(crateIndex, crateIndex + 1);
                if(nextCrate.equals(NO_CRATE))
                    break;
                anotherStack.add(nextCrate);
            }
            stacks.add(anotherStack);
        }

        // moving the crates
        while(input.hasNextLine()) {
            String[] nextMove = input.nextLine().split(SEPARATOR);
            int amount = Integer.parseInt(nextMove[IDX_AMOUNT]);
            int from = Integer.parseInt(nextMove[IDX_FROM]);
            int to = Integer.parseInt(nextMove[IDX_TO]);

            for(int i = 0; i < amount; i++) {
                stacks.get(to-1).add(stacks.get(from-1).pop());
            }
        }

        // getting top crates and printing them out
        String topCrates = "";
        for(Stack<String> stack : stacks) {
            topCrates += stack.get(stack.size() - 1);
        }
        System.out.println(topCrates);
    }
}
