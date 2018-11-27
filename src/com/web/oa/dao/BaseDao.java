package com.web.oa.dao;

import java.util.List;

public interface BaseDao {

	int add();
	
	int excuteSql(String sql,Object[] objects);
	
	Object selectForObject(String sql,Object[] objects,Class clazz);
	
	List<Object> selectForList(String sql,Object[] objects,Class clazz);
}
