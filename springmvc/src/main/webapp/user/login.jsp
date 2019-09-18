<%--
  Created by IntelliJ IDEA.
  User: zqs13
  Date: 2019/9/18
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <hi>登录页面</hi>
    <%--action写的是路由地址，不是物理地址--%>
    <form action="logined" method="post">
        <p>账户：<input type="text" name="username"></p>
        <p>密码：<input type="password" name="password"></p>
        <p><input type="submit" value="登录"></p>
    </form>
</body>
</html>
