package model;

import javafx.beans.property.SimpleStringProperty;

public class AuthorModel {
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private SimpleStringProperty dateOfBirth;
	private SimpleStringProperty gender;
	private SimpleStringProperty webSite;
	
	public AuthorModel() {
		this.firstName = new SimpleStringProperty();
		this.lastName = new SimpleStringProperty();
		this.dateOfBirth = new SimpleStringProperty();
		this.gender = new SimpleStringProperty();
		this.webSite = new SimpleStringProperty();
	}

	public SimpleStringProperty getFirstName() {
		return firstName;
	}

	public void setFirstName(SimpleStringProperty firstName) {
		this.firstName = firstName;
	}

	public SimpleStringProperty getLastName() {
		return lastName;
	}

	public void setLastName(SimpleStringProperty lastName) {
		this.lastName = lastName;
	}

	public SimpleStringProperty getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(SimpleStringProperty dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public SimpleStringProperty getGender() {
		return gender;
	}

	public void setGender(SimpleStringProperty gender) {
		this.gender = gender;
	}

	public SimpleStringProperty getWebSite() {
		return webSite;
	}

	public void setWebSite(SimpleStringProperty webSite) {
		this.webSite = webSite;
	}
	
	
	
	
}
