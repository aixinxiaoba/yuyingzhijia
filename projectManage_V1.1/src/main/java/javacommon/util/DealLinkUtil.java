package javacommon.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

//import resourcencodeUtil.IdentifyCode;
public class DealLinkUtil {
	
	private static final String prefix="http://localhost:8080/Proxy/servlet/";
	public static String modifyLink(String html,String baseUri) throws MalformedURLException, IOException{
		Document doc=Jsoup.parse(html,baseUri);
		
		
		Elements elements=doc.select("a[href!=#]");
		adsoluteAHref(elements);
		Elements jsElements=doc.select("script[src]");
		absoluteScriptSrc(jsElements);
		
		Elements formElements=doc.select("form[action]");
		absoluteFormAction(formElements);
		
		Elements linkElements=doc.select("link[href]");
		absoluteLinkHref(linkElements);
		
		
		
		Element  base=doc.select("base").first();
		if(base!=null){
		System.out.println(base.attr("href"));
		base.attr("href", prefix);}else{
			Element head=doc.select("head").first();
			head.append("<base href=\""+prefix+"\">");
		}
		
		return doc.toString();
	}
	
//	public static void modifyCssurl(String urlpath) throws IOException{
//		URL url=new URL("http://localhost:8080/novel/User/register.jsp");//
////		URL url=new URL("http://www.baidu.com/");
//		Document doc=Jsoup.parse(url.openStream(), IdentifyCode.getFileCode(url), "http://sdfsdfsddfBYSJ/");
//		Elements  css=doc.select("[style]");//获取所有含有style属性的元素
//		IteratorElements(css);
//		
//		Elements styleCss=doc.select("style");//获得html中的样式标签如<style type="text/css"></style>
//		IteratorStyle(styleCss);
//		
//		Elements scriptJs=doc.select("script");//获得<script type="text/javascript"></script>
//		IteratorStyle(scriptJs);
//	}
	
	//处理<script type="text/javascript">脚本
	public static void IteratorStyle(Elements elements){
		Iterator<Element> iterator=elements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
				System.out.println(element.data());//获得<script type="text/javascript">中的值
													//<style type="text/css"></style>中的值
		}
	}
	
	//得到元素中内嵌样式style中的值如<div style="width:100px;">
	public static void IteratorElements(Elements elements){
		Iterator<Element> iterator=elements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
			String style=element.attr("style");
			System.out.println(style);
			getURL(style);
		}
	}
	//从style中获得 url的值
	public static void getURL(String url){
		Pattern p = Pattern.compile("url\\((.*)\\)");//匹配  url(任何)
		Matcher m = p.matcher(url);
		if(m.find()){
			System.out.println(m.group(1));//获取括号中的地址
		}
	}
	//将Form action 装换为绝对的url
	public static void absoluteFormAction(Elements formElements){
		Iterator<Element> iterator=formElements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
			String action=element.attr("abs:action");//将所有的相对地址换为绝对地址;
			//添加隐藏域，用来传替url。
			element.append("<input type='hidden' name='action' value='"+action+"'/>");
			element.attr("action",prefix+ "Actionjsp");//装换为
			
		}
	}
	
	//将<script src>转换为绝对地址 
	public static void absoluteScriptSrc(Elements jsElements) throws MalformedURLException{
		Iterator<Element> iterator=jsElements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
			String src=element.attr("abs:src");//将所有的相对地址换为绝对地址;
			element.attr("src",prefix+"Jsjsp?src="+src);//装换为
	//		element.attr("charset",IdentifyCode.getFileCode(src));
		}
	}
	
	//将Img src 装换为绝对的url
	public static void absoluteImagSrc(Elements imagElements) throws MalformedURLException{
		Iterator<Element> iterator=imagElements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
			String src=element.attr("abs:src");//将所有的相对地址换为绝对地址;
			element.attr("src",prefix+"Imgjsp?src="+src);//装换为
			
		}
	}
	
	//将Link href 装换为绝对的url
		public static void absoluteLinkHref(Elements linkElements) throws MalformedURLException{
			Iterator<Element> iterator=linkElements.iterator();
			while(iterator.hasNext()){
				Element element=iterator.next();
				String src=element.attr("abs:href");//将所有的相对地址换为绝对地址;
				element.attr("href",prefix+ "Linkjsp?href="+src);//装换为
	//			element.attr("charset",IdentifyCode.getFileCode(src));//设置外部文件的编码
			}
		}
	//将所有的的<a href>转换为绝对地址
	public static void adsoluteAHref(Elements AElements){
		Iterator<Element> iterator=AElements.iterator();
		while(iterator.hasNext()){
			Element element=iterator.next();
			String href=element.attr("abs:href");//将所有的相对地址换为绝对地址;
			element.attr("href",prefix+"Ajsp?url="+href);
		}
	}
	
	
//	public static void main(String args[]) throws ParseException, IOException{
//	/*	HttpClient httpClient=TestCookie.GetHttpClient();
//		String htmlpage=TestCookie.getPageByGet(httpClient, "http://book.hao123.com/");
//		System.out.println(modifyLink(htmlpage,"http://localhost:8080/Proxy"));*/
//		modifyCssurl("http://www.hao123.com/?tn=98784002_hao_pg");
//	}
}