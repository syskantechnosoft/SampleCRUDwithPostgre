package com.scb.jdbc;

import com.scb.jdbc.bean.EmployeeBean;
import com.scb.jdbc.dao.EmployeeDAO;
import com.scb.jdbc.dao.EmployeeDAOImpl;

public class DAOMain {

	public static void main(String[] args) {
		EmployeeDAO empDAO = new EmployeeDAOImpl();
		EmployeeDAOImpl.connect();
		
		EmployeeBean emp1 = new EmployeeBean(7, "Test");
		empDAO.insert(emp1);
		
		System.out.println("List of Employees");
		System.out.println(empDAO.findAll());
		
		
	}

}
