package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamCompetitor;

public interface TdreamCompetitorMapper {
	/**
	 * 根据关键字获取
	 * @param compKwords
	 * @return
	 */
	TdreamCompetitor selectByCompKwords(String compKwords);
	/**
	 * 根据一级分类编号获取二级分类
	 * @return
	 */
	List<TdreamCompetitor> getCompetitorByCategoryId(@Param("categoryId") Integer categoryId);
	
	/**
	 * 根据关键字获取二级分类
	 * @param keyword
	 * @return
	 */
	List<TdreamCompetitor> getCompetitorListByKeyword(@Param("keyword") String keyword);
	
	/**
	 * 查询所有的competitor的
	 * @return
	 */
	List<TdreamCompetitor> selectNewCompetitorList();
	/**
	 * 查询所有的competitor
	 * @return
	 */
	List<TdreamCompetitor> selectAllCompetitor();
}