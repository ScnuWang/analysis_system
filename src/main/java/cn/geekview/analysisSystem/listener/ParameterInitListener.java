package cn.geekview.analysisSystem.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.geekview.analysisSystem.service.ICommonService;
import cn.geekview.analysisSystem.utils.AnsjSegUtil;
import cn.geekview.analysisSystem.utils.CommonAPI;

public class ParameterInitListener implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private ICommonService commonService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		//因为机制问题，这个类会被调用两次，只有parent不存在的时候，才是spring准备好了，再进行操作就不会执行两次
		if (event.getApplicationContext().getParent() == null){
			initParameter();
			initAnsjSeg();
		}
	}
	/**
	 * 初始化分词器
	 */
	private void initAnsjSeg(){
		
		try {
			System.out.println("初始化分词器...");
			if(AnsjSegUtil.initKeyWordData(true)){
				System.out.println("初始化分词器成功！");
			}else{
				System.out.println("初始化分词器失败！");
			}
		} catch (Exception e) {
			System.out.println("初始化参数失败！");
			e.printStackTrace();
		}
	}
	/**
	 * 初始化参数
	 */
	private void initParameter(){
		
		try {
			System.out.println("初始化参数...");
			String classPath  = this.getClass().getResource("/").getPath();
			//初始化classpath
			CommonAPI.CLASSPATH = classPath.substring(0,classPath.indexOf("classes"));
			//初始化汇率表
			commonService.initCurrency();
			System.out.println("初始化参数成功！");
		} catch (Exception e) {
			System.out.println("初始化参数失败！");
			e.printStackTrace();
		}
	}

}
