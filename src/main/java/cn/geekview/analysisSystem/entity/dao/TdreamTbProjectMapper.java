package cn.geekview.analysisSystem.entity.dao;

import cn.geekview.analysisSystem.entity.model.TdreamTbProject;

public interface TdreamTbProjectMapper {
	
	/**
	 * 插入数据到t_dream_product表中
	 * @param 
	 * @return
	 */
	void insertProduct(TdreamTbProject tdreamTbProject);
	
	/**
	 * 更新t_dream_product表中的数据
	 * @param project
	 */
	void updateProduct(TdreamTbProject tdreamTbProject);
	
}