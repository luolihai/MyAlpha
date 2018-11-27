package com.javaproject.examplecollect.jdbcexample;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;
/**
 * 导入两包：commons-dbcp..jar,commons-pool..jar
 * @author 26031
 *
 */
public class DbcpPool {
	
	private static  BasicDataSource dataSource;
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
	


	@Test
	public void testConnection(){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()){
				System.out.println(rs.getInt("id"));
			}
		} catch (Exception e) {
			throw new RuntimeException("连接失败");
		}finally{
			//关闭连接
			release(conn, ps, rs);
		}
		
	}
	
	public static BasicDataSource getDataBase(){
		return dataSource;
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
}
