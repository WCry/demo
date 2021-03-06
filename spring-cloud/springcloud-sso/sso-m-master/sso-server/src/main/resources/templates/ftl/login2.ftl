<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8">
    <title>登录界面</title>
    <link rel="stylesheet" href="/uaa/css/reset.css"/>
    <link rel="stylesheet" href="/uaa/css/common.css"/>
    <link rel="stylesheet" href="/uaa/css/font-awesome.min.css"/>
</head>
<body>
<div class="wrap login_wrap">
    <div class="content">
        <div class="logo"></div>
        <div class="login_box">

            <div class="login_form">
                <div class="login_title">
                    登录
                </div>
                <form th:action="@{/message/login}" th:align="center" method="post">
                    phone：<input type="text" id="phone" name="phone"><br>
                    code：<input type="text" id="code" name="code">
                    <a th:id="get_code">获取短信验证码</a><br>
                    <button th:id="code_login">code</button>
                </form>
                <div th:id="result_code" align="center"></div>

                <form action="/uaa/authentication/form" method="post">
                    <#--<form action="/uaa/authentication/form" method="post">-->

                    <div class="form_text_ipt">
                        <input name="username" type="text" placeholder="手机号/邮箱" value="admin">
                    </div>
                    <div class="ececk_warning"><span>手机号/邮箱不能为空</span></div>
                    <div class="_warning"><span>${message!''}</span></div>
                    <div class="form_text_ipt">
                        <input name="password" type="password" placeholder="密码" value="123456">
                    </div>
                    <div class="ececk_warning"><span>密码不能为空</span></div>
                    <div class="form_check_ipt">
                        <div class="left check_left">
                            <label><input name="remember-me" type="checkbox"> 下次自动登录</label>
                        </div>
                        <div class="right check_right">
                            <a href="#">忘记密码</a>
                        </div>
                    </div>
                    <div class="form_btn">
                        <button type="submit">登录</button>
                    </div>
                    <div class="form_reg_btn">
                        <span>还没有帐号？</span><a href="/register">马上注册</a>
                    </div>
                </form>
                <div th:align="center">
                    <a th:href="@{/oauth2/authorization/weibo}">weibo</a><br>
                </div>
                <div class="other_login">
                    <div class="left other_left">
                        <span>其它登录方式</span>&nbsp;&nbsp;<a href="/authentication/mobilePage">短信登录</a>
                    </div>
                    <div id="wixin" style="height: 100px;width:100px"></div>
                    <div class="right other_right">
                        <a href="/login/qq"><i class="fa fa-qq fa-2x"></i></a>
                        <a href="/login/weixin"><i class="fa fa-weixin fa-2x"></i></a>
                        <a href="/login/weibo"><i class="fa fa-weibo fa-2x"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(function () {
        $("#get_code").click(function () {
            $.get({
                url: "/message/code?phone=" + $("#phone").val(),
                success: function (result) {
                    $("#result_code").html(result.code)
                }
            })
        })
    })

    function setWxerwma() {
        const wxElement = document.body.appendChild(s)
        wxElement.onload = function () {
            var obj = new WxLogin({
                id: 'wixin', // 需要显示的容器id
                appid: '', // 公众号appid wx*******
                scope: 'snsapi_login', // 网页默认即可
                redirect_uri: encodeURIComponent(''), // 授权成功后回调的url
                state: Math.ceil(Math.random() * 1000), // 可设置为简单的随机数加session用来校验
                style: 'black', // 提供"black"、"white"可选。二维码的样式
                href: '' // 外部css文件url，需要https
            })
        }
    }
</script>
<script type="text/javascript" src="/uaa/js/jquery.min.js"></script>
<script type="text/javascript" src="/uaa/js/common.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
</body>
</html>
