import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;;

public class day1Part2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day1Input.txt"));
        int currElfCalories = 0;
        int maxElfCalories = 0;
        int secondMostElfCalories = 0;
        int thirdMostElfCalories = 0;
        while(input.hasNext()) {
            String nextVal = input.nextLine();
            if(nextVal.equals("")) {
                if(currElfCalories > maxElfCalories) {
                    thirdMostElfCalories = secondMostElfCalories;
                    secondMostElfCalories = maxElfCalories;
                    maxElfCalories = currElfCalories;
                } else if(currElfCalories > secondMostElfCalories) {
                    thirdMostElfCalories = secondMostElfCalories;
                    secondMostElfCalories = currElfCalories;
                } else if(currElfCalories > thirdMostElfCalories) {
                    thirdMostElfCalories = currElfCalories;
                }
                currElfCalories = 0;
            } else {
                int numCalories = Integer.parseInt(nextVal);
                currElfCalories += numCalories;
            }
        }
        System.out.println(maxElfCalories + secondMostElfCalories + thirdMostElfCalories);
    }
}
