<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="left-sidebar">
	<!-- 用户信息 -->
	<div class="tpl-sidebar-user-panel">
		<div class="tpl-user-panel-slide-toggleable">


			<div class="am-form-group am-form-file">
                   <div class="tpl-user-panel-profile-picture">
                    <img id="avatarImg" src="/user/showPic.do" alt="" onerror="javascript:this.src='/images/dayuan.jpg';">
                </div>


				<form id="avatarForm" method="POST" enctype="multipart/form-data"
					action="/user/upload.do" target="avatarFrame">
					<input id="doc-form-file" type="file" name="upfile"
						onchange="upload();">
				</form>
			</div>

			<a href="javascript:;" class="tpl-user-panel-action-link"> <span></span>
			</a>
		</div>
	</div>

	<!-- 菜单 -->
	<ul class="sidebar-nav">

		<li class="sidebar-nav-link"><a href="/user/toUserCenter.do"
			class="active"> <i class="am-icon-home sidebar-nav-link-logo"></i>
				首页
		</a></li>
		<li class="sidebar-nav-link"><a
			href="/user/toOpenAccount.do" id="openaccountHref">
				<i class="am-icon-wpforms sidebar-nav-link-logo"></i> 开户
		</a></li>
		<li class="sidebar-nav-link"><a href="/card/toDraw.do"
			id="drawHref"> <i class="am-icon-wpforms sidebar-nav-link-logo"></i>
				取款
		</a></li>
		<li class="sidebar-nav-link"><a href="/card/toDeposit.do"
			id="depositHref"> <i
				class="am-icon-wpforms sidebar-nav-link-logo"></i> 存款

		</a></li>
		<li class="sidebar-nav-link"><a href="/card/toTransfer.do"> <i
				class="am-icon-wpforms sidebar-nav-link-logo"></i> 转账

		</a></li>

		<li class="sidebar-nav-link"><a href="/card/toFlow.do"
			id="flowHref"> <i class="am-icon-bar-chart sidebar-nav-link-logo"></i>
				流水

		</a></li>

	</ul>
</div>