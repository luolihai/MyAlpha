package com.web.oa.dao.daoimpl;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.web.oa.dao.ResultSetHandler;

public class BeanHandler implements ResultSetHandler {
	private Class clazz;
	public BeanHandler(Class clazz){
		this.clazz = clazz;
	}
	@Override
	public Object objectHandler(ResultSet rs) {
		
		try {
			if (rs.next()) {
				Object obj = clazz.newInstance();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String key = metaData.getColumnName(i+1);
					Object value = rs.getObject(key);
					if (value != null) {
						Field field = clazz.getDeclaredField(key);
						field.setAccessible(true);
						field.set(obj, value);
					}
				}
				return obj;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	@Override
	public List<Object> ListHandler(ResultSet rs) {
		List<Object> list = null;
		boolean hasResult = true;
		try {
			while (rs.next()) {
				if (hasResult) {
					list = new ArrayList<Object>();
					hasResult = false;
				}
				
				Object obj = clazz.newInstance();
				ResultSetMetaData metaData = rs.getMetaData();
				int columnCount = metaData.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					String key = metaData.getColumnName(i+1);
					Object value = rs.getObject(key);
					if (value != null) {
						Field field = clazz.getDeclaredField(key);
						field.setAccessible(true);
						field.set(obj, value);
					}
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		return list;
	}

}
