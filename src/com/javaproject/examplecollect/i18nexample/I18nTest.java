package com.javaproject.examplecollect.i18nexample;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Currency;
import java.util.Date;
import java.util.Locale;

public class I18nTest {

	public static void main(String[] args) throws ParseException {
		
		Locale locale = Locale.getDefault();
//		locale = locale.US;
		
		
		/*
		//时间i18处理
		//中式： 2016-12-5 17:16:29	 美式：Dec 5, 2016 5:17:17 PM 
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
		System.out.println(df.format(new Date()));
		*/
		
		/*
		//数字i18处理
		//如：￥12,313.00/$12,313.00
		NumberFormat nf = NumberFormat.getCurrencyInstance(locale);
		String num = nf.format(12313.00);
		System.out.println(num);
		*/
		
		/*
		//百分比
		//如：11%
		NumberFormat nf = NumberFormat.getPercentInstance(locale);
		String per = nf.format(0.11);
		System.out.println(per);
		 */		
		
		
		//你好，你于 2016-12-5 17:16:29号存入1000元，利率10%
		//字符i18处理
		String patter = "你好，你于 {0,date,medium } {0,time,medium }号存入{1,number,currency}元，利率{2,number,percent}";
		DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, locale);
		Date date = df.parse("2016-12-5 17:16:29");
		
		Object[] obj = {date,1000,0.11};
		String mf = MessageFormat.format(patter, obj);
		System.out.println(mf);
		
	}
}
