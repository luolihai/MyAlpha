package com.web.oa.test;

import org.junit.Test;

import com.web.oa.service.BaseService;
import com.web.oa.service.serviceimpl.BaseServiceImpl;

public class JdbcTest {
	
	/**
	 * aop原理计算调用运行时间，接口
	 */
	@Test
	public void baseTest(){
		BaseService baseService = new BaseServiceImpl();
		baseService.add();
	}

}
