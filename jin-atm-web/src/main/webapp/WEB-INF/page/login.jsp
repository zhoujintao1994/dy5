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
				<div class="tpl-login-logo"></div>



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
						  <input type="text" class="tpl-form-input" id="code" placeholder="请输入验证码">
						  <img id="codeImg" alt="" src="/getCaptcha.do" onclick="loadCaptcha();">
					</div>
					



					<div class="am-form-group">

						<button type="button"
							class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn"
							onclick="login();">登录</button>

					</div>
					<a href="/wxAuthen/toWxLogin.do">微信登录</a>
					<a href="/user/toRegist.do">注册</a>
				</form>

				<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
					<div class="am-modal-dialog">
						<div class="am-modal-hd"></div>
						<div class="am-modal-bd" id="successAlet">登录成功</div>
						<div class="am-modal-footer">
							<span class="am-modal-btn">确定</span>
						</div>
					</div>
				</div>

				<div class="am-modal am-modal-no-btn" tabindex="-1" id="your-modal">
					<div class="am-modal-dialog">
						<div class="am-modal-hd">
							<a href="javascript: void(0)" class="am-close am-close-spin"
								data-am-modal-close>&times;</a>
						</div>
						<div class="am-modal-bd" id="errorAlert"></div>

					</div>
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
	<script type="text/javascript" src="/js/dayuanit.js"></script>

	<script type="text/javascript">
		function login() {

			var param = {
				username : $('form input[name="username"]').val(),
				passWord : $('form input[name="passWord"]').val()
			};

			var param2 = {
				username : $('#username').val(),
				passWord : $('#passWord').val(),
				code : $('#code').val()
			};

			//$('#loginForm').submit();

			//alert(param2.username + '-' + param2.password);

			$.post('/user/login.do', param2, callback);
		}

		function callback(data, status) {

			//data = eval('(' + data + ')');

			alert(data.success);
			if (data.success) {
				$('#my-alert').on('close.modal.amui', function() {
					window.location.href = "/user/toUserCenter.do";
				});
				$('#my-alert').modal('toggle');

			} else {
				$('#errorAlert').html(data.message);
				$('#your-modal').modal('toggle');
			}
		}
		
		function loadCaptcha() {
			$('#codeImg').attr('src', '/getCaptcha.do?xx=' + new Date().getTime());
		}
	</script>
</body>

</html>