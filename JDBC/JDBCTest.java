
/**
 * JDBC Program Version 1.0
 * 
 * @author Christopher Hardy PRG/421 November 21st, 2015
 * 
 *         Main Class
 * 
 *         Description: The JDBC program allows the user to add and display
 *         animal objects from a database. The user is able to select from a
 *         variety of options to manipulate the database, including adding
 *         animals, displaying animals, and clearing the table. The program will
 *         obtain user input regarding the animal type, color, swim ability, and
 *         vertebrate attribute, and then process the given animal into the
 *         database. The entry will be rejected if the animal exists (by use of
 *         table Primary key). As many animals as necessary can be stored.
 * 
 *         When the user wants to display an animal, they simply select the
 *         display option and enter the name of the animal they want to see. The
 *         program will process each animal in the database and only output the
 *         animals that contain that name (limited to one by the Primary Key).
 *         The user may then close the database and exit the program using the
 *         "0" sentinel value.
 */

public class JDBCTest {

	/**
	 * main method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Messages messages = new Messages(); // create new messages object of
											// type Messages
		JDBC jdbc = new JDBC(); // create new jdbc object of type JDBC

		jdbc.createConnection(); // execute createConnection method
		messages.welcomeMessage(); // execute welcomeMesssage method
		jdbc.selectOption(); // execute selectOption method
		messages.endMessage(); // execute endMessage method
		jdbc.shutdown(); // execute shutdown method
	}
}
