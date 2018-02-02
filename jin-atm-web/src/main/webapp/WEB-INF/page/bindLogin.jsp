<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Amaze UI Admin index Examples</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="/i/favicon.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="/css/amazeui.min.css" />
<link rel="stylesheet" href="/css/amazeui.datatables.min.css" />
<link rel="stylesheet" href="/css/app.css">


</head>

<body data-type="login" class="theme-white">

	<div class="am-g tpl-g">
		<!-- 风格切换 -->

		<div class="tpl-login">
			<div class="tpl-login-content">
				<div class="tpl-login-logo">
					<img alt="" src="${headimgurl }" width="200px" height="200px">
				</div>

				<form class="am-form tpl-form-line-form">
					<div class="am-form-group">
						<span>微信用户：${nickname }</span>
					</div>

					<form class="am-form tpl-form-line-form">
						<div class="am-form-group">
							<input type="text" class="tpl-form-input" id="username"
								placeholder="请输入账号">

						</div>

						<div class="am-form-group">
							<input type="password" class="tpl-form-input" id="passWord"
								placeholder="请输入密码">
						</div>

						<div class="am-form-group">

							<button type="button"
								class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
								onclick="login();">登录</button>

						</div>
			</div>
		</div>
	</div>


	<script src="/js/jquery.js"></script>
	<script src="/js/theme.js"></script>
	<script src="/js/amazeui.min.js"></script>
	<script src="/js/amazeui.datatables.min.js"></script>
	<script src="/js/dataTables.responsive.min.js"></script>
	<script src="/js/app.js"></script>
	<script src="/js/vue.js"></script>
	<script src="/js/reconnecting-websocket.js"></script>
	<script type="text/javascript" src="/js/dayuanit.js"></script>

	<script type="text/javascript">
		function login() {
			
			var param2 = {
				username : $('#username').val(),
				passWord : $('#passWord').val()
			

			};
			

			//$('#loginForm').submit();

			//alert(param2.username + '-' + param2.password);

			$.post('/wxAuthen/wxLogin.do', param2, callback);
		}

		function callback(data, status) {

			//data = eval('(' + data + ')');
	

			alert(data.success);
			if (data.success) {
					window.location.href = "/user/toUserCenter.do";
			} else {
				alert(data.message);
			}
		}

		//function loadCaptcha() {
		//	$('#codeImg').attr('src', '/getCaptcha.do?xx=' + new Date().getTime());
		//}
	</script>
</body>

</html>