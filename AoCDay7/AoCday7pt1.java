import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Main Class for AoC Day 7 Part 1
 * 
 * @author Tony Li
 * Bugs: None
 */
public class AoCday7pt1 {

    /**
     * Main method
     * @param args
     */
    public static void main (String[] args) {
        simulate(getBlueprint("AoC_day7_input.txt"));
    }

    /**
     * Builds the 2D array
     * @param filename the name of the file
     * @return 2D arraylist of the input
     */
    public static ArrayList<ArrayList<Character>> getBlueprint(String filename) {
        try (
            Scanner input = new Scanner(new File(filename));
        ) {
            ArrayList<ArrayList<Character>> charArr = new ArrayList<>();

            // Assemble 2D list 
            while (input.hasNextLine()) {
                String line = input.nextLine();
                ArrayList<Character> charRow = new ArrayList<>();

                // Assemble the rows
                for (char c : line.toCharArray()) {
                    charRow.add(c);
                }
                charArr.add(charRow); 

            }

            return charArr;
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    /**
     * Simulates the beam splitting using the 2D character array
     * @param simArr the 2D array used to simulate
     */
    public static void simulate(ArrayList<ArrayList<Character>> simArr) {
        // Counts the number of time the beam is split
        int count = 0;

        // Locations (indexes) of beams
        ArrayList<Integer> beamLocs = new ArrayList<>();
        // Initialize the source of the first beam
        int initialBeamLoc = simArr.get(0).indexOf('S');
        beamLocs.add(initialBeamLoc);

        // Loop through 2D array rows
        for (int i = 1; i < simArr.size(); i++) {
            // Temp beam loc arraylist
            ArrayList<Integer> beamLocsToAdd = new ArrayList<>();

            // Loop through the beam array
            for (int j = 0; j < beamLocs.size(); j++) {
                int loc = beamLocs.get(j);
                char curr = simArr.get(i).get(loc);

                // Check if beam is split
                if (curr == '^') {
                    count ++; // Increment count

                    // Add beams to sides of spliter
                    simArr.get(i).set(loc - 1, '|'); // Decorative
                    simArr.get(i).set(loc + 1, '|');

                    // Check if there is already a beam for each direction
                    if (!(beamLocsToAdd.contains(loc + 1)) 
                        && (simArr.get(i - 1).get(loc + 1) != '|')) {
                        beamLocsToAdd.add(loc + 1); // If not, add to temp arr
                    }
                    if (!(beamLocsToAdd.contains(loc - 1)) 
                        && (simArr.get(i - 1).get(loc - 1) != '|')) {
                        beamLocsToAdd.add(loc - 1);
                    }

                    beamLocs.remove(j); // Remove the split beam
                    j --;

                } else {
                    // Simulates a beam going straight
                    simArr.get(i).set(loc, '|');
                }

            }
            // Adds the temp array of the split beams from current level 
            beamLocs.addAll(beamLocsToAdd);
            print(simArr);
        }

        // Prints the finished beam diagram and the number of splits
        print(simArr);
        System.out.println("Number of splits: " + count);
    }

    /**
     * Helper function to print a 2D character array
     * @param charArr the array to print
     */
    private static void print(ArrayList<ArrayList<Character>> charArr) {
        for (ArrayList<Character> cArr : charArr) {
                for (char c : cArr) {
                    System.out.print(c);
                }
                System.out.println();
        }
    }
}
