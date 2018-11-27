package com.javaproject.examplecollect.jdbcexample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.KeyedHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.web.oa.bean.User;

public class DBUtilsTest {
	
	private QueryRunner runner = new QueryRunner(DbcpPool.getDataBase());

	//crud
	/*@Test
	public void insertTest() throws SQLException{
		int update = runner.update("insert into user(id,username,password,roleid) values(?,?,?,?)", 5,"lulu","123",1);
		System.out.println(update);
	}*/
	

	//事务crud
	/*@Test
	public void updateTest() throws SQLException{
		Connection conn = null;
		try {
			conn = DbcpPool.getDataBase().getConnection();
			conn.setAutoCommit(false);
			int money = -100;
			
			runner.update(conn,"update user set roleid=roleid-? where id=?", money,5);
	//		int i = 1/0;
			runner.update(conn,"update user set roleid=roleid+? where id=?", money,4);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.close();
		}
	}*/
	
	
	//单列查询
	/*@Test
	public void selectTest1() throws SQLException{
		User user = runner.query("select username from user where id=?", new BeanHandler<User>(User.class),1);
		System.out.println(user.getUsername());
		
	}*/
	//多列column查询
	/*@Test
	public void selectTest2() throws SQLException{
		List<Object[]>  list = runner.query("select username from user", new ArrayListHandler());
		for (Object[] objects : list) {
			System.out.println("------");
			for (Object obj:objects) {
				System.out.println(obj);
			}
		}
		
		
	}*/
	//返回object列值list
	/*@Test
	public void selectTest3() throws SQLException{
		List<Object> list = runner.query("select username from user", new ColumnListHandler());
		for (Object obj : list) {
			System.out.println(obj);
		}
		
		
	}*/
	
	//多街多列值
	/*@Test
	public void selectTest4() throws SQLException{
		Map<Object, Map<String, Object>> query = runner.query("select id,username from user", new KeyedHandler());
		for(Entry<Object, Map<String, Object>> entrys : query.entrySet()){
			System.out.println("----------");
			for (Entry<String, Object> entry : entrys.getValue().entrySet()) {
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
		}
	}*/
	//查询返回单行所有列的map值
	/*@Test
	public void selectTest5() throws SQLException{
		Map<String, Object> query = runner.query("select id,username from user", new MapHandler());
		for(Entry<String, Object> entry : query.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
			
		}
	}*/
	
	//查询单列
	/*@Test
	public void selectTest5() throws SQLException{
		Object query = runner.query("select count(*) from user", new ScalarHandler());
		System.out.println(query);
	}*/
	
	
	//查询BloB
	/*@Test
	public void selectBlob() throws SQLException, IOException{
		Object[] query = runner.query("select * from t1 where id=?", new ArrayHandler(),1);
		FileOutputStream out = new FileOutputStream(new File("d:/t2.png"));
		out.write((byte[])query[1]);
		out.close();
	}*/
	
	//批量添加操作
/*	@Test
	public void batchInsertTest() throws SQLException{
		Object[][] obj = new Object[5][];
		for (int i = 0; i < obj.length; i++) {
			obj[i] = new Object[3];
			for (int j = 0; j < obj[i].length; j++) {
				obj[i][j] = 10+i+j;
			}
			
		}
		int[] batch = runner.batch("insert into user(id,username,password) values(?,?,?)", obj);
		System.out.println(batch);
	}*/
	
	//大数据类型操作
	/*@Test
	public void BigDataTest() throws SQLException, IOException{
		//方式一
		InputStream in = new FileInputStream(new File("d:/111.png"));
		runner.update("insert into t1(id,context) values(?,?)", 1,in);
		
		
		//方式二
		File file = new File("d:/111.png");
		byte[] b = new byte[(int)file.length()];
		FileInputStream in = new FileInputStream(file);
		in.read(b);
		Blob blob = new SerialBlob(b);
		in.close();
		runner.update("insert into t1(id,context) values(?,?)", 2,b);
	}*/
	
	//插入长文本类型
	/*@Test
	public void BigTextTest() throws SQLException, IOException{
		//方式一
		InputStream in = new FileInputStream(new File("d:/t1.txt"));
		runner.update("insert into t2(id,context) values(?,?)", 1,in);
		
		File file = new File("d:/t1.txt");
		char[] ch = new char[(int)file.length()];
		Reader rd = new FileReader(file);
		rd.read(ch);
		rd.close();
		Clob clob = new SerialClob(ch);
		runner.update("insert into t2(id,context) values(?,?)", 2,clob);
	}*/
	
	
	
}
