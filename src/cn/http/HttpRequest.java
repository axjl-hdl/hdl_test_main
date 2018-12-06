package cn.http;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class HttpRequest {
	private static final Logger logger = Logger.getLogger(HttpRequest.class);
	
	//get请求
	public static String sendGet(String url,String params){
		String sendStr=url+"?"+params;
		String resultMsg="";
		BufferedReader br=null;
		InputStream in=null;
		URL requestUrl=null;
		try {
			requestUrl=new URL(sendStr);
			//打开和URL之间的连接
			URLConnection conn=requestUrl.openConnection();
			//设置通用的请求属性
			conn.setRequestProperty("content-type", "text/html");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//建立实际的连接
			conn.connect();
			//获取所有响应头字段
			Map<String , List<String>> map = conn.getHeaderFields();
			//遍历所有的响应头字段 map.keySet()获取map的所有key
			for (String key : map.keySet()) {
				System.out.println(key+" -- "+map.get(key));
			}
			
			//定义bufferedReader输入流来读取URL的响应
			in=conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String lineString;
			while ((lineString = br.readLine()) != null) {
				resultMsg+=lineString;
			}
		} catch (Exception e) {
			logger.info("访问远程服务器超时：<" + requestUrl + ">" + e.getMessage());
			e.printStackTrace();
		} finally{
			try {
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				logger.info("访问远程服务器超时：<" + requestUrl + ">，关闭读取流异常。" + e.getMessage());
			}
		}
		return resultMsg;
	}
	
	//post请求
	public static String sendPost(String url,String params){
		String resultMsg="";
		BufferedReader br=null;
		InputStream in=null;
		PrintWriter out=null;
		URL requestUrl=null;
		try {
			requestUrl=new URL(url);
			URLConnection conn=requestUrl.openConnection();
			conn.setRequestProperty("content-type", "text/html");
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent",  "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//发送POST请求必须设置如下两行
			conn.setDoOutput(true);
			conn.setDoInput(true);
			//获取URLConnection对象对应的输出流
			out = new PrintWriter(conn.getOutputStream());
			//发送请求参数
			out.print(params);
			//flush输出流的缓冲
			out.flush();
			
			//定义bufferedReader输入流来读取URL的响应
			in=conn.getInputStream();
			br = new BufferedReader(new InputStreamReader(in,"UTF-8"));
			String lineStr;
			while ((lineStr = br.readLine()) != null) {
				resultMsg+=lineStr;
			}
		} catch (Exception e) {
			logger.info("访问远程服务器超时：<" + requestUrl + ">" + e.getMessage());
			e.printStackTrace();
		} finally{
			try {
				if (out!=null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (Exception e) {
				logger.info("访问远程服务器超时：<" + requestUrl + ">，关闭读取流异常。" + e.getMessage());
			}
		}
		return resultMsg;
	}
	
	public static void main(String[] args){
		System.out.println(2018-1949);
	}
	
}
