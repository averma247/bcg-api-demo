package com.qa.rest.test;

import java.sql.*;

public class Demo_DBAutomation {

	public static void main(String[] args) {

		try {
			// Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://superman-uat-db.mysql.database.azure.com:3306",
					"supermanadmin@superman-uat-db", "Mankind@1008");

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from supermandb_13jan.applicationconfiguration");

			ResultSetMetaData rsmd = rs.getMetaData();
			int colnum = rsmd.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= colnum; i++) {
					if (i > 1)
						System.out.print(",  ");
					String columnValue = rs.getString(i);
					// System.out.print(columnValue + " " + rsmd.getColumnName(i));
					System.out.print(columnValue);
				}
				System.out.println("");
			}

			/*
			 * while(rs.next())
			 * System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
			 */

			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
