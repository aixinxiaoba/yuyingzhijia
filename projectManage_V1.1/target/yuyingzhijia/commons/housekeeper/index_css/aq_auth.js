(function(i,h){var j=function(){};var c=i,b=h,a="http:"===document.location.protocol?"http://":"https://",f=a+"outer.anquan.org",e=a+"www.anquan.org",g=a+"static.anquan.org";j.prototype={constructor:j,get_id:function(d){return b.getElementById(d)},filter:function(d){d=d||"";return d.replace(/</g,"&lt;").replace(/>/g,"&gt;").replace(/'/g,"&#39;").replace(/"/g,"&quot;")},listen_to:function(m,l,d){if(m.addEventListener){m.addEventListener(l,d,false)}else{if(m.attachEvent){m.attachEvent("on"+l,function(n){n||(n=c.event);if(!n.target){n.target=n.srcElement}d(n)},false)}}},ready:function(d){var n=false;var o=[];var p;ready=function(q){if(n){q.call(document)}else{o.push(function(){return q.call(this)})}return this};var l=function(){for(var q=0;q<o.length;q++){o[q].apply(document)}o=null};var m=function(q){if(n){return}n=true;l.call(window);if(document.removeEventListener){document.removeEventListener("DOMContentLoaded",m,false)}else{if(document.attachEvent){document.detachEvent("onreadystatechange",m);if(window==window.top){clearInterval(p);p=null}}}};if(document.addEventListener){document.addEventListener("DOMContentLoaded",m,false)}else{if(document.attachEvent){document.attachEvent("onreadystatechange",function(){if((/loaded|complete/).test(document.readyState)){m()}});if(window==window.top){p=setInterval(function(){try{n||document.documentElement.doScroll("left")}catch(q){return}m()},5)}}}ready(d)},getElementsByClassName:function(p,n,o){var l=p.getElementsByTagName(n);var d=[];for(var m=0;m<l.length;m++){if(l[m].className==o){d[d.length]=l[m]}}return d},script_onload:function(d,m,n){var l=b.createElement("script");if(l.readyState){l.onreadystatechange=function(){if(l.readyState=="loaded"||l.readyState=="complete"){l.onreadystatechange=null;n()}}}else{l.onload=function(){n()}}l.src=m;l.type="text/javascript";l.setAttribute("async","true");d.appendChild(l)},getElByAttr:function(l,r){var o=[],m=b.getElementsByTagName(r||"*");for(var p=0,q=m.length;p<q;p++){var s=m[p],d=true;for(var n=0;n<l.length;n++){if(!s.getAttribute(l[n])){d=false;break}}if(d){o.push(s)}}return o},getRealWarpNode:function(o,l){var d,l=l||0;var m=o.parentNode;var n=function(p){return p.getAttribute("key")&&p.getAttribute("logo_type")&&p.getAttribute("logo_size")};if(!!n(m)){d=m}else{d=this.getElByAttr(["key","logo_size","logo_type"],"a")[l]}return d},init:function(){var m=this;var l="AQ_fn_aq_auth_callback";c.LOGO__aq_num__=c.LOGO__aq_num__||1;var d="AQ_logo_span_init_"+c.LOGO__aq_num__;c[l]=function(p,v,r){try{if(!(p instanceof Object)){return false}}catch(u){}var w=m.getRealWarpNode(m.get_id("AQ_logo_span_init_1"));v&&(w=m.getRealWarpNode(v,r));c.LOGO__aq_callback_data__=p;var n={size:m.filter(w.getAttribute("logo_size").toLowerCase()),id:m.filter(w.getAttribute("key")),type:m.filter(w.getAttribute("logo_type")),is_match:/true/.test(p.is_match.toLowerCase()),personal_auth:/successful/.test(p.personal_status.toLowerCase()),realname_auth:/successful/.test(p.realname_status.toLowerCase()),official_auth:/successful/.test(p.official_status.toLowerCase()),business_auth:/successful/.test(p.business_status.toLowerCase())};var q=b.createElement("img"),s=n.size.toLowerCase(),t,o;switch(n.type){case"personal":o="gr_";break;case"realname":o="sm_";break;case"business":o="hy_";break;case"official":o="gw_";break;default:o=void 0}if((n.size!="124x47"&&n.size!="83x30")||o===void 0){return false}if(n.is_match){if((n.personal_auth&&o==="gr_")||(n.realname_auth&&o==="sm_")||(n.official_auth&&o==="gw_")||(n.business_auth&&o==="hy_")){t=".png"}else{t="_gray.png"}}else{t="_gray.png"}q.src=g+"/static/outer/image/"+o+s+t;q.style.border="none";q.setAttribute("alt","安全联盟认证");q.width=parseInt(n.size.split(/x/)[0]);q.height=parseInt(n.size.split(/x/)[1]);w.appendChild(q)};b.write("<span style='display:none;' class='LOGO_aq_jsonp_wrap_' id='"+d+"'></span>");c.LOGO__aq_num__++;m.ready(function(){if(c.LOGO__aq_callback_flag__){return false}var o=m.getElementsByClassName(document,"span","LOGO_aq_jsonp_wrap_");var w="";for(var q=0;q<o.length;q++){var s=o[q];var v=m.getRealWarpNode(s);var n=v.getAttribute("logo_type");if(-1!==w.indexOf(n)){continue}w+="&logo_type="+n}var p=o[0],v=m.getRealWarpNode(p),t=m.filter(v.getAttribute("key")),u=top.location.href.split("#")[0].split("?")[0],u=u.replace(/(https?:\/\/)([^\.]+\.[^\.\/]+\/)/,"$1www.$2/$3"),r=f+"/query_auth_status/?callback="+l+"&url="+u+"&id="+t+w;m.script_onload(p,r,function(){for(var B=0;B<o.length;B++){var z=m.getRealWarpNode(o[B]);var A=m.filter(z.getAttribute("logo_type"));z.href=e.replace("https://","http://")+"/authenticate/cert/?site="+b.domain+"&at="+A;z.target="_blank";if(B<1){continue}var y=c[l],x=c.LOGO__aq_callback_data__;y(x,o[B],B)}c.LOGO__aq_callback_flag__=null;c.LOGO__aq_callback_data__=null;c.LOGO__aq_num__=null});c.LOGO__aq_callback_flag__=!!1})}};var k=new j();k.init()})(window,document);