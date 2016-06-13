//// JQUERY PLUGIN - EASING
jQuery.easing["jswing"]=jQuery.easing["swing"];jQuery.extend(jQuery.easing,{def:"easeOutQuad",swing:function(a,b,c,d,e){return jQuery.easing[jQuery.easing.def](a,b,c,d,e)},easeInQuad:function(a,b,c,d,e){return d*(b/=e)*b+c},easeOutQuad:function(a,b,c,d,e){return-d*(b/=e)*(b-2)+c},easeInOutQuad:function(a,b,c,d,e){if((b/=e/2)<1)return d/2*b*b+c;return-d/2*(--b*(b-2)-1)+c},easeInCubic:function(a,b,c,d,e){return d*(b/=e)*b*b+c},easeOutCubic:function(a,b,c,d,e){return d*((b=b/e-1)*b*b+1)+c},easeInOutCubic:function(a,b,c,d,e){if((b/=e/2)<1)return d/2*b*b*b+c;return d/2*((b-=2)*b*b+2)+c},easeInQuart:function(a,b,c,d,e){return d*(b/=e)*b*b*b+c},easeOutQuart:function(a,b,c,d,e){return-d*((b=b/e-1)*b*b*b-1)+c},easeInOutQuart:function(a,b,c,d,e){if((b/=e/2)<1)return d/2*b*b*b*b+c;return-d/2*((b-=2)*b*b*b-2)+c},easeInQuint:function(a,b,c,d,e){return d*(b/=e)*b*b*b*b+c},easeOutQuint:function(a,b,c,d,e){return d*((b=b/e-1)*b*b*b*b+1)+c},easeInOutQuint:function(a,b,c,d,e){if((b/=e/2)<1)return d/2*b*b*b*b*b+c;return d/2*((b-=2)*b*b*b*b+2)+c},easeInSine:function(a,b,c,d,e){return-d*Math.cos(b/e*(Math.PI/2))+d+c},easeOutSine:function(a,b,c,d,e){return d*Math.sin(b/e*(Math.PI/2))+c},easeInOutSine:function(a,b,c,d,e){return-d/2*(Math.cos(Math.PI*b/e)-1)+c},easeInExpo:function(a,b,c,d,e){return b==0?c:d*Math.pow(2,10*(b/e-1))+c},easeOutExpo:function(a,b,c,d,e){return b==e?c+d:d*(-Math.pow(2,-10*b/e)+1)+c},easeInOutExpo:function(a,b,c,d,e){if(b==0)return c;if(b==e)return c+d;if((b/=e/2)<1)return d/2*Math.pow(2,10*(b-1))+c;return d/2*(-Math.pow(2,-10*--b)+2)+c},easeInCirc:function(a,b,c,d,e){return-d*(Math.sqrt(1-(b/=e)*b)-1)+c},easeOutCirc:function(a,b,c,d,e){return d*Math.sqrt(1-(b=b/e-1)*b)+c},easeInOutCirc:function(a,b,c,d,e){if((b/=e/2)<1)return-d/2*(Math.sqrt(1-b*b)-1)+c;return d/2*(Math.sqrt(1-(b-=2)*b)+1)+c},easeInElastic:function(a,b,c,d,e){var f=1.70158;var g=0;var h=d;if(b==0)return c;if((b/=e)==1)return c+d;if(!g)g=e*.3;if(h<Math.abs(d)){h=d;var f=g/4}else var f=g/(2*Math.PI)*Math.asin(d/h);return-(h*Math.pow(2,10*(b-=1))*Math.sin((b*e-f)*2*Math.PI/g))+c},easeOutElastic:function(a,b,c,d,e){var f=1.70158;var g=0;var h=d;if(b==0)return c;if((b/=e)==1)return c+d;if(!g)g=e*.3;if(h<Math.abs(d)){h=d;var f=g/4}else var f=g/(2*Math.PI)*Math.asin(d/h);return h*Math.pow(2,-10*b)*Math.sin((b*e-f)*2*Math.PI/g)+d+c},easeInOutElastic:function(a,b,c,d,e){var f=1.70158;var g=0;var h=d;if(b==0)return c;if((b/=e/2)==2)return c+d;if(!g)g=e*.3*1.5;if(h<Math.abs(d)){h=d;var f=g/4}else var f=g/(2*Math.PI)*Math.asin(d/h);if(b<1)return-.5*h*Math.pow(2,10*(b-=1))*Math.sin((b*e-f)*2*Math.PI/g)+c;return h*Math.pow(2,-10*(b-=1))*Math.sin((b*e-f)*2*Math.PI/g)*.5+d+c},easeInBack:function(a,b,c,d,e,f){if(f==undefined)f=1.70158;return d*(b/=e)*b*((f+1)*b-f)+c},easeOutBack:function(a,b,c,d,e,f){if(f==undefined)f=1.70158;return d*((b=b/e-1)*b*((f+1)*b+f)+1)+c},easeInOutBack:function(a,b,c,d,e,f){if(f==undefined)f=1.70158;if((b/=e/2)<1)return d/2*b*b*(((f*=1.525)+1)*b-f)+c;return d/2*((b-=2)*b*(((f*=1.525)+1)*b+f)+2)+c},easeInBounce:function(a,b,c,d,e){return d-jQuery.easing.easeOutBounce(a,e-b,0,d,e)+c},easeOutBounce:function(a,b,c,d,e){if((b/=e)<1/2.75){return d*7.5625*b*b+c}else if(b<2/2.75){return d*(7.5625*(b-=1.5/2.75)*b+.75)+c}else if(b<2.5/2.75){return d*(7.5625*(b-=2.25/2.75)*b+.9375)+c}else{return d*(7.5625*(b-=2.625/2.75)*b+.984375)+c}},easeInOutBounce:function(a,b,c,d,e){if(b<e/2)return jQuery.easing.easeInBounce(a,b*2,0,d,e)*.5+c;return jQuery.easing.easeOutBounce(a,b*2-e,0,d,e)*.5+d*.5+c}})

