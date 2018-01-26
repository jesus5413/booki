package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import view.MainLauncher;
import javafx.stage.Stage;

/**
 * this is the main menu controller 
 * for the menu. It gives it the actions and initials
 * loads of the menu and view
 * @author jesusnieto
 *
 */
public class MainMenuController implements Initializable{
	@FXML private MenuBar menuBar;
	@FXML private MenuItem authorList;
	@FXML private MenuItem exit;
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	
	/**
	 * function does the actions needed for the item choices
	 * in the menu bar
	 * @param event
	 * @throws IOException
	 */
	@FXML private void handleMenuAction(ActionEvent event) throws IOException{
		if(event.getSource() == authorList) {
			System.out.println("author list\n"); // action to bring up author list view is here
			logger.debug("Authorlist has been clicked");
			// using for testing Author List view. Remove once singleton is finished
			try {
				Stage childScene = new Stage();
				Parent root = FXMLLoader.load(getClass().getResource("/fxml/authorListPane.fxml"));
				childScene.setScene(new Scene(root,1280,720));
				childScene.show();
				MainLauncher.stage.hide(); // closes the main menu
				
				
				}catch(Exception e) {
					e.printStackTrace();
				}
		}
		if(event.getSource() == exit) {
			logger.debug("Application has closed");
			System.exit(0);
		}
	}
	
	/**
	 * function loads what needs to be loaded first before
	 * anything else
	 * @param location
	 * @param resources
	 */
	public void initialize(URL location, ResourceBundle resources) {
		menuBar.setFocusTraversable(true);
	}
	
}