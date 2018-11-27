package com.javaproject.examplecollect.email;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

/**
 * 发送文本内嵌图片及附件
 * @author 26031
 *
 */
public class SendEmailB {

	
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();//不设置任何配置，发送时需要
		props.setProperty("mail.host", "smtp.163.com");
		props.setProperty("mail.transport.protocol", "smtp");
		props.setProperty("mail.smtp.auth", "true");//请求认证，与JavaMail的实现有关
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("sanmaidong@163.com"));
		msg.setRecipients(Message.RecipientType.TO, "15812841211@163.com");
		msg.setSubject("JavaMail A");
		
			//邮件主体内容:组装过程
			//文本
			MimeBodyPart textPort = new MimeBodyPart();
			textPort.setContent("人物图 aaa<img src='cid:mm' />aaa", "text/html;charset=UTF-8");
			
			//图片
			MimeBodyPart imgPart = new MimeBodyPart();
			DataHandler imgHandler = new DataHandler(new FileDataSource("src/1.jpg"));
			imgPart.setDataHandler(imgHandler);
			imgPart.setContentID("mm");
			
			//附件
			MimeBodyPart zipPart = new MimeBodyPart();
			DataHandler zipHandler = new DataHandler(new FileDataSource("src/微信图.zip"));
			String filename = zipHandler.getName();
			zipPart.setDataHandler(zipHandler);
			zipPart.setFileName(MimeUtility.encodeText(filename));
			
			MimeMultipart bothpart = new MimeMultipart();
			bothpart.addBodyPart(textPort);
			bothpart.addBodyPart(imgPart);
			bothpart.setSubType("related");
			
			MimeBodyPart bothPartAdd = new MimeBodyPart();
			bothPartAdd.setContent(bothpart);
				
			//组合
			MimeMultipart multipart = new MimeMultipart();
			multipart.addBodyPart(bothPartAdd);
			multipart.addBodyPart(zipPart);
			multipart.setSubType("mixed");
			
		msg.setContent(multipart);
		
		msg.saveChanges();
//		msg.writeTo(new FileOutputStream("d:/3.eml"));
		//发送邮件
		Transport ts = session.getTransport();
		ts.connect("sanmaidong", "lih668408");
		ts.sendMessage(msg, msg.getAllRecipients());
		}
}
