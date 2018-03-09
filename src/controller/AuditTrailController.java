package controller;

import javax.swing.LayoutFocusTraversalPolicy;

<<<<<<< HEAD
import dataBase.TempStorage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.AuditTrailModel;

public class AuditTrailController {
	ObservableList<AuditTrailModel> auditList = FXCollections.observableArrayList();
	@FXML TableView<AuditTrailModel> auditTable;
	@FXML TableColumn<AuditTrailModel, Integer> ID;
	@FXML TableColumn<AuditTrailModel, String> dateAdded;
	@FXML TableColumn<AuditTrailModel, String> message;
	private int id;
	
	public void initialize(){
		id = TempStorage.oneAuthor.getID();
		TempStorage.oneAuthor = null;
	}
	
	
=======
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.AuditTrailModel;

public class AuditTrailController {
	ObservableList<AuditTrailModel> auditList = FXCollections.observableArrayList();
	@FXML TableView<AuditTrailModel> auditTable;
	@FXML TableColumn<AuditTrailModel, Integer> ID;
	@FXML TableColumn<AuditTrailModel, String> dateAdded;
	@FXML TableColumn<AuditTrailModel, String> message;
	
	public void initialize(){
		
	}
>>>>>>> branch 'fern1' of https://github.com/jesus5413/booki.git

}
