<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="jquery-1.11.3.min.js"></script>
<script src="jquery.barrager.js"></script>


<script type="text/javascript">

	var items = [
	             {
				   img:'haha.gif', //图片 
				   info:'弹幕文字信息aa', //文字 
				   href:'http://www.yaseng.org', //链接 
				   close:true, //显示关闭按钮 
				   speed:6, //延迟,单位秒,默认6 
				   bottom:70, //距离底部高度,单位px,默认随机 
				   color:'#fff', //颜色,默认白色 
				   old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色 
				 },
				 {
					   img:'haha.gif', //图片 
					   info:'弹幕文字信息bb', //文字 
					   href:'http://www.yaseng.org', //链接 
					   close:true, //显示关闭按钮 
					   speed:6, //延迟,单位秒,默认6 
					   bottom:70, //距离底部高度,单位px,默认随机 
					   color:'#fff', //颜色,默认白色 
					   old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色 
					 },
					 {
						   img:'cute.png', //图片 
						   info:'弹幕文字信息cc', //文字 
						   href:'http://www.yaseng.org', //链接 
						   close:true, //显示关闭按钮 
						   speed:6, //延迟,单位秒,默认6 
						   bottom:70, //距离底部高度,单位px,默认随机 
						   color:'#fff', //颜色,默认白色 
						   old_ie_color:'#000000', //ie低版兼容色,不能与网页背景相同,默认黑色 
						 },
	             
	             ];

		//每条弹幕发送间隔
		var looper_time = 3 * 1000;
		//弹幕总数
		var total = items.length;
		//是否首次执行
		var run_once = true;
		//弹幕索引
		var index = 0;
		//先执行一次
		barrager();
		function barrager() {
			if (run_once) {
				//如果是首次执行,则设置一个定时器,并且把首次执行置为false
				looper = setInterval(barrager, looper_time);
				run_once = false;
			}
			//发布一个弹幕
			$('body').barrager(items[index]);
			//索引自增
			index++;
			//所有弹幕发布完毕，清除计时器。
			if (index == total) {
				clearInterval(looper);
				return false;
			}
		}
		
</script>

</head>
<body>
	<a>tt</a>
</body>
</html>