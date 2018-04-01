package dataBase;

import java.math.BigDecimal;
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
	
	public void insertAuthor(int authId, int bookId, BigDecimal royalty) {
		try {
			myStmt = conn.prepareStatement("insert into author_book (author_id, book_id, royalty) values (?, ?, ?)");
			myStmt.setInt(1, authId);
			myStmt.setInt(2, bookId);
			myStmt.setBigDecimal(3, royalty);
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
	
	public double getRoyalty(int bookId, int authId) {
		try {
			myStmt = conn.prepareStatement("select royalty from author_book where author_id = ? and book_id = ?");
			myStmt.setInt(1,  authId);
			myStmt.setInt(2, bookId);
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
			return rs.getBigDecimal("royalty").doubleValue();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
