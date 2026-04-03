import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main Class for AoC Day 6 Part 1
 * 
 * @author Tony Li
 * Bugs: None
 */
public class AoCday6pt1 {
    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(doMath("AoC_day6_input.txt"));
    }

    /**
     * Adds or Multiplies numbers in vertical lines
     * @param filename the name of the file
     * @return the total of the column totals
     */
    public static long doMath(String filename) {

        // Create scanner for each line
        try (
            Scanner input = new Scanner(new File(filename));
        ) {
            // Store the lines of longs and line of +'s and *'s 
            ArrayList<ArrayList<Long>> longArr = new ArrayList<>();
            ArrayList<Character> charArr = new ArrayList<>();
            
            // Loop through each line
            while (input.hasNextLine()) {
                // Store the line as a string and advance to next line
                String currentLine = input.nextLine();
                // Create a Scanner for this line
                Scanner lineInput = new Scanner(currentLine);
                // Skip if line is empty
                if (currentLine.trim().isEmpty()) { continue; }

                // If the line doesn't have * or +, treat as a line of longs
                if (currentLine.indexOf("*") == -1 && currentLine.indexOf("+") == -1) {

                    // Create an arraylist 
                    ArrayList<Long> currentLongArr = new ArrayList<>();

                    // Add the longs to the arraylist
                    while (lineInput.hasNextLong()) {
                        currentLongArr.add(lineInput.nextLong());
                    }

                    // Add the arraylist to the 2D arraylist
                    longArr.add(currentLongArr);
                    lineInput.close();

                }
                // For the last line of +'s and *'s, store in seperate character array
                else {
                    // Add the symbols to the arraylist
                    while (lineInput.hasNext()) {
                        charArr.add(lineInput.next().charAt(0));
                    }
                    lineInput.close();
                }

            }

            // // Prints out the 2D arraylist
            // for (ArrayList<Long> lArr : longArr) {
            //     for (Long tempLong : lArr) {
            //         System.out.print(tempLong + " ");
            //     }
            //     System.out.println();
            // }
            // // Prints out the character arraylist
            // for (char c : charArr) {
            //     System.out.print(c + " ");
            // }
            // System.out.println();

            // // Perform the operations
            return parseLists(longArr, charArr);   
            
        // Handle FNFE
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    /**
     * Takes in the two arraylists and performs the operations
     * @param longArr 2D long arraylist
     * @param charArr specifies which operator to use per column
     * @return the total of the column totals
     */
    private static long parseLists(ArrayList<ArrayList<Long>> longArr, 
        ArrayList<Character> charArr) {
        // Create an arraylist for the column totals
        ArrayList<Long> sumArr = new ArrayList<>();

        // Loop through each column
        for (int i = 0; i < longArr.get(0).size(); i++) {
            long addSum = 0;
            long multSum = 1;

            // Loop through each row
            for (int j = 0; j < longArr.size(); j++) {
    
                // Decide which operation to do per column
                if (charArr.get(i).equals('+')) {
                    addSum += longArr.get(j).get(i);
                }
                else {
                    multSum *= longArr.get(j).get(i);
                }

            }
            
            sumArr.add(Math.max(addSum, multSum));
        }

        // Adds the totals up
        // System.out.println(sumArr);
        long finalSum = 0;
        for (long temp : sumArr) {
            finalSum += temp;
        }

        return finalSum;

    }

}