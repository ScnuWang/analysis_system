package cn.geekview.analysisSystem.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekview.analysisSystem.entity.dao.TdreamTbAnalysisMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamTbProjectMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankGrowthMapper;
import cn.geekview.analysisSystem.entity.model.TdreamTbAnalysis;
import cn.geekview.analysisSystem.entity.model.TdreamTbItem;
import cn.geekview.analysisSystem.entity.model.TdreamTbProject;
import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.entity.model.TdreamRankGrowth;
import cn.geekview.analysisSystem.remotedata.entity.dao.TbItemMapper;
import cn.geekview.analysisSystem.remotedata.entity.dao.TbProjectMapper;
import cn.geekview.analysisSystem.service.IAnalysisService;
import cn.geekview.analysisSystem.utils.CommonAPI;
import cn.geekview.analysisSystem.utils.CrateHtmlUtil;
@Service("TbAnalysisServiceImpl")
public class TbAnalysisServiceImpl implements IAnalysisService {
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	@Autowired
	private TbProjectMapper tbProjectMapper;
	
	@Autowired
	private TbItemMapper tbItemMapper;
	
	@Autowired
	private TdreamTbProjectMapper tdreamTbProjectMapper;
	
	@Autowired
	private TdreamProductMapper tdreamProductMapper;
	
	@Autowired
	private TdreamRankGrowthMapper tdreamRankGrowthMapper;
	
	@Autowired
	private TdreamTbAnalysisMapper tdreamTbAnalysisMapper;
	
	/**
	 * 如果在分析的过程中，出现异常，需要重新分析，会不会对已经分析的数据造成影响？
	 * 		事务回滚
	 * 		解决办法：删除当日的平台分析表（t_dream_tb_analysis）和增速表（t_dream_rank_growth）的数据
	 * 	
	 */
	@SuppressWarnings("unused")
	@Override
	public int doWebsiteAnalysis(Date updateDate,Logger logger) {
		logger.info("淘宝平台开始分析");
		//1、平台需要被分析的的产品
		long start = System.currentTimeMillis();
		List<TdreamTbProject> listByUpdateDate = tbProjectMapper.selectListByUpdateDate(updateDate);
		long end = System.currentTimeMillis();
		logger.info("查询平台需要被分析的的产品花费"+(end-start)/1000+"秒");
		Map<String,Object> parmMap = new HashMap<String, Object>();
		Integer productId = null;
		for (TdreamTbProject tdreamTbProject : listByUpdateDate) {
			String originalId = tdreamTbProject.getOriginalId();
			//2、据平台编号和原始id查询t_dream_product表中
			TdreamProduct tdreamProduct = tdreamProductMapper.selectByOriginalId(1, originalId);
			if(tdreamProduct==null){
				tdreamTbProject.setBeginDate(new Date());
				//不存在，插入的产品到t_dream_product表
				tdreamTbProjectMapper.insertProduct(tdreamTbProject);
				productId = tdreamProductMapper.selectProductId(1,originalId);//改
			}else{
				productId = tdreamProduct.getPkId();
				tdreamTbProject.setBeginDate(tdreamProduct.getProductBegin());
				//存在，更新的产品到t_dream_product表
				tdreamTbProjectMapper.updateProduct(tdreamTbProject);
			}
			/**
			 * 处理当天重新分析，以及多次分析的情况
			 * 	根据更新时间删除当日的平台分析表（t_dream_Tb_analysis）和增速表（t_dream_rank_growth）的数据
			 */
			tdreamTbAnalysisMapper.deleteByProductId(productId,updateDate);
			tdreamRankGrowthMapper.deleteByProductId(productId,updateDate);
			
			/**
			 * 这个有可能被分析的产品还没有分类，导致查回来的数据是空的
			 * 先处理已经分类的产品，再进行分词分类后
			 */
			TdreamTbAnalysis entity = creatPrepareEntity(tdreamTbProject);
			entity.setProductId(productId);
			
			tdreamTbAnalysisMapper.insert(entity);
			tdreamTbAnalysisMapper.updateProduct(entity);
			
			if(tdreamProduct!=null&&tdreamProduct.getProductEnabled()==1){
				TdreamTbAnalysis prevEntity = tdreamTbAnalysisMapper.
						selectPrevAnalysis(productId,tdreamTbProject.getUpdateDate());//传入产品抓取回来传入数据库的时间：否则t_dream_rank_growth表的上一次金额为空
				insertRankGrowth(tdreamTbProject,entity,prevEntity,logger);
			}
		}
		long end2 = System.currentTimeMillis();
		logger.info("淘宝平台分析花费"+(end2-start)/1000+"秒");
		logger.info("淘宝平台分析结束");
		return 1;
	}
	
	
	