//反馈脚本
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

    var NewFbButton = document.getElementById('NewFbButton');
	if(!NewFbButton)return;
    NewFbButton.onclick = globalFbPopShow;

    var globalFbHtml = "";
    globalFbHtml += ("<div class=\"globalFbWz\"><p>Hi~我是育婴之家网产品经理聆，</p><p>欢迎您给我们提产品的使用感受和建议。</p></div><form action=\"/front/suggestion/putSuggestion.do\" onsubmit=\"return globalFbSubmit(this);\" target=\"actionframe\" method=\"post\" id=\"correctPageForm\"><div class=\"globalFbCon\"><input type=\"hidden\" id=\"userID\" name=\"userID\" value=\"-1\"/><input type=\"hidden\" id=\"userName\" name=\"userName\" value=\"默认用户名\"/><input type=\"hidden\" id=\"url\" name=\"url\" value=\"#\"/><input type=\"hidden\" id=\"htmlTitle\" name=\"htmlTitle\"  value=\"默认title\"/><input type=\"hidden\" id=\"browser\" name=\"browser\" value=\"\" /><input type=\"hidden\" id=\"browserVersion\" name=\"browserVersion\" value=\"\"/><p>您要反馈的问题：<span id=\"globalFbConTips\" class=\"globalFbTips\"></span></p><div class=\"globalFbContent\"><textarea class=\"globalFbInput\" style=\"height:(this.scrollHeight+5)+'px');overflow:auto;\" id=\"globalFbTextArea\"  name=\"textContent\"  onblur='globalFbTaBlur(this)'  onkeyUp='globalFbTextareaCheck(this)'></textarea><textarea class=\"globalFbInput\" id=\"globalFbTextAreaTips\" onfocus='globalFbTaFocus()' >欢迎在这里输入您的疑问和建议，我们会及时处理。</textarea><span class=\"globalFbQuoteLeft\"></span><span class=\"globalFbQuoteRight\"></span></div></div><div class=\"globalFbCon\" id=\"globalFbContact\"><p>您的联系方式：<span id=\"globalFbEmailTips\" class=\"globalFbTips\"></span></p><div id=\"globalFbEmailCon\" class=\"globalFbEmailCon\"><input type=\"text\" name=\"emailAddress\" onblur=\"globalFbEmailBlur(this)\" id=\"globalFbInput\" class=\"globalFbEmailInput\"> <input value=\"请留下您的QQ或邮箱，便于我们联系您\" id=\"globalFbEmailWz\" class=\"globalFbEmailInput\" onfocus=\"globalFbEmailFocus()\"></div></div><input type=\"submit\" class=\"globalFbSubmit\" value=\"\" /><div id=\"globalFbError\">如遇功能异常错误请提交至<a href=\"http://bbs.yaolan.com/board_369.aspx\" target=\"_blank\">错误反馈</a>板块</div></form><iframe width=\"0\" height=\"0\" name=\"actionframe\" style=\"display: none;\"></iframe><span class=\"globalFbPm\"></span><a href=\"javascript:globalFbClose()\" class=\"globalFbClose\" title=\"关闭\" id=\"globalFbClose\"  target=\"_self\">关闭</a>");
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
    if(text.length>200) {
        gId("globalFbConTips").innerHTML="*内容长度不能超过200个字符！";
    }
    else    {
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
    var arr = document.cookie.match(new RegExp("(^| )"+name+"=([^;]*)(;|jQuery)"));
    if(arr != null) return arr[2]; return null;
}

function TrimFeedback(str){
    return str.replace(/(^\s*)|(\s*jQuery)/g, "");
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
    if(htmlTitle!=null) {
        document.getElementById("htmlTitle").value =htmlTitle;
    }
     var url=document.URL;
     if(url!=null) {
        document.getElementById("url").value=url;
     }
 }  
 


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

