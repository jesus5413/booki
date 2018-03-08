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
	private java.sql.Timestamp dateAdded;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title.get();
	}
	public void setTitle(String title) {
		this.title.set(title);
	}
	public String getSummary() {
		return summary.get();
	}
	public void setSummary(String summary) {
		this.summary.set(summary);
	}
	public int getYearPublished() {
		return yearPublished.get();
	}
	public void setYearPublished(int yearPublished) {
		this.yearPublished.set(yearPublished);
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
	public void setIsbn(String isbn) {
		this.isbn.set(isbn);
	}
	public java.sql.Timestamp getDateAdded() {
		return dateAdded;
	}
	public void setDateAdded(java.sql.Timestamp timestamp) {
		this.dateAdded = timestamp;
	}
	
	
	
}
