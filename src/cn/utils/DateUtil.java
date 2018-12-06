package cn.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	
	//日期字符串格式化
	public static String transDateToStr(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
	
	//时间戳，从格林威治时间1970-1-1 00:00:00至今的毫秒数
	public static String getTimeStamp(){
		return String.valueOf(new Date().getTime());
	}
	
	public static Date dateAdd(){
		Calendar cal = Calendar.getInstance();//使用当前所在时区获得当前时间日历
		cal.add(Calendar.DAY_OF_MONTH, -1);//当前日期减一天
		return cal.getTime();
	}
}
