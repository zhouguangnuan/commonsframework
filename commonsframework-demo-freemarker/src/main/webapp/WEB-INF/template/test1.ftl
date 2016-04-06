<html>
<script type="text/javascript" src="${mRoot}/static/js/jfb/base/zepto.min.js?v=1.1.6" ></script>
<script type="text/javascript">
var isComeFromCancelPay;
<#if isComeFromCancelPay>
	isComeFromCancelPay = true;
</#if>

/*
if(isComeFromCancelPay)
{
	alert("isComeFromCancelPay");
} else {
	alert("notComeFromCancelPay");
}*/

var goto = function(url){
	window.location.href = url;
}

</script>
<body>
<h2>你好：${name}</h2>

<h2>你好：${name2!"222"}</h2>

<a href="javascript:goto('http://localhost:8080/commonsframework-demo-freemarker/test/test2');">http://localhost:8080/commonsframework-demo-freemarker/test/test2</a>
</br>

<a href="javascript:goto('http://localhost:8080/BenlaiWap_branches/showlogin?backType=100');">http://localhost:8080/BenlaiWap_branches/showlogin</a>
</br>
<#if name2??>

</#if>

</body>
</html>
