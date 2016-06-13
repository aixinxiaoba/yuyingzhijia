<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<title>育婴之家网</title>
<link rel="apple-touch-icon"
	href="http://static.tianyaui.com/global/m/touch/images/icons/apple-touch-icon-114x114.png">
<link rel="stylesheet"
	href="http://static.tianyaui.com/global/m/v3/static/css/main.css">
<link rel="stylesheet"
	href="http://static.tianyaui.com/global/m/v3/static/css/index2.css?v=1.5">
</head>
<body>
	<div id="j-homepage" class="m-doc">
		<!-- header -->
		<div class="ty-m-nav">
			<header class="m-header" id="j-header">
				<div class="m-bar f-cf">
					<ul class="m-bar-left" id="ty_go_back">
						<li class="a"><a href="http://www.tianya.cn/m/"
							class="icon-back"></a></li>
					</ul>
					<ul class="m-bar-center" id="ty_logo">
						<li class="logo"><a href="http://www.tianya.cn/m/"
							class="logo-img"><span class="f-vh">育婴之家</span></a></li>
					</ul>
					<ul class="m-bar-right user-info"></ul>
				</div>
			</header>
			<!-- nav -->
			<nav id="ty_nav" class="m-nav">
				<div class="nav-wrap show">
					<div class="m-nav-row">
						<a appstr="shouye" href="/">首页</a>
						<s:iterator value="lstMainProjectMenu" id="projectMenu"
							status="myStatus">
							<s:if test="#myStatus.index < 4">
								<a appstr="shouye"
									href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"
									class='<s:if test="objProjectMenu.lId==lId">active</s:if><s:else>""</s:else>'>
									<s:property value="mobileMenuName" />
								</a>
							</s:if>
						</s:iterator>
					</div>

					<div class="m-nav-row">
						<s:iterator value="lstMainProjectMenu" id="projectMenu"
							status="myStatus">
							<s:if test="#myStatus.index >= 4">
								<a appstr="shouye"
									href="/front/m/subMenuShow.do?lProjectMenuID=${projectMenu.lId }"
									class='<s:if test="objProjectMenu.lId==lId">active</s:if><s:else>""</s:else>'>
									<s:property value="mobileMenuName" />
								</a>
							</s:if>
						</s:iterator>
					</div>
					<!-- 					<div id="nav_up" style="display:none;" class="nav-up">收起</div> -->
				</div>
			</nav>
			<div class="nav-mask" id="nav_mask"></div>
		</div>
		<!-- For FIS -->

		<!-- main -->
		<div class="m-main">

			<div class="u-slider u-slider-home">
				<div class="sliderbox gg gg-item" id="j-slider-home"
					data-id="10050104">
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-funinfo-6938323-1.shtml">
							<img lazyload="http://img3.laibafile.cn/p/mh/250057000.png" />
						</a>
						<p class="caption">天涯传统晒帅哥，民间帅哥该更新了</p>

					</div>
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-develop-2146012-1.shtml">
							<img lazyload="http://img3.laibafile.cn/p/mh/250168164.jpg" />
						</a>
						<p class="caption">给穷二代的一些忠告！</p>

					</div>
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-develop-2144708-1.shtml">
							<img lazyload="http://img3.laibafile.cn/p/mh/249918315.jpg" />
						</a>
						<p class="caption">北上深和二线的差距正在迅速拉大</p>

					</div>
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-funinfo-6942052-1.shtml">
							<img lazyload="http://img3.laibafile.cn/p/mh/250033440.jpg" />
						</a>
						<p class="caption">818加盟跨界歌王的大牌们</p>

					</div>
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-free-5473500-1.shtml"> <img
							lazyload="http://img3.laibafile.cn/p/mh/250041974.png" />
						</a>
						<p class="caption">逐利模式之下的“豪猪之芒”</p>

					</div>
					<div class="item">
						<a href="http://bbs.tianya.cn/m/post-16-1285493-1.shtml"> <img
							lazyload="http://img3.laibafile.cn/p/mh/249860021.jpg" />
						</a>
						<p class="caption">蛊巫谜境：从三尸蛊开始讲湘西巫术</p>

					</div>
				</div>
			</div>
			<div class="u-box">

				<a name="type"></a>
				<div class="see-wrap">

					<div class="u-tab tab-left-off">
						<div class="tab-wrap" id="tab_wrap">
							<ul class="f-cf"
								style="left: 0px; top: 0px; width: 1904px; position: absolute;">
								<li class="cur" style="width: 136px;" data-c="rec"><span>推荐</span></li>
								<li style="width: 136px;" data-c="hot"><span>热帖</span></li>
								<li style="width: 136px;" data-c="bagua"><span>八卦</span></li>
								<li style="width: 136px;" data-c="guoji"><span>国际</span></li>
								<li style="width: 136px;" data-c="zatan"><span>杂谈</span></li>
								<li style="width: 136px;" data-c="gushi"><span>股市</span></li>
								<li style="width: 136px;" data-c="qinggan"><span>情感</span></li>
								<li style="width: 136px;" data-c="xiaoshuo"><span>小说</span></li>
								<li style="width: 136px;" data-c="guihua"><span>鬼话</span></li>
								<li style="width: 136px;" data-c="meitu"><span>美图</span></li>
								<li style="width: 136px;" data-c="car"><span>汽车</span></li>
								<li style="width: 136px;" data-c="shishang"><span>时尚</span></li>
								<li style="width: 136px;" data-c="lvyou"><span>旅游</span></li>
								<li style="width: 136px;" data-c="chengshi"><span>城市</span></li>
							</ul>
						</div>
					</div>
					<div id="type_div"></div>
					<div class="u-bd" id="rec_div">
						<ul class="u-list-look" id="u-list-look">
							<li><a
								href="http://bbs.tianya.cn/m/post-lookout-465258-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>有颜值又有气质，钟爱涯叔，请为它投票！</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">了望天涯</span> <span class="look-author">文/天涯社区</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>0</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-funinfo-6944158-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>从近两年疯狂流行的真人秀开始，818明星们的住处。</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/250130867.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">娱乐八卦</span> <span class="look-author">文/情劫just</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>5058</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-worldlook-1674878-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>二孩“赶大集”出生超一孩，山东人最敢生</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">国际观察</span> <span class="look-author">文/天佑皇汉1</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>3089</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-free-5470950-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>【吴钩一言堂】北京市公检方对雷洋案的表示可能是个暗示</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">天涯杂谈</span> <span class="look-author">文/挑灯看吴钩</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>52405</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-no20-575386-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>职场上的这群中年人</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">职场天地</span> <span class="look-author">文/潜灵</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>978</em></span>
									</div>
							</a></li>
							<li data-id="10050105" class="gg gg-item" style="display: none;"></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-1095-190108-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>绿茶X和绿巨人——四年青春喂了狗，818我神奇的前闺蜜</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">生活那点事</span> <span class="look-author">文/最长的套路</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>15118</em></span>
									</div>
							</a></li>
							<li><a href="http://bbs.tianya.cn/m/post-16-1290105-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>《阴饭碗》一个被拐女的惨死，整个村差点在过年七天里死绝......</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">莲蓬鬼话</span> <span class="look-author">文/肖三酒</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>16197</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-no04-2666164-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>▲【淼之厨房】私房菜--食无定法，随心所至</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249712723.jpg" /></span> <span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249712834.jpg" /></span> <span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249712856.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">贴图专区</span> <span class="look-author">文/爱做菜的重庆人</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>8137</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-no11-4926711-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>看看我闺蜜的鞋子和包包，谁人能说不是时尚达人呢</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249726004.jpg" /></span> <span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249726017.jpg" /></span> <span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249726028.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">时尚资讯</span> <span class="look-author">文/leenlzy2016</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>1197</em></span>
									</div>
							</a></li>
							<li><a href="http://bbs.tianya.cn/m/post-5124-15417-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>【白喵私房菜】凉拌秋葵</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249806148.jpg" /></span> <span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249806698.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">美渝厨房</span> <span class="look-author">文/白喵</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>361</em></span>
									</div>
							</a></li>
							<li data-id="10050106" class="gg gg-item" style="display: none;"></li>
							<li><a href="http://bbs.tianya.cn/m/post-47-1579604-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>记录在深圳跑滴滴的日子（直播贴）</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">深圳</span> <span class="look-author">文/子贡黑糊糊</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>2373</em></span>
									</div>
							</a></li>
							<li><a href="http://bbs.tianya.cn/m/post-105-529659-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>高术通神--我随国术高手们修行的那些年</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">未知学院</span> <span class="look-author">文/9毫米烟灰</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>1565116</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-funinfo-6944194-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>昨天我一个白富美朋友遇到另一个特别装的白富美，小撕了一下，真的好精彩</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">娱乐八卦</span> <span class="look-author">文/爱枫雨豢</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>12846</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-news-349348-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>油价“三连涨”，为何国内油价总是跟涨不跟跌？</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249913703.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">新闻众评</span> <span class="look-author">文/真正的英雄是群众</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>539</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-free-5473910-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>【曾颖专栏】“知识改变命运”越来越不适合穷人</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249650656.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">天涯杂谈</span> <span class="look-author">文/四川曾颖</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>62063</em></span>
									</div>
							</a></li>
							<li data-id="10050107" class="gg gg-item" style="display: none;"></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-develop-2145814-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>熊市下半场还没走完，6月下旬见大底观点不变</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">经济论坛</span> <span class="look-author">文/慧下金山</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>97758</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-motss-602256-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>《迷途九年》，爱上你我不后退~</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">一路同行</span> <span class="look-author">文/梦逃天堂</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>1246</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-books-559943-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>正如猪想要有会飞的权利——《女权之声》笔记</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">闲闲书话</span> <span class="look-author">文/ddrose</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>923</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-no04-2666162-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>春夏之交游万仙山--郭亮村绝壁长廊</span>
									</h3>
									<div class="look-img">
										<span><img
											src="http://static.tianyaui.com/global/m/touch/images/loading.gif"
											original="http://img3.laibafile.cn/p/s/249711753.jpg" /></span>
									</div>
									<div class="look-sum f-cf">
										<span class="look-item">贴图专区</span> <span class="look-author">文/406088</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>9094</em></span>
									</div>
							</a></li>
							<li><a
								href="http://bbs.tianya.cn/m/post-no11-4926601-1.shtml">
									<h3 class="look-title">
										<i class="icon icon-Laugh-one"></i><span>一个胖妇女的日常碎碎念</span>
									</h3>
									<div class="look-img"></div>
									<div class="look-sum f-cf">
										<span class="look-item">时尚资讯</span> <span class="look-author">文/寒塘鹤影20132013</span>
										<span class="look-v-num"><i class="icon icon-eye"></i><em>9060</em></span>
									</div>
							</a></li>
						</ul>
						<span id="j-look-more" class="u-more">使劲戳，看更多<i
							class="more_face">?</i></span>
					</div>
				</div>
			</div>

			<div class="u-box" id="dashang_div">
				<div class="u-hd f-cf">
					<h2>热门打赏</h2>
					<a href="http://shang.tianya.cn/rank/m/dsRanking.do?from=t&type=1"
						class="u-hd-more">排行榜>></a>
				</div>
				<div class="u-bd">
					<ul class="u-list-shang">
						<li><span class="nodataloading">耐心等等哈，骚年(☆_☆)<i
								class="three-quarters-loader"></i></span></li>
					</ul>

				</div>
			</div>
			<div data-id="10050103" class="gg gg-item" style="display:;"></div>
			<div class="loading-weilun f-cf">
				<a
					href="http://fusion.qq.com/app_download?appid=100750231&platform=qzone&via=QZ.MOBILEDETAIL.QRCODE&u=1413884487"
					class="f-cf"> <span class="weilun-logo"></span>
					<div class="weilun-sum">
						<h3>
							天涯社区<em>·</em>微论
						</h3>
						<p>关注最新社会热点，寻找兴趣相投的小伙伴</p>
					</div> <span class="weilun-load">下载</span></a>
			</div>
		</div>
		<!---<div class="cn-wrapper" id="cn-wrapper">
			<ul id="circle_box">
				<li><a href="#b-head"><span class="b-head">页头</span></a></li>
				<li><a href="javascript:;"><span class="b-search">搜索</span></a></li>
				<li><a href="#b-foot"><span class="b-foot">页尾</span></a></li>
				<li><a href="javascript:;"><span class="b-zuji">足迹</span></a></li>
			</ul>
			<div class="circle_open"></div>
			<div class="circle_close"></div>
		</div>-->
	</div>
	<div data-id="10050199" class="gg gg-item" style="display:;"></div>
	<script src="http://static.tianyaui.com/global/ty2.0/TY_m_2.0.js"></script>
	<script src="/commons/js/tianya/main.js"></script>
	<script src="/commons/js/tianya/index2.js?20160414"></script>

	<script>
		TY.m.nav.init({
			'app_str' : "bbs",
			navFromHtml : true,
			navOpen : true,
			showHideIcon : false,
			moveHide : false,
			clickDom2Hide : false
		})
	</script>
	<!-- Start Alexa Certify Javascript -->
	<script type="text/javascript">
		_atrk_opts = {
			atrk_acct : "WCmTk1a4eFf2mh",
			domain : "tianya.cn",
			dynamic : true
		};
		(function() {
			var as = document.createElement('script');
			as.type = 'text/javascript';
			as.async = true;
			as.src = "https://d31qbv1cthcecs.cloudfront.net/atrk.js";
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(as, s);
		})();
	</script>
	<noscript>
		<img
			src="https://d5nxst8fruw4z.cloudfront.net/atrk.gif?account=WCmTk1a4eFf2mh"
			style="display: none" height="1" width="1" alt="" />
	</noscript>
	<!-- End Alexa Certify Javascript -->
</body>

</html>