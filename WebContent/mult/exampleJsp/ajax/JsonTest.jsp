<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		//json对象形式,语法：普通形式用{},数组形式用[],结束符用分号; 使用为.key形式,装载类型是字符，数字，boolean,对象及null
		//注意，键值对不能用[]包括，需用{}包括后再[]包括,key值同名会被最后的覆盖
	
			
		//语法,key:value,key常用时可不用双引号
		var person = {
				name : "zhangshan",
				age : 18,
				birthday:"1999-12-12"
		};
			
		//数据类型
		var store = {
				numbers:200.22,
				hasMore:true,
				date : new Date(),
				common : "数据量不大"
		}
		
		//josn数组形式
		
		//语法
		var list = ["1","2","3"];
		var personList_1= [
		              	{
		             		numbers:200.22,
			  				hasMore:true,
							date : new Date(),
							common : "数据量不大1"
					  	},
						{
							numbers:200.22,
				  			hasMore:true,
							date : new Date(),
							common : "数据量不大2"
					  	},
					  	{
							numbers:200.22,
				  			hasMore:true,
							date : new Date(),
							common : "数据量不大2"
					  	}
					]; 
		
		//复杂数据类型
		var personList_2= [{
			              	"tt1" : { 
			             		numbers:200.22,
				  				hasMore:true,
								date : new Date(),
								common : "数据量不大1"
						  	},
						  	"tt2" : {
								numbers:200.22,
					  			hasMore:true,
								date : new Date(),
								common : "数据量不大2"
						  	},
						  	"tt3" : {
								numbers:200.22,
					  			hasMore:true,
								date : new Date(),
								common : "数据量不大2"
						  	}
						}];
		
		
		//同名,互相嵌套
		var personList_3= [{
			              	"tt1" : { 
			             		numbers:200.22,
				  				hasMore:true,
								date : new Date(),
								common : "数据量不大1"
						  	},
						  	"tt1" : {
								numbers:200.22,
					  			hasMore:true,
								date : new Date(),
								common : "数据量不大2"
						  	},
						  	"tt3" : {
								numbers:200.22,
					  			hasMore:true,
								date : new Date(),
								common : "数据量不大3",
								count : function(a,b){
									return a+b;
								}
						  	}
						}];
		
		
		var sameList= [{
          	tt1 : { 
         		numbers:200.22,
  				hasMore:true,
				date : new Date(),
				common : "数据量不大1"
		  	},
		  	tt1 : {
				numbers:200.22,
	  			hasMore:true,
				date : new Date(),
				common : "数据量不大2"
		  	},
		  	tt3 : {
				numbers:200.22,
	  			hasMore:true,
				date : new Date(),
				common : "数据量不大2",
				count : function(a,b){
					return a+b;
				}
		  	}
		}];
		
		/* 
		alert(person.name);//简单对象
		console.info(person);
		
		alert(store.date);//对象时间类型
		console.info(store);
		
		alert(list.length+":"+list[2]);//简单数组
		alert(personList_1[0].common);//对象数组
		console.info(personList_2[0]);//key:value数组
		
		console.info(personList_3[0]);//同名
		
		console.info(personList_3[0].tt3.count(2, 3));//函数
		console.info(sameList[0].tt3);//数据内key不用双引号
		*/
		
		console.info(personList_3[0]);//同名
	</script>
</body>
</html>