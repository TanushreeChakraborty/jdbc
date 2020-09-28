package com.jdbctraining;

import java.sql.*;

public class ConnectionUtility {
	
	public static Connection getDerbyConnection() {
		
		Connection derbyConection = null;
		
		try {
			
			String debyURL = "jdbc:derby:SampleDB;create=true";
			
			derbyConection=DriverManager.getConnection(debyURL);
			
			if(derbyConection!=null) {
				
				System.out.println("connection established");
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return derbyConection;
		
	}

}
