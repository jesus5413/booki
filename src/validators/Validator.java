package validators;

import java.util.Date;

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
	
	public static boolean validSiteLength(String site) {
		if(site.length() > 100) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Checks database date against current date. Most currently living people are generally born before
	 * the future
	 * @param dbDate
	 * @return
	 */
	public static boolean validDate(Date dbDate) {
		Date today = new Date();
		
		return dbDate.before(today);
	}
}
