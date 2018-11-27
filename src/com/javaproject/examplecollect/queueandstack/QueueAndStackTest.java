package com.javaproject.examplecollect.queueandstack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.junit.Test;

public class QueueAndStackTest {

	@Test
	public void tt(){
		/**
		 * queue队列添加删除都是根据先进先出规则，没根据下标查询，而linkedlist就有
		 */
		Queue<String> queue = new LinkedList<>();
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.add("1");
		queue.add("2");
		queue.add("3");
		queue.offer("!");
		System.out.println(queue.offer("@"));
		queue.offer("#");
		System.out.println(queue);
		System.out.println(queue.peek());//查看第一个
		System.out.println(queue.remove("a"));
		System.out.println(queue.poll());//弹出第一个
		System.out.println(queue);
		
	}
	
	@Test
	public void tt2(){
		/**
		 * 先进后出，remove只能删除add的元素，而pop只能弹出push的元素
		 */
		Stack<String> stack = new Stack<>();
		stack.add("1");
		stack.add("2");
		stack.add("3");
		stack.push("a");
		stack.push("b");
		stack.push("c");
		stack.add("4");
		
		System.out.println(stack);
		
		System.out.println("firstElement:"+stack.firstElement());//最先放进的
		System.out.println("get(3):"+stack.get(3));
		System.out.println("peek:"+stack.peek());
		System.out.println("pop:"+stack.pop());
		System.out.println("remove:"+stack.remove("c"));
		System.out.println("size:"+stack);
		System.out.println(stack.size());
		for (int i = 0; i < stack.size(); i++) {
				System.out.println(stack.pop());
				System.out.println(i+"次");
		}
		
		
	}
	
	
	@Test
	public void test3(){
		
		LinkedList<String> list = new LinkedList<>();
		list.add("1");
		list.add("2");
		list.add("3");
		System.out.println(list.getFirst());//第一个放进
		System.out.println(list.removeFirst());//第一个放进
		System.out.println(list);
	}

}
