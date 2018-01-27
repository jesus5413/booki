package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import view.MainLauncher;
import javafx.scene.layout.BorderPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class AuthorListController implements Initializable{
	final ObservableList<String> listItems = FXCollections.observableArrayList("Author 1", "author 2", "author3"); 
	private static Logger logger = LogManager.getLogger(AuthorListController.class);
    @FXML private ListView<String> authorListView;
	private BorderPane rootNode;

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		logger.debug("Initializing authorlist");
		authorListView.setItems(listItems);
	}
	
	public void setRootNode(BorderPane rootNode) {
		this.rootNode = rootNode;
	}

}
