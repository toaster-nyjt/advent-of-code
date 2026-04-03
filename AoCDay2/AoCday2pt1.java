import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

/**
 * Advent of Code Day 2 part 1
 * Bugs: None known
 * @author Tony 
 */
public class AoCday2pt1 {

    /**
     * Main method for Advent of Code Day 2 challenge
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        long totalOfDuplicates = sumAll();
        System.out.println("Total of duplicate numbers found: " 
        + totalOfDuplicates);
    }

    /**
     * Generates the total sum of duplicate numbers from input file
     * @return total sum of duplicate numbers
     * @throws FileNotFoundException
     */
    public static long sumAll() throws FileNotFoundException {
        // creates scanner object to read input file
        File file = new File("AoC_day2_input.txt");
        Scanner input = new Scanner(file);
        input.useDelimiter(",");
        long totalCount = 0;

        // loops through each line of input file
        while (input.hasNext()) {
            String line = input.next().trim();
            System.out.println("Processing range: " + line);
            totalCount += generateMatches(line);
        }
        input.close();

        return totalCount;
    }
    
    /**
     * Generates the sum of the matches within the given range
     * @param input
     * @return sum of matches within range
     */
    public static long generateMatches(String input) {
        long sum = 0;

        // Split input into lower and upper bounds
        String lowerS = input.split("-")[0].trim();
        String upperS = input.split("-")[1].trim();

        // Determine starting duplicate number based on lower bound
        int stringIdx = lowerS.length() / 2;
        // If lower bound length is odd, start with next power of 10
        // Else, start with first half of lower bound
        long duplicate = lowerS.length() % 2 == 1 
        ? (long)(Math.pow(10, stringIdx)) 
        : Long.parseLong(lowerS.substring(0, stringIdx));

        // Build the first duplicate number and store in StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.append(duplicate);
        sb.append(duplicate);

        // Loop until the built duplicate number exceeds upper bound
        while(Long.parseLong(sb.toString()) <= Long.parseLong(upperS)) {
            // Check if the built duplicate number is above or equal lower bound
            if (Long.parseLong(sb.toString()) >= Long.parseLong(lowerS)) {
                System.out.println(sb.toString());
                // Increment sum of valid duplicate numbers within range
                sum += Long.parseLong(sb.toString());
            }

            // Increment the first half to build the next duplicate number
            duplicate++;
            sb.setLength(0);
            sb.append(duplicate);
            sb.append(duplicate);
         
        }

        return sum;

    }
}