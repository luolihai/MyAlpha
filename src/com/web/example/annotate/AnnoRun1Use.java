package com.web.example.annotate;

public class AnnoRun1Use {

	@AnnoRun1(time=10)
	public void test1(){
		System.out.println("模拟单元测试1");
	}
	
	@AnnoRun1(time=1000000)
	public void test2(){
		System.out.println("方法2");
	}
}
