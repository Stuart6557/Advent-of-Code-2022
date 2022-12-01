import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;;

public class day1part1 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File("day1.txt"));
        int currElfCalories = 0;
        int maxElfCalories = 0;
        while(input.hasNext()) {
            String nextVal = input.nextLine();
            if(nextVal.equals("")) {
                if(currElfCalories > maxElfCalories) {
                    maxElfCalories = currElfCalories;
                }
                currElfCalories = 0;
            } else {
                int numCalories = Integer.parseInt(nextVal);
                currElfCalories += numCalories;
            }
        }
        System.out.println(maxElfCalories);
    }
}
