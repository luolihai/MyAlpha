package com.javaproject.examplecollect.logparse;

/**
 * 统计分析日志
 * @author 26031
 *
 */
public class LoggerBean {

	private String ip;
	private String timeStr;
	private String logStr;
	private int count;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTimeStr() {
		return timeStr;
	}
	public void setTimeStr(String timeStr) {
		this.timeStr = timeStr;
	}
	public String getLogStr() {
		return logStr;
	}
	public void setLogStr(String logStr) {
		this.logStr = logStr;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}
