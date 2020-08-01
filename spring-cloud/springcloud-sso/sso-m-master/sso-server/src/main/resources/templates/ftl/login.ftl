<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="/uaa/js/jquery-3.5.1.min.js"></script>
    <script>
        $(function () {
            $("#result_code").text("Hello world!");
            $("#get_code").click(function () {
                $.get({
                    url: "/uaa/message/code?phone=" + $("#phone").val(),
                    success: function (result) {
                        $("#result_code").html(result.code)
                    }
                })
            })
        })
    </script>
</head>
<body>
<form action="/uaa/message/login" method="post" enctype="multipart/form-data">
    phone：<input type="text" id="phone" name="phone"><br>
    code：<input type="text" id="code" name="code">
    <input id="get_code" type="button" name="btn1" value="获取短信验证码">
    <input type="submit" value="验证"></input>
</form>
<div align="center">
    <a href="/uaa/oauth2/authorization/weibo">weibo</a><br>
</div>
<div id="result_code" align="center">sadasda</div>
</body>
</html>