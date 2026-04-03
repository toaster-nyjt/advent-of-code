import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Advent of Code Day 4 Part 2
 * @author Tony
 * Bugs: None known
 */
public class AoCday4pt2 {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Maximum number of rolls possible to be removed: "
         + countMaxRolls(buildChar2DArray("AoC_day4_input.txt")));
    }

    /**
     * Builds a 2D ArrayList of characters from the input file
     * @param fileName The name of the file 
     * @return A 2D ArrayList of characters
     * @throws FileNotFoundException
     */
    public static ArrayList<char[]> buildChar2DArray(String fileName) {
        // Create 2D ArrayList of characters from input file
        ArrayList<char[]> char2DArray = new ArrayList<>();

        // Read input file
        try (Scanner input = new Scanner(new File(fileName))) {

            // Read each line of the file and convert to char array
            while(input.hasNextLine()) {
                String line = input.nextLine();
                char[] charArray = line.toCharArray();

                // Add char array to 2D ArrayList
                char2DArray.add(charArray);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found - " + e.getMessage());
        }

        return char2DArray;
    
    }

    /**
     * Counts the number of '@' characters in the 2D ArrayList that
     * have less than 4 adjacent '@' characters and marks them in an int array
     * @param char2DArray The 2D ArrayList of characters
     * @return The count of valid '@' characters
     */
    public static int[][] countAndMarkValidAdjacents(ArrayList<char[]> char2DArray) {
        int[][] intArray = new int[char2DArray.size()][char2DArray.get(0).length + 1];
        int count = 0;

        // Iterate through each char array in the 2D ArrayList
        for (int i = 0; i < char2DArray.size(); i++) {

            // Iterate through each character in the char array
            for (int j = 0; j < char2DArray.get(i).length; j++) {
                int numAdj = 0;

                // Check if the current character is '@'
                if (char2DArray.get(i)[j] != '@') {
                    continue;
                }

                // Check character top left
                if (i - 1 >= 0 && j - 1 >= 0) {
                    if (char2DArray.get(i - 1)[j - 1] == '@') {
                        numAdj++;
                    }
                }

                // Check character top
                if (i - 1 >= 0) {
                    if (char2DArray.get(i - 1)[j] == '@') {
                        numAdj++;
                    }
                }

                // Check character top right
                if (i - 1 >= 0 && j + 1 < char2DArray.get(i).length) {
                    if (char2DArray.get(i - 1)[j + 1] == '@') {
                        numAdj++;
                    }
                }

                // Check character left
                if (j - 1 >= 0) {
                    if (char2DArray.get(i)[j - 1] == '@') {
                        numAdj++;
                    }
                }

                // Check character right
                if (j + 1 < char2DArray.get(i).length) {
                    if (char2DArray.get(i)[j + 1] == '@') {
                        numAdj++;  
                    }
                }

                // Check character bottom left
                if (i + 1 < char2DArray.size() && j - 1 >= 0) {
                    if (char2DArray.get(i + 1)[j - 1] == '@') {
                        numAdj++;
                    }
                }

                // Check character bottom
                if (i + 1 < char2DArray.size()) {
                    if (char2DArray.get(i + 1)[j] == '@') {
                        numAdj++;
                    }
                }

                // Check character bottom right
                if (i + 1 < char2DArray.size() && j + 1 < char2DArray.get(i).length) {
                    if (char2DArray.get(i + 1)[j + 1] == '@') {
                        numAdj++;
                    }
                }

                // If number of adjacent '@' characters is less than 4, count it
                // And remember its index in intArray
                if (numAdj < 4) {
                    intArray[i][j] = 1;
                    count ++;
                }

            }

        }

        //Record the removed amount in the last index of the first row
        intArray[0][intArray[0].length - 1] = count;
        return intArray;
    }

    /**
     * Removes the rolls of paper at given indexes
     * @param int[][] intArray
     * @param char2Darray the 2D array to be modified
     * @return the modified 2D array
     */
    public static ArrayList<char[]> removeRolls(int[][] intArray, ArrayList<char[]> char2DArray) {

        // Iterate through each char array in the 2D ArrayList
        for (int i = 0; i < char2DArray.size(); i++) {
            // Iterate through each character in the char array
            for (int j = 0; j < char2DArray.get(i).length; j++) {

                // Remove the roll of paper
                if (intArray[i][j] == 1) {
                    char2DArray.get(i)[j] = '.';
                }

            }
        }

        return char2DArray;
    }

    /**
     * Counts the max number possible of removed rolls of paper
     * @param char2DArray
     * @return the max number of removed rolls
     */
    public static int countMaxRolls(ArrayList<char[]> char2DArray) throws InterruptedException {
        int count = 0;

        // Iterate until the last possible roll of paper is removed
        while (true) {

            // Print the current 2D ArrayList
            for (int i = 0; i < char2DArray.size(); i++) {
                for (int j = 0; j < char2DArray.get(i).length; j++) {
                    System.out.print(char2DArray.get(i)[j]);
                }
                System.out.println();
            }
            System.out.println();

            // Store coords of rolls to be removed and the number of them
            int[][] intArray = countAndMarkValidAdjacents(char2DArray);
            // Remove the rolls
            char2DArray = removeRolls(intArray, char2DArray);
            // Update the count
            count += intArray[0][intArray[0].length - 1];

            // Add a delay for fun
            Thread.sleep(80);

            // Check if the last possible roll of paper has been removed
            if (intArray[0][intArray[0].length - 1] == 0) {
                break;
            }
        }

        return count;
    }

}
