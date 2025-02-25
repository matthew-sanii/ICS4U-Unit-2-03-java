/*
* This program generates 30 random numbers in an array
* and allows the user to search the array for a number.
*
* @author  Matthew Sanii
* @version 1.0
* @since   2021-01-12
*/

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
* This is the BinarySearch program.
*/
final class BinarySearch {

    /**
    * The min number for array.
    */
    private static final int MIN = 0;
    /**
    * The max number for array.
    */
    private static final int MAX = 999;
    /**
    * The number of elements in the array.
    */
    private static final int ARRAY_SIZE = 30;

    /**
    * Prevent instantiation
    * Throw an exception IllegalStateException.
    * if this ever is called
    *
    * @throws IllegalStateException
    *
    */
    private BinarySearch() {
        throw new IllegalStateException("Cannot be instantiated");
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param userArray parameter for user array.
    * @param userNumber parameter for user number.
    * @param lowIndex parameter for low end of index.
    * @param highIndex parameter for high end of index.
    * @return binarySearch
    */
    static int binarySearch(final int[] userArray, final int userNumber,
                            final int lowIndex, final int highIndex) {
        int result = -1;
        int check = 0;
        final int max = 29;
        final int ranges = 3;
        int middle = lowIndex + (highIndex / 2);
        if (middle > max) {
            middle = max;
        }
        final int spot = userArray[middle];
        final int mid = highIndex - lowIndex;
        if (mid <= ranges) {
            check = 1;
            for (int range = 0; range <= mid; range++) {
                if (middle + range > max || middle - range < 0) {
                    break;
                }
                else if (userArray[middle + range] == userNumber) {
                    result = middle + range;
                    break;
                }
                else if (userArray[middle - range] == userNumber) {
                    result = middle - range;
                    break;
                }
            }
        }
        else if (check == 0) {
            result = checker(userArray, userNumber, lowIndex, highIndex,
                                                        middle, spot);
        }
        return result;
    }

    /**
    * Function finds the index of a number, using Binary Search recursively.
    *
    * @param userArray parameter for user array.
    * @param userNumber parameter for user number.
    * @param lowIndex parameter for low end of index.
    * @param highIndex parameter for high end of index.
    * @param middle parameter for the middle value.
    * @param spot parameter for value at middle spot in array.
    * @return binarySearch
    */
    static int checker(final int[] userArray, final int userNumber,
                            final int lowIndex, final int highIndex,
                            final int middle, final int spot) {
        int results = -1;
        if (spot > userNumber) {
            results = binarySearch(userArray, userNumber, lowIndex, highIndex / 2);
        }
        else if (spot < userNumber) {
            results = binarySearch(userArray, userNumber, middle, highIndex);
        }
        else if (spot == userNumber) {
            results = middle;
        }
        return results;
    }

    /**
    * The starting main() function.
    *
    * @param args Name of file containing a string of numbers
    * @throws IOException when error occurs
    */
    public static void main(final String[] args) {
        System.out.println("Binary Search Program");
        try {
            int searchNumber = 0;
            final Random randNumber = new Random();
            final int[] randomNumberArray = new int[ARRAY_SIZE];
            for (int counter = 0; counter < randomNumberArray.length; counter++) {
                randomNumberArray[counter] = randNumber.nextInt(MAX) + 1;
            }
            final int[] numberArray = randomNumberArray;
            Arrays.sort(numberArray);
            System.out.print("\nSorted list of numbers:\n");
            for (int element: numberArray) {
                final String padded = String.format("%03d", element);
                System.out.print(padded + ", ");
            }
            System.out.print("\n\n");
            final Scanner userInput = new Scanner(System.in);
            System.out.print("What number are you searching for in the array");
            System.out.print(" (integer between 0 and 999): ");
            try {
                searchNumber = userInput.nextInt();
            }
            catch (InputMismatchException errorCode) {
                System.out.println("Error, invalid input (must be an integer)");
                System.exit(0);
            }
            userInput.close();
            System.out.println();
            if (searchNumber > MAX || searchNumber < MIN) {
                System.out.println("Error, invalid input");
                System.exit(1);
            }
            else {
                final int searchResult = binarySearch(numberArray, searchNumber,
                                            0, numberArray.length - 1);
                System.out.println();
                System.out.println("Your number is in index: " + searchResult);
            }
        }
        catch (NumberFormatException errorCode) {
            System.out.println();
            System.out.println("ERROR: Invalid Input");
        }
    }
}
