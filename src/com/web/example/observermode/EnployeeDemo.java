package com.web.example.observermode;

/**
 * 观察者模式
 * @author 26031
 *
 */
public class EnployeeDemo{

	public static void main(String[] args) {
		
		Enployee enployee = new Enployee(); 
		enployee.setName("大小张");
		enployee.addObserverListener(new MyObserverListener());
		
		enployee.study();
		enployee.sleep();
	}
}

class MyObserverListener implements ObserverListener{

	@Override
	public void preStudy(Object object) {
		Enployee enployee = (Enployee)object;
		System.out.println("监管这位学生的学习："+enployee.getName());
	}

	@Override
	public void preSleep(Object object) {
		
	}
	
}