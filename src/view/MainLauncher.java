package view;

import java.io.IOException;
import java.net.URL;

// Assignment 1 by Jesus Nieto and Fernando Renteria 

import com.sun.glass.ui.TouchInputSupport;

import dataBase.AuthorTableGateWay;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.AuthorModel;
import model.BookModel;
import model.Publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Assignment 2 by Jesus Nieto and Fernando Renteria 
 * @author jesusnieto
 *
 */

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
		ObservableList<BookModel> list = FXCollections.observableArrayList();
		BookTableGateWay test =  new BookTableGateWay();
		test.setConnection();
		list = test.getBooks();
		test.closeConnection();
		System.out.println(list.get(1).getId());
		launch(args);
	}
	

	public static Stage getStage() {
		return stage;
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
}

