package com.javaproject.utilcollect.proputils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Test;

public class PropertiesUtil {

	/**
	 * 读取指定Properties文件
	 * @param path 文件路径，如：src/stu.properties
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Properties getPropertiestByPath(String path) throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		FileInputStream in = new FileInputStream(new File(path));
		prop.load(in);
		in.close();
		return prop;
	}
	
	/**
	 * 读取文件所有参数进map
	 * @param path prop文件路径
	 * @return 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, String> getAllProperties(String path) throws FileNotFoundException, IOException{
		Properties prop = getPropertiestByPath(path);
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<Object> keys = prop.keys();
		for (;keys.hasMoreElements();) {
			String ele = (String)keys.nextElement();
			map.put(ele, prop.getProperty(ele));
		}
		return map;
	}
	
	/**
	 * 读取文件所有参数进map
	 * @param prop Properties对象
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static Map<String, String> getAllProperties(Properties prop) throws FileNotFoundException, IOException{
		Map<String, String> map = new HashMap<String, String>();
		Enumeration<Object> keys = prop.keys();
		for (;keys.hasMoreElements();) {
			String ele = (String)keys.nextElement();
			map.put(ele, prop.getProperty(ele));
		}
		return map;
	}
	
	/**
	 * 根据key获取指定文件参数
	 * @param path 文件路径
	 * @param key 键
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static String getPropertiesByKey(String path,String key) throws FileNotFoundException, IOException{
		Properties prop = getPropertiestByPath(path);
		return prop.getProperty(key);
	}
		
	
	
	/**
	 * 写入Properties到指定文件
	 * @param prop 
	 * @param path 保存的路径文件，如：src/stu.properties
	 * @throws IOException
	 */
	public static void writePropertiesToFilepath(Properties prop,String path) throws IOException{
		FileOutputStream out = new FileOutputStream(new File(path));
		prop.store(out, "");
		out.close();
	}
	
	@Test
	public void test() throws FileNotFoundException, IOException{
		/*
		Properties prop = PropertiesUtil.getPropertiestByPath("src/stu.properties");
		prop.setProperty("name", "lishi");
		PropertiesUtil.writePropertiesToFilepath(prop, "src/stu.properties");
		*/
		
	}
	
}
