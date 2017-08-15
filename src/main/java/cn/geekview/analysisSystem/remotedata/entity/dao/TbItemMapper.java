package cn.geekview.analysisSystem.remotedata.entity.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamTbItem;

public interface TbItemMapper {
	/**
	 * 根据产品编号获取对应项目在一个时间段的数据
	 * @param projectId  产品ID(内部数据库里面的编号)
	 * @return
	 */
	List<TdreamTbItem> getItemsByProjectId(@Param("projectId") Integer projectId);
	
   
}