package com.web.example.domain;

import java.math.BigDecimal;

public class Calculate {

	private double number1;
	private double number2;
	private char operater;
	private double result;
	
	public Calculate() {
	}
	
	public Calculate(double number1, double number2, char operater,
			double result) {
		super();
		this.number1 = number1;
		this.number2 = number2;
		this.operater = operater;
		this.result = result;
	}
	
	
	public double getNumber1() {
		return number1;
	}
	public void setNumber1(double number1) {
		this.number1 = number1;
	}
	public double getNumber2() {
		return number2;
	}
	public void setNumber2(double number2) {
		this.number2 = number2;
	}
	public char getOperater() {
		return operater;
	}
	public void setOperater(char operater) {
		this.operater = operater;
	}
	public double getresult() {
		return result;
	}
	public void setresult(double result) {
		this.result = result;
	}
	
	public void calculateNumber(){
		
		
		switch (this.operater) {
		case '+':
			this.result = this.number1+this.number2;
			break;
		case '-':
			this.result = this.number1 - this.number2;
			break;
		case '*':
			this.result = this.number1 * this.number2;
			break;
		case '/':
			if (this.number1 == 0 || this.number2 == 0) {
				throw new RuntimeException("输入数字为零");
			}
			this.result = this.number1 / this.number2;
			break;
		default:
			this.result = 0;
			break;
		}
		
		
		BigDecimal bigDecimal = new BigDecimal(this.result);
		bigDecimal = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);
		this.result = bigDecimal.doubleValue();
		
	}
	
}
