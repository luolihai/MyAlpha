package com.javaproject.examplecollect.jdbcexample;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import com.mysql.jdbc.Driver;

public class JdbcProcedureProcess {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//数据连接
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql:///demo", "root","668408");
		String sql = "call pro_login(?,?,?)";
		CallableStatement preCall = conn.prepareCall(sql);
		//数据访问
		preCall.setString(1, "jack");
		preCall.setString(2, "111");
		preCall.registerOutParameter(3, Types.INTEGER);
		preCall.execute();
		
		int result = preCall.getInt(3);
		System.out.println(result);
		
		//结果输出
		conn.close();
		
		
		
	}
	
	
	
}
