package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamAnalysis;

public interface TdreamAnalysisMapper {

	TdreamAnalysis selectByPrimaryKey(Integer pkId);

	List<TdreamAnalysis> selectAnalysisList(@Param("websiteCode")Integer websiteCode,@Param("productId")Integer productId);
}