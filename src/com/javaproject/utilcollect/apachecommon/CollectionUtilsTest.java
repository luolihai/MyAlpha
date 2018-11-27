package com.javaproject.utilcollect.apachecommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.Transformer;

/**
	isEmpty 是否为空
	select 根据条件筛选集合元素
	find 基本和select一样
	filter 过滤元素，雷瑟List的filter(),与select不同的是不生成新list
	transform 根据指定方法处理集合元素，类似List的map()。提取处理子元素
	collect 和transform 差不多一样，但是返回新数组
	isEqualCollection 判断两个集合是否一致
 * @author 26031
 *
 */
public class CollectionUtilsTest {
	
//	isEmpty 是否为空
	public static void isEmptyPeek(){
		List<String> list = new ArrayList<>();
		System.out.println(CollectionUtils.isEmpty(list));
		System.out.println(CollectionUtils.isEmpty(null));
	}
	
//	select 根据条件筛选集合元素，实现Predicate接口指定规则
	public static void selectPeek(){
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		Collection<String> result = CollectionUtils.select(list, new Predicate<String>() {
			@Override
			public boolean evaluate(String str) {
				return str.equals("aaa");
			}
		});
		System.out.println(result.toString());
	}
	
//	transform 根据指定方法处理集合元素，类似List的map(),提取处理子元素
	public static void transformPeek(){
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		CollectionUtils.transform(list, new Transformer<String, String>() {

			@Override
			public String transform(String str) {
				return str+"1";
			}
		});
		System.out.println(list.toString());
	}

//	filter 过滤元素，类似List的filter()
	public static void filterPeek(){
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		CollectionUtils.filter(list, new Predicate<String>() {
			@Override
			public boolean evaluate(String str) {
				return str.equals("aaa");
			}
		});
		System.out.println(list);
	}
	
	
//	isEqualCollection 判断两个集合是否一致
	public static void isEqualCollectionPeek(){
		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb1");
		
		List<String> list1 = new ArrayList<>();
		list1.add("aaa");
		list1.add("bbb");
		System.out.println(CollectionUtils.isEqualCollection(list, list1));
	}
	
	public static void main(String[] args) {
		isEqualCollectionPeek();
	}
}
