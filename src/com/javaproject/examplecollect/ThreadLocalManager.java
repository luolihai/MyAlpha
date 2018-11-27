package com.javaproject.examplecollect;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.javaproject.examplecollect.jdbcexample.DbcpPool;

public class ThreadLocalManager {

	//1,连接池
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();
	
	public static Connection getConnection(){
		Connection connection = threadLocal.get();
		try {
		if (connection == null) {
			threadLocal.set(DbcpPool.getDataBase().getConnection());
		}
		} catch (SQLException e) {
			throw new RuntimeException("获取连接失败");
		}
		return threadLocal.get();
	}
	
	//释放连接
	public static void releaseConnect(){
		try {
			getConnection().close();
			threadLocal.remove();
		} catch (SQLException e) {
			throw new RuntimeException("释放连接失败");
		}
	}
	
	//2，threadLocal控制事务
	//开启事务
	public static Connection getAutoCommitConnection(){
		Connection conn = null;
		try {
			 conn = getConnection();
			 conn.setAutoCommit(false);
		} catch (SQLException e) {
			throw new RuntimeException("提交事务失败");
		}
		return conn;
	}
	
	//提交
	public static void commitConnect(){
		try {
			getConnection().commit();
		} catch (SQLException e) {
			throw new RuntimeException("提交事务失败");
		}
	}
	
	//回滚
	public static void rollbackConnect(){
		try {
			getConnection().rollback();
		} catch (SQLException e) {
			throw new RuntimeException("回滚事务失败");
		}
	}
	
	
	
	
	
}
