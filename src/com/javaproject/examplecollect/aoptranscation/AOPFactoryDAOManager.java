package com.javaproject.examplecollect.aoptranscation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.javaproject.examplecollect.ThreadLocalManager;

public class AOPFactoryDAOManager {
	
	public static BaseDao getBaseDao(){
		
		final BaseDao baseDao = new BaseDaoImpl();
		BaseDao newBaseDao = (BaseDao)Proxy.newProxyInstance(baseDao.getClass().getClassLoader(), baseDao.getClass().getInterfaces(), new InvocationHandler() {
			
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable {
				Object obj = null;
				try {
					ThreadLocalManager.getAutoCommitConnection();
					obj = method.invoke(baseDao, args);
					ThreadLocalManager.commitConnect();
				} catch (Exception e) {
					ThreadLocalManager.rollbackConnect();
				}
				return obj;
			}
		});
		return newBaseDao;
	}
	
	

}
