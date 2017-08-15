package cn.geekview.analysisSystem.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.geekview.analysisSystem.entity.model.TdreamWebsite;
import cn.geekview.analysisSystem.remotedata.service.TaskService;
import cn.geekview.analysisSystem.service.IAnalysisService;
import cn.geekview.analysisSystem.service.ICommonService;
import cn.geekview.analysisSystem.service.IStatisticService;
import cn.geekview.analysisSystem.utils.SpringContextsUtil;
/**
 * 分析控制器
 * @author Jason
 *
 */
@Controller
@RequestMapping("/analysis")
public class AnalysisController {
	
	private static final Logger logger = LogManager.getLogger(AnalysisController.class.getName());

	@Autowired
	private ICommonService commonService;
	
	@Autowired
	private IStatisticService statisticService;
	
	@Autowired
	private TaskService taskService;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	
	
	/**
	 * 单个产品的展示
	 * @param originId 产品的原始Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/product/{originId}/{websiteCode}")
	public String doProductAnalysis(@PathVariable("originId") Integer originId, @PathVariable("websiteCode") Integer websiteCode){
		int result = 0;
		//获取分析实例化对象
		TdreamWebsite website = commonService.getWebsiteByWebsiteCode(websiteCode);
		IAnalysisService analysisServiceImpl = (IAnalysisService) SpringContextsUtil.getBean(website.getClassAnalysee());
		//进行分析
		result = analysisServiceImpl.doProductAnalysis(originId);
		if( result == 1 ){
			return "product";
		}else{
			return "error";
		}
	}
	
	@RequestMapping("/website/{websiteCode}")
	@ResponseBody
	public int doWebsiteAnalysis(@PathVariable("websiteCode")Integer websiteCode, HttpServletRequest request){
		int result = 0;
		try {
			//接收参数
			String date = request.getParameter("date");
			//获取分析实例化对象
			TdreamWebsite website = commonService.getWebsiteByWebsiteCode(websiteCode);
			IAnalysisService analysisServiceImpl = (IAnalysisService) SpringContextsUtil.getBean(website.getClassAnalysee());
			if(!StringUtils.isEmpty(date)){	
				result = analysisServiceImpl.doWebsiteAnalysis(dateFormat.parse(date),logger);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		return result;
	}
	
	@RequestMapping("/statistics")
	@ResponseBody
	public String doStatistics(HttpServletRequest request) throws ParseException {
		String result = "0";
		try {
			String date = request.getParameter("date");
			if(!StringUtils.isEmpty(date)){
				statisticService.doStatisticsData(dateFormat.parse(date), logger);
				result = "1";
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * 手动调用定时分析方法
	 */
	@RequestMapping("/hand")
	@ResponseBody
	public void handTimer(){
		taskService.checkTaskStatus();
	}
	
}
