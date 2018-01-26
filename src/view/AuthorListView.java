package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AuthorListView extends Application{
	private static Logger logger = LogManager.getLogger(MainLauncher.class);
	public static Stage stage;
	
	/**
	 * This function builds the scene to launch
	 * the authorlistpane.fxml file
	 */
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/authorListPane.fxml"));
			primaryStage.setScene(new Scene(root, 500, 246));
			primaryStage.setTitle("Authors");
			primaryStage.show();
			logger.debug("Opening author view");
		}catch(Exception e) {
			e.printStackTrace();
			logger.debug("Error opening author view");
		}
		
		this.stage = primaryStage;
			
	}
	
}
