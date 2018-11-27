package com.web.example.daomode;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class BaseDaoImpl<T> implements Dao<T>{
	
	private Class clazz;
	public BaseDaoImpl(){
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType)type;
		clazz = (Class)ptype.getActualTypeArguments()[0];
		System.out.println(clazz);
	}

	@Override
	public void add(T t) {
		Session.add(t);
	}

	@Override
	public void update(T t) {
		Session.update(t);
	}

	@Override
	public void delete(Serializable id) {
		T t = findOne(id);
		Session.delete(t);
	}

	@Override
	public T findOne(Serializable id) {
		Session.findOne(clazz, id);
		return null;
	}

	@Override
	public List<T> findAll() {
		Session.findAll(clazz);
		return null;
	}
	
}