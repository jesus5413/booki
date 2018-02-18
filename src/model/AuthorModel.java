package model;

import java.sql.Date;

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

	public void setFirstName(String firstName) {
//		try {
//			if(Validator.validName(firstName, lastName.getName())) {
				this.firstName.set(firstName);
//			}
//		} catch (InvalidNameException e) {
//			AlertHelper.showWarningMessage("Name Error", 
//					"Invalid Author Name",
//					"1: Name fields can't be empty, and can't be over 100 characters!");
//			e.printStackTrace();
//		}
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
//		try {
//			if(Validator.validName(firstName.getName(), lastName)) {
				this.lastName.set(lastName);
//			}
//		} catch (InvalidNameException e) {
//			AlertHelper.showWarningMessage("Name Error", 
//					"Invalid Author Name",
//					"1: Name fields can't be empty, and can't be over 100 characters!");
//			e.printStackTrace();
//		}
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		try {
			if(Validator.validDate(dateOfBirth)) {
				this.dateOfBirth = dateOfBirth;
			}
		} catch (InvalidDoBException e) {
			AlertHelper.showWarningMessage("Birthdate Error", 
					"Invalid Date of Birth",
					"1: Author can't be born on that date!");
			e.printStackTrace();
		}
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		try {
			if(Validator.validGender(gender)) {
				this.gender.set(gender);
			}
		} catch (InvalidGenderException e) {
			AlertHelper.showWarningMessage("Gender Error", 
					"Invalid Gender",
					"1: Author can only be M, F, or U!");
			e.printStackTrace();
		}
	}

	public String getWebSite() {
		return webSite.get();
	}

	public void setWebSite(String webSite) {
		try {
			if(Validator.validSiteLength(webSite)) {
				this.webSite.set(webSite);	
			}
		} catch (InvalidSiteException e) {
			AlertHelper.showWarningMessage("Website Error", 
					"Invalid Website Length",
					"1: Website must be less than 100 characters");
			e.printStackTrace();
		}	
	}
}
