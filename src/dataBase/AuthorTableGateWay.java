package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Observable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.*;
import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import alert.AlertHelper;
import exception.AppException;
import exception.InvalidDoBException;
import exception.InvalidNameException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AuthorModel;
import validators.Validator;

/**
 * this class connects to the database but as well does the necessary function to update and delete rows
 * @author jesusnieto
 *
 */
public class AuthorTableGateWay {
	private static Logger logger = LogManager.getLogger(AuthorTableGateWay.class);
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public AuthorTableGateWay() {
		
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
	
	/**
	 * 
	 * returns the database info as an observablelist <authormodel> collection
	 * @return
	 */
	public ObservableList<AuthorModel> getAuthors(){
		ObservableList<AuthorModel> authorList = FXCollections.observableArrayList();
		try {
			myStmt = conn.prepareStatement("select * from authorDetail");
			rs = myStmt.executeQuery();
			while(rs.next()) {
				AuthorModel authorDetail = new AuthorModel();
				authorDetail.setID(rs.getInt("ID"));
				authorDetail.setFirstName(rs.getString("first_name"));
				authorDetail.setLastName(rs.getString("last_name"));
				authorDetail.setDateOfBirth(rs.getDate("dob"));
				authorDetail.setGender(rs.getString("gender"));
				authorDetail.setWebSite(rs.getString("web_site"));
				authorDetail.setLastModified(rs.getTimestamp("last_modified").toLocalDateTime());
				authorList.add(authorDetail);
			}		
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		
		return authorList;
	}
	
	/**
	 * 
	 * deletes row from database using ID
	 * must pass the int ID value of the author we want to delete
	 * @param ID
	 */
	public void deleteAuthor(int ID) {
		try {
			myStmt = conn.prepareStatement("delete from authorDetail where ID = ?");
			myStmt.setInt(1, ID);
			myStmt.execute();
			logger.debug("deletion successful \n");
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * 
	 * function updates rows in the server
	 * - most pass an author model
	 * @param author
	 */
	public void updateAuthor(AuthorModel author) {
		// we will check for timestamp differences here. Continue updating only if Timestamps of
		// author and same author in DB are the same
		if(isDifferentTs(author)) {
			AlertHelper.showWarningMessage(
					"Update Error", 
					"Author Being Updated",
					"Author is currently being updated by someone");
		}else {
			try {
				myStmt = conn.prepareStatement("update authorDetail set first_name = ? , last_name = ? , dob = ? , gender = ? , web_site = ? where ID = ?");
				myStmt.setString(1, author.getFirstName());
				myStmt.setString(2, author.getLastName());
				myStmt.setDate(3, author.getDateOfBirth());
				myStmt.setString(4, author.getGender());
				myStmt.setString(5, author.getWebSite());
				myStmt.setInt(6, author.getID());
				myStmt.executeUpdate();
				
				myStmt = conn.prepareStatement("select last_modified from authorDetail");
				rs = myStmt.executeQuery();
				
				while(rs.next()) {
					// update author's lastModified val
					author.setLastModified(rs.getTimestamp("last_modified").toLocalDateTime());		
				}
				
				logger.debug("update successful\n");
			} catch (SQLException e) {
				throw new AppException(e);
			}	
		}
	}
	
	/**
	 * when a user requests author information, read form database
	 * @param author - author that was double clicked on
	 */
	public void readAuthor(AuthorModel author) {
		try {
			// IDs are unique, thus we select the record where it matches
			myStmt = conn.prepareStatement("select * from authorDetail where ID = ?");
			myStmt.setInt(1, author.getID());
			myStmt.executeQuery();
			logger.debug("read successful\n");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// return author with specific id
	public AuthorModel getSingleAuthor(int id) {
		AuthorModel auth = new AuthorModel();
		
		try {
			// IDs are unique, thus we select the record where it matches
			myStmt = conn.prepareStatement("select * from authorDetail where ID = ?");
			myStmt.setInt(1, id);
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
				auth.setID(id);
				auth.setFirstName(rs.getString("first_name"));
				auth.setLastName(rs.getString("last_name"));
				auth.setDateOfBirth(rs.getDate("dob"));
				auth.setGender(rs.getString("gender"));
				auth.setWebSite(rs.getString("web_site"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return auth;
	}
	
	public void saveAuthor(AuthorModel author) {
		try {
			String query = " insert into authorDetail (first_name, last_name, dob, gender, web_site) values (? , ? , ? , ? , ?)";
			myStmt = conn.prepareStatement(query);
			myStmt.setString(1, author.getFirstName());
			myStmt.setString(2, author.getLastName());
			myStmt.setDate(3, author.getDateOfBirth());
			myStmt.setString(4, author.getGender());
			myStmt.setString(5, author.getWebSite());
			myStmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private boolean isDifferentTs(AuthorModel author) {
		// we will get last_modified to compare timestamp with author model we're updating
		try {
			myStmt = conn.prepareStatement("select last_modified from authorDetail where ID = ?");
			myStmt.setInt(1,  author.getID());
			rs = myStmt.executeQuery();
			
			while(rs.next()) {
				AuthorModel authInDb = new AuthorModel();
				authInDb.setLastModified(rs.getTimestamp("last_modified").toLocalDateTime());
				
				// if dates are equal, compareTo returns 0
				if(author.getLastModified().compareTo(authInDb.getLastModified()) == 0) {
					return false;
				}
				
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
}
