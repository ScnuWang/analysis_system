package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamXmAnalysis;

public interface TdreamXmAnalysisMapper {


    int insert(TdreamXmAnalysis tdreamJdAnalysis);

    void updateProduct(TdreamXmAnalysis entity);
    
    TdreamXmAnalysis selectPrevAnalysis(@Param("productId")Integer productId,@Param("updateDate")Date updateDate);
    
    /**
	 * 根据产品编号删除某一天的分析信息
	 * @param updateDate
	 */
	void deleteByProductId(@Param("productId")Integer productId,@Param("updateDate")Date updateDate);
}