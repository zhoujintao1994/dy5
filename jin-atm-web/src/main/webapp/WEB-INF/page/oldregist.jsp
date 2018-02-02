<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统--淘!你喜欢</title>

</head>

<body>
<h1>注册页面</h1>
<form id="registForm" method="post">
用户名:<input type="text" id="username" name="username">
密码:<input type="text" id="passWord" name="passWord">

<input type="button" value="注册" onclick="regist();">
</form>



</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>

<script type="text/javascript">

	  function regist() {
	     var param = {
			 username : $('#username').val(),
			 passWord : $('#passWord').val()
	 	};
	     
		    $.post('/user/regist.do',param, callback); 
	  }
	  
	  function callback(ajaxDTO, status) {
		  
		 alert(ajaxDTO.success);
			if (ajaxDTO.success) {
				window.location.href='/';
			} else {
				alert(ajaxDTO.message);
			}
			
		}
	  
	
</script>


</html>