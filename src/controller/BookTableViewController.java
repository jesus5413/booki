package controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.BookModel;

public class BookTableViewController {
	
	@FXML TableView<BookModel> bookTable;
	@FXML TableColumn<BookModel, Integer> id;
	@FXML TableColumn<BookModel, String> title;
	@FXML TableColumn<BookModel, String> summary;
	@FXML TableColumn<BookModel, String> yearPublished;
	@FXML TableColumn<BookModel, String> publisher;
	@FXML TableColumn<BookModel, String> ISBN;
	@FXML TableColumn<BookModel, String> dateAdded;
	@FXML public Button delete;
	

}
