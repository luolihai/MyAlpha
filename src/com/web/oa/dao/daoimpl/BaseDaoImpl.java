package com.web.oa.dao.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.web.oa.dao.BaseDao;
import com.web.oa.util.JdbcUtil;
public class BaseDaoImpl implements BaseDao{

	
	public int add() {
		System.out.println("走dao方法");
		return 0;
	}

	/**
	 * 执行insert,update,delete类型Sql
	 */
	public int excuteSql(String sql, Object[] objects) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			int count = ps.getParameterMetaData().getParameterCount();
			if (count>0) {
				for (int i = 0; i < objects.length; i++) {
					ps.setObject(i+1, objects[i]);
				}
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JdbcUtil.release(conn, ps, null);
		}
	}

	@Override
	public Object selectForObject(String sql, Object[] objects,Class clazz) {
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
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
			conn = JdbcUtil.getConnection();
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