//千人千面脚本
function fixAppendDom(){//添加千人千面HTML结构
    var fixParentHtmlStr = '<div id="newExpertOldHtml" class="new-expertOnlineOld"></div>',
        //千人千面父级HTML结构
        fixHtmlArr = [//千人千面内容HTML结构
            '<!--[if lte IE 8]>',
                    '<style type="text/css">',
                        '.new-expert-pop-con .arrow-2{right:21px;}',
                    '</style>',
            '<![endif]-->',
            '<style type="text/css">',
                '.new-emlink-ad-box:hover{background:none;}',
            '</style>',
            '<div class="new-expertOnlinebg" id="newExpertOnlinebg"></div>',
            '<div class="new-expertOnline" id="newExpertOnline">',
                '<a href="javascript:void(0);" title="关闭侧边栏" id="newExpOlClose" class="new-expOlClose" target="_self">×</a>',
                '<div class="new-expertMidbox">',
                    '<a href="javascript:void(0);" target="_self" class="new-emMidlink-top" id="newExpOlOpen">',
                        '<span class="arrow arrow-left"></span>',
                        '<span class="new-emMidlink-top-box"></span>',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '猜你喜欢',
                            '</span>',
                        '</span>',
                    '</a>',
                    
                    /*
                     * '<a href="http://www.yaolan.com/topic/ylapp/index.shtml" target="_blank" class="new-emlink new-emMidlink-1" title="摇篮孕育">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '摇篮孕育',
                            '</span>',
                        '</span>',
                    '</a>',
                     * */
                    
                    '<a href="http://ask.yaolan.com/ask.html?fr=sdh" target="_blank" class="new-emlink new-emMidlink-2" title="专家问答">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '咨询专家',
                            '</span>',
                        '</span>',
                    '</a>',
                    '<a href="http://abc.yaolan.com/" target="_blank" class="new-emlink new-emMidlink-3" title="成长阶梯">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '成长阶梯',
                            '</span>',
                        '</span>',
                    '</a>',
					 '<a href="javascript:void(0);" target="_self" class="new-emlink new-emBotlink-1">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-weixin-box">',
                            '<span class="new-expert-weixin-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                            '</span>',
                        '</span>',
                    '</a>',
                '</div>',
               
            '</div>',

 '<div class="new-expertBotBox">',
					'<a href="javascript:void(0);" class="new-emMidlink-top" target="_self" id="newExpOlOpen2" style="display:none;">',
                        '<span class="arrow arrow-left"></span>',
                        '<span class="new-emMidlink-top-box"></span>',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '猜你喜欢',
                            '</span>',
                        '</span>',
                    '</a>',
					'<a href="javascript:void(0);" class="new-emlink new-emBotlink-3" target="_self" id="NewShareButton">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '分享',
                            '</span>',
                        '</span>',
                    '</a>',
                    '<a href="javascript:void(0);" class="new-emlink new-emBotlink-0" target="_self" id="NewFbButton">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '意见反馈',
                            '</span>',
                        '</span>',
                    '</a>',                   
                    '<a href="javascript:void(0);" class="new-emlink new-emBotlink-2" target="_self" id="toUp">',
                        '<span class="new-expert-icon"></span>',
                        '<span class="new-expert-tip-box">',
                            '<span class="new-expert-tip-con">',
                                '<span class="arrow arrow-right arrow-edec"></span>',
                                '<span class="arrow arrow-right arrow-white"></span>',
                                '返回顶部',
                            '</span>',
                        '</span>',
                    '</a>',
                '</div>',


            '<div class="new-expert-signInBox" id="newExpertSignInBox">',
                '<div class="new-expert-signInTopBox">',
                    '<span class="arrow arrow-left top-bg-arrow"></span>',
                    '<div class="new-expert-signInTopCon">',
                        '<div class="new-expert-sign-user-box">',
                            '<a href="http://my.yaolan.com/" target="_blank" class="new-expert-user-chbox new-expert-user-name text-ellipsis" title="" id="newExUser"></a>',
                            '<span class="new-expert-user-chbox new-expert-user-age text-ellipsis" id="newExUserAge"></span>',
                            '<a href="http://my.yaolan.com/UserSetting/BabyInfo.aspx" class="new-expert-icon new-expert-user-chbox new-expert-user-edit"></a>',
                        '</div>',
                    '</div>',
                    '<a href="javascript:void(0);" class="new-expert-icon new-expert-sign-btn" id="signBtn">每日签到</a>',
                    '<div class="new-expert-userImgBox">',
                        '<a href="http://my.yaolan.com/" target="_blank" title="" id="nexUserImg">',
                            '<img src="" >',
                        '</a>',
                    '</div>',
                    '<div class="new-expert-icon new-expert-user-day-shadow"></div>',
                    '<input type="button" value="今天" class="new-expert-user-day-box j-day-btn" id="nexDay" data-type="0">',
                    '<input type="button" value="" class="arrow arrow-left new-expert-date-btn new-expert-toleft-btn j-day-btn" id="nexPrevDate">',
                    '<div class="new-expert-user-date-box" id="newExDate"></div>',
                    '<input type="button" value="" class="arrow arrow-right new-expert-date-btn new-expert-toright-btn j-day-btn" id="nexNextDate">',
                '</div>',
                '<div class="new-expert-sd-box">',
                    '<div class="new-expert-state-box">',
                        '<div class="new-expert-state-note-box clearfix">',
                            '<a href="javascript:void(0);" class="fr new-expert-state-note">',
                                '<i class="new-expert-icon"></i>记录小豆子的状态',
                            '</a>',
                        '</div>',
                        '<div class="new-expert-state-txt" id="nexStatus"></div>',
                    '</div>',
                    '<div class="new-expert-data-box">',
                        '<div class="new-expert-data-title">妈妈必读</div>',
                        '<a href="javascript:void(0);" title="与同龄妈妈交流" target="_blank" class="new-expert-data-em" id="newExTalk">与同龄妈妈交流</a>',
                        '<ul class="new-expert-data-ul" id="nexDataUl"></ul>',
                    '</div>',
                    '<div class="new-expert-data-box">',
                        '<div class="new-expert-data-title">摇篮推荐</div>',
                        '<span class="new-expert-recommend-line"></span>',
                        '<div class="new-expert-recommend-box clearfix" id="nexRecBox"></div>',
                    '</div>',
                '</div>',
            '</div>',
            '<div class="new-expert-pop-box" id="newExPop">',
                '<div class="new-expert-pop-con">',
                    '<span class="arrow arrow-left arrow-1 arrow-rotate"></span>',
                    '<span class="arrow arrow-left arrow-2 arrow-rotate"></span>',
                    '<div class="new-expert-pop-txt"></div>',
                '</div>',
            '</div>',
            '<div class="new-expert-form-box" id="nexFormBox">',
                '<form class="new-expert-form-con" id="nexForm">',
                    '<span class="arrow arrow-right arrow-edec"></span>',
                    '<span class="arrow arrow-right arrow-white"></span>',
                    '<span class="new-expert-question">宝宝在不同时期妈妈们最需要关注什么问题？</span>',
                    '<span class="new-expert-inp-title">请输入宝宝生日/预产期</span>',
                    '<input type="text" readonly="readonly" class="Wdate new-expert-inp", onClick="WdatePicker()" id="nexInp">',
                    '<input type="hidden" id="nexInpHidden">',
                    '<input type="submit" value="确定" class="new-expert-sub">',
                '</form>',
            '</div>'
        ],
        fixHtmlStr = fixHtmlArr.join(''),
    jQuerybody = jQuery('body');
    jQuerybody.append(fixParentHtmlStr);//添加千人千面父级HTML结构
    jQuery('#newExpertOldHtml')[0].innerHTML = fixHtmlStr;//渲染千人千面内容HTML结构
};



