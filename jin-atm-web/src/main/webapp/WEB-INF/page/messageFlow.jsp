<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<body data-type="index" class="theme-white">
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<jsp:include page="common/header.jsp"></jsp:include>
		<!-- 风格切换 -->

		<!-- 侧边导航栏 -->
		<jsp:include page="common/left.jsp"></jsp:include>

		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">

			<div class="row-content am-cf">
				<div class="row  am-cf">

					<div class="row">

						<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">消息列表</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr"></div>

							</div>

							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl"></div>
									<div class="widget-function am-fr"></div>
								</div>
								<div class="widget-body  widget-body-lg am-fr">

									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="appMessage">
										<thead>
											<tr>
												<th>序号</th>
												<th>消息</th>
												<th>时间</th>
											</tr>
										</thead>
										<tbody>
											<tr class="gradeX" v-for="message in messageList">
												<td>{{message.id}}</td>
												<td>{{message.msg}}</td>
												<td>{{message.createTime}}</td>

											</tr>

											<!-- more data -->
										</tbody>
									</table>

									<ul class="am-pagination">
										<li><a href="###" onclick="fist();">首页 &raquo;</a></li>
										<li><a href="###" onclick="pre();">&laquo; 上一页</a></li>
										<li><a href="###" onclick="next();">下一页 &raquo;</a></li>
										<li><a href="###" onclick="tail();">尾页 &raquo;</a></li>
										<li id="fenyeInfo">1/20</li>
									</ul>

								</div>
							</div>

							<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
								<div class="am-modal-dialog">
									<div class="am-modal-hd"></div>
									<div class="am-modal-bd" id="successAlet">查看流水成功</div>
									<div class="am-modal-footer">
										<span class="am-modal-btn">确定</span>
									</div>
								</div>
							</div>

							<div class="am-modal am-modal-no-btn" tabindex="-1"
								id="your-modal">
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

			</div>



		</div>
	</div>
</body>
<jsp:include page="common/footer.jsp"></jsp:include>

<script type="text/javascript">
	var currentPageNum = 1;
	var totalPageNum = 0;

	var app = {};

	$(document).ready(function() {

		app = new Vue({
			el : '#appMessage',
			data : {
				messageList : []
			}
		})

		loadAllMessage();

	});

	
	function loadAllMessage() {
		var param = {
				currentPageNum : currentPageNum,
		};

		$.post('/message/messageFlow.do', param, callback);

	}

	function callback(ajaxDTO, status) {
		alert(ajaxDTO.success);
		if (ajaxDTO.success) {
			
			var result = ajaxDTO.data.obj;
			totalPageNum = ajaxDTO.data.totalPageNum;
			$('#fenyeInfo').html(currentPageNum + '/' + totalPageNum);
			app.messageList = result;
		} else {
			alert('sx');;
		}
	}

	function next() {
		if (currentPageNum == totalPageNum) {
			return false;
		}

		currentPageNum= parseInt(currentPageNum) + 1;
		loadAllMessage();
	}
	
	

	function pre() {

		if (currentPageNum == 1) {
			return false;
		}

		currentPageNum = parseInt(currentPageNum) - 1;
		loadAllMessage();
	}

	function fist() {
		currentPageNum = 1;
		loadAllMessage();
	}

	function tail() {
		currentPageNum = totalPageNum;
		loadAllMessage();
	}
</script>

</html>