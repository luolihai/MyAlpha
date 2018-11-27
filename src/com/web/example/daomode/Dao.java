package com.web.example.daomode;

import java.io.Serializable;
import java.util.List;

public interface Dao<T> {

	void add(T t);
	
	void update(T t);
	
	void delete(Serializable id);
	
	T findOne(Serializable id );
	
	List<T> findAll();
}
