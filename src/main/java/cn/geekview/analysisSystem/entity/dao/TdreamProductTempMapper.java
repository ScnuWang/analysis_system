package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamProductTemp;

public interface TdreamProductTempMapper {
	
	List<TdreamProductTemp> selectNewProductList();
	
	void updateProductEnabled(@Param("updateDate")Date updateDate);
	
	int insert(TdreamProductTemp tdreamProductTemp);
	/**
	 * 查询没有分类的产品列表
	 * @return
	 */
	List<TdreamProductTemp> selectNoClassifyList();
	
	int updateByPrimaryKey(TdreamProductTemp tdreamProductTemp);
}