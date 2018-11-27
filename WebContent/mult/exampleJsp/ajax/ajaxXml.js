
window.onload = function(){
	var xhr = ajaxFunction();
	xhr.onreadystatechange = function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				//获取xml信息
				var domXml =xhr.responseXML;
				//通过dom解析
				var proviceElements=domXml.getElementsByTagName("province");;
				//获取所有省信息
				for (var int = 0; int < proviceElements.length; int++) {
					var proviceElement =  proviceElements[int];
					var provinceValue=proviceElement.getAttribute("name");
					
					//创建子节点
					var option=document.createElement("option");
					option.setAttribute("value", provinceValue);
					var text=document.createTextNode(provinceValue);
					option.appendChild(text);
					
					var bigProvince=document.getElementById("province");
					bigProvince.appendChild(option);
				}
				
				//添加省onclick事件
				document.getElementById("province").onchange = function() {
					var city=document.getElementById("city")
					var cityOptions = city.getElementsByTagName("option");
					//清空原信息;
					for (var i = cityOptions.length-1;i>0;i--) {
						city.removeChild(cityOptions[i]);
					}
					
					var currendtProvice = this.value;
					for (var int = 0; int < proviceElements.length; int++) {
						var proviceElement =  proviceElements[int];
						var provinceValue=proviceElement.getAttribute("name")
						
						if (currendtProvice == provinceValue) {
							//获取省下的市
							var cityEles=proviceElement.getElementsByTagName("city");
							for (var int2 = 0; int2 < cityEles.length; int2++) {
								var cityEleValue = cityEles[int2].firstChild.nodeValue;
								
								//创建子节点
								var cityOption=document.createElement("option")
								cityOption.setAttribute("value", cityEleValue);
								var cityText=document.createTextNode(cityEleValue)
								cityOption.appendChild(cityText);
								//动态生成
								city.appendChild(cityOption);
								
							}
							
							
						}
					}
					
					
				}
			}
		}
	}
	
	xhr.open("GET","/MyAlpha/xmlAjaxServlet?timeStamp="+new Date().getTime(),true);
	xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
	xhr.send(null);
	

	function ajaxFunction(){
	   var xmlHttp;
	   try{ // Firefox, Opera 8.0+, Safari
	        xmlHttp=new XMLHttpRequest();
	    }
	    catch (e){
		   try{// Internet Explorer
		         xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
		      }
		    catch (e){
		      try{
		         xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		      }
		      catch (e){}
		      }
	    }
	
		return xmlHttp;
	 }
}


/* 
 普通的ajax实现，点击按键获取服务端响应信息

window.onload=function(){
	
	//获取按钮
	var testButton=document.getElementById("testButton");
	testButton.onclick=function(){
		//生成xmlhttprequest
		var xhr = createXmlHttpRequest();
		//open
		xhr.open("POST","/MyAlpha/xmlAjaxServlet?timeStamp="+new Date().getTime(),true);
		
		xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		//send
//		xhr.send(null);
		xhr.send("aa=abc");
		
		//读取服务器状态，读取返回结果，获取返回值
		xhr.onreadystatechange = function() {
			console.info(xhr.readyState);
			if (xhr.readyState == 4) {
				console.info(xhr.status);
				if (xhr.status == 200) {
					alert(xhr.responseText);
				}
			}
		}
		
	}
	
	
	
	//创建xmlHttpRequest
	function createXmlHttpRequest(){
		//变量
		var xmlHttpRequest;
		try {
			//先创建非ie引擎
			xmlHttpRequest = new XMLHttpRequest();
		} catch (e) {
			try {
				//再创建ie引擎
				xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					//再创建旧版本引擎
					xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
				}
			}
		}
		return xmlHttpRequest;
	} 
}

*/