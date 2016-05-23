<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>微官网</title>
<style type="text/css">
div#responseDiv {
	margin-top: 10px;
	padding: 10px;
	border: red solid;
}
</style>
</head>
<body>
	rest api test</br>

	<button value="put" id="put">put</button>
	<button value="delete" id="delete">delete</button>
	<button value="post" id="post">post</button>
	<button value="get" id="get">get</button>

	<div id="responseDiv"></div>

	<script>
		$(function(){
			var $responseDiv = $("#responseDiv");
			
			$("#put").on("click", function(){
				var requestUrl = "${ctxBase}/api/orders";
				var requestdata = {"productId": "1","count": 0};
				
				$.ajax({ 
					type: "PUT",
					url: requestUrl, 
					data: JSON.stringify(requestdata),
					async: true,
					dataType: "json",
					headers: {"Content-Type":"application/json"},
					timeout: 1000,
					cache: false,
					success: function(data){
						$responseDiv.html(JSON.stringify(data));
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			});
			
			$("#delete").on("click", function(){
				var requestUrl = "${ctxBase}/api/orders/1";
				var requestdata = null;
				
				$.ajax({ 
					type: "DELETE",
					url: requestUrl, 
					data: requestdata,
					async: true,
					dataType: "json",
					headers: {"Content-Type":"application/json"},
					timeout: 1000,
					cache: false,
					success: function(data){
						$responseDiv.html(JSON.stringify(data));
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			});
			
			$("#post").on("click", function(){
				var requestUrl = "${ctxBase}/api/orders/1";
				var requestdata = {"productId": "1","count": 0};
				
				$.ajax({ 
					type: "POST",
					url: requestUrl, 
					data: JSON.stringify(requestdata),
					async: true,
					dataType: "json",
					headers: {"Content-Type":"application/json"},
					timeout: 1000,
					cache: false,
					success: function(data){
						$responseDiv.html(JSON.stringify(data));
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			});
			
			$("#get").on("click", function(){
				var requestUrl = "${ctxBase}/api/orders/1";
				var requestdata = null;
				
				$.ajax({ 
					type: "GET",
					url: requestUrl, 
					data: requestdata,
					async: true,
					dataType: "json",
					headers: {"Content-Type":"application/json"},
					timeout: 1000,
					cache: false,
					success: function(data){
						$responseDiv.html(JSON.stringify(data));
					},
					error: function(XMLHttpRequest, textStatus, errorThrown){
						console.log(XMLHttpRequest);
						console.log(textStatus);
						console.log(errorThrown);
					}
				});
			});
		});	
	</script>
</body>
</html>