package cn.geekview.analysisSystem.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekview.analysisSystem.entity.dao.TdreamCategoryMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamCompetitorExtraMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamCompetitorMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductCateMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductStatMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductTempMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankGrowthMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankPlatformMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankProjectMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamWebsiteMapper;
import cn.geekview.analysisSystem.entity.model.TdreamCategory;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitorExtra;
import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.entity.model.TdreamProductCate;
import cn.geekview.analysisSystem.entity.model.TdreamProductStat;
import cn.geekview.analysisSystem.entity.model.TdreamProductTemp;
import cn.geekview.analysisSystem.entity.model.TdreamWebsite;
import cn.geekview.analysisSystem.service.IStatisticService;
import cn.geekview.analysisSystem.utils.AnsjSegUtil;
import cn.geekview.analysisSystem.utils.DataAnalysis;

@Service
public class StatisticServiceImpl implements IStatisticService{

	@Autowired
	private TdreamProductTempMapper tdreamProductTempMapper;
	
	@Autowired
	private TdreamCompetitorMapper tdreamCompetitorMapper;
	
	@Autowired
	private TdreamProductCateMapper tdreamProductCateMapper;
	
	@Autowired
	private TdreamCategoryMapper tdreamCategoryMapper;
	
	@Autowired
	private TdreamProductStatMapper tdreamProductStatMapper;
	
	@Autowired
	private TdreamRankGrowthMapper tdreamRankGrowthMapper;
	
	@Autowired
	private TdreamRankPlatformMapper tdreamRankPlatformMapper;
	
	@Autowired
	private TdreamRankProjectMapper tdreamRankProjectMapper;

	@Autowired
	private TdreamProductMapper tdreamProductMapper;
	
	@Autowired
	private TdreamWebsiteMapper tdreamWebsiteMapper;
	
	@Autowired
	private TdreamCompetitorExtraMapper tdreamCompetitorExtraMapper; 
	
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
	
