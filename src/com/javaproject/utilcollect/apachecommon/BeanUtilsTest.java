package com.javaproject.utilcollect.apachecommon;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

/**
	copyPeoperties 复制属性值，从一个对象到另一个对象
	getProperty 获取对象属性值
	setProperty 设置对象属性值
	populate 根据Map给属性复制
	copyPeoperty 复制单个值，从一个对象到另一个对象。
	cloneBean 克隆
 * @author 26031
 *
 */
public class BeanUtilsTest {

//	cloneBean 克隆
	public static void BeanUtilsPeek() throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException{
		StrawBerry straw = new StrawBerry();
		straw.setName("aa1");
		straw.setWeight(10);
		Object result = BeanUtils.cloneBean(straw);
		System.out.println(result.toString());
	}
	
	public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
		BeanUtilsPeek();
		
	}
}
