<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>home</title>
    <script src="js/jquery.min.js"></script>
    <title>SSO client2</title>
</head>
<body>
<h1>SSO tmall Client2</h1>
<a href="http://sso-taobao:8083/client1/index.html">访问Client1</a>
<a href="http://localhost:8082/uaa/logout">退出</a>
<a href="http://localhost:8084/client2/logout">退出</a>
<form action="/client2/logout" enctype="multipart/form-data" method="post">
    <input name="${(_csrf.parameterName)!}" value="${(_csrf.token)!}" type="hidden">
    <input type="submit" value="tuichupost">
</form>
</body>
</html>
