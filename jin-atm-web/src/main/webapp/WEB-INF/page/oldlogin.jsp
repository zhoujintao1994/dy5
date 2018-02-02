<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>ATM系统--淘!你喜欢</title>

</head>

<body>
	<h1>登录页面</h1>
	<form id="loginForm" method="post">
		用户名:<input type="text" id="username" name="username"> 密码:<input
			type="text" id="passWord" name="passWord"> <input
			type="button" value="登录" onclick="login()">
	</form>

	<a href="/user/toPage.do?pageName=regist">注册</a>
	<div id="usercenter"></div>
</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
<script type="text/javascript" src="/js/json2.js"></script>

<script type="text/javascript">
	function login() {

		var param = {
			username : $('form input[name="username"]').val(),
			passWord : $('form input[name="passWord"]').val()
		};

		var param2 = {
			username : $('#username').val(),
			passWord : $('#passWord').val()
		};

		//$('#loginForm').submit();

		//alert(param2.username + '-' + param2.password);

		$.post('/user/login.do', param2, callback);
	}

	function callback(data, status) {

		//data = eval('(' + data + ')');

		var ajaxDTO = JSON.parse(data);

		alert(ajaxDTO.success);
		if (ajaxDTO.success) {
			window.location.href = '/user/toUserCenter.do';
		} else {
			alert(ajaxDTO.message);
		}
	}
</script>


</html>