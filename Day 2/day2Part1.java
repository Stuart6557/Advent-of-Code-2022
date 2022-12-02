/*
 * This file is for Advent of Code Day 2
 */

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * This class is my solution for Day 2 Part 1
 */
public class day2Part1 {
    /* Constants (Magic numbers) */
    private static final String INPUT_FILE = "day2Input.txt";
    private static final String OPPONENT_ROCK = "A";
    private static final String OPPONENT_PAPER = "B";
    private static final String OPPONENT_SCISSORS = "C";
    private static final String RESPONSE_ROCK = "X";
    private static final String RESPONSE_PAPER = "Y";
    private static final String RESPONSE_SCISSORS = "Z";
    private static final int WIN = 1;
    private static final int DRAW = 0;
    private static final int LOSE = -1;
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
            int winOrNot = didWeWin(opponentMove, responseMove);
            if(winOrNot == WIN) {
                score += WIN_SCORE + scoreShapeSelected(responseMove);
            } else if(winOrNot == DRAW) {
                score += DRAW_SCORE + scoreShapeSelected(responseMove);
            } else {
                score += scoreShapeSelected(responseMove);
            }
        }

        System.out.println(score);
    }

    /**
     * Returns the result of a round of Rock Paper Scissors given the opponent's
     * move and our move.
     * 
     * @param opponentMove opponent's move: "A" for rock, "B" for paper, "C" for
     *                     scissors
     * @param responseMove our move: "X" for rock, "Y" for paper, "Z" for
     *                     scissors
     * @return 1 for win, 0 for tie, -1 for lose
     */
    private static int didWeWin(String opponentMove, String responseMove) {
        if(opponentMove.equals(OPPONENT_ROCK) && responseMove.equals(RESPONSE_ROCK)
                || opponentMove.equals(OPPONENT_PAPER) && responseMove.equals(RESPONSE_PAPER)
                || opponentMove.equals(OPPONENT_SCISSORS) && responseMove.equals(RESPONSE_SCISSORS))
            return DRAW;

        if(opponentMove.equals(OPPONENT_ROCK) && responseMove.equals(RESPONSE_PAPER)
                || opponentMove.equals(OPPONENT_PAPER) && responseMove.equals(RESPONSE_SCISSORS)
                || opponentMove.equals(OPPONENT_SCISSORS) && responseMove.equals(RESPONSE_ROCK))
            return WIN;

        return LOSE;
    }

    /**
     * Returns the the score for the selected shape.
     * 
     * @param shape our move: "X" for rock, "Y" for paper, "Z" for scissors
     * @return 1 for rock, 2 for paper, and 3 for scissors
     */
    private static int scoreShapeSelected(String shape) {
        if(shape.equals(RESPONSE_ROCK)) return ROCK_SCORE;
        if(shape.equals(RESPONSE_PAPER)) return PAPER_SCORE;
        return SCISSORS_SCORE;
    }
}
