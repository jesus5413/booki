package model;

import javafx.beans.property.SimpleStringProperty;

public class Publisher {
	private int ID;
	private SimpleStringProperty publisherName;
	
	public Publisher() {
		this.ID = 0;
		this.publisherName = new SimpleStringProperty();
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public SimpleStringProperty getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName.set(publisherName);
	}
	
	

}
