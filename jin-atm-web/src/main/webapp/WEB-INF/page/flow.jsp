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
									<div class="widget-title am-fl">流水操作</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">

									<form class="am-form tpl-form-line-form">


										<div class="am-form-group">
											<label for="user-phone" class="am-u-sm-3 am-form-label">银行卡
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{searchBox: 1}"
													style="display: none;" id="cardNumber">
													<option value="no">请选择银行卡</option>
													<option value="b">622***009</option>
													<option value="o">622***007</option>
												</select>
												<button type="button"
													class="am-btn am-btn-default am-radius" onclick="flow();">查询</button>
											</div>

										</div>



										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">密码
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<input type="passWord" class="tpl-form-input" id="passWord"
													placeholder="请输入银行卡密码"> <small></small>
											</div>
										</div>


									</form>


									<div class="widget am-cf">
										<div class="widget-head am-cf">
											<div class="widget-title am-fl"></div>
											<div class="widget-function am-fr"></div>
										</div>
										<div class="widget-body  widget-body-lg am-fr">

											<table width="100%"
												class="am-table am-table-compact am-table-striped tpl-table-black "
												id="appFlow">
												<thead>
													<tr>
														<th>卡号</th>
														<th>金额</th>
														<th>备注</th>
														<th>时间</th>
													</tr>
												</thead>
												<tbody>
													<tr class="gradeX" v-for="flow in flowList">
														<td>{{flow.cardNum}}</td>
														<td>{{flow.amount}}</td>
														<td>{{flow.descript}}</td>
														<td>{{flow.creatTime}}</td>

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

									<div class="am-modal am-modal-alert" tabindex="-1"
										id="my-alert">
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
	$(document).ready(function() {
		loadBankCard();

	});

	var currentPage = 1;
	var totalPageNum = 0;

	var app = {};

	app = new Vue({
		el : '#appFlow',
		data : {
			flowList : []
		}
	})

	function exportFlow() {
		$('#export_pwd').val($('#passWord').val());
		$('#exportForm').submit();

	}

	function next() {
		if (currentPage == totalPageNum) {
			return false;
		}

		currentPage = parseInt(currentPage) + 1;
		flow();
	}

	function pre() {

		if (currentPage == 1) {
			return false;
		}

		currentPage = parseInt(currentPage) - 1;
		flow();
	}

	function fist() {
		currentPage = 1;
		flow();
	}

	function tail() {
		currentPage = totalPageNum;
		flow();
	}

	function flow() {
		var param = {
			currentPage : currentPage,
			cardNumber : $('#cardNumber').val(),
			passWord : $('#passWord').val()

		};

		$.post('/card/flow.do', param, callback);

	}

	function callback(ajaxDTO, status) {
		alert(ajaxDTO.success);
		if (ajaxDTO.success) {
			$('#my-alert').on('close.modal.amui', function() {
			});
			$('#my-alert').modal('toggle');
			var result = ajaxDTO.data.obj;
			totalPageNum = ajaxDTO.data.totalPageNum;
			$('#fenyeInfo').html(currentPage + '/' + totalPageNum);
			app.flowList = result;
		} else {
			$('#errorAlert').html(ajaxDTO.message);
			$('#your-modal').modal('toggle');
		}
	}

	function loadBankCard() {

		$.post('/card/loadBankCardNoPage.do', {}, success);

	}

	function success(data, status) {
		var msg = '<option value="no">请选择银行卡</option>';
		var result = data.data;

		for (var i = 0; i < result.length; i++) {
			var card = result[i];
			msg += '<option value="'+card.cardNumber+'">' + card.cardNumber
					+ '</option>';
		}

		$('select').html(msg);

	}
</script>

</html>