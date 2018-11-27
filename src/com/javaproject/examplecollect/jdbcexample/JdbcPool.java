package com.javaproject.examplecollect.jdbcexample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.javaproject.examplecollect.developmodel.ZxModelPool;


/**
 * 自定义连接池
 * @author 26031
 *
 */
public class JdbcPool{
	
	//连接池
//	private static List<Connection> pool = new ArrayList<Connection>();
	//属性同步
	private static List<Connection> pool = Collections.synchronizedList(new ArrayList<Connection>());
	
	private static String driverClass;
	private static String url;
	private static String user;
	private static String password;
	static{
		Properties ps = new Properties();
		try {
			ps.load(JdbcPool.class.getClassLoader().getResourceAsStream("dbcfg.properties"));
			driverClass = ps.getProperty("driverClass");
			url = ps.getProperty("url");
			user = ps.getProperty("user");
			password = ps.getProperty("password");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//10个连接
		for (int i = 0; i < 10; i++) {
			pool.add(getLocalConnect());
		}
		
	}
	
	//拿连接 
	public static Connection getPoolConnect(){
		if (pool.size() > 0) {
			System.out.println("取出了一个，当前数是："+(pool.size()-1));
			return pool.remove(0);
		}
		throw new RuntimeException("连接用完了"); 
	}
	
	//还连接
	public static void retrieveConnect(Connection conn){
		if (pool.size() < 10) {
			System.out.println("添加了一个，当前数是："+(pool.size()+1));
			pool.add(conn);
		}
	}
	
	public static List<Connection> getPool(){
		return pool;
	}
	
	
	//从驱动中获取连接
	private static Connection getLocalConnect(){
		Connection conn = null;
		try {
			Connection oldConn = DriverManager.getConnection(url, user, password);
			System.out.println("放进连接池");
			conn = new ZxModelPool(oldConn,pool);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void main(String[] args) throws SQLException {
		MyConnectionPool jdbcPool = new MyConnectionPool();
		Connection conn1 = jdbcPool.getConnection();
		System.out.println("连接池有：");
		for (int i = 0; i < pool.size(); i++) {
			System.out.println(pool.get(i));
		}
		System.out.println("取出连接是："+conn1);
		System.out.println();
		Connection conn2 = jdbcPool.getConnection();
		System.out.println("连接池有：");
		for (int i = 0; i < pool.size(); i++) {
			System.out.println(pool.get(i));
		}
		System.out.println("取出连接是："+conn2);
		
		System.out.println();
		System.out.println("开始回收：");
//		jdbcPool.retrieveConnect(conn1);
		//代理模式
		conn1.close();
		for (int i = 0; i < pool.size(); i++) {
			System.out.println(pool.get(i));
		}
		System.out.println();
//		jdbcPool.retrieveConnect(conn2);
		//代理模式
		conn1.close();
		for (int i = 0; i < pool.size(); i++) {
			System.out.println(pool.get(i));
		}
	}
	
}
