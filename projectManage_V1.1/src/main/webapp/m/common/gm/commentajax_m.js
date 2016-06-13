// JavaScript Document
//表情部分
/*
var M_contentId = $("META[name=contentid]").attr("content");
(function() {
	var mojs1 = document.createElement('script');
	mojs1.type = 'text/javascript';
	mojs1.async = true;
	mojs1.src = 'http://motions.gmw.cn/show/' + M_contentId;
	var smo1 = document.getElementsByTagName('script')[0];
	smo1.parentNode.insertBefore(mojs1, smo1);
	*//*
	var mojs2 = document.createElement('script');
	mojs2.type = 'text/javascript';
	mojs2.async = true;
	mojs2.src = 'http://motions.gmw.cn/getotal/' + M_contentId;
	var smo2 = document.getElementsByTagName('script')[0];
	smo2.parentNode.insertBefore(mojs2, smo2);
	*/
//})();
//评论部分
/*
if(document.getElementById('comment-userName')){
    document.getElementById('comment-userName').onkeyup = function() {
        if (this.value !== '') {
            document.getElementById(this.id + "-tips").style.display = 'none';
        } else {
            document.getElementById(this.id + "-tips").style.display = 'block';
        }
    }
}
if(document.getElementById('comment-userPwd')){
    document.getElementById('comment-userPwd').onkeyup = function() {
        if (this.value !== '') {
            document.getElementById(this.id + "-tips").style.display = 'none';
        } else {
            document.getElementById(this.id + "-tips").style.display = 'block';
        }
    }
}
var local_url = window.location.href;
var dizhi1 = local_url.split("/");
var dzlen = dizhi1.length;
var dizhi_1 = dizhi1[(dzlen - 3)];
var dizhi_2 = dizhi1[(dzlen - 2)];
var articleid1 = dizhi1[(dzlen - 1)].split("_");
var articleid2 = articleid1[1].split(".htm");
var articleid = articleid2[0];
if (dzlen == 7) {
	var uurl = "../../../pinglun/" + dizhi_1 + "/" + dizhi_2 + "/pinglun_" + articleid + ".html";
} else {
	var uurl = "../../pinglun/" + dizhi_1 + "/" + dizhi_2 + "/pinglun_" + articleid + ".html";
}
//获得二级域
var demon2 = dizhi1[2].split(".gmw.cn");
var demon = demon2[0];
pinglun_ajaxFunction(demon, uurl);
/**
 * AJAX 读取评论
 * demon 域名，即栏目
 * url 评论文件的路径
 */
/*
function pinglun_ajaxFunction(demon, url) {
	var xmlHttp;
	try {
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("您的浏览器不支持AJAX！");
				return false;
			}
		}
	}
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			//if(xmlHttp.responseText.indexOf("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">")<0&&xmlHttp.responseText!="")
			if (xmlHttp.responseText.indexOf("<div id=\"pinglun_title\">") >= 0) {
				if (document.getElementById('pinglun') != null) {
					document.getElementById('pinglun').style.display = "block";
					document.getElementById('pinglun').innerHTML = xmlHttp.responseText;
					var pinglun_num1 = xmlHttp.responseText.split("<div id=\"pinglun_title_1\">");
					var pinglun_num2 = pinglun_num1[1].split("</div>");
					var pinglun_num = pinglun_num2[0];
					var pinglun_num = pinglun_num.replace("共", "共<a href=\"javascript:void(0);\" onClick=\"Toresult();\" class=\"comment-actCount\">");
					var pinglun_num = pinglun_num.replace("条评论", "</a>条评论");
					document.getElementById('pinglun_num').innerHTML = pinglun_num + "<span id=\"pinglun_reg1\" class=\"info-line\">|</span><a href=\"javascript:void(0);\" onClick=\"ToReg_m();\" id=\"pinglun_reg2\" target=\"_self\">注册</a>";
				}
			} else if (xmlHttp.responseText == "pinglun_" + articleid + ".html_auto") {
				var goto = 1;
			} else {
				mkpinglundirfile(demon + url);
			}
		}
	}
	xmlHttp.open("POST", url, true);
	xmlHttp.send(null);
}
/**
 * 匿名评论
 */