	@Override
	public void doStatisticsData(Date updateDate, Logger logger){
		
		logger.info("开始统计信息");
		logger.info("开始分词和分类");
		String keyword = ""; 
		AnsjSegUtil.initKeyWordData(false);
		List<TdreamProductTemp> newProductList = tdreamProductTempMapper.selectNewProductList();
		for(TdreamProductTemp product :newProductList){
			keyword = AnsjSegUtil.getKeyword(product.getProductName() + " " + product.getProductDesc(),product.getWebsiteType());//网站类型：1：国内；2：国外
			product.setKeyword(keyword);
			TdreamProductCate productCate = updateProduct(product);
				
			//计算极客分
			CalcGeekScore(productCate,product);
			tdreamProductTempMapper.insert(product);
		}
		/**
		 * 1、修改产品缓存表的众筹状态为产品表的众筹状态
		 * 2、如果缓存表中关键字为0，则修改产品表为不是智能硬件
		 * 3、如果产品表为不是智能硬件，则修改缓存表中的关键字为0、标记产品为不是智能硬件
		 * 4、删除产品分类表中产品为不是智能硬件的产品
		 */
		tdreamProductTempMapper.updateProductEnabled(updateDate);
		newProductList = tdreamProductTempMapper.selectNoClassifyList();
		for(TdreamProductTemp product :newProductList){
			TdreamProductCate productCate = updateProduct(product);
			//计算极客分
			CalcGeekScore(productCate,product);
			//根据主键更新t_dream_product_temp表
			tdreamProductTempMapper.updateByPrimaryKey(product);
		}
		tdreamProductTempMapper.updateProductEnabled(updateDate);
		logger.info("分词和分类完成");
		logger.info("删除旧的排名统计信息");
		tdreamRankProjectMapper.deleteByUpdateDate(updateDate);
		tdreamRankGrowthMapper.deleteByUpdateDate(updateDate);
		tdreamProductStatMapper.deleteByUpdateDate(updateDate);
		tdreamRankPlatformMapper.deleteByUpdateDate(updateDate);
		logger.info("更新排名统计信息");
		tdreamProductMapper.insertRankByUpdateDate(updateDate);
		tdreamRankProjectMapper.updateRankingInfo(updateDate);
		tdreamRankGrowthMapper.updateRankingInfo(updateDate);
		tdreamCategoryMapper.updateCategoryInfo(updateDate, 7);
	
		
		/**
		 * 更新二级分类下的最值信息
		 * 
		 * 	遍历t_dream_competitor表
		 * 	插入competitorId,categoryId
		 * 	根据competitor编号通过t_dream_product_cate表出所有的产品，然后通过SQL语句进行排名
		 */
		List<TdreamCompetitor> competitorList = tdreamCompetitorMapper.selectNewCompetitorList();
		for (TdreamCompetitor tdreamCompetitor : competitorList) {
			tdreamCompetitorExtraMapper.insert(tdreamCompetitor);
		}
		
		long start = System.currentTimeMillis();
		/**
		 * 有新产品的时候才和之前的最值进行比较、
		 */
		//将分类的最值信息更新到t_dream_competitor_extra表
		List<TdreamCompetitor> allCompetitor = tdreamCompetitorMapper.selectAllCompetitor();
		for (TdreamCompetitor tdreamCompetitor : allCompetitor) {
			 /* 
			    二级分类排序
			        1、获取二级分类下的所有产品中价格最高的产品的金额以及产品的编号
			                            直接遍历所有的已经分类好的产品按照金额降序排列
			        2、优化：：将查询操作提出来，并进行缓存
			        
			        3、使用每天更新的产品与之前的最值进行比较，就不需查询所有的分类的所有产品
			    */
			tdreamCompetitorExtraMapper.updateRankingInfo(dateFormat.format(updateDate),tdreamCompetitor.getPkId());
		}
		long end = System.currentTimeMillis();
		logger.info("更新competitor最值信息共花费了:"+(end-start)/1000+"秒");
		
		List<TdreamWebsite> selectWebsiteList = tdreamWebsiteMapper.selectRankWebsiteList();
		for(TdreamWebsite web : selectWebsiteList){
			tdreamProductMapper.insertPlatformAnalysis(web.getWebsiteCode(), updateDate, 7);//平台统计正常
		}
		
		logger.info("类别统计开始");//主要是一级分类统计
		List<TdreamCategory> categoryList=tdreamCategoryMapper.selectCategoryList();
		for(TdreamCategory category: categoryList){
			TdreamProductStat entity = tdreamProductStatMapper.selectStatisticsByCategory(category.getPkId());
			if(entity!=null){
				final double level = 4;
				Map<Integer,Integer> moneyMap = new HashMap<Integer, Integer>();
				Map<Integer,Integer> capitaMap = new HashMap<Integer, Integer>();
				Map<Integer,Integer> supportMap = new HashMap<Integer, Integer>();
				Map<Integer,Integer> finishMap = new HashMap<Integer, Integer>();
				double moneyCell = ((entity.getMoneyMax().subtract(entity.getMoneyMin()).doubleValue()))/level,
						capitaCell = ((entity.getCapitaMax().subtract(entity.getCapitaMin()).doubleValue()))/level,
						supportCell = (entity.getSupportMax()-entity.getSupportMin())/level,
						finishCell = (entity.getFinishMax()-entity.getFinishMin())/level;
				List<TdreamProduct> productList = tdreamProductMapper.selectListByCategory(category.getPkId());
				int temp = 1,value = 0;
				for(TdreamProduct product:productList){
					if(product.getCurrMoney().compareTo(entity.getMoneyMin())==0){
						temp = 1;
					}else if(product.getCurrMoney().compareTo(entity.getMoneyMax())==0){
						temp = (int)level;
					}else{
						temp = (int)Math.ceil(product.getCurrMoney().subtract(entity.getMoneyMin()).doubleValue()/moneyCell);
					}
					if(moneyMap.get(temp)==null){
						moneyMap.put(temp, 1);
					}else{
						moneyMap.put(temp,moneyMap.get(temp)+1);
					}
					
					if(product.getAverageMoney().compareTo(entity.getCapitaMin())==0){
						temp = 1;
					}else if(product.getAverageMoney().compareTo(entity.getCapitaMax())==0){
						temp = (int)level;
					}else{
						temp = (int)Math.ceil(product.getAverageMoney().subtract(entity.getCapitaMin()).doubleValue()/capitaCell);
					}
					if(capitaMap.get(temp)==null){
						capitaMap.put(temp, 1);
					}else{
						capitaMap.put(temp,capitaMap.get(temp)+1);
					}
					if(product.getSupportPerson()==entity.getSupportMin()){
						temp = 1;
					}else if(product.getSupportPerson()==entity.getSupportMax()){
						temp = (int)level;
					}else{
						temp = (int)Math.ceil((product.getSupportPerson()-entity.getSupportMin())/supportCell);
					}
					if(supportMap.get(temp)==null){
						supportMap.put(temp, 1);
					}else{
						supportMap.put(temp,supportMap.get(temp)+1);
					}
					if(product.getFinishPer()==entity.getFinishMax()){
						temp = 1;
					}else if(product.getFinishPer()==entity.getFinishMin()){
						temp = (int)level;
					}else{
						temp = (int)Math.ceil((product.getFinishPer()-entity.getFinishMin())/finishCell);
					}
					if(finishMap.get(temp)==null){
						finishMap.put(temp, 1);
					}else{
						finishMap.put(temp,finishMap.get(temp)+1);
					}
				}
				temp = 1;value = 0;
				for(Entry<Integer, Integer> entry:moneyMap.entrySet()){
					if(entry.getValue() > value){
						temp = entry.getKey();
						value = entry.getValue();
					}
				};
				entity.setMoneyMod(new BigDecimal((2*entity.getMoneyMin().doubleValue()+ moneyCell*(temp*2-1))/2));
				temp = 1;value = 0;
				for(Entry<Integer, Integer> entry:capitaMap.entrySet()){
					if(entry.getValue() > value){
						temp = entry.getKey();
						value = entry.getValue();
					}
				};
				entity.setCapitaMod(new BigDecimal((2*entity.getCapitaMin().doubleValue()+ capitaCell*(temp*2-1))/2));
				temp = 1;value = 0;
				for(Entry<Integer, Integer> entry:supportMap.entrySet()){
					if(entry.getValue() > value){
						temp = entry.getKey();
						value = entry.getValue();
					}
				};
				entity.setSupportMod((int)((2*entity.getSupportMin()+ supportCell*(temp*2-1))/2));
				temp = 1;value = 0;
				for(Entry<Integer, Integer> entry:finishMap.entrySet()){
					if(entry.getValue() > value){
						temp = entry.getKey();
						value = entry.getValue();
					}
				};
				entity.setFinishMod((int)((2*entity.getFinishMin()+ finishCell*(temp*2-1))/2));
				entity.setCategoryId(category.getPkId());
				entity.setUpdateDate(updateDate);
				tdreamProductStatMapper.insert(entity);
			}
		}
		/*logger.writeNewLine("请求生成页面");
		final Map<String,Object> parmMap = new HashMap<String,Object>();
		parmMap.put("time",sdf.format(updateDate));
		List<TDreamServer> serverList = tDreamServerMapper.selectListByTaskkey(CodeGrap.INDEX_PAGE,CodeGrap.INDEX_KEY,CodeGrap.CODE_STATUS);
		for(TDreamServer server : serverList){
			CommonUtils.httpRequestWithParam(server.getServerUrl(),parmMap,"utf-8");
		}
		CommonAPI.statisticsFlag = false;
		
		if(task!=null){
			task.setTaskCode(CodeGrap.CODE_DONE);
			tDreamTaskMapper.updateByPrimaryKey(task);
		}*/
		logger.info("统计结束！");
	}
	
