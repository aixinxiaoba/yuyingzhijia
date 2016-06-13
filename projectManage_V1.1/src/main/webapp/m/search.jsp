<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>
<meta name="viewport"
	content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<META name="Keywords"
	content="育婴之家，儿童之家，育婴师，育儿，育婴师知识，育儿知识，育婴知识分享，分享，问答，育婴师交流，育婴交流，育儿交流，学习，育婴师学习，育婴师学习资料，怀孕，胎教，孕妇，辣妈，儿童，产后，婴儿，宝宝，yuyingzhijia，yyzj，yuyingzhijia.cn，育儿问答，育儿计划，育儿资料，小孩，幼儿">
<META name="description"
	content="育婴之家是一个分享交流育儿知识经验的平台，我们致力于提供最好的育儿知识，通过我们这个平台将知识推送到您的手中，让更多的人在育儿方面不再手忙脚乱">

<title>知识搜索</title>

<style id="ls[yBL]">
* {
	margin: 0;
	padding: 0
}

html {
	background: #f9f9f9
}

body {
	font: 14px/20px Helvetica, sans-serif, arial;
	color: #424242;
	background-color: #eee;
	font-size-adjust: none;
	-webkit-text-size-adjust: none
}

h1, h2, h3, h4 {
	font-weight: 400
}

i {
	font-style: normal
}

a {
	color: #002be5
}

a:focus {
	outline: thin dotted
}

a:active, a:hover {
	outline: 0
}

a:link, a:visited {
	text-decoration: none
}

textarea {
	vertical-align: top
}

em {
	color: #e50000;
	font-style: normal
}

ul, ol, li {
	list-style: none
}

input[type=search]::-webkit-search-cancel-button, input[type=search]::-webkit-search-decoration
	{
	-webkit-appearance: none
}

input[type=search] {
	-webkit-appearance: textfield;
	-moz-box-sizing: content-box;
	-webkit-box-sizing: content-box;
	box-sizing: content-box
}

button, html input[type=button], input[type=reset], input[type=submit] {
	-webkit-appearance: button
}

button, select {
	text-transform: none
}

button, input, select, textarea {
	font-family: inherit;
	font-size: 100%;
	margin: 0
}

button, input {
	line-height: normal
}

h2, .sc-title:link, .result .title:link {
	display: block;
	padding: 12px 7px 0;
	margin-bottom: 9px;
	line-height: 21px;
	font-size: 17px;
	text-decoration: none;
	font-weight: 400;
	-webkit-tap-highlight-color: initial
}

.result .title:link {
	margin-bottom: 5px
}

h2 {
	color: #222
}

.cards {
	position: relative
}

.sc-title:visited, .result .title:visited {
	color: purple
}

table {
	border-collapse: collapse;
	border-spacing: 0;
	color: #424242
}

.bold {
	font-weight: 700
}

.price {
	color: #ff3f35
}

.sc, .sc-panel {
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0)
}

.r {
	border-radius: 1px
}

.r-icon {
	border-radius: 5px
}

.card {
	display: block;
	position: relative;
	background-color: #fff;
	margin: 6px 8px;
	border: 1px solid #eaeaea
}

.sc a, .sc-panel a, .hl {
	-webkit-tap-highlight-color: initial
}

.f-right {
	float: right
}

.f-left {
	float: left
}

.clear:after {
	display: block;
	content: ".";
	font-size: 0;
	width: 0;
	height: 0;
	visibility: hidden;
	clear: both;
	line-height: 0
}

.sc-padding {
	padding: 0 7px
}

.sc-margin {
	margin: 0 7px
}

.top, .top1, .top2, .top3 {
	display: block;
	height: 14px;
	font-size: 12px;
	line-height: 13px;
	width: 14px;
	background-color: #A7D7FF;
	color: #fff;
	font-style: normal;
	text-align: center
}

.top1 {
	background-color: #FF6156
}

.top2 {
	background-color: #FE8E42
}

.top3 {
	background-color: #FFBA00
}

.warning, .pass, .from, .info {
	display: inline-block;
	height: 14px;
	font-size: 12px;
	line-height: 14px;
	color: #fff;
	text-align: center;
	background-color: #ffba00;
	padding: 0 3px
}

.pass {
	background-color: #17c953
}

.from {
	background-color: #093
}

.info {
	background-color: #24C100
}

#content {
	position: relative;
	min-width: 320px;
	overflow: hidden
}

header {
	position: relative;
	border-bottom: 1px solid #c8c8c8;
	background: #f9f9f9
}

header .logo {
	display: block;
	width: 106px;
	height: 45px;
	line-height: 43px;
	padding-left: 10px;
	overflow: hidden
}

header .logo img {
	vertical-align: middle
}

#headbox {
	padding: 5px 8px
}

.doodle {
	display: block;
	font-size: 0;
	line-height: 0;
	background: #ffe5f2;
	text-align: center;
	border-bottom: 1px solid #cacaca
}

.result .abs {
	font-size: 14px;
	color: #424242;
	line-height: 20px;
	margin-top: 6px;
	word-wrap: break-word
}

.result .title img, .phone-type {
	height: 15px;
	width: 10px;
	margin: -4px 0 0 5px;
	vertical-align: middle
}

.result .host {
	font-size: 12px;
	line-height: 14px;
	padding: 0 0 12px 9px;
	color: #093
}

.host img {
	height: 11px;
	width: 10px;
	vertical-align: middle
}

.guide {
	padding: 3px 7px;
	line-height: 30px;
	background: #fff
}

.guide * {
	display: inline-block
}

.guide span {
	color: #717171;
	white-space: nowrap
}

.guide a {
	padding: 0 5px
}

.no-result {
	background: #eee;
	line-height: 20px;
	padding: 17px 16px 50px;
	word-break: break-all
}

.no-result label {
	display: block;
	margin-top: 10px
}

.no-result li {
	font-size: 13px;
	list-style: decimal outside none;
	margin: 0 0 0 21px
}

