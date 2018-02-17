package changeSingleton;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import view.MainLauncher;

public class ChangeViewsSingleton {
	private static ChangeViewsSingleton singleton = null;
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	
	
	private ChangeViewsSingleton() {
		
	}
	
	public static ChangeViewsSingleton getInstance() {
		if(singleton == null) {
			singleton = new ChangeViewsSingleton();
		}
		return singleton;
		
	}
	
	public void changeViews(String x) {
		try {
			String fxmlPath = "";
			BorderPane test = MainLauncher.getMainPane();
			
			if(x == "x"){
				fxmlPath = "/fxml/authorListPane.fxml";
			}
			if(x == "y") {
				fxmlPath = "/fxml/AuthorDetailView.fxml";
			}	
			if(x == "z") {
				fxmlPath = "/fxml/authorsTableView.fxml";
				System.out.println("table\n");
			}
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(fxmlPath));
			test.setCenter(null); 
			test.setCenter(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error when trying to change views");
			e.printStackTrace();
		}
		
	}
	
	
}
