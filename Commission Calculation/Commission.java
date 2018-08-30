/**
 * Commission Calculation Program
 * Version 1.2
 * 
 * @author Christopher Hardy
 * Java Programming I - PRG/420
 * October 5th, 2015
 * 
 * Version 1.0 -- Description
 * This program is designed to calculate the total compensation a person will receive
 * based on their salary and a constant commission percentage.  They will then be
 * displayed at the end of the program.
 * 
 * Version 1.1 -- Description and Changes
 * In version 1.1 of the Commission Calculator, a commission threshold of 80% of
 * target sales has been added.  In addition, if a salesperson achieves over 100% of
 * the target sales, they will earn an accelerated commission rate of 20%.  Finally,
 * a future earnings table has been added for up to 50% over the salesperson's
 * current sales, showing them their potential earnings (commission plus salary)
 * to be earned at $5,000 increments.
 * 
 * Version 1.2 -- Description and Changes
 * In version 1.2 of the Commission Calculator, the program now asks for the names,
 * salaries, and total sales of two salespeople and compares them.  Not only does it
 * list the possible future earnings for each, but it also displays how much more 
 * the lesser of the two earners would have to make so that they at least match the
 * annual compensation of the higher earner.
 */

import java.util.Scanner; // import Scanner object for user input

public class Commission 
{	
	// begin main method
	public static void main(String[] args)
	{		
		Scanner input = new Scanner(System.in); // create a Scanner for user input
		
		Calculations myCalculations = new Calculations(); // create a Calculations object and assign it to myCalculations
		
		myCalculations.displayMessage(); // call myCalculation's displayMessage method
		
		// condition block for more than one salesperson
		for (int personCount = 0; personCount < 2; personCount++)
		{
			// prompt user for name and retrieve input
			if (personCount == 0)
				System.out.print("Please enter the first salesperson's name:  ");
			else
				System.out.print("Please enter the next salesperson's name:  ");
				
			String name = input.nextLine(); // read a line of text
			myCalculations.setName(name);
						
			// prompt user for salary and retrieve input
			System.out.print("\nPlease enter your salary:  $");
			double salary = input.nextDouble(); // read a double
			
			// validate user input for non-negative salary
			while (salary < 0)
			{
				System.out.print("Invalid input, please enter your salary:  $");
				salary = input.nextDouble(); // read a double
			} // end salary validation
			
			myCalculations.setSalary(salary); // execute setSalary method
			
			// prompt user for annual sales and retrieve input
			System.out.print("Please enter your annual sales:  $");
			double sales = input.nextDouble(); // read a double
			
			// validate user input for non-negative sales
			while (sales < 0)
			{
				System.out.print("Invalid input, please enter your sales:  $");
				sales = input.nextDouble(); // read a double
			} // end sales validation
		
			myCalculations.setSales(sales); // execute setSales(sales) method
			myCalculations.setFutureLimit(); // execute setFutureLimit method
			myCalculations.setCommission(); // execute setCommission(sales) method
			myCalculations.setCommissionSalary(); // execute setCommissionSalary method
			
			myCalculations.displayCommission(); // call myCalculation's displayCommission method
			myCalculations.displayFutureEarnings(); // call myCalculation's displayFutureEarnings method
			
			input.nextLine(); // clears keyboard buffer for next String input
		} // end condition block for more than one sales person
		
		myCalculations.compareSales(); // call myCalculation's compareSales method
		myCalculations.displayClose(); // call myCalculation's displayClose method
		
		input.close(); // close Scanner input
	} // end main method
} // end class Commission
