package model;


import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

import alert.AlertHelper;
import exception.InvalidDoBException;
import exception.InvalidIsbnException;
import exception.InvalidNameException;
import exception.InvalidPubDateException;
import exception.InvalidSummaryException;
import exception.InvalidTitleException;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import validators.Validator;

public class BookModel {
	private int id;
	private SimpleStringProperty title;
	private SimpleStringProperty summary;
	private SimpleIntegerProperty yearPublished;
	private int publisherId;
	private Publisher publisher;
	private SimpleStringProperty isbn;
	private Timestamp dateAdded;
	private int authorId;
	
	public BookModel() {
		this.id = 0;
		this.title = new SimpleStringProperty();
		this.summary = new SimpleStringProperty();
		this.yearPublished = new SimpleIntegerProperty();
		this.publisherId = 0;
		this.publisher = new Publisher();
		this.isbn = new SimpleStringProperty();
		this.dateAdded = new Timestamp(1);
	}
	
	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title.get();
	}
	
	public boolean setTitle(String title) {
		boolean val = false;
		
		try {
			if(Validator.validTitle(title)) {
				this.title.set(title);
				val = true;
			}
		} catch (InvalidTitleException e) {
			AlertHelper.showWarningMessage("Name Error", 
					"Invalid Book Name",
					"1: Book must be between 1 and 255 characters!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}
	
	public String getSummary() {
		return summary.get();
	}
	
	public boolean setSummary(String summary) {
		this.summary.set(summary);
		boolean val = false;
		
		try {
			if(Validator.validSummary(summary)) {
				this.summary.set(summary);
				val = true;
			}
		}catch(InvalidSummaryException e) {
			AlertHelper.showWarningMessage("Summary Error", 
					"Invalid Summary Size",
					"2: Summary must be less than 65,536 characters!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}
	
	public int getYearPublished() {
		return yearPublished.get();
	}
	
	public boolean setYearPublished(int yearPublished) {
		boolean val = false;
		
		try {
			if(Validator.validPublicationYear(yearPublished)) {
				this.yearPublished.set(yearPublished);
				val = true;
			}
		} catch (InvalidPubDateException e) {
			AlertHelper.showWarningMessage("Publishing Date Error", 
					"Invalid Publishing Date",
					"3: Book can't be released on that date!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}

	public int getPublisherId() {
		return publisherId;
	}
	
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}
	
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	public String getIsbn() {
		return isbn.get();
	}
	
	public boolean setIsbn(String isbn) {
		boolean val = false;
		
		try {
			if(Validator.validIsbn(isbn)) {
				this.isbn.set(isbn);
				val = true;
			}
		} catch (InvalidIsbnException e) {
			AlertHelper.showWarningMessage("ISBN Error", 
					"Invalid ISBN Length",
					"4: ISBN of book can't be over 13 characters!");
			e.printStackTrace();
			val = false;
		}finally {
			return val;
		}
	}
	
	public java.sql.Timestamp getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(java.sql.Timestamp timestamp) {
		this.dateAdded = timestamp;
	}
	
	// Returns field name that has been changed
	public String compare(BookModel book) {
		String msg;
		String field;
		String strVal1 = "";
		String strVal2 = "";
		int numVal1 = -1;
		int numVal2 = -1;
		
		// first we check for value that is different
		if(!this.getTitle().equals(book.getTitle())) {
			field =  "title";
		}else if(!this.getSummary().equals(book.getSummary())) {
			field =  "summary";
		}else if(this.getYearPublished() != book.getYearPublished()) {
			field =  "year_published";
		}else if(this.getPublisherId() != book.getPublisherId()) {
			field =  "publisher_id";
		}else if(!this.getIsbn().equals(book.getIsbn())) {
			field =  "isbn";
		}else {
			// assume nothing was changed
			return null;
		}
		
		// Next, we will save both old and new values of specified field
		switch(field) {
		case "title":
			strVal1 = this.getTitle();
			strVal2 = book.getTitle();
			break;
		case "summary":
			strVal1 = this.getSummary();
			strVal2 = book.getSummary();
			break;
		case "year_published":
			numVal1 = this.getYearPublished();
			numVal2 = book.getYearPublished();
			break;
		case "publisher_id":
			numVal1 = this.getPublisherId();
			numVal2 = book.getPublisherId();
			break;
		case "isbn":
			strVal1 = this.getIsbn();
			strVal2 = book.getIsbn();
			break;
		}
		
		// finally we build and return the message
		msg = field + " changed from ";
		
		if(strVal1.isEmpty()) {
			msg = msg + numVal1 + " to " + numVal2;
		}else {
			msg = msg + strVal1 + " to " + strVal2;
		}
		
		return msg;
	}
	
	public void getAuthors() {
		
	}
}
