package com.javaproject.examplecollect.aoptranscation;

import java.sql.SQLException;
import java.util.List;

public interface BaseDao {
	
	
	public int add(String sql, Object[] objects) throws SQLException;

	public int add(String sql1, Object[] objects1,String sql2, Object[] objects2) throws SQLException;
	
	public int excuteSql(String sql, Object[] objects) throws SQLException;
	
	Object selectForObject(String sql,Object[] objects,Class clazz);
	
	List<Object> selectForList(String sql,Object[] objects,Class clazz);
}
