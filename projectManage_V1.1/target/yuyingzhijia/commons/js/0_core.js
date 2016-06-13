/**
 * @class Core 系统核心API
 * @author <a href="mailto:luoxueyong@126.com">罗学勇</a>
 * @constructor Core
 * @description 提供系统常用的公用的JS函数 DATE PERSON DES 2011-07-01 MIUKOO 创建文件 2011-07-07
 *              张强 修改getCookie方法
 * 
 * @example
 * 初始函数 new Core({});
 * @since version 0.0.1
 * @see
 * 
 */

var regEmail = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;

// 电话
var regPhone = /^1[358][0-9]{9}$/;

// 固定电话
var regTel = /^\d{3}-\d{8}|\d{4}-\d{7}$/;

// QQ验证
var regQQ = /^[0-9]{4,15}$/;

function Core() {

	/**
	 * @description 浏览器名称
	 * @type String
	 * @field
	 */
	this.browser = "";
}

Core.prototype = {

	/**
	 * @description 检测浏览器名称
	 * @author 曹纬纬
	 * @param 无
	 * @exception 无
	 * @return {String} name 浏览器名称(不带版本号)
	 */
	browser : function() {

		var Browser_Name = navigator.appName;
		var Browser_Version = parseFloat(navigator.appVersion);
		var Browser_Agent = navigator.userAgent;
		var Actual_Name;
		var is_IE = (Browser_Name == "Microsoft Internet Explorer");// 判读是否为ie浏览器
		var is_NN = (Browser_Name == "Netscape");// 判断是否为netscape浏览器
		var is_op = (Browser_Name == "Opera");// 判断是否为Opera浏览器
		if (is_NN) {

			if (Browser_Version >= 5.0) {
				if (Browser_Agent.indexOf("Netscape") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Bname = Browser_Agent.substring(0, Split_Sign);
					var Split_sign2 = Bname.lastIndexOf(" ");
					Actual_Name = Bname
							.substring(Split_sign2 + 1, Bname.length);
				}
				if (Browser_Agent.indexOf("Firefox") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.lastIndexOf(" ");
					Actual_Name = Browser_Agent.substring(Version + 1,
							Split_Sign);
				}
				if (Browser_Agent.indexOf("Safari") != -1) {
					if (Browser_Agent.indexOf("Chrome") != -1) {
						var Split_Sign = Browser_Agent.lastIndexOf(" ");
						var Version = Browser_Agent.substring(0, Split_Sign);
						;
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Version.lastIndexOf(" ");
						Actual_Name = Version.substring(Bname + 1, Split_Sign2);
					} else {
						var Split_Sign = Browser_Agent.lastIndexOf("/");
						var Version = Browser_Agent.substring(0, Split_Sign);
						;
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Browser_Agent.lastIndexOf(" ");
						Actual_Name = Browser_Agent.substring(Bname + 1,
								Split_Sign);
					}
				}
			} else {

				Actual_Name = Browser_Name;
			}
		} else if (is_IE) {
			var Version_Start = Browser_Agent.indexOf("MSIE");
			var Version_End = Browser_Agent.indexOf(";", Version_Start);
			Actual_Name = Browser_Name;

			if (Browser_Agent.indexOf("Maxthon") != -1
					|| Browser_Agent.indexOf("MAXTHON") != -1) {
				var mv = Browser_Agent.lastIndexOf(" ");
				var mv1 = Browser_Agent.substring(mv, Browser_Agent.length - 1);
				Actual_Name += "(Maxthon)";

			}

		} else if (Browser_Agent.indexOf("Opera") != -1) {
			Actual_Name = "Opera";
			var tempstart = Browser_Agent.indexOf("Opera");
			var tempend = Browser_Agent.length;

		} else {
			Actual_Name = "Unknown Navigator"

		}

		return Actual_Name;

	},

	/**
	 * @description 检测浏览器版本
	 * @author 曹纬纬
	 * @param 无
	 * @exception 无
	 * @return {Number} name 浏览器版本
	 */
	browserVersion : function() {

		var Browser_Name = navigator.appName;
		var Browser_Version = parseFloat(navigator.appVersion);
		var Browser_Agent = navigator.userAgent;
		var Actual_Version;
		var is_IE = (Browser_Name == "Microsoft Internet Explorer");// 判读是否为ie浏览器
		var is_NN = (Browser_Name == "Netscape");// 判断是否为netscape浏览器
		var is_op = (Browser_Name == "Opera");// 判断是否为Opera浏览器
		if (is_NN) {

			if (Browser_Version >= 5.0) {
				if (Browser_Agent.indexOf("Netscape") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.lastIndexOf(" ");
					var Bname = Browser_Agent.substring(0, Split_Sign);
					var Split_sign2 = Bname.lastIndexOf(" ");
					Actual_Version = Browser_Agent.substring(Split_Sign + 1,
							Browser_Agent.length);
				}
				if (Browser_Agent.indexOf("Firefox") != -1) {
					var Split_Sign = Browser_Agent.lastIndexOf("/");
					var Version = Browser_Agent.lastIndexOf(" ");
					Actual_Version = Browser_Agent.substring(Split_Sign + 1,
							Browser_Agent.length);
				}
				if (Browser_Agent.indexOf("Safari") != -1) {
					if (Browser_Agent.indexOf("Chrome") != -1) {
						var Split_Sign = Browser_Agent.lastIndexOf(" ");
						var Version = Browser_Agent.substring(0, Split_Sign);
						;
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Version.lastIndexOf(" ");
						Actual_Version = Version.substring(Split_Sign2 + 1,
								Version.length);
					} else {
						var Split_Sign = Browser_Agent.lastIndexOf("/");
						var Version = Browser_Agent.substring(0, Split_Sign);
						;
						var Split_Sign2 = Version.lastIndexOf("/");
						var Bname = Browser_Agent.lastIndexOf(" ");
						Actual_Version = Browser_Agent.substring(
								Split_Sign2 + 1, Bname);
					}
				}
			} else {
				Actual_Version = Browser_Version;
			}
		} else if (is_IE) {
			var Version_Start = Browser_Agent.indexOf("MSIE");
			var Version_End = Browser_Agent.indexOf(";", Version_Start);
			Actual_Version = Browser_Agent.substring(Version_Start + 5,
					Version_End)

			if (Browser_Agent.indexOf("Maxthon") != -1
					|| Browser_Agent.indexOf("MAXTHON") != -1) {
				var mv = Browser_Agent.lastIndexOf(" ");
				var mv1 = Browser_Agent.substring(mv, Browser_Agent.length - 1);
				mv1 = "遨游版本:" + mv1;
				Actual_Version += mv1;
			}

		} else if (Browser_Agent.indexOf("Opera") != -1) {
			Actual_Name = "Opera";
			var tempstart = Browser_Agent.indexOf("Opera");
			var tempend = Browser_Agent.length;
			Actual_Version = Browser_Version;
		} else {

			Actual_Version = "Unknown Version";
		}

		return Actual_Version;

	},

	/**
	 * @description 检测是否为数字
	 * @author 蔡金民
	 * @param {Object}
	 *            val 验证的值
	 * @exception 无
	 * @return {Boolean} bool 检测结果 true为是 false为不是
	 */
	isNumber : function(val) {
		return /^\d+(\.\d+)?$/.test(val);
	},
	/**
	 * @description 判断是否是数字包括（整数、负数、小数）
	 * @author 曹纬纬
	 * @param {Object}
	 *            num 验证的值
	 * @exception 无
	 * @return true-是数字 false-不是数字
	 */
	isAllNumber : function(num) {
		var pattern = /^(\+|-)?\d+($|\.\d+$)/;
		var isOK = false;
		if (pattern.test(num)) {
			isOK = true;
		}
		return isOK;
	},
	/**
	 * @description 验证非负数
	 * @author 郝玲风
	 * @param 无
	 * @exception
	 * @return true/false
	 */
	isPositive : function(val) {
		return /^\d+(?=\.{0,1}\d+$|$)/.test(val);
	},
	/**
	 * @description 验证非负整数
	 * @author 郝玲风
	 * @param 无
	 * @exception
	 * @return 无
	 */
	isInt : function(val) {
		return /^[0-9]{1,20}$/.test(val);
	},
	/**
	 * @description 验证手机号
	 * @author 郝玲风
	 * @param 无
	 * @exception
	 * @return 无
	 */
	isMobilePhone : function(val) {
		if (val.length == 11) {
			if (val.substr(0, 1) == 1 && /^[0-9]{1,20}$/.test(val)) {
				return true;
			}
		}
		return false;
	},
	/**
	 * @description 验证家庭电话
	 * @author 郝玲风
	 * @param 无
	 * @exception
	 * @return 无
	 */
	isTel : function(s) {
		var patrn = /^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
		if (!patrn.exec(s)) {
			return false;
		}
		return true;
	},
	/**
	 * @description 检测是否为汉字
	 * @author 蔡金民
	 * @param {Object}  val 验证的值
	 * @exception 无
	 * @return {Boolean} bool 检测结果 true为是 false为不是
	 */
	isChinese : function(val) {
		return !/[u4e00-u9fa5]+$/.test(val);
	},

	/**
	 * @description 检测是否为字符
	 * @author 蔡金民
	 * @param {Object}
	 *            val 验证的值
	 * @exception 无
	 * @return {Boolean} bool 检测结果 true为是 false为不是
	 */
	isChar : function(val) {
		return /^[A-Za-z]+$/.test(val);
	},

	/**
	 * @description 检测是否为Email
	 * @author 蔡金民
	 * @param {Object}
	 *            val 验证的值
	 * @exception 无
	 * @return {Boolean} bool 检测结果 true为是 false为不是
	 */
	isEmail : function(val) {
		var reg = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
		return reg.test(val);
	},

	/**
	 * @description 检测是否为身份证
	 * @author 蔡金民
	 * @param {Object}
	 *            val 验证的值
	 * @exception 无
	 * @return {Boolean} bool 检测结果 true为是 false为不是
	 */
	isIDCard : function(val) {
		return /(^\d{15}$)|(^\d{17}([0-9]|X)$)/.test(val);
	},

	/**
	 * 判断对象值是否为空,参数为jQuery对象
	 * @param {jQuery} jqObj
	 * @return {Boolean}
	 */
	isEmpty : function(jqObj) {
		if (jqObj) {
			var val = jqObj.val();
			if (core.trim(val) == "") {
				return true;
			}
		}
		return false;
	},

	/**
	 * 去除两头空格
	 * @param {} str
	 * @return {}
	 */
	trim : function(str) {
		return str.replace(/(^ *)|( *$)/g, "");
	},

	/**
	 * @description 格式字符串,去空格、undefind/null转换为""
	 * @author 唐吉海
	 * @param {Object}
	 *            val 转换的值
	 * @exception 无
	 * @return {String} str 转换的结果
	 */
	formatStr : function(val) {
		if (typeof val == "undefined" || val == null || val == "") {
			return "";
		}
		var reg = /^\s* | \s*$/g;
		var str = val.replace(reg, "");
		return str;
	},

	/**
	 * @description 格式数字类型和小数
	 * @author 唐吉海
	 * @param {Object}
	 *            val 转换的值
	 * @param {String}
	 *            format 格式串如：‘0.00’
	 * @exception 无
	 * @return {Number} num 转换的结果
	 */
	formatNum : function(val, format) {
		if (val == "" || val == null)
			return 0;
		if (isNaN(val))
			return 0;
		if (format == "" || format == null)
			return val;
		val = val.toString();
		val = val.replace(/\,/g, "");
		var ptnLen = format.length;
		var pointIndex = format.indexOf(".");
		var groupIndex = format.indexOf(",");
		var pointLen = (pointIndex == -1 ? 0 : ptnLen - pointIndex - 1);
		val = parseFloat(val).toFixed(pointLen) + "";
		if (groupIndex != -1) {
			if (pointIndex == -1)
				pointIndex = ptnLen;
			var len = pointIndex - groupIndex - 1;
			var reg = new RegExp("(-?\\d+)(\\d{" + len + "})");
			while (reg.test(val))
				val = val.replace(reg, "$1,$2");
		}
		return val;
	},

	/**
	 * @description 格式日期字符
	 * @author 唐吉海
	 * @param {Object}
	 *            val 转换的值，如果位空默认按照当前的日期计算
	 * @param {String}
	 *            format 格式串如：‘yyyy/MM/dd’
	 * @exception 无
	 * @return {String} date 转换的结果
	 */
	formatDate : function(val, format) {
		if (!format || format == "") {
			format = "yyyy-MM-dd";
		}
		var year = val.getYear().toString();
		var month = (val.getMonth() + 1).toString();
		var day = val.getDate().toString();
		var yearMarker = format.replace(/[^y|Y]/g, '');
		if (yearMarker.length == 2) {
			year = year.substring(2, 4);
		}
		var monthMarker = format.replace(/[^m|M]/g, '');
		if (monthMarker.length > 1) {
			if (month.length == 1) {
				month = "0" + month;
			}
		}
		var dayMarker = format.replace(/[^d]/g, '');
		if (dayMarker.length > 1) {
			if (day.length == 1) {
				day = "0" + day;
			}
		}
		return format.replace(yearMarker, year).replace(monthMarker, month)
				.replace(dayMarker, day);
	},

	/**
	 * @description 后台AJAX请求函数
	 * @author 张强
	 * @param {String}
	 *            url 请求URL
	 * @param {JSON}
	 *            param 请求参数,如{key:value}
	 * @param {Function}
	 *            callback
	 *            回调函数，格式如{success:function(){...},fail:function(){...}}
	 * @exception 无
	 * @return 无
	 */
	ajax : function(url, param, callback) {
		jQuery.ajax( {
			type : "GET",
			url : url,
			data : param,
			success : callback
		});
	},
	/**
	 * @description 监听事件
	 * @author 张强
	 * @param {String}
	 *            name 事件名称,如：cilck、mouseover
	 * @param {String}
	 *            fun 函数名称
	 * @exception 无
	 * @return 无
	 */
	addListener : function(name, id, fun) {
		jQuery("#" + id).bind(name, fun);
	},

	/**
	 * @description 动态加载JS文件
	 * @author 张强
	 * @param {String}
	 *            id 定义加载文件的ID
	 * @param {String}
	 *            url js文件的资源路径
	 * @exception 无
	 * @return 无
	 */
	loadJs : function(id, url) {
		var styleTag = document.createElement("script");
		styleTag.setAttribute('id', id);
		styleTag.setAttribute('src', url);
		styleTag.setAttribute('type', "text/javascript");
		styleTag.setAttribute('defer', 'defer');
		document.getElementsByTagName("head")[0].appendChild(styleTag);
	},

	/**
	 * @description 动态加载CSS文件
	 * @author 张强
	 * @param {String}
	 *            id 定义加载文件的ID
	 * @param {String}
	 *            url Css文件的资源路径
	 * @exception 无
	 * @return 无
	 */
	loadCss : function(id, url) {
		var styleTag = document.createElement("link");
		styleTag.setAttribute('type', 'text/css');
		styleTag.setAttribute('rel', 'stylesheet');
		styleTag.setAttribute('href', url);
		styleTag.setAttribute('id', id);
		document.getElementsByTagName("head")[0].appendChild(styleTag);
	},

	/**
	 * @description 设置cookie
	 * @author 张强
	 * @param {String}
	 *            c_name cookie的名称
	 * @param {String}
	 *            value cookie值
	 * @param {int}
	 *            expiredays cookie有效期
	 * @exception 无
	 * @return 无
	 */
	setCookie : function(c_name, value, expiredays) {
		var exdate = new Date();
		exdate.setDate(exdate.getDate() + expiredays);
		document.cookie = c_name
				+ "="
				+ escape(value)
				+ ((expiredays == null) ? "" : ";expires="
						+ exdate.toGMTString()) + ";path=/";
	},

	/**
	 * @description 获取cookie的值
	 * @author 张强
	 * @param {String}
	 *            c_name cookie的名称
	 * @exception 无
	 * @return {String} 返回cookie的值,如果没有该cookie,返回"getfailed"
	 */
	getCookie : function(c_name) {
		if (document.cookie.length > 0) {
			var c_start = document.cookie.indexOf(c_name + "=");
			if (c_start != -1) {
				c_start = c_start + c_name.length + 1;
				var c_end = document.cookie.indexOf(";", c_start);
				if (c_end == -1)
					c_end = document.cookie.length;
				var returnCookieValue = unescape(document.cookie.substring(
						c_start, c_end));
				return returnCookieValue;
			}
		}
		return "getfailed";
	},

	/**
	 * @description 删除cookie
	 * @author 张强
	 * @param {String}
	 *            c_name cookie的名称
	 * @exception 无
	 * @return 无
	 */
	removeCookie : function(c_name) {
		core.setCookie(c_name, null, 0);
	},

	/**
	 * @description 页面跳转
	 * @author 蔡金民
	 * @param {String}
	 *            url 所要跳转的路径
	 * @exception 无
	 * @return 无
	 */
	open : function(url) {
		document.location = url;
	},

	/**
	 * @description 利用某组件下input、select、textarea的Id和值，构造url参数串
	 * @author 蔡金民
	 * @param {String}
	 *            id 组件id
	 * @exception 无
	 * @return {String} url参数串
	 */
	createUrlParam : function(id) {
		var val = "";
		var obj = document.getElementById(id);
		var objs = obj.getElementsByTagName("input");
		if (objs != null && objs.length > 0) {
			for ( var i = 0; i < objs.length; i++) {
				if (objs[i].type == "text" || objs[i].type == "hidden"
						|| objs[i].type == "password")
					val += objs[i].id + "=" + objs[i].value + "&";
			}
		}
		objs = obj.getElementsByTagName("select");
		if (objs != null && objs.length > 0) {
			for ( var i = 0; i < objs.length; i++) {
				val += objs[i].id + "=" + objs[i].value + "&";
			}
		}
		objs = obj.getElementsByTagName("textarea");
		if (objs != null && objs.length > 0) {
			for ( var i = 0; i < objs.length; i++) {
				val += objs[i].id + "="
						+ objs[i].value.replace("/\n/g", "<br />") + "&";
			}
		}
		if (val.length > 0) {
			return val.substring(0, val.length - 1);
		} else {
			return "";
		}
	},

	/**
	 * @description 获取项目根目录
	 * @author 毕志波
	 */
	getRootPath : function() {
		var strFullPath = window.document.location.href;
		var strPath = window.document.location.pathname;
		var pos = strFullPath.indexOf(strPath);
		var prePath = strFullPath.substring(0, pos);
		var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
		return postPath;
	},

	jsonToArray : function(sdata) {
		var jobj = eval("(" + sdata + ")");
		var sobj = jobj["response"];
		var alist = new Array();
		var count = sobj["allcount"];
		for ( var i = 1; i <= count; i++) {
			var dataobj = {};
			jq.each(sobj, function(key, value) {
				var a = key.split("_");
				if (a[1] == i) {
					var len = (i + "").length;
					key = key.substring(0, key.length - len - 1);
					var ss = value.split("'");
					dataobj[key] = ss[1];
				}
			});
			alist.push(dataobj);
		}
		return alist;
	},

	/**
	 * 验证ID是objID的元素的text是否为空 。
	 *
	 * @param objID 标签id的值
	 * @param bIsAlert 是否使用alert提示
	 * @param msg 提示信息
	 * @param bIsMust 1为必填、2为不必填
	 * @return
	 */
	commonTextValidate : function(objID, bIsAlert, msg, bIsMust) {
		var objElement = document.getElementById(objID);

		if (objElement != null) {
			var objValue = objElement.value.replace(/^\s\s*/, '');

			if (bIsMust == 2) {
				if (objElement.value.length == 0) {
					return true;
				}
			}
			if (objValue == "") {
				if (bIsAlert) {
					alert(msg + "不能为空");
				}
				$(objElement).poshytip("reset");
				if (msg != "出生日期") {
					$(objElement).attr("style","border:1px solid red;");
					$(objElement).poshytip(
							{
								content : "<span style='color:red;'>" + msg
										+ "不能为空" + "</span>",
								className : 'tip-yellowsimple',
								showOn : 'none',
								alignTo : 'target',
								alignX : 'right',
								alignY : 'center',
								offsetX : 5,
								showTimeout : 100
							});
				} else {
					$(objElement).poshytip(
							{
								content : "<span style='color:red;'>" + msg
										+ "不能为空" + "</span>",
								className : 'tip-yellowsimple',
								showOn : 'none',
								alignTo : 'target',
								alignX : 'center',
								alignY : 'inner-bottom',
								offsetX : 5,
								showTimeout : 100
							});
				}
				$(objElement).poshytip('show');

				objElement.value = "";
				// objElement.focus(); // 获取光标。
				return false;
			}
		}
		$(objElement).attr("style","border:1px solid green;");
		$(objElement).poshytip("reset");
		$(objElement).poshytip( {
			content : "<span style='color:green;'>" + msg + "填写正确！</span>",
			className : 'tip-yellowsimple',
			showOn : 'none',
			alignTo : 'target',
			alignX : 'right',
			alignY : 'center',
			offsetX : 5,
			showTimeout : 100
		});
		$(objElement).poshytip('show');

		// 返回结果
		return true;
	},

	/**
	 * 判断ID是selectID的select框是否选择。
	 * 
	 * @param selectID select标签id的值
	 * @param bIsAlert 是否进行alert
	 * @param strMsg 提示信息
	 * @param bIsMust 是否是必填的
	 */
	commonSelectValidate : function(selectID, bIsAlert, strMsg, bIsMust) {
		var objElement = document.getElementById(selectID);

		if (bIsMust == 2) {
			return true;
		}
		if (objElement != null) {
			if (objElement.value == "") {
				if (bIsAlert) {
					alert("请选择" + strMsg + "!");
				}

				$(objElement).poshytip("reset");
				$(objElement).poshytip(
						{
							content : "<span style='color:red;'>请选择" + strMsg
									+ "!" + "</span>",
							className : 'tip-yellowsimple',
							showOn : 'none',
							alignTo : 'target',
							alignX : 'right',
							alignY : 'center',
							offsetX : 5,
							showTimeout : 100
						});
				$(objElement).poshytip('show');

				// objElement.focus();
				return false;
			}
		}

		$(objElement).poshytip("reset");
		$(objElement).poshytip(
				{
					content : "<span style='color:green;'>" + strMsg + "已选择!"
							+ "</span>",
					className : 'tip-yellowsimple',
					showOn : 'none',
					alignTo : 'target',
					alignX : 'right',
					alignY : 'center',
					offsetX : 5,
					showTimeout : 100
				});
		$(objElement).poshytip('show');

		return true;
	},

	/**
	 * 判断单选框是否选择。
	 * 
	 * @param classValue 标签class值
	 * @param bIsAlert 是否进行alert
	 * @param msg 提示信息
	 * @param bIsMust 是否是必填的
	 */
	commonRadioValidate : function(classValue, bIsAlert, msg, bIsMust) {
		var num = 0;
		var objElement = $("." + classValue);

		if (bIsMust == 2) {
			return true;
		}
		if (objElement != null && objElement.length > 0) {
			for ( var i = 0; i < objElement.length; i++) {
				if (objElement[i].checked) {
					num++;
				}
			}

			if (num == 0) {
				if (bIsAlert) {
					alert(msg + "还未选择");
				}

				$(objElement[objElement.length - 1]).poshytip("reset");
				$(objElement[objElement.length - 1]).poshytip(
						{
							content : "<span style='color:red;'>" + msg
									+ "还未选择" + "</span>",
							className : 'tip-yellowsimple',
							showOn : 'none',
							alignTo : 'target',
							alignX : 'center',
							alignY : 'inner-bottom',
							offsetX : 5,
							showTimeout : 100
						});
				$(objElement[objElement.length - 1]).poshytip('show');

				return false;
			}
		}

		$(objElement[objElement.length - 1]).poshytip('hide');

		// 返回结果
		return true;
	},

	/**
	 * 根据正则进行验证。
	 * @param reg 正则表达式
	 * @param bIsAlert 是否进行alert
	 * @param IDValue 表单id的值
	 * @param msg 提示信息
	 * @param bIsMust 是否是必填的
	 */
	commonRegValidate : function(reg, bIsAlert, IDValue, msg, bIsMust) {
		var objElement = document.getElementById(IDValue);

		if (reg == "phone") {
			reg = regPhone;
		} else if (reg == "tel") {
			reg = regTel;
		} else if (reg == "email") {
			reg = regEmail;
		}else if (reg == "qq")
			reg = regQQ

		if (objElement != null) {
			if (bIsMust == 2) {
				if (objElement.value.length == 0) {
					return true;
				}
			}
			if (objElement.value == "") {
				if (bIsAlert) {
					alert(msg + "不能为空！");
				}

				$(objElement).attr("style","border:1px solid red;");
				$(objElement).poshytip("reset");
				$(objElement).poshytip(
						{
							content : "<span style='color:red;'>" + msg
									+ "不能为空" + "</span>",
							className : 'tip-yellowsimple',
							showOn : 'none',
							alignTo : 'target',
							alignX : 'right',
							alignY : 'center',
							offsetX : 5,
							showTimeout : 100
						});
				$(objElement).poshytip('show');

				return false;
			}
			if (!reg.test(objElement.value)) {
				if (bIsAlert) {
					alert(msg + "格式不正确！");
				}

				$(objElement).attr("style","border:1px solid red;");
				$(objElement).poshytip("reset");
				$(objElement).poshytip(
						{
							content : "<span style='color:red;'>" + msg
									+ "格式不正确！" + "</span>",
							className : 'tip-yellowsimple',
							showOn : 'none',
							alignTo : 'target',
							alignX : 'right',
							alignY : 'center',
							offsetX : 5,
							showTimeout : 100
						});
				$(objElement).poshytip('show');

				return false;
			}
		}

		$(objElement).attr("style","border:1px solid green;");
		$(objElement).poshytip("reset");
		$(objElement).poshytip( {
			content : "<span style='color:green'> " + msg + "填写正确！</span>",
			className : 'tip-yellowsimple',
			showOn : 'none',
			alignTo : 'target',
			alignX : 'right',
			alignY : 'center',
			offsetX : 5,
			showTimeout : 100
		});
		$(objElement).poshytip('show');

		// 返回结果
		return true;
	},

	/**
	 * 身份证验证。
	 * 
	 * @param {Object} idValue ID的值。
	 * @param {Object} isUseAlert 是否弹框。
	 * @param {Object} bIsMust 是否是必须的。
	 * @param bIsErrorMsg 提示消息
	 * @param IDCardType 证件类型
	 * @return {TypeName} 
	 */
	checkIDCard : function(idValue, isUseAlert, bIsMust, bIsErrorMsg,
			IDCardType) {
		var IDCard = document.getElementById(idValue);
		var strResult;

		if (IDCard != null) {
			var strErrorMsg = "证件号码不能为空！";
			var strSuccessMsg = "success";

			// 验证是否为必填选项。
			if (!bIsMust) {
				if (IDCard.value == null || IDCard.value == "") {
					if (bIsErrorMsg) {
						return strSuccessMsg;
					}
					return true;
				}
			}

			// 必填非空验证。
			if (IDCard.value == null || IDCard.value == "") {
				if (isUseAlert) {
					alert(strErrorMsg);
					IDCard.focus();
				}

				if (bIsErrorMsg) {
					$(IDCard).poshytip("reset");
					$(IDCard).poshytip(
							{
								content : "<span style='color:red;'>"
										+ strErrorMsg + "</span>",
								className : 'tip-yellowsimple',
								showOn : 'none',
								alignTo : 'target',
								alignX : 'right',
								alignY : 'center',
								offsetX : 5,
								showTimeout : 100
							});
					$(IDCard).poshytip('show');

					return strErrorMsg;
				}

				return false;
			}

			if (IDCardType == 1) {
				// 必填格式验证。
				strResult = prewCheckIDCard(IDCard.value);

				$(IDCard).poshytip("reset");
				$(IDCard).poshytip(
						{
							content : "<span style='color:red;'>" + strResult
									+ "</span>",
							className : 'tip-yellowsimple',
							showOn : 'none',
							alignTo : 'target',
							alignX : 'right',
							alignY : 'center',
							offsetX : 5,
							showTimeout : 100
						});

				$(IDCard).poshytip('show');
				if (bIsErrorMsg) {
					if (strResult == "success") {
						$(IDCard).poshytip("update",
								"<span style='color:green;'>确认成功</span>");
					}
					return strResult;
				}

				if (strResult != "success") {
					if (isUseAlert) {
						alert(strResult);
						IDCard.focus();
					}

					return false;
				}
			}
		}

		if (bIsErrorMsg) {
			return strSuccessMsg;
		}
		// 返回结果
		return true;
	},
	// 模拟键盘复制功能。idValue要复制内容的id属性
	copyData :function (idValue)
	{
		var copyStatus = window.clipboardData.setData("text", $("#" + idValue + "").val());
		
		if (copyStatus)
		{
			alert("复制成功！");
		}
		else
		{
			alert("复制失败！");
		}
	},
	browserRedirect:function(strType,url)
	{
		var sUserAgent = navigator.userAgent.toLowerCase();
	    var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
	    var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
	    var bIsMidp = sUserAgent.match(/midp/i) == "midp";
	    var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
	    var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
	    var bIsAndroid = sUserAgent.match(/android/i) == "android";
	    var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
	    var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
	    if (!(bIsIpad || bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM) ){
	    	if (strType == "m")
	    	{
	    		window.location.href=url;
	    	}
	    }
	    else
	    {
	    	if (strType == "p")
	    	{
	    		window.location.href=url;
	    	}
	    }
	}
}

