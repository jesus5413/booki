package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BookAuditTrailGateWay {
	private static Logger logger = LogManager.getLogger(BookAuditTrailGateWay.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;

	public BookAuditTrailGateWay() {
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

	public void addMessage(int bookId, String msg) {
		try {
			myStmt = conn.prepareStatement("insert into book_audit_trail (book_id, entry_msg) values (?, ?)");
			myStmt.setInt(1, bookId);
			myStmt.setString(2, msg);
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
