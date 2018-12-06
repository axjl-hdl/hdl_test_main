package cn.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import cn.utils.ToMapUtil;

public class HttpClientUtil {
	protected static Logger logger = Logger.getLogger(HttpClientUtil.class);
	/**
     * 通过GET方式发起http请求
     */
    public static String requestByGetMethod(String url,String params){
        //创建默认的httpClient实例
        CloseableHttpClient httpClient = getHttpClient();
        try {
            //用get方法发送http请求
            HttpGet get = new HttpGet(url+"?"+params);
            logger.info("【请求url:"+get.getURI()+"】");
            
            CloseableHttpResponse httpResponse = null;
            //发送get请求
            httpResponse = httpClient.execute(get);
            try{
                //response实体
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                	String res = EntityUtils.toString(entity);
                	Map<String,Object> resMap = ToMapUtil.getMapByJson(res);
                	resMap.put("errmsg", java.net.URLDecoder.decode(resMap.get("errmsg").toString(),"UTF-8"));
                	
                	logger.info("【响应状态码:"+httpResponse.getStatusLine()+"】");
                	logger.info("【响应内容:"+resMap.toString()+"】");
                    return resMap.toString();
                }
            }finally{
                httpResponse.close();
            }
        } catch (Exception e) {
        	logger.info("【httpClient请求异常】");
            e.printStackTrace();
        } finally{
            try{
                closeHttpClient(httpClient);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return null;
    }
     
     
    /**
     * POST方式发起http请求
     */
    public static String requestByPostMethod(String url,Map<String,Object> paramsMap){
        CloseableHttpClient httpClient = getHttpClient();
        try {
            HttpPost post = new HttpPost(url);          //这里用上本机的某个工程做测试
            post.setHeader("X-Gizwits-Application-Id", "2017120300341625");
            post.setHeader("X-Gizwits-User-token", "adf567f57c8c41cda98d814c25bd6845");
            //创建参数列表
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            for (String key : paramsMap.keySet()) {
            	list.add(new BasicNameValuePair(key, paramsMap.get(key).toString()));
			}
            //url格式编码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list,"UTF-8");
            post.setEntity(uefEntity);
            System.out.println("POST 请求...." + post.getURI());
            //执行请求
            CloseableHttpResponse httpResponse = httpClient.execute(post);
            try{
                HttpEntity entity = httpResponse.getEntity();
                if (null != entity){
                	String res = EntityUtils.toString(entity);
                	Map<String,Object> resMap = ToMapUtil.getMapByJson(res);
                	
//                	logger.info("【响应状态码:"+httpResponse.getStatusLine()+"】");
//                	logger.info("【响应内容:"+resMap.toString()+"】");
                	System.out.println("【响应状态码:"+httpResponse.getStatusLine()+"】");
                	System.out.println("【响应内容:"+resMap.toString()+"】");
                    return resMap.toString();
//                    System.out.println("-------------------------------------------------------");
//                    System.out.println(EntityUtils.toString(entity));
//                    System.out.println("-------------------------------------------------------");
                }
            } finally{
                httpResponse.close();
            }
             
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                closeHttpClient(httpClient);                
            } catch(Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }
     
    private static CloseableHttpClient getHttpClient(){
        return HttpClients.createDefault();
    }
     
    private static void closeHttpClient(CloseableHttpClient client) throws IOException{
        if (client != null){
            client.close();
        }
    }
}
