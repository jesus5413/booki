package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.interfaces.PBEKey;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.sun.org.apache.regexp.internal.recompile;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.BookModel;
import model.Publisher;

public class PublisherTableGateWay {
	private static Logger logger = LogManager.getLogger(AuthorTableGateWay.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public PublisherTableGateWay() {
		// TODO Auto-generated constructor stub
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
	
	
	public ObservableList<Publisher> getPublishers(){
		ObservableList<Publisher> publisherList = FXCollections.observableArrayList();
		
		
		try {
			myStmt = conn.prepareStatement("select * from publishers");
			rs = myStmt.executeQuery();
			while(rs.next()) {
				Publisher pub = new Publisher();
				pub.setID(rs.getInt("ID"));
				pub.setPublisherName(rs.getString("publisher_name"));
				publisherList.add(pub);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return publisherList;
	}
	
	public Publisher getPublisherByID(int id) {
		Publisher publisher = new Publisher();
		try {
			myStmt = conn.prepareStatement("select * from publishers where ID = ?");
			myStmt.setInt(1, id);
			rs = myStmt.executeQuery();
			rs.next();
			publisher.setID(rs.getInt("ID"));
			publisher.setPublisherName(rs.getString("publisher_name"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return publisher;
	}

}








