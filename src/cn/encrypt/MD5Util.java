package cn.encrypt;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Map;

public class MD5Util {
	//32位加密
	public static String encode(String verifyStr) {
		try {
			//update进行加密编码，digest进行赋值
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(verifyStr.getBytes("utf-8"));
			StringBuilder encStr = new StringBuilder();
			for (byte b : result) {
				int number = b & 0xff;//按位与操作，即b的二进制和0xFF的二进制按位比较，只有都为1时才返回1
				String hex = Integer.toHexString(number);
				if (hex.length() == 1) {
					encStr.append("0" + hex);
				} else {
					encStr.append(hex);
				}
			}
			//大写
//			String encVerify = encStr.toString().toUpperCase();
			//小写
			String encVerify = encStr.toString().toLowerCase();
			return encVerify;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//请求参数ASCII升序排序
	public static String ascEncode(Map<String, Object> map,String apikey){
		if (map == null || map.isEmpty()) {
			return null;
		}
		// 对参数名按照ASCII升序排序
		Object[] key = map.keySet().toArray();
		Arrays.sort(key);
		// 生成加密原串
		StringBuffer res = new StringBuffer(128);
		for (int i = 0; i < key.length; i++) {
			Object k = key[i];
			res.append(k + "=" + map.get(k).toString() + "&");
		}
		String verifyStr = res.toString()+"key="+apikey;
		return encode(verifyStr);
	}
	
	public static void main(String[] args){
		byte[] res = new String("a").getBytes();
		System.out.println("res:["+res+"]");
		for (byte b : res) {
			int num = b & 0xff;
			System.out.println(b+","+num);
		}
		
	}
}
