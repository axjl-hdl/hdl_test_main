package cn.ms;

public class StringTest {
	
	public static void main(String[] args){
		decStr("123abc");
	}
	
	
	//字符串反序
	public static void decStr(String str){
		StringBuffer buf = new StringBuffer(str);
		buf.reverse();
		System.out.println(buf);
	}
}
