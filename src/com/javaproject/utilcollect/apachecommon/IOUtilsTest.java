package com.javaproject.utilcollect.apachecommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;
/**
	input流转字符时一般会有乱码，注意编码转换时默认编码问题
	
	closeQuietly 关闭一个IO流、socket、或者selector且不抛出异常。通常放在finally块。
	toString 转换IO流、 Uri、 byte[]为String。
	copy IO流数据复制，从输入流写到输出流中，最大支持2GB。
	toByteArray 从输入流、URI获取byte[]。
	write 把字节. 字符等写入输出流。
	toInputStream 把字符转换为输入流。
	readLines 从输入流中读取多行数据，返回List<String>
	copyLarge 同copy，支持2GB以上数据的复制。
	lineIterator 从输入流返回一个迭代器，
	根据参数要求读取的数据量，全部读取，如果数据不够，则失败。
 * @author 26031
 *
 */
public class IOUtilsTest {
	
//	closeQuietly 关闭一个IO流、socket、或者selector且不抛出异常。通常放在finally块。
	public static void closeQuietlyPeek() throws IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		byte[] b = new byte[1024];
		int ind = 0;
		StringBuffer sb = new StringBuffer();
		while((ind = input.read(b)) > -1 ){
			sb.append(new String(b, 0, ind,"gbk"));
		}
		IOUtils.closeQuietly(input);
		//input.read();	//不可再读
	}
	
//	toString 转换IO流、 Uri、 byte[]为String。
	public static void toStringPeek() throws UnsupportedEncodingException, IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		String str = IOUtils.toString(input,"gbk");
		IOUtils.closeQuietly(input);
		System.out.println(str);
	}
	
//	copy IO流数据复制，从输入流写到输出流中，最大支持2GB。
//	copyLarge 同copy，支持2GB以上数据的复制。
	public static void copyPeek() throws IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		FileOutputStream output = new FileOutputStream(new File("d:\\temporary11.txt"));
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(output);
	}
	
//	toByteArray 从输入流、URI获取byte[]。
	public static void toByteArrayPeek() throws IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		byte[] bs = IOUtils.toByteArray(input);
		IOUtils.closeQuietly(input);
		System.out.println(new String(bs, "gbk"));
	}
	
//	write 把字节. 字符等写入输出流。
	public static void writePeek() throws IOException{
		String b = "会中文吗翻译一本字典";
		FileOutputStream output = new FileOutputStream(new File("d:\\temporary11.txt"));
		IOUtils.write(b, output);
		IOUtils.closeQuietly(output);
	}
	
//	toInputStream 把字符转换为输入流。
	public static void toInputStreamPeek() throws IOException{
		InputStream input = IOUtils.toInputStream("刘刘刘小刘");
		FileOutputStream output = new FileOutputStream(new File("d:\\temporary11.txt"));
		IOUtils.copy(input, output);
		IOUtils.closeQuietly(input);
		IOUtils.closeQuietly(output);
		
	}
	
//	readLines 从输入流中读取多行数据，返回List<String>
	public static void readLinesPeek() throws IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		List<String> strList = IOUtils.readLines(input, "gbk");
		IOUtils.closeQuietly(input);
		for (int i = 0; i < strList.size(); i++) {
			System.out.println("第"+i+"行："+strList.get(i));
		}
	}
	
//	lineIterator 从输入流返回一个迭代器，
	public static void lineIteratorPeek() throws IOException{
		FileInputStream input = new FileInputStream(new File("d:\\temporary.txt"));
		LineIterator lineIterator = IOUtils.lineIterator(input, "gbk");
		while (lineIterator.hasNext()) {
			System.out.println(lineIterator.next());
		}
		IOUtils.closeQuietly(input);
	}
	
	public static void main(String[] args) throws IOException {
//		lineIteratorPeek();
	}

}
