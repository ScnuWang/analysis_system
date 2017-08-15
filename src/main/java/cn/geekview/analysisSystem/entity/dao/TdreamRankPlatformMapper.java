package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface TdreamRankPlatformMapper {
	/**
	 * 根据时间删除
	 * @param updateDate
	 */
	void deleteByUpdateDate(@Param("updateDate")Date updateDate);
	/**
	 * 计算总的项目数
	 * @param updateDate
	 * @return
	 */
	Integer selectProductCount(@Param("updateDate")Date updateDate);

}