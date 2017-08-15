package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamCategory;

public interface TdreamCategoryMapper {
	/**
	 * 获取所有的一级分类
	 * @return
	 */
	List<TdreamCategory> getCategoryList();
	
	void updateCategoryInfo(@Param("updateDate")Date updateDate,@Param("day")Integer day);
	
	List<TdreamCategory> selectCategoryList();
}