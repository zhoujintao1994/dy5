<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统--淘!你喜欢</title>

</head>

<body>
<h1>修改页面</h1>
<form id="registForm" method="post">
原密码:<input type="text" id="oldPassword" name="oldPassword">
新密码:<input type="text" id="newPassword" name="newPassword">
重复新密码:<input type="text" id="comfirmPassword" name="comfirmPassword">

<input type="button" value="修改" onclick="modifyPwd();">
</form>



</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>

<script type="text/javascript">

	  function modifyPwd() {
	     var param = {
	    		 oldPassword : $('#oldPassword').val(),
	    		 newPassword : $('#newPassword').val(),
	    		 comfirmPassword : $('#comfirmPassword').val()
	 	};
	     
	    $.post('/userInfo/modifyPwd.do',param, callback); 
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