/*
 * This file is for Advent of Code Day 5
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my first solution for Day 5 Part 1. The data is hard coded.
 */
public class day5Part1ManualInput {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day5Input.txt";
    private static final String SEPARATOR = " ";
    private static final int UNNECESSARY_LINES = 10;
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

        ArrayList<ArrayList<String>> stacks = new ArrayList<>();

        ArrayList<String> stack1 = new ArrayList<String>();
        ArrayList<String> stack2 = new ArrayList<String>();
        ArrayList<String> stack3 = new ArrayList<String>();
        ArrayList<String> stack4 = new ArrayList<String>();
        ArrayList<String> stack5 = new ArrayList<String>();
        ArrayList<String> stack6 = new ArrayList<String>();
        ArrayList<String> stack7 = new ArrayList<String>();
        ArrayList<String> stack8 = new ArrayList<String>();
        ArrayList<String> stack9 = new ArrayList<String>();

        stack1.add("G");
        stack1.add("F");
        stack1.add("V");
        stack1.add("H");
        stack1.add("P");
        stack1.add("S");

        stack2.add("G");
        stack2.add("J");
        stack2.add("F");
        stack2.add("B");
        stack2.add("V");
        stack2.add("D");
        stack2.add("Z");
        stack2.add("M");

        stack3.add("G");
        stack3.add("M");
        stack3.add("L");
        stack3.add("J");
        stack3.add("N");

        stack4.add("N");
        stack4.add("G");
        stack4.add("Z");
        stack4.add("V");
        stack4.add("D");
        stack4.add("W");
        stack4.add("P");

        stack5.add("V");
        stack5.add("R");
        stack5.add("C");
        stack5.add("B");

        stack6.add("V");
        stack6.add("R");
        stack6.add("S");
        stack6.add("M");
        stack6.add("P");
        stack6.add("W");
        stack6.add("L");
        stack6.add("Z");

        stack7.add("T");
        stack7.add("H");
        stack7.add("P");

        stack8.add("Q");
        stack8.add("R");
        stack8.add("S");
        stack8.add("N");
        stack8.add("C");
        stack8.add("H");
        stack8.add("Z");
        stack8.add("V");

        stack9.add("F");
        stack9.add("L");
        stack9.add("G");
        stack9.add("P");
        stack9.add("V");
        stack9.add("Q");
        stack9.add("J");

        stacks.add(stack1);
        stacks.add(stack2);
        stacks.add(stack3);
        stacks.add(stack4);
        stacks.add(stack5);
        stacks.add(stack6);
        stacks.add(stack7);
        stacks.add(stack8);
        stacks.add(stack9);

        /* we don't need the fist 10 lines of the input file since we hard coded
        the data */
        for(int i = 0; i < UNNECESSARY_LINES; i++) {
            input.nextLine();
        }
        
        // moving the crates
        while(input.hasNextLine()) {
            String[] nextMove = input.nextLine().split(SEPARATOR);
            int amount = Integer.parseInt(nextMove[IDX_AMOUNT]);
            int from = Integer.parseInt(nextMove[IDX_FROM]);
            int to = Integer.parseInt(nextMove[IDX_TO]);

            for(int i = 0; i < amount; i++) {
                int idxToBeRemoved = stacks.get(from-1).size() - 1;
                String temp = stacks.get(from-1).remove(idxToBeRemoved);
                stacks.get(to-1).add(temp);
            }
        }

        // getting top crates and printing them out
        String topCrates = "";
        for(ArrayList<String> stack : stacks) {
            topCrates += stack.get(stack.size() - 1);
        }
        System.out.println(topCrates);
    }
}
