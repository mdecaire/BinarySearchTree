/**
 * this class is the fraction class, it checks the syntax of the fraction, it
 * overrides the comparable method by getting and comparing values of each
 * fraction
 * 
 * @author Michelle Decaire due 12/3/2017
 *
 */
public class Fraction implements Comparable<Fraction> {
	private String value;
	private double comp;

	// constructor one
	public Fraction() {
		value = null;

	}

	// constructor two
	public Fraction(String input) {
		value = input;
		if (!checkFractNum()) {
			throw new NumFormatException("Invalid formatting for type fraction");
		}
		comp = getValue();
	}

	/**
	 * verifies that the fractions are in the correct format
	 * 
	 * @param fractNum
	 * @return
	 */
	public boolean checkFractNum() {
		boolean isCorrect = true;
		int slashCount = 0;
		char[] fractChecker = value.toCharArray();
		try {
			for (char ch : fractChecker) {
				if (ch == '/') {
					slashCount++;
				}
				if (slashCount >= 2) {
					isCorrect = false;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return isCorrect;
	}

	/**
	 * Compare to method gets the numeric value of each fraction and compares them
	 * before sending the value back to the bst
	 */
	@Override
	public int compareTo(Fraction node) {
		int value;
		if (this.comp == node.comp) {
			value = 0;
		} else if (this.comp < node.comp) {
			value = -1;
		} else {
			value = 1;
		}
		return value;
	}

	/**
	 * this method parses each fraction and then calculates the value and returning
	 * a double
	 * 
	 * @return
	 */
	private double getValue() {
		double tokenVal = 0;
		String[] eachFrac = null;
		try {
			eachFrac = value.split("/+");
			double num1 = Double.parseDouble(eachFrac[0]);
			double num2 = Double.parseDouble(eachFrac[1]);
			tokenVal = num1 / num2;
		} catch (Exception e) {

		}
		return tokenVal;
	}

	public String toString() {
		return value;
	}

}
