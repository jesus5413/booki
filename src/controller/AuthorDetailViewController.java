package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dataBase.AuthorTableGateWay;
import javafx.stage.Stage;
import model.AuthorModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AuthorDetailViewController{
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	private AuthorModel authMod;
	private AuthorTableGateWay gate;
	
    @FXML private GridPane authorGrid;
    @FXML private Button saveButton;
    
//    public AuthorDetailViewController() {
//    		System.out.println("sdfsdf");
//    }
//    
//    // set and bind controller's AuthorModel to the model user double clicked on
//    public AuthorDetailViewController(AuthorModel mod) {
//    		this.authMod = mod;
//    }
    
    public void copyModel() {
    		authMod = new AuthorModel();
    		
    		/*************
    		 * START TESTING
    		 *************/
    		authMod = new AuthorModel();
    		authMod.setID(1);
    		authMod.setFirstName("First");
    		authMod.setLastName("Last");
    		authMod.setDateOfBirth("2018-02-15");
    		authMod.setGender("m");
    		authMod.setWebSite("www.site.com");
    		/*************
    		 * END TESTING
    		 *************/
    		
    		// read author
//    		gate.setConnection();
//    		gate.readAuthor(authMod);
//    		gate.closeConnection();
    }
    
	public void initialize() {
		gate = new AuthorTableGateWay();
		copyModel();

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                saveUpdate();
            }
        });
	}
	
	// when user clicks button, retrieve all fields, save them to copied model, and update database
	private void saveUpdate() {
		logger.debug("Save button has been clicked");
		
		// get all text fields to update
		ObservableList<Node> children = authorGrid.getChildren();
		authMod.setFirstName(((TextField)children.get(5)).getText());
		authMod.setLastName(((TextField)children.get(6)).getText());
		authMod.setDateOfBirth(((TextField)children.get(7)).getText());
		authMod.setGender(((TextField)children.get(8)).getText());
		authMod.setWebSite(((TextField)children.get(9)).getText());
		
		gate.setConnection();
		gate.updateAuthor(authMod);
		gate.closeConnection();
	}
}
