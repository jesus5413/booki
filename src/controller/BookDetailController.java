package controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import alert.AlertHelper;
import changeSingleton.ChangeViewsSingleton;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import dataBase.TempStorage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.StringConverter;
import model.AuthorBook;
import model.BookModel;
import model.Publisher;

public class BookDetailController {
	@FXML
	public Label ID;
	public TextField title;
	public TextArea summary;
	public TextField yearPublished;
	public ComboBox<Publisher> publisher;
	@FXML public TextField ISBN;
	public Label dateAdded;
	public Button update;
	public Button save;
	public Button auditTrail;
	ObservableList<Publisher> pubList = FXCollections.observableArrayList();
	ObservableList<AuthorBook> authBookList = FXCollections.observableArrayList();
	
	@FXML private TableView<AuthorBook> table;
	@FXML private TableColumn<AuthorBook, String> authorName;
	@FXML private TableColumn<AuthorBook, Integer> royalty;
	
	public void initialize() {
		if(TempStorage.oneBook != null) {
			save.setVisible(false);	
			comboboxStringConverter();
			textFieldPlaceHolder();
			populateAuthorTable();
		}else {
			save.setVisible(true);
			update.setVisible(false);
			ID.setText("");
			dateAdded.setText(java.time.LocalDate.now().toString());
			ObservableList<Publisher> pubList = FXCollections.observableArrayList();
			PublisherTableGateWay pubCon = new PublisherTableGateWay();
			pubCon.setConnection();
			pubList = pubCon.getPublishers();
			pubCon.closeConnection();
			comboboxStringConverter();
			publisher.setItems(pubList);

		}
		
	}
	
	public void textFieldPlaceHolder() {
		update.setVisible(true);
		ID.setText(Integer.toString(TempStorage.oneBook.getId()));
		title.setText(TempStorage.oneBook.getTitle());
		summary.setText(TempStorage.oneBook.getSummary());
		yearPublished.setText(Integer.toString(TempStorage.oneBook.getYearPublished()));
		
		
		PublisherTableGateWay pubCon = new PublisherTableGateWay();
		pubCon.setConnection();
		pubList = pubCon.getPublishers();
		pubCon.closeConnection();
		publisher.setItems(pubList);
		publisher.setPromptText(TempStorage.oneBook.getPublisher().getPublisherName().get());
		
		
		ISBN.setText(TempStorage.oneBook.getIsbn());
		dateAdded.setText(TempStorage.oneBook.getDateAdded().toString());	
	}
	
	public void updateButtonHandle() {
		// validations before allowing to save
		if(!TempStorage.oneBook.setTitle(title.getText())) {
			return;
		}
		
		if(!TempStorage.oneBook.setSummary(summary.getText())) {
			return;
		}
		
		if(!TempStorage.oneBook.setYearPublished(Integer.parseInt(yearPublished.getText()))) {
			return;
		}
		
		if(!TempStorage.oneBook.setIsbn(ISBN.getText())) {
			return;
		}
		
//		TempStorage.oneBook.setTitle(title.getText());
//		TempStorage.oneBook.setSummary(summary.getText());
//		TempStorage.oneBook.setYearPublished(Integer.parseInt(yearPublished.getText()));
		//TempStorage.oneBook.setIsbn(ISBN.getText());
		
		if(publisher.getSelectionModel().getSelectedItem() != null) {
			TempStorage.oneBook.setPublisher(publisher.getSelectionModel().getSelectedItem());
			TempStorage.oneBook.setPublisherId(publisher.getSelectionModel().getSelectedItem().getID());
		}
		
		BookTableGateWay bookCon = new BookTableGateWay();
		bookCon.setConnection();
		bookCon.updateBook(TempStorage.oneBook);
		bookCon.closeConnection();
		TempStorage.oneBook = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("t");
	}
	
	/**
	 * convert the actual model object to a string so it can  be readable
	 */
	public void comboboxStringConverter() {
		publisher.setConverter(new StringConverter<Publisher>() {
		    @Override
		    public String toString(Publisher object) {
		        return object.getPublisherName().get();
		    }

		    @Override
		    public Publisher fromString(String string) {
		        return null;
		    }
		});
	}
	
	public void saveButtonHandle() {
		BookModel book = new BookModel();
		book.setTitle(title.getText());
		book.setSummary(summary.getText());
		book.setYearPublished(Integer.parseInt(yearPublished.getText()));
		book.setPublisher(publisher.getSelectionModel().getSelectedItem());
		book.setPublisherId(publisher.getSelectionModel().getSelectedItem().getID());
		book.setIsbn(ISBN.getText());
		
		// validations before allowing to save
		if(!book.setTitle(title.getText())) {
			return;
		}
		
		if(!book.setSummary(summary.getText())) {
			return;
		}
		
		if(!book.setYearPublished(Integer.parseInt(yearPublished.getText()))) {
			return;
		}
		
		if(!book.setIsbn(ISBN.getText())) {
			return;
		}
		
		BookTableGateWay bookCon = new BookTableGateWay();
		bookCon.setConnection();
		bookCon.saveBook(book);
		bookCon.closeConnection();
		
		TempStorage.oneBook = null;
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("t");
	}
	
	public void auditTrailHandle() {
		if(ID.getText().isEmpty()) {
			// pop alert
			AlertHelper.showWarningMessage(
					"No Audit Trail",
					"Audit Trail Doesn't Exist",
					"Please save book before looking up audit trail");
			return;
		}
		
		ChangeViewsSingleton singleton = ChangeViewsSingleton.getInstance();
		singleton.changeViews("a");
		
	}
	
	
	
	public void populateAuthorTable() {
		BookTableGateWay conn = new BookTableGateWay();
		conn.setConnection();
		authBookList = conn.getAuthorsForBook(TempStorage.oneBook);
		conn.closeConnection();
		setCell();
		table.setItems(authBookList);
		
	}
	
	public void setCell() {
		royalty.setCellValueFactory(new PropertyValueFactory<>("royalty"));
		authorName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor().getFirstName() + " " +cellData.getValue().getAuthor().getLastName()));
	}
	

}





