<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统--淘!你喜欢</title>

</head>

<body>
<h1>转账页面</h1>
<form action="/card/transfer.do" method="post">
转出卡号:${cardNumber }
<input type="hidden" name="outCardNum" value="${cardNumber }">
密码:<input type="text" name="passWord">
转入卡号：<input type="text" name="inCardNum">
金额:<input type="text" name="sum">
<input type="submit" value="转账">
</form>

</body>


</html>