var globalCookie = {
    setCookie:function(name,value,time){//设置cookie
        var self = this,
            strsec = self.getsec(time),
            exp = new Date();
        exp.setTime(exp.getTime() + strsec*1);
        document.cookie = name + "="+ escape(value) + ";expires=" + exp.toGMTString();
    },
    getsec : function(str){//时间转换
        //alert(str);
            var self = this,
                str1=str.substring(1,str.length)*1,
                str2=str.substring(0,1);
           if (str2=="s")
           {
                return str1*1000;
           }
           else if (str2=="h")
           {
               return str1*60*60*1000;
           }
           else if (str2=="d")
           {
               return str1*24*60*60*1000;
           }
            //这是有设定过期时间的使用示例：
            //s20是代表20秒
            //h是指小时，如12小时则是：h12
            //d是天数，30天则：d30
    },
    getCookie : function(name){//获得Cookie
        var self = this,
            arr,
            reg=new RegExp("(^| )"+name+"=([^;]*)(;|jQuery)");
        if(arr=document.cookie.match(reg)){
            return (arr[2]);
        }else{
            return null;
        }
    },
    delCookie : function(name){//清除COOKIE
        var self = this,
            exp = new Date(),
            cval=self.getCookie(name);
        exp.setTime(exp.getTime() - 1);
        if(cval!=null)
            document.cookie= name + "="+cval+";expires="+exp.toGMTString();
    }
};

/*var fixDataApi = {//数据接口地址
    "signUrl" : "http://dev.qrqm.yaolan.com/index/daily?callback=?",
    "feedBackUrl" : "http://dev.qrqm.yaolan.com/index/feedBack?callback=?",
    "showDataUrl" : "http://dev.qrqm.yaolan.com/index/getShowData?callback=?",
    "popUrl" : "http://dev.qrqm.yaolan.com/index/index?callback=?"
};*/

var fixDataApi = {//数据接口地址
    "signUrl" : "http://open.api.yaolan.com/api/qrqm/daily?callback=?",//签到
    "feedBackUrl" : "http://open.api.yaolan.com/api/qrqm/feedback?callback=?",//反馈
    "showDataUrl" : "http://open.api.yaolan.com/api/qrqm/detail?callback=?",//详细内容
    "popUrl" : "http://open.api.yaolan.com/api/qrqm/pop?callback=?"//是否弹窗
};



function fixSignFun(){ //签到功能
    var jQuerysignBtn = jQuery('#signBtn'),
        jQuerynewExPop = jQuery('#newExPop'),
        jQuerynewExPopTxt = jQuerynewExPop.find('div.new-expert-pop-txt')[0],
        nexHtml = '',
        url = fixDataApi.signUrl;
        
    jQuerysignBtn.on('click',function(){
        var jQueryself = jQuery(this);
        
        function c(r){//签到回调函数
            var code = r.code,
                msg = r.msg,
                data = r.data,
                isRunWeek = data.isRunWeek,
                RunDayCount = data.RunDayCount,
                scoreCount = 0;//经验值
                    //console.log(data);
                function nexHide(){
                  jQuerynewExPop.hide();
                  jQueryself.off('click');
                };
                if(code==1){
                    //alert(msg);
                    jQueryself.addClass('nesb-act').text('已签到');
                    /*switch(isRunWeek)
                    {
                      case 0:
                        nexHtml = '您已经连续'+RunDayCount+'天签到<br>连续签到7天将有摇豆奖励哦~';
                        break;
                      case 1:
                        nexHtml = '您已经连续7天签到<br>恭喜获得摇豆奖励！';
                        break;
                    }*/
                    scoreCount = RunDayCount<=5 ? RunDayCount : 5;
                    
                    nexHtml = '您已经连续签到'+RunDayCount+'天，获得'+scoreCount+'个经验！';
                    if(jQuerynewExPopTxt){
                        jQuerynewExPopTxt.innerHTML = nexHtml;
                    }
                    jQuerynewExPop.show();
                    setTimeout(nexHide, 2000);
                }else{
                    //alert(msg);
                    Login();
                }
        };
        jQuery.ajax({
            url : url,
            type: "GET",
            dataType : "jsonp",
            timeout : 5000,
            jsonpCallback : "c",
            success : function(r){
                c(r);
            }
        });
		
		return false;
    });
};


