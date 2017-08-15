package cn.geekview.analysisSystem.service;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.Logger;



public interface IAnalysisService {
	
	/**
	 * 单个产品分析
	 * @param originId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int doProductAnalysis(Integer originId);
	/**
	 * 单个平台分析
	 * @return
	 */
	int doWebsiteAnalysis(Date updateDate,Logger logger);
	
	
}
