
/**
 * Horse Race Program
 * Version 1.0
 * 
 * @author Christopher Hardy
 * PRG/421
 * November 30th, 2015
 * Janet Borzuchowski
 * 
 * Race Class
 * 
 * Description:  The Horse Race Program focuses on the use of threads to essentially
 * have two different horses race against each other with the first horse to have their
 * name printed 1,000 times being the winner.  The user must enter two unique horse names
 * to execute the startRace method.  Once this is done, two thread representing the horses
 * are executed to count each horse to 1,000.  Processing ends for each thread once their
 * count method reaches 1,000, and the portion of the program that the user controls is
 * essentially put on hold until the race is over for proper output display.
 * 
 * The output provided to the user is each horses name 1,000 times, and once one horse's
 * name is printed the full 1,000 times, the program then outputs the winner of the race
 * to the user and thanks the user for using the program.
 */

import java.util.Scanner;

public class Race {

	public static void main(String args[]) {
		Core core = new Core();

		core.welcomeMessage();
		core.enterNames();
		core.startRace();
	}
}

/**
 * Core Class
 * 
 * Description: The Core class provides core functionality aspects to the
 * program, including instantiating the RaceHorse threads, receiving user input,
 * processing user input, and outputing the resulting thread information.
 */
class Core {

	private String choice;
	private String horseName = "";

	private boolean FLAG = true;
	private boolean THREAD = true;
	private boolean WINNER = true;

	Scanner input = new Scanner(System.in);
	RaceHorse rh1 = new RaceHorse(horseName);
	RaceHorse rh2 = new RaceHorse(horseName);

	/**
	 * method to welcome the user and provide brief program description
	 */
	public void welcomeMessage() {
		System.out.printf("Welcome to the Horse Race Program!\n\n"
				+ "This program allows you to enter the names of two competing horses\n"
				+ "so that they may race against each other.  The winner is the horse\n"
				+ "that has their name printed 1,000 times first.\n");
	}

	/**
	 * method to thank the user for using the program
	 */
	public void endMessage() {
		System.out.printf("\nThank you for using the Horse Race Program!");
	}

	/**
	 * method to establish RaceHorse threads
	 */
	public void enterNames() {
		System.out.printf("\nPlease enter the name of horse #1:  ");
		horseName = input.nextLine();
		rh1.setHorseName(horseName);

		// check for valid race horse 1 name
		while (FLAG) {
			if (rh1.getHorseName().isEmpty() || rh1.getHorseName().equals(null)) {
				System.out.printf("\nSorry, but the horse's name must contain a value.\n"
						+ "Please enter the name of horse #1:  ");
				horseName = input.nextLine();
				rh1.setHorseName(horseName);
			}

			else
				break;
		}

		rh1.setName(rh1.getHorseName());

		System.out.printf("\nPlease enter the name of horse #2:  ");
		horseName = input.nextLine();
		rh2.setHorseName(horseName);

		// check for valid race horse 2 name
		while (FLAG) {
			if (rh1.getHorseName().equals(rh2.getHorseName())) {
				System.out.printf("\nSorry, but the horses cannot have the same name.\n"
						+ "Please enter the name of horse #2:  ");
				horseName = input.nextLine();
				rh2.setHorseName(horseName);
			}

			else if (rh2.getHorseName().isEmpty() || rh2.getHorseName().equals(null)) {
				System.out.printf("\nSorry, but the horse's name must contain a value.\n"
						+ "Please enter the name of horse #2:  ");
				horseName = input.nextLine();
				rh2.setHorseName(horseName);
			}

			else
				break;
		}

		rh2.setName(rh2.getHorseName());
	}

	/**
	 * method to prompt user to start the race
	 */
	public void startRace() {

		System.out.printf("\nEnter 1 to start the race or 0 to exit the program:  ");
		choice = input.nextLine();

		// check user input
		while (FLAG) {
			switch (choice) {
			case ("1"):
				THREAD = true;
				FLAG = false;
				break;

			case ("0"):
				THREAD = false;
				FLAG = false;
				break;

			default:
				THREAD = false;

				System.out.printf(
						"\nSorry, that input is invalid.\n" + "Enter 1 to start the race or 0 to exit the program:  ");
				choice = input.nextLine();
				break;
			}
		}

		FLAG = true; // reset FLAG

		if (THREAD) {
			rh1.start();
			rh2.start();

			// check for race winner
			while (FLAG) {
				if ((rh1.getState() == Thread.State.TERMINATED)) {
					WINNER = true;
					break;
				} else if ((rh2.getState() == Thread.State.TERMINATED)) {
					WINNER = false;
					break;
				}
			}

			// check for thread termination
			while (FLAG) {
				if ((rh1.getState() == Thread.State.TERMINATED) && (rh2.getState() == Thread.State.TERMINATED)) {

					if (WINNER)
						System.out.printf("\n" + rh1.getName() + " is the winner!\n");
					else
						System.out.printf("\n" + rh2.getName() + " is the winner!\n");

					break;
				}
			}
		}

		endMessage();
	}
}