package validators;

import java.util.Date;

import exception.InvalidDoBException;
import exception.InvalidGenderException;
import exception.InvalidIsbnException;
import exception.InvalidNameException;
import exception.InvalidPubDateException;
import exception.InvalidSiteException;
import exception.InvalidSummaryException;
import exception.InvalidTitleException;

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
		
		if(dbDate == null || !dbDate.before(today)) {
			throw new InvalidDoBException("Person can't be born in that time!");
		}
		
		return true;
	}
	
	public static boolean validTitle(String title) throws InvalidTitleException{
		if(title.length() < 1 || title.length () > 255) {
			throw new InvalidTitleException("Book title must be between 1 and 255 characters!");
		}
		
		return true;
	}
	
	public static boolean validSummary(String summary) throws InvalidSummaryException{
		if(summary.length () > 65536) {
			throw new InvalidSummaryException("Summary must be shorter than 65,536 characters!");
		}
		
		return true;
	}
	
	public static boolean validPublicationYear(int year) throws InvalidPubDateException{
		if(year > 2018) {
			throw new InvalidPubDateException("Book can't be published past current year!");
		}
		
		return true;
	}
	
	public static boolean validIsbn(String isbn) throws InvalidIsbnException{
		if(isbn.length() > 13) {
			throw new InvalidIsbnException("ISBN can't be over 13 characters!");
		}
		
		return true;
	}
}
