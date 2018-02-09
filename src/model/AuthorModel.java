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

	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public SimpleStringProperty getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public SimpleStringProperty getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth.set(dateOfBirth);
	}

	public SimpleStringProperty getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender.set(gender);
	}

	public SimpleStringProperty getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite.set(webSite);
	}
	
	
	
	
}
