
/**
 * JDBC Program
 * Version 1.0
 * 
 * @author Christopher Hardy
 * PRG/421
 * November 21st, 2015
 * 
 * JDBC Class
 * 
 * Description:
 * The JDBC class contains the core functionality of the program, including 
 * database interaction and operations based on user input.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBC {

	Scanner input = new Scanner(System.in); // create new input object of type
											// Scanner
	Animal animal = new Animal(); // create new animal object of type Animal

	private String mainChoice; // variable for main menu choice
	private String animalName; // variable for animal name
	private String animalColor; // variable for animal color
	private String attributeChoice; // variable for true or false attribute
									// value
	private String animalChoice; // variable for animal choice to display
	private String name; // variable for animal name display
	private String color; // variable for animal color display
	private String swim; // variable for animal swim display
	private String vertebrate; // variable for animal vertebrate display

	private boolean animalSwim; // variable for animal swim ability
	private boolean animalVertebrate; // variable for animal vertebrate
	private boolean FLAG = false;
	private boolean CHECK = false;

	final private String url = "jdbc:derby:AnimalDB"; // JDBC URL
	final private String sql = "insert into ANIMAL values (?, ?, ?, ?)"; // add
																			// animal
																			// variable
	final private String query = "select * from ANIMAL where ANIMALNAME = ?"; // select
																				// animal
																				// from
																				// database
	final private String delete = "drop table ANIMAL"; // delete ANIMAL table

	Connection conn = null; // connection variable
	Statement stmt = null; // statement variable
	PreparedStatement pstmt = null; // prepared statement variable

	/**
	 * method to establish connection with database
	 */
	public void createConnection() {
		try {
			System.out.printf("Establishing connection to database...\n");

			conn = DriverManager.getConnection(url + "; create = true");
			stmt = conn.createStatement();

			System.out.printf("Connection established\n");

			try {
				System.out.printf("\nChecking for table existence...\n");

				stmt.execute(
						"create table ANIMAL (ANIMALNAME CHAR(20), " + "ANIMALCOLOR CHAR(20), ANIMALSWIM CHAR(20), "
								+ "ANIMALVERTEBRATE CHAR(20), PRIMARY KEY(ANIMALNAME))");

				System.out.printf("Table created\n");
			} catch (SQLException exception) {
				System.out.printf("Table already exists\n");
				stmt.close(); // close stmt resource
			}

			stmt.close(); // close stmt resource
		} catch (SQLException exception) {
			System.out.print("Connection failed\n");
		}
	}

	/**
	 * method to clear the table
	 */
	public void clearTable() {
		try {
			System.out.printf("\nClearing table...\n");

			stmt = conn.createStatement();
			stmt.executeUpdate(delete);

			stmt.execute("create table ANIMAL (ANIMALNAME CHAR(20), " + "ANIMALCOLOR CHAR(20), ANIMALSWIM CHAR(20), "
					+ "ANIMALVERTEBRATE CHAR(20), PRIMARY KEY(ANIMALNAME))");

			stmt.close(); // close stmt resource
			System.out.printf("Table cleared\n");
		} catch (SQLException exception) {
			System.out.printf("Table could not be cleared\n");
		}

		selectOption(); // execute selectOption method
	}

	/**
	 * method to close connection with database
	 */
	public void shutdown() {
		// attempt to close the connection to the database
		try {
			if (stmt != null) {
				stmt.close(); // close stmt resource
			}
			if (conn != null) {
				conn.close(); // close connection
			}
		} catch (SQLException exception) {
			System.out.printf("\nConnection never established\n");
		}
	}

	/**
	 * method to add animals to database
	 */
	public void addToDB() {
		// attempt to add an animal to the database
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, animal.getName());
			pstmt.setString(2, animal.getColor());
			pstmt.setString(3, animal.getSwim());
			pstmt.setString(4, animal.getVertebrate());
			pstmt.executeUpdate();

			System.out.printf("\nAnimal successfully added!\n");
		} catch (SQLException exception) {
			System.out.printf("\nSorry, that animal already exists.\n");
		}

		selectOption(); // execute selectOption method
	}

	/**
	 * method to read information from the database
	 */
	public void readFromDB() {
		// attempt to execute database query
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, animalChoice);
			ResultSet result = pstmt.executeQuery();

			// check the result set for the animal
			while (result.next()) {
				name = result.getString(1);
				color = result.getString(2);
				swim = result.getString(3);
				vertebrate = result.getString(4);
				System.out.printf("\nType:  " + name + "\nColor:  " + color + "\nSwim:  " + swim + "\nVertebrate:  "
						+ vertebrate + "\n");
			}

		} catch (SQLException exception) {
			System.out.printf("\nError procesing query\n");
		}

		selectOption(); // execute selectOption method
	}

	/**
	 * method to allow users to select an option from a Main Menu
	 */
	public void selectOption() {
		System.out.printf("\nMain Menu\n" + "\n1. Add an animal\n" + "2. Display an animal\n" + "3. Clear the table\n"
				+ "0. Exit program\n" + "\nWhat would you like to do? (Use numbers for selections):  ");
		mainChoice = input.nextLine(); // read a line of text

		while (!FLAG) {
			// switch block for user choice
			switch (mainChoice) {
			case ("1"):
				addAnimal(); // execute addAnimal method
				addToDB(); // execute addToDB method
				break;

			case ("2"):
				displayAnimal(); // execute displayAnimal method
				readFromDB(); // execute readFromDB method
				break;

			case ("3"):
				clearTable(); // execute clearTable method
				break;

			case ("0"):
				FLAG = true;
				break;

			default:
				System.out.printf("\nSorry, that is not a valid choice.  Please select an option. ");
				mainChoice = input.nextLine(); // read a line of text
			}
		}

		FLAG = false; // reset FLAG
	}

	/**
	 * method to get user information on a given animal
	 */
	public void addAnimal() {
		System.out.printf("\nEnter the type of animal you want to add:  ");
		animalName = input.nextLine(); // read a line of text
		System.out.printf("\nWhat color is the animal?:  ");
		animalColor = input.nextLine(); // read a line of text
		System.out.printf("\nCan the animal swim?:  ");
		attributeChoice = input.nextLine(); // read a line of text

		while (!CHECK) {
			switch (attributeChoice) {
			case ("yes"):
				animalSwim = true;
				CHECK = true;
				break;

			case ("no"):
				animalSwim = false;
				CHECK = true;
				break;

			default:
				System.out.printf("\nPlease enter \"yes\" or \"no\":  ");
				attributeChoice = input.nextLine(); // read a line of
													// text
			}
		}

		CHECK = false; // reset CHECK

		System.out.printf("\nDoes the animal have a vertebrate?:  ");
		attributeChoice = input.nextLine(); // read a line of text

		while (!CHECK) {
			switch (attributeChoice) {
			case ("yes"):
				animalVertebrate = true;
				CHECK = true;
				break;

			case ("no"):
				animalVertebrate = false;
				CHECK = true;
				break;

			default:
				System.out.printf("\nPlease enter \"yes\" or \"no\":  ");
				attributeChoice = input.nextLine(); // read a line of
													// text
			}
		}

		CHECK = false; // reset CHECK

		animal.setName(animalName); // execute setName method
		animal.setColor(animalColor); // execute setColor method
		animal.setSwim(animalSwim); // execute setSwim method
		animal.setVertebrate(animalVertebrate); // execute setVertebrate
												// method
	}

	/**
	 * method for user animal selection
	 */
	public void displayAnimal() {
		System.out.printf("\nEnter the name of the animal you wish to display:  ");
		animalChoice = input.nextLine(); // read a line of text
	}
}
