package cn.geekview.analysisSystem.entity.dao;

import java.util.List;

import cn.geekview.analysisSystem.entity.model.TdreamArticle;

public interface TdreamArticleMapper {

    TdreamArticle selectByPrimaryKey(Integer id);

    List<TdreamArticle> getActicleTilttle();
}