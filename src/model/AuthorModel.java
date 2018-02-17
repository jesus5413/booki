package model;

import javafx.beans.property.SimpleStringProperty;

public class AuthorModel {
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty dateOfBirth;
	private SimpleStringProperty gender;
	private SimpleStringProperty webSite;
	private int ID;
	
	
	public AuthorModel() {
		this.ID = 0;
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.dateOfBirth = new SimpleStringProperty();
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
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public String getDateOfBirth() {
		return dateOfBirth.get();
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public String getGender() {
		return gender.get();
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

	public String getWebSite() {
		return webSite.get();
	}

	public void setWebSite(String webSite) {
		this.webSite.set(webSite);
	}
	
	
	
	
}
