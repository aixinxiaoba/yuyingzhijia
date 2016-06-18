TY.loadedModule("TY.m.mustache"),TY.ns("TY.m.mustache",function(){function t(t,e){return d.call(t,e)}function e(e){return!t(g,e)}function n(t){return t.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g,"\\$&")}function r(t){return String(t).replace(/[&<>"'\/]/g,function(t){return b[t]})}function i(t){this.string=t,this.tail=t,this.pos=0}function a(t,e){this.view=t,this.parent=e,this._cache={}}function o(){this.clearCache()}function s(t,e,n,r){for(var i,a,o,c="",l=0,u=t.length;u>l;++l)switch(i=t[l],a=i[1],i[0]){case"#":if(o=n.lookup(a),"object"==typeof o)if(k(o))for(var h=0,f=o.length;f>h;++h)c+=s(i[4],e,n.push(o[h]),r);else o&&(c+=s(i[4],e,n.push(o),r));else if("function"==typeof o){var g=null==r?null:r.slice(i[3],i[5]);o=o.call(n.view,g,function(t){return e.render(t,n)}),null!=o&&(c+=o)}else o&&(c+=s(i[4],e,n,r));break;case"^":o=n.lookup(a),(!o||k(o)&&0===o.length)&&(c+=s(i[4],e,n,r));break;case">":o=e.getPartial(a),"function"==typeof o&&(c+=o(n));break;case"&":o=n.lookup(a),null!=o&&(c+=o);break;case"name":o=n.lookup(a),null!=o&&(c+=p.escape(o));break;case"text":c+=a}return c}function c(t){for(var e,n=[],r=n,i=[],a=0,o=t.length;o>a;++a)switch(e=t[a],e[0]){case"#":case"^":i.push(e),r.push(e),r=e[4]=[];break;case"/":var s=i.pop();s[5]=e[2],r=i.length>0?i[i.length-1][4]:n;break;default:r.push(e)}return n}function l(t){for(var e,n,r=[],i=0,a=t.length;a>i;++i)e=t[i],e&&("text"===e[0]&&n&&"text"===n[0]?(n[1]+=e[1],n[3]=e[3]):(n=e,r.push(e)));return r}function u(t){return[new RegExp(n(t[0])+"\\s*"),new RegExp("\\s*"+n(t[1]))]}var p={};p.name="mustache.js",p.version="0.7.2",p.tags=["{{","}}"],p.Scanner=i,p.Context=a,p.Writer=o;var h=/\s*/,f=/\s+/,g=/\S/,v=/\s*=/,m=/\s*\}/,w=/#|\^|\/|>|\{|&|=|!/,d=RegExp.prototype.test,y=Object.prototype.toString,k=Array.isArray||function(t){return"[object Array]"===y.call(t)},b={"&":"&amp;","<":"&lt;",">":"&gt;",'"':"&quot;","'":"&#39;","/":"&#x2F;"};p.escape=r,i.prototype.eos=function(){return""===this.tail},i.prototype.scan=function(t){var e=this.tail.match(t);return e&&0===e.index?(this.tail=this.tail.substring(e[0].length),this.pos+=e[0].length,e[0]):""},i.prototype.scanUntil=function(t){var e,n=this.tail.search(t);switch(n){case-1:e=this.tail,this.pos+=this.tail.length,this.tail="";break;case 0:e="";break;default:e=this.tail.substring(0,n),this.tail=this.tail.substring(n),this.pos+=n}return e},a.make=function(t){return t instanceof a?t:new a(t)},a.prototype.push=function(t){return new a(t,this)},a.prototype.lookup=function(t){var e=this._cache[t];if(!e){if("."==t)e=this.view;else for(var n=this;n;){if(t.indexOf(".")>0){e=n.view;for(var r=t.split("."),i=0;e&&i<r.length;)e=e[r[i++]]}else e=n.view[t];if(null!=e)break;n=n.parent}this._cache[t]=e}return"function"==typeof e&&(e=e.call(this.view)),e},o.prototype.clearCache=function(){this._cache={},this._partialCache={}},o.prototype.compile=function(t,e){var n=this._cache[t];if(!n){var r=p.parse(t,e);n=this._cache[t]=this.compileTokens(r,t)}return n},o.prototype.compilePartial=function(t,e,n){var r=this.compile(e,n);return this._partialCache[t]=r,r},o.prototype.getPartial=function(t){return t in this._partialCache||!this._loadPartial||this.compilePartial(t,this._loadPartial(t)),this._partialCache[t]},o.prototype.compileTokens=function(t,e){var n=this;return function(r,i){if(i)if("function"==typeof i)n._loadPartial=i;else for(var o in i)n.compilePartial(o,i[o]);return s(t,n,a.make(r),e)}},o.prototype.render=function(t,e,n){return this.compile(t)(e,n)},p.parse=function(t,r){function a(){if(E&&!P)for(;U.length;)delete x[U.pop()];else U=[];E=!1,P=!1}if(t=t||"",r=r||p.tags,"string"==typeof r&&(r=r.split(f)),2!==r.length)throw new Error("Invalid tags: "+r.join(", "));for(var o,s,g,d,y,k=u(r),b=new i(t),_=[],x=[],U=[],E=!1,P=!1;!b.eos();){if(o=b.pos,g=b.scanUntil(k[0]))for(var C=0,T=g.length;T>C;++C)d=g.charAt(C),e(d)?U.push(x.length):P=!0,x.push(["text",d,o,o+1]),o+=1,"\n"==d&&a();if(!b.scan(k[0]))break;if(E=!0,s=b.scan(w)||"name",b.scan(h),"="===s?(g=b.scanUntil(v),b.scan(v),b.scanUntil(k[1])):"{"===s?(g=b.scanUntil(new RegExp("\\s*"+n("}"+r[1]))),b.scan(m),b.scanUntil(k[1]),s="&"):g=b.scanUntil(k[1]),!b.scan(k[1]))throw new Error("Unclosed tag at "+b.pos);if(y=[s,g,o,b.pos],x.push(y),"#"===s||"^"===s)_.push(y);else if("/"===s){if(0===_.length)throw new Error('Unopened section "'+g+'" at '+o);var j=_.pop();if(j[1]!==g)throw new Error('Unclosed section "'+j[1]+'" at '+o)}else if("name"===s||"{"===s||"&"===s)P=!0;else if("="===s){if(r=g.split(f),2!==r.length)throw new Error("Invalid tags at "+o+": "+r.join(", "));k=u(r)}}var j=_.pop();if(j)throw new Error('Unclosed section "'+j[1]+'" at '+b.pos);return x=l(x),c(x)};var _=new o;return p.clearCache=function(){return _.clearCache()},p.compile=function(t,e){return _.compile(t,e)},p.compilePartial=function(t,e,n){return _.compilePartial(t,e,n)},p.compileTokens=function(t,e){return _.compileTokens(t,e)},p.render=function(t,e,n){return _.render(t,e,n)},p.to_html=function(t,e,n,r){var i=p.render(t,e,n);return"function"!=typeof r?i:void r(i)},p}());