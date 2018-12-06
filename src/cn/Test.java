package cn;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.encrypt.MD5Util;
import cn.http.HttpClientUtil;
import cn.http.HttpRequest;
import cn.utils.ExcelUtil;

public class Test {

	public static void main(String[] args) {
//		String res = sendNotice();
//		System.out.println(res);
		
//		int a = 11>>>1;
//		System.out.println(a);
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("name", "hudl");
//		map.put("bir", 12);
//		map.put("sex", "男");
//		Set<Entry<String, Object>> set = map.entrySet();
//		for (Entry<String, Object> entry : set) {
//			System.out.println(entry.getKey());
//		}
		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("phone", "18363971316");
//		System.out.println(HttpClientUtil.requestByPostMethod("http://api.gizwits.com/app/sms_code", map));
		
	
	}
	
	public static int f(int x){
		int a = x>>1;
//		a = a*(a+1);
		g(a);
		System.out.println(a+","+x);
		return a+x;
	}
	
	public static void g(int a){
		a = a*(a+1);
	}
	
	public static String sendNotice(){
		String url = "http://callback.app.ejiaofei.net:11140/callback/notify/5002491";
		
		String merOrderNo = "1214151323573443003812891";
		String orderNo = "1214151323573443003812891";
		String orderStatus = "3";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merOrderNo", merOrderNo);
		map.put("orderNo", orderNo);
		map.put("orderStatus", orderStatus);
		String sign = MD5Util.ascEncode(map, "123456789");
		
		String params = "merOrderNo="+merOrderNo+"&orderNo="+orderNo+"&orderStatus="+orderStatus+"&sign="+sign;
		String res = HttpRequest.sendPost(url, params);
		return res;
	}
	

}
