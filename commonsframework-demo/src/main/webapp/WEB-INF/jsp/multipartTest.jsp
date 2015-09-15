<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	==================================== 图片上传==================================== 
	<form action="/multipart/upload" method="post" enctype="multipart/form-data">  
        <input type="file" name="file"/>  
        <input type="submit"/>  
    </form> 
	<img alt="暂时没有图片" src="${uploadResult.relativePath}">
	
	==================================== 图片下载====================================
	<form action="/multipart/download" method="post" enctype="multipart/form-data">  
        <input type="text" name="path" value="${uploadResult.relativePath}"/>  
        <input type="text" name="fileName" value="${uploadResult.fileName}"/>  
        <input type="submit"/>  
    </form> 
    
	==================================== 批量图片上传====================================
	<form action="/multipart/uploads" method="post" enctype="multipart/form-data">  
        <input type="file" name="files[0]"/>  
        <input type="file" name="files[1]"/>  
        <input type="file" name="files[2]"/>  
        <input type="submit"/>  
    </form> 
	<c:forEach items="${list}" var="uploadResult">
		<img alt="暂时没有图片" src="${uploadResult.relativePath}">
	</c:forEach>
</body>
</html>