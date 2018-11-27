package com.javaproject.examplecollect.developmodel;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import org.junit.Test;

/**
 * 子类动态代理
 * @author 26031
 *
 */
public class CglibEnhancerProxy {
	
	@Test
	public void excuCglib(){
		final CglibEnhancerExam cglib = new CglibEnhancerExam();
		
		CglibEnhancerExam cee = (CglibEnhancerExam)Enhancer.create(cglib.getClass(), new MethodInterceptor(){

			@Override
			public Object intercept(Object object, Method method, Object[] args,
					MethodProxy methodProxy) throws Throwable {
				if (method.getName().equals("dance")) {
					int money = (int)args[0];
					return method.invoke(cglib, money/2);
				}else if (method.getName().equals("sing")) {
					int money = (int)args[0];
					return method.invoke(cglib, money/2);
				}else{
					return method.invoke(cglib, args);
				}
			}
		});
		
		cee.dance(2000);
		cee.sing(1000);
		cee.eat();
	}
}
