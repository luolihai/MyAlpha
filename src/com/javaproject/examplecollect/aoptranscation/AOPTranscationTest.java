package com.javaproject.examplecollect.aoptranscation;

import java.sql.SQLException;

import org.junit.Test;

import com.javaproject.examplecollect.beanexample.IDCard;
import com.javaproject.examplecollect.beanexample.Persion;
/**
 * aop模拟测试
 * @author 26031
 *
 */
public class AOPTranscationTest {
	
	@Test
	public void testAdd() throws SQLException{
		int money = 100;
		String sql1 = "update user set roleid=roleid-? where id=?";
		String sql2 = "update user set roleid=roleid+? where id=?";
		//模拟转账
		AOPFactoryDAOManager.getBaseDao().add(sql1, new Object[]{money,5}, sql2, new Object[]{money,4});
	}
	
	@Test
	public void testOneToOne() throws SQLException{
		Persion persion = new Persion();
		persion.setId(3);
		persion.setName("c");
		
		IDCard idCard = new IDCard();
		idCard.setNum("c333");
		
		persion.setIdCard(idCard);
		
		String sql1 = "insert into persion values(?,?)";
		//自己写的模拟aoc事务连接池
		AOPFactoryDAOManager.getBaseDao().add(sql1, new Object[]{persion.getId(),persion.getName()});
		if ( persion.getIdCard() != null) {
			String sql2 = "insert into idcard values(?,?)";
			AOPFactoryDAOManager.getBaseDao().add(sql2, new Object[]{persion.getId(),persion.getIdCard().getNum()});
		}
	}

}
