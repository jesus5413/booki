package controller;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import auth.Authenticator;
import auth.AuthenticatorLocal;
import auth.LoginDialog;
import auth.SessSing;
import changeSingleton.ChangeViewsSingleton;
import dataBase.BookTableGateWay;
import dataBase.TempStorage;
import exception.LoginException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;

/**
 * this is the main menu controller 
 * for the menu. It gives it the actions and initials
 * loads of the menu and view
 * @author jesusnieto
 *
 */
public class MainMenuController{
	private static Logger logger = LogManager.getLogger(MainMenuController.class);
	
	@FXML private MenuBar menuBar;
	@FXML private MenuItem exit;
	@FXML private MenuItem authorTable;
	@FXML private MenuItem addAuthor;
	@FXML private MenuItem addBook;
	@FXML private MenuItem bookTable;
	@FXML private MenuItem excel;
	@FXML private MenuItem login;
	@FXML private MenuItem logout;
	
	int sessionId;
	AuthenticatorLocal auth = new AuthenticatorLocal();
	
	/**
	 * function does the actions needed for the item choices
	 * in the menu bar
	 * @param event
	 * @throws IOException
	 */
	@FXML private void handleMenuAction(ActionEvent event) throws IOException{
		if(event.getSource() == authorTable) {
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			singleton.changeViews("z");
		}
		
		if(event.getSource() == addAuthor) {
			TempStorage.oneAuthor = null;
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			singleton.changeViews("b");		
		}
		if(event.getSource() == addBook) {
			BookTableGateWay.min = 0;
			BookTableGateWay.max = 0;
			TempStorage.oneBook = null;
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			singleton.changeViews("y");	
		}
		if(event.getSource() == bookTable) {
			TempStorage.oneBook = null;
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			singleton.changeViews("t");	
		}
		if(event.getSource() == excel) {
			ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			singleton.changeViews("e");		
		}
		if(event.getSource() == login) {
			authenticate();
			
			//restrict access to GUI controls based on current login session
			updateGUIAccess();
		}
		if(event.getSource() == logout) {
			sessionId = Authenticator.INVALID_SESSION;
			SessSing.setId(sessionId);
			
			//restrict access to GUI controls based on current login session
			updateGUIAccess();
		}
		if(event.getSource() == exit) {
			TempStorage.oneBook = null;
			logger.error("Application has closed");
			System.exit(0);
		}
	}
	
	/**
	 * function loads what needs to be loaded first before
	 * anything else
	 * @param location
	 * @param resources
	 */
	public void initialize() {
		menuBar.setFocusTraversable(true);
		
		if(sessionId == Authenticator.INVALID_SESSION) {
			setToDisable(true);
		}else {
			setToDisable(false);
		}
	}
	
	public void authenticate() {
		Pair<String, String> creds = LoginDialog.showLoginDialog();
		if(creds == null) { //canceled
			return;
		}
		
		String userName = creds.getKey();
		String pw = creds.getValue();
		
		logger.info("userName is " + userName + ", password is " + pw);
		
		//hash password
		String pwHash = "Not done";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pw.getBytes(StandardCharsets.UTF_8));
			pwHash = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		logger.info("sha256 hash of password is " + pwHash);
		
		//send login and hashed pw to authenticator
		try {
			//if get session id back, then replace current session
			System.out.println(sessionId);
			sessionId = auth.loginSha256(userName, pwHash);
			
			logger.info("session id is " + sessionId);
			
			SessSing s = SessSing.getInstance();
			s.setId(sessionId);
			s.setUsername(userName);
			
		} catch (LoginException e) {
			//else display login failure
			Alert alert = new Alert(AlertType.WARNING);
			alert.getButtonTypes().clear();
			ButtonType buttonTypeOne = new ButtonType("OK");
			alert.getButtonTypes().setAll(buttonTypeOne);
			alert.setTitle("Login Failed");
			alert.setHeaderText("The user name and password you provided do not match stored credentials.");
			alert.showAndWait();

			return;
		}
	}
	
	private void updateGUIAccess() {
		//if logged in, login should be disabled
		if(sessionId == Authenticator.INVALID_SESSION) {
			login.setDisable(false);
			setToDisable(true);
		}else {
			login.setDisable(true);
			setToDisable(false);
		}
		
		//if not logged in, logout should be disabled
		if(sessionId == Authenticator.INVALID_SESSION) {
			logout.setDisable(true);
			setToDisable(true);
		}else {
			logout.setDisable(false);
			setToDisable(false);
		}
	}
	
	private void setToDisable(boolean disable) {
		authorTable.setDisable(disable);
		addAuthor.setDisable(disable);
		addBook.setDisable(disable);
		bookTable.setDisable(disable);
		excel.setDisable(disable);
	}
}