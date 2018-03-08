package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorTableGateWay;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import dataBase.TempStorage;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
	@FXML public Button delete;
	@FXML public TextField search;
	
	public void initialize() {
		getBook();
		populateTable();

	}
	
	public void populateTable() {
		BookTableGateWay bookCon = new BookTableGateWay();
		bookCon.setConnection();
		bookList = bookCon.getBooks(); 
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
		bookTable.getItems().removeAll(bookTable.getSelectionModel().getSelectedItem());
		
		BookTableGateWay connection = new BookTableGateWay();
		connection.setConnection();
		connection.deleteBook(book.getId());  
		connection.closeConnection();
		
	}
	
	

}









