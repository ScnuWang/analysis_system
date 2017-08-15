package cn.geekview.analysisSystem.utils;

import java.util.HashMap;
import java.util.Map;

import cn.geekview.analysisSystem.entity.model.TdreamCurrency;

/**
 * 公共的参数
 * @author Jason
 *
 */
public class CommonAPI {
	
	public static String CLASSPATH; 
	
	public static final double MSEC_DAY = 24*60*60*1000;
	
	public static String TEMPLATEPATH = CommonAPI.CLASSPATH + "/ftl/";//为啥这样写，取不到值呢
	
	public static boolean statisticsFlag = false;
	
	public static Map<String,TdreamCurrency> CURRENCY_MAP = new HashMap<String, TdreamCurrency>();
}
