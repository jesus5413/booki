package view;

import java.io.IOException;
import java.net.URL;

// Assignment 1 by Jesus Nieto and Fernando Renteria 

import com.sun.glass.ui.TouchInputSupport;
import com.sun.jndi.url.iiopname.iiopnameURLContextFactory;

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

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import javafx.application.Platform;
import auth.MyNameBeanRemote;

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
	
	private static MyNameBeanRemote bean = null;
	private static InitialContext context = null;
	
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
		// TEST
//		BookTableGateWay conn = new BookTableGateWay();
//		conn.setConnection();
//		BookModel book = new BookModel();
//		book.setId(43);
//		ObservableList<AuthorBook> authorBookL = conn.getAuthorsForBook(book);
//		conn.closeConnection();
//		
//		System.out.println(authorBookL.size());
//		authorBookL.forEach((record) ->{
//			System.out.println(record.getRoyalty());
//		});
		
		// TEST
		Properties props = new Properties();
		//use the jboss factory for context to lookup the EJB remote methods 
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		//URL is the jboss server; port 8080 is jboss default for remote corba access 
		props.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		//below statement triggers the creation of a EJBClientContext containing a EJBReceiver capable of handling the EJB invocations 		
		props.put("jboss.naming.client.ejb.context", "true");
		try {
			//create and save context as instance var
			context = new InitialContext(props);
			//grab ref to bean’s remote interface
			bean = (MyNameBeanRemote) context.lookup("MyEJB/MyNameBean!auth.MyNameBeanRemote");
			
			System.out.println(bean.getName());
		} catch (NamingException e) {
			e.printStackTrace();
			Platform.exit();
		}
		
		// ENDTEST
		
		
		launch(args);
	}
	

	public static Stage getStage() {
		return stage;
	}
	
	public static BorderPane getMainPane() {
		return mainPane;
	}
}

