import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Advent of Code Day 4 Part 1
 * @author Tony
 * Bugs: None known
 */
public class AoCday4pt1 {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        ArrayList<char[]> char2DArray = buildChar2DArray("AoC_day4_input.txt");
        int validAdjacents = countValidAdjacents(char2DArray);
        System.out.println("Number of valid adjacents: " + validAdjacents);
    }

    /**
     * Builds a 2D ArrayList of characters from the input file
     * @param fileName The name of the input file
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
     * have less than 4 adjacent '@' characters
     * @param char2DArray The 2D ArrayList of characters
     * @return The count of valid '@' characters
     */
    public static int countValidAdjacents(ArrayList<char[]> char2DArray) {
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
                if (numAdj < 4) {
                    count ++;
                }

            }

        }

        return count;
    }

}
