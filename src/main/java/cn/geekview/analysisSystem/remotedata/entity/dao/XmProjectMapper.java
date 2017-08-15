package cn.geekview.analysisSystem.remotedata.entity.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.geekview.analysisSystem.entity.model.TdreamXmProject;


public interface XmProjectMapper {
	/**
	 * 根据平台编号、平台原始ID获取一个产品在一个时间段内的数据
	 * @param originId	   平台原始id
	 * @param startDate  开始时间
	 * @param endDate 	   结束 时间
	 * @return
	 */
	List<TdreamXmProject> getProductByOriginIdWithDaySpace(@Param("originId")Integer OriginId);
	
	/**
	 * 获取具体某一天之后的产品列表
	 * @param updateDate
	 * @return
	 */
	List<TdreamXmProject> selectListByUpdateDate(@Param("updateDate")Date updateDate);
	
}