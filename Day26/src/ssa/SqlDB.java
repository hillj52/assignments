package ssa;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class SqlDB {

	private static SqlDB instance = null;
	
	public static SqlDB getInstance() {
		if (SqlDB.instance == null) 
			SqlDB.instance = new SqlDB();
		return instance;
	}
	
	private String userName;
	private String password;
	private String dbUrl;
	private String dbOptions;
	private String dbName;
	private Connection myConn;
	private PreparedStatement prepStmt;
	private SqlStatement sql;
	private ResultSet results;
	
	public List<Student> getAllStudents() {
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			this.connect();
			this.prepStmt = sql.getSelectStatement("student","*");
			results = this.prepStmt.executeQuery();
			while (results.next()) {
				int majorId = results.getInt("major_id");
				ResultSet majorInfo = null;
				if (majorId != 0) {
					this.prepStmt = sql.getSelectStatement("major","*","id = ?");
					prepStmt.setInt(1,majorId);
					majorInfo = prepStmt.executeQuery();
					majorInfo.next();
				}
				list.add(new Student(results.getInt("id"),
										results.getString("first_name"),
										results.getString("last_name"),
										results.getInt("sat"),
										results.getDouble("gpa"),
										((majorId == 0)?null:(new Major(majorInfo.getInt("id"),
																	majorInfo.getString("description"),
																	majorInfo.getInt("req_sat"))))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.close();
		}
		
		return list;
		
	}
	
	private void connect() throws SQLException {
		this.myConn = DriverManager.getConnection(this.dbUrl + dbName + this.dbOptions,this.userName,this.password);
		this.sql = new SqlStatement(myConn);
	}
	
	private void close() {
		try {
			this.myConn.close();
			this.prepStmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private SqlDB() {
		InputStream input = null;
		Properties props = new Properties();
		try {
			input = new FileInputStream("demo.properties");
			props.load(input);
			
			this.userName = props.getProperty("user");
			this.password = props.getProperty("password");
			this.dbUrl = props.getProperty("url");
			this.dbOptions = props.getProperty("options");
			this.dbName = props.getProperty("dbname");
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