.page-index {
	margin: 6px 16px;
	line-height: 13px;
	font-size: 13px;
	color: #999
}

#pager {
	display: block;
	-webkit-box-sizing: border-box;
	margin: 6px 8px;
	height: 42px;
	line-height: 40px;
	text-align: center;
	font-size: 16px;
	border: 1px solid #e6e6e6;
	border-radius: 1px;
	color: #666;
	background: #fff
}

#pager span {
	display: inline-block;
	position: relative
}

#pager .p-next {
	padding-right: 12px
}

#pager .p-next:after {
	display: block;
	height: 0;
	width: 0;
	position: absolute;
	right: 0;
	top: 18px;
	content: '.';
	font-size: 0;
	line-height: 0;
	text-indent: -9999px;
	border-style: solid;
	border-color: transparent;
	border-width: 5px;
	border-top-color: #999
}

#pager .p-retry {
	font-size: 14px;
	color: #999
}

#pager .p-retry em {
	color: #346cfa
}

#pager .p-loading, #pager .p-sm-loading, #pager .p-retry {
	display: none
}

#pager .p-sm-loading {
	width: 100%;
	height: 100%
}

#pager .p-sm-loading span {
	font-size: 12px;
	color: #999
}

#pager .p-sm-loading img {
	position: absolute;
	top: 4px;
	-webkit-transition: left 1.5s linear
}

#pager .p-sm-loading * {
	vertical-align: middle
}

#pager .p-loading i {
	display: inline-block;
	vertical-align: middle;
	background-color: #346cfa;
	height: 6px;
	margin-left: 3px;
	width: 6px;
	-webkit-border-radius: 6px;
	-webkit-animation: bounceLoading 1.2s ease 0 infinite;
	-webkit-transform: scale(.3)
}

#pager .p-loading i:nth-child(1) {
	-webkit-animation-delay: 0
}

#pager .p-loading i:nth-child(2) {
	-webkit-animation-delay: .2s
}

#pager .p-loading i:nth-child(3) {
	-webkit-animation-delay: .4s
}

#pager .p-loading i:nth-child(4) {
	-webkit-animation-delay: .6s
}

#pager.loading {
	border: 0;
	background: 0 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0)
}

#pager.loading .p-next, #pager.loading .p-retry {
	display: none
}

#pager.retry .p-retry, #pager.loading .p-loading, #pager.loading .p-sm-loading
	{
	display: inline-block
}

#pager.retry .p-next, #pager.retry .p-loading, #pager.retry .p-sm-loading
	{
	display: none
}

@
-webkit-keyframes bounceLoading { 0%{
	-webkit-transform: scale(1)
}

100%{
-webkit-transform
:scale
(
.3
)
}
}
.rel-keywords {
	text-align: left;
	background: #fff;
	overflow: hidden
}

.rel-keywords h3 {
	border-bottom: 1px solid #e0e0e0;
	color: #717171;
	padding-left: 7px;
	font-size: 15px;
	line-height: 40px;
	font-weight: 400
}

.rel-keywords ul {
	padding: 0 7px;
	font-size: 14px
}

.rel-keywords li {
	position: relative;
	box-sizing: border-box;
	float: left;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	width: 50%;
	padding: 2px 6px 2px 0
}

.rel-keywords a {
	display: block;
	height: 36px;
	line-height: 36px;
	white-space: normal
}

.rel-keywords li:nth-child(2n+1):after {
	position: absolute;
	top: 11px;
	right: 0;
	content: ".";
	font-size: 0;
	text-indent: -9999px;
	height: 15px;
	width: 1px;
	background-color: #e3e3e3
}

.rel-keywords li:nth-child(2n) {
	padding-left: 5px;
	padding-right: 0
}

footer {
	border-top: 1px solid #e0e0e0;
	background: #f9f9f9;
	padding: 6px 8px 0
}

#footnote {
	font-size: 12px;
	text-align: center
}

#footnote p {
	padding: 9px 0 2px
}

#footnote p a, #footnote p span {
	color: #888;
	padding: 0 14px;
	vertical-align: middle;
	border-left: 1px solid #ccc;
	display: inline-block;
	height: 14px;
	line-height: 14px
}

#footnote p span {
	color: #ccc
}

#footnote p a:first-child, #footnote p span:first-child {
	border: 0
}

#footnote #copy {
	color: #ccc;
	padding: 9px 0 11px
}

.searchbox {
	padding: 0 73px 0 0;
	position: relative;
	z-index: 20
}

.searchbox a {
	text-decoration: none
}

.searchbox .txt {
	position: relative;
	display: block;
	overflow: hidden;
	border: 1px solid #bbb;
	background-color: #fff;
	height: 38px
}

.searchbox .txt input {
	color: #222;
	border: 0;
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	padding: 6px 36px 6px 7px;
	font-size: 16px;
	outline: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	-webkit-appearance: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box
}

.searchbox .txt input::-webkit-input-placeholder {
	color: #b8b7b7
}

.searchbox .btn_clear, .searchbox .btn_voice {
	position: absolute;
	right: 0;
	top: 0;
	z-index: 2;
	width: 37px;
	height: 37px;
	display: none;
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqBAMAAAA37dRoAAAAFVBMVEUAAAAAAAAAAAAAAAAAAAAAAAAAAAASAQCkAAAAB3RSTlMzACMsDBwD1g7lRgAAAQpJREFUKM99k01uwjAQhZ8cwt5VYG1XpGtLcIDQql2T3gAQ978CeH48jCJ4i0Tz8mU89owRWcN/wX78k0jcX7B2z+43VAdzNzB9qbtNMHVncX/wrAO7A7yO5F7htSK3ZbXM4AKKOvWRH+4EIFy0rkQpQAmypAnxo/4QMdCrRvR1W2oVoLQzrxnkawbV1XOYpaA1JijcUKxwgsKEcoQEhT8VRYcCgauSuQoz6tUL6hWcqxmyZTA3+NVSa+JoleHE6PnhzLaLSdGE3nZ8bSgavMamoWhw5lMnlGA+dd+hXjvkuzlTN192PpbFlLycqOX0vZlUqnE51fG2vAFVF6h27mYVNUl2C/foxqNEd38SJ8VVujZBAAAAAElFTkSuQmCC)
		no-repeat scroll center center transparent;
	background-size: 16px;
	z-index: 2
}