/**
 * 预处理。
 * 
 * @param {Object} idcard 身份证号。
 * @return {TypeName} 
 */
function prewCheckIDCard(idcard){
		var Errors=new Array( "success",
			"身份证号码位数不对!",
			"身份证号码出生日期超出范围或含有非法字符!",
			"身份证号码校验错误!",
			"身份证地区非法!" );
		var area={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
		var retflag=false;
		var idcard,Y,JYM;
		var S,M;
		var idcard_array = new Array();
		
		idcard_array = idcard.split("");
		
		//地区检验
		if(area[parseInt(idcard.substr(0,2))]==null) return Errors[4];
		//身份号码位数及格式检验
		switch(idcard.length){
			case 15:
				if ((parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
					ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
				} else {
					ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
				}
				if(ereg.test(idcard))
					{
					return Errors[0];
					}
				else 
				 	return Errors[2];
				break;
				case 18:
				//18位身份号码检测
				//出生日期的合法性检查 
				//闰年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))
				//平年月日:((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))
				if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
					ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
				} else {
					ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
				}
				if(ereg.test(idcard)){//测试出生日期的合法性
				//计算校验位
				S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
					+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
					+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
					+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
					+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
					+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
					+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
					+ parseInt(idcard_array[7]) * 1 
					+ parseInt(idcard_array[8]) * 6
					+ parseInt(idcard_array[9]) * 3 ;
					Y = S % 11;
					M = "F";
					JYM = "10X98765432";
					M = JYM.substr(Y,1);//判断校验位
				if(M == idcard_array[17]) return Errors[0]; //检测ID的校验位
					else return Errors[3];
				}
				else return Errors[2];
					break;
					default:
					return Errors[1];
					break;
	}
}

/* 
 =========================================== 
 //去除前后空格 
 =========================================== 
 */
String.prototype.Trim = function() {
	return this.replace(/(^ *)|( *$)/g, "");
}
/**
 * @description 去掉字符串两边空格
 * @author 蔡金民
 * @param 无
 * @exception 无
 * @return {String} 去掉两边空格之后的字符串
 */
String.prototype.trim = function() {
	// return this.replace(/(^[\t\n\r]*)|([\t\n\r]*$)/g, '');
	return this.replace(/^\s*/, "").replace(/\s*$/, "");
}

/**
 * @description 把字符串转换为json对象
 * @author 蔡金民
 * @param 无
 * @exception 无
 * @return {jsonobj} json对象
 */
String.prototype.stringToJSON = function() {
	return eval('(' + this + ')');
}

/**
 * @description 核心工具类的调用变量
 */
var core = new Core();

function Ext() {
}
//打开通用弹出窗口
Ext.openNewWin = function(url, winName, width, height, attribute) {

	if (attribute == null)
		attribute = "";
	if (winName == null)
		winName = "";
	if (typeof (width) == "undefined" || width == null || width == "-1") {
		width = screen.availWidth;
	}
	if (typeof (height) == "undefined" || height == null || height == "-1") {
		height = screen.availheight;
	}
	var tyWin = window.open(url, winName, 'width=' + width + ' height='
			+ height + 'px left=' + (screen.availWidth - width) / 2 + ' top='
			+ (screen.availheight - height) / 2 + ' ' + attribute);

	tyWin.focus();
};
/**
 * 获取服务访问根路径,
 * 解决在iframe里执行ajax,跨域问题
 * @return {}
 */
Ext.getBaseUrl = function() {
	var SERVER_NAME = "eCurrency";//core.getRootPath();
	var url = window.location.href;
	var len = url.indexOf(SERVER_NAME);
	var baseUrl = "";
	if (len != -1) {
		baseUrl = url.substring(0, len - 1);
	}
	baseUrl += "/" + SERVER_NAME;
	return baseUrl;
};

var objCore = new Core();