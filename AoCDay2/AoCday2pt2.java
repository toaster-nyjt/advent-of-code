import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Advent of Code Day 2 part 2
 * Bugs: None known
 * @author Tony 
 */
public class AoCday2pt2 {

    /**
     * Main method for Advent of Code Day 2 challenge
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        long totalSum = sumAll();
        System.out.println("Total sum of  numbers: " + totalSum);
    }

    /**
     * Generates the total sum of numbers made of repeated numbers input file
     * @return total sum of matched numbers
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
        StringBuilder sb = new StringBuilder();
        // To make sure we don't double count
        ArrayList<Long> matchedNumbers = new ArrayList<>();

        // Split input into lower and upper bounds
        String lowerS = input.split("-")[0].trim();
        String upperS = input.split("-")[1].trim();

        // Find the factors of the length of the lower bound
        ArrayList<Integer> lowerLengthFactors = generateFactors(lowerS.length());

        // Find the factors of the length of the upper bound, taking into account 
        // the difference in the number of digits between lower and upper
        ArrayList<ArrayList<Integer>> upperLengthFactorsList = new ArrayList<>();
        for (int i = 0; i < upperS.length() - lowerS.length(); i++) {
            upperLengthFactorsList.add(generateFactors(lowerS.length() + i + 1));
        }
        
        // Just a debug check (if upper # digits - lower # digits > 1)
        if (upperLengthFactorsList.size() > 1) {
            System.out.println("MAYDAY");
        }

        // Loop through all factors of lower length
        System.out.println("Processing lower bound matches");
        for (int i = 0; i < lowerLengthFactors.size(); i++) {

            // Create a string by repeating substrings of the first n 
            // characters of lower where n is each factor of lower length
            int factor = lowerLengthFactors.get(i);
            String block = lowerS.substring(0, factor);
            System.out.println("Processing factor: " + factor);

            // Loop through all constructions of blocks of given factor length
            while (Integer.parseInt(block) <= Math.pow(10, factor) - 1) {

                // Reset StringBuilder for new block
                sb.setLength(0); 
                // Repeat the block to build the full string of same length as lowerS
                for (int j = 0; j < lowerS.length() / factor; j++) {
                    sb.append(block);
                }

                // Check if the built string is within the range
                if (Long.parseLong(sb.toString()) >= Long.parseLong(lowerS) 
                    && Long.parseLong(sb.toString()) <= Long.parseLong(upperS)) {

                    //Avoid double counting
                    matchedNumbers.add(Long.parseLong(sb.toString()));
                    if (matchedNumbers.indexOf(Long.parseLong(sb.toString())) 
                        != matchedNumbers.lastIndexOf(Long.parseLong(sb.toString()))) {

                        // Increment block to build next possible string
                        block = Integer.toString(Integer.parseInt(block) + 1);
                        continue;
                    }
                    
                    // Increment sum of valid duplicate numbers within range
                    System.out.println("Valid match " + sb.toString());
                    sum += Long.parseLong(sb.toString());
                }

                // Increment block to build next possible string
                block = Integer.toString(Integer.parseInt(block) + 1);
                
            }
        }

        // If the length of the lower bound < upper bound, also process upper bound
        if (lowerS.length() < upperS.length()) {
            System.out.println("Lower # digits < Upper # digits");

            // Loop through all factors of upper length
            System.out.println("Processing upper bound matches");
            for (int k = 0; k < upperLengthFactorsList.size(); k++) {
                // Get current upper length factors for i + 1 digits more than lower
                ArrayList<Integer> upperLengthFactors = upperLengthFactorsList.get(k);
                System.out.println("Current # of digits: " + (lowerS.length() + k + 1));

                // And loop through each factor
                for (int i = 0; i < upperLengthFactors.size(); i++) {

                    // Create a string by repeating substrings of powers of 10 
                    // based on the factors of upper length
                    int factor = upperLengthFactors.get(i);
                    String block = Integer.toString((int)Math.pow(10, factor - 1));
                    System.out.println("Processing factor: " + factor);

                    // Loop through all constructions of blocks of given factor length
                    while (Integer.parseInt(block) <= Math.pow(10, factor) - 1) {

                        // Reset StringBuilder for new block
                        sb.setLength(0); 
                        // Repeat the block to build the full string of same length as upperS
                        for (int j = 0; j < (lowerS.length() + k + 1) / factor; j++) {
                            sb.append(block);
                        }

                        // Check if the built string is within the range
                        if (Long.parseLong(sb.toString()) >= Long.parseLong(lowerS) 
                            && Long.parseLong(sb.toString()) <= Long.parseLong(upperS)) {

                            //Avoid double counting
                            matchedNumbers.add(Long.parseLong(sb.toString()));
                            if (matchedNumbers.indexOf(Long.parseLong(sb.toString())) 
                                != matchedNumbers.lastIndexOf(Long.parseLong(sb.toString()))) {

                                // Increment block to build next possible string
                                block = Integer.toString(Integer.parseInt(block) + 1);
                                continue;
                            }
                            
                            // Increment sum of valid duplicate numbers within range
                            System.out.println("Valid match " + sb.toString());
                            sum += Long.parseLong(sb.toString());
                        }

                        // Increment block to build next possible string
                        block = Integer.toString(Integer.parseInt(block) + 1);
                    
                    }
                }
            }

        }

        return sum;

    }

    /**
     * Generates the factors of a given number not inluding the number itself
     * @param num
     * @return ArrayList of factors
     */
    public static ArrayList<Integer> generateFactors(long num) {
        ArrayList<Integer> factors = new ArrayList<>();
        // Loop through all numbers less than num to find factors
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }

        return factors;
    }

}
