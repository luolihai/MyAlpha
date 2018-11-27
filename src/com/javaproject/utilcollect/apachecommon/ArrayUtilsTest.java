package com.javaproject.utilcollect.apachecommon;

import org.apache.commons.lang3.ArrayUtils;

/**
	contains 是否包含某字符串
	addAll 添加所有
	clone 克隆一个数组
	isEmpty 是否空数组
	add 向数组添加元素
	subarray 截取数组
	indexOf 查找下标
	isEquals 比较数组是否相等
	toObject 基础类型数据数组转换为对应的Object数组
 * @author 26031
 *
 */
public class ArrayUtilsTest {

//	contains 是否包含某字符串
	public static void containsPeek(){
		String[] strs = new String[]{"111","aaa","ccc"};
		boolean result = ArrayUtils.contains(strs, "111");
		System.out.println(result);
	}

//	addAll 添加所有,返回新数组
	public static void addAllPeek(){
		String[] strs = new String[]{"111","aaa","ccc"};
		String[] arrs = new String[]{"222","aaa","bbb"};
		String[] result = ArrayUtils.addAll(strs, arrs);
		System.out.println(ArrayUtils.toString(result));
	}

//	clone 克隆一个数组
	public static void clonePeek(){
		String[] strs = new String[]{"111","aaa","ccc"};
		String[] clone = ArrayUtils.clone(strs);
		clone[0] = "222";
		System.out.println(ArrayUtils.toString(strs));
	}
	
//	isEmpty 是否空数组,null或没值
	public static void isEmptyPeek(){
		String[] strs = null;
		String[] arr = new String[]{};
		System.out.println(ArrayUtils.isEmpty(strs));
		System.out.println(ArrayUtils.isEmpty(arr));
	}
	
//	add 向数组添加元素,返回新元素
	public static void addPeek(){
		String[] strs = new String[]{"111","aaa","ccc","aaa4","ccc5"};
		String[] result = ArrayUtils.add(strs, "bbb");
		System.out.println(ArrayUtils.toString(result));
	}
	
//	subarray 截取数组
	public static void subarrayPeek(){
		String[] strs = new String[]{"111","aaa","ccc","aaa4","ccc5"};
		String[] result = ArrayUtils.subarray(strs, 2, 3);
		System.out.println(ArrayUtils.toString(result));
	}
	
//	indexOf 查找下标
	public static void indexOfPeek(){
		String[] strs = new String[]{"111","aaa","ccc","aaa4","ccc5"};
		int result = ArrayUtils.indexOf(strs, "aaa");
		System.out.println(result);
	}
	
//	isEquals 比较数组是否相等
	public static void isEqualsPeek(){
		String[] strs = new String[]{"111","aaa","ccc","aaa4","ccc5"};
		String[] array = new String[]{"111","aaa","ccc","aaa4","ccc5"};
		String[] array1 = new String[]{"111","aaa","ccc","aaa4","ccc1"};
		System.out.println(ArrayUtils.isEquals(strs, array));
		System.out.println(ArrayUtils.isEquals(strs, array1));
	}
	
//	toObject 基础类型数据数组转换为对应的Object数组,只能转为基础类型同类
	public static void toObjectPeek(){
		int[] ints = {1,2,3};
		Integer[] intes = ArrayUtils.toObject(ints);
		String[] strs = ArrayUtils.toStringArray(intes);
		System.out.println(ArrayUtils.toString(strs));
	}
	
	public static void main(String[] args) {
		toObjectPeek();
	}
	
	
}
