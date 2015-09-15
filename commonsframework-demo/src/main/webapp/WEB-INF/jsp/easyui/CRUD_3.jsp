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
	src="${ctxStatic}/js/jquery-easyui-1.4.1/jquery.easyui.min2.js"></script>
	<script type="text/javascript" src="${ctxStatic}/js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js" ></script>
</head>
<body>
	<form id="searchForm">
		<input name="param1" type="text" >
		<input name="param2" type="text" >
		<a id="button1">查询</a>
	</form>
	<table id="box"></table>

	<script type="text/javascript">
		(function($) {
			$.fn.serializeJson = function() {
				var objArray = [];
				
				$(this).each(function(i,obj){
					var serializeObj = {};
					$($(obj).serializeArray()).each(function() {
						serializeObj[this.name] = this.value;
					});
					objArray[i] = serializeObj;
				});
				if(objArray.length==1){
					return objArray[0];
				}else{
					return objArray; 
				}
			}
		})(jQuery);
	
		
		$.fn.datagrid.defaults.loadFilter = function(data){
				var pageResult = {};
				pageResult.rows = data.object;
				pageResult.total = data.totalElements;
				return pageResult;
		}
		 
		$('#box').datagrid({
			url : '/test/easyui/crud/data2',
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
			/* onLoadSuccess : function(data){
				alert(1); 
			}, */
			/* onBeforeLoad : function(){
				alert(2);
			} */
			/* onSelectPage : function (pageNumber, pageSize) {
				alert(pageNumber + "|" + pageSize);
				//$('#box').panel('refresh', '/test/easyui/crud/data2?page='+pageNumber+'&size='+size); 
			}, */
			/* loader : function(param,success,error){
					$.ajax({
						type : 'get',
						url : '/test/easyui/crud/data2',
						data : param,
						dataType : "json",
						success : function (data) {
							success(bulidData(data));
						},
						error : function () {
							error.apply(this, arguments);
						}
					});
					
					function bulidData(data) 
					{
						var pageResult = {};
						pageResult.rows = data.object;
						pageResult.total = data.totalElements;
						return pageResult;
					}
				}, */
				/* $('#box').datagrid("getPager")[0].pagination("onSelectPage", function(pageNumber, pageSize){
					alert(1);
					console.log(pageNumber+"|"+pageSize);
				});  */
				/* loadFilter: function(data){
						var pageResult = {};
						pageResult.rows = data.object;
						pageResult.total = data.totalElements;
						return pageResult;
				} ,*/
				//queryParams : $("#searchForm").serializeJson()
		});
		
		
		
		/* var p = $('#box').datagrid('getPager'); 
	    $(p).pagination({ 
	        pageSize: 10,//每页显示的记录条数，默认为10 
	        pageList: [5,10,15, 20],//可以设置每页记录条数的列表 
	        beforePageText: '第',//页数文本框前显示的汉字 
	        afterPageText: '页    共 {pages} 页', 
	        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录', 
	        onBeforeRefresh:function(){
	            $(this).pagination('loading');
	            alert('before refresh');
	            $(this).pagination('loaded');
	        }
	        //onSelectPage : function(pageNumber, pageSize){ 
				//alert('pageNumber:'+pageNumber+',pageSize:'+pageSize);
			//}
	    });  */
		
		
		//console.log($('#box').datagrid("getPager"));
		//console.log($("#searchForm").serializeJson());
		//console.log($("#searchForm").serializeArray());
		//console.log($($('#box').datagrid('getPager')).pagination("options"));
		
		$("#button1").click(function(){
			$($('#box').datagrid('getPager')).pagination('select', 1);   
			var params = $("#searchForm").serializeJson();
			$('#box').datagrid({
				queryParams : params,
			});
		});
	</script>
</body>

</html>