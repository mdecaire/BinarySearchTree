/**
 * this class is a customized exception class that shows error messages for
 * invalid input
 * 
 * @author Michelle Decaire due 12/3/201
 *
 */

public class NumFormatException extends NumberFormatException {
	// class level access so Jpanel can print message
	protected String mess = "";
	private static final long serialVersionUID = 1L;

	public NumFormatException(String string) {
		mess = string;
	}

	public String ToString() {

		return mess;
	}

}
