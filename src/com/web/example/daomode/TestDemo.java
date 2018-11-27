package com.web.example.daomode;

import org.junit.Test;

import com.web.example.domain.UserBean;

public class TestDemo {
	@Test
	public void test(){
		UserBeanDao beanDao = new UserBeanDaoImpl();
		UserBean userBean = new UserBean();
		userBean.setId(1);
		userBean.setName("as");
		userBean.setValue("12");
		
		beanDao.add(userBean);
		beanDao.update(userBean);
		beanDao.delete(userBean.getId());
		beanDao.findOne(userBean.getId());
		beanDao.findAll();
	}
}
