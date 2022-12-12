/*
 * This file is for Advent of Code Day 2
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 2 Part 2
 */
public class day2Part2 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day2Input.txt";
    private static final String OPPONENT_ROCK = "A";
    private static final String OPPONENT_PAPER = "B";
    private static final String LOSE = "X";
    private static final String DRAW = "Y";
    private static final int WIN_SCORE = 6;
    private static final int DRAW_SCORE = 3;
    private static final int ROCK_SCORE = 1;
    private static final int PAPER_SCORE = 2;
    private static final int SCISSORS_SCORE = 3;

    /**
     * Calculates and prints total score given strategy guide.
     * 
     * @param args not used
     * @throws FileNotFoundException if INPUT_FILE doesn't exist
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(INPUT_FILE));
        int score = 0;

        while(input.hasNextLine()) {
            String opponentMove = input.next();
            String responseMove = input.next();
            if(responseMove.equals(LOSE)) {
                // we lose
                if(opponentMove.equals(OPPONENT_ROCK)) {
                    score += SCISSORS_SCORE;
                } else if(opponentMove.equals(OPPONENT_PAPER)) {
                    score += ROCK_SCORE;
                } else {
                    score += PAPER_SCORE;
                }
            } else if(responseMove.equals(DRAW)) {
                // we draw
                score += DRAW_SCORE;
                if(opponentMove.equals(OPPONENT_ROCK)) {
                    score += ROCK_SCORE;
                } else if(opponentMove.equals(OPPONENT_PAPER)) {
                    score += PAPER_SCORE;
                } else {
                    score += SCISSORS_SCORE;
                }
            } else {
                // we win
                score += WIN_SCORE;
                if(opponentMove.equals(OPPONENT_ROCK)) {
                    score += PAPER_SCORE;
                } else if(opponentMove.equals(OPPONENT_PAPER)) {
                    score += SCISSORS_SCORE;
                } else {
                    score += ROCK_SCORE;
                }
            }
        }

        System.out.println(score);
    }
}
