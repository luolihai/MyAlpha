package com.javaproject.utilcollect.apachecommon;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

/**
	deleteDirectory 删除文件夹
	deleteQueitly 删除文件或文件夹且不会抛出异常。
	readFileToString 以字符形式读取文件内容。
	copyFile 复制文件
	copyDirectory 复制文件夹
	writeStringToFile 把字符写到目标文件，如果文件不存在，则创建。
	write 把字符写到指定文件中
	forceMkdir 强制创建文件夹，如果该文件夹父级目录不存在，则创建父级。
	forceDelete 强制删除文件
	listFiles 列举某个目录下的文件(根据过滤器),flase表示不递归查
 * @author 26031
 *
 */
public class FileUtilsTest {
	
//	deleteDirectory 删除文件夹,只能删除文件夹
	public static void deleteDirectoryPeek() throws IOException{
		FileUtils.deleteDirectory(new File("d://test"));
	}
	
//	deleteQueitly 删除文件或文件夹且不会抛出异常。
	public static void deleteQueitlyPeek() throws IOException{
		FileUtils.deleteQuietly(new File("d://test//temporary11.txt"));
	}
	
//	readFileToString 以字符形式读取文件内容。
	public static void readFileToStringPeek() throws IOException{
		String toStr = FileUtils.readFileToString(new File("d://test//temporary11.txt"));
		System.out.println(toStr);
	}
	
//	copyFile 复制文件
	public static void copyFilePeek() throws IOException{
		FileUtils.copyFile(new File("d://test//temporary11.txt"), new File("d://test//temporary12.txt"));
	}
	
//	forceMkdir 强制创建文件夹，如果该文件夹父级目录不存在，则创建父级。
	public static void forceMkdirPeek() throws IOException{
		FileUtils.forceMkdir(new File("d://test//test2"));
	}
	
//	writeStringToFile 把字符写到目标文件，如果文件不存在，则创建。
	public static void writeStringToFilePeek() throws IOException{
		FileUtils.writeStringToFile(new File("d://test//temporary13.txt"),"这个是回写的方法translate word to word");
	}
	

//	write 把字符写到指定文件中
	public static void writePeek() throws IOException{
		FileUtils.write(new File("d://test//temporary14.txt"),"temporary14这个是回写的方法translate word to word ");
	}

//	listFiles 列举某个目录下的文件(根据过滤器),flase表示不递归查
	public static void listFilesPeek() throws IOException{
		Collection<File> list = FileUtils.listFiles(new File("d://test"),new String[]{"txt"},true);
		for (File file : list) {
			System.out.println(file.getName());
		}
	}
	
//	copyDirectory 复制文件夹
	public static void copyDirectoryPeek() throws IOException{
		FileUtils.copyDirectory(new File("d://test"), new File("d://test_copy"));
	}
	
//	forceDelete 强制删除文件
	public static void forceDeletePeek() throws IOException{
		FileUtils.forceDelete(new File("d://test_copy"));
		FileUtils.forceDelete(new File("d://test"));
	}
	public static void main(String[] args) throws IOException {
		forceDeletePeek();
	}
	
}
