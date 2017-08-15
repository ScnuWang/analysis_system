package cn.geekview.analysisSystem.remotedata.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import cn.geekview.analysisSystem.controller.AnalysisController;
import cn.geekview.analysisSystem.entity.dao.TdreamServerMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamWebsiteMapper;
import cn.geekview.analysisSystem.entity.model.TdreamServer;
import cn.geekview.analysisSystem.entity.model.TdreamWebsite;
import cn.geekview.analysisSystem.remotedata.entity.dao.TdreamTaskMapper;
import cn.geekview.analysisSystem.remotedata.service.TaskService;
import cn.geekview.analysisSystem.utils.HttpUtil;
import cn.geekview.analysisSystem.utils.MailUtil;

@Service("TaskServiceImpl")
public class TaskServiceImpl implements TaskService {
	
	private static final Logger logger = LogManager.getLogger(AnalysisController.class.getName()); 

	@Autowired
	private TdreamTaskMapper tdreamTaskMapper;

	@Autowired
	private TdreamWebsiteMapper tdreamWebsiteMapper;
	
	@Autowired
	private TdreamServerMapper tdreamServerMapper;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 12:00:00");

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
//	@Scheduled(cron="0 30 13 * * ?")//每天13点30查询抓取状态
	public void checkTaskStatus() {
		/**
		 * 遍历平台列表t_dream_website，获取平台代码
		 */
		SimpleDateFormat mapDateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
		try {
			Date udpate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateFormat.format(new Date()));
//			Date udpate = new DateTime(2017,5,21,12,00,00).toDate();
			List<TdreamWebsite> websites = tdreamWebsiteMapper.selectRankWebsiteList();
			String result = "";
			TdreamServer server;
			HttpResponse response;
			Map map = new HashMap();
			map.put("date", mapDateFormat.format(udpate));
			for (TdreamWebsite tdreamWebsite : websites) {
				logger.info(tdreamWebsite.getName()+"定时分析开始：");
				Integer websiteCode = tdreamWebsite.getWebsiteCode();
				int count = 0;
				count = tdreamTaskMapper.selectCountByUpdateDate(websiteCode, udpate);//12:00:00
				if(count>0){
					server = tdreamServerMapper.selectURLByTaskkey(1043, websiteCode, 1);
					response = HttpUtil.post(server.getServerUrl()+websiteCode, map);					
					result = EntityUtils.toString(response.getEntity());//1
					logger.info(tdreamWebsite.getName()+"定时分析返回结果："+result);
				}
			}
			//整体统计分析
			logger.info("分词分类统计分析开始");
			server = tdreamServerMapper.selectURLByTaskkey(1051, 0, 0);
			response = HttpUtil.post(server.getServerUrl(), map);
			result = EntityUtils.toString(response.getEntity());//1
			logger.info("分词分类统计分析结果："+result);
			MailUtil.sendEmail("分词分类统计分析结果", result);
		} catch (Exception e) {
			logger.info(e.getMessage());
			MailUtil.sendEmail("定时分析出现异常", e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		Date updateDate = new DateTime(2017,5,20,12,00,00).toDate();
		System.out.println(updateDate);
	}
}
