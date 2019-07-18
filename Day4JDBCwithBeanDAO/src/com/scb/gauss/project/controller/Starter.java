package com.scb.gauss.project.controller;

import java.util.ArrayList;
import java.util.List;

import com.scb.gauss.project.common.Utility;
import com.scb.gauss.project.dao.CustomerDAO;
import com.scb.gauss.project.dao.CustomerDAOImpl;
import com.scb.gauss.project.model.Customer;

public class Starter {

	public static void main(String[] args) {

		CustomerDAO customerDAO = new CustomerDAOImpl();

		customerDAO.Connection();
		// Insert Operation
		Customer c = new Customer();
		c.setCustomerId(109);
		c.setCustomerName("From JAVA Bean");
		c.setEmail("dao@gmail.com");
		c.setMobile(9887566765l);
		int insertStatus = 0;
		insertStatus = customerDAO.insert(c);
		if (insertStatus > 0) {
			System.out.println("Successfully Inserted");
		}

		// Display All Operation
		List<Customer> customerList = new ArrayList<Customer>();
		customerList = customerDAO.displayAll();
		System.out.println("Displaying ALL Records");
		System.out.println("___________________________________________");
		for (Customer cust : customerList) {
			System.out.println(cust);
		}

		// Update Operation
		c.setCustomerName("Updated");
		c.setEmail("update@gmail.com");
		c.setMobile(9878786778l);
		int updateStatus = 0;
		updateStatus = customerDAO.update(109, c);
		if (updateStatus > 0) {
			System.out.println("Successfully Updated");
		}
		// Display One
		Customer c1 = customerDAO.display(109);
		System.out.println("Updated Record");
		System.out.println(c1);

		// Delete Operation
		int deleteStatus = 0;
		deleteStatus = customerDAO.delete(109);
		if (deleteStatus > 0) {
			System.out.println("Successfully deleted");
		}

		Utility.closeResources();
	}

}
