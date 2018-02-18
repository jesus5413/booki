package validators;

public class Validator {

	public static boolean invalidName(String fName, String lName) {
		if(fName.isEmpty() || fName.isEmpty() && fName.length() + fName.length() > 100) {
			return true;
		}
		
		return false;
	}
}
