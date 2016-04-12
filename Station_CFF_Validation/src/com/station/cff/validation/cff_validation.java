package com.station.cff.validation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.station.cff.database.DataBaseUtility;

public class cff_validation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			Connection connection = DataBaseUtility.getConnection();
			/** It will fetch the stations table **/
			String query="SELECT * FROM FIRSTSPIN.STATIONS";
			ResultSet resultSet = DataBaseUtility.executeQuery(query, connection);
			while(resultSet.next()){
				
				System.out.println(resultSet.getString(1));
				System.out.println(resultSet.getString(2));
				System.out.println(resultSet.getString(3));
				System.out.println(resultSet.getString(4));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
