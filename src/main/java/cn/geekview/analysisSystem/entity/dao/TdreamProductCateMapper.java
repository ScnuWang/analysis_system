package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamProduct;
import cn.geekview.analysisSystem.entity.model.TdreamProductCate;


public interface TdreamProductCateMapper {
	
	 TdreamProductCate selectByProductId(Integer productId);
	 
	 int insertFromProgram(TdreamProductCate tdreamProductCate);
	 
	 /**
	  * 根据二级分类编号获取这个下面的所有产品
	  * @param competitorId
	  * @return
	  */
	 List<TdreamProduct> getProductListByCompetitorId(@Param("competitorId")Integer competitorId);
	 
	 List<TdreamProduct> getProductListByCompetitorIdLimit(@Param("competitorId")Integer competitorId,@Param("limitsize")Integer limitsize);
	 
	 
}