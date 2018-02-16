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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.AuthorModel;

public class AuthorTableGateWay {
	
	private Connection conn = null;
	private PreparedStatement myStmt = null;
	private ResultSet rs = null;
	
	public AuthorTableGateWay() {
		
	}
	
	public void setConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://easel2.fulgentcorp.com:3306/wwh124?useSSL=false","wwh124","sanAntonio1234");
			System.out.println("connection success\n");
			//conn.close();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("some erroe\n");
			System.out.println(e);
		}
		
	}
	
	public void closeConnection() {
		try{
			conn.close();
			System.out.println("connection closed! \n");
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
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
				authorDetail.setDateOfBirth(rs.getString("dob"));
				authorDetail.setGender(rs.getString("gender"));
				authorDetail.setWebSite(rs.getString("web_site"));
				authorList.add(authorDetail);
				//System.out.println(authorDetail.getDateOfBirth());
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return authorList;
	}
	
	public void deleteAuthor(int ID) {
		
	}
	

	
	

}
