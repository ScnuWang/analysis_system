package cn.geekview.analysisSystem.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
/**
 * 极分、客分计算
 * @author Jason
 *	极分：类别中金额最高的1000分，其他的产品按照金额计算进行排名：金额除以最高的金额*1000
 *	客分：活跃度指关注的人数，点赞的人数，支持的人数之和最多的1000分，其他的产品按照金额计算进行排名：总和除以最高的*1000
 *	
 *	一个分类下支持人数最多的目标价格
 *	
 *	加入与小米产品的对比
 *
 *
 *	目前由于有些分类的最值还是null导致出错，还需要改
 */
public class DataAnalysis {
	private static final int G_TOP_SCORE = 1000;//最高的极分
	private static final int K_TOP_SCORE = 1000;//最高的客分
	private static final int COREPRICE_SCORE = 1000;//最高的极客分（核心价格的得分）
	
	//计算总体排名
	public static void statisticsAnalysis(Map<String,Object> parmMap) {
		
		int G_score = 0;
		int K_score = 0;
		int corePrice_score = 0;
		
		BigDecimal currMoney = (BigDecimal) parmMap.get("currMoney");
		BigDecimal top_currMoney = (BigDecimal) parmMap.get("top_currMoney");
		
		Integer supportPerson = (Integer) parmMap.get("support_person");
		Integer top_supportPerson = (Integer) parmMap.get("top_supportPerson");
		

		BigDecimal corePrice = (BigDecimal) parmMap.get("corePrice");
		BigDecimal low_corePrice = (BigDecimal) parmMap.get("low_corePrice");
		
		
		
		//如果数据库中最值为0，则默认当前值为最值
		if(top_currMoney.intValue()==0){
			top_currMoney = currMoney;
		}
		if(top_supportPerson==0){
			top_supportPerson = supportPerson;
		}
		if(low_corePrice.intValue()==0){
			low_corePrice = corePrice;
		}
		//如果数据库中的最值小于当前值，则当前值为最值
		if(top_currMoney.compareTo(currMoney)==-1){
			top_currMoney = currMoney;
		}
		if(top_supportPerson.compareTo(supportPerson)==-1){
			top_supportPerson = supportPerson;
		}
		if(corePrice.compareTo(low_corePrice)==-1){
			low_corePrice = corePrice;
		}
		/*
		 * 注意要先乘再除，不然会出现结果全部为0
		 */
		if(top_currMoney.intValue()!=0){
			G_score = (int) Math.floor(currMoney.multiply(new BigDecimal(G_TOP_SCORE)).divide(top_currMoney,0,RoundingMode.HALF_DOWN).doubleValue());
		}
		if(top_supportPerson!=0){
			K_score = (int) Math.floor(supportPerson*K_TOP_SCORE/top_supportPerson);		
		}
		if(corePrice.intValue()!=0){
			corePrice_score = (int) Math.floor(low_corePrice.multiply(new BigDecimal(COREPRICE_SCORE)).divide(corePrice,0,RoundingMode.HALF_DOWN).doubleValue());
		}
		parmMap.put("G_score", G_score);
		parmMap.put("K_score", K_score);
		parmMap.put("corePrice_score", corePrice_score);
		parmMap.put("GK_index", G_score+K_score+corePrice_score);
	}
	public static void main(String[] args) {
		Map<String,Object> parmMap = new HashMap<String, Object>();
		parmMap.put("top_currMoney", new BigDecimal(50000));
		parmMap.put("top_supportPerson", 365);
		parmMap.put("low_corePrice", new BigDecimal(0));
		parmMap.put("support_person",234);//支持			
		parmMap.put("currMoney", new BigDecimal(46538));
		parmMap.put("corePrice", new BigDecimal(999));
		statisticsAnalysis(parmMap);
	}
}