.searchbox .btn_voice {
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAoCAMAAADaOGodAAAAM1BMVEUAAACqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqoNNXyEAAAAEHRSTlMAbx8FsETUDZ78iuYzW8Tx9mzMsAAAAPBJREFUOMvlk9uOBREQRcullDpa2///taOk08GYZN7PeiCxbJcS9E+uT1JNn+ugJCsGmuWXu/Fy7zZjIm/7YeE6Bc/Rssoyu6Cr1DCfFRvyZVLkLG3ct3SWqXm6oGGv7TN0dQk5vYqgS9+bw3tayBM1OCJeJRM5NCKKiKOdiO9whfa8JLwku4SidhnKmCPxzckIlkAjCmc9xwKUyNRxGMExS/mpVqABKyIZYz91NOHU9n3wCbj5SQW+geTnX2Rlya5Wl61Y21/i+SMx7XgXG9Ci83QkW1X/wj03PsJW8gP107Flra/babCwnknSpN7y/ABKySCHJDwQNwAAAABJRU5ErkJggg==)
		center center no-repeat;
	background-size: 14px 20px
}

.searchbox .btn {
	font: 16px/38px 黑体, Helvetica, sans-serif, arial;
	font-weight: 700;
	display: block;
	overflow: hidden;
	width: 74px;
	height: 40px;
	color: #424242;
	border: 1px #bbb solid;
	text-align: center;
	position: absolute;
	right: 0;
	top: 0;
	z-index: 5;
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #f9f9f9),
		color-stop(1, #ebebeb));
	padding: 0;
	border-radius: 0
}

.searchbox .btn_cancel {
	display: none
}

.searchbox.active.fill_query .btn_clear, .searchbox.active:not (.fill_query
	) .btn_voice, .searchbox:not (.active ) .btn_voice {
	display: block
}

.suggest {
	position: absolute;
	left: 0;
	top: 0;
	z-index: 20;
	width: 100%;
	margin-top: -1px
}

.suggest a {
	text-decoration: none;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap
}

.suggest .box {
	line-height: 34px;
	border: 1px solid #b4b4b4;
	background-color: #fff;
	overflow: hidden;
	border-radius: 0 0 2px 2px
}

.suggest .lst {
	padding: 0;
	margin: 0;
	list-style: none
}

.suggest li {
	border-bottom: 1px solid #d3d3d3;
	position: relative;
	text-align: left;
	padding-left: 6px
}

.suggest .query {
	color: #222;
	display: block;
	margin: 0 54px 0 0
}

.suggest .sug strong {
	color: #999;
	font-weight: 400
}

.suggest .add {
	position: absolute;
	right: 0;
	top: 0;
	width: 38px;
	height: 100%;
	text-align: center;
	font-size: 22px;
	line-height: 34px;
	padding-right: 10px;
	color: #a6a6a6
}

.suggest .direct {
	height: 40px;
	padding: 5px 0 5px 6px
}

.suggest .direct .query {
	line-height: 0
}

.suggest .direct .add {
	line-height: 50px
}

.suggest .direct span {
	display: block;
	line-height: 20px;
	color: #333
}

.suggest .direct .title {
	position: relative;
	font-size: 16px
}

.suggest .title.tip {
	display: inline-block;
	overflow: initial;
	padding-right: 34px;
	position: relative;
	box-sizing: border-box;
	max-width: 100%
}

.suggest .title span {
	-webkit-box-flex: 1;
	-webkit-box-sizing: border-box
}

.suggest .title, .suggest .title span, .suggest .abs {
	line-height: 20px;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap
}

.suggest .direct .abs {
	font-size: 12px;
	color: #999
}

.suggest .tip i {
	position: absolute;
	top: 3px;
	right: 0;
	display: block;
	font-size: 12px;
	color: #fff;
	text-align: center;
	vertical-align: middle;
	line-height: 14px;
	height: 14px;
	width: 30px;
	border-radius: 1px
}

.suggest .www .tip i {
	background: #59a1f9
}

.suggest .news .tip i {
	background: #ff6155
}

.suggest .btns {
	background: #f7f7f7;
	overflow: hidden
}

.suggest .clear_history {
	position: relative;
	float: left;
	padding: 0 6px;
	border-right: 1px solid #d3d3d3;
	color: #666
}

.suggest .btn_hide {
	position: relative;
	float: right;
	color: #666;
	height: 34px;
	width: 52px;
	padding: 0;
	border-left: 1px solid #d3d3d3;
	text-align: center
}

.suggest .btn_hide:after, .suggest .clear_history:after {
	position: absolute;
	content: '.';
	top: 0;
	left: 0;
	display: block;
	height: 100%;
	width: 1px;
	background: #fff;
	font-size: 0;
	text-indent: -9999px
}

.suggest .clear_history:after {
	left: auto;
	right: 0
}

.sc-mark {
	display: block;
	position: relative;
	padding-right: 15px
}

.sc-mark::after, .sc-mark::before {
	content: "";
	position: absolute;
	display: inline-block;
	width: 7px;
	height: 2px;
	background: #CCC;
	top: 50%;
	right: 2px;
	margin-top: -1px
}

.sc-mark::after {
	-webkit-transform: rotate(60deg);
	-webkit-transform-origin: right 90%
}

.sc-mark::before {
	-webkit-transform: rotate(-60deg);
	-webkit-transform-origin: right 10%
}

.down-rt {
	position: relative
}

.down-rt:after {
	position: absolute;
	top: 50%;
	right: 10px;
	margin-top: -2px;
	content: ".";
	font-size: 0;
	text-indent: -9999px;
	height: 0;
	width: 0;
	border-color: #999 transparent transparent;
	border-width: 5px;
	border-style: solid
}

.sc-btn {
	display: block;
	height: 35px;
	line-height: 35px;
	color: #1b6ad9;
	font-size: 14px;
	border: 1px solid #bcd4f5;
	border-radius: 1px;
	text-align: center;
	background-color: #ebf4fd;
	text-decoration: none
}

.stars, .stars strong {
	display: inline-block;
	height: 15px;
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABsAAAA8CAMAAACgozHpAAAAdVBMVEUAAADa2tr/kUfa2trttpXa2tr/kUfa2tr/kUfttpX/kUfa2tr/kUf/kUfa2tra2tr/kUfa2tr/kUfa2tr/kUf/kUf/kUfa2tra2tr/kUfa2tr/kUfa2tr/kUfa2tra2tr/kUfa2tr/kUf/kUf/kUf/kUfa2to9d0E/AAAAJXRSTlMA0nl5CB/RERED9fXfLeEsHFw+OlqMIrxKnp6/b2+Oik7IuRdlZrC9TQAAASRJREFUOMvNktmSgyAQAEclKp5RY4xXdrO78P+fuIhFajjmPf1IYztQgEUHJENDu0UOlGJSLpTrpJSMcJNyxDQXqZjCLpEHl6BrtEvQSmYYpKbJDAwekmDK1AhRUG26yp++iVo48btfzCi3Wz4Aw7HMwKY0wj9+i5uJe1+IyHav806egWhmJm8jL9odHw164M2NLmovN2d9WVFeRi2u4Gi7cfsFJPDZ3GjVV7SbRU8pJsRM/k0IwQi3KkdMcxWKNexicXANukq7GK3khl5oqvcCg29B8POrRkiDatdVPvqmquHE747MKLdb2LfKsczBpkDubqsaN2P3vhCp7dJz8jEQzc3kdepFb+/j8t2Nzmovh5P7akV5kdZo5x+O1ju3X0AMn8Y/0DlDLtVgtRkAAAAASUVORK5CYII=)
		0 bottom repeat-x;
	background-size: 14px 30px
}

