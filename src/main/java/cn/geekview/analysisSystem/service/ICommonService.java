package cn.geekview.analysisSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.geekview.analysisSystem.entity.model.TdreamArticle;
import cn.geekview.analysisSystem.entity.model.TdreamCategory;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;
import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.entity.model.TdreamWebsite;

public interface ICommonService {
	
	TdreamWebsite getWebsiteByWebsiteCode(Integer websiteCode);
	
	List<TdreamProduct> getProductListByWebsiteCode(Integer websiteCode);
	
	List<TdreamCategory> getCategoryList();
	
	List<TdreamCompetitor> getCompetitorByCategoryId(Integer categoryId);
	
	List<TdreamProduct> getProductListByCompetitorId(Integer competitorId);
	
	List<TdreamProduct> getProductListByKeyword(String keyword);
	
	void initCurrency();
	
	Integer selectProductCount(Date updateDate);
	
	TdreamProduct getProductById(Integer pkId);
	
	/**
	 * 查询单个产品的变化
	 * @param websitecode 平台代码
	 * @param productId 产品编号
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Map getProductChange(Integer productId);
	
	List<TdreamProduct> selectHotProduct(Date updateDate,Integer day);
	
	TdreamArticle getActicle(Integer id);
	
	List<TdreamArticle> getActicleTilttle();
	
	@SuppressWarnings("rawtypes")
	Map getCompetitor(Integer productId);
}
