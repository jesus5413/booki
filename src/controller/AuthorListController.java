package controller;

import javafx.fxml.FXML;
import javafx.scene.layout.*;

public class AuthorListController {
	@FXML private BorderPane authorPane;
	
	public AuthorListController() {
		
	}
	
	public void setNode(BorderPane node) {
		this.authorPane = node;
		
	}

}
