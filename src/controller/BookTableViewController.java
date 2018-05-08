package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import auth.AuthenticatorLocal;
import auth.RBACPolicyAuthDemo;
import auth.Session;
import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import dataBase.SessionGateway;
import dataBase.TempStorage;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import model.AuthorModel;
import model.BookModel;

public class BookTableViewController {
	private static Logger logger = LogManager.getLogger(BookTableViewController.class);
	@FXML TableView<BookModel> bookTable;
	@FXML TableColumn<BookModel, Integer> id;
	@FXML TableColumn<BookModel, String> title;
	@FXML TableColumn<BookModel, String> summary;
	@FXML TableColumn<BookModel, String> yearPublished;
	@FXML TableColumn<BookModel, String> publisher;
	@FXML TableColumn<BookModel, String> ISBN;
	@FXML TableColumn<BookModel, String> dateAdded;
	ObservableList<BookModel> bookList = FXCollections.observableArrayList();
	ObservableList<BookModel> bookList2 = FXCollections.observableArrayList();
	@FXML public Button delete;
	@FXML public Button next;
	@FXML public Button prev;
	@FXML public Button first;
	@FXML public Button last;
	@FXML public TextField search;
	
	AuthenticatorLocal auth;
	SessionGateway sessGate = new SessionGateway();
	String username;
	
	public void initialize() {
		getBook();
		populateTable(0, 50);
		searchHandle();
		
		sessGate.setConnection();
		username = sessGate.checkPerms();
		sessGate.closeConnection();
		
		// disable delete if Data Entry or Intern
		if(username.equalsIgnoreCase("leroy") || username.equalsIgnoreCase("sasquatch")) {
			delete.setDisable(true);
		}
	}
	
	public void populateTable(int x, int y) {
		
		BookTableGateWay bookCon = new BookTableGateWay();
		bookCon.setConnection();
		bookList = bookCon.getBooks(x, y); 
		bookCon.closeConnection();
	
		PublisherTableGateWay pubCon = new PublisherTableGateWay();
		pubCon.setConnection();
		for(int i = 0; i < bookList.size(); i++) {
			bookList.get(i).setPublisher(pubCon.getPublisherByID(bookList.get(i).getPublisherId()));
		}
		pubCon.closeConnection();
		setCell();
		bookTable.setItems(bookList);
	}
	
	public void setCell() {
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		summary.setCellValueFactory(new PropertyValueFactory<>("summary"));
		yearPublished.setCellValueFactory(new PropertyValueFactory<>("yearPublished"));
		publisher.setCellValueFactory(new Callback<CellDataFeatures<BookModel, String>, 
                ObservableValue<String>>() {  
				@Override  
				public ObservableValue<String> call(CellDataFeatures<BookModel, String> data){  
					return data.getValue().getPublisher().getPublisherName();  
				}  
		}); 
		
		ISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
		dateAdded.setCellValueFactory(new PropertyValueFactory<>("dateAdded"));
		
	}
	
	public void getBook() {
		
		bookTable.setRowFactory(tv -> {
			TableRow<BookModel> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if(event.getClickCount() == 2 && (!row.isEmpty())) {
					BookModel rowData = row.getItem();
					TempStorage.oneBook = rowData; // stores the data from the row into a temp storage object that will be used in the detail view
					String currentItemSelected = TempStorage.oneBook.getTitle();
					logger.debug("Clicked " + currentItemSelected);
					BookTableGateWay.min = 0;
					BookTableGateWay.max = 0;
					ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
			        singleton.changeViews("y");    
				}
			});
			return row;
		});
	}
	
	public void deleteHandle() {
		BookModel book = new BookModel();
		book = bookTable.getSelectionModel().getSelectedItem();
		BookTableGateWay connection = new BookTableGateWay();
		connection.setConnection();
		connection.deleteBook(book.getId());  
		connection.closeConnection();
		populateTable(0, 50);
		searchHandle();
		
	}
	
	// autocomplete search
	public void searchHandle() {
		// p-> true is a predicate that must be true. This is a lambda expression
		FilteredList<BookModel> filteredBooks = new FilteredList<>(bookList, p-> true);
		search.textProperty().addListener((observable, oldVal, newVal) ->{
			filteredBooks.setPredicate(book ->{
				// if filter is empty, display all books
				if(newVal == null || newVal.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newVal.toLowerCase();
				
				if(book.getTitle().toLowerCase().contains(lowerCaseFilter)) {
					return true;
				}
				
				return false;
			});
		});
		
		SortedList<BookModel> sortedBooks = new SortedList<>(filteredBooks);
		sortedBooks.comparatorProperty().bind(bookTable.comparatorProperty());
		bookTable.setItems(sortedBooks);
	}
	
	
	public void nextHandle() {
		populateTable(50, 0);
		searchHandle();
		
	}
	
	public void prevHandle() {
		populateTable(-50, 0);
		searchHandle();
	}
	
	public void firstHandle() {
		BookTableGateWay.min = 0;
		BookTableGateWay.max = 0;
		populateTable(0, 50);
		searchHandle();
		
	}
	
	public void lastHandle() {
		BookTableGateWay.min = 0;
		
		populateTable(9966, 0);
		searchHandle();
		
	}
	
}









