package com.web.oa.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;

import com.javaproject.examplecollect.jdbcexample.DbcpPool;

public class JdbcUtil {
	
	private static DataSource dataSource;
	
	static{
		try {
		InputStream resourceAsStream = DbcpPool.class.getClassLoader().getResourceAsStream("dbcfg.properties");
		Properties prop = new Properties(); 
		prop.load(resourceAsStream);
		
		BasicDataSource basicData = new BasicDataSource();
		basicData.setDriverClassName(prop.getProperty("driverClass"));
		basicData.setUrl(prop.getProperty("url"));
		basicData.setUsername(prop.getProperty("user"));
		basicData.setPassword(prop.getProperty("password"));
		basicData.setInitialSize(10);  
		basicData.setMaxActive(20);  
		basicData.setMaxIdle(10);  
		basicData.setMaxWait(1000); 
		dataSource = basicData;
		
		} catch (IOException e) {
			throw new RuntimeException("初始化连接池失败");
		}
	}
	public static Connection getConnection(){
		if (dataSource == null) {
			throw new RuntimeException("没数据源");
		}
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException("获取数据连接失败");
		}
	}
	
	
	public static void release(Connection conn,PreparedStatement ps,ResultSet rs){
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				rs = null;
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				ps = null;
				e.printStackTrace();
			}
			
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				conn = null;
				e.printStackTrace();
			}
			
		}
	}

	public static DataSource getDataSource() {
		return dataSource;
	}

	public static void setDataSource(DataSource dataSource) {
		JdbcUtil.dataSource = dataSource;
	}
	

}
