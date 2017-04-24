package com.infsb.commons.dbsource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException, SQLException, PropertyVetoException {
		System.out.println("Hello World!");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		for (int i = 0; i < 40000; i++) {
			try {

				conn = DBConnectionSource.getInstance().getConnection("userdb2");

				ps = conn.prepareStatement("select * from UM_USER");
				rs = ps.executeQuery();

				while (rs.next()) {
					System.out.println(i + " = " + rs.getString("UM_USER_NAME"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			} catch (PropertyVetoException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
		}
	}
}
