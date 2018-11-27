package com.web.example.paradigm;

import java.util.Arrays;

import com.web.example.domain.UserBean;

public class ParadignUse {
	
	public static void main(String[] args) {
		String[] t = {"1","2","3","4","5"};
		ParadignUtil.replacePara(t, 1, 4);
		System.out.println(t);
		System.out.println(Arrays.asList(t));//转为数组
		
		String[] t1 = {"1","2","3","4","5"};
		ParadignUtil.reversalPara(t1);
		System.out.println(Arrays.asList(t1));
		
		ParadignUtil.find();
	}
	

}
