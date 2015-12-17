<html>
<script type="text/javascript">
var isComeFromCancelPay;
<#if isComeFromCancelPay>
	isComeFromCancelPay = true;
</#if>

if(isComeFromCancelPay)
{
	alert("isComeFromCancelPay");
} else {
	alert("notComeFromCancelPay");
}
</script>
<body>
<h2>你好：${name}</h2>

<h2>你好：${name2!"11"}</h2>

<#if name2??>

</#if>

</body>
</html>
