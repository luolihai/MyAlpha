package com.javaproject.examplecollect.aoptranscation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import com.javaproject.examplecollect.ThreadLocalManager;
import com.javaproject.examplecollect.jdbcexample.DbcpPool;
import com.web.oa.util.JdbcUtil;
public class BaseDaoImpl implements BaseDao{

	private QueryRunner runner = new QueryRunner(DbcpPool.getDataBase());
	
	@Override
	public int add(String sql, Object[] objects) throws SQLException {
		return excuteSql(sql, objects);
	}
	
	public int add(String sql1, Object[] objects1,String sql2, Object[] objects2) throws SQLException {
		excuteSql(sql1, objects1);
		int i = 1/0;
		excuteSql(sql2, objects2);
		return 0;
	}

	/**
	 * 执行insert,update,delete类型Sql
	 * @throws SQLException 
	 */
	public int excuteSql(String sql, Object[] objects) throws SQLException {
		
		return runner.update(ThreadLocalManager.getConnection(), sql, objects);
		
	}

	@Override
	public Object selectForObject(String sql, Object[] objects,Class clazz) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ThreadLocalManager.getConnection();
			ps = conn.prepareStatement(sql);
			int count = ps.getParameterMetaData().getParameterCount();
			if (count>0) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i+1, objects[i]);
				}
			}
			
			rs =  ps.executeQuery();
			BeanHandler beanHandler = new BeanHandler(clazz);
			Object obj = beanHandler.objectHandler(rs);
			return obj;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(conn, ps, null);
		}
	}

	@Override
	public List<Object> selectForList(String sql, Object[] objects, Class clazz) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = ThreadLocalManager.getConnection();
			ps = conn.prepareStatement(sql);
			int count = ps.getParameterMetaData().getParameterCount();
			if (count>0) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i+1, objects[i]);
				}
			}
			
			rs =  ps.executeQuery();
			BeanHandler beanHandler = new BeanHandler(clazz);
			List<Object> list = beanHandler.ListHandler(rs);
			return list;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(conn, ps, rs);
		}
	}

	

}
