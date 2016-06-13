/*[/mobile/img/bui/extension/stat/stat.js]*/
/*
 * stat.js
 * 日志统计相关方法集合
 */
(function (global, document, $, bui) {
    "use strict";

    /*
     * 生成伪GUID http://baike.baidu.com/view/185358.htm 利用random随机数
     */
    function guid() {
        var tmpl = Math.random().toString(31).substr(2, 32);
        return tmpl.replace(/(\S{8})(\S{4})(\S{4})(\S{4})(\S{12})/, '$1-$2-$3-$4-$5');
    }

    var LogUrl = {

        /*
         * LogUrl地址检测
         * 检测地址是否是以http://或https://开头，保证地址是绝对路径
         */
        isCorrect : function (src) {
            return LogUrl.LOGURLRE.test(src);
        },

        /*
         * 发送请求，记录日志
         * 使用$.get方法占用内存较高；
         * 使用Image占用内存适中，且实现简单
         * 使用原生XMLHttpRequest占用内存较低；
         * src: trim 之后的logurl
         */
        stat: function (src) {
            var id,
                img;

            if (global.navigator.sendBeacon) {
                global.navigator.sendBeacon(src);
                return;
            }

            id = guid();
            img = global[id] = new global.Image();

            img.onload = img.onerror = img.abort = function () {
                try {
                    delete global[id];
                } catch (ex) {
                    global[id] = null;
                }
                img.onload = img.onerror = img.abort = null;
            };

            img.src = src;
        },

        listener: function () {
            LogUrl.stat('http://' + bui.webSite.getRdDomain() + '/rd/rd.php?refcode=' + $.trim($(this).attr("refcode")));
            return true;
        },

        bclistener: function () {
            LogUrl.stat($.trim($(this).attr("bcrefcode")));
            return true;
        }
    };


    // 将logurl统计标签委托在document上
    // 使用mousedown事件是为了利用鼠标按下的瞬间进行统计，
    // 防止click事件因页面已跳转导致没有时机进行统计消息发送
    if (bui && bui.webSite && bui.webSite.getRdDomain()) {
        if ($.fn.tap) {
            $(document).delegate("[refcode]", "tap", LogUrl.listener);
        } else {
            $(document).delegate("[refcode]", "mousedown", LogUrl.listener);
        }
    }
    //广告统计for Sunhaoyan
    if ($.fn.tap) {
        $(document).delegate("[bcrefcode]", "tap", LogUrl.bclistener);
    } else {
        $(document).delegate("[bcrefcode]", "mousedown", LogUrl.bclistener);
    }
    //轮播广告TODO: 根据.wap-ad确定是否需要轮播
    /*var $slider = $('.wap-ad'),
        adWidth = $slider.width();
    if ($slider.length > 0) {
        $slider.each(function () {
            //超过2条，轮播
            var slideX = 0,
                originX = 0,
                moveX = 0,
                originMoveX = 0,
                $this = $(this),
                currentIndex = 0,
                adLen = $(this).find('.product-ad').length,
                pageHtml = '<ul class="page">',
                className = ' class="active"';
            if (adLen > 1) {
                //插入分页标识
                for (var i = 0; i < adLen; i++) {
                    if (i > 0) {
                        className = '';
                    }
                    pageHtml += '<li' + className + '>' + (i + 1) + '</li>';
                }
                pageHtml += '</ul>';
                $(this).append(pageHtml);
                $(this).on('touchstart', function (e) {
                    if (currentIndex === 0) {
                        originX = e.touches[0].pageX;
                    } else {
                        originX = currentIndex * adWidth + e.touches[0].pageX;
                    }
                    moveX = e.touches[0].pageX;
                    originMoveX = e.touches[0].pageX;
                });
                $(this).on('touchmove', function (e) {
                    var trans;
                    slideX = originX - e.touches[0].pageX;
                    moveX = originMoveX - e.touches[0].pageX;
                    trans = 'translate3d(-' + slideX + 'px, 0, 0)';

                    $this.find('.product-ad-slider').css({
                        'transform': trans,
                        '-webkit-transform': trans,
                    });
                });
                $(this).on('touchend', function (e) {
                    var trans = {};
                    if (moveX > 100) {
                        currentIndex = ++currentIndex % adLen;
                        slideX = adWidth * currentIndex;
                        trans = {
                            'transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                            '-webkit-transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                        };
                        $this.find('.product-ad-slider')
                            .animate(trans, 600, 'ease-in-out', function () {
                                $this.find('.page li').removeClass('active')
                                    .eq(currentIndex).addClass('active');
                            });
                    } else if (moveX < -100) {
                        currentIndex = --currentIndex % adLen;
                        slideX = adWidth * currentIndex;
                        $this.find('.product-ad-slider').animate({
                            'transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                            '-webkit-transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                        }, 600, 'ease-in-out', function () {
                            $this.find('.page li').removeClass('active')
                                .eq(currentIndex).addClass('active');
                        });
                    } else {
                        slideX = adWidth * currentIndex;
                        $this.find('.product-ad-slider').animate({
                            'transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                            '-webkit-transform': 'translate3d(-' + slideX + 'px, 0, 0)',
                        });
                    }
                });
            }
        });
    }*/
}(this, this.document, this.jQuery || this.Zepto, this.bui));

