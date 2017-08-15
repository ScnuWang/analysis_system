package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamCategoryExtra {
    private Integer pkId;

    private Integer categoryId;

    private Date updateDate;

    private BigDecimal topProductMoney;

    private Integer topProductId;

    private BigDecimal lowProductMoney;

    private Integer lowProductId;

    private BigDecimal topCompetitorMoney;

    private Integer topCompetitorId;

    private BigDecimal lowCompetitorMoney;

    private Integer lowCompetitorId;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public BigDecimal getTopProductMoney() {
        return topProductMoney;
    }

    public void setTopProductMoney(BigDecimal topProductMoney) {
        this.topProductMoney = topProductMoney;
    }

    public Integer getTopProductId() {
        return topProductId;
    }

    public void setTopProductId(Integer topProductId) {
        this.topProductId = topProductId;
    }

    public BigDecimal getLowProductMoney() {
        return lowProductMoney;
    }

    public void setLowProductMoney(BigDecimal lowProductMoney) {
        this.lowProductMoney = lowProductMoney;
    }

    public Integer getLowProductId() {
        return lowProductId;
    }

    public void setLowProductId(Integer lowProductId) {
        this.lowProductId = lowProductId;
    }

    public BigDecimal getTopCompetitorMoney() {
        return topCompetitorMoney;
    }

    public void setTopCompetitorMoney(BigDecimal topCompetitorMoney) {
        this.topCompetitorMoney = topCompetitorMoney;
    }

    public Integer getTopCompetitorId() {
        return topCompetitorId;
    }

    public void setTopCompetitorId(Integer topCompetitorId) {
        this.topCompetitorId = topCompetitorId;
    }

    public BigDecimal getLowCompetitorMoney() {
        return lowCompetitorMoney;
    }

    public void setLowCompetitorMoney(BigDecimal lowCompetitorMoney) {
        this.lowCompetitorMoney = lowCompetitorMoney;
    }

    public Integer getLowCompetitorId() {
        return lowCompetitorId;
    }

    public void setLowCompetitorId(Integer lowCompetitorId) {
        this.lowCompetitorId = lowCompetitorId;
    }
}