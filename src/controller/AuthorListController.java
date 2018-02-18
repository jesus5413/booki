package controller;

import java.net.URL;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.input.MouseEvent;
import model.AuthorModel;
import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;


public class AuthorListController {
	
	private static Logger logger = LogManager.getLogger(AuthorListController.class);
	AuthorTableGateWay autherInfo = new AuthorTableGateWay();
	ObservableList<AuthorModel> authorList = FXCollections.observableArrayList();
	@FXML private ListView<String> authorListView;
	
	
	public void initialize() {
		// TODO Auto-generated method stub
		logger.debug("Initializing authorlist");
		populateListView();
		
		authorListView.setOnMouseClicked(new EventHandler<MouseEvent>(){
		    @Override
		    public void handle(MouseEvent event) {

		        if (event.getClickCount() == 2) {
		           //Use ListView's getSelected Item
		           String currentItemSelected = authorListView.getSelectionModel().getSelectedItem();
		           
		          // getSingleAuthor(currentItemSelected);
		           //use this to do whatever you want to. Open Link etc.
		           logger.debug("Clicked " + currentItemSelected);
		           ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		           singleton.changeViews("y");      
		        }
		    }
		});
	}
	
	private void getSingleAuthor(String selected) {
		AuthorModel author = new AuthorModel();
		
		// make an Author Model based on the selected name
		author.setFirstName(selected.substring(0, selected.indexOf(" ")));
		author.setLastName(selected.substring(selected.indexOf(" "), selected.length()));
		
		
	}
	
	
	public void populateListView() {
		autherInfo.setConnection();
		authorList = autherInfo.getAuthors();
		autherInfo.closeConnection();
		ObservableList<String> stringAuthor = FXCollections.observableArrayList();
		for(int i = 0; i < authorList.size() ; i++) {
			
			stringAuthor.add(authorList.get(i).getFirstName() +" "+ authorList.get(i).getLastName());
		}
		authorListView.setItems(stringAuthor);
		
	}
	
	
	
	
}
