package com.javaproject.examplecollect.jdbcexample;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class JDBCTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		selectText();
	
	}

	private static void selectText() throws IOException,
			ClassNotFoundException, SQLException {
		File file = new File("d:/xx1.txt");
		FileWriter out = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(out);
		Connection conn = getConn();
		String sql = "select descript from tab1 where id= 14";
		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			InputStream in = result.getBinaryStream(1);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String line = null;;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
				bw.write(line);
			}
			br.close();
		}
		out.close();
		conn.close();
	}

	private static void insertText() throws ClassNotFoundException,
			SQLException, FileNotFoundException {
		Connection conn = getConn();
		String sql = "insert into tab1(descript) values(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
		String path = JDBCTest.class.getResource("xx1.txt").getPath();
		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		ps.setBinaryStream(1, in, (int)file.length());
		ps.executeUpdate();
		conn.close();
	}

	private static void selectBlob() throws ClassNotFoundException,
			SQLException, FileNotFoundException, IOException {
		Connection conn = getConn();
		String sql = "select multiple from tab1 where id= 13";
		PreparedStatement ps = conn.prepareStatement(sql);
		File file = new File("d:/tt1.png");
		FileOutputStream out = new FileOutputStream(file);
		ResultSet result = ps.executeQuery();
		if (result.next()) {
			InputStream in = result.getBinaryStream(1);
			byte[] by = new byte[1024];
			int index = -1;
			while ((index = in.read(by)) != -1) {
				out.write(by, 0, index);
			}
			in.close();
		}
		out.close();
		conn.close();
	}

	private static void insertBlob() throws ClassNotFoundException,
			SQLException, FileNotFoundException, IOException {
		Connection conn = getConn();
		String sql = "insert into tab1(multiple) values(?)";
		PreparedStatement ps = conn.prepareStatement(sql);
//		ps.setInt(1, 11);
		URL resource = JDBCTest.class.getResource("tt1.png");
		String path = resource.getPath();
		System.out.println(path);
		File file = new File(path);
		FileInputStream in = new FileInputStream(file);
		ps.setBinaryStream(1, in,(int)file.length());
		ps.executeUpdate();
		in.close();
		conn.close();
	}

	private static void selectTest() throws ClassNotFoundException,
			SQLException {
		Connection conn = getConn();
		PreparedStatement prepStatement = conn.prepareStatement("select * from tab1");
		ResultSet result = prepStatement.executeQuery();
		while (result.next()) {
			System.out.println(result.getInt("id")+":"+result.getString("likes"));
		}
		
		System.out.println("****************");
		result.absolute(3);
		System.out.println(result.getInt("id")+":"+result.getString("likes"));
		System.out.println(result.getRow());//获取结果集当前行数
		
		System.out.println("****************");
		while (result.previous()) {
			System.out.println(result.getInt("id")+":"+result.getString("likes"));
		}
		
		System.out.println("****************");
		result.beforeFirst();
		result.next();
		System.out.println(result.getInt("id")+":"+result.getString("likes"));
	}

	/**
	 * 调用存储过程
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static void callProcedureTest() throws ClassNotFoundException,
			SQLException {
		Connection conn = getConn();
		CallableStatement call = conn.prepareCall("call pro_update_tab1()");
		boolean result = call.execute();
		System.out.println(result);
	}

	private static void connectTest() throws ClassNotFoundException,
			SQLException {
		//第一种
		Connection conn = getConn();
		Statement statement = conn.createStatement();
		//execute()执行selecte返回true,update返回false
		boolean exec = statement.execute("select * from tab1");
		//boolean exec = statement.execute("update tab1 set id=3 where id=5");
		System.out.println(exec);
	}

	/**
	 * Connection获取方式
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static Connection getConn() throws ClassNotFoundException,
			SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		/*
		//第二种,根据driver文件源码
		Driver driver = new Driver(); 
		DriverManager.registerDriver(driver);
		*/
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "668408");
		return conn;
	}
}
