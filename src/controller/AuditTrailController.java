package controller;

import javax.swing.LayoutFocusTraversalPolicy;

import dataBase.BookTableGateWay;
import dataBase.TempStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AuditTrailModel;

public class AuditTrailController {
	ObservableList<AuditTrailModel> auditList = FXCollections.observableArrayList();
	@FXML TableView<AuditTrailModel> auditTable;
	@FXML TableColumn<AuditTrailModel, String> dateAdded;
	@FXML TableColumn<AuditTrailModel, String> message;
	@FXML public Label labelAudit;
	private int id;
	
	public void initialize(){
		id = TempStorage.oneBook.getId();
		labelAudit.setText("Audit trail for " + TempStorage.oneBook.getTitle());
		TempStorage.oneBook = null;
		populateTable();
	}
	public void populateTable() {
		BookTableGateWay bookCon = new BookTableGateWay();
		bookCon.setConnection();
		auditList = bookCon.getAuditTrail(id); 
		bookCon.closeConnection();
		
		setCell();
		auditTable.setItems(auditList);
		
	}
	
	public void setCell() {
		
		dateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
		message.setCellValueFactory(new PropertyValueFactory<>("msg"));
		
	}
	
	
}