	private TdreamProductCate updateProduct(TdreamProductTemp product){
		TdreamProductCate productCate = null ;
		//0:准备就绪，1:需要手动 ，2:无效项目 ，3:成功分类 
		product.setCompStatus(1);
		if("0".equals(product.getKeyword())){
			product.setCompStatus(2);
		}else if(!StringUtils.isEmpty(product.getKeyword())){
			productCate = tdreamProductCateMapper.selectByProductId(product.getPkId());
			if(productCate==null){
				TdreamCompetitor competitor = tdreamCompetitorMapper.selectByCompKwords(product.getKeyword());
				if(competitor!=null){
					productCate = new TdreamProductCate();
					productCate.setCategoryId(competitor.getCategoryId());
					productCate.setCompId(competitor.getPkId());
					productCate.setProductId(product.getPkId());
					productCate.setWebsite(product.getWebsite());
					tdreamProductCateMapper.insertFromProgram(productCate);
					product.setCompStatus(3);
				}
			}
		}
		return productCate;
	}
	
	/**
	 * 计算极客指数
	 * 	避免每次都要去查询所有的产品，以及方便展示更多的分类信息
	 * 1、新建t_dream_competitor_extra表中新增字段存放二级分类中产品的最高值最低值(在每一项后面存放t_dream_product的编号)
	 * 2、新建t_dream_category_extra表中新增字段存放一级分类中产品的最高值最低值包括金额(在每一项后面存放t_dream_product的编号)
	 * 
	 * 怎样获取当前产品的分类编号？
	 * 1、判断是否已经分类：t_dream_product_cate表是否为null
	 * 		已经分类有效项目：t_dream_product_cate表获取competitorId
	 * 		需要手动分类：先设置为0，手动分类之后在进行指数计算排名
	 * 
	 * 怎获取每个分类的排名:t_dream_competitor_extra表的数据
	 */
	private void CalcGeekScore(TdreamProductCate productCate,TdreamProductTemp product){
		
		if(productCate!=null){
			TdreamCompetitorExtra competitorExtra = tdreamCompetitorExtraMapper.selectByCompetitorId(productCate.getCompId());
			if(competitorExtra==null){
				//手动分类之前，预设置极客分为0
				product.setTargetMoneyOrg(new BigDecimal(0));//平台分析表中的金额得分存放极分
				product.setCurrMoneyOrg(new BigDecimal(0));//平台分析表中的流量得分存放客分
				product.setItemCorePriceOrg(new BigDecimal(0));//核心价格得分
				product.setGvIndex(0);
				return;
			}
			//活跃度：支持+分享+关注+喜欢---》并不是每个平台都有这些选项
			int support = product.getSupportPerson() == null ? 0 : product.getSupportPerson();
			int share = product.getShareCount() == null ? 0 : product.getShareCount();
			int focus = product.getFocusCount() == null ? 0 : product.getFocusCount();
			int like = product.getLikeCount() == null ? 0 : product.getLikeCount();
			BigDecimal currmoney = product.getCurrMoney() == null ? new BigDecimal(0) : product.getCurrMoney();
			BigDecimal coreprice = product.getItemCorePrice() == null ? new BigDecimal(0) : product.getItemCorePrice();
			
			BigDecimal top_currMoney = competitorExtra.getTopProductMoney() == null ? new BigDecimal(0) : competitorExtra.getTopProductMoney();
			int top_supportPerson = competitorExtra.getTopProductPeople() == null ? 0 : competitorExtra.getTopProductPeople();
			BigDecimal low_corePrice = competitorExtra.getLowCoreprice() == null ? new BigDecimal(0) : competitorExtra.getLowCoreprice();
			
			
			Map<String,Object> parmMap = new HashMap<String, Object>();
			parmMap.put("top_currMoney", top_currMoney);
			parmMap.put("top_supportPerson", top_supportPerson);
			parmMap.put("low_corePrice", low_corePrice);
			parmMap.put("support_person",support+share+focus+like);//支持			
			parmMap.put("currMoney", currmoney);
			parmMap.put("corePrice", coreprice);
			DataAnalysis.statisticsAnalysis(parmMap);
			product.setTargetMoneyOrg(new BigDecimal(parmMap.get("G_score").toString()));//平台分析表中的金额得分存放极分
			product.setCurrMoneyOrg(new BigDecimal(parmMap.get("K_score").toString()));//平台分析表中的流量得分存放客分
			product.setItemCorePriceOrg(new BigDecimal(parmMap.get("corePrice_score").toString()));//核心价格得分
			product.setGvIndex((Integer)parmMap.get("GK_index"));
		}else{
			//手动分类之前，预设置极客分为0
			product.setTargetMoneyOrg(new BigDecimal(0));//平台分析表中的金额得分存放极分
			product.setCurrMoneyOrg(new BigDecimal(0));//平台分析表中的流量得分存放客分
			product.setItemCorePriceOrg(new BigDecimal(0));//核心价格得分
			product.setGvIndex(0);
		}
	}
}
