package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamWebsite;



public interface TdreamWebsiteMapper {
	
	TdreamWebsite getWebsiteByWebsiteCode(@Param("websiteCode") Integer websiteCode);
	
	List<TdreamWebsite> selectRankWebsiteList();
	
}