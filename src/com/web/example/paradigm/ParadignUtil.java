package com.web.example.paradigm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

//全局泛型
public class ParadignUtil <T>{
	
	//返回泛型
	public <T> T findOne(T t){
		System.out.println(t);
		return t;
	}
	
	
	
	//泛型参数
	public <T> void  voidUse1(T t){
		System.out.println(t);
	}
	//泛型参数
	public <T> void  voidUse2(T t){
		System.out.println(t);
	}
	
	public static <T> void  voidUse3(T t){
		System.out.println(t);
	}
	
	//两个泛型
	public <K,V> Map<K, V> findMap(K k,V v){
		System.out.println(k+":"+v);
		return new HashMap<K, V>();
	}
	
	//泛型通配符
	public void pringLength1(Collection<?> c){
		System.out.println("通配符方式");
		System.out.println(c.size());
	}
	//上限
	public void pringLength2(Collection<? extends String> c){
		System.out.println("通配符上限方式");
		System.out.println(c.size());
	}
	//下限
	public void pringLength3(Collection<? super String> c){
		System.out.println("通配符下限方式");
		System.out.println(c.size());
	}
	//数组交换位置
	public static <T> T[] replacePara(T[] t,int oldIndex,int newIndex){
		T t1 = t[oldIndex];
		t[oldIndex] = t[newIndex];
		t[newIndex] = t1;
		return t;
	}
	
	//数组反转
	public static <T>  T[] reversalPara(T[] t){
		int startIndex = 0;
		int endIndex = t.length-1;
		
		if (startIndex < endIndex) {
			T tmp = t[startIndex];
			t[startIndex] = t[endIndex];
			t[endIndex] = tmp;
			startIndex++;
			endIndex--;
		}
		return t;
	}
	
	public static <T> void find(){
		System.out.println("你好呀");
	}
	
	
	
	
}
