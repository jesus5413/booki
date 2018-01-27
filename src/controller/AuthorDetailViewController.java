package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

public class AuthorDetailViewController implements Initializable {
    @FXML private Button saveButton;
    @FXML private TableColumn<?, ?> firstName;
    @FXML private TableColumn<?, ?> lastName;
    @FXML private TableColumn<?, ?> doB;
    @FXML private TableColumn<?, ?> gender;
    @FXML private TableColumn<?, ?> website;

	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("you clicked save button");
	}
	
	public void openAuthorDetails() {
		
	}
}
