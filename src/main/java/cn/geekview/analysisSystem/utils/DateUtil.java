package cn.geekview.analysisSystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * 时间相关的工具类
 * @author Jason
 *
 */
public class DateUtil {
	
	/**
	 * 在某个特定的时间日期上面运算
	 * 
	 * @param date 计算的时间yyyy年MM月dd 00:00:00
	 * @param amount 运算的天数
	 * @return Date 返回运算后的天数
	 */
	public static Date addInteger(Date date, int amount) {  
		Date myDate = null;  
		try {
			SimpleDateFormat sdf_cn = new SimpleDateFormat("yyyy年MM月dd日  '00:00:00'");
			Date d =	sdf_cn.parse(sdf_cn.format(date));
	        if (date != null) {  
	            Calendar calendar = Calendar.getInstance();  
	            calendar.setTime(date);  
	            calendar.add(Calendar.DAY_OF_MONTH, amount);  
	            myDate = calendar.getTime();  
	        } 
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return myDate;  
    }
	
	
}
