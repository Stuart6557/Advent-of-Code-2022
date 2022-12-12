/*
 * This file is for Advent of Code Day 11
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is my solution for Day 11 Part 2
 * 
 * Instance variables:
 * ArrayList<BigInteger> items - lists your worry level for each item the monkey
 *                               is currently holding in the order they will be
 *                               inspected
 * String operation - shows how your worry level changes as that monkey inspects
 *                    an item
 * BigInteger divisibleBy - monkey tests to see if worry level is divisible by
 *                          this number to decide where to throw an item next
 * int trueMonkey - throws item to this monkey if test is true
 * int falseMonkey - throws item to this monkey if test is false
 * int numInspections - number of items the monkey has inspected
 */
public class day11Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day11Input.txt";
    private static final int NUM_ROUNDS = 10000;
    private static final String INITIAL_NUM_INSPECTIONS = "0";
    private static final String ANOTHER_INSPECTION = "1";
    private static final String STARTING_ITEMS = "  Starting items: ";
    private static final String SEPARATOR = ", ";
    private static final String OPERATION = "  Operation: new = old ";
    private static final String TEST = "  Test: divisible by ";
    private static final String IF_TRUE = "    If true: throw to monkey ";
    private static final String IF_FALSE = "    If false: throw to monkey ";
    private static final char ADD = '+';
    private static final char MULTIPLY = '*';
    private static final String OLD_VALUE = "old";
    private static final int OPERATION_NUM_IDX = 2;
    private static final BigInteger NO_REMAINDER = new BigInteger("0");

     /* Instance variables */
     private ArrayList<BigInteger> items;
     String operation;
     BigInteger divisibleBy;
     int trueMonkey;
     int falseMonkey;
     BigInteger numInspections;

    /**
     * Finds and prints the level of monkey business after 20 rounds of
     * stuff-slinging simian shenanigans
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        ArrayList<day11Part2> monkeys = new ArrayList<day11Part2>();
        while(true) {
            input.nextLine();
            monkeys.add(new day11Part2(input.nextLine(), input.nextLine(),
                    input.nextLine(), input.nextLine(), input.nextLine()));
            if(!input.hasNext())
                break;
            input.nextLine();
        }

        for(int i = 0; i < NUM_ROUNDS; i++) {
            for(int j = 0; j < monkeys.size(); j++) {
                monkeys.get(j).inspectAllItems();
                while(monkeys.get(j).items.size() > 0) {
                    if(monkeys.get(j).items.get(0).mod(monkeys.get(j).divisibleBy).equals(NO_REMAINDER)) {
                        monkeys.get(monkeys.get(j).trueMonkey).items.add(monkeys.get(j).items.remove(0));
                    } else {
                        monkeys.get(monkeys.get(j).falseMonkey).items.add(monkeys.get(j).items.remove(0));
                    }
                    monkeys.get(j).numInspections =
                            monkeys.get(j).numInspections.add(new BigInteger(ANOTHER_INSPECTION));
                }
            }
        }

        // calculates and prints monkey business
        BigInteger mostInspected = new BigInteger(INITIAL_NUM_INSPECTIONS);
        BigInteger secondMostInspected = new BigInteger(INITIAL_NUM_INSPECTIONS);
        for(int i = 0; i < monkeys.size(); i++) {
            if(monkeys.get(i).numInspections.compareTo(secondMostInspected) == 1) {
                if(monkeys.get(i).numInspections.compareTo(mostInspected) == 1) {
                    secondMostInspected = mostInspected;
                    mostInspected = monkeys.get(i).numInspections;
                } else {
                    secondMostInspected = monkeys.get(i).numInspections;
                }
            }
        }
        System.out.println(mostInspected.multiply(secondMostInspected));
    }

    /**
     * Constructor that creates new monkey object based on input
     * 
     * @param startingItems contains the list of items the monkey starts with
     * @param operation contains the operation that shows how worry level
     *                  changes
     * @param test contains the test to decide where the monkey will throw the
     *             item
     * @param ifTrue contains the number of the monkey this monkey will throw
     *               the items to if the test is true
     * @param ifFalse contains the number of the monkey this monkey will throw
     *               the items to if the test is false
     */
    public day11Part2(String startingItems, String operation, String test,
            String ifTrue, String ifFalse) {
        startingItems = startingItems.substring(STARTING_ITEMS.length());
        String[] itemsStringList = startingItems.split(SEPARATOR);
        items = new ArrayList<BigInteger>();
        for(String item : itemsStringList) {
            items.add(new BigInteger(item));
        }
        this.operation = operation.substring(OPERATION.length());
        divisibleBy = new BigInteger(test.substring(TEST.length()));
        trueMonkey = Integer.parseInt(ifTrue.substring(IF_TRUE.length()));
        falseMonkey = Integer.parseInt(ifFalse.substring(IF_FALSE.length()));
        numInspections = new BigInteger(INITIAL_NUM_INSPECTIONS);
    }

    /**
     * Updates ArrayList items to change worry values
     */
    public void inspectAllItems() {
        // all worry levels increase after inspection
        if(operation.charAt(0) == ADD) {
            for(int i = 0; i < items.size(); i++) {
                if(operation.substring(OPERATION_NUM_IDX).equals(OLD_VALUE)) {
                    items.set(i, items.get(i).add(items.get(i)));
                } else {
                    items.set(i, items.get(i).add(new BigInteger(operation.substring(OPERATION_NUM_IDX))));
                }
            }
        } else if(operation.charAt(0) == MULTIPLY) {
            for(int i = 0; i < items.size(); i++) {
                if(operation.substring(OPERATION_NUM_IDX).equals(OLD_VALUE)) {
                    items.set(i, items.get(i).multiply(items.get(i)));
                } else {
                    items.set(i, items.get(i).multiply(new BigInteger(operation.substring(OPERATION_NUM_IDX))));
                }
            }
        }
    }
}
