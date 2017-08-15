package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamJdAnalysis;

public interface TdreamJdAnalysisMapper {

    int insert(TdreamJdAnalysis tdreamJdAnalysis);

    void updateProduct(TdreamJdAnalysis entity);
    
    TdreamJdAnalysis selectPrevAnalysis(@Param("productId")Integer productId,@Param("updateDate")Date updateDate);
    
    /**
	 * 根据产品编号删除某一天的分析信息
	 * @param updateDate
	 */
	void deleteByProductId(@Param("productId")Integer productId,@Param("updateDate")Date updateDate);
}