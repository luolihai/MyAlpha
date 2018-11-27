package com.javaproject.examplecollect.readeraddnumber;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
/**
 * 包装类，改造字符流的读
 * @author 26031
 *
 */
public class BufferReaderAddNumber extends BufferedReader{
	private int num = 1;
	public BufferReaderAddNumber(Reader in) {
		super(in);
	}

	@Override
	public String readLine() throws IOException {
		
		String line = super.readLine();
		if(line == null) 
			return null;
		return num+++line;
	}
	
	public static void main(String[] args) throws IOException {
		BufferReaderAddNumber bu = new BufferReaderAddNumber(new FileReader(new File("src/com/javaproject/examplecollect/readeraddnumber/BufferReaderAddNumberTest.java")));
		String s = null;
		while ((s = bu.readLine()) != null) {
			System.out.println(s);
		}
		bu.close();
	}
}

