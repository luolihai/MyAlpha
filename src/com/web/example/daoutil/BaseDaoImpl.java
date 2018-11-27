package com.web.example.daoutil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BaseDaoImpl{
	
	/**
	 * 通用更新对象
	 * 注意：对象变量名与类型需与数据表对应
	 * @param object 实例对象
	 * @param tableName 表名 
	 * @param keyName where条件名
	 * @return
	 */
	public int updateObject(Object object,String tableName,String keyName){
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			List<Map<String, Object>> list = getParaMapFromClass(object);
			String sql = getParaMapUpdateSql(list, tableName,keyName);
			stmt = conn.prepareStatement(sql);
			stmt = setAllParaMapToPreStmt(stmt,list,keyName);
			int index = stmt.executeUpdate();
			System.out.println("结果："+index);
			return index;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
		return 0;
	}
	
	/**
	 * 根据Class类，表示，条件集合，排序集合及分页信息，生成sql查询数据，返回list结果集
	 * 注意：该方法只支持类属性与数据表字段一 一对应 的类 
	 * 
	 * @param clazz	Class类
	 * @param tableName 数据表
	 * @param whereList where条件集合，其中map的fie_name表示字段，fie_operation表示操作符如and,or
	 * @param orderList orderList 集合，其中map的fie_name表示字段名，fie_sortType表示排序类型，asc或desc
	 * @param limitList limitList 数组，第一个值表示分页开始数，第二个表示多少个
	 * @return
	 */
	public List<? extends Object> selectToList(Class clazz,String tableName,List<Map<String, Object>> whereList,
			List<Map<String, Object>> orderList, int[] limitList) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try{
			conn = JdbcUtil.getConnection();
			
		List<Map<String, Object>> fieldList = getClassFields(clazz);//变量集
		//拼接sql
		String headSql = getHeadSelectSql(fieldList);
		String bottomSql = " from "+ tableName+" ";
		String whereSql = getWhereSql(whereList);
		String orderSql = getOrderSql(orderList);
		String limitSql = getLimitSql(limitList);
		String sql = headSql+bottomSql+whereSql+orderSql+limitSql;
		System.out.println(sql);
		
		//set值
		 stmt = conn.prepareStatement(sql);
		 if (whereList != null) {
			 for (int j = 0; j < whereList.size(); j++) {
				 stmt.setObject(j+1, whereList.get(j).get("fie_value"));
			 }
		}
		 
		 //遍历结果
		 rs = stmt.executeQuery();
		 List<Object> listResultObject = getResultList(rs, clazz);
		 
		 return listResultObject;
		 
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs, stmt, conn);
		}
		return null;
	}
	
	


	/**
	 * 根据Class与ResultSet结果集，生成list数据集合
	 * @param rs ResultSet结果集
	 * @param clazz Class类
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private List<Object> getResultList(ResultSet rs, Class clazz) throws SQLException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		List<Object> listResultObject = null;
		List<Map<String, Object>> fieldList = null;
		 boolean hasResult = true;
		 while (rs.next()) {
			if (hasResult) {
				listResultObject = new ArrayList<Object>();
				fieldList = getClassFields(clazz);
				hasResult = false;
			}
			Object customer  = null;
			customer = clazz.getDeclaredConstructors()[0].newInstance();
			for (int i = 0;i < fieldList.size();i++) {
				Map<String, Object> map = fieldList.get(i);
				
				String field = map.get("fie_name").toString();
				String type = map.get("fie_type").toString();
				String methodName = "set"+field.substring(0,1).toUpperCase()+field.substring(1);
				if (type.equals("String")) {
					Method method = clazz.getMethod(methodName,new Class[]{String.class});
					method.invoke(customer,new String[]{rs.getString(field)});
				}else if (type.equals("Date")) {
					Method method = clazz.getMethod(methodName,new Class[]{Date.class});
					method.invoke(customer,new Date[]{rs.getDate(field)});
				}else if (type.equals("int")) {
					Method method = clazz.getMethod(methodName,new Class[]{int.class});
					method.invoke(customer,new int[]{rs.getInt(field)});
				}
				else if (type.equals("boolean")) {
					Method method = clazz.getMethod(methodName,new Class[]{boolean.class});
					method.invoke(customer,new boolean[]{rs.getBoolean(field)});
				}
			}
			listResultObject.add(customer);
		}
		return listResultObject;
	}

	/**
	 * 拼接limit sql，如：limit 0,2
	 * @param limitList 数组，第一个值表示分页开始数，第二个表示多少个
	 * @return
	 */
	private String getLimitSql(int[] limitList) {
		if (limitList == null || limitList.length < 1) {
			return "";
		}
		StringBuffer limitsql = new StringBuffer(" limit ");
		limitsql.append(limitList[0]);
		limitsql.append(",");
		limitsql.append(limitList[1]);
		return limitsql.toString();
	}

	/**
	 * 根据集合拼接order排序sql,如：order by id asc,username desc 
	 * @param orderList 集合，其中map的fie_name表示字段名，fie_sortType表示排序类型，asc或desc
	 * @return
	 */
	private String getOrderSql(List<Map<String, Object>> orderList) {
		if (orderList == null || orderList.size() < 1) {
			return "";
		}
		StringBuffer ordersql = new StringBuffer(" order by ");
		for (int i = 0; i < orderList.size(); i++) {
			ordersql.append(orderList.get(i).get("fie_name")+" "+orderList.get(i).get("fie_sortType")+" ,");
		}
		return ordersql.substring(0,ordersql.length()-1);
	}
	/**
	 * 根据集合拼接where条件，如： where 1=1 and id=?
	 * @param whereList 集合，其中map的fie_name表示字段，fie_operation表示操作符如and,or
	 * @return
	 */
	private String getWhereSql(List<Map<String, Object>> whereList) {
		if (whereList == null || whereList.size() < 1) {
			return "";
		}
		StringBuffer wheresql = new StringBuffer(" where 1=1 ");
		for (int i = 0; i < whereList.size(); i++) {
			wheresql.append(whereList.get(i).get("fie_operation")+" "+whereList.get(i).get("fie_name")+"=? ");
		}
		return wheresql.toString();
	}

	/**
	 * 根据集合拉拼接sql,结果如：select id,username 
	 * @param fieldList 集合，其中map的fie_name表示字段名
	 * @return
	 */
	private String getHeadSelectSql(List<Map<String, Object>> fieldList) {
		if (fieldList == null || fieldList.size() < 1) {
			return "select * ";
		}
		StringBuffer headsql = new StringBuffer("select ");
		for (int i = 0; i < fieldList.size(); i++) {
			headsql.append(fieldList.get(i).get("fie_name")+",");
		}
		return headsql.substring(0,headsql.length()-1);
	}
	
	

	/**
	 * 根据list集合，set PreparedStatement值，key作where条件去除在外
	 * @param stmt
	 * @param list
	 * @param key 主键
	 * @return
	 * @throws SQLException
	 */
	private PreparedStatement setAllParaMapToPreStmt(PreparedStatement stmt,
			List<Map<String, Object>> list,String id) throws SQLException {
		boolean rr = false;
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> fieldMap = list.get(i);
			if (fieldMap.get("fie_name").toString().equals(id)) {
				stmt.setObject(list.size(), fieldMap.get("fie_value"));
				rr = true;
				continue;
			}
			if (rr) {
				stmt.setObject(i, fieldMap.get("fie_value"));
			}else{
				stmt.setObject(i+1, fieldMap.get("fie_value"));
			}
		}
		return stmt;
	}

	/**
	 * 根据list集合拼接update sql，key为where条件
	 * @param list	List<Map<String, Object>>,javaBean变量信息
	 * @param tableName 表名 
	 * @param key 主键
	 * @return
	 */
	private String getParaMapUpdateSql(List<Map<String, Object>> list,String tableName,String key) {
		//拼接sql,排除主键
		StringBuffer headSql = new StringBuffer("update "+tableName +" set ");
		String whereSql = "";
		//stmt 设类型
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> fieldMap = list.get(i);
			
			if (fieldMap.get("fie_name").toString().equals(key)) {
				whereSql = " where "+fieldMap.get("fie_name").toString()+"=?";
				continue;
			}
			headSql.append(fieldMap.get("fie_name").toString()+"=?,");
		}
		StringBuffer sql = new StringBuffer(headSql.substring(0, headSql.length()-1)+whereSql);
		System.out.println("sql:"+sql);
		return sql.toString();
	}
	

	
	/**
	 * 遍历obj对象，转化成员变量名，值及类型为map对象
	 * 如：
	 * private String username
	 * 转为map装载：fie_name名，fie_value值，fie_type类型
	 * 
	 * @param obj 对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public List<Map<String, Object>> getParaMapFromClass(Object obj) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class clazz = obj.getClass();
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String field = fields[i].getName();
			String methodName = "get"+field.substring(0,1).toUpperCase()+field.substring(1);
			Method method = clazz.getMethod(methodName);
			Object valueObj = method.invoke(obj);
			map.put("fie_name", field);
			map.put("fie_value", valueObj);
			map.put("fie_type", fields[i].getType().getSimpleName());
			list.add(map);
		}
		return list;
	}
	
	
	
	/**
	 * 遍历Class变量，转化成员变量名，类型为map对象
	 * 如：
	 * private String username
	 * 转为map装载：fie_name名,fie_type类型
	 * 
	 * @param Clazz 对象
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public List<Map<String, Object>> getClassFields(Class clazz) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		Field[] fields = clazz.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String field = fields[i].getName();
			String methodName = "get"+field.substring(0,1).toUpperCase()+field.substring(1);
			map.put("fie_name", field);
			map.put("fie_type", fields[i].getType().getSimpleName());
			list.add(map);
		}
		return list;
	}
	
	
	
	
	
	
	
	

}
