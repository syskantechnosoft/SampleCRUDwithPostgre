package com.scb.gauss.project.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Utility {
	// Declaring Instance Variables
		private static Statement stmt = null;
		private static ResultSet rs = null;
		private static Connection conn = null;
		private static PreparedStatement pstmt = null;

		public static Connection connect() {
			try {
				// Step 1 : Loading & Registering the Driver
				Class.forName("org.postgresql.Driver");

				// Step 2 : Establishing the connection
				String url = "jdbc:postgresql://localhost:5432/Project";
				conn = DriverManager.getConnection(url, "postgres", "postgres");
			} catch (Exception e) {
				System.out.println("Exception in connect:" + e.getMessage());
			}

			return conn;
		}
		
		public static void closeResources() {
			try {
				if (rs != null)
					rs.close();
				if (stmt != null)
					stmt.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				System.out.println("Exception in close:" + e.getMessage());
			}

		}
}
