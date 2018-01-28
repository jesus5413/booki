package controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class AuthorDetailViewController implements Initializable {
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	
    @FXML private GridPane authorGrid;
    @FXML private Button saveButton;
    
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                logger.debug("Save button has been clicked");
            }
        });
	}
}
