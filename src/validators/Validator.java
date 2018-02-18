package validators;

public class Validator {

	public static boolean validName(String fName, String lName) {
		if(fName.isEmpty() || fName.isEmpty() && fName.length() + fName.length() > 100) {
			return false;
		}
		
		return true;
	}
	
	public static boolean validGender(String gender) {
		if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") ||  
				gender.equalsIgnoreCase("u")) {
			return true;	
		}
		
		return false;
	}
}
