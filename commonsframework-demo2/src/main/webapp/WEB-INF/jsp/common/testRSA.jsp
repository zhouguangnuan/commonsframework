<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>RSA</title>
<script type="text/javascript" src="${ctxStatic}/js/security.js"></script>
<script type="text/javascript">
        $(function(){
            $("#btn").click(function(){
            $.getJSON('${ctxBase}/common/testRSA/keyPair',function(jsonResult) {
                        var modulus = jsonResult.data.modulus;
                        var exponent = jsonResult.data.exponent;
                        var epwd = $('#password').val();
                        epwd = encodeURIComponent(epwd);// 兼容中文
                        if (epwd.length != 256) {
                            var publicKey = RSAUtils.getKeyPair(exponent, '', modulus);
                            $('#password').val(RSAUtils.encryptedString(publicKey, epwd));
                        }
                        $("#login").submit();
                    });
            });
        });
</script>
</head>
<body>
	<form id="login" name="login" action="${ctxBase}/common/testRSA/login2" method="post">
		密码：<input type="text" id="password" name="password" /> 
		<input id="btn" type="button" value="提 交" />
	</form>
</body>
</html>


