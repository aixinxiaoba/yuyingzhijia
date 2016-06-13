(function(window){
    /*翻页按钮 只有上、下页*/
	if(!$('a.ptfontcon').size()){
		//$('.pageNum').css('display','none');
	}else if($('a.ptfontcon').size()==1){
		$('.pageNum').css('display','block');
		if($('a.ptfontcon').prev().hasClass('pagefontcon')){
			$('#ptNext').attr('href',$('a.ptfontcon').eq(0).attr('href'));
			$('#ptPrev').removeAttr('href').css('backgroundColor','#e0e0e0').css('border','#e0e0e0 1px solid').css('color','#fff');
		}else if($('a.ptfontcon').next().hasClass('pagefontcon')){
			$('#ptPrev').attr('href',$('a.ptfontcon').eq(0).attr('href'));
			$('#ptNext').removeAttr('href').css('backgroundColor','#e0e0e0').css('border','#e0e0e0 1px solid').css('color','#fff');
		}
	}else if($('a.ptfontcon').size()==2){
		$('.pageNum').css('display','block');
		$('#ptNext').attr('href',$('a.ptfontcon').eq(1).attr('href'));
		$('#ptPrev').attr('href',$('a.ptfontcon').eq(0).attr('href'));
	}

    /*当前页数和总页数*/
    $('.curPage').text($('#mcurrentpage').text());
    $('.totalPage').text($('#mpagecount').text());

    /* video */
    if($("video").length >0){
        $("video").each(function(){
            $(this).attr("poster",$("video").siblings("img").attr("src"));
        });
    }
})(window);


$(function(){
    /*share*/
	var qqLink="http://api.bshare.cn/share/qzone?url="+encodeURIComponent(window.location.href)+"&title="+encodeURIComponent($('#articleTitle').text())+"&publisherUuid=fbe7b28e-050d-4ab3-9af4-0740ed16ea11"+"&summary="+encodeURIComponent($("meta[name='description']").attr('content'));
	var sinaLink="http://api.bshare.cn/share/sinaminiblog?url="+encodeURIComponent(window.location.href)+"&title="+encodeURIComponent($('#articleTitle').text())+"&publisherUuid=fbe7b28e-050d-4ab3-9af4-0740ed16ea11"+"&summary="+encodeURIComponent($("meta[name='description']").attr('content'));
	$('#qzone').attr('href',qqLink);
	$('#sinaminiblog').attr('href',sinaLink);


    //new recommend
	$('.news_list').css('display','block');
	// $('.nav_content_item').css('max-height','980px');
	$('.sports').css('display','block');
	$('.hot1').click(function(){
		$(this).parent().parent().parent().find('.list_wrap').css('display','none').eq(1).css('display','block');
	});
	$('.hot2').click(function(){
		$(this).parent().parent().parent().find('.list_wrap').css('display','none').eq(2).css('display','block');
	});

	//底部回退
	/*var href=$('.return_btn').attr('href');
	$('.return_btn').remove();
	$('.foot_nav').prepend("<a atremote href=\'http://m.gmw.cn/"+href+"\' class=\'return_btn\'></a>");*/


    //字体增大减小
	var fontSize=parseInt($('#contentMain p').css('fontSize'));
	$('#fontPlus').click(function(){
		fontSize++;
		if(fontSize>=24){
			fontSize=24;
		}
		$('#contentMain p').css('fontSize',fontSize+'px');
	});
	$('#fontMinus').click(function(){
		fontSize--;
		if(fontSize<=16){
			fontSize=16;
		}
		$('#contentMain p').css('fontSize',fontSize+'px');
	});

    /*文章页内的来源，要求去掉来源链接，只保留来源文字
    if($("#source").length>0){
        $("#source").html($("#source").text());
    }*/



	var oT=document.getElementById('goTop');
	var scrollTop=null;

	window.onscroll=function(){
		scrollTop=document.documentElement.scrollTop||document.body.scrollTop;
		if(scrollTop>100){
			oT.style.display='block';
		}else{
			oT.style.display='none';
		}
	};
	oT.onclick=function(){
		document.documentElement.scrollTop=document.body.scrollTop=0;
	};

});

//猜你喜欢限字数
$(function(){
	if(document.body.clientWidth<600){
		setTimeout(function(){
			if($('.b-recomm-list li a')){
				$('.b-recomm-list li a').each(function(){
					var maxwidth=16;
					if($(this).text().length>maxwidth){
						$(this).text($(this).text().substring(0,maxwidth));
						$(this).html($(this).html()+'…');
					}
				});
			}
		},1000);
	}
});