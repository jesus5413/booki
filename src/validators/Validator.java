package validators;

import java.util.Date;

import exception.InvalidDoBException;
import exception.InvalidGenderException;
import exception.InvalidNameException;
import exception.InvalidSiteException;

public class Validator {
	public static boolean validName(String name) throws InvalidNameException{
		if(name.isEmpty() || name.length() > 100) {
			throw new InvalidNameException("Name fields can't be empty");
		}
		
		return true;
	}
	
	public static boolean validGender(String gender) throws InvalidGenderException{
		if(gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("f") ||  
				gender.equalsIgnoreCase("u")) {
			return true;	
		}
		
		throw new InvalidGenderException("Gender can only be 'm', 'f', or 'u'");
	}
	
	public static boolean validSiteLength(String site) throws InvalidSiteException{
		if(site.length() > 100) {
			throw new InvalidSiteException("Website can't be more than 100 characters");
		}
		
		return true;
	}
	
	/**
	 * Checks database date against current date. Most currently living people are generally born before
	 * the future
	 * @param dbDate
	 * @return
	 */
	public static boolean validDate(Date dbDate) throws InvalidDoBException{
		Date today = new Date();
		
		if(dbDate == null) {
			throw new InvalidDoBException("Person can't be born in that time!");
		}
		
		if(dbDate.before(today)) {
			return true;
		}else {
			throw new InvalidDoBException("Person can't be born in that time!");
		}
	}
}
