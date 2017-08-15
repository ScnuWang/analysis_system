package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamProduct;

public interface TdreamProductMapper {
	/**
	 * 根据平台编号和原始id查询
	 * @param website
	 * @param originalId
	 * @return
	 */
	TdreamProduct selectByOriginalId(@Param("website")Integer website,@Param("originalId")String originalId);
	/**
	 * 根据平台编号查询平台下所有智能硬件的产品
	 * @param website
	 * @return
	 */
	List<TdreamProduct> getProductListByWebsiteCode(@Param("website")Integer website);
	
	/**
	 * 插入数据到t_dream_rank_project表
	 * @param updateDate
	 */
	void insertRankByUpdateDate(@Param("updateDate")Date updateDate);
	
	/**
	 * 插入数据到t_dream_rank_platform表
	 * @param website
	 * @param updateDate
	 * @param day
	 */
	void insertPlatformAnalysis(@Param("website")Integer website,@Param("updateDate")Date updateDate,@Param("day")Integer day);
	
	List<TdreamProduct> selectListByCategory(@Param("categoryId")Integer categoryId);
	
	Integer selectProductId(@Param("website")Integer website,@Param("originalId")String orid);
	
	
	TdreamProduct getProductById(@Param("pkId")Integer pkId);
	/**
	 * 
	 * @param updateDate
	 * @param day
	 * @return
	 */
	List<TdreamProduct> selectHotProduct(@Param("updateDate")Date updateDate,@Param("day")Integer day);
}