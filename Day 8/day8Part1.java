/*
 * This file is for Advent of Code Day 8
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.ArrayList;

/**
 * This class is my solution for Day 8 Part 1
 */
public class day8Part1 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day8Input.txt";

    /**
     * Counts and prints the number of trees visible from outside the grid
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

        // counts and prints visible trees
        int count = 0;
        for(int i = 0; i < trees.length; i++) {
            for(int j = 0; j < rowLen; j++) {
                if(isVisible(trees, i, j)) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * Returns whether or not the tree at row row and column col of the 2D array
     * of trees is visible from outside
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return whether or not the tree is visible
     */
    private static boolean isVisible(int[][] trees, int row, int col) {
        if(isVisibleLeft(trees, row, col) || isVisibleTop(trees, row, col) ||
                isVisibleRight(trees, row, col) || isVisibleBottom(trees, row, col))
            return true;
        return false;
    }

    /**
     * Returns whether or not the tree at row row and column col of the 2D array
     * of trees is visible from the left
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return whether or not the tree is visible from the left
     */
    private static boolean isVisibleLeft(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        for(int i = 0; i < col; i++) {
            if(trees[row][i] >= thisTreeHeight)
                return false;
        }
        return true;
    }

    /**
     * Returns whether or not the tree at row row and column col of the 2D array
     * of trees is visible from the top
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return whether or not the tree is visible from the top
     */
    private static boolean isVisibleTop(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        for(int i = 0; i < row; i++) {
            if(trees[i][col] >= thisTreeHeight)
                return false;
        }
        return true;
    }

    /**
     * Returns whether or not the tree at row row and column col of the 2D array
     * of trees is visible from the right
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return whether or not the tree is visible from the right
     */
    private static boolean isVisibleRight(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        for(int i = col + 1; i < trees[row].length; i++) {
            if(trees[row][i] >= thisTreeHeight)
                return false;
        }
        return true;
    }

    /**
     * Returns whether or not the tree at row row and column col of the 2D array
     * of trees is visible from the bottom
     * 
     * @param trees the given 2D array of trees
     * @param row the row of the tree in question
     * @param col the column of the tree in question
     * @return whether or not the tree is visible from the bottom
     */
    private static boolean isVisibleBottom(int[][] trees, int row, int col) {
        int thisTreeHeight = trees[row][col];
        for(int i = row + 1; i < trees.length; i++) {
            if(trees[i][col] >= thisTreeHeight)
                return false;
        }
        return true;
    }
}
