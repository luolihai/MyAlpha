package com.javaproject.examplecollect.logparse;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 解析access.log日志
 * @author 26031
 *
 */
public class LoggerParse {
	
	public static void main(String[] args) throws IOException {
		
		//读取文件
		BufferedReader br = new BufferedReader(new FileReader(new File("d:/access_log.log")));
		
		Map<String, LoggerBean> map = new HashMap<String, LoggerBean>();
		List<LoggerBean> exceptionList = new ArrayList<>();
		
		String str = "";
		while((str = br.readLine()) != null){
			String ip = str.substring(0,str.indexOf("- -")).trim();
			String time = str.substring(str.indexOf("[")+1,str.indexOf("]"));
			
			//判断是否ip
			Pattern ipPat = Pattern.compile("[\\d]{1,3}.[\\d]{1,3}.[\\d]{1,3}.[\\d]{1,3}");
			Matcher ipMat = ipPat.matcher(ip);
			if (ipMat.matches()) {	//是否ip格式
				//判断是否11点半到6点前范围
				Pattern timePat = Pattern.compile("[\\S]{1,}:(11:[3-5][0-9]|0[0-5]:[0-9]{2}):[\\d]{2}[\\s\\S]{1,}");
				Matcher timeMat = timePat.matcher(time);
				if (timeMat.matches()) {	//是否指定范围
					
					LoggerBean bean = new LoggerBean();
					bean.setIp(ip);
					bean.setTimeStr(time);
					bean.setLogStr(str);
					LoggerBean loggerBean = map.get(ip);
					if (loggerBean == null) {
						bean.setCount(1);
						map.put(ip, bean);
					}else{
						loggerBean.setCount(loggerBean.getCount()+1);
						if (loggerBean.getCount() >= 50) {
							exceptionList.add(loggerBean);
						}
					}
				}
			}
		}
		br.close();
		
		for (int i = 0; i < exceptionList.size(); i++) {
			System.out.println(exceptionList.get(i).getIp()+" 11点半至6点前访问次数是："
				+exceptionList.get(i).getCount());
		}
		System.out.println("总数："+exceptionList.size());
	}
	

}
