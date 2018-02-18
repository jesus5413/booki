package controller;


import javafx.scene.control.*;
import javafx.scene.control.Label;
import model.AuthorModel;

import java.sql.Date;
import java.time.ZoneId;

import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.TempStorage;
import javafx.fxml.FXML;

public class AuthorDetailController {
	@FXML
	public Label ID;
	public TextField firstName;
	public TextField lastName;
	//public TextField dob;
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
		TempStorage.oneAuthor.setFirstName(firstName.getText());
		TempStorage.oneAuthor.setLastName(lastName.getText());
		TempStorage.oneAuthor.setDateOfBirth(Date.valueOf(dob.getValue()));
		TempStorage.oneAuthor.setGender(gender.getText());
		TempStorage.oneAuthor.setWebSite(website.getText());
		AuthorTableGateWay connection = new AuthorTableGateWay();
		connection.setConnection();
		connection.updateAuthor(TempStorage.oneAuthor);
		connection.closeConnection();
		TempStorage.oneAuthor = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("z");
	}
	public void saveButtonHandle() {
		AuthorModel test = new AuthorModel();
		test.setFirstName(firstName.getText());
		test.setLastName(lastName.getText());
		test.setDateOfBirth(Date.valueOf(dob.getValue()));
		test.setGender(gender.getText());
		test.setWebSite(website.getText());
//		TempStorage.oneAuthor.setFirstName(firstName.getText());
//		TempStorage.oneAuthor.setLastName(lastName.getText());
//		TempStorage.oneAuthor.setDateOfBirth(dob.getText());
//		TempStorage.oneAuthor.setGender(gender.getText());
//		TempStorage.oneAuthor.setWebSite(website.getText());
		AuthorTableGateWay connection = new AuthorTableGateWay();
		connection.setConnection();
		connection.saveAuthor(test);
		connection.closeConnection();
		TempStorage.oneAuthor = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("z");
	}
	
	
	
	
	
	

}
