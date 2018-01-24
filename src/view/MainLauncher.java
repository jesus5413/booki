package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainLauncher extends Application{
	public static Stage stage;
	
	/**
	 * This function builds the scene to launch
	 * the maineMunuPane.fxml file
	 * 
	 */
	public void start(Stage primaryStage) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenuPane.fxml"));
			primaryStage.setScene(new Scene(root, 500, 246));
			primaryStage.setTitle("bookie");
			primaryStage.show();
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		
		this.stage = primaryStage;
			
	}
	
	/**
	 * This function launches the application 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		
	}
	
	/**
	 * returns the stage
	 * @return
	 */
	public static Stage getStage() {
		return stage;
	}
	
}
