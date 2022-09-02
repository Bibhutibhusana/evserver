package com.nic.ev.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LiveDbConnect {

	Connection connection;
	
	public Connection getConnection() {
		
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://10.246.40.187:5432/vow4","or","or#40018");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}

}
