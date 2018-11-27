package com.web.example.observermode;

public class Enployee {

	
	private String name;
	private String age;
	
	private ObserverListener listener;
	
	//注册观察者
	public void addObserverListener(ObserverListener listener){
		this.listener = listener;
	}
	
	public void study(){
		if (listener != null) {
			listener.preStudy(this);
		}
		System.out.println(name+"学习");
	}
	
	public void sleep(){
		System.out.println(name+"睡觉");
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
}
