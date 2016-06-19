!
function(t) {
    "use strict";
    function a() {
        t("body").delegate("a", "click",
        function() {
            var a = t(this).attr("href");
            if ( - 1 == a.indexOf("/m/") && a.indexOf("bbs.tianya.cn") > 0 && !t(this).closest(".gg").size()) {
                var n = a.split("bbs.tianya.cn");
                a = n[0] + "bbs.tianya.cn/m" + n[1],
                t(this).attr("href", a)
            }
        })
    }
    function n() {
        t(".sliderbox").delegate("a", "click",
        function() {
            BBSMain.clickStat("首页轮播图")
        }),
        t(".u-list-look").delegate("a", "click",
        function() {
            BBSMain.clickStat("大家都在看")
        }),
        t("body").delegate("#j-look-more", "click",
        function() {
            BBSMain.clickStat("大家都在看更多")
        }),
        t(".u-list-shang").delegate("a", "click",
        function() {
            BBSMain.clickStat("热门打赏")
        }),
        t("body").delegate(".u-hd-more", "click",
        function() {
            BBSMain.clickStat("热门打赏更多")
        })
    }
    function i(t) {
        TY.loader("TY.m.lazyload",
        function(a) {
            var n = new a;
            n.load(t || "#u-list-look img", null, "original")
        })
    }
    function e() {
        function a(t) {
            for (var a = n(), i = 0; i < a.length; i++) a[i] == t && a.splice(i, 1);
            a.unshift(t),
            k[v] = a.join(y)
        }
        function n() {
            var t = "";
            try {
                t = k[v] || ""
            } catch(a) {}
            return t.split(y)
        }
        function e() {
            var t, a = "-webkit-sticky",
            n = document.createElement("i");
            return n.style.position = a,
            t = n.style.position,
            n = null,
            t === a
        }
        function l() {
            return TY.os && TY.os.ios ? parseInt(window.scrollY, 10) : Math.max(document.documentElement.scrollTop, document.body.scrollTop) || 0
        }
        function o() {
            var t = __global.getUserCityName() || null,
            a = __global.getUserCityId() || null,
            n = "",
            i = b.chengshi.links;
            "undefined" != typeof i[t] ? location.href = i[t] : TY.loader("TY.data.area",
            function() {
                var t = TY_getAreaData.getProvinceByCid(a);
                n = t.pName || "",
                location.href = "undefined" != typeof i[n] ? i[n] : "http://bbs.tianya.cn/m/block.jsp?t=block"
            })
        }
        function c(a, n, i) {
            var e = "100501",
            //l = b[a].gg,
            s = "",
            o = 0,
            c = [];
            if (n.find(".gg-item").each(function(a, n) {
                o = a + l,
                10 > o && (o = "0" + o),
                s = e + o + "",
                t(n).attr({
                    "data-id": s
                }),
                c.push(s)
            }), c.length && "undefined" != typeof AdsInterface) AdsInterface.getADData(c.join(","));
            else if (c.length && i) var r = setInterval(function() {
                i--&&"undefined" != typeof AdsInterface ? (AdsInterface.getADData(c.join(",")), clearInterval(r), r = null) : 0 >= i && (clearInterval(r), r = null)
            },
            500)
        }
        function r(n) {
            function i(t, a) {
                var n = "set";
                _.addClass(n),
                v && v.scrollTo(t, a),
                setTimeout(function() {
                    _.removeClass(n)
                },
                500)
            }
            function s() {
                j || (l() < S || l() > T ? (N || f.removeClass(C), N = !0) : (N && f.addClass(C), N = !1))
            }
            var r = t("#rec_div"),
            u = t("#type_div"),
            d = f.find("li"),
            m = n,
            v = null,
            y = d.width(),
            k = d.length,
            _ = f.find("ul"),
            x = Math.ceil(g / 2);
            f.delegate("li", "click",
            function() {
                var a = t(this),
                e = a.data("c"),
                s = a.index();
                a.hasClass(w) || ("undefined" != typeof b[m] && (b[m].html = u.html()), m = e, e == p.key ? (r.show(), u.hide()) : h(e,
                function(t) {
                    r.hide(),
                    u.show().html(t),
                    c(e, u)
                }), n = e, d.removeClass(w), a.addClass(w), S < l() && (location = "#type"), s >= x && k - x >= s ? i( - 1 * (s + 1 - x) * y, 0) : x > s && i(0, 0))
            });
            var S = f.parent().offset().top,
            T = t("#dashang_div").offset().top,
            C = "u-tab-fixed",
            N = !0,
            j = e();
            t(window).bind("scroll", s),
            u.delegate("a", "click",
            function() {
                var i = "";
                return t(this).hasClass("u-more") && (i = "-更多"),
                BBSMain.clickStat(b[n].title + i),
                a(n),
                "chengshi" == n && t(this).hasClass("u-more") ? (o(), !1) : void 0
            }),
            r.delegate("a", "click",
            function() {
                a("rec")
            }),
            TY.loader("TY.m.iscroll_lite",
            function(a) {
                {
                    var n = f.get(0);
                    t(n).parent(),
                    t(window).width()
                }
                v = new a(n, {
                    onScrollEnd: function() {}
                })
            })
        }
        function h(a, n) {
            function e(e) {
                n && n(e + l.TY_format(b[a])),
                s.shuaxinData(t("#type_div").find("li"), "xiaoshuo" == a ? 1 : 0),
                i("#type_div img")
            };
            var l = '<a class="u-more" href="{link}" ><em>{moreName}</em></a>';
            // 自定义子类tab设置 根据点击不同的项做调整。$("#basePath").val() + 
//            "undefined" == typeof b[a].html ? "hot" == a ? u(e) : "xiaoshuo" == a ? d(e) : '' : n(b[a].html)
            t.ajax({
                url: "/static/m/" + a,
                dataType: "html",
                error: function() {},
                success: function(t) {
                	e(t);
                }
            })
            /*
             * "undefined" == typeof b[a].html ? "hot" == a ? u(e) : "xiaoshuo" == a ? d(e) : t.ajax({
                url: "http://www.tianya.cn/m/find/" + a + "/index.shtml",
                dataType: "html",
                error: function() {},
                success: function(t) {
                    e(t)
                }
            }) : n(b[a].html)
             * */
        }
        function u(a) {
            var n = "postDefault",
            i = ["<li>", '<a href="http://bbs.tianya.cn/m/post-{item}-{id}-1.shtml ">', '<h3 class="look-title"><i class="icon icon-Laugh-one"></i><span>{title}</span></h3>', '<div class="look-img">', "</div>", '<div class="look-sum f-cf">', '<span class="look-item">{item_name}</span>', '<span class="look-author">文/{author_name}</span>', '<span class="look-v-num"><i class="icon icon-eye"></i><em>{top_count}</em></span>', "</div>", "</a>", "</li>"].join(""),
            e = [],
            l = 20,
            s = "http://bbs.tianya.cn/api?method=bbs.ice.getHotArticleList&params.type=&params.pageSize=" + l + "&params.pageNum=1&var=" + n + "&_r=" + Math.random();
            t.loadUrl(s,
            function() {
            	//alert(window[n].data.rows);
                if ("undefined" != typeof window[n] && 1 == window[n].success) {
                    for (var t = window[n].data.rows || [], s = 0, o = t.length; o > s && l > s; s++) e.push(i.TY_format(t[s])),
                    (4 == s || 9 == s || 14 == s) && e.push('<li data-id="" class="gg gg-item" style="display:none;"></li>');
                    a('<ul class="u-list-look">' + e.join("") + "</ul>")
                }
            })
        }
        function d(a) {
            var n = "postBook",
            i = ["<li>", '<a href="{url}">', '<h3 class="look-title"><i class="icon icon-Laugh-one"></i><span>{title}</span></h3>', "{img}", '<div class="look-sum f-cf">', '<span class="look-item"></span>', '<span class="look-author">文/{author_name}</span>', '<span class="look-v-num"><i class="icon icon-eye"></i><em>{click}</em></span>', "</div>", "</a>", "</li>"].join(""),
            e = 20,
            l = [],
            s = "http://app.3g.tianya.cn/webservice/web/get_editor_recommendlst.jsp?size=" + e + "&var=" + n;
            t.loadUrl(s,
            function() {
                if ("undefined" != typeof window[n] && 1 == window[n].success) {
                    for (var t = window[n].data || [], s = 0, o = t.length; o > s && e > s; s++)"" != t[s].img && (t[s].img = '<div style="text-align:center;"><img style="max-width:100%;" src="' + t[s].img + '" /></div>'),
                    l.push(i.TY_format(t[s]));
                    a('<ul class="u-list-look">' + l.join("") + "</ul>")
                }
            })
        }
        var m = "tab_wrap",
        f = t("#" + m),
        g = 5.5,
        p = {
            title: "推荐",
            key: "rec"
        },
        b = {
            hot: {
                title: "热帖",
                moreName: "猛戳，无节操有见地的排行",
                link: "http://bbs.tianya.cn/m/hotArticle.jsp?pageNum=2",
                gg: 8
            }
        /*
         * ,
            bagua: {
                title: "八卦",
                moreName: "猛戳，泡天涯必须八卦一下",
                link: "http://bbs.tianya.cn/m/list-funinfo-1.shtml",
                gg: 11
            },
            guoji: {
                title: "国际",
                moreName: "猛戳，啃着馒头评国际大事",
                link: "http://bbs.tianya.cn/m/list-worldlook-1.shtml",
                gg: 14
            },
            zatan: {
                title: "杂谈",
                moreName: "猛戳，天下事随你想谈就谈",
                link: "http://bbs.tianya.cn/m/list-free-1.shtml",
                gg: 17
            },
            gushi: {
                title: "股市",
                moreName: "猛戳，股票牛人教你赚大钱",
                link: "http://bbs.tianya.cn/m/list-stocks-1.shtml",
                gg: 20
            },
            qinggan: {
                title: "情感",
                moreName: "猛戳，心情树洞等你来调侃",
                link: "http://bbs.tianya.cn/m/list-feeling-1.shtml",
                gg: 23
            },
            xiaoshuo: {
                title: "小说",
                moreName: "【前方高能】热辣小说来袭！",
                link: "http://book.tianya.cn/m/"
            },
            meitu: {
                title: "美图",
                moreName: "猛戳，摄影大作与美女惊现",
                link: "http://bbs.tianya.cn/m/list-no04-1.shtml",
                gg: 29
            },
            car: {
                title: "汽车",
                moreName: "轻点，进入汽车世界深水区",
                link: "http://bbs.tianya.cn/m/list-cars-1.shtml",
                d: "20160411",
                gg: 38
            },
            shishang: {
                title: "时尚",
                moreName: "猛戳，瞧时尚界最潮流做派",
                link: "http://bbs.tianya.cn/m/list-no11-1.shtml",
                gg: 32
            },
            lvyou: {
            
                title: "旅游",
                moreName: "猛戳，去瞅瞅啦么美的世界",
                link: "http://bbs.tianya.cn/m/list-travel-1.shtml",
                gg: 35
            },
            chengshi: {
                title: "城市",
                moreName: "猛戳，看看身边发生那些事",
                link: "",
                links: {
                    "台湾": "http://bbs.tianya.cn/m/list-333-1.shtml",
                    "重庆": "http://bbs.tianya.cn/m/list-45-1.shtml",
                    "海南": "http://bbs.tianya.cn/m/list-hn-1.shtml",
                    "上海": "http://bbs.tianya.cn/m/list-41-1.shtml",
                    "深圳": "http://bbs.tianya.cn/m/list-47-1.shtml",
                    "广东": "http://bbs.tianya.cn/m/list-44-1.shtml",
                    "四川": "http://bbs.tianya.cn/m/list-63-1.shtml",
                    "北京": "http://bbs.tianya.cn/m/list-39-1.shtml"
                },
                gg: 38
            }
         * */
        },
        
        v = "bbs-v3-navs",
        y = ",",
        k = window.localStorage,
        w = "cur",
        _ = n() || [],
        x = '<li class="{cur}" style="width:{w}px" data-c="{key}"><span class="{new}">{title}</span></li>',
        S = [],
        T = (new Date, !1),
        C = Math.floor((f.width() - 2) / g);
        x = x.replace("{w}", C);
        var N = (new Date).getTime() - 432e6,
        j = new Date(N).TY_format("yyyymmdd"),
        B = 4,
        Y = [];
        $("#f-cf li").each(function(){
        	if ($(this).attr("class") != undefined)
        	{
        		p = {   title:  $(this).text(),    key:$(this).attr("data-c")  };
        	}
        	
        	else
        	{
        		S.push(x.TY_format({title: $(this).text(),key:$(this).attr("data-c")}));
        	}
        });
        if (p.cur = T ? "": w, S.unshift(x.TY_format(p)), Y.length) ;
        for (var I = Y.length - 1; I >= 0; I--) - 1 == TY.inArray(Y[I], _) && S.splice(B, 0, x.TY_format(b[Y[I]]));
        
        
        	//- 1 == TY.inArray(L, _) && (b[L].key = L, b[L].cur = "", b[L]["new"] = "", b[L].d > j ? (Y.unshift(L)) : S.push(x.TY_format(b[L])));
        //for (var D = _.length - 1; D >= 0; D--) {
        //    var q = _[D];
        //    "undefined" != typeof b[q] && (b[q].key = _[D], b[q].cur = 0 == D ? w: "", b[L]["new"] = "", S.unshift(x.TY_format(b[q])), 0 == D && (T = !0))
        // }
        //if (p.cur = T ? "": w, S.unshift(x.TY_format(p)), Y.length) 
        //	for (var I = Y.length - 1; I >= 0; I--) - 1 == TY.inArray(Y[I], _) && S.splice(B, 0, x.TY_format(b[Y[I]]));
        //S.join("")
//        alert(S.join(""));
//        alert(document.getElementById("f-cf").innerHTML);
        //alert($("#f-cf li").stringify());
        f.find("ul").html(S.join("")),
        f.find("ul").width(C * S.length),
        /*
         * T && h(_[0],
        function(a) {
            t("#rec_div").hide(),
            t("#type_div").show().html(a),
            c(_[0], t("#type_div"), 10)
        }),
         * */
        r(_[0] || "")
        
    }
    var l = {
        run: function() {
            var a = this,
            n = "#j-slider-home",
            i = t("body").width();
            t(n)[0] && (t(n).height(.5 * i), a.sliderEvents(n))
        },
        sliderEvents: function(a) {
            TY.loader("TY.zepto.widget.slider",
            function() {
                TY(a).slider({
                    viewNum: 1,
                    imgInit: 2,
                    itemRender: !1,
                    imgZoom: !1,
                    loop: !0,
                    stopPropagation: !1,
                    springBackDis: 15,
                    autoPlay: !0,
                    autoPlayTime: 3e3,
                    animationTime: 300,
                    showArr: !1,
                    showDot: !0,
                    zIndexFrom: 50
                }).on("ready",
                function() {
                    t(a).find(".caption").show()
                })
            })
        }
    },
    s = {
        init: function() {
            this.liHtml = "",
            this.maxPage = 5,
            this.CurPageNo = 1,
            this.CurPageNum = 20,
            this.url = "http://www.tianya.cn/m/find/index.shtml",
            this.ShuaUrl = "http://bbs.tianya.cn/api?method=bbs.api.getArticles&params.articles=[",
            this.bindEvent(),
            this.shuaxinData()
        },
        bindEvent: function() {
            var a = this;
            t("#j-look-more").attr("loadStatius", "able").bind("click",
            function() {
                var n = t(this);
                if ("able" != n.attr("loadStatius")) return ! 1;
                a.onLoad(n);
                var i = {
                    url: a.url,
                    data: "",
                    dataType: "html",
                    cache: !1,
                    success: function(t) {
                        var i = /<li>(.|\s)*?<\/li>/g;
                        a.liHtml = t.match(i),
                        n.unbind(),
                        a.chouquLi(),
                        n.bind("click",
                        function() {
                            return "able" != n.attr("loadStatius") ? !1 : (a.onLoad(n), a.chouquLi(), void(a.CurPageNo > a.maxPage ? a.unableLoad(n) : a.ableLoad(n)))
                        }),
                        a.ableLoad(n)
                    },
                    error: function() {
                        a.unableLoad(n)
                    },
                    type: "get"
                };
                t.ajax(i)
            })
        },
        chouquLi: function() {
            for (var a = this,
            n = a.CurPageNum + 15 * (a.CurPageNo - 1), i = n + 15, e = [], l = n; i > l; l++) e.push(a.liHtml[l]);
            var s = t(e.join(""));
            a.shuaxinData(s),
            t(".u-list-look").append(s),
            a.CurPageNo++
        },
        ableLoad: function(t) {
            t.attr("loadStatius", "able").html('使劲戳，看更多<i class="more_face">☺</i>')
        },
        onLoad: function(t) {
            t.attr("loadStatius", "on").html('耐心等等哈，骚年(☆_☆)<i class="three-quarters-loader"></i>')
        },
        unableLoad: function(t) {
            t.attr("loadStatius", "unable").html("<em>别戳了，没有了-_-#</em>")
        },
        shuaxinData: function(a, n) {
            for (var i = this,
            a = a || t(".u-list-look li"), e = i.ShuaUrl, l = [], s = 0; s < a.length; s++) if (!a.eq(s).hasClass("gg-item") && -1 != a.eq(s).children("a").attr("href").indexOf("bbs.tianya.cn/m/post-")) {
                var o = a.eq(s).children("a").attr("href").split("/post-")[1].split("-"),
                c = o[0],
                r = o[1];
                l.push('"' + c + "-" + r + '"')
            }
            var h = new Date,
            u = "shuaxin" + h.getTime();
            e = e + l.join(",") + "]&var=" + u,
            l.length ? t.loadUrl(e,
            function() {
                var t = window[u];
                if ("undefined" != typeof t && "1" == t.success && "1" == t.code) for (var e = t.data.rows,
                l = 0,
                s = 0; l < a.length && s < e.length; l++) a.eq(l).hasClass("gg-item") || -1 == a.eq(l).children("a").attr("href").indexOf("bbs.tianya.cn/m/post-") || -1 != a.eq(l).find("a").attr("href").indexOf(e[s].item + "-" + e[s].id) && (i.addFace(e[s].reply_count, a.eq(l), n), i.addFocus(e[s], a.eq(l)), i.addShang(e[s], a.eq(l)), s++)
            }) : a.each(function(a, e) {
                e = t(e),
                i.addFace(e.find(".look-v-num em").html(), e, n)
            })
        },
        addFace: function(t, a, n) {
            n = n || 0;
            var i = a.find(".icon-Laugh-one"),
            e = parseInt(t),
            l = [[500, 1e3, 2e3], [1e6, 5e6, 1e6]]; (0 >= n || n >= l.length) && (n = 0);
            var s = l[n];
            e <= s[0] || (e > s[0] && e <= s[1] ? i.removeClass("icon-Laugh-one").addClass("icon-Laugh-tow") : e > s[1] && e <= s[2] ? i.removeClass("icon-Laugh-one").addClass("icon-Laugh-three") : e > s[2] && i.removeClass("icon-Laugh-one").addClass("icon-Laugh-four"))
        },
        addFocus: function(t, a) {
            a.find(".look-v-num em").html(t.click_count)
        },
        addShang: function(t, a) {
            var n = t.extend.user_reward,
            i = t.extend.adshow;
            n && 2 == parseInt(n) ? a.find(".look-title span").append('<i class="s-shang">赏</i>') : i && 1 == parseInt(i) && a.find(".look-title span").append('<i class="s-zhuan">商</i>')
        }
    },
    o = {
        init: function() {
            this.url = "http://static.tianyaui.com/global/data/nav/dashang.tianya.cn/block/popularBBS_shangRanking_w.js",
            this.html = ["<li>", '<div class="shang-name"><a href="http://www.tianya.cn/m/home.jsp?uid={rec_user_id}" class="shang-head"><em class="icon shang-head-photo"><img src="http://tx.tianyaui.com/logo/small/{rec_user_id}"></em><span class="shang-head-name">{rec_user_name}</span></a><span class="shang-num"><i class="icon icon-shang"></i>{shang_sum}</span></div>', '<h3 class="shang-title">', '<a href="http://bbs.tianya.cn/m/post-{mer_num1}-{mer_num2}-1.shtml">{title}</a>', "</h3>", "</li>"].join(""),
            this.putHtmlBox = t(".u-list-shang"),
            this.getData()
        },
        getData: function() {
        	// 加载排行榜数据。
            /*
             * var a = this;
            t.loadUrl(a.url,
            function() {
                if (window.popularBBS_ShangRankingW) {
                    var t = window.popularBBS_ShangRankingW.data,
                    n = a.htmlSet(t);
                    a.putHtmlBox.html(n)
                }
            })
             * */
        },
        htmlSet: function(t) {
            for (var a = this.html,
            n = [], i = t.length > 6 ? 6 : t.length, e = 0; i > e; e++) {
                var l = this.regExpFillData(a, t[e]);
                n.push(l)
            }
            return n.join("")
        },
        regExpFillData: function(t, a) {
            var n = /{\w+}/g,
            i = t.match(n);
            if (!i) return t;
            for (var e = t,
            l = 0; l < i.length; l++) {
                var s = i[l].replace(/({|})/g, ""),
                o = new RegExp(i[l], "g");
                e = e.replace(o, a[s])
            }
            return e
        }
    };
    t(document).ready(function() {
        t("#j-homepage")[0] && (l.run(), s.init(), o.init(), a(), n(), i(), e())
    })
} (Zepto);