/*
function nimingpl() {
	var t = document.getElementById('ifniming');
	if (t.checked == true) {
		document.getElementById('comment-userName').disabled = "disabled";
		document.getElementById('comment-userName-tips').style.display = "none";
		document.getElementById('comment-userName').style.display = "none";
		document.getElementById('comment-userPwd-tips').style.display = "none";
		document.getElementById('comment-userPwd').style.display = "none";
		document.getElementById('niming_gmwy').style.display = "inline";
	} else {
		document.getElementById('comment-userName').disabled = "";
		document.getElementById('comment-userName-tips').style.display = "inline";
		document.getElementById('comment-userName').style.display = "inline";
		document.getElementById('comment-userName-tips').style.display = "inline";
		document.getElementById('comment-userPwd-tips').style.display = "inline";
		document.getElementById('comment-userPwd').style.display = "inline";
		document.getElementById('niming_gmwy').style.display = "none";
		if (document.getElementById('comment-userName').value !== '') {
			document.getElementById("comment-userName-tips").style.display = 'none';
		} else {
			document.getElementById("comment-userName-tips").style.display = 'block';
		}
		if (document.getElementById('comment-userPwd').value !== '') {
			document.getElementById("comment-userPwd-tips").style.display = 'none';
		} else {
			document.getElementById("comment-userPwd-tips").style.display = 'block';
		}
	}
}
/**
 * 实现通讯生成评论默认页文件程序
 * 即AJAX没有找到评论而时，而生成的默认页
 */
