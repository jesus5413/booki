package controller;


import javafx.scene.control.*;
import javafx.scene.control.Label;
import model.AuthorModel;

import java.sql.Date;
import java.time.ZoneId;

import alert.AlertHelper;
import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.TempStorage;
import javafx.fxml.FXML;

public class AuthorDetailController {
	@FXML
	public Label ID;
	public TextField firstName;
	public TextField lastName;
	public DatePicker dob;
	public TextField gender;
	public TextField website;
	public Button update;
	public Button save;
	
	public void initialize() {
		if(TempStorage.oneAuthor != null) {
			save.setVisible(false);
			textFieldPlaceHolder();
		}else {
			ID.setText("");
			save.setVisible(true);
			update.setVisible(false);
			
		}
	}
	
	public void textFieldPlaceHolder() {
			update.setVisible(true);
			ID.setText(Integer.toString(TempStorage.oneAuthor.getID()));
			firstName.setText(TempStorage.oneAuthor.getFirstName());
			lastName.setText(TempStorage.oneAuthor.getLastName());
			dob.setValue(TempStorage.oneAuthor.getDateOfBirth().toLocalDate());
			gender.setText(TempStorage.oneAuthor.getGender());
			website.setText(TempStorage.oneAuthor.getWebSite());
	}


	public void updateButtonHandle() {
		if(!TempStorage.oneAuthor.setFirstName(firstName.getText())) {
			return;
		}
		
		if(!TempStorage.oneAuthor.setLastName(lastName.getText())) {
			return;
		}
		
		if(!TempStorage.oneAuthor.setDateOfBirth(Date.valueOf(dob.getValue()))) {
			return;
		}
		
		if(!TempStorage.oneAuthor.setGender(gender.getText())) {
			return;
		}
		
		if(!TempStorage.oneAuthor.setWebSite(website.getText())) {
			return;
		}
		
		AuthorTableGateWay connection = new AuthorTableGateWay();
		connection.setConnection();
		connection.updateAuthor(TempStorage.oneAuthor);
		connection.closeConnection();
		TempStorage.oneAuthor = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("z");
	}
	public void saveButtonHandle(){
		AuthorModel test = new AuthorModel();
		if(!test.setFirstName(firstName.getText())) {
			return;
		}
		
		if(!test.setLastName(lastName.getText())) {
			return;
		}
		
		// Meant to check if User has picked a value
		if(dob.getValue() == null) {
			AlertHelper.showWarningMessage("Birthdate Error", 
					"Invalid Date of Birth",
					"3: Author can't be born on that date!");
			
			return;
		}
		
		if(!test.setDateOfBirth(Date.valueOf(dob.getValue()))) {
			return;
		}
		
		if(!test.setGender(gender.getText())) {
			return;
		}
		
		if(!test.setWebSite(website.getText())) {
			return;
		}

		AuthorTableGateWay connection = new AuthorTableGateWay();
		connection.setConnection();
		connection.saveAuthor(test);
		connection.closeConnection();
		TempStorage.oneAuthor = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("z");
	}
}
