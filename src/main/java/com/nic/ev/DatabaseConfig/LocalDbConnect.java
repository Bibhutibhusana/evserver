package com.nic.ev.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDbConnect {
Connection connection;
	
	public Connection getConnection() throws ClassNotFoundException {
		
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://10.242.75.208:5432/ev","postgres","304599");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
