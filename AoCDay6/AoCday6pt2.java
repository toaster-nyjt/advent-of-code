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
        cephalopod("AoC_day6_input.txt");
    }

    /**
     * Does math cephalopod style
     * @param filename name of the file
     * @return cephalopod's math problem answer
     */
    public static void cephalopod(String filename) {
        getRangeArr(filename);
        inputTo2DCharArr(filename);
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
            System.out.println(rangeArray);
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

            for (ArrayList<Character> cArr : arr) {
                for (char c : cArr) {
                    System.out.print(c + " ");
                }
                System.out.println();
            }

            return arr;

        // Handle FNFE
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }

    } 

}