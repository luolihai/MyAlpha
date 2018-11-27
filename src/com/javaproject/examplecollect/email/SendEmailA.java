package com.javaproject.examplecollect.email;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 * 内嵌图片邮件
 * @author 26031
 *
 */
public class SendEmailA {

	
	public static void main(String[] args) throws Exception {
		Properties props = new Properties();
		//smtp地址
		props.setProperty("mail.host", "smtp.163.com");
		//smtp协议
		props.setProperty("mail.transport.protocol", "smtp");
		//163授权 
		props.setProperty("mail.smtp.auth", "true");
		
		
		Session session = Session.getInstance(props);
		session.setDebug(true);
		MimeMessage msg = new MimeMessage(session);
		
		msg.setFrom(new InternetAddress("sanmaidong@163.com"));
		msg.setRecipients(Message.RecipientType.TO, "15812841211@163.com");
		msg.setSubject("java email add file");
		
		//简单文本邮件
		//msg.setText("please, see this email and say:Oh! very good!");
		
		//文本内容
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent("aaa<img src='cid:mm' />aaa", "text/html");
		
		//图片内容
		MimeBodyPart imgPart = new MimeBodyPart();
		DataHandler imgHandler = new DataHandler(new FileDataSource("src/1.jpg"));
		
		imgPart.setDataHandler(imgHandler);
		imgPart.setContentID("mm");
		
		//Multipart描述关系
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(textPart);
		mimeMultipart.addBodyPart(imgPart);
		mimeMultipart.setSubType("related");
		
		
		msg.setContent(mimeMultipart);
		
		msg.saveChanges();
		
		Transport transport = session.getTransport();
		transport.connect("sanmaidong", "lih668408");
		transport.sendMessage(msg, msg.getAllRecipients());
		
		
	}
	
	//Seesion设置参照JMail技术说明，form发件人，emailPassword邮件密码，to收件人，subject主题，content内容，files附件
	public void send(Session session,String from, String emailPassword,String to,String subject, 
			String content, List<File> files) throws AddressException, MessagingException{
		MimeMessage msg = new MimeMessage(session);//元数据
		msg.setFrom(new InternetAddress(from));//发送人
		msg.setRecipients(Message.RecipientType.TO, to);//收件人
		msg.setSubject(subject);//主题
		
		MimeBodyPart textPart = new MimeBodyPart();
		textPart.setContent(content, "text/html");//文件内容
		MimeBodyPart imgPart = new MimeBodyPart();
		DataHandler imgHandler = new DataHandler(new FileDataSource(files.get(0)));//附件
		imgPart.setDataHandler(imgHandler);
		imgPart.setContentID(files.get(0).getName());
		
		//Multipart描述关系
		MimeMultipart mimeMultipart = new MimeMultipart();
		mimeMultipart.addBodyPart(textPart);//内容
		mimeMultipart.addBodyPart(imgPart);//附件
		mimeMultipart.setSubType("related");
		msg.setContent(mimeMultipart);
		msg.saveChanges();
		
		Transport transport = session.getTransport();//发送
		String username = from.substring(0,from.indexOf("@")-1);//提取用户名
		transport.connect(username, emailPassword);
		transport.sendMessage(msg, msg.getAllRecipients());
	}

}
