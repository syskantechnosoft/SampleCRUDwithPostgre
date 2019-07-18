package com.scb.gauss.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.scb.gauss.project.common.Utility;
import com.scb.gauss.project.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	private Statement stmt = null;
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	
	public void Connection() {
		this.conn = Utility.connect();
	}

	@Override
	public List<Customer> displayAll() {
		String query = "select * from \"Customer\"";
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery(query);
			while (rs.next()) {
				Customer c = new Customer();
				c.setCustomerId(rs.getInt("customer_id"));
				c.setCustomerName(rs.getString(2));
				c.setMobile(rs.getLong(3));
				c.setEmail(rs.getString("email"));
				customerList.add(c);
			}

		} catch (Exception e) {
			System.out.println("Exception in displayAll:" + e.getMessage());
		}
		return customerList;

	}

	@Override
	public Customer display(int customerId) {
		String query = "select * from \"Customer\" where customer_id="+customerId;
		Customer c = new Customer();
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery(query);
			if (rs.next()) {
				c.setCustomerId(rs.getInt("customer_id"));
				c.setCustomerName(rs.getString(2));
				c.setMobile(rs.getLong(3));
				c.setEmail(rs.getString("email"));
			}

		} catch (Exception e) {
			System.out.println("Exception in displayAll:" + e.getMessage());
		}
		return c;
	}

	@Override
	public int insert(Customer c) {
		String insertQuery = "insert into \"Customer\" values (?,?,?,?)";
		int insertStatus = 0;
		try {
			pstmt = conn.prepareStatement(insertQuery);
			pstmt.setInt(1, c.getCustomerId());
			pstmt.setString(2, c.getCustomerName());
			pstmt.setLong(3, c.getMobile());
			pstmt.setString(4, c.getEmail());

			insertStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in Insert:" + e.getMessage());
		}

		return insertStatus;
	}

	@Override
	public int update(int customerId, Customer c) {
		String updateQuery = "update \"Customer\"  set customer_name=?, mobile=?, email=? where customer_id=?";
		int updateStatus = 0;
		try {
			pstmt = conn.prepareStatement(updateQuery);
			pstmt.setString(1, c.getCustomerName());
			pstmt.setLong(2,c.getMobile());
			pstmt.setString(3, c.getEmail());
			pstmt.setInt(4, customerId);

			updateStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in Update:" + e.getMessage());
		}
		return updateStatus;
	}

	@Override
	public int delete(int customerId) {
		String deleteQuery = "delete from  \"Customer\" where customer_id=?";
		int deleteStatus = 0;
		try {
			pstmt = conn.prepareStatement(deleteQuery);
			pstmt.setInt(1, customerId);
			deleteStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("Exception in delete:" + e.getMessage());
		}
		return deleteStatus;
	}

}
