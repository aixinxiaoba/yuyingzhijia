
(function(){
var option = {"zIndex": "9999999999", "product": "float", "theme_version": "2.9.10", "fullbg": "pictures/gt/c6b163230/c6b163230.jpg", "staticserver": "http://static.geetest.com/", "challenge": "34e760cc244c27a4f52ef56e382620dfff", "ypos": 10, "height": 116, "apiserver": "http://api.geetest.com/", "sliceurl": "pictures/gt/c6b163230/slice/14d4df8a.png", "theme": "golden", "version": "2.9.10", "link": "", "imgserver": "http://static.geetest.com/", "https": false, "logo": true, "geetestid": "d52c132b7f43064285aeaabd68e881f3", "id": "a34e760cc244c27a4f52ef56e382620df", "imgurl": "pictures/gt/c6b163230/bg/14d4df8a.jpg"};
if (!window.GeeTestOptions) {
window.GeeTestOptions = [option];
}
else {
GeeTestOptions = GeeTestOptions.concat(option);
};
var node, scripts = document.body.getElementsByTagName('script'),
src = "http://api.geetest.com/get.php?gt=d52c132b7f43064285aeaabd68e881f3";
for (var i = 0; i < scripts.length; i++) {
if (scripts[i].src == src) {
node = scripts[i];
var gs = document.createElement("script");
gs.type = "text/javascript";
gs.id = option.id + "_script";
gs.charset = "utf-8";
gs.async = true;
gs.src = option.staticserver + "static/js/geetest." + option.version + ".js";
node.parentNode.insertBefore(gs, node.nextSibling);
node.parentNode.removeChild(node);
var ie6 = /msie 6/i.test(navigator.userAgent),
ieimg = ie6 ? "gif" : "png";
var retina = window.devicePixelRatio && window.devicePixelRatio > 1,
rImg = option.theme + "/sprite" + (retina ? "2x" : "") + "." + option.theme_version + '.' + ieimg;
document.createElement("img").src = option.staticserver + "static/" + rImg;
if (!document.getElementById("gt_css")) {
var css = document.createElement("link");
css.setAttribute("rel", "stylesheet");
css.setAttribute("type", "text/css");
css.setAttribute("href", option.staticserver + "static/" + option.theme + "/style" + (option.https ? "_https" : "") + "." + option.theme_version + ".css");
css.setAttribute("id", "gt_css");
document.getElementsByTagName("head")[0].appendChild(css)
}
break;
}
}
})()