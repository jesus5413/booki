package controller;

import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.TempStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AuditTrailModel;

public class AuthorAuditTrail {
	ObservableList<AuditTrailModel> auditList = FXCollections.observableArrayList();
	@FXML public Label authorName;
	@FXML public Button back;
	@FXML public TableColumn<AuditTrailModel, String> dateAdded;
	@FXML public TableColumn<AuditTrailModel, String> message;
	@FXML public TableView<AuditTrailModel> authorAuditTable;
	private int id;
	
	public void initialize() {
		id = TempStorage.oneAuthor.getID();
		authorName.setText("Audit trail for " + TempStorage.oneAuthor.getFirstName() + " " +TempStorage.oneAuthor.getLastName());
		populateTable();
	}
	
	public void populateTable() {
		AuthorTableGateWay authCon = new AuthorTableGateWay();
		authCon.setConnection();
		auditList = authCon.getAuditTrail(id);
		authCon.closeConnection();
		setCell();
		authorAuditTable.setItems(auditList);
		
	}
	
	public void setCell() {
		dateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
		message.setCellValueFactory(new PropertyValueFactory<>("msg"));
	}
	
	public void backHandle() {
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
        singleton.changeViews("b"); 
		
	}
	
}
