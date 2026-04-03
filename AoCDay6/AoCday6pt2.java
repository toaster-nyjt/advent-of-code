import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.lang.StringBuilder;

/**
 * Main Class for AoC Day 6 Part 2
 * 
 * @author Tony Li
 * Bugs: None
 */
public class AoCday6pt2 {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(cephalopod("AoC_day6_input.txt"));
    }

    /**
     * Does math cephalopod style
     * @param filename name of the file
     * @return cephalopod's math problem answer
     */
    public static long cephalopod(String filename) {
        long finalResult = 0;

        // Initialize arraylists
        ArrayList<Integer> rangeArr = getRangeArr(filename);
        ArrayList<ArrayList<Character>> charArr = inputTo2DCharArr(filename);

        int problemIdx = 0; // Index of rangeArr
        int problemSize = 0; // Range of problem
        boolean isMult = false; // Whether problem is *
        ArrayList<Long> problemNums = new ArrayList<>(); // Nums in problem

        // Loop through columns of charArr
        for (int i = 0; i <= charArr.get(0).size(); i++) {
            // Check if digits of problem have been exhaused (and not first)
            if (problemSize == 0) {

                // Check for initial index 
                if (i != 0) {
                    i ++; // Skip over free space 
                    problemIdx ++; // Goto next problem

                    long result = doMath(problemNums, isMult); // Do actual math
                    finalResult += result; // Add it to the final sum

                    System.out.println(problemNums + " Multiplication: " 
                        + isMult + " Result: " + result);
                    System.out.println();
                }
                // If is out of bounds (ie last problem has been solved)
                if (i == charArr.get(0).size() + 1) {
                    break; // Stop loop
                }
                // Reinitialize vars
                problemSize = Math.abs(rangeArr.get(problemIdx)); 
                isMult = (rangeArr.get(problemIdx) < 0); 
                problemNums.clear();

            }
            
            // Build the numbers
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < charArr.size() - 1; j++) {
                sb.append(charArr.get(j).get(i));
            }
            // Add it to the current arr for this problem
            problemNums.add(Long.parseLong(sb.toString().trim()));

            problemSize --;
        }

        return finalResult;
    }

    /**
     * Finds the distance in spaces between symbols in the last line of input,
     * If negative: Symbol was multiplication, if positive: Symbol was addition
     * @param filename the name of the file
     * @return ArrayList<Integer> of the ranges
     */
    public static ArrayList<Integer> getRangeArr(String filename) {
        try (
            Scanner input = new Scanner(new File(filename));
        ) {
            // Get the last line (the one with +'s and *'s)
            String lastLine = "";
            while (input.hasNextLine()) {
                lastLine = input.nextLine();
            }

            // Keep track of ranges (number of columns in use per computation) 
            ArrayList<Integer> rangeArray = new ArrayList<>();
            int range = -1; // Indicates starting a new cnt
            int isMult = -1; // Indicates whether * or +

            for (int i = 0; i < lastLine.length(); i++) {
                // If char is * or +
                boolean isSymbol = ((lastLine.charAt(i) == '+') 
                    || (lastLine.charAt(i) == '*'));

                // Adds range to the array, decrements idx for new range cnt
                if (range != -1 && isSymbol) {
                    rangeArray.add((range - 1) * isMult); //-1 for col of spaces
                    range = -1;
                    i --;
                }
                // Otherwise increment range
                else {
                    if (range == -1) {
                        isMult = (lastLine.charAt(i) == '*') ? -1 : 1;
                    }
                    range = Math.max(1, ++range); //Max needed for range = -1
                }
            }
            // Adds final cnt
            rangeArray.add(isMult * range);
            return rangeArray;

        // Handle FNFE
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Initializes a 2D char array of the input
     * @param filename the name of the file
     * @return 2D char array
     */
    public static ArrayList<ArrayList<Character>> inputTo2DCharArr(String filename) {

        ArrayList<ArrayList<Character>> arr = new ArrayList<>();
        try (
            Scanner input = new Scanner(new File(filename));
        ) {
            while (input.hasNextLine()) {
                String line = input.nextLine();
                ArrayList<Character> row = new ArrayList<>();

                // Creates the row
                for (char c : line.toCharArray()) {
                    row.add(c);
                }
                arr.add(row); // Adds the row
            }

            // for (ArrayList<Character> cArr : arr) {
            //     for (char c : cArr) {
            //         System.out.print(c + " ");
            //     }
            //     System.out.println();
            // }

            return arr;

        // Handle FNFE
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    } 

    /**
     * If isMult is true, multiplies the numbers; otherwise add them
     * @param nums the list of numbers
     * @param isMult whether to multiply (or to add)
     * @return result
     */
    public static long doMath(ArrayList<Long> nums, boolean isMult) {
        long result;
        if (isMult) {
            result = 1;
            for (long num : nums) {
                result *= num;
            }
        } 
        else {
            result = 0;
            for (long num : nums) {
                result += num;
            }
        }
        return result;
    }

}