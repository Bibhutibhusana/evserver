package com.nic.ev.DatabaseConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.nic.ev.exception.BusinessException;

public class LiveDbConnect {

	Connection connection;
	
	public Connection getConnection() throws SQLException,BusinessException {
		
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://10.246.40.238:5444/vow4","or","or#40018");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new BusinessException("Something Went Wrong in Data Access Layer" + e.getMessage());
		}
		
		return connection;
	}

}