	private void insertRankGrowth(TdreamTbProject project, TdreamTbAnalysis entity, TdreamTbAnalysis prevEntity, Logger logger) {
		int days = 1;
		logger.info("-- 生成"+entity.getProductId()+"的增速榜数据！");
		TdreamRankGrowth  growth = new TdreamRankGrowth();
		growth.setProductId(entity.getProductId());
		growth.setUpdateDate(entity.getUpdateDate());
		if(prevEntity == null){
			growth.setCurrMoneyLast(new BigDecimal(0));
			growth.setCurrMoneyLastOrg(new BigDecimal(0));
			growth.setSupportLast(0);
		}else{
			growth.setCurrMoneyLast(prevEntity.getCurrMoney());
			growth.setCurrMoneyLastOrg(prevEntity.getCurrMoney());
			growth.setSupportLast(prevEntity.getSupportPerson());
			days = (int) Math.ceil(((entity.getUpdateDate().getTime()-prevEntity.getUpdateDate().getTime())/CommonAPI.MSEC_DAY));
			days = days<1?1:days;
		}
		growth.setCurrMoneyNow(entity.getCurrMoney());
		growth.setCurrMoneyNowOrg(entity.getCurrMoney());
		growth.setSupportNow(entity.getSupportPerson());
		
		growth.setGrowthMoney(new BigDecimal(growth.getCurrMoneyNow().subtract(growth.getCurrMoneyLast()).doubleValue()/days));
		growth.setGrowthMoneyOrg(new BigDecimal(growth.getCurrMoneyNowOrg().subtract(growth.getCurrMoneyLastOrg()).doubleValue()/days));
		growth.setGrowthSupport((growth.getSupportNow()-growth.getSupportLast())/days);
		
		growth.setTargetMoney(entity.getTargetMoney());
		int costDays = 0;long startMsec = 0,endMsec = 0;
		if(project.getEndDate()!=null){
			endMsec = project.getEndDate().getTime();
		}else{
			endMsec = System.currentTimeMillis();
		}
		if(project.getBeginDate() != null){
			startMsec = project.getBeginDate().getTime();
		}else{
			startMsec = endMsec;
		}
		costDays = (int) ((endMsec - startMsec)/CommonAPI.MSEC_DAY);
		if(costDays < 1){
			growth.setTargetAverage(entity.getTargetMoney());
		}else{
			growth.setTargetAverage(new BigDecimal(entity.getTargetMoney().doubleValue()/costDays));
		}
		if(entity.getTargetMoney() == null||growth.getTargetAverage() == null
				||entity.getTargetMoney().doubleValue() == 0||growth.getTargetAverage().doubleValue() == 0){
			growth.setGrowthSpeed(0);
		}else{
			int speed = (int) (growth.getGrowthMoney().doubleValue()/growth.getTargetAverage().doubleValue())*100;
			growth.setGrowthSpeed(speed);
		}
		tdreamRankGrowthMapper.insert(growth);
		logger.info("-- 增速榜数据生成完毕！");
	}
	
	
	private  TdreamTbAnalysis creatPrepareEntity(TdreamTbProject project){
		TdreamTbAnalysis entity = new TdreamTbAnalysis();
		entity.setFinishPer(project.getFinishPer());
		entity.setUpdateDate(project.getUpdateDate());
		entity.setCurrencyId(1);
		if(project.getTargetMoney() == null){
			entity.setTargetMoney(new BigDecimal(0));
		} else{
			entity.setTargetMoney(project.getTargetMoney());
		}
		
		if(project.getCurrMoney() == null){
			entity.setCurrMoney(new BigDecimal(0));
		} else{
			entity.setCurrMoney(project.getCurrMoney());
		}
		
		if(project.getFocusCount() == null){
			entity.setFocusCount(0);
		} else{
			entity.setFocusCount(project.getFocusCount());
		}
		
		if(project.getSupportPerson() == null||project.getSupportPerson() == 0){
			entity.setSupportPerson(0);
			entity.setAverageMoney(new BigDecimal(0));
		} else{
			entity.setSupportPerson(project.getSupportPerson());
			entity.setAverageMoney(new BigDecimal(entity.getCurrMoney().doubleValue()/entity.getSupportPerson()));
		}
		
		if(entity.getAverageMoney() == null){
			entity.setAverageMoney(new BigDecimal(0));
		}
		
		/**
		 * 1、计算目标价格（众筹发起方希望众筹产品售卖的价格：去除一元档、期望支持人数最多且价格最低）
		 * 2、无限额的去掉一元档，要价格最低的
		 */
		
		int coreSupport = 0,coreTotal = 0;
		double corePrice = 0;
		Map<BigDecimal,Integer[]> itemMap = new HashMap<BigDecimal,Integer[]>();
		if(project.getItemList()!=null&&project.getItemList().size()>0){
			List<TdreamTbItem> limitList = new ArrayList<TdreamTbItem>();//支持人数无限额的支持档
			List<TdreamTbItem> unLimitList = new ArrayList<TdreamTbItem>();//支持人数有限额的支持档
			for(TdreamTbItem item : project.getItemList()){
				if(item.getItemPrice().doubleValue()< 5 ) continue;
				if(item.getItemTotal() == 0)
					unLimitList.add(item);
				else
					limitList.add(item);
			};
			if(limitList.size()>0){
				for(TdreamTbItem item : limitList){
					if(item.getItemPrice().intValue()==1){//如果支持档的价格是1，跳过执行下一次循环
						continue;
					}
					if(itemMap.get(item.getItemPrice())!=null){
						Integer[] numArray = itemMap.get(item.getItemPrice());
						numArray[0] = numArray[0]+item.getItemSupport();
						numArray[1] = numArray[1]+item.getItemTotal();
						itemMap.put(item.getItemPrice(), numArray);
					}else{
						Integer[] numArray = {item.getItemSupport(),item.getItemTotal()};
						itemMap.put(item.getItemPrice(), numArray);
					}
				};
				for(Map.Entry<BigDecimal,Integer[]> entry : itemMap.entrySet()){
					Integer[] values = entry.getValue();
					if(values[1] > coreTotal){
						corePrice = entry.getKey().doubleValue();
						coreSupport = values[0];
						coreTotal = values[1];
					}else if(values[1] == coreTotal){
						if(corePrice>entry.getKey().doubleValue()){
							corePrice = entry.getKey().doubleValue();
							coreSupport = values[0];
							coreTotal = values[1];
						}
					}
				}
			}else{
				if(unLimitList.size()>0){
					for(TdreamTbItem item : unLimitList){
						if(item.getItemPrice().intValue()==1){//如果支持档的价格是1，跳过执行下一次循环
							continue;
						}
						if(itemMap.get(item.getItemPrice())!=null){
							Integer[] numArray = itemMap.get(item.getItemPrice());
							numArray[0] = numArray[0]+item.getItemSupport();
							numArray[1] = numArray[1]+1;
							itemMap.put(item.getItemPrice(), numArray);
						}else{
							Integer[] numArray = {item.getItemSupport(),1};
							itemMap.put(item.getItemPrice(), numArray);
						}
					};
					for(Map.Entry<BigDecimal,Integer[]> entry : itemMap.entrySet()){
						Integer[] values = entry.getValue();
						if(values[1] > coreTotal){
							corePrice = entry.getKey().doubleValue();
							coreSupport = values[0];
							coreTotal = values[1];
						}else if(values[1] == coreTotal){
							if(corePrice>entry.getKey().doubleValue()){
								corePrice = entry.getKey().doubleValue();
								coreSupport = values[0];
								coreTotal = values[1];
							}
						}
					}
					coreTotal = 0;
				}
			}
		}
		entity.setItemCorePrice(new BigDecimal(corePrice));
		entity.setItemCoreSupport(coreSupport);
		entity.setItemCoreTotal(coreTotal);
		return entity;
	}
	
