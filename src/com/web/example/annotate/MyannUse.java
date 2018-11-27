package com.web.example.annotate;

public class MyannUse {
	
	//注解内含注解法
	@Myann(value = "",age = 1,name = "",myann = @Myann2(strs = { "" }))
	public void getName(){
		
	}

}
