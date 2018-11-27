package com.javaproject.examplecollect.developmodel;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;
/**
 * 
 * @author 26031
 *
 */
public class ExcuteExample {
	
	/**
	 * 动态代理
	 */
	@Test
	public void excuStaticExam(){
		
		final StaticProxyExam exam = new StaticProxyExam();
		
		StaticProxyInterface obj = (StaticProxyInterface)Proxy.newProxyInstance(exam.getClass().getClassLoader(), exam.getClass().getInterfaces(), 
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						if (method.getName().equals("dance")) {
							int money = (int)args[0];
							return method.invoke(exam, money/2);
						}else if (method.getName().equals("sing")) {
							int money = (int)args[0];
							return method.invoke(exam, money/2);
						}else{
							return method.invoke(exam, args);
						}
					}
				});
		
		obj.dance(2000);
		obj.sing(1000);
		obj.eat();
	}


	@Test
	public void testJunit() {
		System.out.println("hello junit");
	}
}