	/**
	 * 展示一个产品的曲线
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public int doProductAnalysis(Integer originId) {
		List<TdreamTbProject> projectList = null; //产品列表
		List<TdreamTbItem> itemsList = null;	  //产品项列表
		List supportPersonList = new ArrayList(); //产品总的支持人数列表
		List finishPercentList = new ArrayList(); //产品完成百分比列表
		List currentMoneyList = new ArrayList();  //产品当前众筹金额列表
		List updateDateList = new ArrayList();    //产品更新时间列表
		List itemPrice = new ArrayList(); 	  	  //产品项价格列表
		List itemTarget = new ArrayList(); 	      //产品项期望支持人列表
		List itemActual = new ArrayList(); 	      //产品项实际支持人数列表

		
		//获取指定的产品列表
		projectList = tbProjectMapper.getProductByOriginIdWithDaySpace(originId);
		
		
		//项目的名称
		String productName = null;
		if(projectList==null||projectList.size()==0){
			//没有找到产品的处理
			int productResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "product.ftl", CommonAPI.CLASSPATH + "/html/", "product.html", null);
			return productResult;
		}
		
		int itemnum = 0;//产品项的数量
		Integer temptarget = 0; 
		BigDecimal tempprice = new BigDecimal(0); 
		Integer tempactual = 0;
		for (TdreamTbProject tdreamTbProject : projectList) {
			//获取要展示产品的数据
			productName = tdreamTbProject.getProjectName();
			supportPersonList.add(String.valueOf(tdreamTbProject.getSupportPerson()));
			finishPercentList.add(String.valueOf(tdreamTbProject.getFinishPer()));
			currentMoneyList.add(String.valueOf(tdreamTbProject.getCurrMoney()));
			updateDateList.add(dateFormat.format(tdreamTbProject.getUpdateDate()));
			//获取这个产品下的项目
			itemsList = tbItemMapper.getItemsByProjectId(tdreamTbProject.getPkId());
			if(itemsList==null||itemsList.size()==0){
				continue;
			}
			itemnum = itemsList.size();
			for (TdreamTbItem tdreamTbIterm : itemsList) {
				
				BigDecimal 	price = (BigDecimal) tdreamTbIterm.getItemPrice();
				Integer target = (Integer) tdreamTbIterm.getItemTotal();
				Integer actual = (Integer) tdreamTbIterm.getItemSupport();
				
				itemPrice.add(price);	    //添加产品项金额
				itemTarget.add(target);	    //添加产品项目标数量
				itemActual.add(actual);     //添加产品项实际支持数量
				/*
				 * 获取目标价格
				 * 期望支持人数最多的且价格最低的产品项，排除一元或者无限额的产品项
				 * 比较期望人数：
				 * 
				 */
				if(!(price.intValue()==1||target==0)){
					if(temptarget < target){
						temptarget = target;
						tempprice = price;
						tempactual = actual;
					}else if (temptarget == target){
						if(tempprice.compareTo(price)==1){
							temptarget = target;
							tempprice = price;
							tempactual = actual;
						}
					}
				}
			}
		}
		
		Map<String,List> itemMap = new HashMap<String, List>();			//封装单个产品项数据
		Map<String,List> itemActualMap = new HashMap<String, List>();	//封装单个产品项的实际支持人数
		Map<String,List> itemPriceMap = new HashMap<String, List>();	//封装单个产品项的价格
		Map<String,List> itemTargetMap = new HashMap<String, List>();	//封装单个产品项的期望支持人数

		//新建产品项个数个List
		for (int i = 0; i < itemnum; i++) {
			List item = new ArrayList();		
			List itemActualList = new ArrayList();		
			List itemPriceList = new ArrayList();
			List itemTargetList = new ArrayList();
			itemMap.put("item"+i, item);
			itemActualMap.put("itemActualList"+i, itemActualList);
			itemPriceMap.put("itemPriceList"+i, itemPriceList);
			itemTargetMap.put("itemTargetList"+i, itemTargetList);
		}
		//将每个项的每天的数据取出来放在一起
		int datasize = itemPrice.size()/itemnum;
		for (int j = 0; j < datasize; j++) {
			int index = 0;
			index = j*itemnum;
			for (int i = 0; i < itemnum; i++) {
				List itemActualList = itemActualMap.get("itemActualList"+i);
				List itemPriceList = itemPriceMap.get("itemPriceList"+i);
				List itemTargetList = itemTargetMap.get("itemTargetList"+i);
				itemActualList.add(itemActual.get(index));
				itemPriceList.add(itemPrice.get(index));
				itemTargetList.add(itemTarget.get(index));
				index ++;
			}
		}
		
		//封装一个产品的所有产品项
		List itemList = new ArrayList();
		for (int i = 0; i < itemnum; i++) {
			List item = itemMap.get("item"+i);
			item.add(itemActualMap.get("itemActualList"+i).toArray());
			item.add(itemPriceMap.get("itemPriceList"+i).toArray());
			item.add(itemTargetMap.get("itemTargetList"+i).toArray());
			itemList.add(item);//itemList为null或者size为0,前端不显示产品项的数据 
		}
		
		
		Map mapParm = new HashMap();
		//产品相关的参数
		mapParm.put("productName",productName);
		mapParm.put("updateDate",updateDateList);
		mapParm.put("supportPerson",supportPersonList);
		mapParm.put("finishPercent",finishPercentList);
		mapParm.put("currentMoney",currentMoneyList);
		//产品项相关的参数
		mapParm.put("itemList",itemList);
		//目标价格产品项
//		mapParm.put("itemTargetPeople",temptarget.toString());
		mapParm.put("itemTargetPrice",String.valueOf(tempprice));
//		mapParm.put("itemTargetFinish",tempactual.toString());
		
		int productResult = CrateHtmlUtil.createHTML(CommonAPI.CLASSPATH + "/ftl/", "product.ftl", CommonAPI.CLASSPATH + "/html/", "product.html", mapParm);
		return productResult;
	}
}
