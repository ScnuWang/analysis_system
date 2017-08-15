package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface TdreamRankProjectMapper {
	
	/**
	 * 根据时间删除
	 * @param updateDate
	 */
	void deleteByUpdateDate(@Param("updateDate")Date updateDate);
	/**
	 * 更新排名信息
	 * @param updateDate
	 */
	void updateRankingInfo(@Param("updateDate")Date updateDate);
	
}