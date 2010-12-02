/*
 * Box.java
 * Package: SudokuSolver
 * Author: Andrew Vehlies
 * Purpose: Provide a class for each
 *   individual box inside of the Sudoku
 *   puzzle. Will hold information about
 *   current values and possible values
 *   that the box could have.
 */

package sudokusolver;

import java.util.*;


public class Box {
    // declarations
    private int value;
    private ArrayList<Boolean> possibleValues;
    // holds t/f for the possible values.
    // index ranges from 0-8.

    // methods

    // Box(int defaultValue)
    // Initializes the box (cell).
    // If there is a default value, then it has no possible values.
    // If there is no default value, then it can be "any" of the others.
    public Box(int defaultValue) {
        this.value = defaultValue;
        this.possibleValues = new ArrayList<Boolean>();
        if(this.value > 0) {
            for(int i=0;i<9;i++) {
                possibleValues.add(false);
            }
        } else {
            for(int i=0;i<9;i++) {
                possibleValues.add(true);
            }
        }
    }

    // getValue()
    // Returns the value of the box (cell)
    public int getValue() {
        return this.value;
    }

    // setValue(int newValue)
    // Sets the value of the box (cell)
    public void setValue(int newValue) {
        this.value = newValue;
    }

    // hasPossibleValue(int valueToCheck)
    // Returns t/f if the given number is a possible value.
    public boolean hasPossibleValue(int valueToCheck) {
        return possibleValues.get(valueToCheck-1);
    }

    // setPossibleValue(int index, boolean value)
    // Sets t/f for a possible value of the cell
    public void setPossibleValue(int index, boolean value) {
        possibleValues.set(index,value);
    }

    // getPossibleValuesString()
    // used for debugging to see which values are possible
    public String getPossibleValuesString() {
        String temp = "";
        for(int i=0;i<9;i++) {
            if(possibleValues.get(i) == true)
                temp = temp.concat(""+(i+1)+"");
            temp = temp.concat(" ");
        }
        return temp;
    }

    // getPossibleValue()
    // used to check if there's only one possible value
    // for the cell. if there is, it returns that value.
    public int getPossibleValue() {
        if( getNumberOfPossibleValues() == 1 ) {
            for(int i=0;i<possibleValues.size();i++) {
                if(possibleValues.get(i))
                    return i+1;
            }
        }
        return 0;
    }

    // getNumberOfPossibleValues()
    // returns the number of possible values of the cell
    public int getNumberOfPossibleValues() {
        int temp = 0;
        for(int i=0;i<possibleValues.size();i++) {
            if( possibleValues.get(i) )
                temp++;
        }
        return temp;
    }

}