.stars {
	width: 70px;
	vertical-align: middle;
	overflow: hidden;
	margin-top: -3px;
	background-position: 0 0
}

@
-webkit-keyframes loading-anim {
	from {-webkit-transform: rotate(0deg)
}

to {
	-webkit-transform: rotate(360deg)
}

}
.panel-anim {
	-webkit-transition: -webkit-transform 350ms ease
}

.mark-icon {
	display: inline-block;
	height: 14px;
	line-height: 14px;
	font-size: 12px;
	padding: 0 3px;
	margin-left: 4px;
	background-color: #59a1f9;
	color: #fff;
	border-radius: 1px;
	vertical-align: top;
	margin-top: 3px
}

.imgmr {
	margin-right: 7px
}

.panel-group {
	display: none;
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	z-index: 1000
}

.panel-group .mask {
	position: absolute;
	top: 0;
	left: 0;
	right: 0;
	bottom: 0;
	background: #000;
	opacity: .5;
	z-index: 1
}

.panel-group .panel {
	position: relative;
	z-index: 2;
	background: #fff
}

.panel-group .panel .title {
	font-weight: 400;
	height: 40px;
	line-height: 40px;
	font-size: 16px;
	color: #999;
	text-align: center;
	border-bottom: 1px solid #ddd;
	background: -webkit-gradient(linear, 0 0, 0 100%, color-stop(0, #f9f9f9),
		color-stop(100%, #f0f0f0))
}

#install_group .panel {
	top: 123px;
	width: 237px;
	height: 124px;
	line-height: 24px;
	border-radius: 2px;
	margin: 0 auto;
	padding: 6px 0 0 13px
}

#install_group h3 {
	position: relative;
	font-size: 16px
}

#install_group h3 a {
	position: absolute;
	height: 24px;
	width: 24px;
	right: 6px;
	top: 0;
	color: #aaa;
	text-align: center;
	font-size: 26px;
	line-height: 20px
}

#install_group h4 {
	font-size: 14px;
	color: #f08300;
	margin-bottom: 26px
}

#install_group .btn-dl {
	display: block;
	height: 40px;
	width: 130px;
	color: #fff;
	text-align: center;
	line-height: 40px;
	font-size: 16px;
	float: left;
	border-radius: 2px;
	background: -webkit-gradient(linear, 0 0, 0 100%, color-stop(0, #ff9311),
		color-stop(100%, #f08300));
	text-shadow: 0 0 1px #db7800
}

#install_group .btn-close {
	display: inline-block;
	font-size: 14px;
	line-height: 40px;
	margin-left: 12px;
	color: #999;
	position: relative;
	line-height: 14px;
	margin-top: 13px
}

#install_group .btn-close:after {
	content: "";
	display: block;
	position: absolute;
	height: 5px;
	width: 5px;
	right: -8px;
	top: 4px;
	border: 1px solid #999;
	border-left: 0;
	border-bottom: 0;
	-webkit-transform: rotate(45deg)
}
</style>
<style id="ls[D6f]">
* {
	margin: 0;
	padding: 0
}

html {
	background: #f9f9f9
}

body {
	font: 14px/20px Helvetica, sans-serif, arial;
	color: #424242;
	background-color: #F9F9F9;
	-webkit-text-size-adjust: 100%
}

i {
	font-style: normal
}

a {
	color: #002be5
}

a:focus {
	outline: thin dotted
}

a:active, a:hover {
	outline: 0
}

a:link, a:visited {
	text-decoration: none
}

textarea {
	vertical-align: top
}

em {
	color: #e50000;
	font-style: normal
}

ul, ol, li {
	list-style: none
}

h1, h2, h3, h4, h5, h6 {
	font-weight: 400;
	padding: 0;
	margin: 0
}

input[type=search]::-webkit-search-cancel-button, input[type=search]::-webkit-search-decoration
	{
	-webkit-appearance: none
}

input[type=search] {
	-webkit-appearance: textfield;
	-moz-box-sizing: content-box;
	-webkit-box-sizing: content-box;
	box-sizing: content-box
}

