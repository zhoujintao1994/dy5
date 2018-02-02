<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>

<head>
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<title>ATM系统--淘!你喜欢</title>

</head>

<body>
<h1>流水页面2</h1>
<form id="flowForm"  action="/card/flow.do" method="post">
卡号:${cardNumber }
<input type="hidden" name="cardNumber" id="cardNumber" value="${cardNumber }">
密码:<input type="text" name="passWord" id="passWord" value="${passWord }">
<input type="button" value="查询流水" onclick="loadFlow();">
</form>

<a href="/user/toUserCenter.do">返回个人中心</a>

<form id="exportForm" action="/card/downFlow.do" method="post">
<input type="hidden" name="cardNumber" value="${cardNumber }">
<input type="hidden" id="export_pwd" name="passWord">
<input type="button" value="导出" onclick="exportFlow();">
</form>

<table id="flowTable">
</table>
		
		<button onclick="fist();">首页</button>
		<button onclick="pre();">上一页</button>
		<button onclick="next();">下一页</button>
		<button onclick="tail();">尾页</button>
		<span id="pageInfo">1/0</span>

</body>
	
	<script type="text/javascript" src="/js/jquery-3.2.1.js"></script>
	<script type="text/javascript" src="/js/json2.js"></script>
	
<script type="text/javascript">

var currentPage = 1;
var totalPageNum = 0;

function exportFlow() {
	$('#export_pwd').val($('#passWord').val());
	$('#exportForm').submit();
}

function next() {
	if (currentPage == totalPageNum) {
		return false;
	}
	
	currentPage = parseInt(currentPage) + 1;
	loadFlow();
}

function pre() {
	
	if (currentPage == 1) {
		return false;
	}
	
	currentPage = parseInt(currentPage) - 1;
	loadFlow();
}

function fist() {
	currentPage = 1;
	loadFlow();
}

function tail() {
	currentPage = totalPageNum;
	loadFlow();
}

function loadFlow() {
	$.post('/card/flow.do',{
		currentPage : currentPage,
		cardNumber : $('#cardNumber').val(),
		passWord : $('#passWord').val()
		}, function(ajaxDTO, status) {
			
			
			if (!ajaxDTO.success) {
				alert(ajaxDTO.message);
				return false;
			}
			
			var result = ajaxDTO.data.obj;
			var msg = '<tr><td>卡号</td><td>金额</td><td>备注</td><td>时间</td></tr>';
			for (var i=0; i<result.length;i++) {
				msg += '<tr>';
				msg += '<td>'+result[i].cardNum+'</td>';
				msg += '<td>'+result[i].amount+'</td>';
				msg += '<td>'+result[i].descript+'</td>';
				msg += '<td>'+result[i].createTime+'</td>';
				msg += '</tr>';
			}
			
			totalPageNum = ajaxDTO.data.totalPageNum;
		
			$('#flowTable').html(msg);
			$('#pageInfo').html(currentPage+ '/' + totalPageNum);
			
		}
	);
}
	
</script>


</html>