function fixFeedBackFun(){//用户反馈方法
    var url = fixDataApi.feedBackUrl,
        jQuerynexDataUl = jQuery('#nexDataUl'),
        domFirst = 'li a.new-expert-link',
        domSecond = 'li input.new-expert-left-hand',
        domThird = 'li input.new-expert-right-hand';
    jQuerynexDataUl.off('click',domFirst);
    jQuerynexDataUl.off('click',domSecond);
    jQuerynexDataUl.off('click',domThird);

    

    jQuerynexDataUl.on ('click',domFirst,function(){//浏览信息
        var jQueryself = jQuery(this),
            dataId = jQueryself.attr('data-id'),
            selfUrl = jQueryself.attr('href'),
            data = {"point":dataId,"url":selfUrl};
            //console.log(data);
            function c(r){//浏览信息回调函数
                var code = r.code,
                    msg = r.msg;
                if(code == 0){
                    alert(msg);
                }
            };
            jQuery.ajax({
                url : url,
                type : "GET",
                dataType : "jsonp",
                data : data,
                timeout : 5000,
                jsonpCallback : "c",
                success : function(r){
                    c(r);
                }
            });
    });
    function likeFun(dom,dclass){//支持,不支持通用方法
        jQuerynexDataUl.on('click',dom,function(){
            var jQueryself = jQuery(this),
                dataId = jQueryself.attr('data-id'),
                linkUrl = jQueryself.parent().find('a:first').attr('href'),
                data = {"point":dataId,"url":linkUrl};
                //console.log(data);
                function c(r){//支持、不支持回调函数
                    var code = r.code,
                        msg = r.msg;
                        switch(code){
                          case 1:
                              //jQueryself.parent().find('a.new-expert-hand').hide();
                              jQueryself.addClass(dclass);
                              jQueryself.parent().find('input.new-expert-hand').addClass('cursor-default').attr('disabled','disabled').off('click');
                              //jQueryself.siblings('input.new-expert-hand').addClass('cursor-default').attr('disabled','disabled').off('click');
                              break;
                          case 0:
                              alert(msg);
                              break;
                        };
                };

                jQuery.ajax({
                    url : url,
                    type : "GET",
                    dataType : "jsonp",
                    data : data,
                    timeout : 5000,
                    jsonpCallback : "c",
                    success : function(r){
                        c(r);
                    }
                });
        });
    };

    likeFun(domSecond,'nelh-act');//支持
    likeFun(domThird,'nerh-act');//不支持

    
};

function fixGetData(qrqmid,type,chosedtime){//请求详情数据方法
    var jQuerynexFormBox = jQuery('#nexFormBox'),
        jQuerynexInp = jQuery('#nexInp'),
        jQuerynexInpHidden = jQuery('#nexInpHidden'),
        jQuerynewExpertSignInBox = jQuery('#newExpertSignInBox'),
        jQuerynexPrevDate = jQuery('#nexPrevDate'),
        jQuerynexNextDate = jQuery('#nexNextDate'),
        jQuerynewExUser = jQuery('#newExUser'),
        jQuerynewExUserAge = jQuery('#newExUserAge'),
        jQuerynewExDate = jQuery('#newExDate'),
        jQuerynexUserImgBox = jQuery('#nexUserImg'),
        jQuerynexUserImg = jQuerynexUserImgBox.find('img'),
        jQuerynexDay = jQuery('#nexDay'),
        jQuerynexStatus = jQuery('#nexStatus'),
        jQuerynexDataUl = jQuery('#nexDataUl'),
        jQuerynexDataUlDom = jQuerynexDataUl[0],
        jQuerynexRecBox = jQuery('#nexRecBox'),
        jQuerynexRecBoxDom = jQuerynexRecBox[0],
        jQuerysignBtn = jQuery('#signBtn'),
        jQuerynewExTalk = jQuery('#newExTalk'),
        url = fixDataApi.showDataUrl,
        WdateVal = jQuerynexInp.val() || globalCookie.getCookie('baby_birth'),
        babyBirth = jQuerynexInpHidden.val(),
        param = {};
        if(babyBirth && babyBirth!=''){
            param = {"qrqmid":qrqmid,"babybirth":babyBirth};
            if(type){//如果有type参数，增加请求参数type
                param = {"qrqmid":qrqmid,"babybirth":babyBirth,"type":type};
                if(chosedtime){//如果有chosedtime参数，增加请求参数chosedtime
                    param = {"qrqmid":qrqmid,"babybirth":babyBirth,"type":type,"chosedtime":chosedtime};
                }
            }
        }else{
            if(WdateVal && WdateVal != ''){
                param = {"qrqmid":qrqmid,"babybirth":WdateVal};
                if(type){//如果有type参数，增加请求参数type
                    param = {"qrqmid":qrqmid,"babybirth":WdateVal,"type":type};
                    if(chosedtime){//如果有chosedtime参数，增加请求参数chosedtime
                        param = {"qrqmid":qrqmid,"babybirth":WdateVal,"type":type,"chosedtime":chosedtime};
                    }
                }
            }
        }
        
        function c(r){//请求详情回调函数
                //console.log(r);
                var userInfo = r.userInfo,
                    NickName = userInfo.NickName,
                    UserID = userInfo.UserID,
                    AvatarUrl = userInfo.AvatarUrl,
                    babyAge = r.babyAge,
                    babyAgeStr = r.babyAgeStr,
                    choosedtime = r.choosedtime,
                    pre = r.pre,
                    next = r.next,
                    data = r.data,
                    mamabidu = 'none',
                    tipsContent = r.tipsContent,
                    content = '',
                    yltuijian = 'none',
                    signShow = r.signShow,
                    sameMama = r.sameMama;
                    if(data != null && data != ''){
                        mamabidu = data.mamabidu;
                        yltuijian = data.yltuijian;
                    }
                    if(tipsContent){
                        content = tipsContent.content;
                    }
                    if(signShow && signShow == 1){
                        jQuerysignBtn.addClass('nesb-act').text('已签到').off('click');
                    }
                jQuerynexFormBox.hide();
                jQuerynexUserImgBox.attr('title',NickName);
                jQuerynexUserImg.attr('src',AvatarUrl);
                jQuerynewExUser.text(NickName);
                jQuerynewExUser.attr('title',NickName);
                jQuerynewExUserAge.text(babyAge);
                jQuerynewExDate.text(choosedtime);
                jQuerynexPrevDate.attr('data-type',pre);
                jQuerynexNextDate.attr('data-type',next);
                jQuerynewExTalk.attr('href',sameMama);
                if(tipsContent && content){//如果有个人状态内容
                    jQuerynexStatus.text(content);
                }else{
                    jQuerynexStatus.hide();
                }
                var jQuerynewExpertDateBtn = jQuery('input.new-expert-date-btn');
                jQuerynewExpertDateBtn.each(function(){//判断前进、后退按钮的data-type，如果为空，不可点击
                    var jQueryself = jQuery(this),
                        dataType = jQueryself.attr('data-type');
                    if(dataType == ''){
                        jQueryself.attr('disabled','disabled');
                    }else{
                        jQueryself.removeAttr('disabled');
                    }
                });
                
                if(babyAgeStr){
                    jQuerynewExUserAge.text(babyAgeStr);
                }
                
                //console.log(mamabidu);
                function biduHtmlRender(){//妈妈必读渲染方法
                    var html =[],
                        pushHtml = '',
                        strHtml = '';
                    if(mamabidu && mamabidu != 'none'){
                        for(var i in mamabidu){
                            var pcurl = mamabidu[i].urls.pcurl,
                                urltitle = mamabidu[i].urltitle;
                            if(pcurl && urltitle){
                                pushHtml = '<li><i class="new-expert-icon new-expert-data-icon new-expert-data-'+i+'"></i><a href="'+pcurl+'" title="'+urltitle+'" target="_blank" class="new-expert-link" data-id="1">'+urltitle+'</a><input type="button" class="new-expert-icon new-expert-hand new-expert-left-hand" data-id="2"><input type="button" class="new-expert-icon new-expert-hand new-expert-right-hand" data-id="-2"></li>';
                                html.push(pushHtml);
                            }
                        }
                        strHtml = html.join('');
                    }
                    
                    return strHtml;
                };
                if(jQuerynexDataUlDom){
                    jQuerynexDataUlDom.innerHTML = biduHtmlRender();//渲染妈妈必读内容
                }

                function tuijianHtmlRender(){//摇篮推荐渲染方法
                    var html = [],
                        pushHtml = ''
                        strHtml = '';
                    if(yltuijian && yltuijian != 'none'){
                        for (var i in yltuijian){
                            var pcurl = yltuijian[i].urls.pcurl,
                                urltitle = yltuijian[i].urltitle,
                                pic = yltuijian[i].pic;

                            if(i<=2){
                                if(pcurl && urltitle && pic){
                                    pushHtml = '<a href="'+pcurl+'" target="_blank" title="'+urltitle+'" class="new-expert-recommend-img-box"><img src="'+pic+'" alt="'+urltitle+'"><span class="new-expert-recommend-bg"></span><em class="new-expert-recommend-em">'+urltitle+'</em></a>';
                                    html.push(pushHtml);
                                }
                            }else{
                                if(pcurl && urltitle){
                                    pushHtml = '<a href="'+pcurl+'" title="'+urltitle+'" target="_blank">'+urltitle+'</a>';
                                    html.push(pushHtml);
                                }
                            }
                        }
                        strHtml = html.join('');
                    }
                    
                    return strHtml;
                };
                if(jQuerynexRecBoxDom){
                    jQuerynexRecBoxDom.innerHTML = tuijianHtmlRender();//渲染摇篮推荐内容
                }
                
                jQuerynewExpertSignInBox.show();
        };
    
    
        jQuery.ajax({
            url : url,
            type : "GET",
            dataType : "jsonp",
            data : param,
            timeout : 5000,
            jsonpCallback : "c",
            success :function(r){
                c(r);
            }
        });
};

