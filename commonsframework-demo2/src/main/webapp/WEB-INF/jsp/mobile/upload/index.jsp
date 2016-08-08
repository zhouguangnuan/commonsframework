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
	body{ font-size: 12px;}
	body,p,div{ padding: 0; margin: 0;}
	.wraper{ padding: 30px 0;}
	.btn-wraper{ text-align: center;}
	.btn-wraper input{ margin: 0 10px;}
	#file-list{ width: 350px; margin: 20px auto;}
	#file-list li{ margin-bottom: 10px;}
	.file-name{ line-height: 30px;}
	.progress{ height: 4px; font-size: 0; line-height: 4px; background: orange; width: 0;}
    .tip2{text-align: center; font-size:12px; padding-top:10px; color:#b00}
    a.deleteBtn{margin-left: 10px;text-decoration: none;font-size: 17px;color: red;}
    span.result{margin-left: 10px;text-decoration: none;font-size: 17px;color: green;}
</style>
<script type="text/javascript" src="/commonsframework-demo2/static/js/mobile/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/commonsframework-demo2/static/js/mobile/plupload.full.min.js"></script>

<body>
	<p class="tip2">注意：该demo把上传的地址设为了一个静态的html页面，所以文件并不会真正的上传到服务器，但这不会影响上传功能的演示！</p>
	<div class="wraper">
		<div class="btn-wraper">
			<input type="button" value="选择文件..." id="browse" />
			<input type="button" value="开始上传" id="upload-btn" />
		</div>
		<ul id="file-list">

		</ul>
	</div>
</body>
<script>

	var params = {};
	
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'browse',//触发文件选择对话框的按钮，为那个元素id
		url : '/commonsframework-demo2/common/upload',//服务器端的上传页面地址
		flash_swf_url : 'js/Moxie.swf',//swf文件，当需要使用swf方式进行上传时需要配置该参数
		silverlight_xap_url : 'js/Moxie.xap',//silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
		/* filters: { // 文件过滤
			mime_types : [ //只允许上传图片和zip文件
		    	{title : 'Image files', extensions : 'jpg,gif,png'}, 
		    	{title : 'Zip files', extensions : 'zip'}
		  	],
		  	max_file_size : '1000kb', // 最大只能上传400kb的文件
		  	prevent_duplicates : true // 不允许选取重复文件
		}, */
		multipart : true,
		max_retries : 0, // 重试次数
		multi_selection : true, // 是否可以在文件浏览对话框中选择多个文件
		file_data_name : 'file', // 文件上传时文件域的名称
		multipart_params : params, // 上传时的附加参数
		/* multipart_params : { 
		  	one: '1',
		  	two: '2',
		 	three: { //值还可以是一个字面量对象
		    	a: '4',
		    	b: '5'
		  	},
		  	four: ['6', '7', '8']  //也可以是一个数组
		}, */
		chunk_size : 30000000, // 分片上传文件时，每片文件被切割成的大小，为数字时单位为字节
	});
	uploader.init(); //初始化

	var elesLi = {};
	
	// 当文件添加到上传队列后触发
	uploader.bind('FilesAdded',function(uploader,files){
		//每个事件监听函数都会传入一些很有用的参数，
        //我们可以利用这些参数提供的信息来做比如更新UI，提示上传进度等操作
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			var html = '' +
				'<li id="' + files[i].id +'">' +
					'<p class="file-name">' + file_name + '' + 
						'<a href="javascript:void(0);" class="deleteBtn">x</a>' + 
						'<span class="result"></span>' + 
					'</p>' + 
					'<p class="progress"></p>' + 
				'</li>';
			var $li = $(html);
			elesLi[files[i].id] = $li;
			$li.appendTo('#file-list');
		}
	});

	// 当队列中的某一个文件正要开始上传前触发
	uploader.bind('BeforeUpload',function(uploader,file){
		params.fileId = file.id;
	});
	
	// 会在文件上传过程中不断触发，可以用此事件来显示上传进度
	uploader.bind('UploadProgress',function(uploader,file){
        var $li = elesLi[file.id];
		$li.find("p.progress").css('width', file.percent + '%');//控制进度条
	});
	
	// 当队列中的某一个文件上传完成后触发
	uploader.bind('FileUploaded',function(uploader,file){
		var $li = elesLi[file.id];
		$li.find("a.deleteBtn").hide();
		$li.find("span.result").html("完成");
	});
	
	uploader.bind('Error',function(uploader,errObject){
		console.log(errObject);
		// {code: -200, message: "HTTP Error.", file: n, response: "", status: 0…}
	});

	// 上传按钮
	$('body').on('click', '#upload-btn', function(){
		uploader.start(); //调用实例对象的start()方法开始上传文件，当然你也可以在其他地方调用该方法
	});
	
	// 移除文件
	$('body').on('click', 'a.deleteBtn', function(){
		var $this = $(this);
		var $li = $this.parents("li");
		var fileId = $li.attr("id");
		for(var i=0; i<uploader.files.length; i++){
			var f = uploader.files[i];
			if(f.id === fileId){
				uploader.removeFile(f);
				break;
			}
		}
		$li.remove();
	});

</script>
</html>