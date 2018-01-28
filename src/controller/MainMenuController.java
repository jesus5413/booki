package controller;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import changeSingleton.ChangeViewsSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;


/**
 * this is the main menu controller 
 * for the menu. It gives it the actions and initials
 * loads of the menu and view
 * @author jesusnieto
 *
 */
public class MainMenuController implements Initializable{
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	
	@FXML private MenuBar menuBar;
	@FXML private MenuItem authorList;
	@FXML private MenuItem exit;
	
	/**
	 * function does the actions needed for the item choices
	 * in the menu bar
	 * @param event
	 * @throws IOException
	 */
	@FXML private void handleMenuAction(ActionEvent event) throws IOException{
		if(event.getSource() == authorList) {
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			
			singleton.changeViews("x");
		}
		
		if(event.getSource() == exit) {
			logger.error("Application has closed");
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