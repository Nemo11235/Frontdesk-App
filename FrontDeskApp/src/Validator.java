import javax.swing.*;

public class Validator { 
	// Method to validate doubles
	public static double validateDouble(String prompt, double min, double max) {
		boolean isValid = false;
		double data = 0.0;

		while (!isValid) {
			try {
				String intStr = JOptionPane.showInputDialog(prompt);
				data = Double.parseDouble(intStr);

				if (data < min || data > max) // less than min or greater than max
					throw new Exception(); // Throw the exception
				isValid = true;
			}
			// End of try

			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Data input error.  Please enter a decimal data");

			} // End of catch
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Input data out of the range error. Please check the data and try again");

			}
		} // End of while
		return data;
	} // End of validateDouble()

	// Method to validate int
	public static int validateInt(String prompt, int min, int max) {
		boolean isValid = false;
		int data = 0;

		while (!isValid) {
			try {
				String intStr = JOptionPane.showInputDialog(prompt);
				data = Integer.parseInt(intStr);

				if (data < min || data > max) // less than min or greater than max
					throw new Exception(); // Throw the exception

				isValid = true;
			}
			// End of try
			catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Data input error.  Please enter an integer");
			} // End of catch
			catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Input data out of the range error. Please check the data and try again");

			}
		} // End of while
		return data;
	} // End of validateInt()
	
	// Method to validate String
	public static String validateString(String prompt) {
		boolean isValid = false;
		String input = ""; 
		while (!isValid) {
			try {
				input = JOptionPane.showInputDialog(prompt);
				if (input.equals("y") || input.equals("n")) { // if the inputs are valid
					isValid = true;

				} else // if they are not valid
					throw new Exception();

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null,
						"Input data was not either \"y\" or \"n\", please enter either \"y\" or \"n\"");
			}

		} //end of loop
		return input;
	} // end of validateString

} // End of Validator3
