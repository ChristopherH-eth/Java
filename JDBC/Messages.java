
/**
 * JDBC Program Version 1.0
 * 
 * @author Christopher Hardy PRG/421 November 21st, 2015
 * 
 *         Messages Class
 *
 *         Description: The Messages Class is used to display simple messages to
 *         the user that require no response.
 */

public class Messages {

	/**
	 * method to welcome the user and provide brief program description
	 */
	public void welcomeMessage() {
		System.out.printf("\nWelcome to the JDBC Animal Program!\n\n"
				+ "This program allows you to add a variety of animals to a database table"
				+ "\nand then allows you to display them by entering their name.  These"
				+ "\nentries remain stored even after the program is closed.");
	}

	/**
	 * method to thank the user for using the program
	 */
	public void endMessage() {
		System.out.printf("\nThank you for using the JDBC Animal Program!");
	}
}