function fixPopFun(){//判断是否弹窗，弹页面   
    var url = fixDataApi.popUrl,
        jQuerynexFormBox = jQuery('#nexFormBox'),
        jQuerynexInp = jQuery('#nexInp'),
        jQuerynexInpHidden = jQuery('#nexInpHidden'),
        jQuerynewExpertSignInBox = jQuery('#newExpertSignInBox'),
        jQuerynewExpOlClose = jQuery('#newExpOlClose'),
        jQuerynewExpOlOpen = jQuery('#newExpOlOpen'),
		jQuerynewExpOlOpen2 = jQuery('#newExpOlOpen2'),
		
        jQuerynewExpertOldHtml = jQuery('#newExpertOldHtml'),
        jQuerynewExpertOnlinebg = jQuery('#newExpertOnlinebg'),
        jQuerynewExpertOnline = jQuery('#newExpertOnline'),
        jQuerynexFormBox = jQuery('#nexFormBox'),
        jQuerynewExPop = jQuery('#newExPop');
        webUrl = window.location.href,
        param = {"url":webUrl};
        //console.log(param);

    jQuerynewExpOlClose.off('click');
    jQuerynewExpOlOpen.off('click');
	jQuerynewExpOlOpen2.off('click');
    jQuerynewExpOlClose.on('click',function(){      
        jQuerynewExpertSignInBox.hide();
        jQuerynewExpertOnlinebg.hide();
        jQuerynewExpertOnline.hide();
        jQuerynexFormBox.hide();
        jQuerynewExPop.hide();
		
		jQuery("#newExpOlOpen2").show();

    });

    jQuerynewExpOlOpen.on('click',function(){
        jQuery(".new-expert-tip-box",this).hide();
		return false;
    });
	 jQuerynewExpOlOpen2.on('click',function(){
        
		
		
		
       //jQuerynewExpertSignInBox.show();
        jQuerynewExpertOnlinebg.show();
        jQuerynewExpertOnline.show();
       // jQuerynexFormBox.show();
       // jQuerynewExPop.hide();
		//if(window['needOpennewExpOlOpen']){
					jQuerynewExpOlOpen.click();
		//	}
		
		jQuery("#newExpOlOpen2").hide();
		return false;
    });
	/**
    jQuerynewExpOlOpen.on('mouseout',function(){
        jQuery(".new-expert-tip-box",this).css('display','');
    });
**/



    function c(r){//弹窗回调函数
        var code = r.code,
                msg = r.msg,
                data = r.data,
                isOpenPop = data.isOpenPop,
                isOpenShow = data.isOpenShow,
                isLogin = data.isLogin,
                babyBirth = data.babyBirth;
            if(code == 0){
                alert(msg);
            }else{
                //console.log(data);
                jQuerynexInp.val(babyBirth);
                jQuerynexInpHidden.val(babyBirth);
                
                if(isOpenPop == 1){//弹出日期浮层
                    var datePop = globalCookie.getCookie('date_pop');
                    if(!datePop || datePop == ''){//判断1天内用户是否提交生日
                        jQuerynexFormBox.show();
                            jQuerynewExpOlOpen.on('click',function(){
                                  if( jQuerynexFormBox.is(':visible')){
                                     jQuerynexFormBox.hide();
                                }
								return false;
                            });
							

                        //globalCookie.setCookie('date_pop','1','d1');//若没有，记一下COOKIE
                    }
                    //jQuerynexFormBox.show();
                }

                if(isOpenShow == 1){//弹出内容层
                    
                    jQuerynewExpOlOpen.on('click',function(){



                        var isShow = jQuerynewExpertSignInBox.is(':visible');
                        if(isShow){
                            jQuerynewExpertSignInBox.hide();
                        }else{
                            switch(isLogin)
                            {
                                case 0 : 
                                    fixGetData(3);
                                    fixGetDayData('.j-day-btn',4); //执行按天请求数据
                                break;
                                case 1 : 
                                    fixGetData(2);
                                    fixGetDayData('.j-day-btn',5); //执行按天请求数据
                                break;
                            };
                            jQuerynewExpertSignInBox.show();
                        }
						return false;
                    });
					
					
					if( jQuery(window).width() >1900)
					{
						window.needOpennewExpOlOpen = true;
				//		jQuerynewExpOlOpen.click();
					}
					
                }else{
                    if(isLogin == 0){//未登录
                        var cookieBirth = globalCookie.getCookie('baby_birth');
                        jQuerynexInpHidden.val(cookieBirth);
                        jQuerynewExpOlOpen.on('click',function(){
                            //console.log(dataPop);
                            var datePop = globalCookie.getCookie('date_pop');
                            if(!datePop || datePop == ''){//未登录且没有COOKIE
                                jQuerynexFormBox.show();
                                 jQuerynewExpOlOpen.on('click',function(){
                                      if( jQuerynexFormBox.is(':visible')){
                                         jQuerynexFormBox.hide();
                                    }else{
                                        jQuerynexFormBox.show();
                                    }
                                });
                                var isShow = jQuerynewExpertSignInBox.is(':visible');
                                if(isShow){
                                    jQuerynewExpertSignInBox.hide();
                                    jQuerynexFormBox.hide();
                                }
                            }else{//未登录且有COOKIE,用户已提交生日
                                var isShow = jQuerynewExpertSignInBox.is(':visible');
                                if(isShow){
                                    jQuerynewExpertSignInBox.hide();
                                }else{
                                    fixGetData(3);
                                    fixGetDayData('.j-day-btn',4); //执行按天请求数据
                                    jQuerynewExpertSignInBox.show();
                                }
                            }
							return false;
                        });
						
						
                        
                    }
                }
            }
    };

    jQuery.ajax({
        url : url,
        type : "GET",
        dataType : "jsonp",
        data : param,
        timeout : 5000,
        jsonpCallback : "c",
        success : function(r){
            c(r);
			if(window['needOpennewExpOlOpen']){
					jQuerynewExpOlOpen.click();
			}
        }
    });
    
    
};


