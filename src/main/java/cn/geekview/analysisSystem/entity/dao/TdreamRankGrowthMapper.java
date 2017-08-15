package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamRankGrowth;

public interface TdreamRankGrowthMapper {
	
	/**
	 * 根据时间将国内国外排名，以及变化字段设置为null
	 * @param updateDate
	 */
	void deleteByUpdateDate(@Param("updateDate")Date updateDate);
	/**
	 * 更新排名信息
	 * @param updateDate
	 */
	void updateRankingInfo(@Param("updateDate")Date updateDate);
	
	
	int insert(TdreamRankGrowth tdreamRankGrowth);
	/**
	 * 根据产品编号删除某一天的排名信息
	 * @param updateDate
	 */
	void deleteByProductId(@Param("productId")Integer productId,@Param("updateDate")Date updateDate);
	
	/**
	 * 根据产品编号查询
	 */
	List<TdreamRankGrowth> selectListByProductId(@Param("productId")Integer productId);
}