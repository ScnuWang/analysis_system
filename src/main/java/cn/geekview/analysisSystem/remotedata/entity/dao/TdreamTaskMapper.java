package cn.geekview.analysisSystem.remotedata.entity.dao;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

public interface TdreamTaskMapper {

	
	int selectCountByUpdateDate(@Param("websiteCode")Integer websiteCode,@Param("updateDate")Date updateDate);

	
}