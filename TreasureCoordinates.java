/* Nicole Walsh
 * Dr. Steinberg
 * COP3503 Fall 2024
 * Programming Assignment 2
 */

package all;

import java.util.ArrayList;

public class TreasureCoordinates {

	// Method to determine all possible coordinates
	public ArrayList<String> determineCoordinates(String coords) 
	{
		ArrayList<String> result = new ArrayList<>();
		String nums = coords.substring(1, coords.length()-1); // eliminates parantheses
		backtrack(result, nums, "", "", 0, false, false);
		return result;
	}
	
    private void backtrack(ArrayList<String> result, String nums, String x, String y, int index, boolean xHasDecimal, boolean yHasDecimal) 
    {
        // If entire string is checked, return if valid
        if (index == nums.length()) 
        {
            if (!x.isEmpty() && !y.isEmpty() && isValidNumber(x) && isValidNumber(y)) 
            {
                result.add("(" + x + ", " + y + ")"); // put in coordinate form
            }
            return;
        }

        // Add current digit to x-coordinate
        if (y.isEmpty()) 
        {
            backtrack(result, nums, x + nums.charAt(index), y, index + 1, xHasDecimal, yHasDecimal);
        }

        // Add current digit to y-coordinate
        if (!x.isEmpty()) 
        {
            backtrack(result, nums, x, y + nums.charAt(index), index + 1, xHasDecimal, yHasDecimal);
        }

        // Add decimal point to x-coordinate
        if (!xHasDecimal && x.length() >= 1 && index < nums.length() - 1) 
        {
            backtrack(result, nums, x + ".", y, index, true, yHasDecimal);
        }

        // Add decimal point to y-coordinate
        if (!yHasDecimal && y.length() >= 1 && index <= nums.length() - 1) 
        {
            backtrack(result, nums, x, y + ".", index, xHasDecimal, true);
        }
	}
    
    
    // Helper function to validate numbers (no leading/trailing decimals)
    private boolean isValidNumber(String num) 
    {
        // Eliminate leading/trailing decimal points
        if (num.startsWith(".") || num.endsWith(".")) 
        {
            return false;
        }
        
        // Eliminate leading zeros (i.e. "01") unless the number is "0.XX"
        if (num.startsWith("0") && num.length() > 1 && !num.startsWith("0.")) 
        {
            return false;
        }
        
        // Eliminate cases like "0.0", "0.00", and so on
        if (num.matches("0\\.0+")) {
            return false;
        }
        
        return true;
    }
	
    /*
    public static void main(String[] args) 
    {
        // Create an instance of TreasureCoordinates
        TreasureCoordinates tc = new TreasureCoordinates();

        // Example input string of digits
        String input = "(123)";

        // Call  determineCoordinates method and store result
        ArrayList<String> result = tc.determineCoordinates(input);

        // Print results
        for (String coordinate : result) {
            System.out.println(coordinate);
        }
    }
    
    */
}
