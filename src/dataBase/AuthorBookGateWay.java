package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import model.BookModel;

public class AuthorBookGateWay {
	
	private static Logger logger = LogManager.getLogger(AuthorTableGateWay.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public AuthorBookGateWay() {
		// TODO Auto-generated constructor stub
	}
	
	public void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://easel2.fulgentcorp.com:3306/wwh124?useSSL=false","wwh124","sanAntonio1234");
			logger.debug("connection success\n");
		}catch (Exception e) {
			logger.debug("some error\n");
			logger.debug(e);
		}
	}
	
	public void closeConnection() {
		try{
			conn.close();
			logger.debug("connection closed! \n");
		}catch (Exception e) {
			logger.debug(e);
		}
	}
	
	public void insertAuthorsForBook(BookModel book) {
//		try {
//			myStmt = conn.prepareStatement("select author_id from book where ID = ?");
//			myStmt.setInt(1,  book.getId());
//			rs = myStmt.executeQuery();
//			rs.next();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
	public void deleteAuthor(int bookId, int authId) {
		try {
			myStmt = conn.prepareStatement("delete from author_book where author_id = ? and book_id = ?");
			myStmt.setInt(1,  authId);
			myStmt.setInt(2, bookId);
			myStmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