button, html input[type=button], input[type=reset], input[type=submit] {
	-webkit-appearance: button
}

button, select {
	text-transform: none
}

button, input, select, textarea {
	font-family: inherit;
	font-size: 100%;
	margin: 0
}

button, input {
	line-height: normal
}

.result .title:visited {
	color: purple
}

.card {
	display: block;
	position: relative;
	background-color: #fff;
	margin: 0 8px;
	border: 0;
	border-bottom: 1px solid #eaeaea
}

#content {
	min-width: 320px;
	overflow: hidden;
	background-color: #fff
}

header {
	position: relative;
	border-bottom: 1px solid #E1E1E1;
	background: #f9f9f9
}

#headbox {
	padding: 7px 8px
}

.clear:after {
	display: block;
	content: " ";
	font-size: 0;
	line-height: 0;
	width: 0;
	height: 0;
	visibility: hidden;
	clear: both
}

#results>a:nth-last-of-type(1) {
	border: 0
}

#results .box {
	display: -webkit-box
}

.result {
	padding: 11px 4px 12px;
	color: #424242
}

#results .flexbox {
	-webkit-box-flex: 1
}

.result h2 {
	font-size: 15px
}

.result .img {
	width: 80px;
	height: 60px;
	overflow: hidden;
	margin: 0 0 0 7px
}

.result .imgs {
	margin: 5px 0 2px
}

.result .imgs>div:nth-of-type(1) .img {
	float: left;
	margin: 3px 0 4px
}

.result .imgs>div:nth-of-type(2) .img {
	margin: 3px auto 4px
}

.result .imgs>div:nth-of-type(3) .img {
	float: right;
	margin: 3px 0 4px
}

.result .abs {
	font-size: 14px;
	color: #424242;
	line-height: 20px;
	margin-top: 6px
}

.result .tail {
	clear: both;
	color: #999;
	font-size: 12px;
	line-height: 14px;
	padding-top: 5px
}

.result .time {
	margin-left: 4px
}

.result .host {
	color: #999;
	font-size: 12px;
	line-height: 14px;
	padding: 0
}

.host img {
	height: 11px;
	width: 10px;
	vertical-align: middle
}

.no-result {
	background: #eee;
	line-height: 20px;
	padding: 17px 16px 50px;
	word-break: break-all
}

.no-result label {
	display: block;
	margin-top: 10px
}

.no-result li {
	font-size: 13px;
	list-style: decimal outside none;
	margin: 0 0 0 21px
}

footer {
	border-top: 1px solid #e0e0e0;
	background: #f9f9f9;
	padding: 6px 8px 0
}

#footnote {
	font-size: 12px;
	text-align: center
}

#footnote .home {
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABoAAAAYBAMAAAAWrPD2AAAAIVBMVEUAAACjo6Ojo6Ojo6Ojo6Ojo6Ojo6Ojo6Ojo6Ojo6Ojo6PRn39dAAAACnRSTlMAqJgHZxF3vIrSznJ07gAAAH5JREFUGNNjwALYpiDzulYlIDgcq1YtQ/C0Vq1a1QDjMK4CghVQDrPUKhBQgPAMV4HBIohU1SoIEAbxzFdBwUIDBgbWWatgoJiBIXIVHCw3YJCCsKDGeiHxHBiYA2HsRcEQl8AshPBEQ0NDq+A8ByCVRU0e26pVymB/LWFgAABe0mQb5AIDLwAAAABJRU5ErkJggg==)
		left center no-repeat;
	background-size: 13px 12px;
	padding-left: 16px;
	margin-top: 10px;
	color: #888;
	display: inline-block
}

#footnote p {
	padding: 9px 0 2px
}

#footnote p a, #footnote p span {
	color: #888;
	padding: 0 14px;
	vertical-align: middle;
	border-left: 1px solid #ccc;
	display: inline-block;
	height: 14px;
	line-height: 14px
}

#footnote p span {
	color: #ccc
}

#footnote p a:first-child, #footnote p span:first-child {
	border: 0
}

#footnote #copy {
	color: #ccc;
	padding: 4px 0 11px
}

.searchbox {
	padding: 0 73px 0 0;
	position: relative;
	z-index: 20
}

.searchbox a {
	text-decoration: none
}

.searchbox .txt {
	position: relative;
	display: block;
	overflow: hidden;
	border: 1px solid #bbb;
	background-color: #fff;
	height: 38px
}

.searchbox .txt input {
	color: #222;
	border: 0;
	margin: 0;
	padding: 0;
	width: 100%;
	height: 100%;
	padding: 6px 36px 6px 7px;
	font-size: 16px;
	outline: 0;
	-webkit-tap-highlight-color: rgba(0, 0, 0, 0);
	-webkit-appearance: none;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box
}

.searchbox .txt input::-webkit-input-placeholder {
	color: #b8b7b7
}

.searchbox .btn_clear {
	position: absolute;
	right: 0;
	top: 0;
	z-index: 2;
	width: 37px;
	height: 37px;
	display: none;
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAqBAMAAAA37dRoAAAAFVBMVEUAAAAAAAAAAAAAAAAAAAAAAAAAAAASAQCkAAAAB3RSTlMzACMsDBwD1g7lRgAAAQpJREFUKM99k01uwjAQhZ8cwt5VYG1XpGtLcIDQql2T3gAQ978CeH48jCJ4i0Tz8mU89owRWcN/wX78k0jcX7B2z+43VAdzNzB9qbtNMHVncX/wrAO7A7yO5F7htSK3ZbXM4AKKOvWRH+4EIFy0rkQpQAmypAnxo/4QMdCrRvR1W2oVoLQzrxnkawbV1XOYpaA1JijcUKxwgsKEcoQEhT8VRYcCgauSuQoz6tUL6hWcqxmyZTA3+NVSa+JoleHE6PnhzLaLSdGE3nZ8bSgavMamoWhw5lMnlGA+dd+hXjvkuzlTN192PpbFlLycqOX0vZlUqnE51fG2vAFVF6h27mYVNUl2C/foxqNEd38SJ8VVujZBAAAAAElFTkSuQmCC)
		no-repeat scroll center center transparent;
	background-size: 16px;
	z-index: 2
}

