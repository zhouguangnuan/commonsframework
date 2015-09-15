<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>CRUD</title>
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/js/jquery-easyui-1.4.1/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="${ctxStatic}/js/jquery-easyui-1.4.1/themes/icon.css">
		<script type="text/javascript" src="${ctxStatic}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
		<script type="text/javascript" src="${ctxStatic}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	</head>
	<body>
		<table id="dg" title="My Users" class="easyui-datagrid"  url="/test/easyui/crud/data" method="get" toolbar="#toolbar" rownumbers="true" fitColumns="true" singleSelect="true">
			<thead>
				<tr>
					<th field="firstname" width="50">First Name</th>
					<th field="lastname" width="50">Last Name</th>
					<th field="phone" width="50">Phone</th>
					<th field="email" width="60">Email</th>
				</tr>
			</thead>
		</table>
		<div id="toolbar">
			<a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="newUser()">New User</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editUser()">Edit User</a>
			<a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="destroyUser()">Remove User</a>
		</div>
	</body>

</html>