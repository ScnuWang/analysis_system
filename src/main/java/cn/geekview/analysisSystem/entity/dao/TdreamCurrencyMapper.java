package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import cn.geekview.analysisSystem.entity.model.TdreamCurrency;

public interface TdreamCurrencyMapper {
	/**
	 * 查询汇率表
	 * @return
	 */
	List<TdreamCurrency> selectCurrencyList();
}