package com.collectionframe.iface;

import java.util.*;
import com.collectionframe.*;

public interface InvoiceDAO {
	
	Collection<Invoice> findAll();
	//Collection<Invoice> sortedBy(String propName);
	//Collection<Invoice> sortedByInvoiceNumber();
	boolean add(Invoice entity);
	boolean add(Invoice ...invoices);//variable arguments can be passed
	Invoice findById(int id);
	boolean remove(Invoice entity);
	boolean update(Invoice oldElement, Invoice newElement);
	

}
