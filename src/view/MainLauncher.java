package view;

import java.io.IOException;
import java.net.URL;

// Assignment 1 by Jesus Nieto and Fernando Renteria 

import com.sun.glass.ui.TouchInputSupport;

import dataBase.AuthorTableGateWay;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AuthorModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MainLauncher extends Application{
	private static Logger logger = LogManager.getLogger(MainLauncher.class);
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
		
		AuthorTableGateWay test = new AuthorTableGateWay();
		//test.setConnection();
		
		//testing of the functions
//		AuthorModel updateTest = new AuthorModel();
//		updateTest.setDateOfBirth("2018-02-01");
//		updateTest.setFirstName("aaroon");
//		updateTest.setGender("f");
//		updateTest.setID(2);
//		updateTest.setLastName("nieto");
//		updateTest.setWebSite("asdgadgad.com");
//		test.updateAuthor(updateTest);
//		ObservableList<AuthorModel> authorList = test.getAuthors();
//		for(AuthorModel model : authorList) {
//			System.out.println(model.getFirstName());
//		}
		//test.deleteAuthor(2);
		//test.closeConnection();
		launch(args);
	}
	

	public static Stage getStage() {
		return stage;
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
}

