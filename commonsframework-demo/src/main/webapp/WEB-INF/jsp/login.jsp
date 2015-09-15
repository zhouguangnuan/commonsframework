<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <title>登录</title>
        <style>
            .error {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="error">
            ${error.message}
        </div>
        <form action="" method="post">
           <%--  用户名：<input type="text" name="username" value="<shiro:principal/>"> --%>
            用户名：<input type="text" name="username" value=""/>
            <br/>
            密码：<input type="password" name="password">
            <br/>
            自动登录：<input type="checkbox" name="rememberMe" value="true">
            <br/>
            <input type="submit" value="登录">
        </form>
    </body>
</html>