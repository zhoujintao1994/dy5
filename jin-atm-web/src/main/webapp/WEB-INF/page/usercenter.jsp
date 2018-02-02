<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
				<div class="row  am-cf" id="cardData">
				
				</div>


				<div class="am-u-sm-12 am-u-md-12 am-u-lg-6">
					<div class="widget am-cf">
						<div class="widget-head am-cf">
							<div class="widget-title am-fl">最近十笔流水</div>
							<div class="widget-function am-fr"></div>
						</div>
						<div class="widget-body  widget-body-lg am-fr">

							<table width="100%"
								class="am-table am-table-compact am-table-bordered am-table-radius am-table-striped tpl-table-black "
								id="example-r">
								<thead>
									<tr>
										<th>卡号</th>
										<th>金额</th>
										<th>备注</th>
										<th>时间</th>

									</tr>
								</thead>
								<tbody id="flowData">
									<!-- more data -->
								</tbody>
							</table>

						</div>
					</div>
				</div>
			</div>



		</div>
	</div>

  <jsp:include page="common/footer.jsp"></jsp:include>




<script type="text/javascript">
	$(document).ready(function() {
		loadBankCard();
		loadFlowTen();
	});
	
	function upload() {
		$('#avatarForm').submit();
	}
	
	function loadAvatar() {
		$('#avatarImg').attr('src', '/user/showPic.do?' + new Date().getTime());
	}

	function loadFlowTen() {

		DY.post('/card/loadFLowTen.do', {}, function(data, status) {
			var msg = "";
			var result = data.data;

			for (var i = 0; i < result.length; i++) {
				var flow = result[i];

				msg += '<tr class="gradeX">';
				msg += '<td>' + flow.cardNum + '</td>';
				msg += '<td>' + flow.amount + '</td>';
				msg += '<td>' + flow.descript + '</td>';
				msg += '<td>' + flow.createTime + '</td>';
				msg += '</tr>';
			}

			$('#flowData').html(msg);

		}, function(data, state) {
			alert(data.message);
		});
	}

	function loadBankCard() {

		$.post('/card/loadBankCardNoPage.do', {}, success);

	}

	function success(data, status) {
		var msg = "";
		var result = data.data;

		for (var i = 0; i < result.length; i++) {
			var card = result[i];
			msg += '<div class="am-u-sm-12 am-u-md-6 am-u-lg-4">';
			msg += '<div class="widget widget-primary am-cf">';
			msg += '<div class="widget-statistic-header">';
			msg += card.cardNumber;
			msg += '</div>';
			msg += '<div class="widget-statistic-body">';
			msg += '<div class="widget-statistic-value">';
			msg += '￥' + card.sum;
			msg += '</div>';
			msg += '<div class="widget-statistic-description">';
			msg += '</div>';
			msg += '<span class="widget-statistic-icon am-icon-credit-card-alt"></span>';
			msg += '</div>';
			msg += '</div>';
			msg += '</div>';
		}

		$('#cardData').html(msg);

	}
	
</script>

</body>

<iframe name="avatarFrame" style="display: none;">
</iframe>

</html>