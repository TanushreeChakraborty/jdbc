package com.jdbctraining;

import com.collectionframe.Invoice;
import com.collectionframe.daos.InvoiceDaoImpl;
import com.collectionframe.iface.InvoiceDAO;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//System.out.println(ConnectionUtility.getDerbyConnection());
		
		//Invoice ram=new Invoice(101,"Ramesh",56000);
		InvoiceDAO dao=new InvoiceDaoImpl();
		//boolean result = dao.add(ram);
		//if (result) {
		//	System.out.println("row added");
		
	//}
		System.out.println(dao.findAll());

	}

}
