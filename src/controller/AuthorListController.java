package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.*;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import changeSingleton.ChangeViewsSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import view.MainLauncher;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class AuthorListController implements Initializable{
	final ObservableList<String> listItems = FXCollections.observableArrayList("Author 1", "author 2", "author3"); 
	private static Logger logger = LogManager.getLogger(AuthorListController.class);
    @FXML private ListView<String> authorListView;
	

 
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		logger.debug("Initializing authorlist");
		authorListView.setItems(listItems);
		
		authorListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
		    @Override
		    public void handle(MouseEvent event) {

		        if (event.getClickCount() == 2) {
		           //Use ListView's getSelected Item
		           String currentItemSelected = authorListView.getSelectionModel().getSelectedItem();
		           //use this to do whatever you want to. Open Link etc.
		           logger.debug("Clicked " + currentItemSelected);
		           ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		           singleton.changeViews("y");
		           
		        }
		    }
		});
	}
	
}
