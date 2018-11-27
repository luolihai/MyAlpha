<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<meta charset="utf-8">
<title>无标题文档</title>
<link href="css/video-js.min.css" rel="stylesheet">
<script src="js/video.min.js"></script>
	<style>
		body{background-color: #252525}
		.m{ margin-left: auto; margin-right: auto; width: 960px;height: 400px;margin-top: 100px}
	</style>
</head>

<body>
<!--
	<source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4"></source>
  <source src="http://vjs.zencdn.net/v/oceans.webm" type="video/webm"></source>
  <source src="http://vjs.zencdn.net/v/oceans.ogv" type="video/ogg"></source>
  
	-->
<div class="m">
<video
    id="my-player"
    class="video-js"
    controls
    preload="auto"
    poster="image/1.png"
    data-setup='{}'>
  		<source src="avi/12.mp4" type="video/webm"></source>
  <p class="vjs-no-js">
    To view this video please enable JavaScript, and consider upgrading to a
    web browser that
    <a href="http://videojs.com/html5-video-support/" target="_blank">
      supports HTML5 video
    </a>
  </p>
</video>
</div>

<script type="text/javascript">
	//打开自动播放
    var myPlayer = videojs('my-player');
    videojs("my-player").ready(function(){
        var myPlayer = this;
        myPlayer.play();
    });
</script>

</body>
</html>
