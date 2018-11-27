package com.web.example.annotate;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnoTest {

	public static void main(String[] args) {
		Class clazz = AnnoRun1Use.class;
		/*
		 * clazz.isAnnotation();//是否是注解
		 * clazz.getAnnotation(AnnoRun1Use.class);//获取注解
		 * clazz.getAnnotations();//获取全部注解
		 * clazz.getDeclaredAnnotations();//声明的注解
		 */
		
		/*
		//执行AnnoRun1Use类中有注解的方法
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getAnnotations().length > 0) {
				System.out.println(methods[i].getName());
				try {
					methods[i].invoke(clazz.newInstance(), null);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		*/
		
		
		//time,性能测试
		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getAnnotations().length >0) {
				AnnoRun1 annotation = methods[i].getAnnotation(AnnoRun1.class);
				int time = annotation.time();
				if (time > 0) {
					long startTime = System.nanoTime();
					try {
						methods[i].invoke(clazz.newInstance(), null);
					} catch (Exception e) {
						e.printStackTrace();
					}
					if (System.nanoTime()-startTime > time) {
						System.out.println(methods[i].getName()+"运行超过预算时间");
					}
				}else{
					try {
						methods[i].invoke(clazz.newInstance(), null);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
