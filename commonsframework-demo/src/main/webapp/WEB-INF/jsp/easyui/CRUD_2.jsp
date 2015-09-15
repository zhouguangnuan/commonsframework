<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CRUD</title>
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/jquery-easyui-1.4.1/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${ctxStatic}/js/jquery-easyui-1.4.1/themes/icon.css">
<script type="text/javascript"
	src="${ctxStatic}/js/jquery-easyui-1.4.1/jquery.min.js"></script>
<script type="text/javascript"
	src="${ctxStatic}/js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
</head>
<body>
	<table id="box"></table>

	<script type="text/javascript">
		$('#box').datagrid({
			url : '/test/easyui/crud/data',
			//width : 500,
			title : '用户列表',
			iconCls : 'icon-search',
			columns : [ [ {
				field : 'firstname',
				title : '姓',
				//sortable : true,
				editor : {
					type : 'validatebox',
					options : {
						required : true,
					},
				}
			}, {
				field : 'lastname',
				title : '名字',
				sortable : true,
				sorter : function(a, b) {
					console.log(a + '|' + b);
				},
			}, {
				field : 'phone',
				title : '电话',
			//sortable : true,
			}, {
				field : 'email',
				title : '邮箱地址',
				//sortable : true,
				formatter : function(value, row, index) {
					return '[' + value + ']';
				}
			}, ] ],
			fit : false,
			fitColumns : true,
			pagination : true,
			idField : 'id',
			pageSize : 10,
			pageList : [ 10, 20, 30, 40 ],
			//sortName : 'date',
			//sortOrder : 'DESC',
			method : 'get',
			//multiSort : true, 
			remoteSort : false,
			striped : true,
			//fitColumns : false,
			loadMsg : "数据加载中...",
			//showHeader : false,
			//showFooter : true,
			fixed : true,
			resizable : true,
		});
		
		console.log($('#box').datagrid('options'));
	</script>
</body>

</html>