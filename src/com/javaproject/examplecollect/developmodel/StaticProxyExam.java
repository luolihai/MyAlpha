package com.javaproject.examplecollect.developmodel;

public class StaticProxyExam implements StaticProxyInterface{

	@Override
	public void dance(int monney) {
		System.out.println("跳舞"+monney+"块钱");
	}

	@Override
	public void sing(int monney) {
		System.out.println("唱歌"+monney+"块钱");
	}

	@Override
	public void eat() {
		System.out.println();
		System.out.println("吃饭不用钱");
	}
	
}
