<#include "/jfb/ftl/base/basic.ftl">
	<#macro mhead>
		<!--[if lt IE 9]> 
<script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script> 
<![endif]-->
	</#macro>
	<#macro mbody>
		
		<a href="javascript:void(0);">page1</a>
		<a href="javascript:void(0);">page2</a>
		<a href="javascript:void(0);">page3</a>
		
		<p>内容: </p>
		<div id="content"></div>
		
		<script type="text/javascript">
			$(function(){
				function loadData(page){
					$.getApi(Config.apiRoot + "/api/test2/" + page + "/data", {}, function(json) {
						console.log(json);
						$("#content").html(json.data.page);
					}, "json");
				}
			
				$("a").on("click", function(){
					var page = $(this).html();
					window.history.pushState(null, null, Config.mRoot + "/test2/" + page);
					loadData(page);
				});
				
				window.addEventListener('popstate', function(e) {
					var page = location.pathname.substr(location.pathname.lastIndexOf("/")+1, location.pathname.length);
					loadData(page);
		 　　	});
				
				loadData("${page}");
				alert("页面初始化完毕");
			})
		</script>
	</#macro>