package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamJdProject;

public interface TdreamJdProjectMapper {
	/**
	 * 根据平台编号、平台原始ID获取一个产品在一个时间段内的数据
	 * @param originId	   平台原始id
	 * @param startDate  开始时间
	 * @param endDate 	   结束 时间
	 * @return
	 */
	List<TdreamJdProject> getProductByOriginIdWithDaySpace(@Param("originId")Integer OriginId,@Param("beginDate")Date startDate,@Param("endDate")Date endDate);
	
	/**
	 * 获取具体某一天之后的产品列表
	 * @param updateDate
	 * @return
	 */
	List<TdreamJdProject> selectListByUpdateDate(@Param("updateDate")Date updateDate);
	
	/**
	 * 插入数据到t_dream_product表中
	 * @param 
	 * @return
	 */
	void insertProduct(TdreamJdProject tdreamJdProject);
	
	/**
	 * 更新t_dream_product表中的数据
	 * @param project
	 */
	void updateProduct(TdreamJdProject tdreamJdProject);
	
}