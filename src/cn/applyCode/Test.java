package cn.applyCode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.applyCode.proxy.ProxyUtil;
import cn.http.HttpRequest;


public class Test {

	public static void main(String[] args) {
		String url = "http://www.jssy.cn/csp_rest/weixin/applycard/preApplyCard";
		String realName = "贾安宜";
		String phoneNo = "15701658489";
		String email = "dasf@qq.com";
		String identityNo = "622724199412226477";
		String address = "海淀";
		String idCardFront = "E:/hdl/001.jpg";
		String idCardContrary = "E:/hdl/yzn.jpg";
		
//		RequestFuleCard.requestFuleCard(url, realName, phoneNo, email, identityNo,
//				address, idCardFront, idCardContrary);

//		RequestFuleCard.getApplyId("622724199412226477");

		ProxyUtil.getApplyId("370782199311065213");
		
//		String str = "123 1231 12312 123";
//		String[] arr = str.split(" ");
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i].length());
//		}
		
//		try {
//			System.out.println(URLDecoder.decode(URLDecoder.decode("%25E4%25B8%25AD%25E5%259B%25BD", "utf-8"),"utf-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
	}

}
