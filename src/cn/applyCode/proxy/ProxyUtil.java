package cn.applyCode.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ProxyUtil {
	//获取代理ip列表
	public static List<String> getProxyIpList(){
		try {
			Document doc = Jsoup.connect("http://www.xicidaili.com/nn/1")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.ignoreContentType(true)
					.timeout(20000)
					.get();
			String text = doc.text();
			List<String> list = getIpList(text);
			return list;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<String> getIpList(String text){
		List<String> list = new ArrayList<String>();
		//ip地址的正则表达式
		String regex="\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\s\\d{1,5}\\s";
		/*
		 * 作为字符串指定的正则表达式必须首先编译到这个类的实例中。
		 * 然后得到的模式可用于创建一个匹配对象，可以匹配任意字符序列与正则表达式。
		 * 所有国家参与执行匹配驻留在匹配，所以许多匹配器可以共享相同的模式。
		 */
		Pattern pattern = Pattern.compile(regex);
		/*
		 * 通过解释模式在字符序列上执行匹配操作的引擎。
		 * 匹配器是通过调用模式匹配方法从模式创建
		 */
		Matcher matcher = pattern.matcher(text);
		boolean result = matcher.find();
		while (result) {
			/*
			 * 返回与前面正则模式相匹配的文本串
			 */
			list.add(matcher.group().trim());
			result = matcher.find();
		}
		return list;
	}
	
	//业务请求
	public static String getApplyId(String identityNo){
//		List<String> list = getProxyIpList();
//		System.out.println("【业务请求】--"+list.size());
//		int tar = (int)(Math.random()*(list.size()));
//		String ip = list.get(tar);
//		System.out.println("ip:"+ip);
//		String[] arr = ip.split(" ");
		//设置代理IP
//		System.setProperty("http.proxyHost", arr[0]);
//		System.setProperty("http.proxyPort", arr[1]);
		try {// http://www.jssy.cn/csp_rest/weixin/applycard/getApplyCode
			Document doc = Jsoup.connect("http://sgs-wx.jspec.cn/wxweb/toyybk")
					.userAgent("Mozilla/5.0 (Linux; Android 6.0; 1503-M02 Build/MRA58K) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/37.0.0.0 Mobile MQQBrowser/6.2 TBS/036558 Safari/537.36 MicroMessenger/6.3.25.861 NetType/WIFI Language/zh_CN")
					.ignoreContentType(true)
					.timeout(10000)
					.get();
			System.out.println(doc.html());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
}
