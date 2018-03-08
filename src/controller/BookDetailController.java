package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import dataBase.TempStorage;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class BookDetailController {
	@FXML
	public Label ID;
	public TextField title;
	public TextArea summary;
	public DatePicker yearPublished;
	public ComboBox<String> publisher;
	public TextField ISBN;
	public Label dateAdded;
	public Button update;
	public Button save;
	
	public void initialize() {
		if(TempStorage.oneBook != null) {
			save.setVisible(false);
			

			
		}else {
			save.setVisible(true);
			ID.setText("");
			dateAdded.setText(java.time.LocalDate.now().toString());
			update.setVisible(false);
			
		}
		
	}
	
	public void textFieldPlaceHolder() {
		update.setVisible(true);
	}

}
