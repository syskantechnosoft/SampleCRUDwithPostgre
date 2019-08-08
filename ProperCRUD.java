package com.scb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class ProperCRUD {
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private Connection conn = null;
	private ResultSet rs = null;

	public void connect() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			conn = DriverManager.getConnection(url, "postgres", "postgres");
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public void closeResource() {
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

	public int insert(int id, String name) {
		int insertStatus = 0;
		try {
			pstmt = conn.prepareStatement("INSERT INTO employee VALUES (?, ?)");
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			insertStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}

		return insertStatus;
	}

	public int update(int id, String name) {
		int updateStatus = 0;
		try {
			pstmt = conn.prepareStatement("UPDATE employee SET emp_name=? WHERE emp_id=?");
			pstmt.setInt(2, id);
			pstmt.setString(1, name);
			updateStatus = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return updateStatus;
	}

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

	public ResultSet findAll() {
		try {
			stmt=conn.createStatement();
			rs = stmt.executeQuery("select * from employee");
			System.out.println("Emp_ID \t Emp_Name");
			while (rs.next()) {
				System.out.println(rs.getInt("emp_id") + " \t " + rs.getString("emp_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return rs;
	}

	public ResultSet findById(int id) {
		try {
			stmt=conn.createStatement();
			rs = stmt.executeQuery("select * from employee where emp_id=" + id);
			System.out.println("Emp_ID \t Emp_Name");
			while (rs.next()) {
				System.out.println(rs.getInt("emp_id") + " \t " + rs.getString("emp_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
//			System.out.println(e.getMessage());
		}
		return rs;
	}

	public static void main(String[] args) {
		ProperCRUD obj = new ProperCRUD();
		obj.connect();
		obj.findAll();
		
		Scanner input = new Scanner(System.in);
		int id=0;
		String name=null;
		
		System.out.println("Enter employee id :");
		id= input.nextInt();
		System.out.println("Enter employee name");
		name = input.next();
		int status = 0;
		status = obj.insert(id, name);
		if (status>0) {
			System.out.println("1 Record inserted successfully");
		}else {
			System.out.println("Error while inserting data..");
		}
		
		obj.findAll();
		
		obj.closeResource();
		
	}

}