function fixGetDayData(selector,qrqmid){//按天数请求
    var jQuerydom = jQuery(selector),
        dataType,
        jQuerynewExDate = jQuery('#newExDate'),
        jQuerynexDay = jQuery('#nexDay'),
        chosedtime = '';
        jQuerydom.off('click');
        jQuerydom.on('click',function(){
            var jQueryself = jQuery(this);
            dataType = jQueryself.attr('data-type');
            if(dataType == 0){
                fixGetData(qrqmid,dataType);//执行请求数据
                //jQueryself.attr('disabled','disabled').addClass('neud-act');
                jQueryself.attr('disabled','disabled').removeClass('neud-act');
            }else{
                chosedtime = jQuerynewExDate.text();
                //alert(chosedtime);
                fixGetData(qrqmid,dataType,chosedtime);//执行请求数据
                jQuerynexDay.removeAttr('disabled','disabled').addClass('neud-act');
            }
            return false;
        });

};


function globalToTopFun(){//回到顶部功能
    var jQuerytoUp = jQuery('#toUp'),
        jQuerybodyHtml = jQuery('body,html');
    jQuerytoUp.off('click');
    jQuerytoUp.on('click',function(){
        //jQuerybodyHtml.animate({scrollTop:0},200); 
        jQuerybodyHtml.scrollTop(0);
		return false;
    });
};

function fixDateFormSub(){//日期提交方法
    var jQuerynexForm = jQuery('#nexForm'),
        jQuerynexInp = jQuery('#nexInp'),
        jQuerynexInpHidden = jQuery('#nexInpHidden');

    jQuerynexForm.submit(function(){
        var nexInpVal = jQuerynexInp.val();
        if(nexInpVal == ""){
            alert('请输入宝宝生日!');
        }else{
            jQuerynexInpHidden.val(nexInpVal);
            fixGetData(1); //执行请求详情数据
            fixGetDayData('.j-day-btn',4); //执行按天请求数据
            globalCookie.setCookie('date_pop','1','d10000');//用户提交生日成功，记一下COOKIE
            globalCookie.delCookie('baby_birth');//清除上次记下的COOKIE
            globalCookie.setCookie('baby_birth',nexInpVal,'d10000');//用户提交生日成功，记一下生日的COOKIE
        }
        return false;
    });
    
};

