<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<body data-type="index" class="theme-white">
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
									<div class="widget-title am-fl">银行卡开户</div>
									<div class="widget-function am-fr">
										<a href="javascript:;" class="am-icon-cog"></a>
									</div>
								</div>
								<div class="widget-body am-fr">

									<form class="am-form tpl-form-line-form">
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
												<button type="button" type="button"
													class="am-btn am-btn-primary tpl-btn-bg-color-success "
													onclick="openAccout();">开户</button>
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>

				</div>

			</div>
		</div>

		<div class="am-modal am-modal-alert" tabindex="-1" id="my-alert">
			<div class="am-modal-dialog">
				<div class="am-modal-hd"></div>
				<div class="am-modal-bd" id="successAlet">开户成功</div>
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


	<jsp:include page="common/footer.jsp"></jsp:include>

	<script type="text/javascript">
		function openAccout() {
			var param = {
				passWord : $('#passWord').val()
			};

			$.post('/card/openAccount.do', param, callback);

		}

		function callback(ajaxDTO, status) {
			alert(ajaxDTO.success);
			if (ajaxDTO.success) {
				$('#my-alert').on('close.modal.amui', function() {
					window.location.href = '/user/toUserCenter.do';
				});
				$('#my-alert').modal('toggle');
			} else {
				$('#errorAlert').html(ajaxDTO.message);
				$('#your-modal').modal('toggle');
			}

		}
	</script>

</body>

</html>