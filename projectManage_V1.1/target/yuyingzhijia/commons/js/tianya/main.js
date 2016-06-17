function isPassport() {
    return /^http:\/\/passport\.tianya\.cn\/register|login_m\.jsp/.test(window.location.href) ? !0 : !1
}
function isFromApp() {
    var t = TY.param.getHrefParam();
    return t && (/^[iac]$/i.test(t.f) || /^daily/i.test(t.f)) ? !0 : !1
}
TY.namespace("m"),
TY.loadedModule("TY.m.nav"),
TY.m.nav = function($) {
    function getData(t, n, a, i) {
        return t = t || "",
        n = n || {},
        a = a ||
        function() {},
        i = i || !1,
        "" == t ? !1 : (i || (n._t = (new Date).getTime()), -1 == t.indexOf("?") && (t += "?"), t += $.param(n), void TY.loadUrl(t,
        function() {
            a && $.isFunction(a) && a()
        }))
    }
    function getDomain() {
        var t = document.domain,
        n = t.split("."),
        a = n.length;
        return t = a >= 2 ? n[a - 2] + "." + n[a - 1] : "tianya.cn"
    }
    function getScrollTop(t) {
        var n = t || document;
        return TY.os && TY.os.ios ? parseInt(window.scrollY, 10) : Math.max(n.documentElement.scrollTop, n.body.scrollTop)
    }
    function initDom() {
    	if (false) {
    		// 暂时关闭登陆功能
        //if (Conf.showTopNav) {
            Conf.navFromHtml || $(Conf.app_id).prepend(htmlTpl.TY_format({
                showNav: Conf.navOpen ? "show": "",
                title: "" != Conf.title ? "<span>Â·" + Conf.title + "</span>": ""
            }));
            var t = location.href,
            n = ['<li class="item menu ' + (Conf.navOpen ? "": "active") + '" id="ty_menu"  ><i class="nav-down"></i></li>', isOnline ? '<li id="nav_user" class="item avatar ' + (Conf.navOpen ? "active": "") + '" ><a href="http://www.tianya.cn/m/my.jsp?comeURL=' + t + '"><img alt="å¤´å" src="http://tx.tianyaui.com/logo/small/' + userId + '"></a></li>': '<li class="item ' + (Conf.navOpen ? "active": "") + '"><a href="http://passport.tianya.cn/login_m.jsp?fowardURL=' + t + '">登陆</a></li>'].join("");
            $("." + pre + " .user-info").html(n);
            var a = $("#ty_nav .nav-wrap");
            //a.html(navsTpl);
            var i = $("#nav_up"),
            e = $("#nav_mask");
            Conf.showHideIcon && Conf.navOpen && i.show(),
            Conf.clickDom2Hide && Conf.navOpen && (e.height($("body").height()), e.show()),
            !Conf.goBack && Conf.navFromHtml && ($("#ty_go_back").remove(), $("#ty_logo").attr({
                "class": "m-bar-left"
            })),
            checkLocation(),
            Msg = new MyMsg,
            initEvent()
        }
        // Conf.showBottomNav && $("body").append(footTpl),
        Conf.showBottomNav,
        Conf.renderCbk()
    }
    function initEvent() {
        function t() {
            l = getScrollTop(),
            r && l > r ? i ? (n.trigger("click"), Conf.navOpen && (Conf.navOPen = !1)) : r = l = 0 : r = l
        }
        var n = $("#ty_menu"),
        a = $("." + pre + " .user-info li"),
        i = Conf.navOpen,
        e = $("#nav_mask"),
        o = $("#ty_nav"),
        s = $("#nav_up"),
        c = o.find(".nav-wrap");
        n.bind("click",
        function() {
            a.toggleClass("active"),
            i = !i,
            i ? (c.show(), c.addClass("show"), e.height(r), o.css({
                position: "absolute"
            }), Conf.clickDom2Hide && (e.height($("body").height()), e.show()), Conf.showHideIcon && s.show(), "undefined" != typeof BBSMain && BBSMain.clickStat && BBSMain.clickStat("å¯¼èªå±å¼")) : (c.removeClass("show"), Conf.showHideIcon && setTimeout(function() {
                s.hide()
            },
            500), Conf.clickDom2Hide && e.hide())
        }),
        s.bind("click",
        function() {
            return n.trigger("click"),
            !1
        }),
        e.bind("tap touchend touchmove click",
        function() {
            return n.trigger("click"),
            !1
        });
        var r = 0,
        l = 0;
         if (Conf.moveHide && (TY.os && TY.os.ios ? TY(window).bind("touchmove", t) : $(window).bind("scroll", t)), Conf.goBack && Conf.referrerBack) try {
            var p = location.pathname.match(/(\/m\/\w+)/),
            d = location.href,
            f = location.host + (p && p[1]),
            u = document.referrer,
            h = "javascript:history.go(-1)",
            m = /tianya.cn|hainan.net|tianyaclub.com/,
            g = /^http:\/\/bbs.tianya.cn\/m\/my_trace_list.jsp|^http:\/\/search.tianya.cn\/m\/bbs.jsp/,
            b = {
                "www.tianya.cn/m": "javascript:history.go(-1)",
                "bbs.tianya.cn/m/block": "http://www.tianya.cn/m/"
            };
            if (u.match(m)) {
                if ("undefined" != typeof b[f]) h = b[f];
                else if (d.match(g)) {
                    var v = "referrer_" + p;
                    u && !u.match(/^http:\/\/bbs.tianya.cn\/m\/post-/) ? window.localStorage[v] = u: u && (h = window.localStorage[v])
                }
            } else h = "http://www.tianya.cn/m/";
            TY("#ty_go_back .icon-back").attr({
                href: h
            })
        } catch(k) {}
        o.delegate("a", "click",
        function() {
            var t = $(this);
            "undefined" != typeof BBSMain && BBSMain.clickStat && BBSMain.clickStat("å¯¼èª-" + t.html())
        }),
        $("#nav_user a").click(function() {
            Msg.delMsg("t")
        })
    }
    function MyMsg() {
        function getCookie(type) {
            type = type || "ty";
            var cn = argMap[type].cN,
            mo = argMap[type].mM,
            msg = __global.getCookie(cn);
            if (!msg) return null;
            if ( - 1 !== msg.indexOf(ch)) {
                for (var arr = msg.split(ch), obj = {},
                i = 0, l = mo.length; l > i; i++) obj[mo[i][0]] = +arr[i] || 0;
                return obj
            }
            try {
                return eval(msg)
            } catch(e) {
                return null
            }
        }
        function setCookie(t, n) {
            n = n || "ty";
            for (var a = [(new Date).getTime(), t.uId || __global.getUserId()], i = argMap[n].cN, e = argMap[n].mM, o = 2, s = e.length; s > o; o++) a.push(t[e[o][0]] || 0);
            __global.setCookieNoEscape(i, a.join(ch), 1, "/", domain, !1)
        }
        function showMsg() {
            var t = getCookie("ty") || {},
            n = getCookie("bbs") || {},
            a = (parseInt(t.t, 10) || 0) + (parseInt(n.t, 10) || 0) + (parseInt(t.uC, 10) || 0);
            a ? numWrap.addClass(cls) : numWrap.removeClass(cls)
        }
        function delMsg(t, n, a, i) {
            i = i || "ty";
            var e = argMap[i].mM;
            if ("t" == n) {
                for (var o = 2,
                s = e.length; s > o; o++) if (1 == e[o][2]) {
                    var c = t[e[o][0]];
                    t[e[o][0]] = a && a > 0 && c >= a ? c - a: 0
                }
            } else "bbs" == i && (t.t = t.t - t[n]),
            t[n] = 0;
            return setCookie(t, i),
            showMsg(),
            t
        }
        function setMsg(t, n) {
            mo = argMap[n].mM;
            var a, i = {};
            i.tS = (new Date).getTime(),
            i.uId = t.uId || __global.getUserId();
            for (var e = 2,
            o = mo.length; o > e; e++) a = mo[e],
            i[a[0]] = parseInt(t[a[1]] || 0, 10),
            a[2] && (i.t += parseInt(t[a[1]] || 0, 10));
            argMap[n].hC = getCookie(),
            setCookie(i, n),
            showMsg()
        }
        function getMsg(t, n) {
            t = t || "ty",
            getData(argMap[t].url, {},
            function() {
                var a = window[argMap[t].mO];
                if ("undefined" != typeof a && a.success && a.data && (setMsg(a.data, t), n && n(), "ty" == t)) {
                    var i = getCookie("ty") || {}; ("undefined" == typeof i.t || i.t < 1) && getMsg("bbs")
                }
            })
        }
        function updateMsg() {
            var t = (new Date).getTime(),
            n = getCookie();
            n && n.uId == userId && t < parseInt(n.tS, 10) + loopTime ? showMsg() : getMsg("ty"),
            setTimeout(function() {
                updateMsg()
            },
            loopTime)
        }
        if (isOnline) {
            var cookieName = "ty_msg",
            bbsCookieName = "bbs_msg",
            ch = "_",
            historyCookie = null,
            msgObj = "msgObj",
            msgMap = [["tS", "tS", 0], ["uId", "userId", 0], ["t", "t", 1], ["fC", "fanCount", 1], ["ryC", "replyCount", 1], ["seC", "shareCount", 1], ["rtC", "requestCount", 1], ["uC", "userCount", 0], ["ssC", "sysCount", 1], ["amC", "atMeCount", 0], ["aC", "approveCount", 1], ["dsC", "daShanfCount", 0], ["wlC", "weilunCount", 1]],
            bbsMsgObj = "bbsMsgObj",
            bbsMsgMap = [["tS", "tS", 0], ["uId", "userId", 0], ["t", "t", 1], ["aNC", "attention_notice_count", 1], ["rNC", "reply_notice_count", 0], ["cNC", "comment_notice_count", 0]],
            loopTime = 6e4,
            url = "http://www.tianya.cn/api/tw?var=msgObj&method=messagecount.ice.select&params.userId=" + userId + "&",
            bbsUrl = "http://bbs.tianya.cn/api?method=bbs.api.getUserNoticeCount&var=bbsMsgObj&",
            argMap = {
                ty: {
                    cN: cookieName,
                    mO: msgObj,
                    mM: msgMap,
                    url: url,
                    hC: null
                },
                bbs: {
                    cN: bbsCookieName,
                    mO: bbsMsgObj,
                    mM: bbsMsgMap,
                    url: bbsUrl,
                    hC: null
                }
            },
            numWrap = $("." + pre + " .user-info li"),
            div = $("#j-user"),
            cls = "u-reddot";
            div.click(function() {
                return delMsg(getCookie("ty") || {},
                "t"),
                delMsg(getCookie("bbs") || {},
                "t"),
                !0
            }),
            updateMsg();
            var wait = 1;
            this.getMsg = function(t, n) {
                var a = getCookie();
                return a && "undefined" != typeof a[t] ? (n && n(a[t]), a[t]) : (wait--&&arguments.callee(t, n), !1)
            },
            this.delMsg = function(t, n, a) {
                n || (obj = getCookie(), delMsg(obj, t, a))
            }
        }
    }
    function checkLocation() {
        function t(t, n) {
            n > o && (o = n, c.removeClass(s), t.addClass(s))
        }
        var n = "http://" + location.hostname,
        a = location.href,
        i = Conf.app_str,
        e = "",
        o = 0,
        s = Conf.sel_class,
        c = $(".ty-m-nav .nav-wrap a");
        c.each(function(o, s) {
            s = $(s),
            e = s.attr("href"),
            i && -1 != i.indexOf(s.attr("appstr")) && t(s, 1),
            0 == e.indexOf(n) && t(s, 2),
            a == e && t(s, 3)
        })
    }
    var pub = {},
    Conf = {
        app_str: "",
        app_id: "body",
        title: "",
        navOpen: !1,
        sel_class: "active",
        goBack: !1,
        referrerBack: !1,
        goBackUrl: "javascript:history.go(-1)",
        showHideIcon: !0,
        moveHide: !1,
        clickDom2Hide: !1,
        showBottomNav: !0,
        showTopNav: !0,
        renderCbk: function() {},
        navFromHtml: !1
    },
    htmlTpl = ['<div class="ty-m-nav">', '<header class="m-header" id="j-header">', '<div class="m-bar f-cf">', '<ul class="m-bar-left" id="ty_go_back">', '<li class="a">', '<a href="{goBackUrl}" class="icon-back"></a>', "</li>", "</ul>", '<ul class="m-bar-center" id="ty_logo">', '<li class="logo">', '<a href="http://www.tianya.cn/m/" class="logo-img"><span class="f-vh">å¤©æ¶¯ç¤¾åº</span></a>{title}', "</li>", "</ul>", '<ul class="m-bar-right user-info"></ul>', "</div>", "</header>", '<nav class="m-nav "  id="ty_nav" >', '<div class="nav-wrap {showNav}">', "</div>", "</nav>", '<div class="nav-mask" id="nav_mask"></div>', "</div>"].join(""),
    navsTpl = ['<div class="m-nav-row">', '<a class="active" href="http://www.tianya.cn/m/" appstr="shouye">é¦é¡µ</a>', '<a href="http://bbs.tianya.cn/m/block.jsp" appstr="bbs">çå</a>', '<a href="http://focus.tianya.cn/g" appstr="focus">èç¦</a>', '<a href="http://groups.tianya.cn/" appstr="groups">é¨è½</a>', '<a href="http://blog.tianya.cn/m/" appstr="blog">åå®¢</a>', "</div>", '<div class="m-nav-row">', '<a href="http://wenda.tianya.cn/m/" appstr="wenda">é®ç­</a>', '<a href="http://book.tianya.cn/m/" appstr="ebook">æå­¦</a>', '<a href="http://yy.tianya.cn/" appstr="game">æ¸¸æ</a>', '<a href="http://nong.tianya.cn/m/" appstr="food">ååº</a>', '<a href="http://shang.tianya.cn/rank/m/dsRanking.do?from=t&type=1" appstr="shang">æèµ</a>', "</div>", '<div class="nav-up" style="display:none;" id="nav_up">æ¶èµ·</div>'].join(""),
    footTpl = ['<div class="ty-m-foot">', '<div class="m-pc">', '<a href="http://www.tianya.cn/m/">触屏版</a>', '<a href="http://www.tianya.cn/login.html" id="j-to-web">电脑板</a>', "</div>", '<div class="copyright">', '<a target="_blank" href="http://service.tianya.cn/m/html/index.html" id="j-feedback">给育婴之家提点建议</a>', "<small>yuyingzhijia.cn</small>", "</div>", "</div>"].join(""),
    pre = "ty-m-nav",
    isOnline = __global.isOnline(),
    userId = isOnline && __global.getUserId() || null,
    Msg = null,
    domain = getDomain();
    return pub.init = function(t) {
        $.extend(Conf, t),
        initDom()
    },
    pub
} (TY);
var BBSMain = {};
BBSMain.maskShow = function(t, n, a, i, e) {
    function o() {
        var t = $("body").height();
        c.height(t),
        a && a(!0),
        c.show()
    }
    function s(t) {
        c.hide(),
        a && a(!1, t)
    }
    var c = $("#" + t);
    if (!c.size()) {
        var r = '<div class="func-mask ' + (i || "") + '" id="' + t + '"></div>';
        TY("body").append(r),
        c = TY("#" + t)
    }
    c.unbind("click").bind("click",
    function(t) {
        return s(t),
        !1
    }),
    e || c.bind("touchmove",
    function() {
        return ! 1
    }),
    this.hide = function() {
        s()
    },
    this.show = function() {
        o()
    }
},
BBSMain.QucickFun = function(t) {
    function n(t, n) {
        t = t || 50,
        setTimeout(function() {
            a(s.get(0), "translate3d(0px,0px,0px) scale(0.2)"),
            s.removeClass(b),
            n || f.hide(),
            c.show()
        },
        t)
    }
    function a(t, n) {
        "use strict";
        var a = t.style;
        a.webkitTransform = a.MsTransform = a.msTransform = a.MozTransform = a.OTransform = a.transform = n
    }
    function i(t, a, i, e) {
        s.find(t).bind("click",
        function() {
            var t = $(this);
            return BBSMain.clickStat(t.find("span").html()),
            t.hasClass(l) || (a && a($(this)), n(e)),
            i ? i: !1
        })
    }
    var e = ['<div class="post-func" id="post_more" style="">', '<ul id="circle_box">', "{items}", "</ul>", '<div class="i-close" id="post_close_btn"></div>', '</div><div class="post-func-close" id="post_more_btn"></div>'].join(""),
    o = {
        tpl: ['<li><a href="#" class="i-top"><span>页头</span></a></li>', '<li><a href="/front/m/newsSearch.do" class="i-search"><span>搜索</span></a></li>', '<li><a href="#js-footer" class="i-tail"><span>页尾</span></a></li>', '<li><a href="/front/m/index.do" class="i-history"><span>首页</span></a></li>'].join(""),
        firstFunc: function() {},
        openCbk: function() {}
    };
    $.extend(o, t),
    $("body").append(e.TY_format({
        items: o.tpl
    }));
    var s = $("#post_more"),
    c = $("#post_more_btn"),
    r = $("#post_close_btn"),
    l = "i-off",
    p = s.find("li").size(),
    d = 360 / p,
    f = new BBSMain.maskShow("post_mask", s,
    function(t) {
        t || n(0, !0)
    }),
    u = $(window).width(),
    h = $("body").width(),
    m = -1 * (h / 2 - 125 - 10),
    g = 0,
    b = "post-func-open",
    v = 10;
    fisrtShow = !0,
    u > h && (v += (u - h) / 2, c.css({
        right: v + "px"
    }), s.css({
        right: v + "px"
    })),
    s.addClass("post-func-" + p),
    s.find("span").each(function(t, n) {
        a(n, "rotate(" + -d * t + "deg)")
    }),
    c.bind("click",
    function() {
        return a(s.get(0), "translate3d(" + m + "px," + g + "px,0px) "),
        s.addClass(b),
        fisrtShow && (fisrtShow = !1, o.firstFunc()),
        f.show(),
        o.openCbk(),
        c.hide(),
        BBSMain.clickStat("åç¹å±å¼"),
        !1
    }),
    r.bind("click",
    function() {
        return n(),
        c.show(),
        !1
    }),
    i(".i-top",
    function() {
        window.scrollTo(0, 0)
    }),
    $("#js_2_bottom").size() || $("body").append('<a name="js-footer" id="js_2_bottom"></a>'),
    i(".i-tail", null, !0),
    this.bind = function(t, n, a, e) {
        i(t, n, a, e)
    },
    this.hide = n,
    this.mask = f
},
BBSMain.storage = function(t, n, a) {
    var i = !1,
    a = a || 0,
    e = (new Date).getTime();
    try {
        localStorage.test = 1,
        i = window.localStorage
    } catch(o) {
        i = !1
    }
    if (i) {
        var s = null;
        if (!n) {
            var c = i[t];
            return c ? (s = JSON.parse(c), s && s.d >= e ? s.v: null) : null
        }
        s = {
            v: n,
            d: e + 1e3 * a * 3600 * 24
        },
        i[t] = JSON.stringify(s)
    } else {
        if (!n) return TY.cookie.get(t);
        TY.cookie.set(t, n, location.host, "/", a)
    }
},
BBSMain.Trace = function() {
    function t(t) {
        try {
            var n = c[t];
            return n ? (n = JSON.parse(n), TY.isArray(n) && n.length ? n: !1) : !1
        } catch(a) {
            return ! 1
        }
    }
    function n(n, a, i) {
        var e = t(n),
        o = [];
        if (e) for (var r = 0,
        l = e.length; l > r && s > r; r++) {
            var p = e[r];
            i(p, a) && o.push(p)
        }
        return o.unshift(a),
        c[n] = JSON.stringify(o),
        !0
    }
    function a(t) {
        var a = n(e, t,
        function(t, n) {
            return t.a != n.a || t.i != n.i
        });
        return a && i({
            id: t.i,
            name: t.b,
            t: t.t
        }),
        a
    }
    function i(t) {
        return n(o, t,
        function(t, n) {
            return t.id != n.id
        })
    }
    var e = "s_trace",
    o = "footstep",
    s = 50,
    c = !1;
    try {
        localStorage.test = 1,
        c = window.localStorage
    } catch(r) {
        return c = !1,
        !1
    }
    this.addArticle = function(t) {
        return c ? void a(t) : !1
    },
    this.getArticle = function() {
        var n = t(e);
        return n
    },
    this.getBlock = function() {
        var n = t(o);
        return n
    },
    this.addBlock = function(t) {
        i({
            id: t.i,
            name: t.b,
            t: t.t,
            fb: t.fb || 0
        })
    }
},
BBSMain.showPop = function(t) {
    function n() {
        o.remove(),
        a.closeCbk()
    }
    var a = {
        hd: "",
        bd: "",
        ft: "",
        height: 200,
        width: 280,
        skinClass: "",
        renderCbk: function() {},
        closeCbk: function() {}
    };
    $.extend(a, t);
    var i = "bbs-pop-" + (new Date).getTime(),
    e = ['<div class="bbs-com-pop {skinClass}" id="' + i + '" style="width:{width}px;height:{height}px;margin-top:{mt}px;margin-left:{ml}px" >', '<span class="close-btn close">Ã</span>', '<div class="hd">{hd}</div>', '<div class="bd">{bd}</div>', '<div class="ft">{ft}</div>', "</div>"].join("");
    a.mt = -1 * a.height / 2,
    a.ml = -1 * a.width / 2,
    TY("body").append(e.TY_format(a));
    var o = TY("#" + i),
    s = new BBSMain.maskShow("pop_mask", o,
    function(t, a) {
        t || n(a)
    },
    "dark-mask");
    s.show(),
    a.renderCbk(o),
    o.delegate(".close", "click",
    function() {
        n(),
        s.hide()
    })
},
BBSMain.showLoginTips = function(t, n, a, i) {
    function e() {
        f--&&"undefined" == typeof clickPartLink ? setTimeout(e, 500) : f && (TY("#login_tip_btn").trigger("click"), BBSMain.clickStat(n + "æç¤º-å±ç¤º"))
    }
    function o() {
        TY("#login_tip_nomore:checked").size() ? (TY("#login_tip_close_n_btn").trigger("click"), BBSMain.clickStat(n + "æç¤º-å³é­-åå±ç¤º"), TY.cookie.set(p, 1)) : (TY("#login_tip_close_btn").trigger("click"), BBSMain.clickStat(n + "æç¤º-å³é­")),
        h.remove()
    }
    if (n = n || "登陆", t = t ||
    function() {
        return ! 0
    },
    a = a || "登陆" == n ? '<label><input type="checkBox" id="login_tip_nomore" />ä¸åæç¤º</label>': "æ­¤åè½é登陆æè½ä½¿ç¨å¦~", __global.isOnline() || !t()) return ! 1;
    var s = i ? i: "http://" + location.host + location.pathname + "?st=" + (TY.os && TY.os.ios ? parseInt(window.scrollY, 10) : Math.max(document.documentElement.scrollTop, document.body.scrollTop) || 0),
    c = encodeURIComponent(s),
    r = window.navigator.userAgent.toLowerCase(),
    l = "micromessenger" == r.match(/MicroMessenger/i) ? !0 : !1,
    p = "m-login",
    d = TY.cookie.get(p) ? !1 : !0,
    f = 20,
    u = ['<div class="bbs-login-tip" id="bbs_login_div">', '<a href="http://bbs.tianya.cn/m/login-uv/show.html" id="login_tip_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-' + n + "æé-å±ç¤º');return false;\"></a>", '<a href="http://bbs.tianya.cn/m/login-uv/close.html" id="login_tip_close_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-' + n + "æé-å³é­'); return false;\"></a>", '<a href="http://bbs.tianya.cn/m/login-uv/close-no-show.html" id="login_tip_close_n_btn" style="display:none;" onclick="typeof(clickPartLink)!=\'undefined\'&&clickPartLink(event,\'stat\',\'m-' + n + "æé-å³é­-ä¸åæé'); return false;\"></a>", '<div class="hd">', '<a class="closeBtn" href="#">Ã</a>', '<p class="b">登陆å¤©æ¶¯  åç°ç²¾å½©</p>', '<p class="s">ä¸1äº¿æ¶¯åä¸èµ·æ¼ç»ç²¾å½©ç½ç»äººç</p>', "</div>", '<div class="bd">', "<p>" + a + "</p>", '<div class="other-login">', '<a href="https://graph.qq.com/oauth2.0/authorize?client_id=100253980&response_type=code&redirect_uri=' + encodeURIComponent("http://passport.tianya.cn/login/qq.do?client=pc&fowardURL=" + s) + "\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登陆æé-QQ登陆');\"  class=\"btn qq-btn\">QQ登陆</a>", l ? '<a href="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx160fa51254add6be&redirect_uri=' + encodeURIComponent("http://passport.tianya.cn/login/weixin.do?client=touch&fowardURL=" + s) + "&response_type=code&scope=snsapi_login#wechat_redirectt\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登陆æé-å¾®ä¿¡登陆');\"  class=\"btn wx-btn\">å¾®ä¿¡登陆</a>": "", '<a href="https://api.weibo.com/oauth2/authorize?client_id=482040646&response_type=code&with_offical_account=1&redirect_uri=' + encodeURIComponent("http://passport.tianya.cn/login/sinaweibo.do?client=pc&fowardURL=" + s) + "\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-登陆æé-å¾®å登陆');\"  class=\"btn wb-btn\">å¾®å登陆</a>", "</div>", "</div>", '<div class="ft">', '<a href="http://passport.tianya.cn/login_m.jsp?fowardURL=' + c + "\" class=\"login btn btn-blue\" onclick=\"typeof(clickPartLink)!='undefined'&&clickPartLink(event,'stat','m-" + n + "æé-登陆');\" >å·²æè´¦å·ï¼é©¬ä¸登陆</a>", "</div>", "</div>"].join("");
    if (!d && "登陆" == n) return ! 1;
    // 暂时关闭登陆
     TY("body").append(u);
    var h = TY("#bbs_login_div"),
    m = new BBSMain.maskShow("login_mask", h,
    function(t, n) {
        t || o(n)
    },
    "dark-mask");
    m.show(),
    e(),
    h.find(".closeBtn").click(function() {
        return m.hide(),
        !1
    }),
    h.find(".login").click(function() {
        BBSMain.clickStat(n + "æç¤º-登陆")
    }),
    h.find(".login").click(function() {
        BBSMain.clickStat(n + "æç¤º-登陆")
    }),
    h.find(".regist").click(function() {
        BBSMain.clickStat(n + "æç¤º-æ³¨å")
    }),
    h.find(".other-login").delegate("a", "click",
    function() {
        BBSMain.clickStat(n + TY(this).html())
    })
},
BBSMain.clickStat = function(t) {
    var n = "m-v3-",
    a = "",
    i = location.host + location.pathname,
    e = {
        index: /^www.tianya.cn\/m\/$/,
        bbs: /^bbs.tianya.cn\/m\/$/,
        other: /\/m\/(\w+)/
    },
    o = window.navigator.userAgent.toLowerCase(),
    s = "micromessenger" == o.match(/MicroMessenger/i) ? !0 : !1,
    c = [];
    for (var r in e) c = i.match(e[r]),
    c && (a = r),
    "other" == r && c && (a = c[1]);
    "undefined" != typeof _hmt && t ? _hmt.push(["_trackEvent", n + a + "-" + t + (s ? "-wx": ""), "click"]) : "undefined" == typeof _hmt && setTimeout(function() {
        "undefined" != typeof _hmt && _hmt.push(["_trackEvent", n + a + "-" + t + (s ? "-wx": ""), "click"])
    },
    1e3)
},
BBSMain.goWeb = function() {
    var t = location.href,
    n = "http://www.tianya.cn/login.html";
    window.bbsGlobal && bbsGlobal.ToWeb && "true" === bbsGlobal.ToWeb ? n = t.replace("/m/", "/") : t.match(/^http:\/\/bbs.tianya.cn/) && (n = "http://bbs.tianya.cn"),
    $("#j-to-web")[0] && $("#j-to-web").attr({
        href: n,
        target: "_blank"
    })
},
BBSMain.msg = function(t, n, a) {
    $('<div id="j-msg" class="u-msg"><p></p></div>').appendTo("body").addClass("u-msg-" + t).show().find("p").html("<i></i>" + n),
    setTimeout(function() {
        $("#j-msg").remove()
    },
    a || 2e3)
};
var msg = BBSMain.msg,
maskShow = BBSMain.maskShow,
QucickFun = BBSMain.QucickFun,
checkVip = {
    check: function() {
        __global.isVipUser(function(t) {
            1 != t && TY.loadUrl("commons/js/tianya/extra_0a740d7.js",
            function() {
                $("#j-bbs-post")[0] && bbsShowApp.init()
            })
        })
    }
},
bbsShowApp = {
    init: function() {
        this.addTips(this.isFrom())
    },
    addTips: function(t) {
        function n() {
            var t = '<div class="app-tips app-tips-fixed f-cf"><div class="app-tips-right f-fr"><a href="' + e + '" class="u-btn u-btn-blue">ç«å³' + a + '</a></div><div class="app-tips-left"><div class="app-logo ' + l + '"></div><p><a href="' + e + '">' + i + "</a><br>" + p + '</p></div><a class="app-tips-close" href="javascript:;">&times;</a></div>';
            $("body").append(t).addClass("formapp"),
            BBSMain.clickStat(i + "-å±ç¤º"),
            $(".app-tips-fixed").delegate("a", "click",
            function() {
                BBSMain.clickStat($(this).hasClass("app-tips-close") ? i + "-å³é­": i + "-" + a)
            }),
            $(".app-tips-close").click(function() {
                $(this).parent().remove(),
                $("body").css("padding-bottom", "0"),
                $("#j-gotop").css("bottom", "0")
            })
        }
        var a, i, e, o, s, c, r, l = "add-logo-default",
        p = "åäººç§»å¨å´è¶£ç¤¾äº¤å¹³å°",
        d = navigator.userAgent.match(/android/gi),
        f = navigator.userAgent.match(/iphone|ipod/gi),
        u = navigator.userAgent.match(/ipad/gi);
        switch (s = bbsGlobal.item, c = bbsGlobal.artId, r = bbsGlobal.page, t) {
        case 0:
            break;
        case 1:
            a = "ä¸è½½",
            i = "å¤©æ¶¯ç¤¾åº",
            e = "weixin.html?isappinstalled=0&tianya=1&openurl=http://3g.tianya.cn/dl/tianya.jsp",
            n();
            break;
        case 2:
            a = "æå¼",
            i = "å¤©æ¶¯ç¤¾åº",
            !f && !u || d ? d && (o = "tianya://post?catid=" + s + "&aticleid=" + c + "&page=" + r) : o = "tianya://reply?categoryId=" + s + "&noteId=" + c + "&pageNo=" + r + "&onlyOpen=1&version=1",
            e = "weixin.html?isappinstalled=1&tianya=1&openurl=" + encodeURIComponent(o),
            n();
            break;
        case 3:
            a = "ä¸è½½",
            i = "å¤©æ¶¯ç¤¾åº",
            e = "weixin.html?isappinstalled=0&tianya=1&openurl=http://3g.tianya.cn/dl/tianya.jsp",
            n();
            break;
        case 4:
            l = "app-logo-daily",
            a = "æå¼",
            i = "å¤©æ¶¯æ¥æ¥",
            p = "å¤©æ¶¯ç²¾åéè¯»",
            !f && !u || d ? d && (o = "tianyadaily://notecontent?categoryId=" + s + "&noteId=" + c + "&pageNo=" + r) : o = "tianyadaily://openpostdetail?categoryId=" + s + "&noteId=" + c + "&pageNo=" + r,
            e = "weixin.html?isappinstalled=1&tianya=1&openurl=" + encodeURIComponent(o),
            n();
            break;
        case 5:
            l = "app-logo-daily",
            a = "ä¸è½½",
            i = "å¤©æ¶¯æ¥æ¥",
            p = "å¤©æ¶¯ç²¾åéè¯»",
            e = "weixin.html?isappinstalled=0&openurl=http://3g.tianya.cn/dl/daily.jsp",
            n()
        }
    },
    isFrom: function() {
        var t = 0,
        n = document.referrer,
        a = TY.param.getHrefParam(),
        i = a.f,
        e = a.isappinstalled;
        return t = "" == n || "" != n && -1 == n.indexOf(".tianya.cn") ? 1 : i && /^[iac]$/i.test(i) && e ? 1 == e ? 2 : 3 : i && /[daily]$/i.test(i) && e ? 1 == e ? 4 : 5 : 0
    }
},
Trace = BBSMain.Trace,
goWeb = BBSMain.goWeb;
$("body").ready(function(t) {
    checkVip.check(),
    Trace(),
    t("#j-post-content").size() || new QucickFun;
    //t.loadUrl("http://static.tianyaui.com/global/ty/stat/stat_20080313.js"),
    //t.loadUrl("http://hm.baidu.com/h.js?bc5755e0609123f78d0e816bf7dee255");
    var n = null; (n = t("#j-bbs-hotpost")) && n && n.size() && n.delegate(".rank-list a", "click",
    function() {
        BBSMain.clickStat("ç­å¸æ¦")
    }),
    goWeb()
});