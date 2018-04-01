package controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;

import alert.AlertHelper;
import changeSingleton.ChangeViewsSingleton;
import dataBase.AuthorBookGateWay;
import dataBase.AuthorTableGateWay;
import dataBase.BookAuditTrailGateWay;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import dataBase.TempStorage;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import model.AuthorBook;
import model.AuthorModel;
import model.BookModel;
import model.Publisher;

public class BookDetailController {
	@FXML
	public Label ID;
	public TextField title;
	public TextArea summary;
	public TextField yearPublished;
	public TextField royalties;
	public ComboBox<Publisher> publisher;
	public ComboBox<AuthorModel> allAuthsCb; 
	@FXML public TextField ISBN;
	public Label dateAdded;
	public Button update;
	public Button save;
	public Button auditTrail;
	public Button addAuthButton;
	public Button delButton;
	public Button updateRoyalty;
	ObservableList<Publisher> pubList = FXCollections.observableArrayList();
	ObservableList<AuthorModel> allAuthsList = FXCollections.observableArrayList();
	ObservableList<AuthorBook> authBookList = FXCollections.observableArrayList();
	public AuthorBook obj = new AuthorBook();
	
	@FXML private TableView<AuthorBook> table; // holds authors for book
	@FXML private TableColumn<AuthorBook, String> authorName;
	@FXML private TableColumn<AuthorBook, Integer> royalty;
	
	// Database connections
	AuthorTableGateWay authConn;
	PublisherTableGateWay pubCon;
	BookTableGateWay bookCon;
	AuthorBookGateWay authBookConn;
	BookAuditTrailGateWay auditConn;
	
	public void initialize() {
		authConn = new AuthorTableGateWay();
		pubCon = new PublisherTableGateWay();
		bookCon = new BookTableGateWay();
		authBookConn = new AuthorBookGateWay();
		auditConn = new BookAuditTrailGateWay();
		setCellDataToTextField();
		
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
			
			// add authors associated with book
			authConn.setConnection();
			allAuthsList = authConn.getAuthors();
			authConn.closeConnection();
			
			// add publishers
			pubCon.setConnection();
			pubList = pubCon.getPublishers();
			pubCon.closeConnection();
			comboboxStringConverter();
			
			publisher.setItems(pubList);
			allAuthsCb.setItems(allAuthsList);
		}
		
	}
	
	public void textFieldPlaceHolder() {
		update.setVisible(true);
		ID.setText(Integer.toString(TempStorage.oneBook.getId()));
		title.setText(TempStorage.oneBook.getTitle());
		summary.setText(TempStorage.oneBook.getSummary());
		yearPublished.setText(Integer.toString(TempStorage.oneBook.getYearPublished()));
		
		// Add publishers
		pubCon.setConnection();
		pubList = pubCon.getPublishers();
		pubCon.closeConnection();
		publisher.setItems(pubList);
		publisher.setPromptText(TempStorage.oneBook.getPublisher().getPublisherName().get());
		
		// add authors associated with book
		authConn.setConnection();
		allAuthsList = authConn.getAuthors();
		authConn.closeConnection();
		allAuthsCb.setItems(allAuthsList);
		allAuthsCb.setPromptText("Authors");
		
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
		
		allAuthsCb.setConverter(new StringConverter<AuthorModel>() {
		    @Override
		    public String toString(AuthorModel object) {
		        return object.getFirstName() + " " + object.getLastName();
		    }

		    @Override
		    public AuthorModel fromString(String string) {
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
		bookCon.setConnection();
		authBookList = bookCon.getAuthorsForBook(TempStorage.oneBook);
		bookCon.closeConnection();
		setCell();
		table.setItems(authBookList);
	}
	
	public void setCell() {
		royalty.setCellValueFactory(new PropertyValueFactory<>("royalty"));
		authorName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor().getFirstName() + " " +cellData.getValue().getAuthor().getLastName()));
		
	}
	
	// add new author to current book
	public void addAuthButtonHandle(){
		AuthorBookGateWay conn = new AuthorBookGateWay();
		AuthorBook authBook = new AuthorBook();
		
		// Get AuthorBook information
		authBook.setAuthor(allAuthsCb.getSelectionModel().getSelectedItem());
		authBook.setBook(TempStorage.oneBook);
		authBook.setRoyalty(0.0); // default royalty of 0
		
		// Update AuthorBook table with current book id and newly added author id and royalty
		authBookConn.setConnection();
		authBookConn.insertAuthor(authBook.getAuthor().getID(), authBook.getBook().getId(), new BigDecimal(authBook.getRoyalty()));
		authBookConn.closeConnection();
		
		// Add new author to audit trail
		auditConn.setConnection();
		auditConn.addMessage(authBook.getBook().getId(),
				authBook.getAuthor().getFirstName() + " " + authBook.getAuthor().getLastName() + " has been added");
		auditConn.closeConnection();
		
		// once new author has been added, repopulate author table
		populateAuthorTable();
	}
	
	// Whenever user highlights and clicks delete, delete specified author from authorBook table and view
	public void delButtonHandle() {
		AuthorBook selectedAuthor = new AuthorBook();
		
		selectedAuthor = table.getSelectionModel().getSelectedItem();
		table.getItems().removeAll(table.getSelectionModel().getSelectedItem());
	
		// delete author from authorBook
		authBookConn.setConnection();
		authBookConn.deleteAuthor(TempStorage.oneBook.getId(), selectedAuthor.getAuthor().getID());
		authBookConn.closeConnection();
		
		// Add deleted author to audit trail
		auditConn.setConnection();
		auditConn.addMessage(TempStorage.oneBook.getId(),
				selectedAuthor.getAuthor().getFirstName() + " " + selectedAuthor.getAuthor().getLastName() + " has been deleted");
		auditConn.closeConnection();
		
		// once author has been deleted, repopulate the author table
		populateAuthorTable();
	}
	
	public void setCellDataToTextField() {
		table.setOnMousePressed(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				obj = table.getItems().get(table.getSelectionModel().getSelectedIndex());
				royalties.setText(Integer.toString(obj.getRoyalty()));
				
			}
			
		});
		
	}
	
	public void updateRoyaltyHandle() {
		double value = Double.parseDouble(royalties.getText());
		double oldVal = 0.0;
		
		if(value >= 0 && value <= 1.0) {
			// get previous value to store in audit trail
			authBookConn.setConnection();
			oldVal = authBookConn.getRoyalty(TempStorage.oneBook.getId(), obj.getAuthor().getID());
			authBookConn.closeConnection();
			
			obj.setRoyalty(value);
			bookCon.setConnection();
			bookCon.updateAuthBook(obj);
			bookCon.closeConnection();
			
			// Add changes in royalty
			auditConn.setConnection();
			auditConn.addMessage(TempStorage.oneBook.getId(),
					"Royalty changed from " + oldVal + " to " + value);
			auditConn.closeConnection();
			
			populateAuthorTable();
		}else {
			//some error alert stating to input number between 0 and 1
			AlertHelper.showWarningMessage(
					"Wrong Royalty", 
					"Enter correct royalty",
					"Please enter a royalty between 0 and 1");
		}
		
		
	}
	
	
	
	
	
}