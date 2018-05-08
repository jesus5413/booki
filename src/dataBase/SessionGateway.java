package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SessionGateway {
	private static Logger logger = LogManager.getLogger(SessionGateway.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public SessionGateway() {
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
	
	public void insertSession(int sessId, String username) {
		try {
			myStmt = conn.prepareStatement("insert into session (sess_id, username) values (?, ?)");
			myStmt.setInt(1, sessId);
			myStmt.setString(2, username);
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void removeSession(int sessId) {
		try {
			myStmt = conn.prepareStatement("delete from session");
			myStmt.execute();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String checkPerms() {
		try {
			myStmt = conn.prepareStatement("select username from session");
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
				return rs.getString("username");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}

	public int checkSessionId() {
		try {
			myStmt = conn.prepareStatement("select sess_id from session");
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
				return rs.getInt("sess_id");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
}
