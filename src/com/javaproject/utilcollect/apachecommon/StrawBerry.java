package com.javaproject.utilcollect.apachecommon;

public class StrawBerry {

	/**
	 * 这是名称
	 */
	private String name;
	private int weight;
	/**
	 * 这是甜度
	 */
	public String sweet;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	@Override
	public String toString() {
		return this.name+":"+this.weight+":"+this.sweet;
	}
	public String getSweet() {
		return sweet;
	}
	
}
