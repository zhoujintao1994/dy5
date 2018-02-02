<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统--淘!你喜欢</title>

</head>

<body>
<h1>取钱页面</h1>
<form action="/card/draw.do" method="post">
卡号:${cardNumber }
<input type="hidden" name="cardNumber" value="${cardNumber }">
密码:<input type="text" name="passWord">
金额:<input type="text" name="sum">
<input type="submit" value="取钱">
</form>

</body>


</html>