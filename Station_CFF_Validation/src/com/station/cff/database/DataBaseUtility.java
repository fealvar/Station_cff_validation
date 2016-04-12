package com.station.cff.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * 
 * @author java-coders
 *
 */
public class DataBaseUtility {
	
	/**These information need to be provided. It can be kept in property file.For now you can put these values**/
	private static String host="";
	private static String port="";
	private static String dbname="";
	private static String user="";
	private static String password="";
	/***********************************************************/

	private static Connection connection=null;
	private static String OrcaleJDBCDriver="oracle.jdbc.driver.OracleDriver";
	private static String OraclethinDriver="Jdbc:oracle:thin:@";
	
	/**
	 * 
	 * @return Connection
	 */
	public static Connection getConnection() {
		try {
			if(connection==null || connection.isClosed()){
				/**This line will create oracle driver in JVM.It is a way to create object dynamically using reflection **/
				Class.forName(OrcaleJDBCDriver);
				/**Open a database connection.Here DriverManager provided by JDBC driver **/
				connection=DriverManager.getConnection(OraclethinDriver+ host+":" + port + ":"+ dbname,	user, password);
			}
			return connection;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return connection; 
	}
	
	/**
	 * 
	 * @param query
	 * @param connection
	 * @return ResultSet
	 */
	public static ResultSet executeQuery(String query,Connection connection){
		ResultSet rs = null;
		try( Statement stmt =  connection.createStatement();) {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 
	 * @param query
	 * @param connection
	 * @param elementToReturn
	 * @return String
	 */
	public static String executeQuery(String query,Connection connection,int elementToReturn){
		ResultSet rs = null;
		String rtnString = "";
		try( Statement stmt =  connection.createStatement();) {
			rs = stmt.executeQuery(query);
			while(rs.next()){
				rtnString=rs.getString(elementToReturn);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rtnString;
	}

	/**
	 * 
	 * @param query
	 * @param connection
	 * @return integer
	 */
	public static int executeInsert(String query,Connection connection){
		try( Statement stmt =  connection.createStatement();) {
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * 
	 * @param query
	 * @param connection
	 * @return integer
	 */
	public static int executeDelete(String query,Connection connection){
		try( Statement stmt =  connection.createStatement();) {
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * 
	 * @param query
	 * @param connection
	 * @return integer
	 */
	public static int executeUpdate(String query,Connection connection){
		try( Statement stmt =  connection.createStatement();) {
			return stmt.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
