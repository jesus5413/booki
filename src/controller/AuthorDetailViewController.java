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
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AuthorDetailViewController{
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	private AuthorModel authMod;
	
    @FXML private GridPane authorGrid;
    @FXML private Button saveButton;
    
    public AuthorDetailViewController() {
    		System.out.println("sdfsdf");
    }
    
    // set and bind controller's AuthorModel to the model user double clicked on
    public AuthorDetailViewController(AuthorModel mod) {
    		this.authMod = mod;
    }
    
    public void copyModel() {
    		authMod = new AuthorModel();
    		
    		AuthorTableGateWay connection = new AuthorTableGateWay();
    		connection.readAuthor(authMod);
    		connection.closeConnection();
    }
    
	public void initialize() {
		//copyModel();
		// TODO Auto-generated method stub
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logger.debug("Save button has been clicked");
            }
        });
	}
}
