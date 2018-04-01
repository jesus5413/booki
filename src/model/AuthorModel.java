package model;

import java.sql.Date;
import java.time.LocalDateTime;

import alert.AlertHelper;
import exception.InvalidDoBException;
import exception.InvalidGenderException;
import exception.InvalidNameException;
import exception.InvalidSiteException;
import javafx.beans.property.SimpleStringProperty;
import validators.Validator;

public class AuthorModel {
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private Date dateOfBirth;
	private SimpleStringProperty gender;
	private SimpleStringProperty webSite;
	private int ID;
	private LocalDateTime lastModified;
	
	// lastModified will be used to implement optimistic record locking on the db
	public LocalDateTime getLastModified() {
		return lastModified;
	}

	public void setLastModified(LocalDateTime lastModified) {
		this.lastModified = lastModified;
	}

	public AuthorModel() {
		this.ID = 0;
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.dateOfBirth = new Date(1);
		this.gender = new SimpleStringProperty();
		this.webSite = new SimpleStringProperty();
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName.get();
	}

	public boolean setFirstName(String firstName) {
		boolean val = false;
		
		try {
			if(Validator.validName(firstName)) {
				this.firstName.set(firstName);
				val = true;
			}
		} catch (InvalidNameException e) {
			AlertHelper.showWarningMessage("Name Error", 
					"Invalid Author Name",
					"1: Name fields can't be empty or be greater than 100 characters!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}

	public String getLastName() {
		return lastName.get();
	}

	public boolean setLastName(String lastName) {
		boolean val = false;
		
		try {
			if(Validator.validName(lastName)) {
				this.lastName.set(lastName);
				val = true;
			}
		} catch (InvalidNameException e) {
			AlertHelper.showWarningMessage("Name Error", 
					"Invalid Author Name",
					"2: Name fields can't be empty or be greater than 100 characters!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public boolean setDateOfBirth(Date dateOfBirth) {
		boolean val = false;
		
		try {
			if(Validator.validDate(dateOfBirth)) {
				this.dateOfBirth = dateOfBirth;
				val = true;
			}
		} catch (InvalidDoBException e) {
			AlertHelper.showWarningMessage("Birthdate Error", 
					"Invalid Date of Birth",
					"3: Author can't be born on that date!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}

	public String getGender() {
		return gender.get();
	}

	public boolean setGender(String gender) {
		boolean val = false;
		
		try {
			if(Validator.validGender(gender)) {
				this.gender.set(gender);
				val = true;
			}
		} catch (InvalidGenderException e) {
			AlertHelper.showWarningMessage("Gender Error", 
					"Invalid Gender",
					"4: Author can only be M, F, or U!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}

	public String getWebSite() {
		return webSite.get();
	}

	public boolean setWebSite(String webSite) {
		boolean val = false;
		
		try {
			if(Validator.validSiteLength(webSite)) {
				this.webSite.set(webSite);
				val = true;
			}
		} catch (InvalidSiteException e) {
			AlertHelper.showWarningMessage("Website Error", 
					"Invalid Website Length",
					"5: Website must be less than 100 characters");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}
	
	public String compare(AuthorModel author) {
		String msg ="";
		String field = "";
		String oldValue = "";
		String newValue = "";
		Date oldDat = new Date(1);
		Date newDate = new Date(1);
		
		if(!this.getFirstName().equals(author.getFirstName())) {
			field = "first name";
		}
		else if(!this.getLastName().equals(author.getLastName())) {
			field = "last name";
		}
		else if(!this.getDateOfBirth().equals(author.getDateOfBirth())) {
			field = "date of birth";
		}
		else if(!this.getGender().equals(author.getGender())) {
			field = "gender";
		}
		else if(!this.getWebSite().equals(author.getWebSite())) {
			field = "website";
		}else {
			return null;
		}
		
		switch(field) {
		case "first name":
			oldValue = this.getFirstName();
			newValue = author.getFirstName();
			break;
		case "last name":
			oldValue = this.getLastName();
			newValue = author.getLastName();
			break;
		case "date of birth":
			oldDat = this.getDateOfBirth();
			newDate = author.getDateOfBirth();
			break;
		case "gender":
			oldValue = this.getGender();
			newValue = author.getGender();
			break;
		case "website":
			oldValue = this.getWebSite();
			newValue = author.getWebSite();
		}
		
		msg = field + " changed from ";
		if(oldValue.isEmpty()) {
			msg = msg + oldDat.toString() + " to " + newDate.toString();
		}else {
			msg = msg + oldValue + " to " + newValue;
		}
		return msg;
		
	}
	
	
}







