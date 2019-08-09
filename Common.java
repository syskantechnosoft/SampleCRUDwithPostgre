package com.scb.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class Common {
	private static Connection conn = null;

	public static Connection connect() {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://localhost:5432/postgres";
			conn = DriverManager.getConnection(url, "postgres", "postgres");
		} catch (Exception e) {
//			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return conn;
	}

}
