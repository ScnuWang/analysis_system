package cn.geekview.analysisSystem.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.geekview.analysisSystem.entity.dao.TdreamArticleMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamCategoryMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamCompetitorMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamCurrencyMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductCateMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamProductStatMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankGrowthMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamRankPlatformMapper;
import cn.geekview.analysisSystem.entity.dao.TdreamWebsiteMapper;
import cn.geekview.analysisSystem.entity.model.TdreamArticle;
import cn.geekview.analysisSystem.entity.model.TdreamCategory;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;
import cn.geekview.analysisSystem.entity.model.TdreamCurrency;
import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.entity.model.TdreamProductCate;
import cn.geekview.analysisSystem.entity.model.TdreamProductStat;
import cn.geekview.analysisSystem.entity.model.TdreamRankGrowth;
import cn.geekview.analysisSystem.entity.model.TdreamWebsite;
import cn.geekview.analysisSystem.service.ICommonService;
import cn.geekview.analysisSystem.utils.CommonAPI;
import cn.geekview.analysisSystem.utils.HttpUtil;
/**
 * 公共的业务类
 * @author Jason
 *
 */

@Service("CommonServiceImpl")
public class CommonServiceImpl implements ICommonService {
	@Autowired
	private TdreamWebsiteMapper tdreamWebsiteMapper;
	
	@Autowired
	private TdreamCategoryMapper tdreamCategoryMapper;
	
	@Autowired
	private TdreamProductMapper tdreamProductMapper;
	
	@Autowired
	private TdreamCompetitorMapper tdreamCompetitorMapper;
	
	@Autowired
	private TdreamProductCateMapper tdreamProductCateMapper;
	
	@Autowired
	private TdreamCurrencyMapper tdreamCurrencyMapper;
	
	@Autowired
	private TdreamRankPlatformMapper tdreamRankPlatformMapper;
	
	@Autowired 
	private TdreamRankGrowthMapper tdreamRankGrowthMapper;
	
	@Autowired
	private TdreamArticleMapper tdreamArticleMapper;
	
	@Autowired 
	private TdreamProductStatMapper tdreamProductStatMapper;
	
	@Override
	public TdreamWebsite getWebsiteByWebsiteCode(Integer websiteCode) {
		return tdreamWebsiteMapper.getWebsiteByWebsiteCode(websiteCode);
	}
	
	@Override
	public List<TdreamProduct> getProductListByWebsiteCode(Integer websiteCode) {
		return tdreamProductMapper.getProductListByWebsiteCode(websiteCode);
	}
	
	@Override
	public TdreamProduct getProductById(Integer pkId) {
		return tdreamProductMapper.getProductById(pkId);
	}

	@Override
	public List<TdreamCategory> getCategoryList() {
		return tdreamCategoryMapper.getCategoryList();
	}

	@Override
	public List<TdreamCompetitor> getCompetitorByCategoryId(Integer categoryId) {
		return tdreamCompetitorMapper.getCompetitorByCategoryId(categoryId);
	}
	
	@Override
	public List<TdreamProduct> getProductListByCompetitorId(Integer competitorId) {
		return tdreamProductCateMapper.getProductListByCompetitorId(competitorId);
	}
	/**
	 * 类别关键字模糊查询（手机，笔记本）
	 */
	@Override
	public List<TdreamProduct> getProductListByKeyword(String keyword) {//有可能一个关键词对应多个competitor
		//封装所有的产品
		List<TdreamProduct> tdreamProducts = new ArrayList<TdreamProduct>();
		List<TdreamCompetitor> competitorlist = tdreamCompetitorMapper.getCompetitorListByKeyword(keyword);
		for (TdreamCompetitor tdreamCompetitor : competitorlist) {
			Integer pkId = tdreamCompetitor.getPkId();
			if(pkId!=null){
				List<TdreamProduct> tempList = getProductListByCompetitorId(pkId);
				tdreamProducts.addAll(tempList);
			}
		}
		return tdreamProducts;
	}
	
	/**
	 * 初始化币种兑换表
	 */
	public void initCurrency(){
		List<TdreamCurrency> currencyList = tdreamCurrencyMapper.selectCurrencyList();
		for(TdreamCurrency currency:currencyList){
			CommonAPI.CURRENCY_MAP.put(currency.getCurrencyNick(), currency);
		}
	}
	
	/**
	 * 查询总的项目数
	 */
	public Integer selectProductCount(Date updateDate){
		return tdreamRankPlatformMapper.selectProductCount(updateDate);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map getProductChange(Integer productId) {
		List<Object[]> moneyArray = new ArrayList<Object[]>();
		List<Object[]> moneyGrowthArray = new ArrayList<Object[]>();
		List<Object[]> supportArray = new ArrayList<Object[]>();
		List<Object[]> supportGrowthArray = new ArrayList<Object[]>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");
		List<TdreamRankGrowth> growthList = tdreamRankGrowthMapper.selectListByProductId(productId);
		if(growthList!=null&&growthList.size()>0){
			for(TdreamRankGrowth obj : growthList){
				Object[] temp1= {dateFormat.format(obj.getUpdateDate()),obj.getCurrMoneyNow()};
				Object[] temp2= {dateFormat.format(obj.getUpdateDate()),obj.getSupportNow()};
				Object[] temp3= {dateFormat.format(obj.getUpdateDate()),obj.getGrowthMoney()};
				Object[] temp4= {dateFormat.format(obj.getUpdateDate()),obj.getGrowthSupport()};
				moneyArray.add(temp1);
				supportArray.add(temp2);
				moneyGrowthArray.add(temp3);
				supportGrowthArray.add(temp4);
			}
		}
		Map resultMap = new HashMap();
		resultMap.put("moneychange", moneyArray);
		resultMap.put("supportchange", supportArray);
		resultMap.put("moneygrowthchange", moneyGrowthArray);
		resultMap.put("supportgrowthchange", supportGrowthArray);
		return resultMap;
	}

	@Override
	public List<TdreamProduct> selectHotProduct(Date updateDate, Integer day) {
		return tdreamProductMapper.selectHotProduct(updateDate, day);
	}

	@Override
	public TdreamArticle getActicle(Integer id) {
		return tdreamArticleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TdreamArticle> getActicleTilttle() {
		return tdreamArticleMapper.getActicleTilttle();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map getCompetitor(Integer productId) {
		TdreamProductCate productCate = tdreamProductCateMapper.selectByProductId(productId);
		List<TdreamProduct> list = null;
		TdreamProductStat tdreamProductStat = null;
		if(productCate!=null){
			DateTime date = new DateTime();
			list = tdreamProductCateMapper.getProductListByCompetitorIdLimit(productCate.getCompId(),10);//取前十位的竞争者
			tdreamProductStat = tdreamProductStatMapper.selectByCategoryId(new DateTime(date.getYear(),date.getMonthOfYear(),date.getDayOfMonth(),12,0,0).plusDays(-1).toDate(),productCate.getCategoryId()); 
		}
		Map mapParm  = new HashMap();
		HttpUtil.addHttpProtocol(list);
		mapParm.put("products", list);
		mapParm.put("catetorytstat", tdreamProductStat);
		return mapParm;
	}
}
