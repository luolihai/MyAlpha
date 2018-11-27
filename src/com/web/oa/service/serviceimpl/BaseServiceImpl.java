package com.web.oa.service.serviceimpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import org.junit.Test;

import com.web.oa.bean.User;
import com.web.oa.dao.BaseDao;
import com.web.oa.dao.daoimpl.BaseDaoImpl;
import com.web.oa.service.BaseService;

public class BaseServiceImpl implements BaseService{

	BaseDao baseDao = new BaseDaoImpl();
	@Override
	public int add() {
		
		BaseDao newBase = (BaseDao)Proxy.newProxyInstance(baseDao.getClass().getClassLoader(), baseDao.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						long beforeTime = System.nanoTime();
						Object obj = method.invoke(baseDao, args);
						long lastTime = System.nanoTime();
						System.out.println("运行时间："+(lastTime-beforeTime)+"纳秒");
						return obj;
					}
				});
		newBase.add();
		return 0;
	}
	@Override
	public int excuteSql(String sql, Object[] objects) {
		
		return baseDao.excuteSql(sql, objects);
	}
	@Override
	public Object selectForObject(String sql, Object[] objects, Class clazz) {
		
		return baseDao.selectForObject(sql, objects, clazz);
	}
	@Override
	public List<Object> selectForList(String sql, Object[] objects, Class clazz) {
		
		return baseDao.selectForList(sql, objects, clazz);
	}
	
	@Test
	public void test(){
		BaseServiceImpl bs = new BaseServiceImpl();
		
//		bs.excuteSql("insert into user(id,username,password) values(4,'mesi','123')", null);
//		bs.excuteSql("update user set roleid=1", null);
		User user = (User)bs.selectForObject("select * from user where id = ?", new Object[]{4}, User.class);
		System.out.println(user.getUsername());
		/*
		List<User> list = (List)bs.selectForList("select * from user ",null, User.class);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getUsername());
			
			
		}*/
	}

}
