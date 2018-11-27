package com.web.example.daomode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Session {

	public static <T> void add(T t){
		System.out.println("添加:"+t);
	}
	
	public static <T> void update(T t){
		System.out.println("更新:"+t);
	}
	
	public static <T> void delete(T t){
		System.out.println("删除:"+t);
	}
	
	public static Object  findOne(Class clazz,Serializable id){
		Object newInstance = null;
		try {
			newInstance = clazz.newInstance();
			System.out.println("根据id查询："+id+"--"+newInstance);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return newInstance;
	}
	
	public static List findAll(Class clazz){
		List list = new ArrayList();
		
		Object newInstance = null;
		try {
			newInstance = clazz.newInstance();
			list.add(newInstance);
			System.out.println("查询全部："+clazz+"--"+newInstance);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
