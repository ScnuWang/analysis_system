package cn.geekview.analysisSystem.entity.dao;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamServer;

public interface TdreamServerMapper {

	TdreamServer selectURLByTaskkey(@Param("taskCode")Integer taskCode,@Param("taskKey")Integer taskKey,@Param("taskStatus")Integer taskStatus);
}