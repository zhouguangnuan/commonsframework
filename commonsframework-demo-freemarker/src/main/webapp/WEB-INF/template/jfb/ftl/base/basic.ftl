<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8" />	
	<meta content="yes" name="apple-mobile-web-app-capable" />
	<meta content="black" name="apple-mobile-web-app-status-bar-style" />
	<meta content="telephone=no" name="format-detection" />
	<meta property="qc:admins" content="2275617726564116375" />
	<meta name="viewport" content="minimal-ui=yes,width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no">
	<#if wapTitle??>
		<title>${wapTitle}</title>
	<#else>
		<title>巨方便M站</title>
	</#if>
	<link href="${mRoot}/static/style/jfb/common.css" rel="stylesheet" type="text/css" />
	<script>
	var Config = {
		Path: {
			cssPath: "${mRoot}/static/style/jfb/",
			scriptPath: "${mRoot}/static/js/jfb/",
			imagePath: "${mRoot}/static/images/jfb/",
			version: "1.0"
		},
		mRoot: '${mRoot}', 
		apiRoot: '${apiRoot}',
	}
	</script>
	<script type="text/javascript" src="${mRoot}/static/js/jfb/base/zepto.min.js?v=1.1.6" ></script>
	<script type="text/javascript" src="${mRoot}/static/js/jfb/base/basic.js?v=100" ></script>
	<@mhead/>
</head>
<body>
<@mbody/>

</body>
</html>