package com.javaproject.examplecollect.i18nexample;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18TestTwo {
	
	public static void main(String[] args) {
		
		Locale locale = Locale.getDefault();
		locale = Locale.US;
		//short,medium,full
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
		System.out.println(dateFormat.format(new Date()));
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);//货币
		System.out.println(numberFormat.format(10000));
		
		numberFormat = NumberFormat.getPercentInstance(locale);
		//System.out.println(numberFormat.format(2/3));	//错的,默认是int类型，结果是0
		System.out.println(numberFormat.format(2f/3));
		
		//you in Feb 13, 2017 by some thing pay $100,000.00 money,67% pay of credit card
		//写入模式及locale,再加入object参数format
		//参数不对类型会出错
		String str = "you in {0,date,medium } by some thing pay {1,number,currency} money,{2,number,percent} pay of credit card";
		MessageFormat messageFormat = new MessageFormat(str, locale);
		Object[] obj = new Object[]{
				new Date(),
				100000,
				2f/3
				};
		System.out.println(messageFormat.format(obj));;
		
		//固定文本国际化
		ResourceBundle bundle = ResourceBundle.getBundle("i18n11.login", locale);
		System.out.println(bundle.getString("loginname"));
		
	}

}
