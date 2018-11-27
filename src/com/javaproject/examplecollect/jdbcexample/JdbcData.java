package com.javaproject.examplecollect.jdbcexample;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;

public class JdbcData {
	
	@Test
	public void testData() throws SQLException{
		Connection conn = DbcpPool.getDataBase().getConnection();
		
		System.out.println("connection元数据");
		DatabaseMetaData metaData = conn.getMetaData();
		System.out.println("数据库名："+metaData.getDatabaseProductName());
		System.out.println("版本号："+metaData.getDatabaseProductVersion());
		
		PreparedStatement ps = conn.prepareStatement("select * from user where id=?");
		ParameterMetaData parameterMetaData = ps.getParameterMetaData();
		ps.setInt(1, 1);
		System.out.println();
		System.out.println("PreparedStatement元数据");
		System.out.println("预设值总数："+parameterMetaData.getParameterCount());
		
		ResultSet rs = ps.executeQuery();
		ResultSetMetaData rsMetaData = rs.getMetaData();
		System.out.println();
		System.out.println("rs元数据");
		System.out.println("列数："+rsMetaData.getColumnCount());
		//4表示int型，12表示String类型
		System.out.println("列类型："+rsMetaData.getColumnType(2));
		System.out.println("列名："+rsMetaData.getColumnName(2));
		
		
		
		
		
	}
	
	public static Connection getConnect(DataSource ds) throws SQLException{
		Connection connection = ds.getConnection();
		return connection;
		
	}

}
