/*
 * Puzzle.java
 * Package: SudokuSolver
 * Author: Andrew Vehlies
 * Purpose: Provide a class for the puzzle
 *   as a whole.  Contains logic to check
 *   if a number may be valid for a cell
 */

package sudokusolver;

import java.io.*;
import java.util.*;

public class Puzzle {

    private ArrayList<Box> grid;

    // Puzzle(String filename)
    // creates a new Puzzle object from a file "puzzle<n>.txt"
    // loads the puzzle into an ArrayList
    public Puzzle(String filename) {
        grid = new ArrayList<Box>(81); // create 9x9 array

        // throw an exception if we can't read the input file
        try {
            BufferedReader inputStream = new BufferedReader(new FileReader(filename.concat(".txt")));
            for(int i=0;i<9;i++) {
                // loop through each line
                try {
                    String in = inputStream.readLine();
                    // replace X's with 0 since it's an int field
                    in = in.replace('X','0');
                    for(int j=0;j<9;j++) {
                        // get the j'th item in the string and parse
                        // it as an int to add to the grid
                        grid.add(new Box(Integer.parseInt(in.substring(j,j+1))));
                    }
                } catch (java.io.IOException ex) { }
            }
            try {
                inputStream.close();
            } catch (java.io.IOException ex) {
                System.out.println("Cannot close stream.");
            }
        } catch (java.io.FileNotFoundException ex) {
            System.out.println("Check that the format of the filename is puzzle<n>.txt");
        }
    }

    // printPuzzle()
    // prints the puzzle with pretty formatting so it's not just a blob
    // of numbers
    public void printPuzzle() {
        for(int i=0;i<9;i++) {
            for( int j=0;j<9;j++) {
                System.out.print(grid.get(i*9+j).getValue());
                if(j==2 || j==5)
                    System.out.print("|");
            }
            System.out.print("\n");

            if(i==2 || i == 5)
                System.out.print("---+---+---\n");
        }
    }

    // calculatePossibleValues()
    // this function processes possible values of each cell.
    public void calculatePossibleValues() {
        for(int x=0;x<9;x++) {
            for(int y=0;y<9;y++) { //loop through each row/column
                if( grid.get(x*9+y).getValue() > 0 )
                    // skip over any cells that already have their values
                    // filled in
                    continue;
                // calculate which square the current cell is in.
                int squareX = x/3;
                int squareY = y/3;

                for(int j=0;j<9;j++) {
                    if(j==y)
                        continue; // don't check against its own cell
                    int tempValue = grid.get(x*9+j).getValue();
                    // if the value exists in a cell in its row,
                    // mark it as not a possible value for that cell
                    if(tempValue!=0) {
                        grid.get(x*9+y).setPossibleValue(tempValue-1,false);
                    }
                }
                for(int i=0;i<9;i++) {
                    if(i==x)
                        continue;
                    int tempValue = grid.get(i*9+y).getValue();
                    if(tempValue!=0) {
                        grid.get(x*9+y).setPossibleValue(tempValue-1,false);
                    }
                }

                // loop through all cells in the current square
                for(int i=squareX*3;i<squareX*3+3;i++) {
                    for(int j=squareY*3;j<squareY*3+3;j++) {
                        if(i==x && j==y)
                            continue; // if it's the same cell, do not check
                        int tempValue = grid.get(i*9+j).getValue();
                        if(tempValue!=0) {
                            grid.get(x*9+y).setPossibleValue(tempValue-1,false);
                        }
                    }
                }
            }
        }
    }

    // debug()
    // prints debugging information for each cell in the puzzle
    public void debug() {
        for(int i=0;i<9;i++) {
            for( int j=0;j<9;j++) {
                System.out.println(i+","+j);
                System.out.println("Value: "+grid.get(i*9+j).getValue());
                System.out.println("Possible: "+grid.get(i*9+j).getPossibleValuesString());
                System.out.println("Number of Possible Values: "+grid.get(i*9+j).getNumberOfPossibleValues());
                System.out.println("");
            }
        }
    }

    // isComplete()
    // checks if the puzzle is completed by looping through each
    // cell and checking if the value is 0
    public boolean isComplete() {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(grid.get(i*9+j).getValue() == 0)
                    return false;
            }
        }
        return true;
    }

    // fillInValues()
    // loops through the cells and sets the value if there's only
    // one possible value in the cell, otherwise it just fills it
    // with 0, the default for unknown cell data.
    public void fillInValues() {
        for(int i=0;i<9;i++) {
            for(int j=0;j<9;j++) {
                if(grid.get(i*9+j).getValue() > 0)
                    continue;
                grid.get(i*9+j).setValue(grid.get(i*9+j).getPossibleValue());
            }
        }
    }

}