.searchbox .btn {
	font: 16px/38px 黑体, Helvetica, sans-serif, arial;
	display: block;
	overflow: hidden;
	width: 74px;
	height: 40px;
	color: #424242;
	border: 1px #bbb solid;
	text-align: center;
	position: absolute;
	right: 0;
	top: 0;
	z-index: 5;
	background-image: -webkit-gradient(linear, left top, left bottom, color-stop(0, #f9f9f9),
		color-stop(1, #ebebeb));
	padding: 0;
	border-radius: 0
}

.searchbox .btn_cancel {
	display: none
}

#head-nav {
	position: relative;
	height: 45px;
	background: #4a9be0;
	color: #FFF
}

#head-nav .back {
	position: relative;
	display: block;
	width: 50px;
	height: 45px;
	float: left;
	background-size: 11px 20px;
	z-index: 1
}

#head-nav .back::before {
	-webkit-transform: rotate(-45deg);
	-webkit-transform-origin: left 90%
}

#head-nav .back::after {
	-webkit-transform: rotate(45deg);
	-webkit-transform-origin: left 10%
}

#head-nav .back::after, #head-nav .back::before {
	content: "";
	position: absolute;
	display: inline-block;
	width: 10px;
	height: 2px;
	background: #FFF;
	top: 50%;
	left: 20px;
	margin-top: -1px
}

#head-nav .title {
	position: absolute;
	left: 0;
	right: 0;
	top: 0;
	bottom: 0;
	font-size: 18px;
	line-height: 45px;
	text-align: center;
	color: #FFF
}

#head-nav .index {
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAACoAAAAkCAYAAAD/yagrAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OUJBMDc3NTEwMTAzMTFFNDg4MTlDMjcwMDNBM0ExNDEiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OUJBMDc3NTIwMTAzMTFFNDg4MTlDMjcwMDNBM0ExNDEiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo5QkEwNzc0RjAxMDMxMUU0ODgxOUMyNzAwM0EzQTE0MSIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo5QkEwNzc1MDAxMDMxMUU0ODgxOUMyNzAwM0EzQTE0MSIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/PhDqtkUAAAIGSURBVHja7JbPSwJBFMdHozpUa0QUdSkD6VDRpUNBdpegKLCbROhfEFF/QRL9BXWIOldCP+4RdOkU0S2IpCCzIFARKqi278QEMs7sDx13FXzwWdbdmfe+vnnzZomu60QhMfAG0uxemW9VjjrAvl5sx6CrWoSGQEqX2wuYdVNoC9gCP7o12wGa00InwJ1E0B7YlrxLgiknhDaBdfAlEPEK5gvGTrNNxds32ATNlRI6DK4kmToB3YI5nSAhmXMDRlUK9YIV8C4IlgNRCz4WQUYw/xOsgYZyhfaBc0lGLoDfxopQX2cGvgZKFboEsgKnH2CVZdpujdM5ywarE7MjlDboI8k/vwYjCnrvkEG9n4rqnXcwwxo0b3SXb7Bdr+o0azTpIHMioRpryDKbVPxNwPdkme3+HxIeXMYIIQegn8jNQyprusG7B7BAhd7jxm/iyE2h1JJeXHzcwwRx3w653z5aI2FWvI8gwuqGN1JhRPEiTBPd3GHP344yXwqnl74onrfMAAFWKlkWTESWjQmUE6icjA6CS9BuMVYGjINbpzMatyGSsLFxNzJKl1SzGS8P2pzOKC8yyAIUEuTGtLqRUaudQYkvL6kRqwutC60LrQu1brkS5uRVCQ2BlKQh82gWxuiCk8lsDGEaQkYnEx3QUyWr/Qx6a6FGPUZLHwPpKhD5BKKFD34FGABMac98iu8vewAAAABJRU5ErkJggg==)
		no-repeat center center;
	position: relative;
	display: block;
	width: 50px;
	height: 45px;
	float: right;
	background-size: 21px 18px;
	z-index: 1
}

#pager {
	background: #F9F9F9
}

.searchbox .btn, .searchbox .btn-submit {
	display: none
}

