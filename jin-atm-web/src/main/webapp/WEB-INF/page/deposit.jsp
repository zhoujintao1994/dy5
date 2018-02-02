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
									<div class="widget-title am-fl">存款操作</div>
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
													<option value="b"></option>
													<option value="o"></option>
												</select>

											</div>
										</div>

										<div class="am-form-group">
											<label for="user-name" class="am-u-sm-3 am-form-label">金额
												<span class="tpl-form-line-small-title"></span>
											</label>
											<div class="am-u-sm-9">
												<input type="text" class="tpl-form-input" id="sum"
													placeholder="存款金额"> <small></small>
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





										<div class="am-form-group">
											<div class="am-u-sm-9 am-u-sm-push-3">
												<button type="button"
													class="am-btn am-btn-primary tpl-btn-bg-color-success "
													onclick="deposit();">存款</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>


					<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
						<div class="am-modal-dialog">
							<div class="am-modal-hd"></div>
							<div class="am-modal-bd" id="successAlet">存款成功</div>
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
					
					<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
						<div class="am-modal-dialog">
							<div class="am-modal-hd"></div>
							<div class="am-modal-bd" id="successAlet">存款成功</div>
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
							<div class="am-modal-bd" id="errorAlert">存款失败</div>


						</div>
					</div>
					<jsp:include page="common/footer.jsp"></jsp:include>

					<script type="text/javascript">
						$(document).ready(function() {
							loadBankCard();

						});

						function deposit() {
							var param = {
								sum : $('#sum').val(),
								cardNumber : $('#cardNumber').val(),
								passWord : $('#passWord').val()
							};

							$.post('/card/deposit.do', param, callback);

						}

						function callback(ajaxDTO, status) {
							alert(ajaxDTO.success);
							if (ajaxDTO.success) {
								$('#my-alert').on('close.modal.amui', function() {
									window.location.href = "/user/toUserCenter.do";
								});
								$('#my-alert').modal('toggle');
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
								msg += '<option value="'+card.cardNumber+'">'
										+ card.cardNumber + '</option>';
							}

							$('select').html(msg);

						}
					</script>
</body>

</html>