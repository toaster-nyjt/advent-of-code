import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Advent of Code Day 3 part 1
 * Bugs: None known
 * @author Tony 
 */
public class AoCday3pt1 {
    // Main method
    public static void main(String[] args) throws FileNotFoundException {
        calculateFromFile("AoC_day3_input.txt");
    }


    /**
     * Calculates the maximum and second maximum digits in the input string.
     * @param input the input string containing digits
     * @return the number formed by the maximum and second maximum digits
     */
    public static int calculateMax(String input) {
        // Initialize variables to track max and second max digits
        int max = -1;
        int secondMax = -1;
        int maxIdx = -1;

        // Find the maximum digit and its index
        for (int i = 0; i < input.length() - 1; i++) {
            int charInt = Character.getNumericValue(input.charAt(i));
            if (charInt > max) {
                max = charInt;
                maxIdx = i;
            }
        }

        // Find the second maximum digit after the maximum digit's index
        for (int i = maxIdx + 1; i < input.length(); i++) {
            int charInt = Character.getNumericValue(input.charAt(i));
            if (charInt > secondMax) {
                secondMax = charInt;
            }
        }

        // Combine max and second max into a single integer
        int result = Integer.parseInt("" + max + secondMax);
        System.out.println("Max: " + result);

        return result;
        
    }

    /**
     * Calculates max number from each line in a file and sums the results.
     * @param filename the name of the file containing input strings
     */
    public static int calculateFromFile(String filename) {
        int sum = 0;

        try {
            // Open the file
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Read each line and calculate 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += calculateMax(line);
            }
            scanner.close();

        } catch (FileNotFoundException e) {

            System.out.println("File not found: " + filename);
            e.printStackTrace();

        }

        System.out.println("Total Sum: " + sum);
        return sum;
    }

}

