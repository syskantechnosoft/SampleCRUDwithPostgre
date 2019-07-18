package com.scb.gauss.project.dao;

import java.util.List;

import com.scb.gauss.project.model.Customer;

public interface CRUDwithDAO {
	public List<Customer> displayAll();
	public Customer display(int customerId);
	public int insert(Customer c);
	public int update(int customerId,Customer c);
	public int delete(int customerId);
}
