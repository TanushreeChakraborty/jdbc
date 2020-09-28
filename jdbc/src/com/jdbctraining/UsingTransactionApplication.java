package com.jdbctraining;

import java.sql.*;

public class UsingTransactionApplication {

	public static void main(String[] args) {
		
		Connection con = ConnectionUtility.getDerbyConnection();
		
		try {
			
			con.setAutoCommit(false);
			
			String sqlOne="insert into invoice values(?,?,?)";
			String sqlTwo="insert into invoice values(?,?,?)";
			
			PreparedStatement pstmt=null;
			PreparedStatement pstmt1=null;
			int rowUpdated=0;
			
				
				pstmt=con.prepareStatement(sqlOne);
				
				pstmt.setInt(1, 808);
				pstmt.setString(2, "loke");
				pstmt.setDouble(3, 456);
				
				pstmt.executeUpdate();	
			
				
				pstmt1=con.prepareStatement(sqlTwo);
				
				pstmt1.setInt(1, 808);
				pstmt1.setString(2, "loke");
				pstmt1.setDouble(3, 456);
				
				pstmt.executeUpdate();	
				
				con.commit();
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			try {
				con.rollback();
				System.out.println("roll back");
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		
	}

}