.searchbox .lbtn {
	font: 16px/37px 黑体, Helvetica, sans-serif, arial;
	font-weight: 700;
	display: block;
	overflow: hidden;
	width: 30px;
	height: 34px;
	color: #424242;
	border: 0;
	text-align: center;
	position: absolute;
	right: 9px;
	top: 50%;
	margin-top: -17px;
	z-index: 5;
	background: #FFF;
	padding: 0;
	outline: 0;
	border-radius: 0;
	background:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAeCAYAAAA/xX6fAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyRpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoTWFjaW50b3NoKSIgeG1wTU06SW5zdGFuY2VJRD0ieG1wLmlpZDoyNjA2Rjc5Q0Y5QjkxMUUzODYyQkVDNkEyREM0NzczOCIgeG1wTU06RG9jdW1lbnRJRD0ieG1wLmRpZDoyNjA2Rjc5REY5QjkxMUUzODYyQkVDNkEyREM0NzczOCI+IDx4bXBNTTpEZXJpdmVkRnJvbSBzdFJlZjppbnN0YW5jZUlEPSJ4bXAuaWlkOjI2MDZGNzlBRjlCOTExRTM4NjJCRUM2QTJEQzQ3NzM4IiBzdFJlZjpkb2N1bWVudElEPSJ4bXAuZGlkOjI2MDZGNzlCRjlCOTExRTM4NjJCRUM2QTJEQzQ3NzM4Ii8+IDwvcmRmOkRlc2NyaXB0aW9uPiA8L3JkZjpSREY+IDwveDp4bXBtZXRhPiA8P3hwYWNrZXQgZW5kPSJyIj8+CogrhAAAAm5JREFUeNq0lk9IVFEUxu+MU4rQxllFLgwks6A2gUjaH4UiJbSpLHQpZGALXQxC1CIUIheJ1EJomVCU/yIKKujfRBC5caFZuFAocuMqMRIG+y58Tz7GmTdvxjsHfnA5997zcd8979wTaXq4aLLYPtAKToEDIEr/CpgDr8EU+GECWMRn7jjoB/UZ5neTRnAHJMBN8MFPMJzGVwJGwDsfsXRWzz0jjBFIsAy8BV0gRN86eALawV5QDHaBavqeco3hni7GKMsmWApeglrxTYKD4BJ4BBYZfBXM09fGNROyr5axSv0Eh0ENx0nQB2JgIcDntGvOc0+SvhrGTCtoE6RT/NfBoMndBrnXs07G3iI4IHc2lqeYio7JnQ6kCh4GdZIgvWb71iOJVEeNTcGYLLQZ99OB4C9mtmcxFdR/bcq4M411TAX3y8RXh4LTMq5SwahMLDsU/C3jaKZKs9MU2MKs+lqQXZnGWlHBeZk45FDwiIy/q2BCJlodCmqshApq0b0Iyh2I7WEsz8ZVcAZ8kqS560BwiM+YYeyZ1Cy9ATbklPFtiMXldBvsArYUb9sWPBD/bXAtD7Fu7vXMxnyf6T20RfsLx0XgHqt+ZQChStbh+9xrGKvX7wFeA80iaviozoJRjivADlJB3yjXXEiJvQT+Zeva7A96kpd+he+ZTaQOkou18Q47pAtI27X9BVcp/DkHAbv2DPgoPq8XigTpS20iHeVr0gJOs1PTRvgbeAWeScWygi/ACcn6EE+6HgrQeedjtlt7DhpSOsDL4QI9CjYBz4I34jtnf5NCCXqiLfzkm6WzkIJeAtoi/hj8Abf+CzAAkst9WLeBzlMAAAAASUVORK5CYII=)
		no-repeat center center;
	background-size: 50% 50%
}

.searchbox .btn_clear {
	width: 40px;
	height: 34px;
	right: 2px;
	background-image:
		url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABwAAAAcCAYAAAByDd+UAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyJpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuMy1jMDExIDY2LjE0NTY2MSwgMjAxMi8wMi8wNi0xNDo1NjoyNyAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvIiB4bWxuczp4bXBNTT0iaHR0cDovL25zLmFkb2JlLmNvbS94YXAvMS4wL21tLyIgeG1sbnM6c3RSZWY9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9zVHlwZS9SZXNvdXJjZVJlZiMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENTNiAoV2luZG93cykiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6N0U0NTg5MUYwMUI2MTFFNEFDMjVDNDg1RjBEM0M1OTMiIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6N0U0NTg5MjAwMUI2MTFFNEFDMjVDNDg1RjBEM0M1OTMiPiA8eG1wTU06RGVyaXZlZEZyb20gc3RSZWY6aW5zdGFuY2VJRD0ieG1wLmlpZDo3RTQ1ODkxRDAxQjYxMUU0QUMyNUM0ODVGMEQzQzU5MyIgc3RSZWY6ZG9jdW1lbnRJRD0ieG1wLmRpZDo3RTQ1ODkxRTAxQjYxMUU0QUMyNUM0ODVGMEQzQzU5MyIvPiA8L3JkZjpEZXNjcmlwdGlvbj4gPC9yZGY6UkRGPiA8L3g6eG1wbWV0YT4gPD94cGFja2V0IGVuZD0iciI/Ph6/vd4AAAIVSURBVHjatJbJSgNBEIbHEYKgIgHBsyjmBVxwC55UXCOICyoGfQoRb4J5jIgnQVRwRz0pKkruSTR41VNAo0jQ6F9QgXHo6pnJUvBByMzUNz1dXd0ViUTCcIgK0AuGQTdoBn6+lgZP4AYcgivwq02mEZogDFZY4iZIvgGiIOdF2Ai2QZtRWDyAafCsGoU9+sB9ETKDn6UcQSdhPzgD9UbxQTnOwYAkDIAd4DNKFz7OGbALK8EWqDVKHzWcu9IqXBTmbJ1fYgZ8aZJ+gklOHhHmNJyvUlpnSaH0qzkZxSDYA1UK2Ti4sIzoXVgyLSZXkrTORi2/T8GEbaR2GcWIkIscQRIOaT5V1FZlVqlKRt1oU5NviIRdmhvo8+0LUpVs16HKO2kOX/CjwaHSaEQhXqOqcCOjeDUtjdjwOFKvMgq/6WE9UTP+Vvz/IzVqaUdIu7iPCmQMXCquqapXijQJHwuQDSsKKeRCmiThbQGyXcWcnrmQ3pLwSHPDgiDzCYVE0iVNvmOn1kZt6sOhGu1Lhnrvm9DaAiafQSLCG62BOjCnKf38SGf53lUhFzly+SMGbR13oNUoT8RABy0h07KWaL4yZZBRznl2/Nvx42AKZEsoy3LOuHSmOeECyJRoZCHOqT21nfAOHStyztrsMkmY/7ztYBmkPIhS/Ey79TO6PXlbX6qHd3/aO5tsR/0UH/UPwLVTI/8TYABjtJTyidNR5AAAAABJRU5ErkJggg==)
}

.searchbox {
	border: 1px solid #E1E1E1;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	padding: 0 45px 0 13px;
	background: #FFF;
	overflow: hidden;
	height: 34px
}

#results>a.result:nth-of-type(1) {
	padding-top: 0;
	margin-top: 11px
}

.searchbox .txt {
	padding-left: 0;
	border: 0;
	-webkit-border-radius: 20px;
	border-radius: 20px;
	height: 100%
}

.searchbox .txt input {
	padding-left: 3px
}