function bindTipAni()
{
	
	jQuery(".new-emlink").hover(function(){
		tipShow(jQuery(".new-expert-tip-box",this));		
		tipShow(jQuery(".new-expert-weixin-box",this));				
	},function(){
		tipHiden(jQuery(".new-expert-tip-box",this));	
		tipHiden(jQuery(".new-expert-weixin-box",this));				
	});
	
	
	
	jQuery(".new-emMidlink-top").hover(function(){
		tipShow(jQuery(".new-expert-tip-box",this));					
	},function(){
		tipHiden(jQuery(".new-expert-tip-box",this));		
	});
	
	tipShow(jQuery(".new-expert-tip-box",jQuery("#newExpOlOpen")));			
	setTimeout(function(){
		tipHiden(jQuery(".new-expert-tip-box",jQuery("#newExpOlOpen")));		
	},5000);
	
	
	
	
};

function tipShow(jdom)
	{		
		if(jdom.length<1 ||jdom.is(":visible") ) return;
		jdom[0].showing=1;
		
		var pleft= jdom[0].pleft =  jdom[0].pleft || parseInt(jdom.css('left'),10) ;
		if(isNaN(pleft)) return;
		jdom.css({
			left: pleft - 85,
			display:"block",
			opacity:0
		});
		jdom.animate({
			left: pleft+"px",
			opacity:[1,"easeInQuad"]
		},200,'easeOutQuad',function(){
			jQuery(this).css({
				left: pleft,
				display:"block",
				opacity:1
			});
		});
	}
	function tipHiden(jdom)
	{
		if(jdom.length<1 )
		{
			return;
		}
		var pleft= jdom[0].pleft =  jdom[0].pleft || parseInt(jdom.css('left'),10) ;
		if(isNaN(pleft)) return;
		jdom.animate({
			left:  pleft - 85,
			opacity:[0,"easeOutQuad"]
		},100,'easeInQuad',function(){
			jQuery(this).css({
				display:"none",
				opacity:0,
				left:pleft
			})
		});
	}

//百度分享
function bdshareInit()
{
	var s = ['<div class="bdsharebuttonbox" data-tag="share_1" style="width: auto !important;">',
				'<a class="bds_weixin" style="background-position: 0 -1612px;background-position: 0 -1612px !important;" data-cmd="weixin"></a>',
				'<a class="bds_qzone" style="background-position: 0 -52px;background-position: 0 -52px !important;"  data-cmd="qzone" href="#"></a>',
				'<a class="bds_tsina" style="background-position: 0 -104px;background-position: 0 -104px !important;" data-cmd="tsina"></a>',
				'<a class="bds_baidu"  data-cmd="baidu"></a>',
				'<a class="bds_tqq" style="background-position: 0 -260px;background-position: 0 -260px !important;" data-cmd="tqq"></a>',
				'<a class="bds_more" style="background: url(http://bdimg.share.baidu.com/static/api/img/share/icons_0_32.png?v=acc572ea.png)!important;  background-position: 0 0px!important;" data-cmd="more">更多</a>',
			'</div>'].join("");
	var bdshare= document.createElement("div");
	bdshare.id="bdshare_newfix";	
	//bdshare.style.display="none";
	
	bdshare.innerHTML = s;
	jQuery("#NewShareButton").parent().append(bdshare);
	//document.body.appendChild(bdshare);
	jQuery(bdshare).css({
		display:"none",
		position:"absolute",
		"top":0,
		"left":"-200px",
		"width":"200px"
	});
	
	
	window._bd_share_config = {
		/**
		common : {
			bdText : '自定义分享内容',	
			bdDesc : '自定义分享摘要',	
			bdUrl : '自定义分享url地址', 	
			bdPic : '自定义分享图片'
		},
		image : [{
			viewType : 'list',
			viewPos : 'top',
			viewColor : 'black',
			viewSize : '32',
			viewList : ['qzone','tsina','huaban','tqq','renren']
		}],
		selectShare : [{
			"bdselectMiniList" : ['qzone','tqq','kaixin001','bdxc','tqf']
		}]
		**/
		share : [{
			"bdSize" : 32
		}]
	}
	with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?cdnversion='+~(-new Date()/36e5)];
	
	//jQuery("#NewShareButton").hover(function(){});
	jQuery("#NewShareButton").unbind("mouseenter").unbind("mouseleave"); 
	

	
	
	var handle = null;
	function hoverShow()
	{
		//jQuery("#bdshare_newfix").show();
		jQuery(bdshare).css('top',jQuery("#NewShareButton").position().top);
		 tipShow(jQuery("#bdshare_newfix"));
		if(handle)
		{
			clearTimeout(handle);
		}
	}
	function hoverHide()
	{
		handle = setTimeout(function(){			
						jQuery("#bdshare_newfix").hide();	
						//tipHiden(jQuery("#bdshare_newfix"));
		},200);	
	}
	
	jQuery("#NewShareButton").hover(hoverShow,hoverHide);
	jQuery("#bdshare_newfix").hover(hoverShow,hoverHide);
	
}



jQuery(function(){	
	try{	
		
		fixAppendDom();//添加千人千面HTML结构    
		fixPopFun();//执行弹出层方法
		fixSignFun();//执行签到
		fixFeedBackFun();//执行用户反馈
		globalToTopFun();//执行回到顶部方法
		fixDateFormSub();//执行日期提交方法
		 bindTipAni();//tips动画初始化		
		bdshareInit();
	}catch(e){
		jQuery("#newExpertOldHtml").hide();
	}	
});
