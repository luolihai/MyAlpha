package com.javaproject.examplecollect.jdbcexample;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;
/**
 * 一般导入一个包：c3p0..jar
 * @author 26031
 *
 */
public class C3p0Pool {
	

	
	private static  ComboPooledDataSource dataSource;
	static{
		try {
		InputStream resourceAsStream = DbcpPool.class.getClassLoader().getResourceAsStream("dbcfg.properties");
		Properties prop = new Properties(); 
		prop.load(resourceAsStream);
		
		ComboPooledDataSource db = new ComboPooledDataSource();
		db.setDriverClass(prop.getProperty("driverClass"));
		db.setJdbcUrl(prop.getProperty("url"));
		db.setUser(prop.getProperty("user"));
		db.setPassword(prop.getProperty("password"));
		db.setInitialPoolSize(2);  
        // 设置连接池的最小值！   
		db.setMinPoolSize(1);  
        // 设置连接池的最大值！   
		db.setMaxPoolSize(10);  
        // 设置连接池中的最大Statements数量！  
		db.setMaxStatements(50);  
        // 设置连接池的最大空闲时间！  
		db.setMaxIdleTime(60);  
		
		dataSource = db;
		
		} catch (IOException | PropertyVetoException e) {
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
			DbcpPool.release(conn, ps, rs);
		}
		
	}
	


}
