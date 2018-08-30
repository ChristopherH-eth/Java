
/**
 * JDBC Program Version 1.0
 * 
 * @author Christopher Hardy PRG/421 November 21st, 2015
 * 
 *         Animal Class
 *
 *         Description: The Animal Class provides the basis for which all Animal
 *         objects are created and contains all key attribute variables.
 */

public class Animal {

	private String name; // variable to store animal name
	private String color; // variable to store animal color
	private String swim; // variable to store animal swim ability
	private String vertebrate; // variable to store true or false value of
								// animal vertebrate

	/**
	 * setter for name
	 * 
	 * @param animalName
	 */
	public void setName(String animalName) {
		name = animalName;
	}

	/**
	 * setter for color
	 * 
	 * @param animalColor
	 */
	public void setColor(String animalColor) {
		color = animalColor;
	}

	/**
	 * setter for swim
	 * 
	 * @param animalSwim
	 */
	public void setSwim(boolean animalSwim) {
		// check swim ability
		if (animalSwim)
			swim = "true";
		else
			swim = "false";
	}

	/**
	 * setter for vertebrate
	 * 
	 * @param animalVertebrate
	 */
	public void setVertebrate(boolean animalVertebrate) {
		// check animal vertebrate
		if (animalVertebrate)
			vertebrate = "true";
		else
			vertebrate = "false";
	}

	/**
	 * getter for name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter for color
	 * 
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * getter for swim
	 * 
	 * @return swim
	 */
	public String getSwim() {
		return swim;
	}

	/**
	 * getter for vertebrate
	 * 
	 * @return vertebrate
	 */
	public String getVertebrate() {
		return vertebrate;
	}
}
