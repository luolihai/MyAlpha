package com.javaproject.utilcollect.xmlparse;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;


public class Dom4jUtils {

	/**
	 * 解析xml为document对象
	 * @param xmlPath xml文件路径，如src/studens.xml
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseXmlToDocument(String xmlPath) throws DocumentException{
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new File(xmlPath));
		return document;
	}
	
	/**
	 * 字符转document对象
	 * @param xmlText，如：<studens><studen><name>小华</name></studen></studens>
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseStringToDocument(String xmlText) throws DocumentException{
		Document document = DocumentHelper.parseText(xmlText);
		return document;
	}
	
	/**
	 * document转字符
	 * 如：
	 *  <?xml version="1.0" encoding="UTF-8"?>
	 *  <studens><studen><name>小华</name></studen></studens>
	 * @param document
	 * @return
	 * @throws DocumentException
	 */
	public static String getStringByDocument(Document document) throws DocumentException{
		return document.asXML(); 
	}
	
	
	/**
	 * 从document中提取指定位置下的节点文本，如果多个默认采用第一个
	 * @param document 
	 * @param xpath 节点路径
	 * @return
	 * @throws DocumentException
	 */
	public static String  getElementValueByXPath(Document document,String xpath) throws DocumentException{
		String value = "";
		List<Node> nodes = document.selectNodes(xpath);
		
		if (nodes != null && nodes.size() > 0) {
			Node node = nodes.get(0);
			value = node.getText();
		}
		return value;
	}
	
	/**
	 * 从document中提取指定位置下的节点
	 * @param document 
	 * @param xpath 节点路径
	 * @return
	 * @throws DocumentException
	 */
	public static Node  getNodeByXPath(Document document,String xpath) throws DocumentException{
		List<Node> nodes = document.selectNodes(xpath);
		if (nodes != null && nodes.size() > 0) {
			return  nodes.get(0);
		}
		return null;
	}
	
	/**
	 * 写入document到指定xmlPath
	 * @param document
	 * @param xmlPath xml路径,如src/stu.xml
	 * @throws IOException
	 */
	public static void writeDocument(Document document,String xmlPath) throws IOException{
		OutputFormat prettyFormat = OutputFormat.createPrettyPrint();
		XMLWriter xmlWriter = new XMLWriter(new FileWriter(new File(xmlPath)), prettyFormat);
		xmlWriter.write(document);
		xmlWriter.close();
	}
	
	
	/**
	 * 创建document例子
	 * @return
	 */
	public static Document createDocumentTest(){
		Document document = DocumentHelper.createDocument();
		Element studensEle = document.addElement("studens");
		
		Element studenEle1 = studensEle.addElement("studen");
		Element nameEle = studenEle1.addElement("name");
		nameEle.addText("小华");	
		Element ageEle = studenEle1.addElement("age");
		ageEle.addText("26");
		Element weightEle = studenEle1.addElement("weight");
		weightEle.addText("12");

		return document;
	}
	
	
	@Test
	public void test() throws DocumentException, IOException{
		
		String string = "<studens><studen><name>小华</name><age>26</age><weight>12</weight></studen></studens>";
		Document document = parseStringToDocument(string);
		System.out.println(getNodeByXPath(document, "//studen/age").getText());
//		System.out.println(element.getText());
	}
}
