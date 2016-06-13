<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<!-- saved from url=(0044)http://i.rijigu.com/index.php/Index/register -->
<!DOCTYPE html PUBLIC "" "">
<HTML>
<HEAD>
<META content="IE=11.0000" http-equiv="X-UA-Compatible">

<META charset="UTF-8">
<TITLE>注册 - 育婴之家网</TITLE>
<META http-equiv="X-UA-Compatible" content="chrome=1">
<LINK href="/commons/rjg/core.css" rel="stylesheet">
<LINK href="/commons/rjg/style.css" rel="stylesheet">
<script type="text/javascript" src="/commons/js/jquery/jquery-1.7.2.min.js" ></script>
<!-- <base id="headbase" href="http://www.phpwind.net/" /> -->
<STYLE>
.main .thread_posts_list .st {
	font-size: 14px;
}
</STYLE>

<SCRIPT>
	//全局变量 Global Variables
	var GV = {
		JS_ROOT : 'http://i.rijigu.com/static/res/js/dev/', //js目录
		JS_VERSION : '20130531', //js版本号(不能带空格)
		JS_EXTRES : 'http://i.rijigu.com/static/themes/extres',
		TOKEN : 'a200ca91c5a23323', //token $.ajaxSetup data
		URL : {
			CRON_AJAX : '', //计划任务 后端输出执行
		}
	};
</SCRIPT>

