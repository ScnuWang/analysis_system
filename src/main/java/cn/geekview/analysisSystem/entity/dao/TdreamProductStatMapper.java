package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamProductStat;

public interface TdreamProductStatMapper {
	/**
	 * 根据时间删除
	 * @param updateDate
	 */
	void deleteByUpdateDate(@Param("updateDate")Date updateDate);
	
	int insert(TdreamProductStat tdreamProductStat);
	
	TdreamProductStat selectStatisticsByCategory(@Param("categoryId")Integer categoryId);
	
	TdreamProductStat selectByCategoryId(@Param("updateDate")Date updateDate,@Param("categoryId")Integer categoryId);
}