/*
function mkpinglundirfile(lj) {
	var mkiframe = document.createElement("iframe");
	mkiframe.id = "mkpinglundirfile";
	mkiframe.style.display = "none";
	mkiframe.src = "http://staff.gmw.cn/txt/mkpinglundir.php?url=" + lj;
	if (mkiframe.attachEvent) {
		mkiframe.attachEvent("onload", function() {
			document.body.removeChild(mkiframe);
		});
	} else {
		mkiframe.onload = function() {
			document.body.removeChild(mkiframe);
		};
	}
	document.body.appendChild(mkiframe);
}
/*
//设置cookie
function setCookie(key, value, t) {
	var oDate = new Date();
	oDate.setDate( oDate.getDate() + t );
	document.cookie = key + '=' + value + ';expires=' + oDate.toGMTString();
}

function getCookie(key) {
	var arr1 = document.cookie.split('; ');
	for (var i=0; i<arr1.length; i++) {
		var arr2 = arr1[i].split('=');
		if (arr2[0] == key) {
			return arr2[1];
		}
	}
}

function delCookie(key) {
	setCookie(key, '', -1);
}
*/
//SSO登录begin
//获得指定名称的cookie值
/*
function getCookie(c_name){
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=")
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1
			c_end = document.cookie.indexOf(";", c_start)
			if (c_end == -1) c_end = document.cookie.length
			return unescape(document.cookie.substring(c_start, c_end))
		}
	}
	return ""
}
//登陆通信 评论
function login_in_common(y) {
	if (y == 1 || y == 2) {
		var cusername = encodeURIComponent(document.commentform.LoginName.value);
		var cpassword = encodeURIComponent(document.commentform.LoginPassword.value);
	}
	var iframe = document.createElement("iframe");
	iframe.id = "login_id_common";
	iframe.style.display = "none";
	if (y == 1) {
		var login_url = "http://home.gmw.cn/www_login.php?do=login&username=" + cusername + "&password=" + cpassword;
	} else if (y == 2) {
		var login_url = "http://home.gmw.cn/www_login.php?do=commentlogin&username=" + cusername + "&password=" + cpassword;
	} else {
		var login_url = "http://home.gmw.cn/www_login.php?do=logout";
	}
	iframe.src = login_url;
	if (iframe.attachEvent) {
		iframe.attachEvent("onload", function() {
			location.reload();
			document.body.removeChild(iframe);
		});
	} else {
		iframe.onload = function() {
			location.reload();
			document.body.removeChild(iframe);
		};
	}
	document.body.appendChild(iframe);
}
//登陆通信 头部
function login_in(y) {
	if (y == 1) {
		var username = encodeURIComponent(document.form_login.name1.value);
		var password = encodeURIComponent(document.form_login.pass.value);
	}
	document.getElementById("login").style.display = "none";
	var loginbar_new_html = document.getElementById('loginbar_new').innerHTML;
	document.getElementById('loginbar_new').innerHTML = "&nbsp;<img border=0 height=20 src=\"http://home.gmw.cn/wait.gif\" />&nbsp;加载中..." + loginbar_new_html;
	var iframe = document.createElement("iframe");
	iframe.id = "login_id";
	iframe.style.display = "none";
	if (y == 1) {
		var login_url = "http://home.gmw.cn/www_login.php?do=login&username=" + username + "&password=" + password;
	} else {
		var login_url = "http://home.gmw.cn/www_login.php?do=logout";
	}
	iframe.src = login_url;
	if (iframe.attachEvent) {
		iframe.attachEvent("onload", function() {
			location.reload();
			//if_login();
			document.body.removeChild(iframe);
		});
	} else {
		iframe.onload = function() {
			location.reload();
			//if_login();
			document.body.removeChild(iframe);
		};
	}
	document.body.appendChild(iframe);
}
var cookie_name = decodeURIComponent(getCookie('Example_auth_username'));
var cookie_uid = getCookie('Example_auth_uid');
if (document.getElementById('loginbar_new') != null) {
	var if_loginbar_new = 1;
}
if (if_loginbar_new == 1) {
	if (cookie_name.length > 0) {
		document.getElementById('loginbar_new').innerHTML = "<div id='login'>" + cookie_name + "&nbsp;&nbsp;您好!&nbsp;&nbsp;<a href='http://home.gmw.cn' style='color:#c00;' target='_blank'>用户统一登录平台</a>&nbsp;&nbsp;<a href='#' onclick=\"login_in();\" target='_self'>安全退出</a></div>";
	} else {
		document.getElementById('loginbar_new').innerHTML = "<div id=\"login\" style=\"width:511px;\"><iframe name=\"iframe_login_2\" style=\"display:none\"></iframe><form style=\"margin:0px;padding:0px;\" name=\"form_login\" action=\"http://home.gmw.cn/www_login.php\" target=\"iframe_login_2\"><a href=\"https://api.weibo.com/oauth2/authorize?client_id=288713144&redirect_uri=http%3A%2F%2Fhome.gmw.cn%2Fapi%2Fweibo%2Fcallback.php&response_type=code&state=" + document.URL + "\"><img style=\"vertical-align:middle;\" src=\"http://img.gmw.cn/pic/weibologin.png\"/></a>&nbsp;&nbsp;&nbsp;&nbsp;用户名：<input class=\"headLogin\" name=\"name1\" type=\"text\" />&nbsp;&nbsp;密码：<input class=\"headLogin\" name=\"pass\" type=\"password\" />&nbsp;&nbsp;<input class=\"headSubLog\" type=\"submit\" value=\"登录\" onclick=\"login_in('1')\"/><input class=\"headSubLog\" name=\"login\" type=\"button\" value=\"注册\" onclick=\"document.location.href='http://home.gmw.cn/register.php?callback=" + document.URL + "';\"/></form></div>";
	}
}
//comment评论
if (document.getElementById('commentform') != null) {
	//document.getElementById('commentform').target='_blank';
}
if (document.getElementById('commentLogin') != null) {
	var if_commentLogin = 1;
}
if (if_commentLogin == 1) {
	if (cookie_name.length > 0) {
		document.getElementById('commentLogin').innerHTML = "<div id='login_1'><span style=\"text-align:right; float:right; padding-right:110px; display:none; color:#9a0000;\">Ctrl+Enter快捷提交</span><a href=\"http://blog.gmw.cn/" + cookie_uid + "\" style=\"display:inline-block;font-weight:bold;letter-spacing:1px;font-size:14px;\" target=\"_blank\">" + cookie_name + "</a><span class=\"info-line\">|</span><a style=\"display:inline-block;\" href='http://bbs.gmw.cn' target='_blank'>进入光明社区</a><span class=\"info-line\">|</span><a style=\"display:inline-block;\" href='http://home.gmw.cn/comment/logout.php'>退出</a><span class=\"info-line\">|</span><a style=\"display:inline-block;\" href='javascript:void(0);' target='_self' onclick='Toresult();'>查看评论</a><a href=\"javascript:void(0);\" target=\"_self\" class=\"comment-btn\" onClick=\"return Comment_submit_m();\">发表评论</a></div><label for=\"comment-userName\" id='comment-userName-tips' class=\"comment-user-tips\" style=\"display: none;\">用户名</label><input name=\"LoginName\" type=\"text\" id=\"comment-userName\" style=\"display: none;\"><label for=\"comment-userPwd\" id='comment-userPwd-tips' class=\"comment-user-tips\" style=\"display: none;\">密码</label><input name=\"LoginPassword\" type=\"password\" id=\"comment-userPwd\" style=\"display: none;\"><input type=\"checkbox\" name=\"ifniming\" id=\"ifniming\" value=\"1\" style=\"display: none;\">";
		document.getElementById('user_averter').innerHTML = "<a href=\"http://blog.gmw.cn/" + cookie_uid + "\" target=\"_blank\"><img border=\"0\" width=\"80\" height=\"80\" src=\"http://uc.gmw.cn/avatar.php?uid=" + cookie_uid + "&size=middle\"></a>";
		if (document.getElementById('pinglun_reg1') != null) {
			document.getElementById('pinglun_reg1').style.display = "none";
		}
		if (document.getElementById('pinglun_reg2') != null) {
			document.getElementById('pinglun_reg2').style.display = "none";
		}
	}
}
//toReg_m()-----来自commentajax.js的函数toReg()方法
function ToReg_m() {
	/*var regForum = "http://home.gmw.cn/register.php?callback=" + document.URL;
	var backurl = window.location.href;
	window.open(regForum, "_self", "", "false");*//*
    window.location.href="http://home.gmw.cn/register.php?callback=" + document.URL;
}
//Ctrl+Enter提交  ---  by 芮继龙 --- 2013年07月10日
function pinglun_getEvent() {
	if (document.all) {
		return window.event;
	}
	func = pinglun_getEvent.caller;
	while (func) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof(arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}
if(document.getElementById('commentform')){
    document.getElementById('commentform').onkeydown = function() {
        var pinglung_event = pinglun_getEvent();
        if (pinglung_event.keyCode == 13 && pinglung_event.ctrlKey) {
            var pass = checkform_comment();
            if (pass) {
                document.getElementById("commentform").action = "http://bbs.gmw.cn/api/save_common_m.php";
                window.open('http://bbs.gmw.cn/api/save_common_m.php', 'myformactionWin');
                document.getElementById('commentform').target = 'myformactionWin';
                document.getElementById("commentform").submit();
                document.getElementById('commentContent').value = "";
                if (!getCookie('Example_auth_username') && document.getElementById('ifniming').checked == false) {
                    login_in_common('2');
                }
            }
        }
    };
}
//SSO登录end
function Comment_submit_m() {
	var pass = checkform_comment();
	if (pass) {
		if (!getCookie('Example_auth_username') && document.getElementById('ifniming').checked == false) {
			login_in_common('2');
		} else {
			location.reload();
		}
		//document.getElementById("commentform").action="http://bbs.gmw.cn/api/save_common_m.php";
		document.getElementById("commentform").submit();
		document.getElementById('commentContent').value = "";
	} else {
		return false;
	}
}*/
$(function(){
	// 修改文章尾部大G链接
	/*if($("img[title=返回光明网首页]").length>0){
		$("img[title=返回光明网首页]").attr("title","返回触屏版首页").parent("a").attr("href","http://m.gmw.cn");
	}*/
	// 去掉手机光明网文章尾部大G图标   by马亚楠  2014.7.28
	if($("img[title=返回光明网首页]").length>0){
		$("img[title=返回光明网首页]").parent("a").remove();
	}
});