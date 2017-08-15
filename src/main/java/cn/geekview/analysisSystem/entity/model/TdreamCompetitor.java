package cn.geekview.analysisSystem.entity.model;

import java.math.BigDecimal;
import java.util.Date;

public class TdreamCompetitor {
    private Integer pkId;

    private Integer categoryId;

    private String compName;

    private String compDesc;

    private String compKwords;

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

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName == null ? null : compName.trim();
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc == null ? null : compDesc.trim();
    }

    public String getCompKwords() {
        return compKwords;
    }

    public void setCompKwords(String compKwords) {
        this.compKwords = compKwords == null ? null : compKwords.trim();
    }
    
    private Integer competitorId;

    private Date updateDate;

    private BigDecimal topProductMoney;

    private Integer topProductMoneyId;

    private BigDecimal lowProductMoney;

    private Integer lowProductMoneyId;

    private Integer topProductPeople;

    private Integer topProductPeopleId;

    private Integer lowProductPeople;

    private Integer lowPeoductPeopleId;

    private BigDecimal lowCoreprice;

    private Integer lowCorepriceProductId;

    private BigDecimal topCoreproce;

    private Integer topCorepriceProductId;

    private BigDecimal topProductPeopleIdCoreprice;
    
    

    public Integer getCompetitorId() {
        return competitorId;
    }

    public void setCompetitorId(Integer competitorId) {
        this.competitorId = competitorId;
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

    public Integer getTopProductMoneyId() {
        return topProductMoneyId;
    }

    public void setTopProductMoneyId(Integer topProductMoneyId) {
        this.topProductMoneyId = topProductMoneyId;
    }

    public BigDecimal getLowProductMoney() {
        return lowProductMoney;
    }

    public void setLowProductMoney(BigDecimal lowProductMoney) {
        this.lowProductMoney = lowProductMoney;
    }

    public Integer getLowProductMoneyId() {
        return lowProductMoneyId;
    }

    public void setLowProductMoneyId(Integer lowProductMoneyId) {
        this.lowProductMoneyId = lowProductMoneyId;
    }

    public Integer getTopProductPeople() {
        return topProductPeople;
    }

    public void setTopProductPeople(Integer topProductPeople) {
        this.topProductPeople = topProductPeople;
    }

    public Integer getTopProductPeopleId() {
        return topProductPeopleId;
    }

    public void setTopProductPeopleId(Integer topProductPeopleId) {
        this.topProductPeopleId = topProductPeopleId;
    }

    public Integer getLowProductPeople() {
        return lowProductPeople;
    }

    public void setLowProductPeople(Integer lowProductPeople) {
        this.lowProductPeople = lowProductPeople;
    }

    public Integer getLowPeoductPeopleId() {
        return lowPeoductPeopleId;
    }

    public void setLowPeoductPeopleId(Integer lowPeoductPeopleId) {
        this.lowPeoductPeopleId = lowPeoductPeopleId;
    }

    public BigDecimal getLowCoreprice() {
        return lowCoreprice;
    }

    public void setLowCoreprice(BigDecimal lowCoreprice) {
        this.lowCoreprice = lowCoreprice;
    }

    public Integer getLowCorepriceProductId() {
        return lowCorepriceProductId;
    }

    public void setLowCorepriceProductId(Integer lowCorepriceProductId) {
        this.lowCorepriceProductId = lowCorepriceProductId;
    }

    public BigDecimal getTopCoreproce() {
        return topCoreproce;
    }

    public void setTopCoreproce(BigDecimal topCoreproce) {
        this.topCoreproce = topCoreproce;
    }

    public Integer getTopCorepriceProductId() {
        return topCorepriceProductId;
    }

    public void setTopCorepriceProductId(Integer topCorepriceProductId) {
        this.topCorepriceProductId = topCorepriceProductId;
    }

	public BigDecimal getTopProductPeopleIdCoreprice() {
		return topProductPeopleIdCoreprice;
	}

	public void setTopProductPeopleIdCoreprice(
			BigDecimal topProductPeopleIdCoreprice) {
		this.topProductPeopleIdCoreprice = topProductPeopleIdCoreprice;
	}
    
    
}