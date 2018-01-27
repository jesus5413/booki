package changeSingleton;

import java.io.IOException;

import controller.MainMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
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
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/fxml/authorListView.fxml"));
			BorderPane test = MainLauncher.getMainPane();
			
			test.setCenter(root);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
