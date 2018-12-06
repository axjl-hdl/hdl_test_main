package cn.applyCode;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.activation.MimetypesFileTypeMap;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class RequestFuleCard {

	/**
	 * 
	 * @param realName
	 * @param phoneNo
	 * @param email
	 * @param identityNo
	 * @param address
	 * @param idCardFront（图片路径）
	 * @param idCardContrary（图片路径）
	 */
	public static void requestFuleCard(String url,String realName, String phoneNo,
			String email, String identityNo, String address,
			String idCardFront, String idCardContrary) {
		if (StringUtils.isEmpty(realName) || StringUtils.isEmpty(phoneNo)
				|| StringUtils.isEmpty(email) || StringUtils.isEmpty(identityNo)
				|| StringUtils.isEmpty(address) || StringUtils.isEmpty(idCardFront)
				|| StringUtils.isEmpty(idCardContrary)) {
			System.out.println("1,有空");
		}
		String params ="realName="+realName+"&phoneNo="+phoneNo+"&email="+email+
						"&identityNo="+identityNo+"&address="+address;
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("realName", realName);
			paramMap.put("phoneNo", phoneNo);
			paramMap.put("email", email);
			paramMap.put("identityNo", identityNo);
			paramMap.put("address", address);
			System.out.println("请求参数："+paramMap);
			
			Map<String, String> imgMap = new HashMap<String, String>();
			imgMap.put("idCardFront", idCardFront);
			imgMap.put("idCardContrary", idCardContrary);
			
			applyId(url,paramMap,imgMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String send(String webUrl){
		String line = null;
		try {
			URL url = new URL(webUrl);
			//打开url
			HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
			//获取服务器响应代码
			int responseCode = urlConn.getResponseCode();
			if (responseCode==200) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(urlConn.getInputStream(),"utf-8"));
				while ((line = reader.readLine())!=null) {
					System.out.println(new String(line.getBytes(),"utf-8"));
				}
			}else {
				System.out.println("获取不到网页的源码，服务器响应代码为："+responseCode);
			}
		} catch (Exception e) {
			System.out.println("获取不到网页的源码，出现异常："+e);
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static String getApplyId(String identityNo){
		
		try {
			Document doc = Jsoup.connect("http://www.jssy.cn/csp_rest/weixin/applycard/getApplyCode")
					.userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31")
					.data("identityNo", identityNo)
					.ignoreContentType(true)
					.timeout(20000)
					.post();
			System.out.println(doc.text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static String applyId(String reqUrl,Map<String, String> paramMap,Map<String, String> imgMap){
		String newLine  = "\r\n";
 		String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(50000);
			conn.setDoOutput(true); //设置是否向httpUrlConnection输出，以后就可以使用conn.getOutputStream().write()， 默认情况下是false;   
			conn.setDoInput(true);  //设置是否从httpUrlConnection读入，以后就可以使用conn.getInputStream().read()，默认情况下是true; 	
			conn.setUseCaches(false);
//			conn.setInstanceFollowRedirects(true);//本次连接是否自动处理重定向
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive"); 
			conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36"); 
			conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+BOUNDARY);
			
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			//param
			if (paramMap!=null) {
				StringBuffer buf = new StringBuffer();
				Iterator<Map.Entry<String, String>> iter = paramMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String> entry = iter.next();
					String inputName = entry.getKey();
					String inputValue = entry.getValue();
					if (inputValue==null) {
						continue;
					}
					buf.append(newLine).append("--").append(BOUNDARY).append(newLine);
					buf.append("Content-Disposition:form-data;name=\""+inputName+"\""+newLine+newLine);
					buf.append(inputValue);
				}
				out.write(buf.toString().getBytes());
			}
            
			//file
			if (imgMap!=null) {
				Iterator<Map.Entry<String, String>> iter = imgMap.entrySet().iterator();
				while (iter.hasNext()) {
					StringBuffer buf = new StringBuffer();
					Map.Entry<String, String> entry = iter.next();
					String inputName = entry.getKey();
					String inputValue = entry.getValue();
					if (inputValue==null) {
						continue;
					}
					File file = new File(inputValue);
					String fileName = file.getName();
					String contentType = new MimetypesFileTypeMap().getContentType(file); 
					//没有传入文件类型，同时根据文件获取不到类型，默认采用application/octet-stream
					//contentType非空采用filename匹配默认的图片类型
					
					if(!"".equals(contentType)){
						if (fileName.endsWith(".png")) {
							contentType = "image/png"; 
                        }else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg") || fileName.endsWith(".jpe")) {
                        	contentType = "image/jpeg";
                        }else if (fileName.endsWith(".gif")) {
                            contentType = "image/gif";
                        }else if (fileName.endsWith(".ico")) {
                            contentType = "image/image/x-icon";
                        }
                    }
                    if (contentType == null || "".equals(contentType)) {
                    	contentType = "application/octet-stream";
                    }
					
					buf.append(newLine).append("--").append(BOUNDARY).append(newLine);
					buf.append("Content-Disposition: form-data; name=\""+inputName+"\"; filename=\""+fileName+"\""+newLine);
					buf.append("Content-Type:"+contentType+newLine+newLine);
					out.write(buf.toString().getBytes());
					DataInputStream in = new DataInputStream(new FileInputStream(file));
					int num = 0;
					byte[] bytes = new byte[1024];
					while ((num=in.read(bytes))!=-1) {
						out.write(bytes,0,num);
					}
					in.close();
				}
			}
			byte[] endData = (newLine+"--"+BOUNDARY+"--"+newLine).getBytes();
            out.write(endData);
			out.flush();  //刷新一下该缓冲流，也就是会把数据写入到目的位置
			out.close();
            //================================================================================================
            int resposneCode = conn.getResponseCode();
            System.out.println("status code: "+resposneCode); 
            if (resposneCode==200) {
	            //读取urlConnection的响应
	            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8")); 
	            String line = "";
	            String str = "";
	            while ((str=in.readLine())!=null) {
					line = line+str;
				}
	            int len = "<span class=\"label\">".length();
	            System.out.println(line.substring(line.indexOf("<span class=\"label\">")+len,line.indexOf("</span>")));
	            in.close();
            }else {
				System.out.println(conn.getResponseMessage());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	
}
