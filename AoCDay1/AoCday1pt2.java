import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Advent of Code Day 1 part 2
 * Bugs: None known
 * @author Tony 
 */
public class AoCday1pt2 {

    /**
     * Main method for Advent of Code Day 1 challenge
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        //creates file from puzzle input and opens scanner
        File file = new File("AoC_day1_input.txt");
        Scanner input = new Scanner(file);

        //creates array list to store lock data
        ArrayList<Integer> data = new ArrayList<>();

        //reads in each line of the input file
        while (input.hasNextLine()) {
            String line = input.nextLine();

            //stores amount
            int amount = Integer.parseInt(line.trim()
            .substring(1).trim());

            //stores negative if dial is turned left
            if (Character.toUpperCase(line.trim().charAt(0)) == 'L') {
                amount = -amount;
            }

            //adds amount to data
            data.add(amount);
        }

        System.out.println(data);

        //closes input
        input.close();

        //initializes starting value
        int value = 50;
        //counts the number of times the dial passes or lands on 0
        int passCount = 0;

        //processes each data point
        for (int dataInt : data) {

            //initializes pass count for this update
            int tempCount = 0;
            //the modded data value before update
            int modDataInt = dataInt % 100;

            //add the number of full rotations
            tempCount += Math.abs(dataInt) / 100;

            //checks if dial passes 0
            if ((Math.abs(value) + Math.abs(modDataInt) >= 100) &&
                !((Math.abs(value) > Math.abs(modDataInt)) &&
                (Math.abs(value + modDataInt))
                < (Math.abs(value) + Math.abs(modDataInt)))) {

                tempCount += 1;
            } 
            else if (Math.abs(value) + Math.abs(modDataInt) < 100 &&
                    (Math.abs(value) <= Math.abs(modDataInt)) &&
                    (Math.abs(value + modDataInt) 
                    < (Math.abs(value) + Math.abs(modDataInt)))) {

                tempCount += 1;
            }
            
            //updates value
            value += dataInt;

            //calculates mod 100 value
            int modInt = (value < 0) ? (((value % 100) + 100) % 100) 
            : (value % 100);
            value = modInt;
            
            //adds pass count to total count
            passCount += tempCount;

            System.out.println("Current value: " + value + 
            " | Passes this update: " + tempCount);

        }

        System.out.println("Number of times dial passes 0: " + passCount);
    }
}
