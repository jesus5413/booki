package controller;

import java.awt.Event;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.sun.glass.events.MouseEvent;

import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.TempStorage;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AuthorModel;
import sun.reflect.generics.tree.VoidDescriptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorTableViewController {
	private static Logger logger = LogManager.getLogger(AuthorTableViewController.class);
	@FXML TableView<AuthorModel> authorTable;
	@FXML TableColumn<AuthorModel, Integer> ID;
	@FXML TableColumn<AuthorModel, String> firstName;
	@FXML TableColumn<AuthorModel, String> lastName;
	@FXML TableColumn<AuthorModel, String> dob;
	@FXML TableColumn<AuthorModel, String> gender;
	@FXML TableColumn<AuthorModel, String> website;
	public Button delete;
	
	
	public void initialize() {
		getAuthor();
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
	
	public void getAuthor() {
		authorTable.setRowFactory(tv -> {
			TableRow<AuthorModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2 && (!row.isEmpty())) {
					AuthorModel rowData = row.getItem();
					TempStorage.oneAuthor = rowData; // stores the data from the row into a temp storage object that will be used in the detail view
					String currentItemSelected = TempStorage.oneAuthor.getFirstName() + " " +TempStorage.oneAuthor.getLastName();
					logger.debug("Clicked " + currentItemSelected);
					System.out.println(TempStorage.oneAuthor.getFirstName());
					ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			        singleton.changeViews("b");    
				}
			});
			return row;
		});
		
	}	
	
	public void deleteHandle() {
		ObservableList<AuthorModel> allAuthors = authorTable.getItems();
		if(!allAuthors.isEmpty()){
			AuthorModel selectedAuthor = new AuthorModel();
		
			selectedAuthor = authorTable.getSelectionModel().getSelectedItem();
			authorTable.getItems().removeAll(authorTable.getSelectionModel().getSelectedItem());
		
			AuthorTableGateWay connection = new AuthorTableGateWay();
			connection.setConnection();
			connection.deleteAuthor(selectedAuthor.getID());  
			connection.closeConnection();
		}
		
	}
}






