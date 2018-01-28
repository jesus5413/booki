package changeSingleton;

import java.io.IOException;

import controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import view.MainLauncher;

public class ChangeViewsSingleton {
	private static ChangeViewsSingleton singleton = null;
	
	
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource(fxmlPath));
			//test.setCenter(null); <- for clearing up previous view?
			test.setCenter(root);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
