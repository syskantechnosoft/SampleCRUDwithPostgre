package com.scb.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.scb.jdbc.bean.EmployeeBean;
import com.scb.jdbc.util.Common;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static Statement stmt = null;
	private static PreparedStatement pstmt = null;
	private static Connection conn = null;
	private static ResultSet rs = null;

	public static void connect() {
		conn = Common.connect();
	}

	@Override
	public int insert(EmployeeBean emp) {
		int insertStatus = 0;
		try {
			pstmt = conn.prepareStatement("INSERT INTO employee VALUES (?, ?)");
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			insertStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}

		return insertStatus;
	}

	@Override
	public int update(int id, EmployeeBean emp) {
		int updateStatus = 0;
		try {
			pstmt = conn.prepareStatement("UPDATE employee SET emp_name=? WHERE emp_id=?");
			pstmt.setInt(2, id);
			pstmt.setString(1, emp.getEmpName());
			updateStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return updateStatus;
	}

	@Override
	public int delete(int id) {
		int deleteStatus = 0;
		try {
			pstmt = conn.prepareStatement("DELETE from employee where emp_id=?");
			pstmt.setInt(1, id);
			deleteStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return deleteStatus;
	}

	@Override
	public List<EmployeeBean> findAll() {
		List<EmployeeBean> employees = new ArrayList<EmployeeBean>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from employee");

			while (rs.next()) {
				EmployeeBean emp = new EmployeeBean();
				emp.setEmpId(rs.getInt("emp_id"));
				emp.setEmpName(rs.getString(2));
				employees.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return employees;
	}

	@Override
	public EmployeeBean findById(int id) {
		EmployeeBean emp = new EmployeeBean();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from employee where emp_id=" + id);

			if (rs.next()) {
				emp.setEmpId(rs.getInt("emp_id"));
				emp.setEmpName(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return emp;
	}

	public static void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
			if (pstmt != null)
				pstmt.close();
			if (rs != null)
				rs.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}
