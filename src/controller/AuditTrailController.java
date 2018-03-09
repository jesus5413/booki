package controller;

import javax.swing.LayoutFocusTraversalPolicy;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.AuditTrailModel;

public class AuditTrailController {
	ObservableList<AuditTrailModel> auditList = FXCollections.observableArrayList();
	@FXML TableView<AuditTrailModel> auditTable;
	@FXML TableColumn<AuditTrailModel, Integer> idCol;
	@FXML TableColumn<AuditTrailModel, String> dateCol;
	@FXML TableColumn<AuditTrailModel, String> msgCol;
	
	public void initialize(){
		
	}

}
