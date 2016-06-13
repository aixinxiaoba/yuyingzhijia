// 懒人之家 www.lanrenzhijia.com
function loaded () {
    var myScroll,
        upIcon = $("#up-icon"),
        downIcon = $("#down-icon"),
        distance = 30; //滑动距离

    myScroll = new IScroll('#wrapper', { probeType: 3, mouseWheel: true,click: true  });

    myScroll.on("scroll",function(){
        var y = this.y,
            maxY = this.maxScrollY - y,
            downHasClass = downIcon.hasClass("reverse_icon"),
            upHasClass = upIcon.hasClass("reverse_icon");

        if(y >= distance){
            !downHasClass && downIcon.addClass("reverse_icon");
            return "";
        }else if(y < distance && y > 0){
            downHasClass && downIcon.removeClass("reverse_icon");
            return "";
        }

        if(maxY >= distance){
            !upHasClass && upIcon.addClass("reverse_icon");
            return "";
        }else if(maxY < distance && maxY >=0){
            upHasClass && upIcon.removeClass("reverse_icon");
            return "";
        }
    });
    
    // 第几页
    var pageNo = parseInt($("#page").val()) + 1;
    var isLoad = true;
    function upAjax(){
    	// 判断是否需要记载。
    	if (!isLoad)
    	{
    		return;
    	}
        // var params = '{"params":{"shopType":"0","sort":"0","cityId":"1","pageRows":"10","deviceType":"MX4","toPage":"1","userX":"118.790609","userY":"32.047616","filter":"0"},"page":2}';
        var url = "/front/m/loadNewsList.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"page":pageNo,"rows":20,"menuID":$("#menuID").val(),"lProjectMenuID":$("#lProjectMenuID").val()},
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            dataType: "json"
            //contentType: "application/json; charset=utf-8"
        }).done(function(d) {
            if (d && d.length > 0) {
//                console.log(d.response.shopList);
                var content = "";
                for(var i=0,len=d.length;i<len;i++){
                	var tagA = "<A href='/front/yuyingshi/detail.do?newsID=" + d[i].LId + "'";
                	tagA+="target='_self'>" + d[i].strFormatTitle + "</A>";
                    content += "<li>"+ tagA +"</li>"
                }
                
                $('#scroller-content ul').append("<li style='color:red;text-align:left;font-size:14px'>已为你准备20条信息</li>");
                
                $('#scroller-content ul').append(content);
                myScroll.refresh(pageNo+1);
                pageNo++;
            } else
            {
            	$('#scroller-content ul').append("<li style='color:red;text-align:center;font-size:14px'>无更多信息!</li>");
            	isLoad = false;
            	myScroll.refresh(pageNo);
            }
        }).fail(function() {
            document.getElementById('list').innerHTML = "<font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font>";
        })
    }

    function downAjax(){
    	// var params = '{"params":{"shopType":"0","sort":"0","cityId":"1","pageRows":"10","deviceType":"MX4","toPage":"1","userX":"118.790609","userY":"32.047616","filter":"0"},"page":2}';
        var url = "/front/m/loadNewsList.do";
        $.ajax({
            type: "post",
            url: url,
            data: {"page":1,"rows":20,"menuID":$("#menuID").val(),"lProjectMenuID":$("#lProjectMenuID").val()},
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            dataType: "json"
            //contentType: "application/json; charset=utf-8"
        }).done(function(d) {
            if (d && d.length > 0) {
//                console.log(d.response.shopList);
                var content = "";
                for(var i=0,len=d.length;i<len;i++){
                	var tagA = "<A href='/front/yuyingshi/detail.do?newsID=" + d[i].LId + "'";
                	tagA+="target='_self'>" + d[i].strFormatTitle + "</A>";
                    content += "<li>"+ tagA +"</li>"
                }
                $('#scroller-content ul').html("");
                $('#scroller-content ul').append(content);
                pageNo = 2;
                isLoad = true;
                myScroll.refresh(1);
            }
        }).fail(function() {
            document.getElementById('list').innerHTML = "<font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font>";
        })
        /*var params = '{"params":{"shopType":"0","sort":"0","cityId":"1","pageRows":"10","deviceType":"MX4","toPage":"1","userX":"118.790609","userY":"32.047616","filter":"0"}}';
        $.ajax({
            type: "post",
            url: "url",
            data: params,
            crossDomain: true,
            xhrFields: {
                withCredentials: true
            },
            dataType: "json",
            contentType: "application/json; charset=utf-8"
        }).done(function(d) {
            if (d && d.response.shopList.length > 0) {
                console.log(d.response.shopList);
                var content = "";
                for(var i=0,len=d.response.shopList.length;i<len;i++){
                    content += "<li>"+ d.response.shopList[i].shopName +"</li>"
                }
                $('#scroller-content ul').prepend(content);
                myScroll.refresh(d.response.page.pageAmount);
            }
        }).fail(function() {
            document.getElementById('list').innerHTML = "<font style='font-size:0.16rem;'>数据请求失败，请重新刷新</font>";
        })*/
    }
    // 下拉刷新事件
    myScroll.on("slideDown",function(){
        if(this.y > distance){
            downAjax();
            upIcon.removeClass("reverse_icon")
        }
    });
    // 上拉滑动事件
    myScroll.on("slideUp",function(){
        if(this.maxScrollY - this.y > distance){
//            upAjax();
//            upIcon.removeClass("reverse_icon");
        }
    });
    
    // 滚动到底端触发
    myScroll.on("scrollEnd",function(){
    	if(this.maxScrollY - this.y >= -400){
    		upAjax();
            upIcon.removeClass("reverse_icon");
        }
    });
    
}
loaded ();