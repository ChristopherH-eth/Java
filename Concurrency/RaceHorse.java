
/**
 * Horse Race Program Version 1.0
 * 
 * @author Christopher Hardy PRG/421 November 30th, 2015 Janet Borzuchowski
 * 
 *         RaceHorse Class
 * 
 *         Description: The RaceHorse class extends Thread to allow for the use
 *         of Thread objects as race horses. It provides a constructor for horse
 *         names, as well as the run method which pulls information from the
 *         Atomic class and provides each Thread object with its "task."
 */

public class RaceHorse extends Thread {

	private String name;

	/**
	 * RaceHorse constructor
	 * 
	 * @param name
	 */
	RaceHorse(String name) {
		setName(name);
	}

	/**
	 * setter for name
	 */
	public void setHorseName(String horseName) {
		name = horseName;
	}

	/**
	 * getter for name
	 * 
	 * @return name
	 */
	public String getHorseName() {
		return name;
	}

	/**
	 * method that contains thread tasks
	 */
	public void run() {
		for (int i = 0; i < 1000; i++)
			System.out.println(Thread.currentThread().getName());
	}
}
