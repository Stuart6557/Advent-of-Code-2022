/*
 * This file is for Advent of Code Day 11
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.io.File;
import java.util.ArrayList;
import java.math.BigInteger;

/**
 * This class is my solution for Day 11 Part 2
 * 
 * Instance variables:
 * ArrayList<HashSet<Integer>> items - lists the HashSet for each item, where a
 *                                     HashSet contains the prime factors of the
 *                                     item's worry level up to the first 8
 *                                     primes. We don't need to include any
 *                                     factors other than the first 8 because
 *                                     the monkeys only  check for divisibility
 *                                     among the first 8 primes
 * String operation - shows how your worry level changes as that monkey inspects
 *                    an item
 * int divisibleBy - monkey tests to see if worry level is divisible by this
 *                   number to decide where to throw an item next
 * int trueMonkey - throws item to this monkey if test is true
 * int falseMonkey - throws item to this monkey if test is false
 * BigInteger numInspections - number of items the monkey has inspected
 */
public class day11Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day11Input.txt";
    private static final int NUM_ROUNDS = 10000;
    private static final int[] FIRST_8_PRIMES = {2, 3, 5, 7, 11, 13, 17, 19};
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
    private static final String ANOTHER_INSPECTION = "1";
    private static final String INITIAL_INSPECTIONS = "0";

     /* Instance variables */
     private ArrayList<HashSet<Integer>> items;
     String operation;
     int divisibleBy;
     int trueMonkey;
     int falseMonkey;
     BigInteger numInspections;

    /**
     * Finds and prints the level of monkey business after 10000 rounds of
     * stuff-slinging simian shenanigans
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        // set up
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

        // 10000 rounds of stuff-slinging simian shenanigans
        for(int i = 0; i < NUM_ROUNDS; i++) {
            for(int j = 0; j < monkeys.size(); j++) {
                monkeys.get(j).inspectAllItems();
                while(monkeys.get(j).items.size() > 0) {
                    if(monkeys.get(j).items.get(0).contains(monkeys.get(j).divisibleBy)) {
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
        BigInteger mostInspected = new BigInteger(INITIAL_INSPECTIONS);
        BigInteger secondMostInspected = new BigInteger(INITIAL_INSPECTIONS);
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
        items = new ArrayList<HashSet<Integer>>();
        for(String item : itemsStringList) {
            HashSet<Integer> primes = getPrime(Integer.parseInt(item));
            items.add(primes);
        }
        this.operation = operation.substring(OPERATION.length());
        divisibleBy = Integer.parseInt(test.substring(TEST.length()));
        trueMonkey = Integer.parseInt(ifTrue.substring(IF_TRUE.length()));
        falseMonkey = Integer.parseInt(ifFalse.substring(IF_FALSE.length()));
        numInspections = new BigInteger(INITIAL_INSPECTIONS);
    }

    /**
     * Given an item's worry level, returns a HashSet containing the prime
     * factors of the worry level up to the first 8 primes
     * 
     * @param item item's worry level
     * @return HashSet containing the prime factors of the worry level up to the
     * first 8 primes
     */
    public static HashSet<Integer> getPrime(int item) {
        HashSet<Integer> primes = new HashSet<Integer>();
        for(int prime : FIRST_8_PRIMES) {
            if(item % prime == 0)
                primes.add(prime);
        }
        return primes;
    }

    /**
     * Updates ArrayList items to change worry values
     */
    public void inspectAllItems() {
        // all worry levels increase after inspection
        if(operation.charAt(0) == ADD) {
            if(operation.substring(OPERATION_NUM_IDX).equals(OLD_VALUE)) {
                // adds 2 to each item in ArrayList items
                for(int i = 0; i < items.size(); i++) {
                    items.get(i).add(FIRST_8_PRIMES[0]);
                }
            } else {
                // updates each item in ArrayList items accordingly
                for(int i = 0; i < items.size(); i++) {
                    int value = 1;
                    for(int prime : items.get(i))
                        value *= prime;
                    value += Integer.parseInt(operation.substring(OPERATION_NUM_IDX));
                    items.set(i, getPrime(value));
                }
            }
        } else if(operation.charAt(0) == MULTIPLY) {
            if(!operation.substring(OPERATION_NUM_IDX).equals(OLD_VALUE)) {
                /* add the prime factors of the multiplier up to the first 8
                primes to each item in ArrayList items */
                for(int i = 0; i < items.size(); i++) {
                    HashSet<Integer> multiplierPrimes = getPrime(Integer.parseInt(operation.substring(OPERATION_NUM_IDX)));
                    for(int prime : multiplierPrimes)
                        items.get(i).add(prime);
                }
            }
        }
    }
}
