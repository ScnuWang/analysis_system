package cn.geekview.analysisSystem.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamSnProject;

public interface TdreamSnProjectMapper {/**
	 * 根据平台编号、平台原始ID获取一个产品在一个时间段内的数据
	 * @param originId	   平台原始id
	 * @param startDate  开始时间
	 * @param endDate 	   结束 时间
	 * @return
	 */
	List<TdreamSnProject> getProductByOriginIdWithDaySpace(@Param("originId")Integer OriginId,@Param("beginDate")Date startDate,@Param("endDate")Date endDate);
	
	/**
	 * 获取具体某一天之后的产品列表
	 * @param updateDate
	 * @return
	 */
	List<TdreamSnProject> selectListByUpdateDate(@Param("updateDate")Date updateDate);
	
	/**
	 * 插入数据到t_dream_product表中
	 * @param 
	 * @return
	 */
	void insertProduct(TdreamSnProject tdreamSnProject);
	
	/**
	 * 更新t_dream_product表中的数据
	 * @param project
	 */
	void updateProduct(TdreamSnProject tdreamSnProject);
}