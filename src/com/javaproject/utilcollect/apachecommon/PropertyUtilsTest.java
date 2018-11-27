package com.javaproject.utilcollect.apachecommon;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.PropertyUtils;

/**
	getProperty 获取对象属性值,属性要有get方法
	setProperty 设置对象属性值，属性要有set方法
	getPropertyDiscriptor 获取属性描述器
	getPropertyDiscriptors 获取所有属性描述器
	isReadable 检查属性是否可访问,判断是否有get方法，当get，set都没会报错
	isWriteable 检查属性是否可写
	copyProperties 复制属性值，从一个对象到另一个对象
	getPropertyType 获取对象属性类型
 * @author 26031
 *
 */
public class PropertyUtilsTest {

	
//	getProperty 获取对象属性值
	public static void getPropertyPeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		Object result = PropertyUtils.getProperty(straw,"name");
		System.out.println(result.toString());
	}
	
//	setProperty 设置对象属性值
	public static void setPropertyPeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		PropertyUtils.setProperty(straw, "name", "bbb");
//		PropertyUtils.setProperty(straw, "sweet", "1");
		System.out.println(straw.toString());
	}
	
//	getPropertyDiscriptor 获取属性描述器
//	getPropertyDiscriptors 获取所有属性描述器
	public static void getPropertyDiscriptorPeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(straw, "name");
		Method writeMethod = pd.getWriteMethod();//从属性描述器中获取 set 方法
		//Method getMethod = pd.getReadMethod();//从属性描述器中获取 get 方法
		System.out.println(writeMethod.getName());
	}
	
//	isReadable 检查属性是否可访问 根据是否有get方法
//	isWriteable 检查属性是否可写 根据是否有set方法
	public static void isReadablePeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		System.out.println(PropertyUtils.isReadable(straw, "name"));
		System.out.println(PropertyUtils.isReadable(straw, "sweet"));
		System.out.println(PropertyUtils.isWriteable(straw, "name"));
		System.out.println(PropertyUtils.isWriteable(straw, "sweet"));
	}
	
//	copyProperties 复制属性值，从一个对象到另一个对象
	public static void copyPropertiesPeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		
		StrawBerry straw1 = new StrawBerry();
		PropertyUtils.copyProperties(straw1, straw);
		System.out.println(straw1.toString());
	}
	
//	getPropertyType 获取对象属性类型
	public static void getPropertyTypePeek() throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		Class<?> res = PropertyUtils.getPropertyType(straw, "name");
		System.out.println(res.getName());
	}
	
	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		getPropertyTypePeek();
	}
	
}
