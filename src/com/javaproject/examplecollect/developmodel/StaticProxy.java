package com.javaproject.examplecollect.developmodel;

/**
 * 静态代理类
 * 把何代理的接口作变量放进来
 * 
 * @author 26031
 *
 */
public class StaticProxy implements StaticProxyInterface{
	private StaticProxyInterface proInterface;
	public StaticProxy(StaticProxyInterface proInterface){
		this.proInterface = proInterface;
	}

	@Override
	public void dance(int money) {
		System.out.println("抽点水 ^_^");
		proInterface.dance(money/2);
	}

	@Override
	public void sing(int money) {
		System.out.println();
		System.out.println("收点福利 ^_^");
		proInterface.sing(money/2);
	}

	@Override
	public void eat() {
		proInterface.eat();
	}

}
