/**
 * @author      Michael Boettner
 *              E01474622
 *              COSC 314
 *              Programming Assignment #1
 * @version     1.0
 * Driver Class containing main method and helper methods to generate and
 * list all subsets of the set {1, 2, ... , n}, where n is specified by user.
 */

import java.util.Scanner;
import java.lang.Math;
import java.lang.Integer;
public class Driver {
    
    /**
    * Keyboard input
    */
    static Scanner keyboard = new Scanner(System.in);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
        * Number of elements in the set (user specified)
        */
        int setSize;
        
        /**
        * Controls program loop to allow entry of different set sizes
        */
        boolean runAgain = true;
        
        /**
        * User input to decide whether to run program again
        */
        char userAnswer;
        
        System.out.println("***************************************");
        System.out.println("*  COSC 314 Programming Assignment 1  *");
        System.out.println("*          Michael Boettner           *");
        System.out.println("***************************************");
        
        while(runAgain)
        {   
            System.out.println();
            System.out.print("Enter set size (positive integer): ");
        
            setSize = keyboard.nextInt();
            
            //call method to display set elements
            printSet(setSize);
        
            //call method to generate and list all possible subsets
            generateSubsets(setSize);
            
            System.out.println();
            System.out.print("Run again? (y/n): ");
            
            userAnswer = keyboard.next().charAt(0);
            
            if(userAnswer != 'y' && userAnswer != 'Y')
                runAgain = false;
        }
    }
    
    /**
     * Formats and prints all members of the set
     * @param setSize user-specified size of set
     */   
    public static void printSet(int setSize){
        
        System.out.print("The set: {");
        
        for(int i = 0; i < setSize; i++)
        {
            System.out.print(i+1);
            
            //test to only include commas for inner items
            if(i < (setSize - 1))
            {
                System.out.print(", ");
            }
        }
        System.out.println("}");   
    }
    
    /**
     * Generates, formats, and prints all subsets 
     * @param setSize user-specified size of set
     */
    public static void generateSubsets(int setSize){
        
        /**
        * Array to store the binary representation of a single subset
        */
        int[] setArray = new int[setSize];
        
        /**
        * Calculated number of subsets (2^n)
        */
        int numSubsets = (int)Math.pow(2, setSize);
        
        /**
        * Counts the number of items in each subset. Used to format the
        * output with the correct number of commas
        */
        int itemCounter;
        
        /**
        * The calculated difference between the number of places needed in the
        * binary representation (n), and the length of the length of the
        * converted bit string.
        */
        int difference;
        
        /**
        * Used to reference the characters in the binary String
        */
        int stringIndex;
        
        /**
        * Stores the binary representation of each subset
        */
        String binaryRep;
        
        System.out.println();
        System.out.println("All subsets:");
        System.out.println();
        
        //first loop generates binary representation for each subset
        for(int i = 0; i < numSubsets; i++)
        {
           //convert each integer to a binary string
           binaryRep = Integer.toBinaryString(i);
           
                /*Since the binary conversion is not formatted with padded
                    zeroes, care must be taken to line them up accordingly
                    with their proper n-digit binary place. The difference
                    between the number of desired binary places and the length
                    of converted binary string gives us the correct starting
                    insertion point in the array*/
                difference = setSize - binaryRep.length(); 
                
                stringIndex = 0;
                
                itemCounter = 0;
                
                /*Since the binary conversion is not formatted with padded
                    zeroes, care must be taken to line them up accordingly
                    with their proper n-digit binary place. We must start
                    inserting into the array at the calculated difference*/
                for(int j = difference; j < setSize; j++)
                {
                    /*insert a 0 or 1 into the array
                        (subtract 48 for ascii conversion)*/
                    setArray[j] = (int)binaryRep.charAt(stringIndex) - 48;
                    
                    //test if value exists at current index
                    if(setArray[j] == 1)
                    {
                        itemCounter++;
                    }
                    
                    stringIndex++;
                }
                
                //Format and print the current subset
                System.out.print("{");
                
                if(itemCounter == 0)
                {
                    System.out.print("empty");
                }
                else
                {
                    for(int k = 0; k < setSize; k++)
                    {
                        if(setArray[k] == 1)
                        {
                            System.out.print((k+1));
                            
                            //determine comma insertion
                            if(itemCounter > 1)
                            {
                                System.out.print(", ");
                                itemCounter--;
                            }                       
                        }
                    }
                }
                System.out.println("}");
        }
    }
}

/*
run:
***************************************
*  COSC 314 Programming Assignment 1  *
*          Michael Boettner           *
***************************************

Enter set size (positive integer): 3
The set: {1, 2, 3}

All subsets:

{empty}
{3}
{2}
{2, 3}
{1}
{1, 3}
{1, 2}
{1, 2, 3}

Run again? (y/n): y

Enter set size (positive integer): 4
The set: {1, 2, 3, 4}

All subsets:

{empty}
{4}
{3}
{3, 4}
{2}
{2, 4}
{2, 3}
{2, 3, 4}
{1}
{1, 4}
{1, 3}
{1, 3, 4}
{1, 2}
{1, 2, 4}
{1, 2, 3}
{1, 2, 3, 4}

Run again? (y/n): n
BUILD SUCCESSFUL (total time: 5 seconds)
*/