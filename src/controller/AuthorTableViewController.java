package controller;

import dataBase.AuthorTableGateWay;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AuthorModel;

public class AuthorTableViewController {
	@FXML TableView<AuthorModel> authorTable;
	@FXML TableColumn<AuthorModel, Integer> ID;
	@FXML TableColumn<AuthorModel, String> firstName;
	@FXML TableColumn<AuthorModel, String> lastName;
	@FXML TableColumn<AuthorModel, String> dob;
	@FXML TableColumn<AuthorModel, String> gender;
	@FXML TableColumn<AuthorModel, String> website;
	
	
	public void initialize() {
		populateTable();
		
	}
	public void populateTable() {
		AuthorTableGateWay connection = new AuthorTableGateWay();
		connection.setConnection();
		ObservableList<AuthorModel> authorInfo = connection.getAuthors();
		connection.closeConnection();
		setCell();
		authorTable.setItems(authorInfo);
	}
	
	public void setCell() {
		ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		dob.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		website.setCellValueFactory(new PropertyValueFactory<>("webSite"));
		
	}
	
	
	
	
	
}