.searchbox .split {
	display: block;
	width: 1px;
	height: 18px;
	color: #424242;
	position: absolute;
	right: 46px;
	top: 50%;
	margin-top: -9px;
	z-index: 5;
	background: #E1E1E1;
	padding: 0;
	outline: 0;
	border-radius: 0
}

.backTop {
	background: rgba(0, 0, 0, .5);
	border-radius: 3px;
	width: 36px;
	height: 36px;
	position: fixed;
	bottom: 15px;
	right: 10px;
	z-index: 99;
	display: none
}

.backTop>i {
	position: absolute;
	width: 2px;
	height: 13px;
	background: #FFF;
	left: 50%;
	margin-left: -1px;
	top: 8px
}

.backTop>i:nth-of-type(1) {
	-webkit-transform-origin: center 5%;
	-webkit-transform: rotate(45deg)
}

.backTop>i:nth-of-type(2) {
	-webkit-transform-origin: center 5%;
	-webkit-transform: rotate(-45deg)
}

.backTop>i:nth-of-type(3) {
	height: 18px;
	top: 10px
}

.top8 {
	background: #fff;
	padding: 11px 8px 50px
}

.top8 h3 {
	font-size: 13px;
	color: #63A6E3;
	margin-bottom: 6px;
	margin-left: 4px
}

.top8 ul li {
	width: 50%;
	height: 32px;
	line-height: 32px;
	margin-bottom: 5px;
	text-align: center;
	float: left
}

.top8 ul li a {
	display: block;
	overflow: hidden;
	text-overflow: ellipsis;
	white-space: nowrap;
	height: 32px;
	line-height: 32px;
	padding: 0 10px;
	color: #424242;
	background: #F9F9F9;
	margin-left: 2px;
	margin-right: 3px;
	font-size: 14px
}

.rel-news {
	margin: 0 8px;
	background: #fff;
	overflow: hidden
}

.rel-news h3 {
	border-bottom: 1px solid #e0e0e0;
	color: #717171;
	font-size: 15px;
	line-height: 40px;
	font-weight: 400;
	padding-left: 4px
}

.rel-news li {
	float: left;
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	width: 50%;
	height: 40px;
	line-height: 40px;
	position: relative;
	border-bottom: 1px solid #f7f7f7;
	padding-left: 4px
}

.rel-news li:nth-last-of-type(1), .rel-news li:nth-last-of-type(2) {
	border: 0
}

.rel-news li:nth-of-type(2n) {
	padding-left: 5px
}

.rel-news li:nth-of-type(2n)::after {
	position: absolute;
	top: 50%;
	margin-top: -8px;
	left: 0;
	content: " ";
	font-size: 0;
	line-height: 0;
	overflow: hidden;
	height: 16px;
	width: 1px;
	background-color: #f7f7f7
}

.rel-news a {
	color: #222;
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap
}
</style>
<script>var DEFINED_PARAMS={"pt":"news","cp":"result","vr":"1.0","ip":"117.143.95.118","pg":"1","bd":"","hid":"bd18f6599d0887dad7345edb466ee3aa","qt":"1434207628"},SEARCH_QUERY = "";</script>
<style>
html {
	background: #fff
}
</style>
</head>
<body>
	<div id="content">
		<header>
			<div id="head-nav">
				<span class="title">知识搜索</span><a class="back"
					href="javascript:window.history.back();"></a>
			</div>
			<div class="box" id="headbox">
				<form class="searchbox fill_query" action="/front/m/newsSearch.do" method="get">
					<span class="txt"> 
						<input name="searchText" type="text" maxlength="60" placeholder="请输入查询词" value="" autocomplete="off">
						<div class="btn_clear"></div>
					</span>
					<label class="split"></label>
					<label class="lbtn" for="btn-submit"></label>
					<input class="btn btn-submit" id="btn-submit" type="submit" value="搜新闻">
				</form>
			</div>
		</header>
		
		<!-- 
		暂不使用
		<div class="top8">
			<h3>今日热搜</h3>
			<ul class="clear">
				<li><a
					onclick="return JLOG({'id':'N00013','ps':1,'l':4},this);"
					href="news?q=%E6%9D%9C%E6%B5%B7%E6%B6%9B%E5%8F%98%E5%9E%8B%E7%94%B7">杜海涛变型男</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':2,'l':4},this);"
					href="news?q=90%E5%90%8E%E5%85%B3%E4%B9%8B%E7%90%B3%E8%B5%B0%E7%BA%A2">90后关之琳走红</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':3,'l':4},this);"
					href="news?q=%E6%9D%A8%E6%B4%8B%E9%97%B9%E8%A7%A3%E7%BA%A6%E9%A3%8E%E6%B3%A2">杨洋闹解约风波</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':4,'l':4},this);"
					href="news?q=%E8%9A%8A%E5%AD%90%E5%92%AC%E7%9E%8E%E5%B7%A6%E7%9C%BC">蚊子咬瞎左眼</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':5,'l':4},this);"
					href="news?q=%E5%AF%8C%E7%BF%81%E6%94%B6%E5%85%BB%E5%AD%A4%E5%84%BF%E8%BF%94%E8%B4%AB">富翁收养孤儿返贫</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':6,'l':4},this);"
					href="news?q=%E7%BD%91%E4%B8%8A%E8%BF%BD%E5%9B%BD%E4%BA%A7%E5%89%A7%E8%A6%81%E4%BB%98%E8%B4%B9">网上追国产剧要付费</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':7,'l':4},this);"
					href="news?q=%E5%8F%B2%E4%B8%8A%E6%9C%80%E9%80%86%E5%A4%A9%E5%B9%BF%E5%9C%BA%E8%88%9E">史上最逆天广场舞</a></li>
				<li><a
					onclick="return JLOG({'id':'N00013','ps':8,'l':4},this);"
					href="news?q=%E6%89%BF%E5%BE%B7%E6%B5%B7%E5%B8%82%E8%9C%83%E6%A5%BC">承德海市蜃楼</a></li>
			</ul>
		</div>
		 -->
	</div>
</body>
</html>
