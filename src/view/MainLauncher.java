package view;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Random;

// Assignment 1 by Jesus Nieto and Fernando Renteria 

import com.sun.glass.ui.TouchInputSupport;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

import dataBase.AuthorBookGateWay;
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
import model.AuditTrailModel;
import model.AuthorBook;
import model.AuthorModel;
import model.BookModel;
import model.Publisher;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * Assignment 4 by Jesus Nieto and Fernando Renteria 
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
//	BookTableGateWay bookCon = new BookTableGateWay();
//	int array[] = {1, 7, 8, 9, 10};
//	
//	bookCon.setConnection();
//	BigDecimal index = new BigDecimal(.09);
//	
//	for(int i = 0; i < 10000; i++) {
//		
//		BookModel bookModel = new BookModel();
//		bookModel.setTitle("Book" + i + 5);
//		bookModel.setAuthorId(1);
//		bookModel.setPublisherId(getRandom(array));
//		bookModel.setIsbn("123456789");
//		bookModel.setYearPublished(2000);
//		bookModel.setSummary("A bunch of books");
//		bookCon.saveBook(bookModel);
//		
//		AuthorBookGateWay authorBookGateWay = new AuthorBookGateWay();
//		authorBookGateWay.setConnection();
//		authorBookGateWay.insertAuthor(bookModel.getAuthorId(), 77 + i, index);
//		authorBookGateWay.closeConnection();
//		
//		
//	}
//	
//	bookCon.closeConnection();
		launch(args);
	}
	
	public static int getRandom(int[] array) {
	    int rnd = new Random().nextInt(array.length);
	    return array[rnd];
	}
	

	public static Stage getStage() {
		return stage;
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
}

