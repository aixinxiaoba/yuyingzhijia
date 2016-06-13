//同时加载多个window.onscroll函数
function gId() { 
  var elements = new Array(); 
  for (var i = 0; i < arguments.length; i++)   { 
    var element = arguments[i]; 
    if (typeof element == 'string') 
      element = document.getElementById(element); 
    if (arguments.length == 1) 
      return element; 
    elements.push(element); 
  } 
  return elements; 
}

function globalMutiScroll(f){
  if (window.onscroll){
	var o  = window.onscroll ;
	window.onscroll = function(){f();o();}//顺序可调
  }
  else
	window.onscroll  = f;
}

function globalMutiOnload(f){
  if (window.onload){
	var o  = window.onload ;
	window.onload = function(){f();o();}//顺序可调
  }
  else
	window.onload  = f;
}

function globalAppendElement(divId,divInnerHtml){
	var creatDiv = document.createElement("div");   	
	creatDiv.id=divId;
	creatDiv.innerHTML=divInnerHtml;	
	if(navigator.userAgent.indexOf("MSIE 6.0") > -1){
		document.body.insertBefore(creatDiv, document.body.childNodes[0]);
	}else{
		document.body.appendChild(creatDiv); 	
	}
}


function globalAppendFeedBack(){	

	var creatWeixin = document.createElement("a");
	creatWeixin.id = "wxFloat";	
	creatWeixin.title = "知识分享微信";
	creatWeixin.href = "javascript:;";
	creatWeixin.onmouseover=globalShowWx;
	creatWeixin.onmouseout=globalHideWx;	
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatWeixin, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatWeixin);
	}
	
	var creatWeixinPop = document.createElement("div");
	creatWeixinPop.id = "wxFloatHover";	
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatWeixinPop, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatWeixinPop);
	}
		
	
	/*var creatActive = document.createElement("a");
	creatActive.className="globalMamaguess"
	creatActive.title = "APP";
	creatActive.innerHTML = "APP";
	creatActive.href="http://www.yaolan.com/topic/ylapp/";
	creatActive.target="_blank";
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatActive, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatActive);
	}*/
	
	
	var creatGotop = document.createElement("a");
	creatGotop.id = "globalGoTop";
	creatGotop.title = "返回顶部";
	creatGotop.href = "javascript:;";
	creatGotop.innerHTML = "返回顶部";
	creatGotop.onclick = globalGoTop;
	creatGotop.target="_self";
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatGotop, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatGotop);
	}
	creatGotop.style.display="block";
	
	var goTophdHtml = "";
	globalAppendElement("globalGotopHide", goTophdHtml);
	globalGotopHide.style.display = "none";
	
	var creatAlink = document.createElement("a");
	creatAlink.id = "globalFbButton";
	creatAlink.title = "反馈";
	creatAlink.href = "javascript:;";
	creatAlink.innerHTML = "反馈";
	creatAlink.onclick = globalFbPopShow;
	creatAlink.target="_self";
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatAlink, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatAlink);
	}

	var globalFbHtml = "";
	/*
	 * globalFbHtml += ("<div class=\"globalFbWz\"><p>Hi~我是摇篮网产品经理墨水，</p><p>欢迎您给我们提产品的使用感受和建议。</p></div><form action=\"http://www.yaolan.com/jiucuo/\" onsubmit=\"return globalFbSubmit(this);\" target=\"actionframe\" method=\"post\" id=\"correctPageForm\"><div class=\"globalFbCon\"><input type=\"hidden\" id=\"userID\" name=\"userID\" value=\"-1\"/><input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"默认用户名\"/><input type=\"hidden\" id=\"url\" name=\"url\" value=\"#\"/><input type=\"hidden\" id=\"htmlTitle\" name=\"htmlTitle\"  value=\"默认title\"/><input type=\"hidden\" id=\"browser\" name=\"browser\" value=\"\" /><input type=\"hidden\" id=\"browserVersion\" name=\"browserVersion\" value=\"\"/><p>您要反馈的问题：<span id=\"globalFbConTips\" class=\"globalFbTips\"></span></p><div class=\"globalFbContent\"><textarea class=\"globalFbInput\" style=\"height:(this.scrollHeight+5)+'px');overflow:auto;\" id=\"globalFbTextArea\"  name=\"textContent\"  onblur='globalFbTaBlur(this)'  onkeyUp='globalFbTextareaCheck(this)'></textarea><textarea class=\"globalFbInput\" id=\"globalFbTextAreaTips\" onfocus='globalFbTaFocus()' >欢迎在这里输入您的疑问和建议，我们会及时处理。</textarea><span class=\"globalFbQuoteLeft\"></span><span class=\"globalFbQuoteRight\"></span></div></div><div class=\"globalFbCon\" id=\"globalFbContact\"><p>您的联系方式：<span id=\"globalFbEmailTips\" class=\"globalFbTips\"></span></p><div id=\"globalFbEmailCon\" class=\"globalFbEmailCon\"><input type=\"text\" name=\"emailAddress\" onblur=\"globalFbEmailBlur(this)\" id=\"globalFbInput\" class=\"globalFbEmailInput\"> <input value=\"请留下您的QQ或邮箱，便于我们联系您\" id=\"globalFbEmailWz\" class=\"globalFbEmailInput\" onfocus=\"globalFbEmailFocus()\"></div></div><input type=\"submit\" class=\"globalFbSubmit\" value=\"\" /><div id=\"globalFbError\">如遇功能异常错误请提交至<a href=\"http://bbs.yaolan.com/board_369.aspx\" target=\"_blank\">错误反馈</a>板块</div></form><iframe width=\"0\" height=\"0\" name=\"actionframe\" style=\"display: none;\"></iframe><span class=\"globalFbPm\"></span><a href=\"javascript:globalFbClose()\" class=\"globalFbClose\" title=\"关闭\" id=\"globalFbClose\"  target=\"_self\">关闭</a>");
	globalAppendElement("globalFb", globalFbHtml);
	globalFb.className = "globalFb";
	globalFb.style.display = "none";	
	
	var globalFbSuccessHtml = "";
	globalFbSuccessHtml += ("<div class=\"globalFbSuccess\">反馈成功！</div><div class=\"globalFbWz\"><p>非常感谢您的参与！</p><p>我们会尽快解决，请您关注。</p></div><a id=\"globalFbResultBtn\"  href=\"javascript:globalFbPopNewHide()\" target=\"_self\">真棒！期待结果！</a><span class=\"globalFbPm\"></span><a href=\"javascript:globalFbPopNewHide()\" class=\"globalFbClose\" title=\"关闭\" id=\"globalFbClose\" target=\"_self\">关闭</a>");
	globalAppendElement("globalFbResult", globalFbSuccessHtml);
	globalFbResult.className = "globalFbResult";
	globalFbResult.style.display = "none";
	 * */
	globalFbHtml += ("<div class=\"globalFbWz\"><p>Hi~我是育婴之家网产品经理聆溪，</p><p>欢迎您给我们提产品的使用感受和建议。</p></div><form action=\"/front/suggestion/putSuggestion.do\" onsubmit=\"return globalFbSubmit(this);\" target=\"actionframe\" method=\"post\" id=\"correctPageForm\"><div class=\"globalFbCon\"><input type=\"hidden\" id=\"userID\" name=\"userID\" value=\"-1\"/><input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"默认用户名\"/><input type=\"hidden\" id=\"url\" name=\"url\" value=\"#\"/><input type=\"hidden\" id=\"htmlTitle\" name=\"htmlTitle\"  value=\"默认title\"/><input type=\"hidden\" id=\"browser\" name=\"browser\" value=\"\" /><input type=\"hidden\" id=\"browserVersion\" name=\"browserVersion\" value=\"\"/><p>您要反馈的问题：<span id=\"globalFbConTips\" class=\"globalFbTips\"></span></p><div class=\"globalFbContent\"><textarea class=\"globalFbInput\" style=\"height:(this.scrollHeight+5)+'px');overflow:auto;\" id=\"globalFbTextArea\"  name=\"textContent\"  onblur='globalFbTaBlur(this)'  onkeyUp='globalFbTextareaCheck(this)'></textarea><textarea class=\"globalFbInput\" id=\"globalFbTextAreaTips\" onfocus='globalFbTaFocus()' >欢迎在这里输入您的疑问和建议，我们会及时处理。</textarea><span class=\"globalFbQuoteLeft\"></span><span class=\"globalFbQuoteRight\"></span></div></div><div class=\"globalFbCon\" id=\"globalFbContact\"><p>您的联系方式：<span id=\"globalFbEmailTips\" class=\"globalFbTips\"></span></p><div id=\"globalFbEmailCon\" class=\"globalFbEmailCon\"><input type=\"text\" name=\"emailAddress\" onblur=\"globalFbEmailBlur(this)\" id=\"globalFbInput\" class=\"globalFbEmailInput\"> <input value=\"请留下您的QQ或邮箱，便于我们联系您\" id=\"globalFbEmailWz\" class=\"globalFbEmailInput\" onfocus=\"globalFbEmailFocus()\"></div></div><input type=\"submit\" class=\"globalFbSubmit\" value=\"\" /><div id=\"globalFbError\">如遇功能异常错误请提交至<a href=\"http://bbs.yaolan.com/board_369.aspx\" target=\"_blank\">错误反馈</a>板块</div></form><iframe width=\"0\" height=\"0\" name=\"actionframe\" style=\"display: none;\"></iframe><span class=\"globalFbPm\"></span><a href=\"javascript:globalFbClose()\" class=\"globalFbClose\" title=\"关闭\" id=\"globalFbClose\"  target=\"_self\">关闭</a>");
    globalAppendElement("globalFb", globalFbHtml);
    globalFb.className = "globalFb";
    globalFb.style.display = "none";    
    
    var globalFbSuccessHtml = "";
    globalFbSuccessHtml += ("<div class=\"globalFbSuccess\">反馈成功！</div><div class=\"globalFbWz\"><p>非常感谢您的参与！</p><p>我们会尽快解决，请您关注。</p></div><a id=\"globalFbResultBtn\"  href=\"javascript:globalFbPopNewHide()\" target=\"_self\">真棒！期待结果！</a><span class=\"globalFbPm\"></span><a href=\"javascript:globalFbPopNewHide()\" class=\"globalFbClose\" title=\"关闭\" id=\"globalFbClose\" target=\"_self\">关闭</a>");
    globalAppendElement("globalFbResult", globalFbSuccessHtml);
    globalFbResult.className = "globalFbResult";
    globalFbResult.style.display = "none";
	
	
	var creatIframe = document.createElement("iframe");
	creatIframe.className = "globalPopBg";
	creatIframe.id = "globalPopBg";
	creatIframe.style.display = "none";
	if (navigator.userAgent.indexOf("MSIE 6.0") > -1) {
		document.body.insertBefore(creatIframe, document.body.childNodes[0]);
	} else {
		document.body.appendChild(creatIframe);
	}

	
	if (getCookieFeedback("user_id") != null) {
		document.getElementById("globalFbContact").style.display = "none";
	}else{
		document.getElementById("globalFbError").style.display = "none";
	}

	globalFbGetUrl();
}
var globalFbSubmitSuccess=true;
globalMutiOnload(globalAppendFeedBack);
//textArea验证	
function globalFbTextareaCheck(q){
	var result=false;
	var text=q.value;	
	if(text.length>200)	{
		gId("globalFbConTips").innerHTML="*内容长度不能超过200个字符！";
	}
	else	{
		gId("globalFbConTips").innerHTML="";
		result=true;
	}
	return result;
}

