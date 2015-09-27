<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="MOBILE_APP" value='<%=cn.singno.commonsframework.constant.SystemConst.ClientType.MOBILE_APP%>'></c:set>
<c:set var="MOBILE_WX" value='<%=cn.singno.commonsframework.constant.SystemConst.ClientType.MOBILE_WX%>'></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>错误页面</title>
</head>
<body> 
	<c:choose>
		<c:when test="${ClientType==MOBILE_APP}">
			404 error</br>
			请求资源不存在</br>
			原生app客户端
		</c:when>
		<c:when test="${ClientType==MOBILE_WX}">
			404 error</br>
			请求资源不存在</br>
			微信客户端
		</c:when>
		<c:otherwise>
			404 error</br>
			请求资源不存在</br>
			默认客户端
		</c:otherwise>
	</c:choose>
</body>
</html>