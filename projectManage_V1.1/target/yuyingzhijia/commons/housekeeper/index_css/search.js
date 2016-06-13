$(function(){
		$("#index-search").click(function(){
		document.searcform_new.submit();
	})
	$("#index-search-key").click(function()
	{
		if($(this).val()=='月嫂')
		{
		   $(this).val('');
		}
		$("#searchdmenu").show();
	})
	$("#index-search-key").blur(function()
	{
	 if($(this).val()=='')
	 {
		   $(this).val('月嫂');
	 }
	 $("#searchdmenu").hide(500);
	 
	})
		   })
	function AddFavorite(sURL, sTitle)
{
    try
    {
        window.external.addFavorite(sURL, sTitle);
    }
    catch (e)
    {
        try
        {
            window.sidebar.addPanel(sTitle, sURL, "");
        }
        catch (e)
        {
            alert("加入收藏失败，请使用Ctrl+D进行添加");
        }
    }
}