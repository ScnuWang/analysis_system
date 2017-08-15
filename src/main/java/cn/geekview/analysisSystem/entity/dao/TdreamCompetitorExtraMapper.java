package cn.geekview.analysisSystem.entity.dao;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;
import cn.geekview.analysisSystem.entity.model.TdreamCompetitorExtra;

public interface TdreamCompetitorExtraMapper {
	/**
	 * 根据competitorId查询
	 * @param competitorId
	 * @return
	 */
	TdreamCompetitorExtra selectByCompetitorId(@Param("competitorId") Integer competitorId);
	
	/**
	 * 更新排名信息
	 * @param updateDate
	 */
	void updateRankingInfo(@Param("updateDate") String updateDate,@Param("competitorId") Integer competitorId);
	
	/**
	 * 插入tdreamCompetitor表的pkId，categoryId
	 * @param tdreamCompetitor
	 */
	void insert(TdreamCompetitor tdreamCompetitor);
}