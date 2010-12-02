/*
 * Main.java
 * Package: SudokuSolver
 * Author: Andrew Vehlies
 * Purpose: Loops through 1-4 and loads the puzzle into a
 *   Puzzle object and then as long as it's not complete,
 *   it will fill in values where it can, and then calculate
 *   possible values for the remaining cells.
 */

package sudokusolver;


public class Main {

    public static void main(String[] args) {

        for(int i=1;i<5;i++) {
            Puzzle newpuzz = new Puzzle("puzzle"+i);
            System.out.println("Puzzle "+i);
            System.out.println("Before:");
            newpuzz.printPuzzle();

            newpuzz.calculatePossibleValues();
            //newpuzz.debug();
            while(!newpuzz.isComplete())
            {
                newpuzz.fillInValues();
                newpuzz.calculatePossibleValues();
                //newpuzz.printPuzzle();
            }
            System.out.println("After:");
            newpuzz.printPuzzle();
            System.out.println("");

        }
        
    }



}
