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
 * Version 1.2 -- Description of Changes
 * In version 1.2 of the Commission Calculator, the program now asks for the names,
 * salaries, and total sales of two salespeople and compares them.  Not only does it
 * list the possible future earnings for each, but it also displays how much more 
 * the lesser of the two earners would have to make so that they at least match the
 * annual compensation of the higher earner.
 */

public class Calculations
{
	String[] personNames = new String[2]; // array for salesperson names
	double[] personCompensation = new double[2]; // array for salesperson compensation
	
	private int arrayIndex; // index counter for salesperson names and compensation
	
	private double userSalary; // salary of user
	private double userSales; // annual sales of user
	private double userCommission; // commission of user
	private double futureLimit; // declared for use in future earnings table
	private double compensationDifference; // stores the difference between sales persons being compared
	
	final private double SALES_TARGET = 150000.0; // sales target for everyone
	final private double COMMISSION_START = SALES_TARGET * 0.8; // amount of sales that determines no commission
	final private double COMMISSION_ACCELERATION = 0.2; // commission accelerator based on exceeds sales target
	final private double COMMISSION_RATE = 0.1; // constant commission rate
	final private double COMMISSION_PERCENT = COMMISSION_RATE * 100; // constant commission rate as a percentage
	final private double COMMISSION_A_PERCENT = COMMISSION_ACCELERATION * 100; // constant accelerated commission as a percent
	
	final private String futureSales = "Sales"; // for use in future earnings header
	final private String futureEarnings = "Annual Earnings"; // for use in future earnings header
	
	// method to set the user name
	public void setName(String name)
	{
		personNames[arrayIndex] = name; // store user name
	} // end method setName
	
	// method to return the user name
	public String getName()
	{
		return personNames[arrayIndex];
	} // end method getName
	
	// display a welcome message to the user
	public void displayMessage()
	{
		System.out.printf("Welcome to the Commission Calculation Program.\n\n"
				+ "The current sales target for every salesperson is currently"
				+ " $%.2f, \nand the commission incentive will not kick in until"
				+ " 80 percent of those sales \nare met.  However, if sales exceed the"
				+ " target, commission will \nincrease based on accelerator factor"
				+ " of %.0f percent.\n\nAt the end of the program, each salesperson's"
				+ " annual compensation will be \ndisplayed, and the program will compare"
				+ " them to determine who made more, \nand how much the other must make"
				+ " to match the other's annual compensation.\n\n", SALES_TARGET, COMMISSION_A_PERCENT);
	} // end method displayMesage
	
	// method to set user salary
	public void setSalary(double salaryValue)
	{
		userSalary = salaryValue; // store user salary
	} // end method setSalary
	
	// method to set user sales
	public void setSales(double sales)
	{
		userSales = sales; // store user sales
	} // end method setSales
	
	// method to set user commission
	public void setCommission()
	{
		// check sales to determine commission rate
		if (getSales() < COMMISSION_START)
			userCommission = 0; // store no user commission
		else if (getSales() > SALES_TARGET)
			userCommission = userSales * COMMISSION_ACCELERATION; // store accelerated user commission
		else
			userCommission = userSales * COMMISSION_RATE; // store normal user commission
		// end commission check block
	} // end method setCommission
	
	// method to set user annual compensation
	public void setCommissionSalary()
	{
		personCompensation[arrayIndex] = getSalary() + getCommission(); // set user annual compensation
	} // end method setCommissionSalary
	
	// method to set limit for earnings table
	public void setFutureLimit()
	{
		futureLimit = getSales() * 1.5; // set futureLimit to 150% of total sales
	} // end method setFutureLimit
	
	// method to return user salary
	public double getSalary()
	{
		return userSalary;
	} // end method getSalary
	
	// method to return user sales
	public double getSales()
	{
		return userSales;
	} // end method getSales
	
	// method to return user commission
	public double getCommission()
	{
		return userCommission;
	} // end method getCommission
	
	// method to return user commission with salary
	public double getCommissionSalary()
	{
		return personCompensation[arrayIndex];
	} // end method getCommissionSalary
	
	// method to return future limit for earnings table
	public double getFutureLimit()
	{
		return futureLimit;
	} // end method getFutureLimit

	// display the user's salary, sales, and annual compensation based on their sales
	public void displayCommission()
	{
		System.out.printf("\nThe salary entered was $%.2f.\n", getSalary());
		System.out.printf("The amount in sales entered was $%.2f.\n", getSales());
		
		// condition block to display commission percent
		if (getSales() < COMMISSION_START)
			System.out.println("The commission rate is " + (int)(userCommission) + "%."); // display 0% for less than 80% of target sales
		else if (getSales() > SALES_TARGET)
			System.out.println("The commission rate is " + (int)(COMMISSION_A_PERCENT) + "%."); // display 20% for over 100% of target sales
		else
			System.out.println("The commission rate is " + (int)(COMMISSION_PERCENT) + "%."); // display 10% for at least 80% of target sales
		// end condition block
		
		System.out.printf("The commission earned based on sales is $%.2f.\n", getCommission());
		System.out.printf("The total annual compensation is $%.2f.\n\n", getCommissionSalary());
	} // end method displayCommission
	
	// display the user's potential future earnings up to 50% over current annual sales
	public void displayFutureEarnings()
	{
		System.out.printf("Here are %s's potential future earnings:\n\n"
				+ "%s%24s\n", personNames[arrayIndex], futureSales, futureEarnings); // print table header
		
		// starts iteration for future earnings table for 50% more than current sales
		do
		{
			getCommission(); // retrieves current commission rate
			
			System.out.printf("%.2f%14.2f\n", getSales(), getCommissionSalary()); // prints table values
			userSales = userSales + 5000; // add $5,000 increment to sales
			
			setCommission(); // sets commission rate based on $5,000 increment
			setCommissionSalary(); // refreshes commission + salary value
		}
		while (getSales() <= getFutureLimit());
		// end of iteration block for future earnings table
		
		arrayIndex++; // adds 1 to the arrayIndex counter
		
		System.out.println(""); // outputs a blank line
	} // end method displayFutureEarnings
	
	// compares user sales
	public void compareSales()
	{
		System.out.printf("The first salesperson, %s, earned $%.2f.\n"
				+ "The second salesperson, %s, earned $%.2f.\n", personNames[0], 
				personCompensation[0], personNames[1], personCompensation[1]);
		
		// condition statement to compare user sales
		if (personCompensation[0] < personCompensation[1])
		{
			compensationDifference = personCompensation[1] - personCompensation[0];
			System.out.printf("\n%s must earn at least $%.2f to match %s\'s earnings."
					+ "\n", 
					personNames[0], compensationDifference, personNames[1]);
		}
		else if (personCompensation[0] > personCompensation[1])
		{
			compensationDifference = personCompensation[0] - personCompensation[1];
			System.out.printf("\n%s must earn at least $%.2f to match %s\'s earnings."
					+ "\n", 
					personNames[1], compensationDifference, personNames[0]);
		}
		else
			System.out.printf("\n%s and %s have the same annual earnings.\n",
					personNames[0], personNames[1]);
		// end condition statement to compare sales
	} // end method compareSales
	
	// display closing message
	public void displayClose()
	{
		System.out.printf("\nThank you for using the Commission Calculation Program!");
	} // end method displayClose
} // end class Calculations
