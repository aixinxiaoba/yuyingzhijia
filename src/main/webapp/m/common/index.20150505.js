(function(win, doc) {
    'use strict';

    var Common = {};

    /**
     * ================================================================
     *
     * 基础方法
     *
     * ================================================================
     */

    /**
     * 获取对象的类型
     * @param {any} obj - 被检查的对象
     */
    function getType(obj) {
        var type;
        if (obj === null) {
            type = String(obj);
        } else {
            type = Object.prototype.toString.call(obj).toLowerCase();
            type = type.substring(8, type.length - 1);
        }
        return type;
    }
    Common.type = getType;

    /**
     * 检查对象是否 function
     * @param {any} obj - 被检查的对象
     */
    function isFunction(obj) {
        return getType(obj) === 'function';
    }
    Common.isFunction = isFunction;

    /**
     * 遍历数组或对象，当迭代函数返回 false 时终止
     * @param {object|array} obj - 需要遍历的对象或数组
     * @param {function} iterator - 迭代器，遍历时调用的迭代函数
     * @param {object} context - 迭代器的 this 上下文对象
     */
    function each(obj, iterator, context) {

        var i,
            type;
        if (typeof obj !== 'object') {
            return;
        }
        type = getType(obj);
        context = context || obj;
        if (type === 'array' ||
            type === 'arguments' ||
            type === 'nodelist' ||
            type === 'htmlcollection') {
            for (i = 0; i < obj.length; i++) {
                if (iterator.call(context, obj[i], i, obj) === false) {
                    return;
                }
            }
        } else {
            for (i in obj) {
                if (obj.hasOwnProperty(i)) {
                    if (iterator.call(context, obj[i], i, obj) === false) {
                        return;
                    }
                }
            }
        }
    }
    Common.each = each;

    /**
     * 浅复制
     * @param {object...} args - 多个被复制的对象
     */
    function extend() {
        var obj = {};
        each(arguments, function(arg) {
            each(arg, function(val, key) {
                obj[key] = val;
            });
        });
        return obj;
    }
    Common.extend = extend;

    /**
     * 获取屏幕高度
     * 目前仅区分 UC浏览器 和其他浏览器
     */
    var getScreenHeight = (function() {
        if (/ucbrowser/i.test(navigator.userAgent)) {
            return function() {
                return doc.documentElement.clientHeight;
            };
        } else {
            return function() {
                return win.screen.height;
            };
        }
    })();
    Common.getScreenHeight = getScreenHeight;
    /**
     * ================================================================
     *
     * Event 相关
     *
     * ================================================================
     */

    /**
     * 绑定事件 >= IE9
     * @param {Element} el - 绑定事件的 dom
     * @param {string} eventName - 事件名
     * @param {function} handler - 处理函数
     */
    var bindEvent = function(el, eventName, handler) {
        if (el && eventName && handler) {
            el.addEventListener(eventName.toLowerCase(), handler, false);
        }
    };
    Common.bindEvent = bindEvent;

    /**
     * 取消绑定事件 >= IE9
     * @param {Element} el - 绑定事件的 dom
     * @param {string} eventName - 事件名
     * @param {function} handler - 处理函数
     */
    var unbindEvent = function(el, eventName, handler) {
        if (el && eventName && handler) {
            el.removeEventListener(eventName.toLowerCase(), handler, false);
        }
    };
    Common.unbindEvent = unbindEvent;


    /**
     * 绑定 click 事件
     * @param {Element} el - 绑定事件的 dom
     * @param {function} handler - 处理函数
     */
    var bindClick = function(el, handler) {
        return bindEvent(el, 'click', handler);
    };
    Common.bindClick = bindClick;


    /**
     * 页面加载完成回调
     * @param {function} cb - 回调的函数
     * @return {object} - Common 本身
     */
    Common.onload = function(cb) {
        bindEvent(win, 'load', cb);
        return Common;
    };


    /**
     * ================================================================
     *
     * 本地存储相关
     *
     * ================================================================
     */

    /*
     * 获取 localStorage
     * @param {string} key - key
     * @param {any} defaultVal - 获取不到或者出错时返回的默认值
     */
    function getLocalStorage(key, defaultVal) {
        if (!localStorage) {
            return defaultVal;
        }
        var val = localStorage.getItem(key);
        if (val !== null) {
            try {
                return JSON.parse(val);
            } catch (e) {}
        }
        return defaultVal;
    }
    Common.getLocalStorage = getLocalStorage;

    /*
     * 设置 localStorage
     * @param {string} key - key
     * @param {any} val - 设置的值
     * @return {boolean} - 是否设置成功
     */
    function setLocalStorage(key, val) {
        if (localStorage) {
            localStorage.setItem(key, JSON.stringify(val));
            return true;
        }
        return false;
    }
    Common.setLocalStorage = setLocalStorage;


    /**
     * ================================================================
     *
     * 选择器
     *
     * ================================================================
     */

    /**
     * 查找满足条件的父元素（包含当前元素）
     * @param {Element} el - 当前查找的 dom 元素
     * @param {function} selectorFn - 检查是否满足条件的方法
     */
    function getParentEl(el, selectorFn) {
        if (!el || !isFunction(selectorFn)) {
            return el;
        }
        while (el) {
            // 如果满足选择方法就返回当前 el
            if (selectorFn(el)) {
                return el;
            }
            // 查找上一级
            el = el.parentElement;
        }
    }
    Common.getParentEl = getParentEl;

    /**
     * 查找满足条件的子元素（包含当前元素）
     * @param {Element} el - 当前查找的 dom 元素
     * @param {function} selectorFn - 检查是否满足条件的方法
     */
    function getChildrenEl(el, selectorFn) {
        if (!el || !isFunction(selectorFn)) {
            return el;
        }
        // 如果满足选择方法就返回当前 el
        if (selectorFn(el)) {
            return el;
        }
        var i=0,
            childrenEl = el.children,
            childResult;
        for (; i < childrenEl.length; i++) {
            el = childrenEl[i];
            // 如果满足选择方法就返回当前 el
            childResult = getChildrenEl(el, selectorFn);
            if (childResult) {
                return childResult;
            }
        }
    }
    Common.getChildrenEl = getChildrenEl;

    // TODO: hasClass, addClass, removeClass


    /**
     * ================================================================
     *
     * 组件化
     *
     * ================================================================
     */
    /**
     * 将选中的元素按指定方法构造成组件
     * @param {string} selector - 选择器
     * @param {function} constructorFn - 构造组件的方法
     */
    Common.cmp = function(selector, constructorFn) {
        if (selector && isFunction(constructorFn)) {
            var selections = doc.querySelectorAll(selector),
                cmpName = 'cmp-' + selector.replace(/[\.\s]/g, '_');
            each(selections, function(el) {
                // 同样的 cmp 只会调用 constructorFn 一次
                if (!el[cmpName]) {
                    el[cmpName] = true;
                    constructorFn(el);
                }
            });
        }
        return Common;
    };

    /**
     * 将数据套入模板
     * @param {string} str - 模板字符串
     * @param {object} data - 模板参数
     */
    Common.tpl = function(str, data) {
        /*jshint evil: true */
        var func = new Function('_data', 'var __tpl="";with(_data){__tpl+="' +
            str.replace(/\\/g, '\\\\')
            .replace(/\"/g, '\\"')
            .replace(/<%=([\s\S]+?)%>/g, function(match, code) {
                return '"+' + code.replace(/\\"/g, '"') + '+"';
            })
            .replace(/<%([\s\S]+?)%>/g, function(match, code) {
                return '";' + code.replace(/\\"/g, '"')
                    .replace(/[\r\n\t]/g, ' ') + '__tpl+="';
            })
            .replace(/\r/g, '\\r')
            .replace(/\n/g, '\\n')
            .replace(/\t/g, '\\t') +
            '"};return __tpl;');
        /*jshint evil: false */
        return func ? func(data) : '';
    };

    /**
     * ================================================================
     *
     * URL，ajax 相关
     *
     * ================================================================
     */

    var baseRequestParams = {}, // 每个请求都会带的参数，可通过 baseParam 设置
        appendUcParamStr; // 为 url 添加 uc 公参和 entry 参数的方法

    // 在 baseRequestParams 中添加来源
    (function() {
        var entry = getQueryParam('from');
        if (entry) {
            baseRequestParams.from = entry;
        }
    })();

    /**
     * 获取 url 上的 search 参数值
     * @param {string} key - 参数名
     * @param {string} url - 被查找的 url，默认为当前的 location
     */
    function getQueryParam(key, url) {
        url = url || location.search;
        var hashIndex = url.indexOf('#'),
            keyMatches;
        if (hashIndex > 0) {
            url = url.substr(0, hashIndex);
        }
        keyMatches = url.match(new RegExp('[?|&]' + encodeURIComponent(key) + '=([^&]*)(&|$)'));
        return keyMatches ? decodeURIComponent(keyMatches[1]) : '';
    }
    Common.query = getQueryParam;

    /**
     * 将 queryString 拼接在 url 后面
     * @param {string} url - 拼接的 url
     * @param {string} query - queryString
     */
    function appendQuery2Url(url, query) {
        if (query) {
            url += (url.indexOf('?') < 0 ? '?' : '&') + query.replace(/^[?|&]+/, '');
        }
        return url;
    }

    /**
     * 序列化对象 {a: 1, b: 2, c: 'd e'} 为 'a=1&b=2&c=d+e' 形式的 querystring
     * @param {object} data - 被转化的对象
     * @param {string} appendTo - ，追加 querystring 的 url
     * @return {string} - 若指定 appendTo，则返回追加 querystring 后的 url，否则直接返回 querystring
     */
    function parseObject2QueryString(data, appendTo) {
        var stack = [],
            query;

        each(data, function(value, key) {
            stack.push(encodeURIComponent(key) + '=' + encodeURIComponent(value));
        });
        query = stack.join('&').replace(/%20/g, '+');

        if (getType(appendTo) === 'string') {
            return appendQuery2Url(appendTo, query);
        } else {
            return query;
        }
    }
    Common.parseQuery = parseObject2QueryString;

    /**
     * 为 url 添加 uc 公参
     * @param {string} url - 需要被添加公参的 url 地址
     */
    appendUcParamStr = (function() {
        var query,
            ucParamStr,
            i = 0,
            pName,
            data = {},
            validLen;

        // 兼容 WP 在 ajax 的时候不带公参
        if (getQueryParam('fr') === 'wp') {
            ucParamStr = getQueryParam('uc_param_str');
            validLen = ucParamStr.length - ucParamStr.length % 2;
            while (i < validLen) {
                pName = ucParamStr.substr(i, 2);
                data[pName] = getQueryParam(pName);
                i += 2;
            }
        }
        query = parseObject2QueryString(data);
        return function(url) {
            if (!getQueryParam('uc_param_str'), url) {
                url = appendQuery2Url(url, 'uc_param_str=dnfrpfbivesscpgimibtbmntnisieijblauputog');
            }
            return appendQuery2Url(url, query);
        };
    })();
    Common.appendUcParamStr = appendUcParamStr;

    /**
     * ajax 方法(只支持 GET 和 POST)
     * @param {object} options - ajax 的配置
     */
    function ajax(options) {
        options = options || {};
        var type = options.type || 'GET',
            url = appendUcParamStr(options.url || ''),
            params = extend(baseRequestParams, options.data),
            success = options.success,
            error = options.error,
            xhr = new XMLHttpRequest(),
            querystring;
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    if (isFunction(success)) {
                        if (xhr.responseText) {
                            success(JSON.parse(xhr.responseText));
                        } else {
                            success();
                        }
                    }
                } else if (isFunction(error)) {
                    error(xhr);
                }
            }
        };
        querystring = parseObject2QueryString(params);
        type = type.toUpperCase() === 'POST' ? 'POST' : 'GET';
        // 请求根目录时要判断域名
        // sport.uc.cn === news.uc.cn/new
        if (url.indexOf('/') === 0 && location.host === 'news.uc.cn') {
            url = '/new' + url;
        }
        try {
            if (type === 'POST') {
                xhr.open(type, url, true);
                xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
                xhr.send(querystring);
            } else {
                xhr.open(type, url + '&' + querystring, true);
                xhr.send();
            }
        } catch (e) {
            console.error('ajax error', e);
            // do nothing
        }
        setTimeout(function() {
            if (xhr) {
                xhr.abort();
            }
        }, 10000);
        /**
         * 请求中断方法
         * @param{XMLHttpRequest}
         */

    }
    Common.ajax = ajax;

    /**
     * 创建一个 GET 或 POST 的 ajax 的 快捷方法
     * @param {string} type - ajax 的 method，'GET' 或 'POST'
     */
    function _typeAjax(type) {
        return function(url, params, success, error) {
            ajax({
                type: type,
                url: url,
                data: params,
                success: success,
                error: error
            });
        };
    }

    /**
     * GET 方法
     * @param {string} url - 请求地址
     * @param {object} params - 请求参数
     * @param {function} success - 成功回调
     * @param {function} error - 错误回调
     */
    Common.get = _typeAjax('GET');
    /**
     * POST 方法
     * @param {string} url - 请求地址
     * @param {object} params - 请求参数
     * @param {function} success - 成功回调
     * @param {function} error - 错误回调
     */
    Common.post = _typeAjax('POST');

    /**
     * JSONP 方法
     * @param {string} url - 请求地址
     * @param {object} params - 请求参数
     * @param {function} callback - 回调
     */
    var jsonp = (function() {
        var _jsonpId = 0;
        return function(url, params, callback) {
            var cbName = 'jsonpCb' + (++_jsonpId),
                s = doc.createElement('script');
            params = params || {};
            params.callback = cbName;
            win[cbName] = callback;
            s.src = parseObject2QueryString(
                extend(baseRequestParams, params),
                appendUcParamStr(url)) + '&__t=' + (+new Date());
            doc.body.appendChild(s);
        };
    })();
    Common.jsonp = jsonp;

    /**
     * ping 一个 url 地址
     * @param {string} url - 访问的地址
     */
    Common.ping = function(url, params) {
        var img = new Image();
        img.src = parseObject2QueryString(
            extend(baseRequestParams, params),
            appendUcParamStr(url)) + '&__t=' + (+new Date());
    };

    /**
     * 设置、获取 baseParam
     * @param {string|object} name - 若为 string，则为被设置、获取的参数名；若为 object，则为设置的对象，并忽略后面的参数
     * @param {string} val - 若为不传参，则返回前面的 name 参数值；否则设置 name 的值为 val
     */
    Common.baseParam = function(name, val) {
        if (getType(name) === 'string') {
            if (arguments.length === 1) {
                return baseRequestParams[name];
            } else {
                baseRequestParams[name] = val;
            }
        } else if (getType(name) === 'object') {
            baseRequestParams = extend(baseRequestParams, name);
        }
    };

    /**
     * 获取 UC 全部公参
     * @param {function} 获取公参后的回调方法
     */
    var getUcParams = (function() {
        var UC_PARAMS_LOCAL_STORAGE_KEY = 'news_uc_params',
            UC_PARAMS_JSONP_TIME_KEY = '__t',
            ucParams = getLocalStorage(UC_PARAMS_LOCAL_STORAGE_KEY), // 从 LS 中获取 当前浏览器公参
            loading,
            cbs = [];
        // LS 里面的公参 3 天 过期：259200000 = 1000 * 60 * 60 * 24 * 3
        if (ucParams && (new Date() - ucParams[UC_PARAMS_JSONP_TIME_KEY] > 259200000)) {
            ucParams = undefined;
        }
        return function(callback) {
            if (!callback || !isFunction(callback)) {
                return;
            }
            if (ucParams) {
                callback(ucParams);
            } else {
                cbs.push(callback);
                if (!loading) {
                    loading = true;
                    jsonp('http://sports.uc.cn/system/getUccparams', {}, function(res) {
                        var cb;
                        ucParams = res || {};
                        while ( !! (cb = cbs.shift())) {
                            cb(ucParams);
                        }
                        // 存入 LS，减少请求
                        ucParams[UC_PARAMS_JSONP_TIME_KEY] = +new Date();
                        setLocalStorage(UC_PARAMS_LOCAL_STORAGE_KEY, ucParams);
                    });
                }
            }
        };
    })();
    Common.getUcParams = getUcParams;


    /**
     * ================================================================
     *
     * 统计方法
     *
     * ================================================================
     */
    /**
     * 统计相关参数
     */
    var _LOG_LOCAL_STORAGE_KEY = 'news_logs',
        // 日志在 localStorage 保留的长度
        _LOG_LOCAL_STORAGE_LENGTH = 10;


    /**
     * 获取完整的一个统计参数对象
     * @param {object} 原始统计参数，空则统计当前页面
     */
    function _getFullLogParams(params) {
        params = params || {};
        // 页面标题
        if (!params.pg) {
            params.pg = doc.title;
        }
        // 日志类型
        if (!params.lt) {
            params.lt = 'pageview';
        }
        // URL 地址
        if (!params.link) {
            params.link = location.href;
        }
        // 日志时间
        if (!params.t) {
            params.t = +new Date();
        }
        // 来源
        var entry = getQueryParam('from');
        if (entry) {
            params.from = entry;
        }
        return params;
    }

    /**
     * 记录 log 信息
     * @param {object} 统计参数，空则不增加
     * @param {int} 检查 localStorage 的日志长度，如果超过则主动发送统计信息，0 则不检查
     */
    var pushLog = (function() {
        // 延迟发送的 timeoutId
        var _logTimeoutId;

        return function(params, checkAndSendLength) {
            if (params) {
                var logs = getLocalStorage(_LOG_LOCAL_STORAGE_KEY, []);
                logs.push(_getFullLogParams(params));
                // 记录在 localStorage 里
                setLocalStorage(_LOG_LOCAL_STORAGE_KEY, logs);
                // 超过 checkAndSendLength 条就发送
                if (checkAndSendLength && logs.length > checkAndSendLength) {
                    clearTimeout(_logTimeoutId);
                    _logTimeoutId = setTimeout(sendLog, 100);
                }
            }
        };
    })();
    Common.pushLog = pushLog;

    /**
     * 立即发送 log 信息
     * @param {object} 新增的一条统计参数，空则不增加
     */
    var sendLog = (function() {
        // 是否正在发送统计信息
        var _logIsSending = false;

        return function(params) {
            if (_logIsSending) {
                return;
            }
            var logs = getLocalStorage(_LOG_LOCAL_STORAGE_KEY, []),
                logLength;
            // 将新增的日志添加到列表中
            if (params) {
                logs.push(_getFullLogParams(params));
            }
            logLength = logs.length;
            // 没有日志就结束吧
            if (!logLength) {
                return;
            }
            // 统计计数器 +1
            each(logs, function(oneLog) {
                oneLog.scount = oneLog.scount ? oneLog.scount + 1 : 1;
            });
            // 回写到 localStorage
            setLocalStorage(_LOG_LOCAL_STORAGE_KEY, logs);
            // 发送统计请求，整个数组上传
            _logIsSending = true;
            Common.post('/log/log', {
                logs: JSON.stringify(logs),
                now: +new Date()
            }, function(res) {
                _logIsSending = false;
                // 如果统计成功，则删除掉已经打 log 的日志
                if (res.success) {
                    logs = getLocalStorage(_LOG_LOCAL_STORAGE_KEY, []);
                    // 回写到 localStorage
                    setLocalStorage(_LOG_LOCAL_STORAGE_KEY, logs.slice(logLength));
                }
            }, function() {
                _logIsSending = false;
            });
        };
    })();
    Common.log = sendLog;

    /**
     * 自动统计
     * 设置全局点击事件代理，点击 [news] 时触发统计
     * 滚动页面栏目进行统计
     * @param {object} 新增的一条统计参数，空则不增加
     */
    Common.autoLog = (function() {
        // attr 属性名
        var ATTR_NEWS = 'news',
            ATTR_NEWS_TITLE = 'news-title',
            ATTR_AREA_NAME = 'areaname',
            // 是否启动过 autoLog
            isAuto,
            // 现有页面上的全部区域
            areas,
            // 上次停留的区域序号
            lastAreaNums = [],
            timeoutId;

        // 绑定新闻点击
        function bindNewsClick(e) {
            // 被点击的 a 标签
            var elA = getParentEl(e.target, function(el) {
                return el.tagName === 'A';
            }),
                // 被点击的 新闻
                elNews = getParentEl(elA, function(el) {
                    return el.attributes.hasOwnProperty('news');
                }),
                // 新闻 id
                newsId,
                // 新闻标题 el
                elNewsTitle = elNews,
                // 新闻所在区域
                elArea,
                // 新闻所在区域全部新闻列表
                elNewsList,
                // 新闻位置
                pos;
            if (elNews) {
                // 当前 elNews 没有 ATTR_NEWS_TITLE 属性，则查找子元素
                if (!elNewsTitle.attributes.hasOwnProperty(ATTR_NEWS_TITLE)) {
                    elNewsTitle = elNews.querySelectorAll('[' + ATTR_NEWS_TITLE + ']')[0];
                    if (!elNewsTitle) {
                        elNewsTitle = elNews;
                    }
                }
                // 获取栏目名称
                elArea = getParentEl(elNews, function(el) {
                    return el.attributes.hasOwnProperty(ATTR_AREA_NAME);
                });
                // 获取新闻位置
                if (elArea) {
                    newsId = elNews.getAttribute(ATTR_NEWS);
                    if (!newsId) {
                        // 没有新闻 id 的自动生成一个，便于下面根据 id 查找
                        newsId = ATTR_NEWS + (+new Date());
                        elNews.setAttribute(ATTR_NEWS, newsId);
                    }
                    elNewsList = elArea.querySelectorAll('[' + ATTR_NEWS + ']');
                    for (pos = 0; pos < elNewsList.length; pos++) {
                        if (elNewsList[pos].getAttribute(ATTR_NEWS) === newsId) {
                            break;
                        }
                    }
                }
                // 记录日志
                pushLog({
                    // 点击日志
                    lt: 'click',
                    // 点击链接
                    link: elA.href,
                    // 自定义参数: 日志类型
                    logType: 'news',
                    // 自定义参数: 栏目名称
                    col: elArea ? elArea.getAttribute(ATTR_AREA_NAME) : '',
                    // 自定义参数: 位置
                    pos: pos >= 0 ? pos + 1 : 0,
                    // 自定义参数: 新闻标题
                    title: elNewsTitle.getAttribute(ATTR_NEWS_TITLE) || elNewsTitle.innerText.replace(/\n/g, ' ')
                });
            }
        }

        // 计算各个区域的高度、位置
        function countAreasPosition() {
            var elAreas = doc.querySelectorAll('[' + ATTR_AREA_NAME + ']');
            areas = [];
            each(elAreas, function(elArea) {
                var height = elArea.offsetHeight;
                // 有高度的才进行统计
                if (height) {
                    areas.push({
                        title: elArea.getAttribute(ATTR_AREA_NAME) || elArea.getAttribute('area'),
                        top: elArea.offsetTop,
                        bottom: elArea.offsetTop + height,
                        minTop: elArea.offsetTop + height / 3, // 区域顶部 1/3 位置
                        minBottom: elArea.offsetTop + 2 * height / 3 // 区域底部 1/3 位置
                    });
                }
            });
        }
        Common.countAreasPosition = countAreasPosition;

        // 统计滚动页面的区域停留
        function statAreaScroll() {
            var top = win.scrollY,
                bottom = top + getScreenHeight(),
                i = 0,
                area,
                hasAreaInView,
                thisAreaNums = [];
            for (; i < areas.length; i++) {
                area = areas[i];
                if (
                    (area.top > top && area.top < bottom && area.minBottom < bottom) || // 上面2/3在屏幕内
                    (area.bottom > top && area.bottom < bottom && area.minTop > top) || // 下面2/3在屏幕内
                    (area.top < top && area.bottom > bottom) // 区域比屏幕还大
                ) {
                    // 不是上次停留的区域才统计
                    if (lastAreaNums.indexOf(i) < 0) {
                        pushLog({
                            // event 日志
                            lt: 'event',
                            // 区域标题
                            col: area.title,
                            // 自定义参数，统计类型：固定"浏览区域"
                            logType: 'area_view'
                        }, _LOG_LOCAL_STORAGE_LENGTH);
                    }
                    thisAreaNums.push(i);
                    hasAreaInView = true;
                } else if (hasAreaInView) {
                    // 后面的不在可视区域内，可以不用判断了
                    break;
                }
            }
            lastAreaNums = thisAreaNums;
        }

        /**
         * 返回初始化方法
         */
        return function(params) {
            sendLog(params);
            if (!isAuto) {
                /*
                 * 绑定新闻点击
                 */
                bindClick(doc.body, bindNewsClick);

                /*
                 * 统计滚动页面的区域停留
                 */
                // 图片加载可能导致位置变化
                var imgs = doc.getElementsByTagName('img'),
                    i = 0;
                for (; i < imgs.length; i++) {
                    bindEvent(imgs[i], 'load', countAreasPosition);
                }
                // 屏幕转动可能导致位置变化
                bindEvent(win, 'resize', countAreasPosition);
                // 初始化计算
                countAreasPosition();
                // 停止滚动 1s 后进行
                bindEvent(win, 'scroll', function() {
                    clearTimeout(timeoutId);
                    timeoutId = setTimeout(statAreaScroll, 1000);
                });

                /**
                 * 页面状态变化是发送统计
                 */
                bindEvent(win, 'popstate', function() {
                    sendLog();
                });
                isAuto = true;
            }
        };
    })();


    /**
     * ================================================================
     *
     * 分享方法
     *
     * ================================================================
     */
    /**
     * 给 a 标签对象添加点击分享事件
     * 该对象的 `uc-share-title`，`uc-share-content`，`uc-share-url` 分别对应分享标题、内容、地址
     * @param {element} el - 分享按钮
     */
    Common.setShareBtn = function(el) {
        if (!el || !el.tagName) {
            return;
        }
        var title = el.getAttribute('uc-share-title') || doc.title,
            content = el.getAttribute('uc-share-content') || '',
            url = el.getAttribute('uc-share-url') || location.href,
            bindNotSupport = function() {
                var msg = el.getAttribute('uc-share-not-support') || '您的浏览器不支持';
                bindClick(el, function(e) {
                    e.preventDefault();
                    win.alert(msg);
                });
            };
        // 绑定分享统计
        bindClick(el, function(e) {
            // 停止事件冒泡，防止进入新闻点击统计
            e.stopPropagation();
            // 获取栏目名称
            var ATTR_AREA_NAME = 'areaname',
                elArea = getParentEl(el, function(parent) {
                    return parent.attributes.hasOwnProperty(ATTR_AREA_NAME);
                }),
                logParams = {
                    // 点击日志
                    lt: 'click',
                    // 点击链接
                    link: url,
                    // 自定义参数: 日志类型
                    logType: 'share',
                    // 自定义参数: 分享标题
                    title: title
                };
            if (elArea) {
                logParams.col = elArea.getAttribute(ATTR_AREA_NAME) || '';
            }
            pushLog(logParams, _LOG_LOCAL_STORAGE_LENGTH);
        });
        getUcParams(function(ucp) {
            // 只比较2位版本号，可以当成 float 处理
            var version = ucp.ve ? parseFloat(ucp.ve, 0) : undefined;
            if (/(iPhone|iPad|iPod|iTouch|ios)/i.test(navigator.userAgent)) {
                if (version && version >= 9.3) {
                    el.href = 'ext:web_share:' + url;
                } else {
                    bindNotSupport();
                }
            } else if (version >= 9.0) {
                if (version < 9.7 || !(win.ucweb || '').startRequest) {
                    el.href = 'ext:new_share:' + title + '|' +
                        encodeURIComponent(url) + '|' + content;
                } else {
                    bindClick(el, function(e) {
                        e.preventDefault();
                        win.ucweb.startRequest('shell.page_share', [title, content, url, '']);
                    });
                }
                // 暂时不处理微信
                // } else if (navigator.userAgent.indexOf('MicroMessenger') > 0) {
                //     bindClick(el, function () {
                //         if (!mask) {
                //             mask = doc.createElement('div');
                //             mask.className = 'mask';
                //             mask.innerHTML = '<img src="http://news.uc.cn/xinwen/cams/album/5LiT6aKY5Zu+54mH/38C2EEA8432000EBCD0A13DF7760F1BB.png">';
                //             bindClick(mask, function () {
                //                 mask.style.display = 'none';
                //             });
                //             doc.body.appendChild(mask);
                //         }
                //         mask.style.display = 'block';
                //     });
            } else {
                bindNotSupport();
            }
        });
    };


    /**
     * ================================================================
     *
     * 图片懒加载
     *
     * ================================================================
     */
    Common.lazyLoad = (function() {
        // 全体图片
        var imgs,
            // 所有图片数量
            imgCount,
            EVENT_SCROLL = 'scroll',
            ATTR_REAL_SRC = 'url',
            // 用户是否选择了无图模式
            newsNoneImageMode = localStorage.getItem('newsNoneImageMode'),
            isNoneImage = newsNoneImageMode && newsNoneImageMode === 'on',
            // 设置图片地址
            setImgSrc = function(img) {
                img.src = img.getAttribute(ATTR_REAL_SRC) || '';
                img.removeAttribute(ATTR_REAL_SRC);
            },
            // 加载完全部图片就可以关闭计时器了
            unbindLoadImgEvent = function() {
                unbindEvent(win, EVENT_SCROLL, loadImg);
            },
            loadImg = function() {
                var noUnloadImg = true;
                // 没有开启无图模式时，才进行图片渲染
                if (!isNoneImage) {
                    each(imgs, function(img) {
                        // 图片距离页面顶部的位置 >= IE9
                        var imgBounding = img.getBoundingClientRect(),
                            imgTop = imgBounding.top,
                            imgBottom = imgBounding.bottom;
                        // url替换成src，图片有默认的src，若无src属性，图片会默认有边框
                        if (!img.src || img.src === 'http://image.uc.cn/s/uae/g/0i/ui/background_fff.png') {
                            // 显示满足条件的图片：不是 display:none 且接近屏幕上下边缘（200px以内）
                            if ((imgTop > 0 || imgBottom > 0) && // imgTop === 0 && imgBottom === 0 则认为是 display:none
                                imgTop > -200 && imgTop - 200 < getScreenHeight()) {
                                // 设置图片地址
                                setImgSrc(img);
                            } else {
                                noUnloadImg = false;
                            }
                        }
                    });
                } else {
                    each(imgs, function(img) {
                        if (!img.src || img.src === 'http://image.uc.cn/s/uae/g/0i/ui/background_fff.png') {
                            img.style.display = 'none';
                        }
                    });
                }
                if (noUnloadImg) {
                    unbindLoadImgEvent();
                }
            };
        // 先加载首屏的图片
        imgs = doc.getElementsByTagName('img');
        loadImg(); // 这里 imgCount 是 undefined，不会触发 unbind
        // 滚动时加载
        Common.onload(function() {
            // 这里重新 getElementsByTagName 是因为某些组件会修改 dom 添加 img，如 slide
            imgs = doc.getElementsByTagName('img');
            // 没有开启无图模式时，才进行图片渲染
            if (!isNoneImage) {
                imgCount = imgs.length;
                // 滚动则更新滚动状态
                bindEvent(win, EVENT_SCROLL, loadImg);
                // 如果是 wifi 环境下则直接加载全部图片
                getUcParams(function(ucp) {
                    if (ucp.nt === '2') {
                        each(imgs, function(img) {
                            if (!img.src || img.src === 'http://image.uc.cn/s/uae/g/0i/ui/background_fff.png') {
                                setImgSrc(img);
                            }
                        });
                        unbindLoadImgEvent();
                    }
                });
            } else {
                each(imgs, function(img) {
                    if (!img.src || img.src === 'http://image.uc.cn/s/uae/g/0i/ui/background_fff.png') {
                        img.style.display = 'none';
                    }
                });
            }
        });
        return loadImg;
    }());

    /**
     * ================================================================
     *
     * 输出到 window
     *
     * ================================================================
     */
    win._ = Common;

})(window, document);