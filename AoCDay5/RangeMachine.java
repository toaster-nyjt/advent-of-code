import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Uses the Range Class to merge a list of possibly overlapping ranges into the 
 * smallest possible set of dijoint ranges
 * For AoC Day 5 Part 2
 * 
 * @Author Tony Li
 * Bugs: None
 */
public class RangeMachine {

    private String fileName; // Contains the ranges in a txt file

    /**
     * Constructor that takes in the name of the file
     * @param fileName
     */
    public RangeMachine(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Takes in the input file and returns an array of ranges
     * @param fileName the name of the input file
     * @return an array of ranges
     */
    private ArrayList<Range> fileToRangeArrayList() {
        // create range array
        ArrayList<Range> rangeArray = new ArrayList<>();

        // create scanner
        try (Scanner sc = new Scanner(new File(this.fileName))) {

            while (sc.hasNextLine()) {
                String line = sc.nextLine();

                // If line is an empty line, break out of loop
                if (line.equals("")) {
                    break;
                }

                // Convert line to range and add to array
                String[] convert = line.split("-");
                Range tempRange = new Range(Long.parseLong(convert[0]), Long.parseLong(convert[1]));
                rangeArray.add(tempRange);

            }
        // catch exceptions
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rangeArray;
    }

    /**
     * Takes in a list of Ranges, minimizes and merges them if they overlap,
     * and returns the total size of the distinct ranges.
     * @param rArr the range arraylist to be manipulated
     * @return the tol of the sizes of each distinct range
     */
    public void totalSumDistinctRange() {
        // Create the list of ranges from the input file
        ArrayList<Range> rArr = fileToRangeArrayList();

        // Create a new list of distinct ranges and populate with the first range
        ArrayList<Range> rDarr = new ArrayList<>();
        rDarr.add(rArr.get(0));

        // Loop through the unfiltered range list
        for (int i = 0; i < rArr.size(); i++) {
            // Store the current range object
            Range compRange = rArr.get(i);

            // Loop through distinct range list
            for (int j = 0; j < rDarr.size(); j++) {
                // Store the current distinct range object
                Range distinctRange = rDarr.get(j);

                // Stores the merged ranges in newRange
                Range newRange = checkIfOverlap(compRange, distinctRange);

                // Checks if there has been a merge
                if (newRange != null) {
                    System.out.println("\nThe ranges: \n" + compRange + " and " + distinctRange 
                    + " overlap and merge to form " + newRange);
                    // Make the current range the new merged range
                    compRange = newRange;
                    // Remove the distinct range since it is now part of compRange
                    rDarr.remove(j);
                    // Account for the index shifting from remove method
                    j --;

                }
            }

            // Add modified (or not modified) compRange to the distict list
            rDarr.add(compRange);

        }

        long total = totalSumRange(rDarr);
        System.out.println("\n\n\nA total of " + rArr.size() + " ranges have been merged into " 
        + rDarr.size() + " ranges and their combined size is " + total + "\n");
        
    } 

    /**
     * Returns the sum of the sizes of the ranges from a list of ranges
     * @param rArr the list of ranges
     * @return the total sum of the sizes
     */
    private long totalSumRange(ArrayList<Range> rArr) {
        long sum = 0;
        System.out.println("\n\n\nMerged Ranges:" + "\n");

        // Sum up the sizes of the range objects
        for (int i = 0; i < rArr.size(); i++) {
            System.out.println(rArr.get(i) + "\n");

            // Account for the fact that the ranges are inclusive
            sum += rArr.get(i).getSize() + 1; 
        }

        return sum;
    }

    /**
     * Given two range objects, checks if they overlap (or have the same bound)
     * If they do, return a new Range object as the union of the two ranges
     * @param r1 the first range
     * @param r2 the second range
     * @return a new range if they overlap, null if they don't
     */
    private Range checkIfOverlap(Range r1, Range r2) {

        // Sorts the bounds int ascending order
        ArrayList<Long> boundArr = sortBoundsAscending(r1, r2);

        // If the largest distance between two bounds <= the combined size
        if (boundArr.get(3) - boundArr.get(0) <= r1.getSize() + r2.getSize()) {
            // The ranges must overlap, so return new Range object
            return new Range(boundArr.get(0), boundArr.get(3));
        }

        return null;

    }

    /**
     * Sorts the min and max bounds of two ranges in ascending order
     * @param r1 first range
     * @param r2 second range
     * @return an arraylist containing the sorted bounds
     */
    private ArrayList<Long> sortBoundsAscending(Range r1, Range r2) {

        // Populate boundArr with the bounds of the two ranges
        ArrayList<Long> boundArr = new ArrayList<>();
        boundArr.add(r1.getMin());
        boundArr.add(r1.getMax());
        boundArr.add(r2.getMin());
        boundArr.add(r2.getMax());

        // whether there has been movement
        boolean numHasMoved = true; 

        // if nothing was moved on the previous sort, stop sorting
        while (numHasMoved) {
            numHasMoved = false;

            // use the first element as first comparison each time
            long compare = boundArr.get(0); // comparison var
            int idx = 0;

            // loop through the arraylist
            for (int i = 1; i < boundArr.size(); i++) {
                // To sort ascending, switch elements if the next one is smaller
                if (boundArr.get(i) < compare) {
                    long temp = boundArr.remove(i);
                    boundArr.add(i, compare);
                    boundArr.set(idx, temp); 

                    // there has been movement in list
                    numHasMoved = true;
                }

                // change compare to be this current element
                compare = boundArr.get(i);
                idx = i;
            }
        }
        
        return boundArr;

    }
}
