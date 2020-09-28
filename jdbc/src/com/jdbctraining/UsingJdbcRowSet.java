package com.jdbctraining;

import java.sql.*;
import javax.sql.*;
import javax.sql.rowset.*;
import javax.sql.rowset.spi.*;

import com.collectionframe.Invoice;

public class UsingJdbcRowSet {

	public static void main(String[] args) {
		
		Connection con = ConnectionUtility.getDerbyConnection();
		
		RowSetFactory fact;
		try {
			fact=RowSetProvider.newFactory();
			CachedRowSet rowSet=fact.createCachedRowSet();
			
			rowSet.setUrl("jdbc:derby:SampleDB;create=true");
			
			String sql="select * from Invoice";
			
			rowSet.setCommand(sql);
			
			rowSet.execute();
			
			int i=1;
			
			do {
			
			System.out.println("Page===="+i);
			
			while (rowSet.next()) {
				int invoiceNumber = rowSet.getInt("invoiceNumber");
				String customerName=rowSet.getString("customerName");
				double amount = rowSet.getDouble("amount");
				
				Invoice inv=new Invoice (invoiceNumber, customerName, amount);
				System.out.println(inv);
				
			}
			i++;
			}while(rowSet.nextPage());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		

	}

}
