package cn.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AES {
	//偏移量
	private static final String VIPARA = "123456787654321a";
    //填充模式  
    private static final String AES_TYPE = "AES/CBC/PKCS5Padding"; 
    //public static final String AES_TYPE = "AES/ECB/PKCS7Padding"; 
    //public static final String AES_TYPE = "AES/ECB/NoPadding";  
    //public static final String AES_TYPE = "AES/CBC/ZeroPadding";
    
    /*
	 * PKCS7Padding此类型 加密内容,密钥必须为16字节的倍数位,否则抛异常,需要字节补全再进行加密
	 * PKCS5Padding比PKCS7Padding效率高，PKCS7Padding可支持IOS加解密  
	 * java不支持ZeroPadding
	 * CBC需要在 cipher.init(Cipher.DECRYPT_MODE, key，[偏移量]);加偏移量，ECB模式没有偏移量
	 */ 
    
	public static void main(String[] args) {
		try {
			aes_encrypt("hahah", "bbaa");
			
//			aes_decrypt("AkI5eeMde4YUUzZcRLpEWQ==", "bbaa");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * AES加密
	 * content 未加密明文
	 * password 加密密码，但不是加密密钥，密钥由key_factor生成
	 */
	public static String aes_encrypt(String content, String password)throws Exception{
		KeyGenerator key_factor = KeyGenerator.getInstance("AES");//创建AES密钥生产者
		
		key_factor.init(128, new SecureRandom(password.getBytes()));
		
		SecretKey secret_key = key_factor.generateKey();//根据用户密码password生成秘钥
		
		byte[] enCodeFormat = secret_key.getEncoded();//返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
		
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");//转换为AES专用秘钥
		
		Cipher cipher = Cipher.getInstance(AES_TYPE);//创建密码器
		
		byte[] byteContent = content.getBytes("UTF-8");
		
		//偏移量
		IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
		cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
		
		//cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
		
		byte[] result = cipher.doFinal(byteContent);// 加密
		//aes_encrypt end
		
		String encryptStr = new BASE64Encoder().encode(result);
		System.out.println("生成AES加密串：" + encryptStr);
		return encryptStr;
	}
	
	/*
	 * AES解密
	 * ciphertext 加密密文
	 * password 密码
	 */
	public static String aes_decrypt(String cipher_text, String password)throws Exception{
		KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
		kgen.init(128, new SecureRandom(password.getBytes()));
        
        SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
        byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥
        SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
        Cipher cipher = Cipher.getInstance(AES_TYPE);// 创建密码器
        
        IvParameterSpec zeroIv = new IvParameterSpec(VIPARA.getBytes());
        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);// 初始化为解密模式的密码器
        
        byte[] result = cipher.doFinal(new BASE64Decoder().decodeBuffer(cipher_text));  
        String decrpytStr = new String(result, "UTF-8"); // 明文  
        System.out.println("生成AES解密串：" + decrpytStr);
        return decrpytStr;
	}
}
