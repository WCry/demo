<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org" xmlns:sec="https://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
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
<form th:action="uaa/message/login" th:align="center" method="post">
    phone：<input type="text" id="phone" name="phone"><br>
    code：<input type="text" id="code" name="code">
    <input id="get_code" type="button" name="btn1" value="获取短信验证码">
    <input type="button" value="code"></input>
</form>
<div th:id="result_code" align="center">sadasda</div>
</body>
</html>