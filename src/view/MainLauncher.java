package view;

import java.io.IOException;
import java.net.URL;

import com.sun.glass.ui.TouchInputSupport;

import controller.AuthorListController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainLauncher extends Application{
	public static Stage stage;
	public static BorderPane mainPane;
	
	/**
	 * This function builds the scene to launch
	 * the maineMunuPane.fxml file
	 * 
	 */
	public void start(Stage primaryStage) throws Exception{
		
		
		BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("/fxml/mainMenuPane.fxml"));
		primaryStage.setScene(new Scene(root, 1000, 600));
		primaryStage.setTitle("Booki");
		primaryStage.show();
			
		
		this.stage = primaryStage;
		this.mainPane = root;
		
			
	}
	
	
	
	/**
	 * This function launches the application 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		
	}
	
	public static Stage getStage() {
		return stage;
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
	
	
	
	
}