function globalFbTaFocus(){	
	gId("globalFbTextArea").style.display="block";
	gId("globalFbTextArea").focus();	
	gId("globalFbTextAreaTips").style.display="none";
	gId("globalFbConTips").innerHTML=""; 
}
function globalFbTaBlur(e) {							   
	if (gId("globalFbTextArea").value =="") {
		gId("globalFbTextAreaTips").style.display="block";
		gId("globalFbTextArea").style.display="none";
	}
}

function globalFbEmailFocus(){
	gId("globalFbInput").style.display="block";
	gId("globalFbInput").focus();	
	gId("globalFbEmailWz").style.display="none";
	gId("globalFbEmailTips").innerHTML=""; 
}
function globalFbEmailBlur(e) {							   
	if (gId("globalFbInput").value =="") {
		gId("globalFbEmailWz").style.display="block";
		gId("globalFbInput").style.display="none";
	}
} 

function getCookieFeedback(name){
	var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|$)"));
	if(arr != null) return arr[2]; return null;
}

function TrimFeedback(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//去掉html特殊字符
function setContent(str) {
	str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
	str.value = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
	return str;
}
//提交表单
function globalFbSubmit(thisForm){
	if(TrimFeedback(gId("globalFbTextArea").value)==""){
		gId("globalFbConTips").innerHTML="*内容不能为空！";
		return false;
	}
	if(gId("globalFbTextArea").value==""){		
		gId("globalFbConTips").innerHTML="您还没有填写反馈内容";
		return false;
	}	
	var result=false;
	var gTextarea=globalFbTextareaCheck(thisForm.textContent);
	if(gTextarea){
		globalFbPopHide();
		thisForm.textContent.value=setContent(thisForm.textContent.value);//过滤特殊字符
		globalFbPopNewShow();		
		window.setTimeout("globalFbPopNewHide()",20000000); 
		globalFbSubmitSuccess=false;
		return true;		
	}	
	return false;
}
function globalFbGetUrl(){			
	var htmlTitle=document.title;
	if(htmlTitle!=null)	{
		document.getElementById("htmlTitle").value =htmlTitle;
	}
	 var url=document.URL;
	 if(url!=null) {
		document.getElementById("url").value=url;
	 }
 }	
 
 
function globalGoTop(){
	var doc = (document.documentElement.scrollTop) ? document.documentElement : document.body;
	var time;
	time = setInterval(function(){								  
	doc.scrollTop -= Math.ceil(doc.scrollTop * 0.7);
	if(doc.scrollTop <= 0)  clearInterval(time);
	}, 1);
}
	

function scrollGoTop() {
   var ele = document.getElementById("globalGoTop");
   var gotopHide = document.getElementById('globalGotopHide');
	if(ele && gotopHide){
	  var doc = (document.documentElement.scrollTop) ? document.documentElement : document.body;
	  ele.style.visibility = doc.scrollTop ? 'visible' : 'hidden';
	  gotopHide.innerHTML = '';  
	}    
}
globalMutiScroll(scrollGoTop);

function globalFbPopShow(){
	gId('globalFb').style.display='block';
	gId('globalPopBg').style.display='block';
	if(globalFbSubmitSuccess==false){
		gId('globalFbTextArea').value='';
		gId('globalFbInput').value='';
		gId('globalFbTextAreaTips').style.display='block';
		gId('globalFbEmailWz').style.display='block';
	}
}
function globalFbPopHide(){
	gId('globalPopBg').style.display='none';
	gId('globalFb').style.display='none';
	gId('globalFbResult').style.display='none';
}
function globalFbPopNewShow(){
	gId('globalPopBg').style.display='block';
	gId('globalFb').style.display='none';
	gId('globalFbResult').style.display='block';
}
function globalFbPopNewHide(){
	gId('globalFbResult').style.display='none';
	gId('globalPopBg').style.display='none';
}
function globalFbClose(){	
	gId('globalPopBg').style.display='none';
	gId('globalFb').style.display='none';
}

function globalShowWx(){
	gId("wxFloatHover").style.display="block";	
}
function globalHideWx(){
	gId("wxFloatHover").style.display="none";	
}