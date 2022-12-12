/*
 * This file is for Advent of Code Day 8
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is my solution for Day 8 Part 2
 */
public class day8Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day8Input.txt";

    /**
     * Finds and prints the highest scenic score possible for any tree
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        // creating 2D array of tree heights
        Scanner input = new Scanner(new File(INPUT_FILE));
        ArrayList<String> treesInput = new ArrayList<>();
        while(input.hasNext())
            treesInput.add(input.nextLine());
        int rowLen = treesInput.get(0).length();
        int[][] trees = new int[treesInput.size()][rowLen];
        for(int i = 0; i < trees.length; i++) {
            for(int j = 0; j < rowLen; j++) {
                trees[i][j] = Integer.parseInt(treesInput.get(i).substring(j, j+1));
            }
        }

        /* Finds and prints highest possible scenic score. We will not consider
        the trees on the edge since their scenic scores are 0 */
        int maxScenicScore = 0;
        for(int i = 1; i < trees.length - 1; i++) {
            for(int j = 1; j < rowLen - 1; j++) {
                int scenicScore = leftViewingDistance(trees, i, j)
                        * topViewingDistance(trees, i, j)
                        * rightViewingDistance(trees, i, j)
                        * bottomViewingDistance(trees, i, j);
                if(scenicScore > maxScenicScore) {
                    maxScenicScore = scenicScore;
                }
            }
        }
        System.out.println(maxScenicScore);
    }

    /**
     * Returns the left viewing distance of a given tree
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return left viewing distance of a given tree
     */
    private static int leftViewingDistance(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        int count = 0;
        for(int i = col - 1; i >= 0; i--) {
            count++;
            if(trees[row][i] >= thisTreeHeight)
                break;
        }
        return count;
    }

    /**
     * Returns the top viewing distance of a given tree
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return top viewing distance of a given tree
     */
    private static int topViewingDistance(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        int count = 0;
        for(int i = row - 1; i >= 0; i--) {
            count++;
            if(trees[i][col] >= thisTreeHeight)
                break;
        }
        return count;
    }

    /**
     * Returns the right viewing distance of a given tree
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return right viewing distance of a given tree
     */
    private static int rightViewingDistance(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        int count = 0;
        for(int i = col + 1; i < trees[row].length; i++) {
            count++;
            if(trees[row][i] >= thisTreeHeight)
                break;
        }
        return count;
    }

    /**
     * Returns the bottom viewing distance of a given tree
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return bottom viewing distance of a given tree
     */
    private static int bottomViewingDistance(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        int count = 0;
        for(int i = row + 1; i < trees.length; i++) {
            count++;
            if(trees[i][col] >= thisTreeHeight)
                break;
        }
        return count;
    }
}
