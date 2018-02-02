<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>

<head>
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<title>ATM系统--淘!你喜欢</title>

</head>

<body>
	<h1>个人中心页面</h1>

	<img src="/user/showPic.do" width="50px" height="50px">
	<h1>用户名：${user.username }</h1>

	<a href="/user/toPage.do?pageName=openAccount">开户</a>
	<a href="/user/toPage.do?pageName=upload">上传头像</a>
	<a href="/userInfo/toPage.do?pageName=modifyPwd">修改密码</a>
	<table id="cardTable">
	
	</table>


	<button onclick="fist();">首页</button>
			<button onclick="pre();">上一页</button>
			<button onclick="next();">下一页</button>
			<button onclick="tail();">尾页</button>
		<span id="pageInfo">3/8</span>
	</body>

<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>


<script type="text/javascript">

var currentPage = 1;

var totalPageNum = 0;

function next() {
	if (currentPage == totalPageNum) {
		return false;
	}
	
	currentPage = parseInt(currentPage) + 1;
	loadBankCard();
}

function pre() {
	
	if (currentPage == 1) {
		return false;
	}
	
	currentPage = parseInt(currentPage) - 1;
	loadBankCard();
}

function fist() {
	currentPage = 1;
	loadBankCard();
}

function tail() {
	currentPage = totalPageNum;
	loadBankCard();
}

function loadBankCard() {
	
	$.post('/card/loadBankCard.do',{
		currentPage : currentPage
		}, function(data, status) {
			
			
			if (data.success) {
				var result = data.data.obj;
				var msg = '<tr><td>卡号</td><td>余额</td><td>时间</td><td>操作</td></tr>';
				for (var i=0; i<result.length;i++) {
					
					msg += '<tr>';
					msg += '<td>'+result[i].cardNumber+'</td>';
					msg += '<td>'+result[i].sum+'</td>';
					msg += '<td>'+result[i].grearTime+'</td>';
					msg += '<td>';
					msg += '<a href="/card/toFlow.do?cardNumber='+result[i].cardNumber+'">流水</a> ';
					msg += '<a href="/card/toDeposit.do?cardNumber='+result[i].cardNumber+'">存钱</a> ';
					msg += '<a href="/card/toDraw.do?cardNumber='+result[i].cardNumber+'">取钱</a> ';
					msg += '<a href="/card/toTransfer.do?cardNumber='+result[i].cardNumber+'">转账</a> ';
					msg += '<a href="/card/deleteCard.do?cardId='+result[i].id+'">删除</a> ';
					msg += '</td>';
					msg += '</tr>';
							
				}
				
				totalPageNum = data.data.totalPageNum;				
				$('#cardTable').html(msg);
				$('#pageInfo').html(currentPage+ '/' + totalPageNum);
				
			} else {
				alert(data.message);
			}
	});
}

loadBankCard();
	
</script>
