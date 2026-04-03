import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * AoCday5pt1
 * @Author: Tony Li 
 */
public class AoCday5pt1 {

    /**
     * Main method to run
     * @param args
     */
    public static void main(String[] args) {

        // Create range array
        ArrayList<String> input = fileToRangeArray("AoC_day5_input.txt");

        // Create number array
        ArrayList<Long> numberArray = fileToNumberArray("AoC_day5_input.txt");

        // Check if number is in range
        System.out.println("Total numbers in range: " + checkIfInRange(input, numberArray));
        
    }

    /**
     * Takes in the input file and returns an array of ranges
     * @param fileName the name of the input file
     * @return an array of ranges
     */
    public static ArrayList<String> fileToRangeArray(String fileName) {
        // create range array
        ArrayList<String> rangeArray = new ArrayList<String>();

        // create scanner
        try (Scanner sc = new Scanner(new File(fileName))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // If line is an empty line, break out of loop
                if (line.equals("")) {
                    break;
                }

                // Add line to range array
                rangeArray.add(line);

            }
        // catch exceptions
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rangeArray;
    }

    /**
     * Takes in the input file and returns an array of numbers to check
     * @param fileName the name of the input file
     * @return an array of numbers
     */
    public static ArrayList<Long> fileToNumberArray(String fileName) {
        // create number array
        ArrayList<Long> numberArray = new ArrayList<>();

        // create scanner
        try (Scanner sc = new Scanner(new File(fileName))) {
            
            // Advance past first empty line
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // If line is empty, break out of loop
                if (line.equals("")) {
                    break;
                }
            }
            
            // Add lines to number array
            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // Add line to number array
                numberArray.add(Long.parseLong(line));
            }

        // catch exceptions
        } catch (IOException e) {
            e.printStackTrace();
        }

        return numberArray;
    }

    /**
     * Checks if a number is in a range
     * @param rangeArray the array of ranges
     * @param numberArray the array of numbers
     * @return number of times a number is in a range
     */
    public static int checkIfInRange(ArrayList<String> rangeArray, ArrayList<Long> numberArray) {
        int count = 0;

        // Loop through number array
        for (int i = 0; i < numberArray.size(); i++) {
            // Loop through range array
            for (int j = 0; j < rangeArray.size(); j++) {
                // Split range into min and max
                String range = rangeArray.get(j);
                String[] rangeSplit = range.split("-");
                long min = Long.parseLong(rangeSplit[0]);
                long max = Long.parseLong(rangeSplit[1]);

                // Check if number is in range, if it is, break out of loop
                if (numberArray.get(i) >= min && numberArray.get(i) <= max) {
                    System.out.println(numberArray.get(i) + " is in range " + range);
                    count++;
                    break;
                }

                // Prints message if number is not in any range
                if (j == rangeArray.size() - 1) {
                    System.out.println(numberArray.get(i) + " is not in any range");
                }

            }
        }

        return count;
    }
}