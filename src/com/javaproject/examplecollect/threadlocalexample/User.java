package com.javaproject.examplecollect.threadlocalexample;



public class User {
	
	private static String index = "0";
	
	
	private static ThreadLocal<String> localIndex = new ThreadLocal<String>();
	
	public static String getIndex(){
		index = index+index;
		localIndex.set(index);
		return localIndex.get();
	}
	
	
}
