package model;

import java.sql.Date;

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
	private Date dateAdded;
	
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
		this.isbn.set(isbn);
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
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
}
