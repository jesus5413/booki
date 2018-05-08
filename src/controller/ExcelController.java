package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.border.LineBorder;

import com.sun.corba.se.spi.ior.Writeable;
import com.sun.org.apache.xpath.internal.operations.And;

import auth.SessSing;
import dataBase.BookTableGateWay;
import dataBase.PublisherTableGateWay;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import model.AuthorBook;
import model.AuthorModel;
import model.BookModel;
import model.Publisher;
import view.MainLauncher;
import jxl.Workbook;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;

public class ExcelController {
	@FXML private Button save;
	@FXML private Button excelDoc;
	@FXML private ComboBox<Publisher> publisher;
	private String path;
	private int pubID;
	ObservableList<Publisher> pubList = FXCollections.observableArrayList();
	ObservableList<BookModel> bookList = FXCollections.observableArrayList();
	ObservableList<AuthorBook> authorBookList = FXCollections.observableArrayList();
	ObservableList<AuthorBook> fullList = FXCollections.observableArrayList();
	PublisherTableGateWay pubCon;
	BookTableGateWay bookCon;
	WritableWorkbook writeBook;
	
	public void initialize() {
		pubCon = new PublisherTableGateWay();
		bookCon = new BookTableGateWay();
		excelDoc.setVisible(false);
		
		pubCon.setConnection();
		pubList = pubCon.getPublishers();
		pubCon.closeConnection();
		pubList.remove(0);
		comboboxStringConverter();
		
		
	
		publisher.setItems(pubList);
		
		if(SessSing.getUsername().equalsIgnoreCase("sasquatch")) {
			save.setDisable(true);
		}	
	}
	
	public void comboboxStringConverter() {
		publisher.setConverter(new StringConverter<Publisher>() {
		    @Override
		    public String toString(Publisher object) {
		    		if(object == null) {
		    			object = pubList.get(pubList.size() - 1);
		    		}
		    		
		        return object.getPublisherName().get();
		    }

		    @Override
		    public Publisher fromString(String string) {
		        return null;
		    }
		});
	}
	
	
	public void saveHandle() throws IOException, RowsExceededException, WriteException {
		if(publisher.getSelectionModel().getSelectedItem() != null){
			DirectoryChooser chooser = new DirectoryChooser();
			File selectedDirectory = chooser.showDialog(MainLauncher.stage);
		
			if (selectedDirectory == null){
			
			}else {
				path = selectedDirectory.getAbsolutePath() + "/" + publisher.getSelectionModel().getSelectedItem().getPublisherName().get() + "Report" + ".xls";
				pubID = publisher.getSelectionModel().getSelectedItem().getID();
				writeBook = Workbook.createWorkbook(new File(path));
				WritableSheet sheet = writeBook.createSheet("Sheet1", 0);
				Label label = new Label(0, 0, "Royalty Report");
				sheet.addCell(label);
				Label label2 = new Label(0, 1, "Publisher: " + publisher.getSelectionModel().getSelectedItem().getPublisherName().get());
				sheet.addCell(label2);
				Label bookTitle = new Label(0,4, "Book Title: ");
				sheet.addCell(bookTitle);
				Label ISBN = new Label(1, 4, "ISBN: ");
				sheet.addCell(ISBN);
				Label author = new Label(2, 4, "Author: ");
				sheet.addCell(author);
				Label royalty = new Label(3, 4, "Royalt: ");
				sheet.addCell(royalty);
				
				
				
				
				System.out.println(path);
				bookCon.setConnection();
				bookList = bookCon.getBooksByPubID(pubID);
				

				int row = 5;
				int isbnRow = 5;
				int authorRow = 5;
				int royaltyRow = 5;
				int incrementHelper = 0;
				double sumRoyalty = 0.0;
				
				
				
				for(int x = 0; x < bookList.size(); x++) {
					authorBookList = bookCon.getAuthorsForBook(bookList.get(x));	
					if(authorBookList.get(0).getBook() != null) {
						Label book = new Label(0,row, authorBookList.get(0).getBook().getTitle().toString());
						sheet.addCell(book);
						Label isbn2 = new Label(1,isbnRow, authorBookList.get(0).getBook().getIsbn().toString());
						sheet.addCell(isbn2);
						
						for(int i = 0; i < authorBookList.size(); i++) {
							Label authorLabe = new Label(2, authorRow + incrementHelper, authorBookList.get(i).getAuthor().getFirstName() + "." + authorBookList.get(i).getAuthor().getLastName());
							sheet.addCell(authorLabe);
							Label royaltyVal = new Label(3, royaltyRow + incrementHelper, "."+Integer.toString(authorBookList.get(i).getRoyalty()));
							sheet.addCell(royaltyVal);
							sumRoyalty += (double)authorBookList.get(i).getRoyalty() / 100000;
							incrementHelper++;
						}
						
						Label totalRoyalty = new Label(2, royaltyRow + incrementHelper , "Total Royalty: ");
						sheet.addCell(totalRoyalty);
						Label sumroyalty = new Label(3, royaltyRow + incrementHelper , Double.toString(sumRoyalty));
						sheet.addCell(sumroyalty);
						sumRoyalty = 0;
						royaltyRow += 7 + incrementHelper;
						authorRow += 7 + incrementHelper;
						isbnRow += 7 + incrementHelper;
						row += 7 + incrementHelper;
						incrementHelper = 0;
					}
				}
				
				bookCon.closeConnection();
				excelDoc.setVisible(true);
			}
		}else {
			publisher.getSelectionModel().selectLast();
			saveHandle();
		}
	}
	
	public void excelHandle() throws IOException, WriteException {
		writeBook.write();
		writeBook.close();
		
	}
	
	
	
}
