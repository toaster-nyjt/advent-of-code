import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.StringBuilder;

/**
 * Advent of Code Day 3 part 1
 * Bugs: None known
 * @author Tony 
 */
public class AoCday3pt2 {
    // Main method
    public static void main(String[] args) throws FileNotFoundException {
        calculateFromFile("AoC_day3_input.txt", 12);
    }


    /**
     * Calculates the maximum and second maximum digits in the input string.
     * @param input the input string containing digits
     * @param numDigits the number of digits to consider from the input
     * @return the number formed by the maximum and second maximum digits
     */
    public static long calculateMax(String input, int numDigits) {
        StringBuilder sb = new StringBuilder();

        // Initialize length of input and index of maximum digit found so far
        int length = input.length();
        int indexMax = -1;

        // Loop to find each of the numDigits maximum digits
        for (int i = 0; i <= numDigits - 1; i++) {
            int max = -1;

            // Find the maximum digit from the index of the last maximum found
            for (int k = indexMax + 1; k <= (length - (numDigits - i)); k++) {
                // Compute maximum digit and its index
                int charInt = Character.getNumericValue(input.charAt(k));
                if (charInt > max) {
                    max = charInt;
                    indexMax = k;
                }
            }
            // Append the found maximum digit to the result
            sb.append(max);
        }

        long result = Long.parseLong(sb.toString());
        System.out.println("Max: " + result);
        return result;
        
    }

    /**
     * Calculates max number from each line in a file and sums the results.
     * @param filename the name of the file containing input strings
     */
    public static long calculateFromFile(String filename, int numDigits) {
        long sum = 0;

        try {
            // Open the file
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            // Read each line and calculate 
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                sum += calculateMax(line, numDigits);
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


