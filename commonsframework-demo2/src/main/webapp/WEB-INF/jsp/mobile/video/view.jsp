<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<title>视频demo测试</title>
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="renderer" content="webkit" />
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<meta name="format-detection" content="telephone=no" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
</head>
<style>
	body{
		margin: 0;
	    padding: 0;
	}
</style>
<!-- <link type="text/css" rel="stylesheet" href="/commonsframework-demo2/static/css/mobile/danmuplayer.css"> -->
<script type="text/javascript" src="/commonsframework-demo2/static/js/mobile/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/commonsframework-demo2/static/js/mobile/danmuplayer.js"></script>

<body class="body2">
	<div class="video-box">
		<div class="myVideo-content">
			<video id="myVideo" width="100%" height="180" 
				controls
				preload="auto" 
				poster="/commonsframework-demo2/static/upload/123.jpg" 
				src="/commonsframework-demo2/static/upload/123.mp4"
				>
			</video>
		</div>
	</div>
</body>

<script type="application/javascript">
	var myVideo = document.getElementById('myVideo');//获取video元素
	var tol = 0 ; //总时长
	
	myVideo.addEventListener("loadedmetadata", function(){
		tol = myVideo.duration;//获取总时长
		console.log("总时长：" + tol);
	});

	//播放
	function play(){
		myVideo.play();
	}

	//暂停
	function pause(){
		myVideo.pause();
	}

	//设置播放点
	function playBySeconds(num){
		myVideo.currentTime = num;
	}
	
	//设置音量
	function setVol(num){
		myVideo.volume = num;
	}
	
	//播放时间点更新时
	myVideo.addEventListener("timeupdate", function(){
		// console.log("播放时间点更新：" + myVideo.currentTime);
	});

	//音量改变时
	myVideo.addEventListener("volumechange", function(){
		console.log("音量改变时：" + myVideo.volume);
	});
</script>
</html>