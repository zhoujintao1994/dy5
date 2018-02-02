<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script src="/js/jquery.js"></script>
<script src="/js/theme.js"></script>
<script src="/js/amazeui.min.js"></script>
<script src="/js/amazeui.datatables.min.js"></script>
<script src="/js/dataTables.responsive.min.js"></script>
<script src="/js/app.js"></script>
<script src="/js/vue.js"></script>
<script src="/js/reconnecting-websocket.js"></script>
<script type="text/javascript" src="/js/dayuanit.js"></script>

<script>
		var ws = new WebSocket("ws://127.0.0.1:8080/transferInfo")
		ws.onopen = function()
		{
			console.log("webSocket已连接");
		};
		
		ws.onmessage = function(evt) 
		{  
			console.log('服务器端发送的消息：'+ evt.data); loadMessage();
		};
		
		ws.onclose = function(evt)   
		{	 
		console.log("close"); 
		};
		
		ws.onerror = function(evt) 	  
		{  
			console.log("error"); 
		};	
		
		$(document).ready(function() 
		{
			loadMessage();
			$('#messageLi').click(reflush); //点击刷新
		});
		
		var messageApp = new Vue({
			el : '#messageDiv',
			data : {
				unReadMessages : []   //将dataxxxxx.data.list丢入该数组 
			}
		});
		
		function reflush() {
			var messageDatas = messageApp.unReadMessages;
			
			var startTime = new Date().getTime();
			
			for (var i=0;i<messageDatas.length; i++) {
				var result = messageDatas[i];
				result.time = parseInt((startTime-result.createTime)/1000);
				result.filed = '秒之前';
				if (result.time > 60) {
					result.time = parseInt((startTime-result.createTime)/1000/60);
					result.filed = '分钟之前';
					
					if (result.time > 60) {
						result.time = parseInt((startTime-result.createTime)/1000/60/60);
						result.filed = '小时之前';
					}
				}
				Vue.set(messageApp.unReadMessages, i, result);
			}
		}
		
		function loadMessage() {
			DY.post('/message/messageInfo.do', {}, function(dataxxxxx, state) {

				var countMessage = dataxxxxx.data.countMessage;

				messageApp.unReadMessages = dataxxxxx.data.list;
				
				$('#countMessage').html(countMessage);

			}, function(data, state) {
			});
		}
</script>

