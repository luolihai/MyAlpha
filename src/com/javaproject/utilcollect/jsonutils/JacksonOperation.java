package com.javaproject.utilcollect.jsonutils;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.SerializationConfig.Feature;
import org.codehaus.jackson.node.ObjectNode;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.junit.Test;

import com.javaproject.utilcollect.bean.Gender;
import com.javaproject.utilcollect.bean.Name;
import com.javaproject.utilcollect.bean.User;

public class JacksonOperation {

	
	
	
	
	
	@Test
	public void test() throws Exception{
		
		/*
		//JsonNode树内取值，json数据从String对象中获取
		String jsonString = "{\"k1\":\"v1\",\"k2\":\"v2\"}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode actualObj = mapper.readTree(jsonString);
		System.out.println(actualObj.toString());
		*/
		
		/*
		//树模型，树内取值JsonNode节点，json数据从文本中获取
		ObjectMapper objectMapper = new ObjectMapper();
		//user.json格式要正确
		JsonNode readTree = objectMapper.readTree(new File("src/user.json"));
		JsonNode findParent = readTree.findValue("gender");
		System.out.println(findParent.toString());
		 */
		/*
		//树模型写操作
		ObjectMapper objectMapper = new ObjectMapper();
		User user = objectMapper.readValue(new File("src/user.json"), User.class);
		
		objectMapper.configure(Feature.INDENT_OUTPUT, true);//设置下格式化输出
		objectMapper.writeValue(new File("src/user1.json"), user);
		System.out.println(user.isVerified());
		*/
		
		
		//获取json
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(new File("src/user.json"), Map.class);
		String json = objectMapper.writeValueAsString(map);
		System.out.println(json);
		
		
		
		
		/*
		//简单数据绑定，没泛型
		ObjectMapper objectMapper = new ObjectMapper();
		Map map = objectMapper.readValue(new File("src/user.json"), Map.class);
		System.out.println(map.get("verified"));
		*/
		
		/*
		//简单数据绑定，泛型，使用TypeReference类引入,注意：泛型中String也要有对应的json值，不然解析不了
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String,User> map = objectMapper.readValue(new File("src/user.json"), new TypeReference<Map<String,User>>() {});
		for (Entry<String, User> en : map.entrySet()) {
			System.out.println(en.getKey());
			System.out.println(en.getValue().isVerified());
		}
		*/
		/*
		//json树，通过JsonNode.path一层层获取值
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readValue(new File("src/user.json"), JsonNode.class);
		JsonNode namepath = rootNode.path("name");
		JsonNode firstpath = namepath.path("first");
		System.out.println(firstpath.getTextValue());
		*/
		/*
		//json树，通过ObjectNode.put修改值再写入
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode rootNode = objectMapper.readValue(new File("src/user.json"), JsonNode.class);
		JsonNode namepath = rootNode.path("name");
		((ObjectNode)namepath).put("first", "tt1");
//		objectMapper.writeValue(new File("src/user1.json"), namepath);
		//获取json字符
//		System.out.println(namepath.toString());
		*/
		
		
		
		/*
		//流写入,JsonGenerator类处理
		JsonFactory f = new JsonFactory();
		JsonGenerator g = f.createJsonGenerator(new FileOutputStream("src/user1.json"));
		g.writeStartObject();
		g.writeObjectFieldStart("name");
		g.writeStringField("fitst", "jack1");
		g.writeStringField("last", "lucy1");
		g.writeEndObject();
		g.writeStringField("gender", Gender.MALE.name());
		g.writeBooleanField("verified", true);
		String str = "Rm9vYmFyIQ==";
		g.writeBinaryField("userImage", str.getBytes("utf-8"));
		g.writeEndObject();
		g.close(); // 重要：强制写入输出，并关闭输出流!
		*/
		
		
		/*
		//流读取，绑定对象
		JsonFactory f = new JsonFactory(); 
		JsonParser parser = f.createJsonParser(new File("src/user.json"));
		parser.nextToken();
		User user = new User();
		System.out.println("开始...");
		while (parser.hasCurrentToken()) {
			parser.nextToken();
			if (parser.getText() == null) {
				continue;
			}
			if (parser.getText().equals("name")) {
				Name name = new Name();
				parser.nextToken();
				while (parser.hasCurrentToken()) {
					parser.nextToken();
					if (parser.getCurrentName().equals("first")) {
						parser.nextToken();
						name.setFirst(parser.getText());
					}else if (parser.getCurrentName().equals("last")) {
						parser.nextToken();
						name.setFirst(parser.getText());
						user.setName(name);
						break;
					}
				}
			}else if(parser.getText().equals("gender")){
				parser.nextToken();
				Gender[] genders = Gender.values();
				for (Gender gender : genders) {
					if (gender.name().equals(parser.getText())) {
						user.setGender(gender);
						break;
					}
				}
			}else if(parser.getText().equals("verified")){
				parser.nextToken();
				user.setVerified(parser.getBooleanValue());
			}else if(parser.getText().equals("userImage")){
				parser.nextToken();
				user.setUserImage(parser.getBinaryValue());
			}
		}
		System.out.println(user.getName().getFirst());
		*/
		
		/*
		private Gender _gender;
private Name _name;
private boolean _isVerified;
private byte[] _userImage;
		
		//流读取，遍历结构
		JsonFactory f = new JsonFactory(); 
		JsonParser parser = f.createJsonParser(new File("src/user.json"));
		parser.nextToken();
		System.out.println(parser.getCurrentName()+"："+parser.getText());
		while (parser.hasCurrentToken()) {
			parser.nextToken();
			System.out.println(parser.getCurrentName()+"："+parser.getText());
		}*/
		/*
		打印：
		null：{
			name：name
			name：{
			first：first
			first：Joe
			last：last
			last：Sixpack
			name：}
			gender：gender
			gender：MALE
			verified：verified
			verified：true
			userImage：userImage
			userImage：Rm9vYmFyIQ==
			null：}
			null：null
		 */
		
	}
	
}
