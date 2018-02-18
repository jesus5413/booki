package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Observable;

import com.mysql.jdbc.*;
import com.mysql.jdbc.exceptions.MySQLStatementCancelledException;
import com.mysql.jdbc.exceptions.jdbc4.MySQLDataException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

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
			System.out.println("connection success\n");
			//conn.close();
		}catch (Exception e) {
			System.out.println("some erroe\n");
			System.out.println(e);
		}
		
	}
	
	/**
	 * closes connection
	 */
	public void closeConnection() {
		try{
			conn.close();
			System.out.println("connection closed! \n");
		}catch (Exception e) {
			System.out.println(e);
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
			System.out.println("deletion successful \n");
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
		try {
			myStmt = conn.prepareStatement("update authorDetail set first_name = ? , last_name = ? , dob = ? , gender = ? , web_site = ? where ID = ?");
			myStmt.setString(1, author.getFirstName());
			myStmt.setString(2, author.getLastName());
			myStmt.setDate(3, author.getDateOfBirth());
			myStmt.setString(4, author.getGender());
			myStmt.setString(5, author.getWebSite());
			myStmt.setInt(6, author.getID());
			myStmt.executeUpdate();
			System.out.println("update successful\n");
		} catch (SQLException e) {
			throw new AppException(e);
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
			System.out.println("read successful\n");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
}
