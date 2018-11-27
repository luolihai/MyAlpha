package com.javaproject.examplecollect.threadlocalexample;

/**
 * User对象中的index属性是经过ThreadLocal类处理，由它的get()方法返回，每次生成个副本，
 * @author  llh
 * @version
 */
public class ThreadLocalExample implements Runnable{

	@Override
	public void run() {
		String tt = User.getIndex();
		System.out.println(Thread.currentThread()+":"+tt);
	}
	/**
	 * 结果类似
	 * Thread[Thread-1,5,main]:00
	 * Thread[Thread-2,5,main]:00
	 * Thread[Thread-0,5,main]:00
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 0; i < 3; i++) {
			new Thread(new ThreadLocalExample()).start();
		}
	}
	
	
}
