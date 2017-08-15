package cn.geekview.analysisSystem.service;

import java.util.Date;

import org.apache.logging.log4j.Logger;

public interface IStatisticService {
	/**生成统计信息**/
	void doStatisticsData(Date updateDate, Logger logger);
}
