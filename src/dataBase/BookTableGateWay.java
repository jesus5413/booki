package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exception.AppException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookModel;
import model.Publisher;

public class BookTableGateWay {
	private static Logger logger = LogManager.getLogger(AuthorTableGateWay.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public BookTableGateWay() {
		
	}
	
	/**
	 * opens connection
	 */
	public void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://easel2.fulgentcorp.com:3306/wwh124?useSSL=false","wwh124","sanAntonio1234");
			logger.debug("connection success\n");
		}catch (Exception e) {
			logger.debug("some error\n");
			logger.debug(e);
		}
	}
	
	/**
	 * closes connection
	 */
	public void closeConnection() {
		try{
			conn.close();
			logger.debug("connection closed! \n");
		}catch (Exception e) {
			logger.debug(e);
		}
	}
	
	public ObservableList<BookModel> getBooks(){
		ObservableList<BookModel> bookList = FXCollections.observableArrayList();
		try {
			myStmt = conn.prepareStatement("select * from book");
			rs = myStmt.executeQuery();
			while(rs.next()) {
			// we'll make a new model and add it onto our list
				BookModel book = new BookModel();
				book.setId(rs.getInt("ID"));
				book.setTitle(rs.getString("title"));
	 			book.setSummary(rs.getString("summary"));
				book.setYearPublished(rs.getInt("year_published"));
				book.setPublisherId(rs.getInt("publisher_id"));
				book.setIsbn(rs.getString("isbn"));
				book.setDateAdded(rs.getTimestamp("date_added"));
				bookList.add(book);	
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return bookList;
	}
	
	public void deleteBook(int ID) {
		try {
			myStmt = conn.prepareStatement("delete from book where ID = ?");
			myStmt.setInt(1, ID);
			myStmt.execute();
			logger.debug("deletion successful \n");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void updateBook(BookModel book) {
		try {
			// get book from books table and save that into a local variable to compare differences
			myStmt = conn.prepareStatement("select * from book where ID = ?");
			myStmt.setInt(1,  book.getId());
			rs = myStmt.executeQuery();
			rs.next();
			
			BookModel temp = new BookModel();
			temp.setId(rs.getInt("ID"));
			temp.setTitle(rs.getString("title"));
			temp.setSummary(rs.getString("summary"));
			temp.setYearPublished(rs.getInt("year_published"));
			temp.setPublisherId(rs.getInt("publisher_id"));
			temp.setIsbn(rs.getString("isbn"));
			temp.setDateAdded(rs.getTimestamp("date_added"));
			
			// here we will check our book for differences and build the message
			String msg = temp.compare(book);
					
			myStmt = conn.prepareStatement("update book set title = ? , summary = ? , year_published = ? , publisher_id = ? , isbn = ? where ID = ?");
			myStmt.setString(1, book.getTitle());
			myStmt.setString(2, book.getSummary());
			myStmt.setInt(3, book.getYearPublished());
			myStmt.setInt(4, book.getPublisherId());
			myStmt.setString(5, book.getIsbn());
			myStmt.setTimestamp(6, book.getDateAdded());
			myStmt.executeUpdate();
			
			// we will also insert a record into audit_book_trail with the previous changes along with the new ones
			myStmt = conn.prepareStatement("insert into book_audit_trail (book_id, date_added, entry_msg) values (?, ?, ?)");
			myStmt.setInt(1, book.getId());
			myStmt.setTimestamp(2, book.getDateAdded());
			myStmt.setString(3, msg);
			myStmt.execute();
			
			logger.debug("update successful\n");
		} catch (SQLException e) {
			throw new AppException(e);
		}	
	}
	
	public void readBook(BookModel book) {
		try {
			// IDs are unique, thus we select the record where it matches
			myStmt = conn.prepareStatement("select * from book where ID = ?");
			myStmt.setInt(1, book.getId());
			myStmt.executeQuery();
			logger.debug("read successful\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void saveBook(BookModel book) {
		try {
			String query = " insert into book (title, summary, year_published, publisher_id, isbn) values (? , ? , ? , ? , ?)";
			myStmt = conn.prepareStatement(query);
			myStmt.setString(1, book.getTitle());
			myStmt.setString(2, book.getSummary());
			myStmt.setInt(3, book.getYearPublished());
			myStmt.setInt(4, book.getPublisherId());
			myStmt.setString(5, book.getIsbn());
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}