<SCRIPT src="/commons/rjg/wind.js"></SCRIPT>
<LINK href="/commons/rjg/register.css" rel="stylesheet">
<META name="GENERATOR" content="MSHTML 11.00.9600.17801">
</HEAD>
<BODY>
	<DIV class="wrap">
		<HEADER class="header_wrap">
			<DIV class="header cc" id="J_header">
				<DIV class="logo">
					<A href="http://www.rijigu.com/"><IMG alt="日记谷日记网"
						src="/commons/rjg/logo.png"> </A>
				</DIV>
				<NAV class="nav_wrap">
					<DIV class="nav">
						<UL>
							<LI class="current"><A href="http://www.rijigu.com/"
								target="_blank">育婴之家</A></LI>
							<LI><A href="http://www.rijigu.com/diarys/" target="_blank">最新知识</A></LI>
							<LI><A href="http://www.rijigu.com/haowenzhang/"
								target="_blank">好文章</A></LI>
							<LI><A href="http://www.rijigu.com/subject/" target="_blank">最热阅读</A></LI>
						</UL>
					</DIV>
				</NAV>
				<DIV class="header_search" role="search">
					<FORM action="http://www.rijigu.com/search/" method="post"
						target="_blank">
						<INPUT name="kw" id="s" accesskey="s" aria-label="搜索关键词"
							type="text" placeholder="搜索育婴知识" value="" speech=""
							x-webkit-speech="">
						<BUTTON aria-label="搜索" type="submit">
							<SPAN>搜索</SPAN>
						</BUTTON>
						<INPUT name="csrf_token" type="hidden" value="eceab51f3afed16f">
					</FORM>
				</DIV>
				<DIV class="header_login">
					<A href="http://i.rijigu.com/index.php/Index/login" rel="nofollow">登录</A><A
						href="http://i.rijigu.com/index.php/Index/register" rel="nofollow">注册</A>
				</DIV>
			</DIV>
		</HEADER>
		<DIV class="tac"></DIV>
		<DIV class="main_wrap">
			<DIV class="box_wrap register cc">
				<H2 class="reg_head">欢迎注册成为 育婴之家 的会员</H2>
				<DIV class="reg_cont_wrap">
					<DIV class="reg_cont">
						<FORM id="J_register_form"
							action="/index.php/index/register?step=2" method="POST">
							<DIV class="reg_form">
								<DIV class="tips">
									<SPAN class="tips_icon"> 小提示：您还可以直接使用右侧的快捷登录方式注册 </SPAN>
								</DIV>
								<INPUT name="isbind" type="hidden" value="0">
								<DL>
									<DT>
										<LABEL for="J_reg_username">笔名：</LABEL>
									</DT>
									<DD>
										<SPAN class="must_red">*</SPAN><INPUT name="username"
											class="input length_4 J_reg_input" id="J_reg_username"
											aria-required="true" type="text" data-id="username">
									</DD>
									<DD class="dd_r" id="J_reg_tip_username" role="tooltip"
										aria-hidden="true"></DD>
								</DL>
								<DL>
									<DT>
										<LABEL for="J_reg_password">密码：</LABEL>
									</DT>
									<DD>
										<SPAN class="must_red">*</SPAN><INPUT name="password"
											class="input length_4" id="J_reg_password"
											aria-required="true" type="password" data-id="password">
									</DD>
									<DD class="dd_r" id="J_reg_tip_password" role="tooltip"
										aria-hidden="true"></DD>
								</DL>
								<DL>
									<DT>
										<LABEL for="J_reg_repassword">确认密码：</LABEL>
									</DT>
									<DD>
										<SPAN class="must_red">*</SPAN><INPUT name="repassword"
											class="input length_4" id="J_reg_repassword"
											aria-required="true" type="password" data-id="repassword">
									</DD>
									<DD class="dd_r" id="J_reg_tip_repassword" role="tooltip"
										aria-hidden="true"></DD>
								</DL>
								<DL>
									<DT>
										<LABEL for="J_reg_email">电子邮箱：</LABEL>
									</DT>
									<DD>
										<DIV class="fl mail_down" id="J_email_list"
											style="margin-top: 27px;"></DIV>
										<SPAN class="must_red">*</SPAN><INPUT name="email"
											class="input length_4" id="J_reg_email" aria-required="true"
											type="text" data-id="email" autocomplete="off">
									</DD>
									<DD class="dd_r" id="J_reg_tip_email" role="tooltip"
										aria-hidden="true"></DD>
								</DL>
								<DL>
									<DT>
										<LABEL for="J_reg_email">验证码：</LABEL>
									</DT>
									<DD>
										<SCRIPT src="/commons/rjg/get.js"
											type="text/javascript" async=""></SCRIPT>
									</DD>
									<DD class="dd_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;仅需拖动左侧滑块
										放置图片缺块位置即可</DD>
								</DL>
								<DL>
									<DT>&nbsp;</DT>
									<DD>
										<BUTTON class="btn btn_big btn_submit mr20" type="submit">同意以下协议并注册</BUTTON>
									</DD>
								</DL>
								<DIV class="agreements">
									<DIV>
										<A title="" class="s4" id="J_agreements_btn"
											href="http://www.rijigu.com/webmsg/2/" target="_blank"
											rel="nofollow">《本站服务协议内容》</A>
									</DIV>
								</DIV>
							</DIV>
							<INPUT name="csrf_token" type="hidden" value="a200ca91c5a23323">
						</FORM>
					</DIV>
				</DIV>
				<DIV class="reg_side">
					<DIV class="reg_side_cont">
						<P class="mb10">已经有帐号？</P>
						<P class="mb20">
							<A class="btn btn_big"
								href="http://i.rijigu.com/index.php/Index/login" rel="nofollow">立即登录</A>
						</P>
						<P class="mb20">
							<A class="btn btn_big" id="kuaijie" href="javascript:;"
								rel="nofollow">使用快捷登录</A>
						</P>
						<P class="mb20">
							<A
								onclick="window.open('http://i.rijigu.com/index.php/Qqlogin', 'oauth2Login' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes')"
								href="javascript:;"><IMG align="middle" alt="QQ登录"
								src="/commons/rjg/qqlogin.png" border="0"></A>
						</P>
						<P class="mb20">
							<A
								onclick="window.open('http://i.rijigu.com/index.php/Weibologin', 'oauth2Login' ,'height=525,width=585, toolbar=no, menubar=no, scrollbars=no, status=no, location=yes, resizable=yes')"
								href="javascript:;"><IMG align="middle" alt="微博登录"
								src="/commons/rjg/weibo.png" border="0"></A>
						</P>
					</DIV>
				</DIV>
			</DIV>
		</DIV>
		<SCRIPT>
			Wind.use('jquery', 'global', 'validate', 'emailAutoMatch', function() {

				//聚焦时默认提示
				var focus_tips = {
					username : '3-15位,支持中文、字母、数字、下划线和小数点',
					password : '长度6-16位；不能和用户名相同',
					repassword : '请再输入一遍您上面填写的密码',
					email : '需要发送验证邮件'
				};

				var register_form = $("#J_register_form"), reg_username = $('#J_reg_username'), //用户名表单
				reg_password = $('#J_reg_password'), //密码表单
				reg_tip_password = $('#J_reg_tip_password'); //密码提示区

				//validate插件修改了remote ajax验证返回的response处理方式；增加密码强度提示 passwordRank
				register_form.validate({
					//debug : true,
					errorPlacement : function(error, element) {
						//错误提示容器
						$('#J_reg_tip_' + element[0].name).html(error);
					},
					errorElement : 'span',
					errorClass : 'tips_icon_error',
					validClass : 'tips_icon_success',
					onkeyup : false,
					focusInvalid : false,
					rules : {
						username : {
							required : true,
							minlength : 3,
							maxlength : 15,
							remote : {
								url : "/index.php/index/ajax_chk_username",
								type : 'post',
								dataType : "json",
								data : {
									username : function() {
										return $("#J_reg_username").val();
									}
								}
							//返回的信息就是messagesss
							}
						},
						password : {
							required : true,
							minlength : 6,
							maxlength : 16,
							remote : {
								url : "/index.php/index/ajax_password",
								dataType : "json",
								type : 'post',
								data : {
									password : function() {
										return $("#J_reg_password").val();
									}
								}
							}
						},
						repassword : {
							required : true,
							equalTo : '#J_reg_password'
						},
						email : {
							required : true,
							email : true,
							remote : {
								url : "/index.php/index/ajax_chk_email",
								dataType : "json",
								type : 'post',
								data : {
									email : function() {
										return $("#J_reg_email").val();
									}
								}
							}
						}
					},
					highlight : false,
					unhighlight : function(element, errorClass, validClass) {
						var tip_elem = $('#J_reg_tip_' + element.name);
						if (element.value) {
							tip_elem.html('<span class="'+ validClass +'" data-text="text"><span>');
						}
					},
					onfocusin : function(element) {
						var id = element.name;
						$('#J_reg_tip_' + id).html('<span class="reg_tips" data-text="text">' + focus_tips[id] + '</span>');
						$(element).parents('dl').addClass('current');
					},
					onfocusout : function(element) {
						var _this = this;
						$(element).parents('dl').removeClass('current');

						if (element.name === 'email') {
							//邮箱匹配点击后，延时处理
							setTimeout(function() {
								element.value = $.trim(element.value);
								_this.element(element);
							}, 150);
						} else {

							if (element.name === 'password') {
								//防止重复绑定
								$(element).off('keyup');

								//失焦标识
								reg_tip_password.data('blur', 'blur');
							}
							_this.element(element);

						}

					},
					messages : {
						username : {
							required : '用户名不能为空',
							remote : '用户名已存在'
						},
						password : {
							required : '密码不能为空',
							remote : '密码过于简单'
						},
						repassword : {
							required : '确认密码不能为空',
							equalTo : '两次输入的密码不一致。请重新输入'
						},
						email : {
							required : '邮箱不能为空',
							email : '需要发邮件验证，请输入正确的电子邮箱地址',
							remote : '该电子邮箱已被注册，请更换别的邮箱'
						}
					}
				});

				//邮箱后缀匹配
				$('#J_reg_email').emailAutoMatch();

				register_form.find('dl:first input:text:visible').focus().parents('dl').addClass('current');

				$('#kuaijie').click(function() {
					Wind.Util.resultTip({
						elem : $(this),
						error : true,
						msg : '请点击下方使用QQ或微博直接登录',
						follow : true
					});
				})

			});
		</SCRIPT>
		<!--.main-wrap,#main End-->
		<DIV class="tac">
			<BR>
		</DIV>
		<DIV class="footer_wrap" style="text-align: center;">
			<%@ include file="../common/foot.jsp" %>
		</DIV>
	</DIV>
</BODY>
</HTML>
