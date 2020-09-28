package com.collectionframe.daos;

import java.util.*;

import java.sql.*;

import com.collectionframe.Invoice;
//import com.collectionframe.iface.InvoiceDAO;
//import com.collectionframe.utils.CustomerNameComparator;
//import com.collectionframe.utils.InvoiceNumberComparator;
import com.collectionframe.iface.InvoiceDAO;
import com.jdbctraining.ConnectionUtility;

public class InvoiceDaoImpl implements InvoiceDAO {
	
	private List<Invoice> invList;
	private Connection derbyConnection;

	public InvoiceDaoImpl() {
		super();
		this.invList=new ArrayList<Invoice>();
		this.derbyConnection=ConnectionUtility.getDerbyConnection();
	}

	@Override
	public Collection<Invoice> findAll() {
		// TODO Auto-generated method stub
		
		String sql="select * from invoice";
		PreparedStatement pstmt=null;
		try {
			pstmt=this.derbyConnection.prepareStatement(sql);
			ResultSet result=pstmt.executeQuery();
			ResultSetMetaData metaData= result.getMetaData();
			int columnCount=metaData.getColumnCount();
			
			for(int i=1;i<3;i++) {
				
				System.out.println("=====column  :="+metaData.getColumnName(i));
				
			}
			
			DatabaseMetaData dbInfo=this.derbyConnection.getMetaData();
			
			System.out.println("Driver Name:"+dbInfo.getDriverName());
			System.out.println("Product Version"+dbInfo.getDatabaseProductVersion());
			
			
			while (result.next()) {
				
				int invoiceNumber = result.getInt("invoiceNumber");
				String customerName= result.getString("customerName");
				double amount=result.getDouble("amount");
				
				Invoice inv=new Invoice(invoiceNumber, customerName, amount);
				
				this.invList.add(inv);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return this.invList;
	}

	@Override
	public boolean add(Invoice entity) {
		// TODO Auto-generated method stub
		
		String sql="insert into invoice values(?,?,?)";
		PreparedStatement pstmt= null;
		int rowUpdate=0;
		try {
			 pstmt=this.derbyConnection.prepareStatement(sql);
			pstmt.setInt(1,entity.getInvoiceNumber());
			pstmt.setString(2, entity.getCustomerName());
			pstmt.setDouble(3, entity.getAmount());
			
			rowUpdate=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return rowUpdate==1?true:false;
	}

	@Override
	public Invoice findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Invoice... invoices) {
		boolean result = false;
		for(Invoice eachInvoice:invoices) {
			
			add(eachInvoice);
			result=true;
			
		}
		return result;
	}

	@Override
	public boolean remove(Invoice entity) {
		// TODO Auto-generated method stub
		return this.invList.remove(entity);
	}
	
	public int indexOf(Invoice oldElement) {
		
		int i=0;
		Iterator<Invoice> iterator=invList.iterator();
		while (iterator.next()!=oldElement) {
			Invoice inv = iterator.next();
			i++;
		}
		return i;
		
	}

	@Override
	public boolean update(Invoice oldElement, Invoice newElement) {
		this.invList.set(indexOf(oldElement), newElement);
		return true;
		
	}

	/*@Override
	public Collection<Invoice> sortedByInvoiceNumber() {
		
		Collections.sort(this.invList);
		return this.invList;
	}

	@Override
	public Collection<Invoice> sortedBy(String propName) {
		if(propName.equalsIgnoreCase("invoiceNumber"))
			Collections.sort(this.invList,getComparator(1));
		if(propName.equalsIgnoreCase("customerName"))
			Collections.sort(this.invList,getComparator(2));
		return this.invList;
	}

private Comparator<Invoice> getComparator(int key) {
		// TODO Auto-generated method stub
		switch(key) {
		
		case 1:
			return new InvoiceNumberComparator();
		case 2:
			return new CustomerNameComparator();
		default:
			return null;
		}
		
	}*/

}
